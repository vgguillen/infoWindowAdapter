package com.example.appmapas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements OnMapReadyCallback,
        GoogleMap.OnMapClickListener{

    GoogleMap mapa;
    int tipoVista;
    LatLng[] puntos = new LatLng[4];
    int cpuntos;
    LatLng[] coordenadas = new LatLng[5];



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        tipoVista=1;
        cpuntos=0;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mapa = googleMap;

        mapa.getUiSettings().setZoomControlsEnabled(true);

        mapa.setOnMapClickListener(this);


        coordenadas[0] = new LatLng(-1.012742,-79.4705562);//FCI
        coordenadas[1] = new LatLng(-1.012208,-79.4709598);//FCE
        coordenadas[2] = new LatLng(-1.012624,-79.4710982);//FCIP
        coordenadas[3] = new LatLng(-1.012766,-79.4715922);//FCSE
        coordenadas[4] = new LatLng(-1.081314,-79.5036452);//FCA

        String[] nombres = new String[5];
        nombres[0] = "Facultad de Ciencias de la Ingeniería";
        nombres[1] = "Facultad de Ciencias Empresariales";
        nombres[2] = "Facultad de Ciencias de la Ingeniería y Producción";
        nombres[3] = "Facultad de Ciencias Sociales y la Educación";
        nombres[4] = "Facultad de Ciencias Agrarias";

        String[] decanos = new String[5];
        decanos[0] = "Decano: Ing. Washington Alberto Chiriboga Casanova, M.Sc.\n" +
                "Correo: facultadci@uteq.edu.ec";
        decanos[1] = "Decana: Ing. Mariela Susana Andrade Arias, PhD.\n" +
                "Correo: facultadce@uteq.edu.ec";
        decanos[2] = "Decana: Ing. Sonnia Esther Barzola Miranda, M.Sc.\n" +
                "Correo: fcip@uteq.edu.ec";
        decanos[3] = "Decano S/N";
        decanos[4] = "Decano: Ing. Leonardo Gonzalo Matute, M.Sc.\n" +
                "Correo: decanato_agropecuarias@uteq.edu.ec";
        String[] url = new String[5];
        url[0] = "https://github.com/vgguillen/infoWindowAdapter/blob/main/Logos/logo_fci.png?raw=true";
        url[1] = "https://github.com/vgguillen/infoWindowAdapter/blob/main/Logos/logo_fce.png?raw=true";
        url[2] = "https://github.com/vgguillen/infoWindowAdapter/blob/main/Logos/logo_fcip.png?raw=true";
        url[3] = "https://github.com/vgguillen/infoWindowAdapter/blob/main/Logos/Logo_fcde.png?raw=true";
        url[4] = "https://github.com/vgguillen/infoWindowAdapter/blob/main/Logos/FCP.png?raw=true";

        for (int i = 0; i < 5; i++) {
            mapa.addMarker(new MarkerOptions().position(coordenadas[i]).title(nombres[i]).snippet(""+decanos[i]+" % "+"\nLat: "
                    +coordenadas[i].latitude+" lng: "+coordenadas[i].longitude+" % "+url[i]));
            mapa.setInfoWindowAdapter(new CustomInfoWindowAdapter(getLayoutInflater()));
        }

    }

    public void ConfigurarMapa(View v){

        mapa.setMapType(tipoVista);
        tipoVista = tipoVista<4?tipoVista+1:1;

        LatLng uteq = new LatLng(-1.0128684338088096, -79.46930575553893);

        CameraPosition camPos = new CameraPosition.Builder()
                .target(uteq)
                .zoom(18)
                .bearing(65)      //noreste arriba
                .tilt(80)         //punto de vista de la cámara grados
                .build();

        CameraUpdate camUpd3 =
                CameraUpdateFactory.newCameraPosition(camPos);

        mapa.animateCamera(camUpd3);

    }

    @Override
    public void onMapClick(LatLng latLng) {
        /*
         Projection proj = mapa.getProjection();
        Point coord = proj.toScreenLocation(latLng);

        Toast.makeText(
                MainActivity.this,
                "Click\n" +
                        "Lat: " + latLng.latitude + "\n" +
                        "Lng: " + latLng.longitude + "\n" +
                        "X: " + coord.x + " - Y: " + coord.y,
                Toast.LENGTH_SHORT).show();


        puntos[cpuntos] = new LatLng(latLng.latitude,latLng.longitude);
        mapa.addMarker(new
                MarkerOptions().position(puntos[cpuntos])
                .title("Punto " + (cpuntos + 1))
                .snippet("Nombre del Lugar"));
        cpuntos++;

        if(cpuntos==4){
            PolylineOptions lineas = new PolylineOptions()
                    .add(puntos[0])
                    .add(puntos[1])
                    .add(puntos[2])
                    .add(puntos[3])
                    .add(puntos[0]);
            lineas.width(8);
            lineas.color(Color.RED);
            mapa.addPolyline(lineas);
            cpuntos=0;
        }
*/
    }
}