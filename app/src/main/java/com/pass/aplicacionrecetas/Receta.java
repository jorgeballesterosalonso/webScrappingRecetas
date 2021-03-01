package com.pass.aplicacionrecetas;

public class Receta {
    private String nombre;
    private String rutaImagen;
    private String enlaceReceta;

    public Receta(String nombre, String rutaImagen, String enlaceReceta) {
        this.nombre = nombre;
        this.rutaImagen = rutaImagen;
        this.enlaceReceta = enlaceReceta;
    }

    public String getNombre() {
        return nombre;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public String getEnlaceReceta() {
        return enlaceReceta;
    }

    @Override
    public String toString() {
        return "Receta{" +
                "nombre='" + nombre + '\'' +
                ", rutaImagen='" + rutaImagen + '\'' +
                ", enlaceReceta='" + enlaceReceta + '\'' +
                '}';
    }
}
