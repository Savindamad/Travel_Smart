package com.travelsmart.fourloop.travelsmart;

import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback, PlaceSelectionListener {

    private SharedPreferences mSp;
    private GoogleMap mMap;
    private String mPrefix;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        // Get shared preferences save prefix
        mPrefix = getIntent().getStringExtra("prefix");

        // Shared preferences
        mSp = getSharedPreferences("mapData", MODE_PRIVATE);

        // Map
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // Place autocomplete
        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment) getFragmentManager()
                .findFragmentById(R.id.place_autocomplete_fragment);
        autocompleteFragment.setOnPlaceSelectedListener(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Focus Colombo
        LatLng colombo = new LatLng(6.9271, 79.8612);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(colombo));
        mMap.moveCamera(CameraUpdateFactory.zoomTo(10));

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                Geocoder geocoder = new Geocoder(getApplicationContext());
                try {
                    List<Address> addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
                    SharedPreferences.Editor editor = mSp.edit();
                    editor.putLong(mPrefix + "lat", Double.doubleToLongBits(latLng.latitude));
                    editor.putLong(mPrefix + "lng", Double.doubleToLongBits(latLng.longitude));
                    // Construct the address
                    String address = "";
                    for (int i=0; i <= addresses.get(0).getMaxAddressLineIndex(); i++) {
                        address += addresses.get(0).getAddressLine(i);
                        address += ", ";
                    }
                    editor.putString(mPrefix + "address", address);
                    editor.apply();
                    finish();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onPlaceSelected(Place place) {
        SharedPreferences.Editor editor = mSp.edit();
        editor.putLong(mPrefix + "lat", Double.doubleToLongBits(place.getLatLng().latitude));
        editor.putLong(mPrefix + "lng", Double.doubleToLongBits(place.getLatLng().longitude));
        editor.putString(mPrefix + "address", (String) place.getAddress());
        editor.apply();
        finish();
    }

    @Override
    public void onError(Status status) {

    }
}
