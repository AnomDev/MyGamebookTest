package com.serbladev.mygamebook.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.serbladev.mygamebook.R;
import com.serbladev.mygamebook.activities.InventarioActivity;
import com.serbladev.mygamebook.pojo.Objeto;

import java.util.ArrayList;

public class ObjetoAdapter extends RecyclerView.Adapter<ObjetoAdapter.PlantillaParaCadaFila> implements View.OnClickListener{
    private View.OnClickListener listener;
    private ArrayList<Objeto> inventario;
    InventarioActivity contextopadre;



    public ObjetoAdapter(ArrayList<Objeto> inventario,  InventarioActivity contextopadre){
        this.inventario = inventario;
        this.contextopadre = contextopadre;
    }

    public static class PlantillaParaCadaFila extends RecyclerView.ViewHolder{
        private ImageView ivObjeto;
        private TextView tvNombre, tvDescripcion;
        private ConstraintLayout layoutContenedorItem;

        public PlantillaParaCadaFila(View itemView){
            super(itemView);
            ivObjeto = itemView.findViewById(R.id.ivImagenObjeto);
            tvNombre = itemView.findViewById(R.id.tvNombreObjeto);
/*
            tvDescripcion = itemView.findViewById(R.id.tvDescripcionObjeto);
*/
            layoutContenedorItem = itemView.findViewById(R.id.layout_contenedor);
        }
    }
    @Override
    public PlantillaParaCadaFila onCreateViewHolder(ViewGroup viewGroup, int viewType){
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cadafila_inventario_layout, viewGroup, false);
        PlantillaParaCadaFila viewHolderObjeto = new PlantillaParaCadaFila(itemView);
        itemView.setOnClickListener(this);
        return viewHolderObjeto;
    }

    @Override
    public void onBindViewHolder (PlantillaParaCadaFila pbjetoCreadoDeCadaFila, int pos){
        Objeto o = inventario.get(pos);
        pbjetoCreadoDeCadaFila.ivObjeto.setImageBitmap(o.getImagen());
        pbjetoCreadoDeCadaFila.tvNombre.setText(o.getNombre());
/*
        pbjetoCreadoDeCadaFila.tvDescripcion.setText(o.getDescripcion());
*/

        pbjetoCreadoDeCadaFila.layoutContenedorItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    contextopadre.hanPinchadoEnUnaFila(pos);
            }
        });

    }

    @Override
    public int getItemCount(){
        return inventario.size();
    }

        @Override
    //TODO: Aquí si cambio el public long por int (que es como debería) me dice que me peine.
    public long getItemId(int pos){
        return pos;
    }

    @Override
    public int getItemViewType(int pos){
        return pos;
    }
    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }
    @Override
    public void onClick(View view) {
        if (listener != null)
            listener.onClick(view);
    }



}
