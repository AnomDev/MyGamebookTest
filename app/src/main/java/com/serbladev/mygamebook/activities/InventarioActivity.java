package com.serbladev.mygamebook.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.serbladev.mygamebook.pojo.Objeto;
import com.serbladev.mygamebook.adapters.ObjetoAdapter;
import com.serbladev.mygamebook.R;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class InventarioActivity extends AppCompatActivity {

    RecyclerView recyclerViewInventario;
    ImageView imagenObjeto;
    ArrayList<Objeto> inventario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventario);

        recyclerViewInventario = findViewById(R.id.rvInventario);
        // Obtenemos el intent que traemos desde la otra actividad.
        Intent intent = getIntent();
        String inventarioTraido = intent.getStringExtra("inventarioEnTexto");

        //Volvemos a crear un objeto Gson
        Gson unGson = new Gson();
        //Con Type le indicamos el tipo de dato que vamos a recuperar (en este caso un ArrayList de Objetos)
        Type typeDeArrayList = new TypeToken<ArrayList<Objeto>>(){}.getType();
        //Aquí acabamos de convertir el JSON (String inventarioTraido) al ArrayList (Inventario)
        inventario = unGson.fromJson(inventarioTraido, typeDeArrayList);


        /*
        imagenObjeto.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.rifle_vintar));
        */


        recyclerViewInventario = (RecyclerView) findViewById(R.id.rvInventario);
        recyclerViewInventario.setHasFixedSize(true);
        ObjetoAdapter objetoAdapter = new ObjetoAdapter(inventario, this);
        recyclerViewInventario.setAdapter(objetoAdapter);
        recyclerViewInventario.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    public void cerrarInventario(View view) {
        finish();
    }

    public void hanPinchadoEnUnaFila(int fila){
        Toast.makeText(this, "AQUI SE HA elegido la fila "+ fila, Toast.LENGTH_SHORT).show();
        mostrarVistaDetalleObjeto (fila);
    }

    public void mostrarVistaDetalleObjeto (int fila){

        Objeto elegido = inventario.get(fila);

        // esta vacio por que eso es lo que por ahora queremos que haga el boton atras, exactamente NADA
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

        LayoutInflater inflaor = getLayoutInflater();
        View midialogo = inflaor.inflate(R.layout.vistadetalle_inventario_layout, null);
        dialogBuilder.setView(midialogo);

        ConstraintLayout clVistaDetalle =  midialogo.findViewById(R.id.clVistaDetalle);
        ImageView ivImagenDetalle = midialogo.findViewById(R.id.ivImagenDetalle);
        TextView tvNombreDetalle = midialogo.findViewById(R.id.tvNombreDetalle);
        TextView tvDescripcionDetalle = midialogo.findViewById(R.id.tvDescripcionDetalle);
        Button bCerrarDetalle = midialogo.findViewById(R.id.bCerrarDetalle);

        ivImagenDetalle.setImageBitmap(elegido.getImagen());
        tvNombreDetalle.setText(elegido.getNombre());
        tvDescripcionDetalle.setText(elegido.getDescripcion());

        AlertDialog miAlertDialogoFinal = dialogBuilder.create();


        bCerrarDetalle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                miAlertDialogoFinal.cancel();
            }
        });

        miAlertDialogoFinal.show();
    }

}