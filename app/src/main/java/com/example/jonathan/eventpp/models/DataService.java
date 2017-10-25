package com.example.jonathan.eventpp.models;

import com.example.jonathan.eventpp.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by jOnathan on 23/09/2017.
 */

public class DataService {

    List<Event> eventList;

    public  DataService(){
        eventList = new ArrayList<>();
        eventList.add(new Event("Red Hot Chili Peppers Peru",new Date(),"Ainhoa Restaurante","Avenida German Aguirre 955 Urb.El rosario - SMP, 051 San Martín",Integer.toString(R.mipmap.ic_badbunny),-76.977113,-12.085407 ));
        eventList.add(new Event("Mis 23 añitos ♥",new Date(),"Ainhoa Restaurante","Avenida German Aguirre 955 Urb.El rosario - SMP, 051 San Martín",Integer.toString(R.mipmap.ic_party), -77.001440,-12.111217));
        eventList.add(new Event("CUBA SALSA FEST @ ANIVERSARIO CHA",new Date(),"Ainhoa Restaurante","Avenida German Aguirre 955 Urb.El rosario - SMP, 051 San Martín",Integer.toString(R.mipmap.ic_hallowinka),-10.285,-1.582));
        eventList.add(new Event("20 ANIVERSARIO CHARANGA HABANERA @ CUBA SALSA FEST ",new Date(),"Ainhoa Restaurante","Avenida German Aguirre 955 Urb.El rosario - SMP, 051 San Martín",Integer.toString(R.mipmap.ic_strangerhalloween), -76.969039 ,-12.099303));
    }
    public List<Event> getEventList(){
        return eventList;
    }

    public  int getAllProducts() {
        return eventList.size();
    }

    public boolean addProduct(Event event){
        eventList.add(event);
        return  true;
    }

}
