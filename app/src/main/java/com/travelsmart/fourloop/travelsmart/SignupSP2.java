package com.travelsmart.fourloop.travelsmart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import Beans.Passenger;
import Beans.ServiceProvider;
import Beans.User;

public class SignupSP2 extends AppCompatActivity {

    User user;
    int flag;
    Passenger passenger;
    ServiceProvider serviceProvider;

    Spinner bankSp;
    Spinner branchSp;

    EditText accountNumEt;
    EditText accountNameEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_sp2);

        Intent intentpr = getIntent();
        user = (User)intentpr.getSerializableExtra("user");
        serviceProvider = (ServiceProvider)intentpr.getSerializableExtra("serviceProvider");

        flag = intentpr.getIntExtra("flag",0);
        if(flag==3){
            passenger = (Passenger)intentpr.getSerializableExtra("passenger");
        }

        accountNumEt = (EditText)findViewById(R.id.AccountNumEt);
        accountNameEt = (EditText)findViewById(R.id.AccountNameEt);

        /*
        bankSp = (Spinner)findViewById(R.id.BankSp);
        branchSp = (Spinner)findViewById(R.id.BranchSp);

        bankSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String bank = bankSp.getSelectedItem().toString();
                String[] bankArr = {"Bank of Ceylon","Commercial Bank of Ceylon","DFCC Vardhana Bank","Hatton National Bank","HDFC Bank"};
                String branch = "";
                for(int j = 0; i<bankArr.length; i++){
                    if(bankArr[0].equals(bank)){
                        branch = "Bank"+(j+1);
                        break;
                    }
                }

                int arrayNum = getResources().getIdentifier(branch, "array", SignupSP2.this.getPackageName());

                ArrayAdapter<String> vehicleTypeAdapter = new ArrayAdapter<String>(SignupSP2.this,android.R.layout.simple_spinner_dropdown_item,getResources().getStringArray(arrayNum));
                branchSp.setAdapter(vehicleTypeAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        */
    }

    public void Done5(View view) {
        if(validation()){
            serviceProvider.setAccountNum(accountNumEt.getText().toString());
            serviceProvider.setAccountName(accountNameEt.getText().toString());
            //serviceProvider.setBank(bankSp.getSelectedItem().toString());
            //serviceProvider.setBranch(branchSp.getSelectedItem().toString());

            Intent intent = new Intent(SignupSP2.this,Signup4.class);
            intent.putExtra("user", user);
            intent.putExtra("flag", flag);
            intent.putExtra("serviceProvider", serviceProvider);

            if(flag==3){
                intent.putExtra("passenger", passenger);
                startActivity(intent);
            }
            else{
                startActivity(intent);
            }
        }
    }

    private boolean validation() {
        if(accountNameEt.getText().toString().equals("")){
            return false;
        }
        else if(accountNumEt.getText().toString().equals("")){
            return false;
        }
        else{
            return true;
        }
    }
}
