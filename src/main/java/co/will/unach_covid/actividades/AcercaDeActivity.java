package co.chenao.unach_covid.actividades;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import co.chenao.unach_covid.R;
import co.chenao.unach_covid.clases.PreferenciasJuego;

public class AcercaDeActivity extends AppCompatActivity {

    FloatingActionButton btnInicio;
    RelativeLayout layoutFondo;
    ImageButton icoYoutube,icoFace,icoInstagram,icoTwitter,icoBlog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acerca_de);

        layoutFondo=findViewById(R.id.idLayoutFondo);
        

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            layoutFondo.setBackground(getResources().getDrawable(PreferenciasJuego.formaBanner));
        }else{
            layoutFondo.setBackgroundDrawable(getResources().getDrawable(PreferenciasJuego.formaBanner));
        }

        Drawable shape = (Drawable) layoutFondo.getBackground();
        shape.setColorFilter(getResources().getColor(PreferenciasJuego.colorTema), android.graphics.PorterDuff.Mode.SRC);

        btnInicio=findViewById(R.id.btnHome);
        btnInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    
}
