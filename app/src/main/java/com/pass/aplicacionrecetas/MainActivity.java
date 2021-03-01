package com.pass.aplicacionrecetas;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnBuscar = null;
    WebView wvTabla = null;
    ProgressBar pb = null;
    EditText et = null;

    Handler manejador = null;
    ArrayList<Receta> lista = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et = findViewById(R.id.edittext);
        pb = findViewById(R.id.progressBar);
        pb.setVisibility(View.INVISIBLE);
        btnBuscar = findViewById(R.id.button);
        wvTabla = findViewById(R.id.WebView);


        manejador = new Handler(getMainLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                lista = (ArrayList<Receta>) msg.obj;
                Log.d("MENSAJE", lista.toString());
                //Relleno mi webview
                pb.setVisibility(View.INVISIBLE);

                wvTabla.loadData(PintarHTML.pintarHTML(lista), "text/html", "UTF-8");
            }
        };

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1. Pillamos el ingrediente
                String ingrediente = et.getText().toString();

                //2. Arrancamos el hilo que hace el webScrapping
                Thread hilo = new Thread(new HiloWebscrapping(manejador, ingrediente));
                hilo.start();

                pb.setVisibility(View.VISIBLE);
            }
        });
    }
}