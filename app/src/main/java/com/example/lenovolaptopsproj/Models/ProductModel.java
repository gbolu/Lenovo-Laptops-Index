package com.example.lenovolaptopsproj.Models;

import java.util.ArrayList;

public class ProductModel {
    String laptop_sub_brand;
    String laptop_name;
    String laptop_imageRef;
    String laptop_brand;
    String laptop_description;
    Boolean hasMultipleProcessors;
    ArrayList<ArrayList<String>> specs;

    public ProductModel() { }

    public ArrayList<ArrayList<String>> getSpecs() {
        return specs;
    }

    public void setSpecs(ArrayList<ArrayList<String>> specs) {
        this.specs = specs;
    }

    public Boolean getHasMultipleProcessors() {
        return hasMultipleProcessors;
    }

    public void setHasMultipleProcessors(Boolean hasMultipleProcessors) {
        this.hasMultipleProcessors = hasMultipleProcessors;
    }

    public String getLaptop_brand() {
        return laptop_brand;
    }

    public void setLaptop_brand(String laptop_brand) {
        this.laptop_brand = laptop_brand;
    }

    public String getLaptop_name() {
        return laptop_name;
    }

    public void setLaptop_name(String laptop_name) {
        this.laptop_name = laptop_name;
    }

    public String getLaptop_description() {
        return laptop_description;
    }

    public void setLaptop_description(String laptop_description) {
        this.laptop_description = laptop_description;
    }

    public String getLaptop_imageRef() {
        return laptop_imageRef;
    }

    public void setLaptop_imageRef(String laptop_imageRef) {
        this.laptop_imageRef = laptop_imageRef;
    }

    public String getLaptop_sub_brand() {
        return laptop_sub_brand;
    }

    public void setLaptop_sub_brand(String laptop_sub_brand) {
        this.laptop_sub_brand = laptop_sub_brand;
    }
}
