package com.serbladev.mygamebook.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.serbladev.mygamebook.pojo.Capitulo;
import com.serbladev.mygamebook.pojo.Objeto;
import com.serbladev.mygamebook.R;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    ArrayList<Objeto> inventario = new ArrayList<>();


    TextView tvNumeroDeCapitulo;
    TextView tvPagina;

    Bitmap imagenObjeto;

    TextView tvEleccion1;
    Button bEleccion1;
    TextView tvEleccion2;
    Button bEleccion2;
    TextView tvEleccion3;
    Button bEleccion3;
    TextView tvEleccion4;
    Button bEleccion4;
    TextView[] textoEleccion = new TextView[4];
    Button[] botonEleccion = new Button[4];

    int capituloActual = 1;
    HashMap< Integer  , Capitulo> matrizpasos = new HashMap<>();
    ArrayList<String> capitulos = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvNumeroDeCapitulo = findViewById(R.id.tvNumCapitulo);
        tvPagina = findViewById(R.id.tvPagina);
        tvPagina.setMovementMethod(new ScrollingMovementMethod());

        tvEleccion1 = findViewById(R.id.tvEleccion1);
        bEleccion1 = findViewById(R.id.bEleccion1);

        tvEleccion2 = findViewById(R.id.tvEleccion2);
        bEleccion2 = findViewById(R.id.bEleccion2);

        tvEleccion3 = findViewById(R.id.tvEleccion3);
        bEleccion3 = findViewById(R.id.bEleccion3);

        tvEleccion4 = findViewById(R.id.tvEleccion4);
        bEleccion4 = findViewById(R.id.bEleccion4);


        //Array de los textos para las opciones.
        textoEleccion[0]= tvEleccion1;
        textoEleccion[1]= tvEleccion2;
        textoEleccion[2]= tvEleccion3;
        textoEleccion[3]= tvEleccion4;

        //Array con todos los botones posibles.
        botonEleccion[0]= bEleccion1;
        botonEleccion[1]= bEleccion2;
        botonEleccion[2]= bEleccion3;
        botonEleccion[3]= bEleccion4;


        iniciar();

        pintarPagina();

       /* bOpcion1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String loquehayenelboton = bOpcion1.getText().toString();
                paginaactual = Integer.parseInt(loquehayenelboton);

                pintarCapitulo();
            }
        });*/




    }




    public void pintarPagina(){
        tvPagina.setText(capitulos.get(capituloActual -1));
        pintarOpciones();

    }

    public void pintarOpciones(){
        Capitulo capituloActual = matrizpasos.get(this.capituloActual);
        for (int i = 0; i< textoEleccion.length ; i++){
            textoEleccion[i].setVisibility(View.INVISIBLE);
            botonEleccion[i].setVisibility(View.INVISIBLE);
        }
        for (int i = 0; i<capituloActual.getTextoElecciones().size(); i++){
            textoEleccion[i].setText(capituloActual.getTextoElecciones().get(i));
            botonEleccion[i].setText(capituloActual.getBotonEleccion().get(i)+"");
            textoEleccion[i].setVisibility(View.VISIBLE);
            botonEleccion[i].setVisibility(View.VISIBLE);
        }

        if(capituloActual.getObjetosEncontrados().size()>0){
            // TODO  CUIDADO CON NO REPETIR SI EN EL FUTURO SERGIO CAMBIA DE PARECER  !!!
            for(int i=0; i<capituloActual.getObjetosEncontrados().size(); i++){
                inventario.add(capituloActual.getObjetosEncontrados().get(i));
            }
            capituloActual.getObjetosEncontrados().clear();

            Snackbar.make(tvPagina, "Has encontrado algo. Revisa tu inventario", Snackbar.LENGTH_LONG).show();
        }

    }

    /*

    public void pintarOpciones(){
        Relaciones relacionesactuales = matrizpasos.get(paginaactual);
        tvOpcion1.setVisibility(View.INVISIBLE);
        bOpcion1.setVisibility(View.INVISIBLE);
        tvOpcion2.setVisibility(View.INVISIBLE);
        bOpcion2.setVisibility(View.INVISIBLE);
        tvOpcion3.setVisibility(View.INVISIBLE);
        bOpcion3.setVisibility(View.INVISIBLE);
        tvOpcion4.setVisibility(View.INVISIBLE);
        bOpcion4.setVisibility(View.INVISIBLE);

        if(relacionesactuales.getTextoOpciones().size() >0){
            tvOpcion1.setText(relacionesactuales.getTextoOpciones().get(0));
            bOpcion1.setText(relacionesactuales.getOpciones().get(0)+"");
            tvOpcion1.setVisibility(View.VISIBLE);
            bOpcion1.setVisibility(View.VISIBLE);
        }
        if(relacionesactuales.getTextoOpciones().size() > 1){
            tvOpcion2.setText(relacionesactuales.getTextoOpciones().get(1));
            bOpcion2.setText(relacionesactuales.getOpciones().get(1)+"");
            tvOpcion2.setVisibility(View.VISIBLE);
            bOpcion2.setVisibility(View.VISIBLE);
        }
        if (relacionesactuales.getTextoOpciones().size()>2){
            tvOpcion3.setText(relacionesactuales.getTextoOpciones().get(2));
            bOpcion3.setText(relacionesactuales.getOpciones().get(2)+"");
            tvOpcion3.setVisibility(View.VISIBLE);
            bOpcion3.setVisibility(View.VISIBLE);
        }
        if (relacionesactuales.getTextoOpciones().size()>3){
            tvOpcion4.setText(relacionesactuales.getTextoOpciones().get(3));
            bOpcion4.setText(relacionesactuales.getOpciones().get(3)+"");
            tvOpcion4.setVisibility(View.VISIBLE);
            bOpcion4.setVisibility(View.VISIBLE);
        }

    }*/

    public void iniciar(){

        // Usando el Bitmap no sólo podríamos tener una imagen desde drawable sino desde otro tipo de rutas (cámara, internet, etc)
        /*Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.vintorez);
        inventario.add(new Objeto("Vendas", "Úsalas para prevenir el sangrado", bm));
        inventario.add(new Objeto("Llave del león", "Una vieja y oxidada llave ornamentada con la cabeza de un león.", bm));
        inventario.add(new Objeto("Cizalla", "Muy útil para cortar vallas, cadenas y otras cosas metálicas.", bm));
        inventario.add(new Objeto("Salvoconducto", "Imprescindible para poder estar en el interior de la Zona de Exclusión.", bm));

*/
        String capitulo1 = getString(R.string.capitulo_1);
        capitulos.add(capitulo1);
        String capitulo2 = getString(R.string.capitulo_2);
        capitulos.add(capitulo2);
        String capitulo3 = getString(R.string.capitulo_3);
        capitulos.add(capitulo3);
        String capitulo4 = getString(R.string.capitulo_4);
        capitulos.add(capitulo4);
        String capitulo5 = getString(R.string.capitulo_5);
        capitulos.add(capitulo5);

        //Cap1
        ArrayList<String> textoEleccion1  = new ArrayList<>();
        textoEleccion1.add(getString(R.string.aceptar_invitación));
        textoEleccion1.add(getString(R.string.rechazar_invitación));
        textoEleccion1.add(getString(R.string.ignorar_gorrión));
        ArrayList<Integer> botonEleccion1  = new ArrayList<>();
        botonEleccion1.add(2)  ;
        botonEleccion1.add(4)  ;
        botonEleccion1.add(5)  ;
        ArrayList<Objeto> objetosEncontradosCap1 = new ArrayList<>();

        Capitulo r = new Capitulo(1,textoEleccion1, botonEleccion1, objetosEncontradosCap1);
        matrizpasos.put(1,r);

        //Cap 2
        ArrayList<String> textoEleccion2  = new ArrayList<>();
        textoEleccion2.add("Si coges el hacha, ve al capítulo ");
        textoEleccion2.add("Si rehusas, ve al capítulo ");
        ArrayList<Integer> botonEleccion2  = new ArrayList<>();
        botonEleccion2.add(5)  ;
        botonEleccion2.add(3)  ;
        ArrayList<Objeto> objetosEncontradosCap2 = new ArrayList<>();
        Bitmap imagenObjetoGasmask = BitmapFactory.decodeResource(getResources(), R.drawable.gas_mask);
        Objeto mascaraDeGas = new Objeto(getString(R.string.name_gasmask), getString(R.string.description_gasmask), imagenObjetoGasmask);
        objetosEncontradosCap2.add(mascaraDeGas);

        Capitulo r2 = new Capitulo(2,textoEleccion2, botonEleccion2, objetosEncontradosCap2);
        matrizpasos.put(2,r2);

        //Cap 3
        ArrayList<String> textoEleccion3  = new ArrayList<>();
        textoEleccion3.add("Si quieres hablar con el tabernero, ve al capítulo ");
        textoEleccion3.add("Si quieres espiar al ladrón, ve al capítulo ");
        textoEleccion3.add("Si quieres beber más, ve al capítulo ");
        textoEleccion3.add("Si quieres irte de la taberna, ve al capítulo ");
        ArrayList<Integer> botonEleccion3  = new ArrayList<>();
        botonEleccion3.add(1)  ;
        botonEleccion3.add(2)  ;
        botonEleccion3.add(4)  ;
        botonEleccion3.add(5)  ;
        ArrayList<Objeto> objetosEncontradosCap3 = new ArrayList<>();

        Capitulo r3 = new Capitulo(3,textoEleccion3, botonEleccion3, objetosEncontradosCap3);
        matrizpasos.put(3,r3);

        //Cap 4
        ArrayList<String> textoEleccion4  = new ArrayList<>();
        textoEleccion4.add("Si te bañas en el lago, ve al capítulo ");
        textoEleccion4.add("Si prefieres ir a las montañas, ve al capítulo ");
        ArrayList<Integer> botonEleccion4  = new ArrayList<>();
        botonEleccion4.add(2)  ;
        botonEleccion4.add(5)  ;
        ArrayList<Objeto> objetosEncontradosCap4 = new ArrayList<>();
        Bitmap imagenVintorez = BitmapFactory.decodeResource(getResources(), R.drawable.vintorez);
        Objeto vintorez = new Objeto(getString(R.string.name_vintorez), getString(R.string.description_vintorez), imagenVintorez);
        objetosEncontradosCap4.add(vintorez);
        Capitulo r4 = new Capitulo(4,textoEleccion4, botonEleccion4, objetosEncontradosCap4);
        matrizpasos.put(4,r4);

        //Cap 5
        ArrayList<String> textoEleccion5  = new ArrayList<>();
        textoEleccion5.add("Si vas a la ciudad de Alderaan, ve al capítulo ");
        textoEleccion5.add("Si quieres conocer al sabio, ve al capítulo ");
        textoEleccion5.add("Si quieres entrar en la tienda, ve al capítulo ");
        ArrayList<Integer> botonEleccion5  = new ArrayList<>();
        botonEleccion5.add(2)  ;
        botonEleccion5.add(4)  ;
        botonEleccion5.add(1)  ;
        ArrayList<Objeto> objetosEncontradosCap5 = new ArrayList<>();
        Bitmap imagenObjetoAmmo5x45 = BitmapFactory.decodeResource(getResources(), R.drawable.ammo);
        Objeto ammo5x45 = new Objeto(getString(R.string.name_ammo5x45), getString(R.string.description_ammo5x45), imagenObjetoAmmo5x45);
        objetosEncontradosCap5.add(ammo5x45);

        Capitulo r5 = new Capitulo(5,textoEleccion5, botonEleccion5,objetosEncontradosCap5);
        matrizpasos.put(5,r5);

    }

    public void botonPulsado(View view) {
        Button b = (Button) view;

        String textoEnElBotonPulsado = b.getText().toString();
        capituloActual = Integer.parseInt(textoEnElBotonPulsado);
        pintarPagina();
    }

    // onCreateOptionsMenu se invoca cuando se construye la actividad y se crea el menu. @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Aqui se “infla” el layoput qye se desee en el menu de esta actividad
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    // onOptionsItemSelected se invoca cuando se selecciona alguna de las opciones del menu y recibe como parametro un objeto MenuItem (el pulsado)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Con el objeto MenuItem y su metodo getItemId() podemos saber el id del view del menu pulsa
        int id = item.getItemId();
        switch (id) {
            case (R.id.itemVerInventario):
                mostrarInventario();
                break;
            case (R.id.itemAjustes):
/*
                mostrarAjustes();
*/
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void mostrarInventario(){
        Intent intent = new Intent(MainActivity.this, InventarioActivity.class);

        Gson unGson = new Gson();
        String inventarioEnFormatoGSON = unGson.toJson(inventario);
        intent.putExtra("inventarioEnTexto", inventarioEnFormatoGSON);
        startActivity(intent);
    }

    /*public void mostrarAjustes(){
        Intent intent = new Intent(MainActivity.this, AjustesActivity.class);

        Gson unGson = new Gson();
        String inventarioEnFormatoGSON = unGson.toJson(inventario);
        intent.putExtra("inventarioEnTexto", inventarioEnFormatoGSON);
        startActivity(intent);
    }*/



}

