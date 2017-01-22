package com.travelsmart.fourloop.travelsmart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import Beans.Passenger;
import Beans.ServiceProvider;
import Beans.User;

public class Signup1 extends AppCompatActivity {

    StringRequest request;
    RequestQueue requestQueue;
    EditText mobileNum;
    String mobile;

    Url URL = new Url("signup1.php");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup1);

        mobileNum = (EditText)findViewById(R.id.MobileNumEt1);
        requestQueue = Volley.newRequestQueue(this);
    }

    public void Next1(View view) {

        mobile = mobileNum.getText().toString();

        if(mobileNum.getText().length()==10){
            request = new StringRequest(Request.Method.POST,URL.getUrl(), new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        if(!(jsonObject.names().get(0).equals("fail"))){
                            String pin = jsonObject.names().get(0).toString();
                            Intent intent = new Intent(Signup1.this,Signup2.class);
                            intent.putExtra("pin",pin);
                            intent.putExtra("mobileNum",mobile);
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
                    hashMap.put("mobileNum1", mobile);
                    hashMap.put("mobileNum2", "+94"+mobile.substring(1,10));
                    return hashMap;
                }
            };
            requestQueue.add(request);

        }
        else{
            //invalid mobile number
        }
    }
}
