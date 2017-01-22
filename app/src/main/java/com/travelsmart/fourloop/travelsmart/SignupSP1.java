package com.travelsmart.fourloop.travelsmart;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import Beans.Passenger;
import Beans.ServiceProvider;
import Beans.User;

public class SignupSP1 extends AppCompatActivity {

    User user;
    ServiceProvider serviceProvider;
    Passenger passenger;

    EditText licenceNumEt;
    EditText vehicleLicenceNumEt;
    EditText vehicleNumEt;

    ImageView licenceImg;

    Spinner vehicleTypeSp;
    Spinner numOfSeatsSp;

    Boolean uploadImg = false;

    Bitmap licenceImgBit;

    int flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_sp1);

        Intent intentpr = getIntent();
        user = (User)intentpr.getSerializableExtra("user");
        flag = intentpr.getIntExtra("flag",0);

        if(flag==2){
            passenger = (Passenger)intentpr.getSerializableExtra("passenger");
        }

        licenceNumEt = (EditText)findViewById(R.id.LicenceNumEt);
        vehicleLicenceNumEt = (EditText)findViewById(R.id.VehicleLicenceNumEt);
        vehicleNumEt = (EditText)findViewById(R.id.VehicleNumEt);

        licenceImg = (ImageView)findViewById(R.id.LicenceImgIv);

        vehicleTypeSp = (Spinner)findViewById(R.id.VehicleTypeSp);
        numOfSeatsSp = (Spinner)findViewById(R.id.NumOfSeatsSp);


        vehicleTypeSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String item = vehicleTypeSp.getSelectedItem().toString();
                int arrayNum = getResources().getIdentifier(item, "array", SignupSP1.this.getPackageName());

                ArrayAdapter<String> vehicleTypeAdapter = new ArrayAdapter<String>(SignupSP1.this,android.R.layout.simple_spinner_dropdown_item,getResources().getStringArray(arrayNum));
                numOfSeatsSp.setAdapter(vehicleTypeAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void Done4(View view) {
        System.out.println("a"+flag);
        if(validation()){
            System.out.println("b");
            String licenceNum = licenceNumEt.getText().toString();
            String vehicleLicenceNum = vehicleLicenceNumEt.getText().toString();
            String vehicleNum = vehicleNumEt.getText().toString();
            //String vehicleType = vehicleTypeSp.getSelectedItem().toString();
            //int numOfSeats = Integer.parseInt(numOfSeatsSp.getSelectedItem().toString());

            serviceProvider = new ServiceProvider(licenceNum,vehicleLicenceNum,vehicleNum,"car","5","image");

            Intent intent = new Intent(SignupSP1.this,SignupSP2.class);
            intent.putExtra("user", user);
            intent.putExtra("serviceProvider",serviceProvider);
            if(flag == 0){
                flag = 1;
                intent.putExtra("flag",flag);
                startActivity(intent);
            }
            else if(flag == 2){
                flag =3;
                intent.putExtra("flag",flag);
                intent.putExtra("passenger",passenger);
                startActivity(intent);
            }
        }
    }

    public boolean validation(){
        String licenceNum = licenceNumEt.getText().toString();
        String vehicleLicenceNum = vehicleLicenceNumEt.getText().toString();
        String vehicleNum = vehicleNumEt.getText().toString();

        if(licenceNum.equals("")){
            return false;
        }
        /*
        else if(vehicleLicenceNum.equals("")){
            return false;
        }
        else if(vehicleNum.equals("")){
            return false;
        }
        else if(licenceNum.length()!=8 || licenceNum.charAt(0)!='B'){
            //invalid licence num
            return false;
        }
        //else if(!(uploadImg)){
        //    return false;
        //}

        */
        else{
            return true;
        }
    }
}
