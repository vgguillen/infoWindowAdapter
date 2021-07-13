package com.example.appmapas;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.squareup.picasso.Picasso;

import java.util.zip.Inflater;

public class CustomInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {
    Context context;
    LayoutInflater inflater;
    public CustomInfoWindowAdapter(LayoutInflater context) {
        this.inflater = context;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        View infView = inflater.inflate(R.layout.custom_info, null);
        TextView Facultad = infView.findViewById(R.id.facultad);
        TextView Decano = infView.findViewById(R.id.Decano);
        TextView Ubicacion = infView.findViewById(R.id.Ubicacion);
        ImageView Logo = (ImageView) infView.findViewById(R.id.ImageView);

        Facultad.setText(marker.getTitle());
        Decano.setText(marker.getSnippet().split("%")[0]);
        Ubicacion.setText(marker.getSnippet().split("%")[1]);
        String ruta = marker.getSnippet().split("%")[2];

        //Log.e("ruta","http://www.uteq.edu.ec/images/about/logo_fci.jpg");
        Picasso.get()
                .load(ruta)
                .into(Logo);

        return infView;
    }
}
