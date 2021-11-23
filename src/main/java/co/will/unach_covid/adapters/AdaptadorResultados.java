package co.chenao.unach_covid.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import co.chenao.unach_covid.R;
import co.chenao.unach_covid.clases.Utilidades;
import co.chenao.unach_covid.clases.vo.usuarioVo;
import co.chenao.unach_covid.clases.vo.ResultadosVo;

public class AdaptadorResultados extends RecyclerView.Adapter<AdaptadorResultados.ViewHolderusuario> implements View.OnClickListener {

    private View.OnClickListener listener;
    List<ResultadosVo> listausuario;
    View vista;

    public AdaptadorResultados(List<ResultadosVo> listausuario) {
        this.listausuario = listausuario;
    }

    @NonNull
    @Override
    public ViewHolderusuario onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        vista=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list_resultados,viewGroup,false);
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
        viewHolderusuario.txtNivel.setText(listausuario.get(i).getNivel());
        viewHolderusuario.txtPuntos.setText(listausuario.get(i).getPuntos()+"");
        viewHolderusuario.txtModo.setText(listausuario.get(i).getModo());
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
        TextView txtNivel;
        TextView txtModo;
        TextView txtPuntos;

        public ViewHolderusuario(@NonNull View itemView) {
            super(itemView);
            imgAvatar=itemView.findViewById(R.id.idAvatar);
            txtNombre=itemView.findViewById(R.id.idNombre);
            txtGenero=itemView.findViewById(R.id.idGenero);
            txtNivel=itemView.findViewById(R.id.idNivel);
            txtModo=itemView.findViewById(R.id.idModo);
            txtPuntos=itemView.findViewById(R.id.idPuntos);
        }

    }
}
