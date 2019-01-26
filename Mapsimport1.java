package com.example.samuelhimself.bible1;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.SeekBar;
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
public class Mapsimport1 extends AppCompatActivity implements OnMapReadyCallback,GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener,
        LocationListener,GoogleMap.InfoWindowAdapter, GoogleMap.OnInfoWindowClickListener {



    private ProgressBar progBar;
    private TextView text;
    private Handler mHandler = new Handler();
    private int mProgressStatus=0;

    private SharedPreferences prefs,prefer;
    private String prefName ="preProfile";
    private String prefName2 ="preBike";

    String serverKey="2y10f2Kkl1GRi5si0AAsgvsgJWyqXsUszC3DuvRLwZZ";
    String gearS;
    static JSONObject jObj = null;
    static String json = "",message;
    int availableBikes;

    private static final String SURNAME_KEY ="Surname";
    private static final String FIRST_NAME_KEY ="First Name";
    private static final String PHONE_NUMBER_KEY ="Phone Number";
    private static final String EMAIL_ADDRESS_KEY ="Email";
    private static final String RESIDENCE_KEY ="Residence";
    private static final String DIGITAL_TIME_KEY ="Digital Time";


    SharedPreferences prefb;
    private static final String BIKE_NUMBER_KEY ="Bike Number";
    private static final String RENT_BIKE_KEY ="Rent Bike";
    static final int rentPrice1=1500,rentPrice2=2000;
    int sente,yourTime,gear;
    String usname,ufname,uphone,umail,uresi,udura="20",upaymeth,uagcode;
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
    String method;

    String dura,duration="20 minutes";
    Dialog myDialog;

    String ditime;
    String paymentInt;
    int checkPm=0,pmi,pmc=0,pmd=0,suckind,succfour,sucki;

    String returntime;

    TextView durationtext;
    SeekBar seekduration;
    int min=0,max=7,current=0;

//    BOTTOM SHEET PRICE

    String ExternalFontPath;
    Typeface FontLoaderTypeface;
    BottomSheetBehavior mBottomSheetBehavior;
    ImageView downArrow;
    LinearLayout upBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        BOTTOM SHEET PRICE
//
//        TextView buttonShow = findViewById(R.id.expand1);
//        downArrow = findViewById(R.id.down_arrow1);
//        upBar = findViewById(R.id.up_bar1);
//
//        buttonShow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {if(mBottomSheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
//                mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
//                downArrow.setVisibility(View.INVISIBLE);
//                upBar.setVisibility(View.VISIBLE);
//            }
//            else {
//                mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
//                downArrow.setVisibility(View.VISIBLE);
//                upBar.setVisibility(View.INVISIBLE);
//            }
//            }
//        });

//        View bottomSheet = findViewById(R.id.bottom_sheet);
//        mBottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
//        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

//        setHasOptionsMenu(true);
        setContentView(R.layout.activity_mapsimport1);

        myDialog = new Dialog(this);
        myDialog.setContentView(R.layout.rent_bike_popup);


//        progBar= (ProgressBar)findViewById(R.id.progressBar2);

//        pogless();
        Toolbar toolbar=findViewById(R.id.mapsimporttoolbar);
        setSupportActionBar(toolbar);

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
//        String jsons = meso.toString();
        Log.e("POSE", meso);

        prefs=getSharedPreferences(prefName,MODE_PRIVATE);

        usname=prefs.getString(SURNAME_KEY,"");
        ufname=prefs.getString(FIRST_NAME_KEY,"");
        uphone=prefs.getString(PHONE_NUMBER_KEY,"");
        umail=prefs.getString(EMAIL_ADDRESS_KEY,"");
        uresi=prefs.getString(RESIDENCE_KEY,"");

//        udura=extras.getString("Duration");
//        upaymeth=extras.getString("Paymethod");

//        TextView Tprice=(TextView)findViewById(R.id.textViewpriceM);
//        Tprice.append(sente+" UGX");

//        TextView Tdurat=(TextView)findViewById(R.id.textViewdurationM);
//        Tdurat.append(udura);


        try {
            jObjc = new JSONObject(meso);
            succfour=jObjc.getInt("success");

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
            ditime=user.getString("DT");


        } catch (JSONException e) {
            e.printStackTrace();
        }

//        tool bar
//        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
//        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
//
//        Menu m = menu.getMenu();
//        for (int i=0;i<m.size();i++) {
//            MenuItem mi = m.getItem(i);
//
//            SubMenu subMenu = mi.getSubMenu();
//            if (subMenu!=null && subMenu.size() >0 ) {
//                for (int j=0; j <subMenu.size();j++) {
//                    MenuItem subMenuItem = subMenu.getItem(j);
////                    applyFontToMenuItem(subMenuItem);
//                }
//            }
//            applyFontToMenuItem(mi);
//        }
        TextView reminder=(TextView)findViewById(R.id.reminder1);

//        WHETHER RENTED OR NOT

        if (succfour==4){
//            RENTED

            try {
                jObjc = new JSONObject(meso);
                JSONArray userArray=jObjc.getJSONArray("user");
                JSONObject user=userArray.getJSONObject(0);
                returntime=user.getString("TSR");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            reminder.append("Please select an agent and return bike by "+returntime);
        }else{

        }

//        save users digital tinme
        prefs=getSharedPreferences(prefName, MODE_PRIVATE);
        SharedPreferences.Editor editor=prefs.edit();
        editor.putString(DIGITAL_TIME_KEY, ditime);
        editor.commit();

//        String somet="some thing s";
//        TextView tdigit=findViewById(R.id.textditime2);
//        tdigit.append(somet);
    }


//    private void applyFontToMenuItem(MenuItem mi) {
//        Typeface font = Typeface.createFromAsset(getAssets(), "helvetica_roman.otf");
//        SpannableString mNewTitle = new SpannableString(mi.getTitle());
//        mNewTitle.setSpan(new CustomTypefaceSpan("" , font), 0 , mNewTitle.length(),  Spannable.SPAN_INCLUSIVE_INCLUSIVE);
//        mi.setTitle(mNewTitle);
//    }

    public void bottomthings(View view){
        //        BOTTOM SHEET PRICE

//        TextView buttonShow = findViewById(R.id.expand1);
        downArrow = findViewById(R.id.down_arrow1);
        upBar = findViewById(R.id.up_bar1);

        if(mBottomSheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                downArrow.setVisibility(View.INVISIBLE);
                upBar.setVisibility(View.VISIBLE);
            }
            else {
                mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                downArrow.setVisibility(View.VISIBLE);
                upBar.setVisibility(View.INVISIBLE);
            }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    public void TACwebsite(View view){
        Intent browserIntent = new Intent(
                Intent.ACTION_VIEW,
                Uri.parse("http://stardigitalbikes.com/terms_and_conditions.php"));
        startActivity(browserIntent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        switch (item.getItemId()){
            case R.id.action_profile:
                startActivity(new Intent(Mapsimport1.this, Profile.class));
                break;
            case R.id.action_tutorial:
                startActivity(new Intent(Mapsimport1.this, Instructions.class));
                break;
            case R.id.action_safetytips:
                startActivity(new Intent(Mapsimport1.this, SafetyTips.class));
                break;
            case R.id.action_support:
                startActivity(new Intent(Mapsimport1.this, Support.class));
                break;
            case R.id.action_termsandconds:
                Intent browserIntent = new Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("http://stardigitalbikes.com/terms_and_conditions.php"));
                startActivity(browserIntent);

                break;
            case R.id.action_share:
                startActivity(new Intent(Mapsimport1.this, Promotions.class));
                break;        }
        return super.onOptionsItemSelected(item);
    }

    public  void goToPrice(View view){

        if (pmc==0) {//REQUEST DIGITAL TIME FROME HERE
            if(ditime.equals("00:00")){
                Toast.makeText(getApplicationContext(), "You do not have digital time to spend", Toast.LENGTH_SHORT).show();
            }else{

                new backgroundrequest(Mapsimport1.this).execute(usname, ufname, uphone, umail, uresi, udura, paymentInt, uagcode);
                    Toast.makeText(getApplicationContext(), "requesting.......", Toast.LENGTH_SHORT).show();
                    Log.d("JSONStatus", "requestING");

            }
        }else{
//            if payment is digital time reequest directly
            new backgroundprice(Mapsimport1.this).execute();


        }
    }


    public void onRadioButtonClickedPayment(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.radio_cash12:
                if (checked){
//                    payment="cash";
                    paymentInt="1";
                    checkPm++;
                    pmc++;
                    pmd=0;
                    Log.d("checkStatus", "cash: "+pmc+"dtime: "+pmd);

                }
                break;
            case R.id.radio_digitime12:
                if (checked){
//                    payment="DT";
                    paymentInt="2";
                    checkPm++;
                    pmd++;
                    pmc=0;
                    Log.d("checkStatus", "cash: "+pmc+"dtime: "+pmd);

//                    Toast.makeText(getApplicationContext(), "Not yet available Coming soon", Toast.LENGTH_SHORT).show();
                }
                break;

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
//                            progBar.setProgress(mProgressStatus);
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
        MarkerOptions af = new MarkerOptions().position(africa).title("Africa").snippet(pkafrica)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.logo1));
        mMap.addMarker(af).showInfoWindow();
        markers.add(af);

        LatLng cedat = new LatLng(0.335882, 32.564807);
        MarkerOptions cd = new MarkerOptions().position(cedat).title("CEDAT").snippet(pkcedat)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.logo1));
        mMap.addMarker(cd).showInfoWindow();
        markers.add(cd);

        LatLng cit = new LatLng(0.332038, 32.570488);
        MarkerOptions it = new MarkerOptions().position(cit).title("CIT").snippet(pkcit)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.logo1));
        mMap.addMarker(it).showInfoWindow();
        markers.add(it);

        LatLng fema = new LatLng(0.335345, 32.568673);
        MarkerOptions fm = new MarkerOptions().position(fema).title("FEMA").snippet(pkfema)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.logo1));
        mMap.addMarker(fm).showInfoWindow();
        markers.add(fm);

        LatLng library = new LatLng(0.334936, 32.568000);
        MarkerOptions lb = new MarkerOptions().position(library).title("Library").snippet(pklibrary)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.logo1));
        mMap.addMarker(lb).showInfoWindow();
        markers.add(lb);
//zzzzzzzzzzzzzzzoooooooooooooooooooommmmmmmmmmmmmmmmmmmmmmm
//       THESE(ZOOM VARIABLES) WILL BE TURNED INTO VARIABLES E.G FREEDOM
//        USE THE LOCATION OF THE USER TO DETERMINE
//                  1***WHERE TO ZOOM IN
//                  2***WHICH MARKER TO OPEN WITH
        LatLng freedom = new LatLng(0.331604, 32.568423);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(freedom,15));


        LatLng livingstone = new LatLng(0.338686, 32.567718);
        MarkerOptions lv = new MarkerOptions().position(livingstone).title("Livingstone").snippet(pklivingstone)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.logo1));
        mMap.addMarker(lv).showInfoWindow();
        markers.add(lv);

        LatLng lumumba = new LatLng(0.331717, 32.566073);
        MarkerOptions lm = new MarkerOptions().position(lumumba).title("Lumumba").snippet(pklumumba)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.logo1));
        mMap.addMarker(lm).showInfoWindow();
        markers.add(lm);

        LatLng maingate = new LatLng(0.329760, 32.570937);
        MarkerOptions mg = new MarkerOptions().position(maingate).title("Main Gate").snippet(pkmaingate)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.logo1));
        mMap.addMarker(mg).showInfoWindow();
        markers.add(mg);

        LatLng marystuart = new LatLng(0.330985, 32.566668);
        MarkerOptions ms = new MarkerOptions().position(marystuart).title("Marystuart").snippet(pkmarystuart)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.logo1));
        mMap.addMarker(ms).showInfoWindow();
        markers.add(ms);

        LatLng mitchell = new LatLng(0.333740, 32.570495);
        MarkerOptions mt = new MarkerOptions().position(mitchell).title("Mitchell").snippet(pkmitchell)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.logo1));
        mMap.addMarker(mt).showInfoWindow();
        markers.add(mt);

        LatLng nkrumah = new LatLng(0.336454, 32.569008);
        MarkerOptions nk = new MarkerOptions().position(nkrumah).title("Nkrumah").snippet(pknkrumah)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.logo1));
        mMap.addMarker(nk).showInfoWindow();
        markers.add(nk);

        LatLng uh = new LatLng(0.332969, 32.572506);
        MarkerOptions u = new MarkerOptions().position(uh).title("University Hall").snippet(pkuh)
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
                        if (pkafrica.equals("0 bikes")){}else if (pkafrica.equals("Agent Available")){availableBikes=2;}else {availableBikes=1;}
                        uagcode="1";
                        break;
                    case "CEDAT":
                        if (pkcedat.equals("0 bikes")){}else if (pkcedat.equals("Agent Available")){availableBikes=2;}else {availableBikes=1;}
                        uagcode="2";
                        break;
                    case "CIT":
                        if (pkcit.equals("0 bikes")){}else if (pkcit.equals("Agent Available")){availableBikes=2;}else {availableBikes=1;}
                        uagcode="3";
                        break;
                    case "FEMA":
                        if (pkfema.equals("0 bikes")){}else if (pkfema.equals("Agent Available")){availableBikes=2;}else {availableBikes=1;}
                        uagcode="4";
                        break;
                    case "Library":
                        if (pklibrary.equals("0 bikes")){}else if (pklibrary.equals("Agent Available")){availableBikes=2;}else {availableBikes=1;}
                        uagcode="5";
                        break;
                    case "Livingstone":
                        if (pklivingstone.equals("0 bikes")){}else if (pklivingstone.equals("Agent Available")){availableBikes=2;}else {availableBikes=1;}
                        uagcode="6";
                        break;
                    case "Lumumba":
                        if (pklumumba.equals("0 bikes")){}else if (pklumumba.equals("Agent Available")){availableBikes=2;}else {availableBikes=1;}
                        uagcode="7";
                        break;
                    case "Main Gate":
                        if (pkmaingate.equals("0 bikes")){}else if (pkmaingate.equals("Agent Available")){availableBikes=2;}else {availableBikes=1;}
                        uagcode="8";
                        break;
                    case "Marystuart":
                        if (pkmarystuart.equals("0 bikes")){}else if (pkmarystuart.equals("Agent Available")){availableBikes=2;}else {availableBikes=1;}
                        uagcode="9";
                        break;
                    case "Mitchell":
                        if (pkmitchell.equals("0 bikes")){}else if (pkmitchell.equals("Agent Available")){availableBikes=2;}else {availableBikes=1;}
                        uagcode="10";
                        break;
                    case "Nkrumah":
                        if (pknkrumah.equals("0 bikes")){}else if (pknkrumah.equals("Agent Available")){availableBikes=2;}else {availableBikes=1;}
                        uagcode="11";
                        break;
                    case "University Hall":
                        if (pkuh.equals("0 bikes")){}else if (pkuh.equals("Agent Available")){availableBikes=2;}else {availableBikes=1;}
                        uagcode="12";
                        break;
                }

                if(availableBikes==1) {
                    showPopup();
//                    new backgroundrequest(Mapsimport1.this).execute(usname, ufname, uphone, umail, uresi, udura, upaymeth, uagcode);
//                    ProgressBar pb =findViewById(R.id.progressBar2);
//                    pb.setVisibility(ProgressBar.VISIBLE);
//                    Toast.makeText(getApplicationContext(), "requesting.......", Toast.LENGTH_SHORT).show();
//                    Log.d("JSONStatus", "requestING");
                }else if(availableBikes==2){
//                    REQUESZT BIKE RETURN
                    returnBike(uagcode);
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
            Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();


            try {
                jObj = new JSONObject(json);
                  int  success = jObj.getInt("success");

                switch (success){
                    case 0:
                        break;
                    case 4:
                        Intent int1 =new Intent(getApplicationContext(),Mapsimport1.class);
                        int1.putExtra("bikesin",s);
                        startActivity(int1);
                        break;
                    case 2:
                        Intent int2 =new Intent(getApplicationContext(),fine.class);
                        int2.putExtra("fines",s);
                        startActivity(int2);
                        break;
                    case 3:
                        Intent int3 =new Intent(getApplicationContext(),finalRegistration.class);
                        startActivity(int3);
                        break;
                }
            } catch (JSONException e) {
                Log.e("JSON Parser", "Error creating the json object " + e.toString());
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


//            String connstr="http://stardigitalbikes.com/bike_request1.php";
            String connstr="http://192.168.43.113/pdobikephp/bike_request_pdo.php";
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
                        +"&&"+ URLEncoder.encode("duration","UTF-8")+"="+URLEncoder.encode(udura,"UTF-8")
//                        +"&&"+ URLEncoder.encode("durationInt","UTF-8")+"="+URLEncoder.encode(dura,"UTF-8")
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

//                        if(success==1){
//                            rentStatus=true;
//                            JSONArray userArray=jObj.getJSONArray("user");
//                            JSONObject user=userArray.getJSONObject(0);
//                            String bike=user.getString("BN");
//                            prefb=getSharedPreferences(prefName2,MODE_PRIVATE);
//                            SharedPreferences.Editor editor=prefb.edit();
//                            editor.putBoolean(RENT_BIKE_KEY,rentStatus);
//                            editor.putString(BIKE_NUMBER_KEY,bike);
//                            editor.commit();
//
//                        }else{
//                            rentStatus=false;
//                            prefb=getSharedPreferences(prefName2,MODE_PRIVATE);
//                            SharedPreferences.Editor editor=prefb.edit();
//                            editor.putBoolean(RENT_BIKE_KEY,rentStatus);
//                            editor.commit();
//                            Log.d("JSONStatus","Login failure");
//                            message=jObj.getString("message");
//                            Log.d("JSONStatus",message);
//                        }

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

    public void showPopup() {
        ImageView closePopup;
        closePopup =(ImageView) myDialog.findViewById(R.id.close_popup);
        closePopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        RadioButton radi=(RadioButton)myDialog.findViewById(R.id.radio_digitime12);
        radi.setText("Digital Time("+ditime+")");

        durationtext =(TextView)myDialog.findViewById(R.id.duration1);
        seekduration =(SeekBar)myDialog.findViewById(R.id.seekbar1);

        seekduration.setMax(max-min);
        seekduration.setProgress(current-min);
        duration="20 minutes";
        durationtext.setText(duration);

        seekduration.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                current=progress+min;

                switch (current){
                    case 0:
                        udura="20";
                        duration="20 minutes";
                        durationtext.setText(duration);
                        break;
                    case 1:
                        udura="1";
                        duration="1 hour";
                        durationtext.setText(duration);
                        break;
                    case 2:
                        udura="2";
                        duration="2 hours";
                        durationtext.setText(duration);
                        break;
                    case 3:
                        udura="3";
                        duration="3 hours";
                        durationtext.setText(duration);
                        break;
                    case 4:
                        udura="4";
                        duration="4 hours";
                        durationtext.setText(duration);
                        break;
                    case 5:
                        udura="5";
                        duration="5 hours";
                        durationtext.setText(duration);
                        break;
                    case 6:
                        udura="6";
                        duration="Half day";
                        durationtext.setText(duration);
                        break;
                    case 7:
                        udura="12";
                        duration="Full day";
                        durationtext.setText(duration);
                        break;
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        myDialog.show();
    }
    class backgroundprice extends AsyncTask<String, Void,String> {

        AlertDialog dialog;
        Context context;
        public backgroundprice(Context context){
            this.context=context;
        }

        @Override
        protected void onPreExecute() {
//            dialog= new AlertDialog.Builder(context).create();
//            dialog.setTitle("login status");
        }

        @Override
        protected void onPostExecute(String s) {
//            dialog.setMessage(s);
//            dialog.show();
            Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
//            Toast.makeText(getApplicationContext(),"cash: "+pmc+"dt: "+pmd,Toast.LENGTH_LONG).show();

            try {
                jObjc = new JSONObject(s);
                suckind=jObjc.getInt("success");

            } catch (JSONException e) {
                e.printStackTrace();
            }
            switch (suckind) {
                case 0:
                    Toast.makeText(getApplicationContext(),"Not connected!",Toast.LENGTH_LONG).show();
                    break;
                case 1:
                Intent intent = new Intent(Mapsimport1.this, Price1.class);
                intent.putExtra("all", s);
                intent.putExtra("duration", udura);
                intent.putExtra("agent", uagcode);
                startActivity(intent);
                break;
                case 2:
                    Intent int3 =new Intent(getApplicationContext(),fine.class);
                    int3.putExtra("fines",s);
                    startActivity(int3);
                    break;
                case 3:
                    Intent int2 = new Intent(Mapsimport1.this, finalRegistration.class);
                    startActivity(int2);
                    break;
            }
        }

        @Override
        protected String doInBackground(String... voids) {
            String result="";
            String geary;

//            String acode=voids[0];


//            String connstr="http://stardigitalbikes.com/user_bikes_in.php";
            String connstr="http://192.168.43.113/pdobikephp/price_pdo.php";

            try {
                URL url =new URL(connstr);
                HttpURLConnection http =(HttpURLConnection) url.openConnection();
                http.setRequestMethod("POST");
                http.setDoInput(true);
                http.setDoOutput(true);

                OutputStream ops =http.getOutputStream();
                BufferedWriter writer =new BufferedWriter(new OutputStreamWriter(ops,"UTF-8"));
                String data = URLEncoder.encode("serverKey","UTF-8")+"="+URLEncoder.encode(serverKey,"UTF-8")
                        +"&&"+ URLEncoder.encode("duration","UTF-8")+"="+URLEncoder.encode(udura,"UTF-8")
                        +"&&"+ URLEncoder.encode("durationstring","UTF-8")+"="+URLEncoder.encode(duration,"UTF-8")
                        +"&&"+ URLEncoder.encode("phonenumber","UTF-8")+"="+URLEncoder.encode(uphone,"UTF-8")
                        +"&&"+ URLEncoder.encode("agent_code","UTF-8")+"="+URLEncoder.encode(uagcode,"UTF-8");

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

                    }else{
                        Log.e("JSON Parser", "RETURNED JSON IS NULL ");
                    }
                } catch (JSONException e) {
                    Log.e("JSON Parser", "Error creating the json object " + e.toString());
                }
//##################################################################33
                http.disconnect();
                return result;

            } catch (MalformedURLException e) {
                Log.d("JSON Exception",e.toString());
                result =e.getMessage();
            } catch (ProtocolException e) {
                Log.d("JSON Exception",e.toString());
                result =e.getMessage();
            } catch (IOException e) {
                Log.d("JSON Exception",e.toString());
                result =e.getMessage();
            }
            return result;
        }
    }

    public void returnBike(String Acodey){

//        ProgressBar pb =findViewById(R.id.progressBar5);
//        pb.setVisibility(ProgressBar.VISIBLE);
        new Mapsimport1.backgroundReturn(this).execute(Acodey);
        Toast.makeText(getApplicationContext(), "Requesting....", Toast.LENGTH_SHORT).show();
        Log.d("Request status","GOOD INPUT am gonna make the request");

    }

    class backgroundReturn extends AsyncTask<String, Void,String> {
        AlertDialog dialog;
        Context context;

        public backgroundReturn(Context context){
            this.context=context;
        }

        @Override
        protected void onPreExecute() {
            dialog= new AlertDialog.Builder(context).create();
            dialog.setTitle("Bike return status");
        }

        @Override
        protected void onPostExecute(String s) {
            String jjon = s.toString();
            prefb=getSharedPreferences(prefName2,MODE_PRIVATE);
            Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();

            Log.d("JSON RETUTN",s);

            try {
                jObjc = new JSONObject(s);
                sucki=jObjc.getInt("success");

            } catch (JSONException e) {
                e.printStackTrace();
            }
            switch (sucki) {
                case 0:
                    Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
                    break;
                case 1:
                    Intent int11 = new Intent(Mapsimport1.this, Mapsimport1.class);
                    int11.putExtra("bikesin", s);
                    startActivity(int11);
                    break;
                case 2:
                    Intent int22 =new Intent(getApplicationContext(),fine.class);
                    int22.putExtra("fines",s);
                    startActivity(int22);
                    break;
                case 3:
                    Intent int33 = new Intent(Mapsimport1.this, finalRegistration.class);
                    startActivity(int33);
                    break;
            }

        }
        public void onBackPressed(){
            moveTaskToBack(true);
        }
        @Override
        protected String doInBackground(String... voids) {
            String result="";
            String AgCode =voids[0];

//            String connstr="http://stardigitalbikes.com/bike_return.php";
            String connstr="http://192.168.43.113/pdobikephp/bike_return_pdo.php";


            prefb=getSharedPreferences(prefName2,MODE_PRIVATE);
            String bikey=prefb.getString(BIKE_NUMBER_KEY,"");
            SharedPreferences.Editor editor=prefb.edit();

            prefer=getSharedPreferences(prefName,MODE_PRIVATE);
            String Usurname=prefer.getString(SURNAME_KEY,""),
            Ufirstname=prefer.getString(FIRST_NAME_KEY,""),
            Uphone=prefer.getString(PHONE_NUMBER_KEY,""),
            Uemail=prefer.getString(EMAIL_ADDRESS_KEY,""),
            Uresidence=prefer.getString(RESIDENCE_KEY,"");


            try {
                URL url =new URL(connstr);
                HttpURLConnection http =(HttpURLConnection) url.openConnection();
                http.setRequestMethod("POST");
                http.setDoInput(true);
                http.setDoOutput(true);


                OutputStream ops =http.getOutputStream();
                BufferedWriter writer =new BufferedWriter(new OutputStreamWriter(ops,"UTF-8"));
                String data = URLEncoder.encode("agent_code","UTF-8")+"="+URLEncoder.encode(AgCode,"UTF-8")
                        +"&&"+ URLEncoder.encode("surname","UTF-8")+"="+URLEncoder.encode(Usurname,"UTF-8")
                        +"&&"+ URLEncoder.encode("firstname","UTF-8")+"="+URLEncoder.encode(Ufirstname,"UTF-8")
                        +"&&"+ URLEncoder.encode("phonenumber","UTF-8")+"="+URLEncoder.encode(Uphone,"UTF-8")
                        +"&&"+ URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(Uemail,"UTF-8")
                        +"&&"+ URLEncoder.encode("bikenumber","UTF-8")+"="+URLEncoder.encode(bikey,"UTF-8")
                        +"&&"+ URLEncoder.encode("residence","UTF-8")+"="+URLEncoder.encode(Uresidence,"UTF-8")
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
                            editor.clear().commit();
                            rentStatus=false;
                            editor.putBoolean(RENT_BIKE_KEY,rentStatus);
                            message=jObj.getString("message");
                            editor.commit();

                        }else{
                            rentStatus=true;
                            editor.putBoolean(RENT_BIKE_KEY,rentStatus);
                            editor.commit();
                            Log.d("JSONStatus","Login failure");
                            message=jObj.getString("message");
                            Log.d("JSONStatus",message);
                        }
                    }else{
                        rentStatus=true;
                        editor.putBoolean(RENT_BIKE_KEY,rentStatus);
                        editor.commit();
                        Log.e("JSON Parser", "RETURNED JSON IS NULL ");
                    }
                } catch (JSONException e) {
                    rentStatus=true;
                    editor.putBoolean(RENT_BIKE_KEY,rentStatus);
                    editor.commit();
                    Log.e("JSON Parser", "Error creating the json object " + e.toString());
                }
//##################################################################33
                http.disconnect();
                return result;

            } catch (MalformedURLException e) {
                rentStatus=true;
                editor.putBoolean(RENT_BIKE_KEY,rentStatus);
                editor.commit();
                Log.d("JSON Exception",e.toString());
                result =e.getMessage();
            } catch (ProtocolException e) {
                rentStatus=true;
                editor.putBoolean(RENT_BIKE_KEY,rentStatus);
                editor.commit();
                Log.d("JSON Exception",e.toString());
                result =e.getMessage();
            } catch (IOException e) {
                rentStatus=true;
                editor.putBoolean(RENT_BIKE_KEY,rentStatus);
                editor.commit();
                Log.d("JSON Exception",e.toString());
                result =e.getMessage();
            }

            return result;
        }


    }

    public void onBackPressed(){
        moveTaskToBack(true);
    }


}
