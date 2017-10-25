package com.example.jonathan.eventpp.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.jonathan.eventpp.R;
import com.example.jonathan.eventpp.ScrumApp;
import com.example.jonathan.eventpp.adapters.EventAdapter;
import com.example.jonathan.eventpp.models.Event;

import java.util.List;

public class ListEventActivity extends AppCompatActivity {
    RecyclerView recyclerViewEvent;
    List<Event> eventList;

    private static final int PETICION_PERMISO_LOCALIZACION = 101;

    FloatingActionButton ActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list_event);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PETICION_PERMISO_LOCALIZACION);
        getdata();

        recyclerViewEvent = (RecyclerView) findViewById(R.id.LisEventRecycle);
        recyclerViewEvent.setLayoutManager(new LinearLayoutManager(this));


        EventAdapter adapter = new EventAdapter();
        adapter.setEvent(eventList);
        recyclerViewEvent.setAdapter(adapter);




        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(),CardEvent.class));
            }
        });

        ActionButton = (FloatingActionButton) findViewById(R.id.floatingActionButton2);

        ActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(v.getContext(),MapsActivity.class));
            }
        });
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        recyclerViewEvent.getAdapter().notifyDataSetChanged();
    }


    private void getdata(){
        eventList = ScrumApp.getInstance().getEvent();
}



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PETICION_PERMISO_LOCALIZACION:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                       return;
                    }

                } else {
                    Toast.makeText(this, "Esta App Requiere De los permisos del GPS", Toast.LENGTH_LONG).show();

                }

                break;
        }

    }

}
