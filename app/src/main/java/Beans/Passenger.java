package Beans;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by Savinda Keshan on 10/16/2016.
 */
public class Passenger implements Serializable {
    String creditCrdNum;
    String month;
    String year;
    String cvv;

    public Passenger(String creditCrdNum, String month, String year, String cvv) {
        this.creditCrdNum = creditCrdNum;
        this.month = month;
        this.year = year;
        this.cvv = cvv;
    }

    public String getCreditCrdNum() {
        return creditCrdNum;
    }

    public String getMonth() {
        return month;
    }

    public String getYear() {
        return year;
    }

    public String getCvv() {
        return cvv;
    }


}
