package com.travelsmart.fourloop.travelsmart;
public class Url {
    //String url = "http://10.0.2.2/TravelSmart/";
    String url = "http://192.168.43.26/TravelSmart/";
    Url(String url){
        this.url=this.url+url;
    }
    public String getUrl(){
        return url;
    }
}
