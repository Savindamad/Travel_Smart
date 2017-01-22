package com.travelsmart.fourloop.travelsmart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Signup2 extends AppCompatActivity {
    String mobileNum;
    String pin;

    EditText codeEt;
    TextView textTv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup2);

        Intent intentpr = getIntent();
        mobileNum = intentpr.getStringExtra("mobileNum");
        pin = intentpr.getStringExtra("pin");

        textTv2 = (TextView)findViewById(R.id.textTv2);
        String text = "Please enter your verification code that was sent to "+mobileNum;
        textTv2.setText(text);

        codeEt = (EditText)findViewById(R.id.CodeEt);

    }

    public void Done1(View view) {
        if(codeEt.getText().toString().equals(pin)){
            Intent intent = new Intent(Signup2.this,Signup3.class);
            intent.putExtra("mobileNum",mobileNum);
            startActivity(intent);
        }
        else{
            //invalid pin
        }

    }

    public void ResendCode(View view) {
    }

    public void ChangeNumber(View view) {
        Intent intent = new Intent(Signup2.this,Signup1.class);
        startActivity(intent);
    }
}
