package com.demo.saulromero.demobran.model;

public class EmpleadoModel {

    String nombre;
    String fecha;
    String puesto;

    public EmpleadoModel(){}

    public EmpleadoModel(String nombre, String fecha, String puesto) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.puesto = puesto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }
}
