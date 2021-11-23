package co.chenao.unach_covid.actividades;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import co.chenao.unach_covid.R;
import co.chenao.unach_covid.clases.PreferenciasJuego;

public class AjustesTemaActivity extends AppCompatActivity {

    Spinner comboModoTema;
    RelativeLayout layoutFondo;
    ImageButton btnAyuda;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajustes_tema);

        comboModoTema=findViewById(R.id.comboModoTema);
        layoutFondo=findViewById(R.id.idLayoutFondo);
        btnAyuda=findViewById(R.id.btnAyuda);

        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.tema,android.R.layout.simple_spinner_item);
        comboModoTema.setAdapter(adapter);

        asignarValoresPreferencias();

        btnAyuda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//
                createSimpleDialog().show();
            }
        });

        comboModoTema.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {

                if (adapterView.getItemAtPosition(pos).toString().equals("CIRCULAR")){
                    PreferenciasJuego.formaBanner=R.drawable.banner_redondo;
                }else{
                    PreferenciasJuego.formaBanner=R.drawable.banner_cuadrado;
                }
              //  Toast.makeText(getApplicationContext(),"Forma: "+adapterView.getItemAtPosition(pos).toString(),Toast.LENGTH_SHORT).show();
                //PreferenciasJuego.cambiarFondo(layoutFondo,getResources(),formaBanner);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void asignarValoresPreferencias() {

        layoutFondo.setBackgroundColor(getResources().getColor(PreferenciasJuego.colorTema));
        if (PreferenciasJuego.formaBanner==R.drawable.banner_redondo){
            comboModoTema.setSelection(0);
        }else{
            comboModoTema.setSelection(1);
        }

    }


    public void onClick(View view) {

        switch (view.getId()){
           
            case R.id.cardRojo:         PreferenciasJuego.colorTema=R.color.colorRojo;      break;
       
            case R.id.cardGris:         PreferenciasJuego.colorTema=R.color.colorGris;      break;
            case R.id.cardNegro:        PreferenciasJuego.colorTema=R.color.colorNegro;     break;
            case R.id.btnIcoAtras:      finish();                                           break;
        }
        layoutFondo.setBackgroundColor(getResources().getColor(PreferenciasJuego.colorTema));
    }

    public AlertDialog createSimpleDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(AjustesTemaActivity.this);

        builder.setTitle("Ayuda")
                .setMessage("Selecciona el Diseño de la App y el Color del tema que deseas aplicar, solo bastará con dar Atrás y los cambios estarán configurados.")
                .setPositiveButton("Aceptar",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

        return builder.create();
    }

    @Override
    protected void onDestroy() {
        SharedPreferences preferences=PreferenceManager.getDefaultSharedPreferences(this);
        PreferenciasJuego.asignarPreferenciasTema(preferences,getApplicationContext());
        super.onDestroy();
    }
}
