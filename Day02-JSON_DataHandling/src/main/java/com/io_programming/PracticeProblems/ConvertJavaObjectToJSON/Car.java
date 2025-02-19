package com.io_programming.PracticeProblems.ConvertJavaObjectToJSON;

public class Car {
    private String name;
    private String brand;
    private boolean isElectrical;
    private double price;

    public Car(String name, String brand, boolean isElectrical, double price){
        this.name = name;
        this.brand = brand;
        this.isElectrical = isElectrical;
        this.price = price;
    }

    public void setPrice(double newPrice){
        price = newPrice;
    }

    public double getPrice(){
        return price;
    }

    public void setName(String newName){
        name = newName;
    }

    public String getName(){
        return name;
    }

    public void setElectrical(boolean newValue){
        isElectrical = newValue;
    }

    public boolean getElectrical(){
        return isElectrical;
    }

    public void setBrand(String newBrand){
        brand = newBrand;
    }

    public String getBrand(){
        return brand;
    }
}
