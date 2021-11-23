package co.chenao.unach_covid.clases;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import co.chenao.unach_covid.R;
import co.chenao.unach_covid.clases.vo.AvatarVo;
import co.chenao.unach_covid.clases.vo.UsuarioVo;

public class Utilidades {

    //permite tener la referencia del avatar seleccionado para el momento del registro en la BD
    public static AvatarVo avatarSeleccion=null;
    public static int avatarIdSeleccion=0;

    public static ArrayList<AvatarVo> listaAvatars=null;
    public static ArrayList<UsuarioVo> listaUsuarioes=null;



    public static final String NOMBRE_BD="unach_covid";
    //Constantes campos tabla usuario
    public static final String TABLA_Usuario="Usuario";
    public static final String CAMPO_ID="id";
    public static final String CAMPO_NOMBRE="nombre";
    public static final String CAMPO_GENERO="genero";
    public static final String CAMPO_AVATAR="avatar";




    public static final String CREAR_TABLA_Usuario="CREATE TABLE "+TABLA_Usuario+" ("+CAMPO_ID+" INTEGER PRIMARY KEY, "+CAMPO_NOMBRE+" TEXT,"+CAMPO_GENERO+" TEXT,"+CAMPO_CORREO+" TEXT,"+CAMPO_FAMILIARES+" TEXT,"+CAMPO_TIENEVACUNA+" TEXT,"+CAMPO_AVATAR+" INTEGER)";
   

    public static void obtenerListaAvatars() {

        //se instancian los avatars y se llena la lista
        listaAvatars=new ArrayList<AvatarVo>();

        listaAvatars.add(new AvatarVo(1,R.drawable.avatar1,"Avatar1"));
       

    }

    public static void consultarListaUsuarioes(Activity actividad) {
        ConexionSQLiteHelper conn=new ConexionSQLiteHelper(actividad,NOMBRE_BD,null,1);
        SQLiteDatabase db=conn.getReadableDatabase();

        UsuarioVo Usuario=null;
        listaUsuarioes=new ArrayList<UsuarioVo>();
        //select * from usuarios
        Cursor cursor=db.rawQuery("SELECT * FROM "+Utilidades.TABLA_Usuario,null);

        while (cursor.moveToNext()){
            Usuario=new UsuarioVo();
            Usuario.setId(cursor.getInt(0));
            Usuario.setNombre(cursor.getString(1));
            Usuario.setGenero(cursor.getString(2));
            Usuario.setAvatar(cursor.getInt(3));

            listaUsuarioes.add(Usuario);
        }

        db.close();
    }
}
