package com.travelsmart.fourloop.travelsmart;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import java.io.File;

import Beans.Passenger;
import Beans.ServiceProvider;
import Beans.User;

public class SignupP1 extends AppCompatActivity {
    User user;
    Passenger passenger;
    ServiceProvider serviceProvider;

    Bitmap bitmap;
    Bitmap reducedImage;
    File file;
    File file_path;
    Uri file_uri;

    Boolean uplaodPic = false;
    int flag;

    ImageView creditCrdImg;

    EditText creditCrdNumEt;
    EditText monthEt;
    EditText yearEt;
    EditText cvvEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_p1);

        Intent intentpr = getIntent();
        user = (User)intentpr.getSerializableExtra("user");
        flag = intentpr.getIntExtra("flag",0);

        if(flag == 1){
            serviceProvider = (ServiceProvider)intentpr.getSerializableExtra("serviceProvider");
        }

        creditCrdNumEt = (EditText)findViewById(R.id.CreditCrdNumEt);
        monthEt = (EditText)findViewById(R.id.MonthEt);
        yearEt = (EditText)findViewById(R.id.YearEt);
        cvvEt = (EditText)findViewById(R.id.CvvEt);

    }

    public void uploadImg(View view) {
        Intent cam = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        getFileUri();
        cam.putExtra(MediaStore.EXTRA_OUTPUT, file_uri);
        startActivityForResult(cam,10);
    }

    public void getFileUri() {
        file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)+File.separator+"NIC_Front");
        file_uri = Uri.fromFile(file);
        file_path = file.getAbsoluteFile();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==10 && resultCode==RESULT_OK){
            uplaodPic = true;
            reducedImageSize();
            //NicImg.setImageURI(file_uri);
        }
    }

    public void reducedImageSize(){
        int targetImageViewWidth = creditCrdImg.getMaxWidth();
        int targetImageViewHeight = creditCrdImg.getMaxHeight();

        BitmapFactory.Options bmOption = new BitmapFactory.Options();
        bmOption.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(String.valueOf(file_path),bmOption);

        int cameraImageWidth = bmOption.outWidth;
        int cameraImageHeight = bmOption.outHeight;

        int scaleFactor = Math.min(cameraImageWidth/targetImageViewWidth,cameraImageHeight/targetImageViewHeight);
        bmOption.inSampleSize = scaleFactor;
        bmOption.inJustDecodeBounds = false;

        reducedImage = BitmapFactory.decodeFile(String.valueOf(file_path),bmOption);
        creditCrdImg.setImageBitmap(reducedImage);
        uplaodPic = true;
    }

    public void Done3(View view) {
        if(validation()){
            String creditCrdNum = creditCrdNumEt.getText().toString();
            int month = Integer.parseInt(monthEt.getText().toString());
            int year = Integer.parseInt(yearEt.getText().toString());
            int cvv = Integer.parseInt(cvvEt.getText().toString());

            passenger = new Passenger(creditCrdNum,month,year,cvv,reducedImage);

            Intent intent = new Intent(SignupP1.this,Signup4.class);
            intent.putExtra("user",user);
            intent.putExtra("passenger", passenger);

            if(flag==0){
                flag=2;
                intent.putExtra("flag",flag);
                startActivity(intent);
            }
            else if(flag==1){
                flag=3;
                intent.putExtra("flag",flag);
                intent.putExtra("serviceProvider",serviceProvider);
                startActivity(intent);
            }
        }
    }

    Boolean validation(){
        if(!(uplaodPic)){
            return false;
        }
        else{
            return true;
        }

    }


}
