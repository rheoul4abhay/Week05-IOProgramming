package com.io_programming.AdvancedProblems.Encrypt_Decrypt_CSV_Data;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Base64;

public class EncryptDecryptCSV {

    private static final String SECRET_KEY = "1234567890123456"; // 16-byte AES key

    // Encrypts data using AES encryption
    public static String encrypt(String data) {
        try {
            SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptedBytes = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(encryptedBytes); // Base64 encode to store in CSV
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Decrypts data using AES decryption
    public static String decrypt(String encryptedData) {
        try {
            byte[] encryptedBytes = Base64.getDecoder().decode(encryptedData); // Base64 decode
            SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
            return new String(decryptedBytes, StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Method to write CSV data with encrypted sensitive fields (Salary, Email)
    public static void writeCSVWithEncryptedData(String csvFilePath, List<String[]> data) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(csvFilePath))) {
            // Write header
            String[] header = {"ID", "Name", "Email", "Salary"};
            writer.writeNext(header);

            // Write data with encryption
            for (String[] row : data) {
                row[2] = encrypt(row[2]);  // Encrypt Email
                row[3] = encrypt(row[3]);  // Encrypt Salary
                writer.writeNext(row);
            }
            System.out.println("CSV file written with encrypted data!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to read CSV data with decrypted sensitive fields (Salary, Email)
    public static void readCSVWithDecryptedData(String csvFilePath) {
        try (CSVReader reader = new CSVReader(new FileReader(csvFilePath))) {
            List<String[]> data = reader.readAll();

            // Print data with decryption
            for (String[] row : data) {
                if (row[0].equals("ID")) continue;  // Skip header row
                row[2] = decrypt(row[2]);  // Decrypt Email
                row[3] = decrypt(row[3]);  // Decrypt Salary
                System.out.println("ID: " + row[0] + ", Name: " + row[1] + ", Email: " + row[2] + ", Salary: " + row[3]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Sample data for CSV
        List<String[]> data = Arrays.asList(
                new String[]{"1", "John Doe", "john@example.com", "50000"},
                new String[]{"2", "Alice Smith", "alice@example.com", "45000"},
                new String[]{"3", "Bob Johnson", "bob@example.com", "55000"}
        );

        String csvFilePath = "src/main/java/com/io_programming/AdvancedProblems/Encrypt_Decrypt_CSV_Data/encrypted_employees.csv";

        // Write encrypted data to CSV
        writeCSVWithEncryptedData(csvFilePath, data);

        // Read and decrypt data from CSV
        System.out.println("Reading decrypted data from CSV:");
        readCSVWithDecryptedData(csvFilePath);
    }
}
