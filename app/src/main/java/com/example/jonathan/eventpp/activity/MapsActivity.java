package com.example.jonathan.eventpp.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.jonathan.eventpp.R;
import com.example.jonathan.eventpp.ScrumApp;
import com.example.jonathan.eventpp.models.Event;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, LocationListener {
    GoogleMap mgoogleMap;

    List<Event> eventList;

    MapView mapView;
    private static final String LOGTAG = "android-localizacion";

    private static final int PETICION_PERMISO_LOCALIZACION = 101;

    double lat = 0.0;
    double log = 0.0;
    Location location;
    LocationManager locationManager;

    boolean isGPSEnabled = false;
    boolean isNetworkEnabled = false;


    Event event;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getdata();

        mapView = (MapView) findViewById(R.id.mapView);
        if (mapView != null) {
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }



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



    @Override
    public void onMapReady(final GoogleMap Map) {



        if(isGPSEnabled == true){
            lat = getLocation().getLatitude();
            log = getLocation().getLongitude();
        }

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            Map.setMyLocationEnabled(true);


        }




        LatLng Ubi = new LatLng(lat, log);

        CameraPosition liberty = CameraPosition
                .builder()
                .target(Ubi)
                .zoom(16)
                .bearing(0)
                .tilt(45)
                .build();


        for (int cant =0; cant<eventList.size(); cant++){
            event=eventList.get(cant);

            Map.addMarker( new  MarkerOptions()
                    .position(new LatLng(event.getLatidud(),event.getLongitud()))
                    .title(event.getNameEvent()));

        }
        Map.moveCamera(CameraUpdateFactory.newCameraPosition(liberty));

        Map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                marker.getTitle();
                marker.showInfoWindow();
                return true;
            }
        });

    }

    private void getdata(){
        eventList = ScrumApp.getInstance().getEvent();
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
