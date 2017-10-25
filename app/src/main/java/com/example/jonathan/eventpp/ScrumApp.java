package com.example.jonathan.eventpp;

import android.app.Application;

import com.example.jonathan.eventpp.models.DataService;
import com.example.jonathan.eventpp.models.Event;

import java.util.List;

/**
 * Created by jOnathan on 23/09/2017.
 */

public class ScrumApp extends Application {
    private DataService dataService = new DataService();

    private static ScrumApp instance;

    public ScrumApp() {
        super();
        instance=this;
    }

    public static ScrumApp getInstance() {

        return instance;

    }

    public List<Event> getEvent(){
        return  dataService.getEventList();
    }

    public boolean AddEvent(Event event){
        return dataService.addProduct(event);
    }

}
