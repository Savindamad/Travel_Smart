package com.travelsmart.fourloop.travelsmart;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;

import Beans.User;

public class Signup3 extends AppCompatActivity {

    EditText FNameEt;
    EditText LNameEt;
    EditText NICEt;
    EditText EmailEt;
    EditText PasswordEt;

    ImageView NicImg;

    String mobileNum;
    String encodedImage;

    Bitmap bitmap;
    Bitmap reducedImage;
    File file;
    File file_path;
    Uri file_uri;

    Boolean uplaodPic = true;

    User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup3);

        Intent intentpr = getIntent();
        mobileNum = intentpr.getStringExtra("mobileNum");

        FNameEt = (EditText)findViewById(R.id.FNameEt);
        LNameEt = (EditText)findViewById(R.id.LNameEt);
        NICEt = (EditText)findViewById(R.id.NICET);
        EmailEt = (EditText)findViewById(R.id.EmailEt);
        PasswordEt = (EditText)findViewById(R.id.PasswordEt);

        NicImg = (ImageView)findViewById(R.id.NICIV);

    }

    public void Next2(View view) {
        if(uplaodPic){ //validation
            String Fname = FNameEt.getText().toString();
            String Lname = LNameEt.getText().toString();
            String NIC = NICEt.getText().toString();
            String Email = EmailEt.getText().toString();
            String Password = PasswordEt.getText().toString();

            int flag = 0;

            user = new User(mobileNum,Fname+" "+Lname,Email,Password,NIC);
            user.setNicImg(encodedImage);
            Intent intent = new Intent(Signup3.this,Signup4.class);
            intent.putExtra("user",user);
            intent.putExtra("flag",flag);

            startActivity(intent);
        }
        else{
            //didn't upload picture
        }

    }

    public void uploadNic(View view) {
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
        int targetImageViewWidth = NicImg.getMaxWidth();
        int targetImageViewHeight = NicImg.getMaxHeight();

        BitmapFactory.Options bmOption = new BitmapFactory.Options();
        bmOption.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(String.valueOf(file_path),bmOption);

        int cameraImageWidth = bmOption.outWidth;
        int cameraImageHeight = bmOption.outHeight;

        int scaleFactor = Math.min(cameraImageWidth/targetImageViewWidth,cameraImageHeight/targetImageViewHeight);
        bmOption.inSampleSize = scaleFactor;
        bmOption.inJustDecodeBounds = false;

        reducedImage = BitmapFactory.decodeFile(String.valueOf(file_path),bmOption);
        NicImg.setImageBitmap(reducedImage);

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        reducedImage.compress(Bitmap.CompressFormat.JPEG,100,stream);

        byte[] array = stream.toByteArray();
        encodedImage = Base64.encodeToString(array,0);
    }
}
