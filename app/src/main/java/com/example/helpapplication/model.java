package com.example.helpapplication;

public class model {
    String companyName,landmark,location,personnel,phoneNumber,plateNumber,vehicleType;

    public model() {
    }

    public model(String companyName, String landmark, String location, String personnel, String phoneNumber, String plateNumber, String vehicleType) {
        this.companyName = companyName;
        this.landmark = landmark;
        this.location = location;
        this.personnel = personnel;
        this.phoneNumber = phoneNumber;
        this.plateNumber = plateNumber;
        this.vehicleType = vehicleType;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPersonnel() {
        return personnel;
    }

    public void setPersonnel(String personnel) {
        this.personnel = personnel;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }
}
