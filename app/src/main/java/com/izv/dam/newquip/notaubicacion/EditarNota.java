package com.izv.dam.newquip.notaubicacion;

import android.*;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationServices;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class EditarNota extends Activity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener {

    Dao<Nota, Integer> daoNota = null;
    private EditText editarTitulo;
    private EditText editarNota;
    private int nota_id;
    private CheckBox cbUbicacion;
    private Button añadir;
    private GoogleApiClient mGoogleApiClient;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crear_nota);
        init();
        Intent i = getIntent();
        nota_id = i.getIntExtra("id", 0);

        Ayudante a = new Ayudante(this);

        final Nota nota;
        try {
            daoNota = a.getDaoNota();
            nota = daoNota.queryForId(nota_id);
            editarNota.setText(nota.getNota());
            editarTitulo.setText(nota.getTitulo());


            añadir.setOnClickListener(new View.OnClickListener() {

                public void onClick(View view) {
                    String titulo = editarTitulo.getText().toString();
                    String n = editarNota.getText().toString();
                    nota.setTitulo(titulo);
                    nota.setNota(n);
                    //Fecha
                    Date d = new Date();
                    DateFormat dateFormat;
                    dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                    String fecha = dateFormat.format(d);
                    nota.setFecha(fecha);

                    if (cbUbicacion.isChecked() == true) {

                        if (ActivityCompat.checkSelfPermission(view.getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION)
                                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(view.getContext(),
                                android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                            // TODO: Consider calling
                            //    ActivityCompat#requestPermissions
                            // here to request the missing permissions, and then overriding
                            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                            //                                          int[] grantResults)
                            // to handle the case where the user grants the permission. See the documentation
                            // for ActivityCompat#requestPermissions for more details.
                            return;
                        }
                        Location location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
                        double longitud = location.getLongitude();
                        double latitud = location.getLatitude();
                        nota.setLatitud(latitud);
                        nota.setLongitud(longitud);
                    }

                    try {
                        daoNota.update(nota);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    //Crear un nuevo intent
                    Intent intent = new Intent(EditarNota.this, Principal.class);
                    //Iniciar actividad
                    startActivity(intent);
                }

            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    private void init() {
        editarTitulo = (EditText) findViewById(R.id.editTextTitulo);
        editarNota = (EditText) findViewById(R.id.editTextNota);
        cbUbicacion = (CheckBox) findViewById(R.id.checkBox);
        añadir = (Button) findViewById(R.id.btnAñadir);
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }

    }


    //GoogleApiClient.ConnectionCallbacks
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Toast toast1 =
                Toast.makeText(this, "Conectado", Toast.LENGTH_SHORT);
        toast1.show();
    }

    //GoogleApiClient.ConnectionCallbacks
    @Override
    public void onConnectionSuspended(int i) {
        Toast toast1 =
                Toast.makeText(this, "Se ha perdido la conexion", Toast.LENGTH_SHORT);
        toast1.show();
    }

    //GoogleApiClient.OnConnectionFailedListener
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast toast1 =
                Toast.makeText(this, "Se ha perdido la conexion", Toast.LENGTH_SHORT);
        toast1.show();
    }


    @Override
    public void onLocationChanged(Location location) {
        /*nota.setLongitud(location.getLongitude());
        nota.setLatitud(location.getLatitude());
        System.out.println("JJJJJJJJ : "+nota.getLatitud());
        System.out.println("JJJJJJJJ : "+nota.getLongitud());*/
    }
}
