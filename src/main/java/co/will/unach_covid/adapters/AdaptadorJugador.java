package co.chenao.unach_covid.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import co.chenao.unach_covid.R;
import co.chenao.unach_covid.clases.PreferenciasJuego;
import co.chenao.unach_covid.clases.Utilidades;
import co.chenao.unach_covid.clases.vo.AvatarVo;
import co.chenao.unach_covid.clases.vo.usuarioVo;

public class Adaptadorusuario extends RecyclerView.Adapter<Adaptadorusuario.ViewHolderusuario> implements View.OnClickListener {

    private View.OnClickListener listener;
    List<usuarioVo> listausuario;
    View vista;

    public Adaptadorusuario(List<usuarioVo> listausuario) {
        this.listausuario = listausuario;
    }

    @NonNull
    @Override
    public ViewHolderusuario onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        vista=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list_usuario,viewGroup,false);
        vista.setOnClickListener(this);
        return new ViewHolderusuario(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderusuario viewHolderusuario, int i) {

        //se resta uno ya que buscamos la lista de elementos que inicia en la pos 0
        viewHolderusuario.imgAvatar.setImageResource(Utilidades.listaAvatars.get(listausuario.get(i).getAvatar()-1).getAvatarId());
        viewHolderusuario.txtNombre.setText(listausuario.get(i).getNombre());
        if (listausuario.get(i).getGenero().equals("M")){
            viewHolderusuario.txtGenero.setText("Masculino");
        }else{
            viewHolderusuario.txtGenero.setText("Femenino");
        }

    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        return listausuario.size();
    }

    @Override
    public void onClick(View view) {
        if(listener!=null){
            listener.onClick(view);
        }
    }

    public class ViewHolderusuario extends RecyclerView.ViewHolder {

        ImageView imgAvatar;
        TextView txtNombre;
        TextView txtGenero;

        public ViewHolderusuario(@NonNull View itemView) {
            super(itemView);
            imgAvatar=itemView.findViewById(R.id.idAvatar);
            txtNombre=itemView.findViewById(R.id.idNombre);
            txtGenero=itemView.findViewById(R.id.idGenero);
        }

    }
}
