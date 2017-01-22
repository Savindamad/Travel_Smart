package Beans;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by Savinda Keshan on 10/16/2016.
 */
public class ServiceProvider implements Serializable {
    String licenceNum;
    String vehicleLicenceNum;
    String vehicleNum;
    String vehicleType;
    String numberOfSeats;
    String bank;
    String branch;
    String accountNum;
    String accountName;

    String licenceImg;

    public ServiceProvider(String licenceNum, String vehicleLicenceNum, String vehicleNum, String vehicleType, String numberOfSeats, String licenceImg) {
        this.licenceNum = licenceNum;
        this.vehicleLicenceNum = vehicleLicenceNum;
        this.vehicleNum = vehicleNum;
        this.vehicleType = vehicleType;
        this.numberOfSeats = numberOfSeats;
        this.licenceImg = licenceImg;
    }

    public String getLicenceNum() {
        return licenceNum;
    }

    public String getVehicleLicenceNum() {
        return vehicleLicenceNum;
    }

    public String getVehicleNum() {
        return vehicleNum;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public String getNumberOfSeats() {
        return numberOfSeats;
    }

    public String getBank() {
        return bank;
    }

    public String getBranch() {
        return branch;
    }

    public String getAccountNum() {
        return accountNum;
    }

    public String getName() {
        return accountName;
    }

    public String getLicenceImg() {
        return licenceImg;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public void setAccountNum(String accountNum) {
        this.accountNum = accountNum;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }
}
