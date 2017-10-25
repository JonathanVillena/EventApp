package com.example.jonathan.eventpp.adapters;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jonathan.eventpp.R;
import com.example.jonathan.eventpp.models.Event;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by jOnathan on 23/09/2017.
 */

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder>{

    List<Event> eventList;
    String[] meses = {"ENE","FEB","MAR","ABR","MAY","JUN","JUL","AGO","SEPT"
            ,"OCT","NOV","DIC"};

    @Override
    public EventAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_event,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {


        holder.TextViewnameEvent.setText(eventList.get(position).getNameEvent());
        holder.TextViewdiscotect.setText(eventList.get(position).getDiscotect());
        holder.TextViewubicacion.setText(eventList.get(position).getUbicacion());
        holder.TextViewdateEvent.setText(String.valueOf(eventList.get(position).getDateEvent().getDate()));
        holder.TextViewMesEvent.setText( meses[eventList.get(position).getDateEvent().getMonth()]);
        holder.imageView.setImageResource(Integer.parseInt(eventList.get(position).getImagenURL()));


        /*holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.printf("Selected position: %d%n", position);
                Intent itemIntent = new Intent(v.getContext(), DetailActivity.class);
            }
        });*/

    }


    @Override
    public int getItemCount() {
        return  eventList.size();
    }

    public List<Event> getEvent() {
        return eventList;
    }


    public void setEvent(List<Event> events){
        this.eventList = events;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView TextViewnameEvent;
        TextView TextViewdateEvent;
        TextView TextViewMesEvent;
        TextView TextViewdiscotect;
        TextView TextViewubicacion;
       ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            TextViewnameEvent = (TextView) itemView.findViewById(R.id.info_text);
            TextViewdateEvent = (TextView) itemView.findViewById(R.id.date_textView);///
            TextViewMesEvent = (TextView) itemView.findViewById(R.id.mes_textView);///
            TextViewdiscotect = (TextView) itemView.findViewById(R.id.dis_text);
            TextViewubicacion = (TextView) itemView.findViewById(R.id.ubi_text);
            imageView = (ImageView) itemView.findViewById(R.id.event_imageView);

        }
    }
}
