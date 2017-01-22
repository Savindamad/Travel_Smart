package com.travelsmart.fourloop.travelsmart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import Beans.Passenger;
import Beans.ServiceProvider;
import Beans.User;

public class Login extends AppCompatActivity {
    EditText mobileNumEt;
    EditText passwordEt;

    User user;
    ServiceProvider serviceProvider;
    Passenger passenger;

    StringRequest request;
    RequestQueue requestQueue;

    StringRequest request1;
    RequestQueue requestQueue1;

    StringRequest request2;
    RequestQueue requestQueue2;

    Url URL = new Url("login1.php");
    Url URL1 = new Url("getsp.php");
    Url URL2 = new Url("getp.php");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mobileNumEt = (EditText)findViewById(R.id.MobileNumEt2);
        passwordEt = (EditText)findViewById(R.id.PasswordEt1);
        requestQueue = Volley.newRequestQueue(this);
        requestQueue1 = Volley.newRequestQueue(this);
        requestQueue2 = Volley.newRequestQueue(this);

    }

    public void Login2(View view) {

        request = new StringRequest(Request.Method.POST,URL.getUrl(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);

                    if(!(jsonObject.names().equals("fail"))){
                        int flag = 0 ;

                        String mobileNum = jsonObject.getString("mobile_num");
                        String password = jsonObject.getString("password");
                        String name = jsonObject.getString("name");
                        String email = jsonObject.getString("email");
                        String nicNum = jsonObject.getString("nic_no");
                        String sp_id = jsonObject.getString("sp_id");
                        String p_id = jsonObject.getString("p_id");

                        user = new User(mobileNum,name,email,password,nicNum);
                        user.setProfilePic(jsonObject.getString("prof_pic"));
                        user.setNicImg(jsonObject.getString("nic_image"));

                        if(!(jsonObject.getString("sp_id").equals("0"))){
                            flag = 1;
                            user.setSpID(sp_id);
                            getServiceProvider(sp_id);
                        }
                        if(!(jsonObject.getString("p_id").equals(null))){
                            if(flag == 1){
                                flag = 3;
                            }
                            else{
                                flag = 2;
                            }
                            user.setpID(sp_id);
                            getPassenger(p_id);
                        }
                        SwitchAccount(flag);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> hashMap = new HashMap<String, String>();
                hashMap.put("mobileNum", mobileNumEt.getText().toString());
                hashMap.put("password", passwordEt.getText().toString());
                return hashMap;
            }
        };

        requestQueue.add(request);

        SwitchAccount(1);
    }

    public void getServiceProvider(String spId){

        request1 = new StringRequest(Request.Method.POST,URL1.getUrl(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if(!(jsonObject.names().equals("fail"))){

                        serviceProvider = new ServiceProvider(jsonObject.getString("license_no"),jsonObject.getString("vehicle_licence_no"),jsonObject.getString("vehicle_no"),jsonObject.getString("vehicle_type"),jsonObject.getString("num_of_seats"),jsonObject.getString("licence_image"));
                        user.setSp(serviceProvider);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> hashMap = new HashMap<String, String>();
                hashMap.put("sp_id", user.getSpID());
                return hashMap;
            }
        };

        requestQueue1.add(request1);

    }
    public void getPassenger(String pId){

        request2 = new StringRequest(Request.Method.POST,URL2.getUrl(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if(!(jsonObject.names().equals("fail"))){
                        passenger = new Passenger(jsonObject.getString("credit_card_no"),jsonObject.getString("month"),jsonObject.getString("year"),jsonObject.getString("cvv"));
                        user.setP(passenger);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> hashMap = new HashMap<String, String>();
                hashMap.put("p_id", user.getpID());
                return hashMap;
            }
        };

        requestQueue2.add(request1);

    }
    public void SwitchAccount(int flag){
        if(flag==1){
            Intent intent = new Intent(Login.this,Home1.class);
            intent.putExtra("user",user);
            startActivity(intent);
        }
        else if(flag==2){
            Intent intent = new Intent(Login.this,Home.class);
            intent.putExtra("user",user);
            startActivity(intent);
        }
        else if(flag==3){
            Intent intent = new Intent(Login.this,SwitchAccount.class);
            intent.putExtra("user",user);
            startActivity(intent);
        }
    }

}
