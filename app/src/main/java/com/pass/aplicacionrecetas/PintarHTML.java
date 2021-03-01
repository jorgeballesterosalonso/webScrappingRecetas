package com.pass.aplicacionrecetas;

import java.util.ArrayList;

public class PintarHTML {
    public static String pintarHTML(ArrayList<Receta> lista) {
        String html = "<html><head></head><body><table>";

        for (Receta r : lista) {
            html += "<tr><td>" + r.getNombre() + "</td><td><a href='" + r.getEnlaceReceta() + "'><img src='" + r.getRutaImagen() + "'></a></td>";
        }
        html += "</table></body></html>";
        return html;
    }
}
