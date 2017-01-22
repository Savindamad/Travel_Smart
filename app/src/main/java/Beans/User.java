package Beans;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by Savinda Keshan on 10/16/2016.
 */
public class User implements Serializable {
    String mobileNum;
    String name;
    String email;
    String password;
    String nicNum;
    String spID;
    String pID;
    String NicImg;
    String profilePic;
    
    ServiceProvider sp;
    Passenger p;

    public User(String mobileNum, String name, String email,String password, String nicNum) {
        this.mobileNum = mobileNum;
        this.name = name;
        this.email = email;
        this.nicNum = nicNum;
        this.password = password;
        spID = null;
        pID = null;
        sp = null;
        p = null;
    }

    public String getMobileNum() {
        return mobileNum;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getNicNum() {
        return nicNum;
    }

    public String getSpID() {
        return spID;
    }

    public String getpID() {
        return pID;
    }

    public ServiceProvider getSp() {
        return sp;
    }

    public Passenger getP() {
        return p;
    }

    public void setP(Passenger p) {
        this.p = p;
    }

    public String getNicImg() {
        return NicImg;
    }

    public void setSp(ServiceProvider sp) {
        this.sp = sp;
    }

    public void setpID(String pID) {
        this.pID = pID;
    }

    public void setSpID(String spID) {
        this.spID = spID;
    }

    public void setNicImg(String nicImg) {
        NicImg = nicImg;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getPassword() {
        return password;
    }
}
