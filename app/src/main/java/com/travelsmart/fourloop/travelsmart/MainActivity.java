package com.travelsmart.fourloop.travelsmart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Login1(View view) {
        //Intent intent = new Intent(MainActivity.this,Login.class);
        Intent intent = new Intent(MainActivity.this, Login.class);
        startActivity(intent);
    }
    public void Signup1(View view) {
        Intent intent = new Intent(MainActivity.this,Signup1.class);
        startActivity(intent);
    }
}
