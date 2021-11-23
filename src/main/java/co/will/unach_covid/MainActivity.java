package co.chenao.unach_covid;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import co.chenao.unach_covid.actividades.AcercaDeActivity;
import co.chenao.unach_covid.actividades.AjustesActivity;
import co.chenao.unach_covid.actividades.ContenedorInstruccionesActivity;
import co.chenao.unach_covid.clases.ConexionSQLiteHelper;
import co.chenao.unach_covid.clases.Utilidades;
import co.chenao.unach_covid.dialogos.DialogoTipoJuegoFragment;
import co.chenao.unach_covid.fragments.GestionUSUARIOFragment;
import co.chenao.unach_covid.fragments.InicioFragment;
import co.chenao.unach_covid.fragments.RankingFragment;
import co.chenao.unach_covid.fragments.RegistroUSUARIOFragment;
import co.chenao.unach_covid.interfaces.IComunicaFragments;
import co.chenao.unach_covid.clases.PreferenciasJuego;

public class MainActivity extends AppCompatActivity implements IComunicaFragments, InicioFragment.OnFragmentInteractionListener,RegistroUSUARIOFragment.OnFragmentInteractionListener,GestionUSUARIOFragment.OnFragmentInteractionListener, RankingFragment.OnFragmentInteractionListener {

    Fragment fragmentInicio,registroUSUARIOFragment,gestionUSUARIOFragment,rankingFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PreferenceManager.setDefaultValues(this,R.xml.preferencias,false);

        SharedPreferences preferences= android.preference.PreferenceManager.getDefaultSharedPreferences(this);
        PreferenciasJuego.obtenerPreferencias(preferences,getApplicationContext());

        System.out.println("COLOR MAIN");
        System.out.println("COLOR: "+PreferenciasJuego.colorTema);

        Utilidades.obtenerListaAvatars();//llena la lista de avatars para ser utilizada
        Utilidades.consultarListaUSUARIOes(this);

        ConexionSQLiteHelper conn=new ConexionSQLiteHelper(this,Utilidades.NOMBRE_BD,null,1);

        fragmentInicio =new InicioFragment();
        registroUSUARIOFragment=new RegistroUSUARIOFragment();
        gestionUSUARIOFragment=new GestionUSUARIOFragment();
        rankingFragment=new RankingFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragments,fragmentInicio).commit();

    }

    public void onClick(View view) {

    }

    public AlertDialog createSimpleDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        builder.setTitle("GESTIONAR USUARIO")
                .setMessage("Indique si desea registrar un nuevo USUARIO o si desea seleccionar uno ya existente.\n\n" +
                        "También podrá modificar un USUARIO desde la opción SELECCIONAR")
                .setNegativeButton("REGISTRAR",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Utilidades.avatarIdSeleccion=0;//solo cuando se haga lo de seleccionar avatar en USUARIO
                                Utilidades.avatarSeleccion=Utilidades.listaAvatars.get(0);//se asigna el primer elemento para que siempre se tenga esta referencia hasta el evento clic
                              //  Toast.makeText(getApplicationContext(),"Avatar Seleccion: "+Utilidades.avatarSeleccion.getId(),Toast.LENGTH_SHORT).show();
                                getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragments,registroUSUARIOFragment).commit();
                            }
                        })
                .setPositiveButton("SELECCIONAR",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (Utilidades.listaUSUARIOes!=null && Utilidades.listaUSUARIOes.size()>0)
                                {
                                    getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragments,gestionUSUARIOFragment).commit();
                                }else{
                                    Toast.makeText(getApplicationContext(),"Debe registrar un USUARIO",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

        return builder.create();
    }

    /*se controla la pulsacion del boton atras*/
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == event.KEYCODE_BACK) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("¿Desea salir de unach_covid?")
                    .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent intent = new Intent(Intent.ACTION_MAIN);
                            intent.addCategory(Intent.CATEGORY_HOME);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                    })
                    .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.dismiss();
                        }
                    });
            builder.show();
        }

        return super.onKeyDown(keyCode, event);
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void mostrarMenu() {
        Utilidades.obtenerListaAvatars();//llena la lista de avatars para ser utilizada
        Utilidades.consultarListaUSUARIOes(this);

        getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragments,fragmentInicio).commit();
    }

    @Override
    public void iniciarJuego() {

        if (Utilidades.listaUSUARIOes!=null && Utilidades.listaUSUARIOes.size()>0)
        {
             DialogoTipoJuegoFragment dialogoTipoJuego=new DialogoTipoJuegoFragment();
            dialogoTipoJuego.show(getSupportFragmentManager(),"DialogoTipoJuego");
        }else{
            Toast.makeText(getApplicationContext(),"Debe registrar un USUARIO",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void llamarAjustes() {
     //   Toast.makeText(getApplicationContext(),"Ajustes desde la actividad",Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(this, AjustesActivity.class);
        startActivity(intent);
    }

    @Override
    public void consultarRanking() {
     //   Toast.makeText(getApplicationContext(),"Ranking desde la actividad",Toast.LENGTH_SHORT).show();
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragments,rankingFragment).commit();
    }

    @Override
    public void consultarInstrucciones() {
     //   Toast.makeText(getApplicationContext(),"Ayuda desde la actividad",Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(this, ContenedorInstruccionesActivity.class);
        startActivity(intent);
    }

    @Override
    public void gestionarUsuario() {
        createSimpleDialog().show();
    }

    @Override
    public void consultarInformacion() {
     //   Toast.makeText(getApplicationContext(),"Informacion desde la actividad",Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(this, AcercaDeActivity.class);
        startActivity(intent);
    }
}
