package com.example.samuelhimself.bible1;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Mapsimport1 extends FragmentActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener,GoogleMap.InfoWindowAdapter, GoogleMap.OnInfoWindowClickListener {

    private GoogleMap mMap;
    private GoogleApiClient client;
    double latitude, longitude;
    private Location lastlocation;
    private Marker currentLocationmMarker;
    Marker mCurrLocationMarker;
    private LocationRequest locationRequest;
    public static final int REQUEST_LOCATION_CODE = 99;
    private static final String TAG = Mapsimport1.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapsimport1);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        isNetworkConnected();

     //   Bundle extras=getIntent().getExtras();
//        Toast.makeText(getApplicationContext(),extras.getInt("money"),Toast.LENGTH_LONG).show();


    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode)
        {
            case REQUEST_LOCATION_CODE:
                if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) !=  PackageManager.PERMISSION_GRANTED)
                    {
                        if(client == null)
                        {
                            bulidGoogleApiClient();
                        }
                        mMap.setMyLocationEnabled(true);
                    }
                }
                else
                {
                    Toast.makeText(this,"Permission Denied" , Toast.LENGTH_LONG).show();
                }
        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {


        mMap = googleMap;
        mMap.setInfoWindowAdapter(this);
        mMap.setOnInfoWindowClickListener(this);
        final List<MarkerOptions> markers = new ArrayList<>();

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            bulidGoogleApiClient();
            mMap.setMyLocationEnabled(true);
        }

        LatLng nkrumah = new LatLng(0.336627, 32.569230);
        MarkerOptions nh = new MarkerOptions().position(nkrumah).title("Nkrumah hall bike lot").snippet("5 bikes")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.logo1));
        mMap.addMarker(nh).showInfoWindow();
        markers.add(nh);

        LatLng mitchell = new LatLng(0.334246, 32.570305);
        MarkerOptions m = new MarkerOptions().position(mitchell).title("Mitchell Hall bike lot").snippet("4 bikes")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.logo1));
        mMap.addMarker(m).showInfoWindow();
        markers.add(m);

        LatLng lumumba = new LatLng(0.332262, 32.566097);
        MarkerOptions la = new MarkerOptions().position(lumumba).title("Lumumba Hall bike lot").snippet("5 bikes")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.logo1));
        mMap.addMarker(la).showInfoWindow();
        markers.add(la);

        LatLng shell = new LatLng(0.347165, 32.649404);
        MarkerOptions sh = new MarkerOptions().position(shell).title("shell bike lot").snippet("7 bikes")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.logo1));
        mMap.addMarker(sh).showInfoWindow();
        markers.add(sh);

        LatLng profla = new LatLng(0.356605, 32.649680);
        MarkerOptions pa = new MarkerOptions().position(profla).title("Profla school bike lot").snippet("6 bikes")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.logo1));
        mMap.addMarker(pa).showInfoWindow();
        markers.add(pa);

        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                Intent intent = new Intent(Mapsimport1.this, Price.class);
                startActivity(intent);
            }
        });

        ApplicationService applicationService = new ApplicationService(Mapsimport1.this);
        final Location newlocation = applicationService.getLocation(LocationManager.NETWORK_PROVIDER);
        if (newlocation != null && isNetworkConnected()) {

            double latitude = newlocation.getLatitude();
            double longitude = newlocation.getLongitude();
            final LatLng latLng = new LatLng(latitude, longitude);
            if (mCurrLocationMarker != null) {
                mCurrLocationMarker.setPosition(latLng);
            } else {

            }



            Button findparkspot = findViewById(R.id.findbikepoint);
            findparkspot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        float mindist = 0;
                        int pos = 0;
                        for (int i = 0; i < markers.size(); i++) {
                            Double slatlng1 = markers.get(i).getPosition().latitude;
                            Double slatlng2 = markers.get(i).getPosition().longitude;
                            LatLng lat = new LatLng(slatlng1, slatlng2);

                            float[] distance = new float[1];

                            Location.distanceBetween(newlocation.getLatitude(), newlocation.getLongitude(), slatlng1, slatlng2, distance);
                            if (i == 0)
                                mindist = distance[0];
                            else if (mindist > distance[0]) {
                                mindist = distance[0];
                                pos = i;
                            }
                        }

                        Toast.makeText(Mapsimport1.this, "Closest Parking Spot: " + markers.get(pos).getTitle(), Toast.LENGTH_LONG).show();
                        CameraPosition myPosition = new CameraPosition.Builder()
                                .target(markers.get(pos).getPosition()).zoom(14).bearing(90).tilt(30).build();
                        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(myPosition));

                        String url = getDirectionsUrl(latLng, markers.get(pos).getPosition());
                        DownloadTask downloadTask = new DownloadTask();

                        // Start downloading json data from Google Directions API
                        downloadTask.execute(url);

                    } catch (Exception e) {

                    }
                }
            });
        }

    }

    protected synchronized void bulidGoogleApiClient() {
        client = new GoogleApiClient.Builder(this).addConnectionCallbacks(this).addOnConnectionFailedListener(this).addApi(LocationServices.API).build();
        client.connect();
    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {

        locationRequest = new LocationRequest();
        locationRequest.setInterval(100);
        locationRequest.setFastestInterval(1000);
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);




        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(client, locationRequest, this);
        }

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        latitude = location.getLatitude();
        longitude = location.getLongitude();
        lastlocation = location;
        if (currentLocationmMarker != null) {
            currentLocationmMarker.remove();

        }
        Log.d("lat = ", "" + latitude);
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("Current Location");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        currentLocationmMarker = mMap.addMarker(markerOptions);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));

        if (client != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(client, this);
        }

    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }

    @Override
    public void onInfoWindowClick(Marker marker) {

    }

    public boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        // test for connection
        if (cm.getActiveNetworkInfo() != null
                && cm.getActiveNetworkInfo().isAvailable()
                && cm.getActiveNetworkInfo().isConnected()) {
            return true;
        } else {
            Log.v(TAG, "Internet Connection Not Present");
            return false;
        }
    }

    private String getDirectionsUrl(LatLng origin, LatLng dest) {

        // Origin of route
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;

        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;


        // Sensor enabled
        String sensor = "sensor=false";

        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + sensor;

        // Output format
        String output = "json";

        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters;


        return url;
    }


    // Fetches data from url passed
    private class DownloadTask extends AsyncTask<String, Void, String> {

        // Downloading data in non-ui thread
        @Override
        protected String doInBackground(String... url) {

            // For storing data from web service
            String data = "";

            try {
                // Fetching the data from web service
                data = downloadUrl(url[0]);
            } catch (Exception e) {
                Log.d("Background Task", e.toString());
            }
            return data;
        }


    }


    /** A method to download json data from url */
    private String downloadUrl(String strUrl) throws IOException {
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(strUrl);

            // Creating an http connection to communicate with url
            urlConnection = (HttpURLConnection) url.openConnection();

            // Connecting to url
            urlConnection.connect();

            // Reading data from url
            iStream = urlConnection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

            StringBuffer sb = new StringBuffer();

            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            data = sb.toString();

            br.close();

        } catch (Exception e) {
            Log.d("Exception", e.toString());
        } finally {
            iStream.close();
            urlConnection.disconnect();
        }
        return data;
    }

//    // Create GoogleApiClient instance
//    private void createGoogleApi() {
//        Log.d(TAG, "createGoogleApi()");
//        if ( mGoogleApiClient == null ) {
//            mGoogleApiClient = new GoogleApiClient.Builder( this )
//                    .addConnectionCallbacks( this )
//                    .addOnConnectionFailedListener( this )
//                    .addApi( LocationServices.API )
//                    .build();
//        }
//        if ( mGoogleApiClient != null ){
//            Log.d(TAG, "createGoogleApi: OK ");
//            mGoogleApiClient.connect();
//        }else {
//            Log.d(TAG, "createGoogleApi: NULL");
//        }
//    }


}
