package com.example.lenovolaptopsproj.Models;

import org.json.JSONArray;

import java.util.ArrayList;

public class BrandModel
{
    String brandName;
    String[] subBrands;
    String details;
    String videoRef;
    
    public BrandModel() {}

    public BrandModel(String brandName, String[] subBrands, String details, String videoRef) {
        this.brandName = brandName;
        this.subBrands = subBrands;
        this.details = details;
        this.videoRef = videoRef;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String[] getSubBrands() {
        return subBrands;
    }

    public void setSubBrands(String[] subBrands) {
        this.subBrands = subBrands;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getVideoRef() {
        return videoRef;
    }

    public void setVideoRef(String videoRef) {
        this.videoRef = videoRef;
    }
}
