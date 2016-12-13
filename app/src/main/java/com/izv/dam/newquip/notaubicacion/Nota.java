package com.izv.dam.newquip.notaubicacion;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "nota")
public class Nota {
    @DatabaseField(generatedId = true,allowGeneratedIdInsert=true)
    private int id;

    @DatabaseField
    private String titulo;

    @DatabaseField
    private String nota;

    @DatabaseField
    private String fecha;

    @DatabaseField
    private double latitud;

    @DatabaseField
    private double longitud;

    public Nota(int id, String titulo, String nota, String fecha, double latitud, double altitud) {
        this.id = id;
        this.titulo = titulo;
        this.nota = nota;
        this.fecha = fecha;
        this.latitud = latitud;
        this.longitud = altitud;
    }

    public Nota(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    @Override
    public String toString() {
        return "Nota{" +
                "id='" + id + '\'' +
                ", titulo='" + titulo + '\'' +
                ", nota='" + nota + '\'' +
                ", fecha=" + fecha +
                ", latitud=" + latitud +
                ", longitud=" + longitud +
                '}';
    }
}
