package com.travelsmart.fourloop.travelsmart;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.volley.*;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import Beans.Passenger;
import Beans.ServiceProvider;
import Beans.User;

public class Signup4 extends AppCompatActivity {
    User user;
    ServiceProvider serviceProvider;
    Passenger passenger;
    int flag;

    Button passengerBtn;
    Button serviceProviderBtn;
    Button doneBtn;

    StringRequest request;
    RequestQueue requestQueue;
    Url URL = new Url("signup2.php");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup4);

        requestQueue = Volley.newRequestQueue(this);

        Intent intentpr = getIntent();
        user = (User)intentpr.getSerializableExtra("user");
        flag = intentpr.getIntExtra("flag", 0);

        passengerBtn = (Button)findViewById(R.id.PassangerBtn);
        serviceProviderBtn = (Button)findViewById(R.id.ServiceProviderBtn);
        doneBtn = (Button)findViewById(R.id.DoneBtn2);

        if(flag == 1){
            //disable buttons and change button color
            //serviceProviderBtn.setBackgroundColor(Color.parseColor("#E7FC3A"));
            serviceProvider = (ServiceProvider)intentpr.getSerializableExtra("serviceProvider");
        }
        else if(flag == 2){
            //disable buttons and change button color
           // passengerBtn.setBackgroundResource(R.drawable.my_button_bg2);
            passenger = (Passenger)intentpr.getSerializableExtra("passenger");
        }
        else if(flag == 3){
            //disable buttons and change button color
            passenger = (Passenger)intentpr.getSerializableExtra("passenger");
            serviceProvider = (ServiceProvider)intentpr.getSerializableExtra("serviceProvider");
        }

    }

    public void ServiceProvider(View view) {
        if(flag == 0 || flag == 1){
            Intent intent = new Intent(Signup4.this,SignupSP1.class);
            intent.putExtra("flag",flag);
            intent.putExtra("user",user);
            startActivity(intent);
        }

        else if(flag == 2){
            Intent intent = new Intent(Signup4.this,SignupSP1.class);
            intent.putExtra("flag", flag);
            intent.putExtra("user",user);
            intent.putExtra("passenger",passenger);
            startActivity(intent);
        }
    }

    public void Passenger(View view) {
        Intent intent = new Intent(Signup4.this,SignupP1.class);
        intent.putExtra("user",user);
        if(flag == 0 || flag == 2){
            intent.putExtra("flag",flag);
            intent.putExtra("user",user);
            startActivity(intent);
        }

        else if(flag == 1){
            intent.putExtra("flag",flag);
            intent.putExtra("user",user);
            intent.putExtra("serviceProvider",serviceProvider);
            startActivity(intent);
        }
    }

    public void Done2(View view) {
        if(flag != 0){
            Signup(flag);
        }
        else{
            //have to register as passenger or service provider
        }

    }
    public void Signup(final int flag){
        if(flag == 1){
            request = new StringRequest(com.android.volley.Request.Method.POST,URL.getUrl(), new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        if(!(jsonObject.names().get(0).equals("fail"))){
                            Intent intent = new Intent(Signup4.this,Login.class);
                            startActivity(intent);
                        }
                        else {
                            Intent intent = new Intent(Signup4.this,Login.class);
                            /*
                            intent.putExtra("user",user);
                            intent.putExtra("serviceProvider",serviceProvider);*/
                            startActivity(intent);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.println(error);
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    HashMap<String, String> hashMap = new HashMap<String, String>();
                    hashMap.put("flag","1");
                    hashMap.put("nic_no",user.getNicNum());
                    hashMap.put("name",user.getName() );
                    hashMap.put("email",user.getEmail());
                    hashMap.put("mobile_no",user.getMobileNum());
                    hashMap.put("password",user.getPassword());
                    hashMap.put("nic_img",user.getNicImg());
                    hashMap.put("licenceNum",serviceProvider.getLicenceNum());
                    hashMap.put("licenceNumImg",serviceProvider.getLicenceImg());
                    hashMap.put("vehicleNum",serviceProvider.getVehicleNum());
                    hashMap.put("vehicleLicenceNum",serviceProvider.getVehicleLicenceNum());
                    hashMap.put("vehicleType",serviceProvider.getVehicleType());
                    hashMap.put("numberOfSeats",serviceProvider.getNumberOfSeats());
                    hashMap.put("bank",serviceProvider.getBank());
                    hashMap.put("branch",serviceProvider.getBranch());
                    hashMap.put("accountNum",serviceProvider.getAccountNum());
                    hashMap.put("accountName",serviceProvider.getName());

                    return hashMap;
                }
            };
            requestQueue.add(request);
        }
        else if(flag==2){
            request = new StringRequest(com.android.volley.Request.Method.POST,URL.getUrl(), new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        if(!(jsonObject.names().get(0).equals("fail"))){
                            Intent intent = new Intent(Signup4.this,Login.class);

                            /*intent.putExtra("user",user);
                            intent.putExtra("passenger",passenger);*/
                            startActivity(intent);
                        }
                        else {
                            //mobile number already exists
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.println(error);
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    HashMap<String, String> hashMap = new HashMap<String, String>();
                    hashMap.put("flag","2");
                    hashMap.put("mobileNum",user.getMobileNum());
                    hashMap.put("name",user.getName() );
                    hashMap.put("email",user.getEmail());
                    hashMap.put("password",user.getPassword());
                    hashMap.put("nic",user.getNicNum());
                    hashMap.put("nicImg",user.getNicImg());
                    hashMap.put("creditCrdNum",passenger.getCreditCrdNum());
                    hashMap.put("month","asd");
                    hashMap.put("year","asd");
                    hashMap.put("cvv","asd");

                    return hashMap;
                }
            };
            requestQueue.add(request);

        }
        else if(flag==3){
            request = new StringRequest(com.android.volley.Request.Method.POST,URL.getUrl(), new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        if(!(jsonObject.names().get(0).equals("fail"))){
                            Intent intent = new Intent(Signup4.this,Login.class);
                            /*
                            intent.putExtra("user",user);
                            intent.putExtra("serviceProvider",serviceProvider);
                            intent.putExtra("passenger",passenger); */

                            startActivity(intent);
                        }
                        else {

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.println(error);
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    HashMap<String, String> hashMap = new HashMap<String, String>();
                    hashMap.put("flag","3");
                    hashMap.put("mobileNum",user.getMobileNum());
                    hashMap.put("name",user.getName() );
                    hashMap.put("email",user.getEmail());
                    hashMap.put("password",user.getPassword());
                    hashMap.put("nic",user.getNicNum());
                    hashMap.put("nicImg",user.getNicImg());
                    hashMap.put("licenceNum",serviceProvider.getLicenceNum());
                    hashMap.put("LicenceNumImg",serviceProvider.getLicenceImg());
                    hashMap.put("vehicleNum",serviceProvider.getVehicleNum());
                    hashMap.put("vehicleLicenceNum",serviceProvider.getVehicleLicenceNum());
                    hashMap.put("vehicleType",serviceProvider.getVehicleType());
                    hashMap.put("numberOfSeats",serviceProvider.getNumberOfSeats());
                    hashMap.put("bank",serviceProvider.getBank());
                    hashMap.put("branch",serviceProvider.getBranch());
                    hashMap.put("accountNum",serviceProvider.getAccountNum());
                    hashMap.put("accountName",serviceProvider.getName());
                    hashMap.put("creditCrdNum",passenger.getCreditCrdNum());
                    hashMap.put("month","asd");
                    hashMap.put("year","asd");
                    hashMap.put("cvv","asd");

                    return hashMap;
                }
            };
            requestQueue.add(request);
        }
    }

}
