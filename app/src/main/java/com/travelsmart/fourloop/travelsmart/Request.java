package com.travelsmart.fourloop.travelsmart;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.google.android.gms.maps.model.LatLng;

import java.sql.Time;
import java.text.DateFormat;
import java.util.Calendar;

import Beans.DateDialog;

public class Request extends Fragment {
    NavigationView navigationView = null;
    Toolbar toolbar = null;

    static final int DIALOG_ID = 0;
    static final String PREFIX_START_POINT = "start";
    static final String PREFIX_END_POINT = "end";

    EditText datePicker;
    EditText startTimePicker;
    EditText endTimePicker;
    EditText startPoint;
    EditText endPoint;

    // Location data
    private LatLng mStartLatLng;
    private String mStartAddress;
    private LatLng mEndLatLng;
    private String mEndAddress;

    DateFormat format = DateFormat.getDateInstance();
    Calendar calendar = Calendar.getInstance();

    DateFormat format1 = DateFormat.getTimeInstance();
    Calendar calendar1 = Calendar.getInstance();

    DateFormat format2 = DateFormat.getTimeInstance();
    Calendar calendar2 = Calendar.getInstance();

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener(){
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            calendar.set(Calendar.YEAR,year);
            calendar.set(Calendar.MONTH,month);
            calendar.set(Calendar.DAY_OF_MONTH,day);
            updatedate();
        }
    };

    TimePickerDialog.OnTimeSetListener time = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker timePicker, int hour, int minute) {
            calendar1.set(Calendar.HOUR_OF_DAY,hour);
            calendar1.set(Calendar.MINUTE,minute);
            updatetime1();
        }
    };

    TimePickerDialog.OnTimeSetListener time1 = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker timePicker, int hour, int minute) {
            calendar1.set(Calendar.HOUR_OF_DAY,hour);
            calendar1.set(Calendar.MINUTE,minute);
            updatetime1();
        }
    };

    public Request() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_request, container, false);

        datePicker = (EditText)view.findViewById(R.id.DateEt);
        startTimePicker = (EditText)view.findViewById(R.id.StartTimeEt);
        endTimePicker = (EditText)view.findViewById(R.id.EndTimeEt);
        startPoint = (EditText)view.findViewById(R.id.StartPoinEt);
        endPoint = (EditText) view.findViewById(R.id.EndPointEt);
        startTimePicker = (EditText)view.findViewById(R.id.EndTimeEt);

        startPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), MapActivity.class);
                i.putExtra("prefix", PREFIX_START_POINT);
                startActivity(i);
            }
        });

        endPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), MapActivity.class);
                i.putExtra("prefix", PREFIX_END_POINT);
                startActivity(i);
            }
        });

        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //setTime1();
                setDate();
            }
        });

        startTimePicker.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                System.out.println("a");
                setTime1();
            }
        });

        endTimePicker.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                System.out.println("a");
                setTime2();
            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        SharedPreferences sp = getActivity().getSharedPreferences("mapData", Context.MODE_PRIVATE);
        // Get start point data
        double startLat = Double.longBitsToDouble(sp.getLong(PREFIX_START_POINT + "lat", -1));
        double startLng = Double.longBitsToDouble(sp.getLong(PREFIX_START_POINT + "lng", -1));
        mStartAddress = sp.getString(PREFIX_START_POINT + "address", null);
        mStartLatLng = new LatLng(startLat, startLng);

        // Get end point data
        double endLat = Double.longBitsToDouble(sp.getLong(PREFIX_END_POINT + "lat", -1));
        double endLng = Double.longBitsToDouble(sp.getLong(PREFIX_END_POINT + "lng", -1));
        mEndAddress = sp.getString(PREFIX_END_POINT + "address", null);
        mEndLatLng = new LatLng(endLat, endLng);

        if (mStartAddress != null) {
            startPoint.setText(mStartAddress);
        }
        if (mEndAddress != null) {
            endPoint.setText(mEndAddress);
        }
    }

    public void setDate(){
        System.out.println("b");
        new DatePickerDialog(getContext(),date,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    public void updatedate(){
        System.out.println("c");
        datePicker.setText(format.format(calendar.getTime()));
    }

    public void setTime1(){
        System.out.println("d");
        new TimePickerDialog(getContext(),time,calendar1.get(Calendar.HOUR_OF_DAY),calendar1.get(Calendar.MINUTE), false).show();
    }

    public void updatetime1() {
        System.out.println("e");
        startTimePicker.setText(format1.format(calendar1.getTime()));
    }

    public void setTime2(){
        System.out.println("d");
        new TimePickerDialog(getContext(),time1,calendar2.get(Calendar.HOUR_OF_DAY),calendar2.get(Calendar.MINUTE), false).show();
    }

    public void updatetime2() {
        System.out.println("e");
        endTimePicker.setText(format2.format(calendar2.getTime()));
    }

}
