package co.chenao.unach_covid.actividades;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import co.chenao.unach_covid.R;
import co.chenao.unach_covid.clases.PreferenciasJuego;
import co.chenao.unach_covid.clases.Utilidades;

public class Nivel1Activity extends AppCompatActivity {

    private String arregloNombres[]=new String[4];
    private int arregloColores[]=new int[4];


    LinearLayout barraSuperior;
    TextView txtCantidad, txtCorrectas, txtRestante, txtPalabra,txtCambia,txtPuntaje;
    ProgressBar pTiempo;
    FloatingActionButton btnPause;
    Button btnBien, btnMal;
    boolean bandera = true;
    boolean bandera1 = true;
    int finalizaJuego=0;//al momento de finalizar
    int valorBoton;//para definir el valor obtenido
    int colorR;//define el rango de 1 a 4 para asignacion de color
    int palabraR;//define el rango de 1 a 4 para asignacion de palabra del color
    int pausar;//variable para definir la cantidad de veces que queremos pausar
    String modo;//modo de juego
    long tiempo;//tiempo de juego
    long tiempoPalabra;//tiempo de la palabra
    int cantidad, intentos;
    int [] milisegundos = {0,30000};//define el tiempo de juego dependiendo de lo parametrizado
    private Handler miHandler2=new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nivel1);

        SharedPreferences preferences= android.preference.PreferenceManager.getDefaultSharedPreferences(this);
        PreferenciasJuego.obtenerPreferencias(preferences,getApplicationContext());

        txtCantidad = findViewById(R.id.txtCantidad);
        txtCorrectas = findViewById(R.id.txtCorrectas);
        txtRestante = findViewById(R.id.txtRestante);
        txtPalabra = findViewById(R.id.txtPalabra);
        txtCambia = findViewById(R.id.txtCambia);
        txtPuntaje=findViewById(R.id.txtPuntaje);
        pTiempo= findViewById(R.id.pTiempo);
        btnPause = findViewById(R.id.btnPause);
        btnBien=findViewById(R.id.btnBien);
        btnMal=findViewById(R.id.btnMal);

        barraSuperior=findViewById(R.id.barraSuperiorId);
        barraSuperior.setBackgroundColor(getResources().getColor(PreferenciasJuego.colorTema));

        inicializaArreglos();
        definirBotonesAleatorios();
        inicializaValores();
        asignaValores();
        iniciarJuego();

        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pausar();
            }
        });
    }


    private void inicializaArreglos() {

        arregloNombres[0]="AMARILLO";   arregloNombres[1]="AZUL";
        arregloNombres[2]="ROJO";   arregloNombres[3]="VERDE";

        arregloColores[0]=getResources().getColor(R.color.colorAmarillo);   arregloColores[1]=getResources().getColor(R.color.colorAzul);
        arregloColores[2]=getResources().getColor(R.color.colorRojo);   arregloColores[3]=getResources().getColor(R.color.colorVerde);

    }

    //Método para hacer aleatorio la palabra, colores y botones
    private void definirBotonesAleatorios() {

        Random r=new Random();//permite la generacion de numeros aleatorios
        palabraR=r.nextInt(4);
        colorR=r.nextInt(4);

        txtPalabra.setText(arregloNombres[palabraR]);
        txtPalabra.setTextColor(arregloColores[colorR]);
    }

    //Método para ingresar valores predeterminados
    private void inicializaValores() {
        modo=PreferenciasJuego.modoJuego;
        tiempoPalabra=PreferenciasJuego.duracionPalabra;

        Utilidades.correctas =0;
        Utilidades.incorrectas=0;
        Utilidades.puntaje=0;
        bandera=true;
        bandera1=true;
        // ab=0;

        if (modo.equals("INTENTOS")) {
            intentos = PreferenciasJuego.numIntentos;
            pTiempo.setMax(intentos);
            pTiempo.setProgress(intentos);
        }else {
            intentos=0;
            tiempo=PreferenciasJuego.tiempoJuego;
            milisegundos[1]= (int) tiempo;
            pTiempo.setMax((int) tiempo);
            pTiempo.setProgress((int) tiempo);
        }
    }

    //Método para ingresar a los TextView la información cantidad de palabras, palabras correctas, intentos restantes
    private void asignaValores() {
        txtCantidad.setText(Integer.toString(cantidad));
        txtCorrectas.setText(Integer.toString(Utilidades.correctas));
        if (modo.equals("INTENTOS")){
            txtCambia.setText("Intentos Faltantes");
            txtRestante.setText(Integer.toString(intentos));
            txtPuntaje.setText(Utilidades.puntaje+"");
        }else {
            txtCambia.setText("Tiempo Faltante");
            txtRestante.setText(Integer.toString((int) tiempo));
            txtPuntaje.setText(Utilidades.puntaje+"");
        }
    }

       

      

    

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnBien:
                validaColores(palabraR,colorR,1);
                break;

            case R.id.btnMal:
                validaColores(palabraR,colorR,2);
                break;
        }
    }

    private void validaColores(int aleatorio1,int aleatorio2,int evento) {
        if (evento==3)
        {
            Utilidades.incorrectas++;
        }else{
            if (evento==1){
                if (aleatorio1==aleatorio2){
                    Utilidades.puntaje+=10;
                    Utilidades.correctas++;
                }else{
                   // puntaje-=10;
                    intentos--;
                    Utilidades.incorrectas++;
                }
            }else{
                if (evento==2){
                    if (aleatorio1!=aleatorio2){
                        Utilidades.puntaje+=10;
                        Utilidades.correctas++;
                    }else{
                     //   puntaje-=10;
                        intentos--;
                        Utilidades.incorrectas++;
                    }
                }
            }
        }

        cantidad++;
        terminarJuego();
        definirBotonesAleatorios();
        asignaValores();
        milisegundos[0]=0;

    }



    


   

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bandera=false;
        bandera1=false;
    }

    @Override
    protected void onStop() {
        super.onStop();
        bandera1=false;
    }

    @Override
    protected void onPause() {
        super.onPause();
        bandera1=false;
    }

}
