package com.travelsmart.fourloop.travelsmart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import Beans.Passenger;
import Beans.ServiceProvider;
import Beans.User;

public class SwitchAccount extends AppCompatActivity {

    User user;
    ServiceProvider serviceProvider;
    Passenger passenger;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch_account);

        Intent intentpr = getIntent();
        user = (User)intentpr.getSerializableExtra("user");
    }

    public void SwitchPassenger(View view) {
        Intent intent = new Intent(SwitchAccount.this,Home.class);
        intent.putExtra("user",user);
        startActivity(intent);
    }

    public void SwitchServiceProvider(View view) {
        Intent intent = new Intent(SwitchAccount.this,Home1.class);
        intent.putExtra("user",user);
        startActivity(intent);
    }
}
