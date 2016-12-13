package com.izv.dam.newquip.notaubicacion;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class Adaptador extends RecyclerView.Adapter<Adaptador.NotasViewHolder> {

    private AdapterView.OnItemClickListener onItemClickListener;
    private AdapterView.OnItemLongClickListener onItemLongClickListener;

    List<Nota> notas;
    Context context;

    public Adaptador(List<Nota> notas, Context context) {
        this.notas = notas;
        this.context = context;
    }

    @Override
    public Adaptador.NotasViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        NotasViewHolder viewHolder=new NotasViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(Adaptador.NotasViewHolder holder, final int position) {
        holder.titulo.setText(notas.get(position).getTitulo());
        holder.nota.setText(notas.get(position).getNota());
        holder.longitug.setText(notas.get(position).getLongitud()+"");
        holder.latitud.setText(notas.get(position).getLatitud()+"");
        holder.fecha.setText(notas.get(position).getFecha());

        holder.ll.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, EditarNota.class);
                i.putExtra("id",notas.get(position).getId());
                context.startActivity(i);
            }
        });

        holder.ll.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                String loc = "geo:"+notas.get(position).getLatitud()+"," + notas.get(position).getLongitud()+
                        "?z=16&q="+notas.get(position).getLatitud()+"," + notas.get(position).getLongitud()+"("+notas.get(position).getTitulo()+")";
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(loc));
                intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                context.startActivity(intent);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return notas.size();
    }

    public static class NotasViewHolder extends RecyclerView.ViewHolder{

        protected TextView titulo;
        protected TextView nota;
        protected TextView longitug;
        protected TextView latitud;
        protected TextView fecha;
        protected LinearLayout ll;

        public NotasViewHolder(View itemView) {
            super(itemView);
            titulo= (TextView) itemView.findViewById(R.id.tvTitulo);
            nota= (TextView) itemView.findViewById(R.id.tvNota);
            longitug= (TextView) itemView.findViewById(R.id.tvLongitud);
            latitud= (TextView) itemView.findViewById(R.id.tvLatitud);
            ll= (LinearLayout) itemView.findViewById(R.id.ll);
            fecha= (TextView) itemView.findViewById(R.id.tvFecha);
        }
    }
}