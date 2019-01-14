package com.example.samuelhimself.bible1;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.KeyEvent;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class parkingPointsMap extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_points_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng freedomsq = new LatLng(0.333942, 32.568012);
        mMap.addMarker(new MarkerOptions().position(freedomsq).title("FREEDOM SQUARE"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(freedomsq));

        LatLng mitchelHall = new LatLng(0.333679, 32.570378);
        mMap.addMarker(new MarkerOptions().position(mitchelHall).title("Mitchel Hall"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(mitchelHall));

        LatLng livingstone = new LatLng(0.338817, 32.568074);
        mMap.addMarker(new MarkerOptions().position(livingstone).title("Livingstone Hall"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(livingstone));

        LatLng lumumba = new LatLng(0.331795, 32.565944);
        mMap.addMarker(new MarkerOptions().position(lumumba).title("Lumumba Hall"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(lumumba));

        LatLng cit = new LatLng(0.331260, 32.570624);
        mMap.addMarker(new MarkerOptions().position(cit).title("CIT"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(cit));

    }


}
