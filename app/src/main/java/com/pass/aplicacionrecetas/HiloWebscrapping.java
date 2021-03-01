package com.pass.aplicacionrecetas;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class HiloWebscrapping implements Runnable {

    private Handler h;
    private String ingrediente;

    HiloWebscrapping(Handler h, String ingrediente) {
        this.h = h;
        this.ingrediente = ingrediente;
    }

    @Override
    public void run() {
        ArrayList<Receta> listaRecetas = new ArrayList<>();
        String ruta = "https://www.recetasderechupete.com/?s=";
        ruta += ingrediente;

        try {
            Document doc = Jsoup.connect(ruta).get();
            String selectorPadre = "pure-u-1-2 pure-u-lg-1-5";
            Elements elementosReceta = doc.getElementsByClass(selectorPadre);

            for (Element elem : elementosReceta) {
                Element imagen = elem.selectFirst("img");
                Element enlace = elem.selectFirst("a");

                String rutaImagen = imagen.absUrl("src");
                String nombreReceta = enlace.selectFirst("strong").text();
                String rutaReceta = enlace.attr("href");

                Receta r = new Receta(nombreReceta, rutaImagen, rutaReceta);
                Log.d("MENSAJE", r.toString());
                listaRecetas.add(r);
            }

            Message mensaje = new Message();
            mensaje.obj = listaRecetas;
            Log.d("MENSAJE", listaRecetas.toString());
            h.sendMessage(mensaje);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
