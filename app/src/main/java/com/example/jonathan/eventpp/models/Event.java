package com.example.jonathan.eventpp.models;

import java.util.Date;

/**
 * Created by jOnathan on 23/09/2017.
 */

public class Event {
    private String nameEvent;
    private Date dateEvent;
    private String discotect;
    private String ubicacion;
    private String imagenURL;
    private Double longitud;
    private Double latidud;


    public Event(String nameEvent, Date dateEvent, String discotect, String ubicacion, String imagenURL, Double longitud, Double latidud) {
        this.nameEvent = nameEvent;
        this.dateEvent = dateEvent;
        this.discotect = discotect;
        this.ubicacion = ubicacion;
        this.imagenURL = imagenURL;
        this.longitud = longitud;
        this.latidud = latidud ;

    }


    public Event() {
    }

    public String getNameEvent() {
        return nameEvent;
    }

    public void setNameEvent(String nameEvent) {
        this.nameEvent = nameEvent;
    }

    public Date getDateEvent() {
        return dateEvent;
    }

    public void setDateEvent(Date dateEvent) {
        this.dateEvent = dateEvent;
    }

    public String getDiscotect() {
        return discotect;
    }

    public void setDiscotect(String discotect) {
        this.discotect = discotect;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public  void setImagenURL(String imagen){
        this.imagenURL = imagen;
    }

    public  String getImagenURL(){
        return imagenURL;
    }


    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public Double getLatidud() {
        return latidud;
    }

    public void setLatidud(Double latidud) {
        this.latidud = latidud;
    }
}
