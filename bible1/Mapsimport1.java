package com.example.samuelhimself.bible1;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Handler;
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
import android.widget.ProgressBar;
import android.widget.TextView;
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
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;


//impooiji


import android.Manifest;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;


import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStates;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;
public class Mapsimport1 extends FragmentActivity implements OnMapReadyCallback,GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener,
        LocationListener,GoogleMap.InfoWindowAdapter, GoogleMap.OnInfoWindowClickListener {

    private ProgressBar progBar;
    private TextView text;
    private Handler mHandler = new Handler();
    private int mProgressStatus=0;

    private SharedPreferences prefs;
    private String prefName ="preProfile";
    private String prefName2 ="preBike";

    String serverKey="2y10f2Kkl1GRi5si0AAsgvsgJWyqXsUszC3DuvRLwZZ";
    String gearS;
    static JSONObject jObj = null;
    static String json = "",message;
    boolean availableBikes;

    private static final String SURNAME_KEY ="Surname";
    private static final String FIRST_NAME_KEY ="First Name";
    private static final String PHONE_NUMBER_KEY ="Phone Number";
    private static final String EMAIL_ADDRESS_KEY ="Email";
    private static final String RESIDENCE_KEY ="Residence";

    SharedPreferences prefb;
    private static final String BIKE_NUMBER_KEY ="Bike Number";
    private static final String RENT_BIKE_KEY ="Rent Bike";
    static final int rentPrice1=1500,rentPrice2=2000;
    int sente,yourTime,gear;
    String usname,ufname,uphone,umail,uresi,udura,upaymeth,uagcode;
    Boolean rentStatus=false;

    private GoogleMap mMap;
    private GoogleApiClient client;
    double latitude, longitude;
    private Location lastlocation;
    private Marker currentLocationmMarker;
    Marker mCurrLocationMarker;
    private LocationRequest locationRequest;
    public static final int REQUEST_LOCATION_CODE = 99;
    private static final String TAG = Mapsimport1.class.getSimpleName();

    String pkafrica,pkcedat,pkcit,pkfema,pklibrary,pklivingstone,pklumumba,pkmaingate,pkmarystuart,pkmitchell,pknkrumah,pkuh;
    static JSONObject jObjc = null;
    static String jsone = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapsimport1);

        progBar= (ProgressBar)findViewById(R.id.progressBar2);

        pogless();

//        Toolbar toolbar=findViewById(R.id.mapsimporttoolbar);
//        setSupportActionBar(toolbar);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        isNetworkConnected();
        if(client ==null){
            client = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API).build();
            client.connect();
        }

     //   Bundle extras=getIntent().getExtras();
//        Toast.makeText(getApplicationContext(),extras.getInt("money"),Toast.LENGTH_LONG).show();
        Bundle extras=getIntent().getExtras();
        String meso=extras.getString("bikesin");

        int dura=extras.getInt("DurationInt");
        int method=extras.getInt("PaymethodInt");
//        gear=extras.getInt("gearInt");

        if (method==1) {
                sente = dura * rentPrice1;
        }
        else {
            sente=dura;
        }
        if (dura==1) {
            yourTime=6;
        }
        else {
            yourTime=12;
        }

        prefs=getSharedPreferences(prefName,MODE_PRIVATE);

        usname=prefs.getString(SURNAME_KEY,"");
        ufname=prefs.getString(FIRST_NAME_KEY,"");
        uphone=prefs.getString(PHONE_NUMBER_KEY,"");
        umail=prefs.getString(EMAIL_ADDRESS_KEY,"");
        uresi=prefs.getString(RESIDENCE_KEY,"");

        udura=extras.getString("Duration");
        upaymeth=extras.getString("Paymethod");

        TextView Tprice=(TextView)findViewById(R.id.textViewpriceM);
        Tprice.append(sente+" UGX   ");

        TextView Tdurat=(TextView)findViewById(R.id.textViewdurationM);
        Tdurat.append(udura+" day");


        try {
            jObjc = new JSONObject(meso);
            JSONArray userArray=jObjc.getJSONArray("user");
            JSONObject user=userArray.getJSONObject(0);
            pkafrica=user.getString("AF");
            pkcedat=user.getString("CD");
            pkcit=user.getString("IT");
            pkfema=user.getString("FM");
            pklibrary=user.getString("LB");
            pklivingstone=user.getString("LV");
            pklumumba=user.getString("LM");
            pkmaingate=user.getString("MG");
            pkmarystuart=user.getString("MS");
            pkmitchell=user.getString("MT");
            pknkrumah=user.getString("NK");
            pkuh=user.getString("UH");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void pogless() {

        new Thread(new Runnable() {
            public void run() {
                final int presentage=0;
                while (mProgressStatus < 100) {
                    mProgressStatus += 5;
                    if(mProgressStatus==100){
                        mProgressStatus=0;
                    }
                    // Update the progress bar
                    mHandler.post(new Runnable() {
                        public void run() {
                            progBar.setProgress(mProgressStatus);
//                            text.setText(""+mProgressStatus+"%");
                        }
                    });
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
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
//                        mMap.setMyLocationEnabled(true);
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

//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
//            bulidGoogleApiClient();
//            mMap.setMyLocationEnabled(true);
//        }

        LatLng africa = new LatLng(0.337912, 32.568790);
        MarkerOptions af = new MarkerOptions().position(africa).title("Africa").snippet(pkafrica+" bikes")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.logo1));
        mMap.addMarker(af).showInfoWindow();
        markers.add(af);

        LatLng cedat = new LatLng(0.335882, 32.564807);
        MarkerOptions cd = new MarkerOptions().position(cedat).title("CEDAT").snippet(pkcedat+" bikes")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.logo1));
        mMap.addMarker(cd).showInfoWindow();
        markers.add(cd);

        LatLng cit = new LatLng(0.332038, 32.570488);
        MarkerOptions it = new MarkerOptions().position(cit).title("CIT").snippet(pkcit+" bikes")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.logo1));
        mMap.addMarker(it).showInfoWindow();
        markers.add(it);

        LatLng fema = new LatLng(0.335345, 32.568673);
        MarkerOptions fm = new MarkerOptions().position(fema).title("FEMA").snippet(pkfema+" bikes")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.logo1));
        mMap.addMarker(fm).showInfoWindow();
        markers.add(fm);

        LatLng library = new LatLng(0.334936, 32.568000);
        MarkerOptions lb = new MarkerOptions().position(library).title("Library").snippet(pklibrary+" bikes")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.logo1));
        mMap.addMarker(lb).showInfoWindow();
        markers.add(lb);
//zzzzzzzzzzzzzzzoooooooooooooooooooommmmmmmmmmmmmmmmmmmmmmm
        LatLng freedom = new LatLng(0.331604, 32.568423);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(freedom,15));


        LatLng livingstone = new LatLng(0.338686, 32.567718);
        MarkerOptions lv = new MarkerOptions().position(livingstone).title("Livingstone").snippet(pklivingstone+" bikes")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.logo1));
        mMap.addMarker(lv).showInfoWindow();
        markers.add(lv);

        LatLng lumumba = new LatLng(0.331717, 32.566073);
        MarkerOptions lm = new MarkerOptions().position(lumumba).title("Lumumba").snippet(pklumumba+" bikes")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.logo1));
        mMap.addMarker(lm).showInfoWindow();
        markers.add(lm);

        LatLng maingate = new LatLng(0.329760, 32.570937);
        MarkerOptions mg = new MarkerOptions().position(maingate).title("Main Gate").snippet(pkmaingate+" bikes")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.logo1));
        mMap.addMarker(mg).showInfoWindow();
        markers.add(mg);

        LatLng marystuart = new LatLng(0.330985, 32.566668);
        MarkerOptions ms = new MarkerOptions().position(marystuart).title("Marystuart").snippet(pkmarystuart+" bikes")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.logo1));
        mMap.addMarker(ms).showInfoWindow();
        markers.add(ms);

        LatLng mitchell = new LatLng(0.333740, 32.570495);
        MarkerOptions mt = new MarkerOptions().position(mitchell).title("Mitchell").snippet(pkmitchell+" bikes")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.logo1));
        mMap.addMarker(mt).showInfoWindow();
        markers.add(mt);

        LatLng nkrumah = new LatLng(0.336454, 32.569008);
        MarkerOptions nk = new MarkerOptions().position(nkrumah).title("Nkrumah").snippet(pknkrumah+" bikes")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.logo1));
        mMap.addMarker(nk).showInfoWindow();
        markers.add(nk);

        LatLng uh = new LatLng(0.332969, 32.572506);
        MarkerOptions u = new MarkerOptions().position(uh).title("University Hall").snippet(pkuh+" bikes")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.logo1));
        mMap.addMarker(u).showInfoWindow();
        markers.add(u);


        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
//                Intent intent = new Intent(Mapsimport1.this, Price.class);
//                intent.putExtra("marker", marker.getTitle());
//                startActivity(intent);
                String selected=marker.getTitle();

                switch (selected){
                    case "Africa":
                        if (pkafrica.equals("0")){}else {availableBikes=true;}
                        uagcode="1";
                        break;
                    case "CEDAT":
                        if (pkcedat.equals("0")){}else {availableBikes=true;}
                        uagcode="2";
                        break;
                    case "CIT":
                        if (pkcit.equals("0")){}else {availableBikes=true;}
                        uagcode="3";
                        break;
                    case "FEMA":
                        if (pkfema.equals("0")){}else {availableBikes=true;}
                        uagcode="4";
                        break;
                    case "Library":
                        if (pklibrary.equals("0")){}else {availableBikes=true;}
                        uagcode="5";
                        break;
                    case "Livingstone":
                        if (pklivingstone.equals("0")){}else {availableBikes=true;}
                        uagcode="6";
                        break;
                    case "Lumumba":
                        if (pklumumba.equals("0")){}else {availableBikes=true;}
                        uagcode="7";
                        break;
                    case "Main Gate":
                        if (pkmaingate.equals("0")){}else {availableBikes=true;}
                        uagcode="8";
                        break;
                    case "Marystuart":
                        if (pkmarystuart.equals("0")){}else {availableBikes=true;}
                        uagcode="9";
                        break;
                    case "Mitchell":
                        if (pkmitchell.equals("0")){}else {availableBikes=true;}
                        uagcode="10";
                        break;
                    case "Nkrumah":
                        if (pknkrumah.equals("0")){}else {availableBikes=true;}
                        uagcode="11";
                        break;
                    case "University Hall":
                        if (pkuh.equals("0")){}else {availableBikes=true;}
                        uagcode="12";
                        break;
                }

                if(availableBikes) {
                    new backgroundrequest(Mapsimport1.this).execute(usname, ufname, uphone, umail, uresi, udura, upaymeth, uagcode);
                    ProgressBar pb =findViewById(R.id.progressBar2);
                    pb.setVisibility(ProgressBar.VISIBLE);
                    Toast.makeText(getApplicationContext(), "requesting.......", Toast.LENGTH_SHORT).show();
                    Log.d("JSONStatus", "requestING");
                }else {
                    Toast.makeText(getApplicationContext(), "no bikes available at "+selected, Toast.LENGTH_SHORT).show();}
            }
        });

//        ApplicationService applicationService = new ApplicationService(Mapsimport1.this);
//        final Location newlocation = applicationService.getLocation(LocationManager.NETWORK_PROVIDER);
//        if (newlocation != null && isNetworkConnected()) {
//
//            double latitude = newlocation.getLatitude();
//            double longitude = newlocation.getLongitude();
//            final LatLng latLng = new LatLng(latitude, longitude);
//            if (mCurrLocationMarker != null) {
//                mCurrLocationMarker.setPosition(latLng);
//            } else {
//
//            }



//            Button findparkspot = findViewById(R.id.findbikepoint);
//            findparkspot.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    try {
//                        float mindist = 0;
//                        int pos = 0;
//                        for (int i = 0; i < markers.size(); i++) {
//                            Double slatlng1 = markers.get(i).getPosition().latitude;
//                            Double slatlng2 = markers.get(i).getPosition().longitude;
//                            LatLng lat = new LatLng(slatlng1, slatlng2);
//
//                            float[] distance = new float[1];
//
//                            Location.distanceBetween(newlocation.getLatitude(), newlocation.getLongitude(), slatlng1, slatlng2, distance);
//                            if (i == 0)
//                                mindist = distance[0];
//                            else if (mindist > distance[0]) {
//                                mindist = distance[0];
//                                pos = i;
//                            }
//                        }
//
//                        Toast.makeText(Mapsimport1.this, "Closest Parking Spot: " + markers.get(pos).getTitle(), Toast.LENGTH_LONG).show();
//                        CameraPosition myPosition = new CameraPosition.Builder()
//                                .target(markers.get(pos).getPosition()).zoom(14).bearing(90).tilt(30).build();
//                        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(myPosition));
//
//                        String url = getDirectionsUrl(latLng, markers.get(pos).getPosition());
//                        DownloadTask downloadTask = new DownloadTask();
//
//                        // Start downloading json data from Google Directions API
//                        downloadTask.execute(url);
//
//                    } catch (Exception e) {
//
//                    }
//                }
//            });
//        }

    }

    class backgroundrequest extends AsyncTask<String, Void,String> {
        AlertDialog dialog;
        Context context;
        public backgroundrequest(Context context){
            this.context=context;

        }

        @Override
        protected void onPreExecute() {
            dialog= new AlertDialog.Builder(context).create();
            dialog.setTitle("Bike renting status");
        }

        @Override
        protected void onPostExecute(String s) {
            json = s.toString();
            prefb=getSharedPreferences(prefName2,MODE_PRIVATE);

            if(prefb.getBoolean(RENT_BIKE_KEY,true)){

                String biko=prefb.getString(BIKE_NUMBER_KEY,"");
                Intent int1 =new Intent(getApplicationContext(),Price.class);
                int1.putExtra("px",sente);
                int1.putExtra("yt",yourTime);
                int1.putExtra("bn",biko);
                startActivity(int1);
            }
            else {
                ProgressBar pb =findViewById(R.id.progressBar2);
                pb.setVisibility(ProgressBar.INVISIBLE);
                try {
                    jObj = new JSONObject(json);
                    message=jObj.getString("message");
                    dialog.setMessage(message);
                    dialog.show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
//                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();

            }
        }

        @Override
        protected String doInBackground(String... voids) {
            String result="";
            String sname =voids[0];
            String fname= voids[1];
            String phonenum=voids[2];
            String mail=voids[3];
            String residence=voids[4];
            String durat=voids[5];
            String payM=voids[6];
            String acode=voids[7];
//            String geary1=voids[8];


            String connstr="http://stardigitalbikes.com/bike_request1.php";

            try {
                URL url =new URL(connstr);
                HttpURLConnection http =(HttpURLConnection) url.openConnection();
                http.setRequestMethod("POST");
                http.setDoInput(true);
                http.setDoOutput(true);


                OutputStream ops =http.getOutputStream();
                BufferedWriter writer =new BufferedWriter(new OutputStreamWriter(ops,"UTF-8"));
                String data = URLEncoder.encode("surname","UTF-8")+"="+URLEncoder.encode(sname,"UTF-8")
                        +"&&"+ URLEncoder.encode("firstname","UTF-8")+"="+URLEncoder.encode(fname,"UTF-8")
                        +"&&"+ URLEncoder.encode("phonenumber","UTF-8")+"="+URLEncoder.encode(phonenum,"UTF-8")
                        +"&&"+ URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(mail,"UTF-8")
                        +"&&"+ URLEncoder.encode("residence","UTF-8")+"="+URLEncoder.encode(residence,"UTF-8")
                        +"&&"+ URLEncoder.encode("duration","UTF-8")+"="+URLEncoder.encode(durat,"UTF-8")
                        +"&&"+ URLEncoder.encode("payment_method","UTF-8")+"="+URLEncoder.encode(payM,"UTF-8")
//                        +"&&"+ URLEncoder.encode("gear","UTF-8")+"="+URLEncoder.encode(geary1,"UTF-8")
                        +"&&"+ URLEncoder.encode("agent_code","UTF-8")+"="+URLEncoder.encode(acode,"UTF-8")
                        +"&&"+ URLEncoder.encode("serverKey","UTF-8")+"="+URLEncoder.encode(serverKey,"UTF-8");
                writer.write(data);
                writer.flush();
                writer.close();
                ops.close();
                Log.d("JSON Exception","DONE SENDING");

                InputStream ips =http.getInputStream();
                BufferedReader reader=new BufferedReader(new InputStreamReader(ips,"ISO-8859-1"));
                String line ="";
                while ((line=reader.readLine()) !=null){
                    result +=line;

                }
//#######INTRODICING THE READING OOF THE RETURNED JSON
                ips.close();
                reader.close();
                json = result.toString();

                try {
                    jObj = new JSONObject(json);
                    if(json!=null){
                        int success=jObj.getInt("success");

                        Log.d("JSONStatus", "JSON RETURNED");

                        if(success==1){
                            rentStatus=true;
                            JSONArray userArray=jObj.getJSONArray("user");
                            JSONObject user=userArray.getJSONObject(0);
                            String bike=user.getString("BN");
                            prefb=getSharedPreferences(prefName2,MODE_PRIVATE);
                            SharedPreferences.Editor editor=prefb.edit();
                            editor.putBoolean(RENT_BIKE_KEY,rentStatus);
                            editor.putString(BIKE_NUMBER_KEY,bike);
                            editor.commit();

                        }else{
                            rentStatus=false;
                            prefb=getSharedPreferences(prefName2,MODE_PRIVATE);
                            SharedPreferences.Editor editor=prefb.edit();
                            editor.putBoolean(RENT_BIKE_KEY,rentStatus);
                            editor.commit();
                            Log.d("JSONStatus","Login failure");
                            message=jObj.getString("message");
                            Log.d("JSONStatus",message);
                        }
                    }else{
                        rentStatus=false;
                        prefb=getSharedPreferences(prefName2,MODE_PRIVATE);
                        SharedPreferences.Editor editor=prefb.edit();
                        editor.putBoolean(RENT_BIKE_KEY,rentStatus);
                        editor.commit();
                        Log.e("JSON Parser", "RETURNED JSON IS NULL ");
                        message=jObj.getString("message");
                    }
                } catch (JSONException e) {
                    rentStatus=false;
                    prefb=getSharedPreferences(prefName2,MODE_PRIVATE);
                    SharedPreferences.Editor editor=prefb.edit();
                    editor.putBoolean(RENT_BIKE_KEY,rentStatus);
                    editor.commit();
                    Log.e("JSON Parser", "Error creating the json object " + e.toString());

                }
//##################################################################33
                http.disconnect();
                return result;

            } catch (MalformedURLException e) {
                rentStatus=false;
                prefb=getSharedPreferences(prefName2,MODE_PRIVATE);
                SharedPreferences.Editor editor=prefb.edit();
                editor.putBoolean(RENT_BIKE_KEY,rentStatus);
                editor.commit();
                Log.d("JSON Exception",e.toString());
                result =e.getMessage();
            } catch (ProtocolException e) {
                rentStatus=false;
                prefb=getSharedPreferences(prefName2,MODE_PRIVATE);
                SharedPreferences.Editor editor=prefb.edit();
                editor.putBoolean(RENT_BIKE_KEY,rentStatus);
                editor.commit();
                Log.d("JSON Exception",e.toString());
                result =e.getMessage();
            } catch (IOException e) {
                rentStatus=false;
                prefb=getSharedPreferences(prefName2,MODE_PRIVATE);
                SharedPreferences.Editor editor=prefb.edit();
                editor.putBoolean(RENT_BIKE_KEY,rentStatus);
                editor.commit();
                Log.d("JSON Exception",e.toString());
                result =e.getMessage();
            }

            return result;
        }


    }


    private void bulidGoogleApiClient() {
        client = new GoogleApiClient.Builder(this).addConnectionCallbacks(this).addOnConnectionFailedListener(this).addApi(LocationServices.API).build();
        client.connect();
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

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {
        client.connect();

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


}
