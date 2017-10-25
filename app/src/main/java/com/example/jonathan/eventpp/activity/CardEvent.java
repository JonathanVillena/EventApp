package com.example.jonathan.eventpp.activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.jonathan.eventpp.R;
import com.example.jonathan.eventpp.ScrumApp;
import com.example.jonathan.eventpp.models.Event;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.net.URI;
import java.util.Date;
import java.util.Map;

public class CardEvent extends AppCompatActivity implements OnMapReadyCallback, LocationListener {

    GoogleMap mgoogleMap;

    MapView mapView;
    private static final String LOGTAG = "android-localizacion";

    private static final int PETICION_PERMISO_LOCALIZACION = 101;
    private static final int PHOTO_SELECTED = 100 ;


    private TextView lblLatitud;
    private TextView lblLongitud;
    private ToggleButton btnActualizar;

    double lat = 0.0;
    double log = 0.0;
    Location location;
    LocationManager locationManager;

    boolean isGPSEnabled = false;
    boolean isNetworkEnabled = false;
    ImageButton fotoH;

    Uri imageUri;

    EditText TextDiscotec;
    EditText TextName;
    EditText TextDate;
    EditText TextUbi;


    TextView textlon;
    TextView textLat;

    FloatingActionButton floatingActionButton;


    Marker publicMarker = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_event);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fotoH = (ImageButton) findViewById(R.id.imageButton);

        TextDiscotec= (EditText) findViewById(R.id.TextDiscotec);
        TextName= (EditText) findViewById(R.id.TextName);
        TextDate= (EditText) findViewById(R.id.TextDate);
        TextUbi= (EditText) findViewById(R.id.TextUbi);

        textLat = (TextView) findViewById(R.id.textView2);
        textlon = (TextView) findViewById(R.id.textView);

        requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PETICION_PERMISO_LOCALIZACION);


        mapView = (MapView) findViewById(R.id.mapView);
        if (mapView != null) {
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }

        fotoH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(gallery,PHOTO_SELECTED);
            }
        });

    floatingActionButton = (FloatingActionButton) findViewById(R.id.floatingActionButton);


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(publicMarker.isVisible() == true ){
                Event event = new Event();
                event.setDiscotect(TextDiscotec.getText().toString());
                event.setNameEvent(TextName.getText().toString());
                event.setImagenURL(Integer.toString(R.mipmap.ic_hallowinka));
                event.setDateEvent(new Date());
                event.setLatidud(publicMarker.getPosition().latitude);
                event.setLongitud(publicMarker.getPosition().longitude);
                event.setUbicacion(TextUbi.getText().toString());

                ScrumApp.getInstance().AddEvent(event);
                finish();

            }else{
                    Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
            }

             }
        });







    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK && requestCode == PHOTO_SELECTED){
            imageUri = data.getData();
            fotoH.setImageURI(imageUri);
        }
    }

    @Override
    public void onMapReady(final GoogleMap Map) {



        if(isGPSEnabled == true){
            lat = getLocation().getLatitude();
            log = getLocation().getLongitude();
        }

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            Map.setMyLocationEnabled(true);


        }
        publicMarker = Map.addMarker(new MarkerOptions().visible(false).position(new LatLng(-12.0788,-77.0680)));



        LatLng Ubi = new LatLng(lat, log);

        CameraPosition liberty = CameraPosition
                .builder()
                .target(Ubi)
                .zoom(16)
                .bearing(0)
                .tilt(45)
                .build();

        Map.moveCamera(CameraUpdateFactory.newCameraPosition(liberty));


        Map.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {


                publicMarker.setPosition(latLng);
                publicMarker.setVisible(true);
                textLat.setText(String.valueOf(latLng.latitude));
                textlon.setText(String.valueOf(latLng.longitude));



            }
        });
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }



    public  Location getLocation() {
        try{

            locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);
            isGPSEnabled = locationManager.isProviderEnabled(locationManager.GPS_PROVIDER);
            isNetworkEnabled=locationManager.isProviderEnabled(locationManager.NETWORK_PROVIDER);

            if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                    || ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED ){

                if(isGPSEnabled){
                    if(location==null){
                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000,10,this);
                        if(locationManager!=null){
                            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                        }
                    }
                }
                // if lcoation is not found from GPS than it will found from network //
                if(location==null){
                    if(isNetworkEnabled){

                        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10000,10,this);
                        if(locationManager!=null){
                            location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        }

                    }
                }

            }

        }catch(Exception ex){

        }
        return  location;
    }



}