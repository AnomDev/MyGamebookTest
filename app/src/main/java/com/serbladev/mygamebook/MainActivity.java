package com.serbladev.mygamebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    ArrayList<Objeto> inventario = new ArrayList<>();


    TextView tvNumPagina;
    TextView tvPagina;


    TextView tvOpcion1;
    Button bOpcion1;
    TextView tvOpcion2;
    Button bOpcion2;
    TextView tvOpcion3;
    Button bOpcion3;
    TextView tvOpcion4;
    Button bOpcion4;
    TextView[]  textosOpciones = new TextView[4];
    Button[] botonOpciones = new Button[4];

    int paginaactual = 1;
    HashMap< Integer  , Capitulo> matrizpasos = new HashMap<>();
    ArrayList<String> capitulos = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvNumPagina = findViewById(R.id.tvNumPagina);
        tvPagina = findViewById(R.id.tvPagina);
        tvPagina.setMovementMethod(new ScrollingMovementMethod());

        tvOpcion1 = findViewById(R.id.tvOpcion1);
        bOpcion1 = findViewById(R.id.bOpcion1);

        tvOpcion2 = findViewById(R.id.tvOpcion2);
        bOpcion2 = findViewById(R.id.bOpcion2);

        tvOpcion3 = findViewById(R.id.tvOpcion3);
        bOpcion3 = findViewById(R.id.bOpcion3);

        tvOpcion4 = findViewById(R.id.tvOpcion4);
        bOpcion4 = findViewById(R.id.bOpcion4);


        //Array de los textos para las opciones.
        textosOpciones[0]= tvOpcion1;
        textosOpciones[1]= tvOpcion2;
        textosOpciones[2]= tvOpcion3;
        textosOpciones[3]= tvOpcion4;

        //Array con todos los botones posibles.
        botonOpciones[0]= bOpcion1;
        botonOpciones[1]= bOpcion2;
        botonOpciones[2]= bOpcion3;
        botonOpciones[3]= bOpcion4;


        iniciar();

        pintarCapitulo();

       /* bOpcion1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String loquehayenelboton = bOpcion1.getText().toString();
                paginaactual = Integer.parseInt(loquehayenelboton);

                pintarCapitulo();
            }
        });*/




    }




    public void pintarCapitulo(){
        tvPagina.setText(capitulos.get(paginaactual-1));
        pintarOpciones();

    }

    public void pintarOpciones(){
        Capitulo capituloActual = matrizpasos.get(paginaactual);
        for (int i=0 ; i<textosOpciones.length ; i++){
            textosOpciones[i].setVisibility(View.INVISIBLE);
            botonOpciones[i].setVisibility(View.INVISIBLE);
        }
        for (int i=0 ; i<capituloActual.getTextoOpciones().size(); i++){
            textosOpciones[i].setText(capituloActual.getTextoOpciones().get(i));
            botonOpciones[i].setText(capituloActual.getOpciones().get(i)+"");
            textosOpciones[i].setVisibility(View.VISIBLE);
            botonOpciones[i].setVisibility(View.VISIBLE);
        }

        if(capituloActual.getObjetosEncontrados().size()>0){
            // TODO  CUIDADO CON NO REPETIR !!!
            for(int i=0; i<capituloActual.getObjetosEncontrados().size(); i++){
                inventario.add(capituloActual.getObjetosEncontrados().get(i));
            }
            Snackbar.make(tvPagina, "HAS PILLADO ALGO !!!!!!", Snackbar.LENGTH_LONG).show();
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

        String texto1 = "CAP 1: Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse eget arcu tempus, tempor augue et, maximus urna. Sed volutpat euismod nisi non varius. Suspendisse eu ante nec nisl mollis lacinia vel quis magna. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse eget arcu tempus, tempor augue et, maximus urna. Sed volutpat euismod nisi non varius. Suspendisse eu ante nec nisl mollis lacinia vel quis magna. Aenean viverra lobortis hendrerit. Fusce volutpat ut tellus ut iaculis. Fusce in enim ac augue pharetra maximus ac sit amet ex. Mauris condimentum nisi et nulla pellentesque consectetur. Nullam ultricies, neque non efficitur tristique, neque mi egestas ipsum, eget dapibus lacus nunc vitae nunc. Maecenas congue, mauris vitae facilisis sagittis, odio nisi tempus magna, in scelerisque magna libero non erat. Nunc semper luctus posuere. Morbi consequat odio id sagittis suscipit. Integer eu consectetur dolor. c Maecenas eu molestie dui, quis vulputate urna. Morbi euismod fringilla mauris in scelerisque. Vestibulum aliquet, justo vel semper molestie, mauris velit cursus mauris, id varius orci diam eu mauris. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus quis ornare turpis. Nullam consectetur nulla et ligula rhoncus, non vestibulum lectus consequat. Vestibulum finibus velit erat, sed cursus odio finibus in. Nunc et nulla commodo, sollicitudin odio eget, blandit augue. Nullam massa tellus, scelerisque sed congue at, varius non purus. Curabitur dolor erat, lobortis sed odio ac, laoreet mattis mi. Ut at arcu diam. Vestibulum vitae pellentesque mi. Curabitur at tristique purus. Nulla accumsan, velit nec mollis molestie, nibh sem maximus ipsum, sit amet pharetra urna nulla semper nunc.";
        capitulos.add(texto1);
        String texto2 = "CAP 2: Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse eget arcu tempus, tempor augue et, maximus urna. Sed volutpat euismod nisi non varius. Suspendisse eu ante nec nisl mollis lacinia vel quis magna. Aenean viverra lobortis hendrerit. Fusce volutpat ut tellus ut iaculis. Fusce in enim ac augue pharetra maximus ac sit amet ex. Mauris condimentum nisi et nulla pellentesque consectetur. Nullam ultricieLorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse eget arcu tempus, tempor augue et, maximus urna. Sed volutpat euismod nisi non varius. Suspendisse eu ante nec nisl mollis lacinia vel quis magna. s, neque non efficitur tristique, neque mi egestas ipsum, eget dapibus lacus nunc vitae nunc. Maecenas congue, mauris vitae facilisis sagittis, odio nisi tempus magna, in scelerisque magna libero non erat. Nunc semper luctus posuere. Morbi consequat odio id sagittis suscipit. Integer eu consectetur dolor. c Maecenas eu molestie dui, quis vulputate urna. Morbi euismod fringilla mauris in scelerisque. Vestibulum aliquet, justo vel semper molestie, mauris velit cursus mauris, id varius orci diam eu mauris. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus quis ornare turpis. Nullam consectetur nulla et ligula rhoncus, non vestibulum lectus consequat. Vestibulum finibus velit erat, sed cursus odio finibus in. Nunc et nulla commodo, sollicitudin odio eget, blandit augue. Nullam massa tellus, scelerisque sed congue at, varius non purus. Curabitur dolor erat, lobortis sed odio ac, laoreet mattis mi. Ut at arcu diam. Vestibulum vitae pellentesque mi. Curabitur at tristique purus. Nulla accumsan, velit nec mollis molestie, nibh sem maximus ipsum, sit amet pharetra urna nulla semper nunc.";
        capitulos.add(texto2);
        String texto3 = "CAP 3: Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse eget arcu tempus, tempor augue et, maximus urna. Sed volutpat euismod nisi non varius. Suspendisse eu ante nec nisl mollis lacinia vel quis magna. Aenean viverra lobortis hendrerit. Fusce volutpat ut tellus ut iaculis. Fusce in enim ac augue pharetra maximus ac sit amet ex. Mauris condimentum nisi et nulla pellentesque consectetur. Nullam ultricies, neque non efficitur tristique, neque mi egestas ipsum, eget dapibus lacus nunc vitae nunc. Maecenas congue, mauris vitae facilisis sagittis, odio nisi tempus magna, in scelerisque magna libero nLorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse eget arcu tempus, tempor augue et, maximus urna. Sed volutpat euismod nisi non varius. Suspendisse eu ante nec nisl mollis lacinia vel quis magna. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse eget arcu tempus, tempor augue et, maximus urna. Sed volutpat euismod nisi non varius. Suspendisse eu ante nec nisl mollis lacinia vel quis magna. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse eget arcu tempus, tempor augue et, maximus urna. Sed volutpat euismod nisi non varius. Suspendisse eu ante nec nisl mollis lacinia vel quis magna. on erat. Nunc semper luctus posuere. Morbi consequat odio id sagittis suscipit. Integer eu consectetur dolor. c Maecenas eu molestie dui, quis vulputate urna. Morbi euismod fringilla mauris in scelerisque. Vestibulum aliquet, justo vel semper molestie, mauris velit cursus mauris, id varius orci diam eu mauris. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus quis ornare turpis. Nullam consectetur nulla et ligula rhoncus, non vestibulum lectus consequat. Vestibulum finibus velit erat, sed cursus odio finibus in. Nunc et nulla commodo, sollicitudin odio eget, blandit augue. Nullam massa tellus, scelerisque sed congue at, varius non purus. Curabitur dolor erat, lobortis sed odio ac, laoreet mattis mi. Ut at arcu diam. Vestibulum vitae pellentesque mi. Curabitur at tristique purus. Nulla accumsan, velit nec mollis molestie, nibh sem maximus ipsum, sit amet pharetra urna nulla semper nunc.";
        capitulos.add(texto3);
        String texto4 = "CAP 4: Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse eget arcu tempus, tempor augue et, maximus urna. Sed volutpat euismod nisi non varius. Suspendisse eu ante nec nisl mollis lacinia vel quis magna. Aenean viverra lobortis hendrerit. Fusce volutpat ut tellus ut iaculis. Fusce in enim ac augue pharetra maximus ac sit amet ex. Mauris condimentum nisi et nulla pellentesque consectetur. Nullam ultricies, neque non efficitur tristique, neque mi egestas ipsum, eget dapibus lacus nunc vitae nunc. Maecenas congue, mauris vitae facilisis sagittis, odio nisi tempus magna, in scelerisque magna libero non erat. Nunc semper luctus posuere. Morbi consequat odio id sagittis suscipit. Integer eu consectetur dolor. c Maecenas eu molestie dui, quis vulputate urna. Morbi euismod fringilla mauris in scelerisque. Vestibulum aliquet, justo vel semper molestie, mauris velit cursus mauris, id varius orci diam eu mauris. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus quis ornare turpis. Nullam consectetur nulla et ligula rhoncus, non vestibulum lectus consequat. Vestibulum finibus velit erat, sed cursus odio finibus in. Nunc et nulla commodo, sollicitudin odio eget, blandit augue. Nullam massa tellus, sceleLorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse eget arcu tempus, tempor augue et, maximus urna. Sed volutpat euismod nisi non varius. Suspendisse eu ante nec nisl mollis lacinia vel quis magna. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse eget arcu tempus, tempor augue et, maximus urna. Sed volutpat euismod nisi non varius. Suspendisse eu ante nec nisl mollis lacinia vel quis magna. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse eget arcu tempus, tempor augue et, maximus urna. Sed volutpat euismod nisi non varius. Suspendisse eu ante nec nisl mollis lacinia vel quis magna. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse eget arcu tempus, tempor augue et, maximus urna. Sed volutpat euismod nisi non varius. Suspendisse eu ante nec nisl mollis lacinia vel quis magna. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse eget arcu tempus, tempor augue et, maximus urna. Sed volutpat euismod nisi non varius. Suspendisse eu ante nec nisl mollis lacinia vel quis magna. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse eget arcu tempus, tempor augue et, maximus urna. Sed volutpat euismod nisi non varius. Suspendisse eu ante nec nisl mollis lacinia vel quis magna. risque sed congue at, varius non purus. Curabitur dolor erat, lobortis sed odio ac, laoreet mattis mi. Ut at arcu diam. Vestibulum vitae pellentesque mi. Curabitur at tristique purus. Nulla accumsan, velit nec mollis molestie, nibh sem maximus ipsum, sit amet pharetra urna nulla semper nunc.";
        capitulos.add(texto4);
        String texto5 = "CAP 5: Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse eget arcu tempus, tempor augue et, maximus urna. Sed volutpat euismod nisi non varius. Suspendisse eu ante nec nisl mollis lacinia vel quis magna. Aenean viverra lobortis hendrerit. Fusce volutpat ut tellus ut iaculis. Fusce in enim ac augue pharetra maximus ac sit amet ex. Mauris condimentum nisi et nulla pellentesque consectetur. Nullam ultricies, neque non efficitur tristique, neque mi egestas ipsum, eget dapibus lacus nunc vitae nunc. Maecenas congue, mauris vitae facilisis sagittis, odio nisi tempus magna, in scelerisque magna libero non erat. Nunc semper luctus posuere. Morbi consequat odio id sagittis suscipit. Integer eu consectetur dolor. c Maecenas eu molestie dui, quis vulputate urna. Morbi euismod fringilla mauris in scelerisque. Vestibulum aliquet, justo vel semper molestie, mauris velit cursus mauris, id varius orci diam eu mauris. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus quis ornare turpis. Nullam consectetur nulla et ligula rhoncus, non vestibulum lectus consequat. Vestibulum finibus velit erat, sed cursus odio finibus in. Nunc et nulla commodo, sollicitudin odio eget, blandit augue. Nullam massa tellus, scelerisque sed congue at, variusLorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse eget arcu tempus, tempor augue et, maximus urna. Sed volutpat euismod nisi non varius. Suspendisse eu ante nec nisl mollis lacinia vel quis magna. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse eget arcu tempus, tempor augue et, maximus urna. Sed volutpat euismod nisi non varius. Suspendisse eu ante nec nisl mollis lacinia vel quis magna. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse eget arcu tempus, tempor augue et, maximus urna. Sed volutpat euismod nisi non varius. Suspendisse eu ante nec nisl mollis lacinia vel quis magna. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse eget arcu tempus, tempor augue et, maximus urna. Sed volutpat euismod nisi non varius. Suspendisse eu ante nec nisl mollis lacinia vel quis magna. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse eget arcu tempus, tempor augue et, maximus urna. Sed volutpat euismod nisi non varius. Suspendisse eu ante nec nisl mollis lacinia vel quis magna. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse eget arcu tempus, tempor augue et, maximus urna. Sed volutpat euismod nisi non varius. Suspendisse eu ante nec nisl mollis lacinia vel quis magna. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse eget arcu tempus, tempor augue et, maximus urna. Sed volutpat euismod nisi non varius. Suspendisse eu ante nec nisl mollis lacinia vel quis magna. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse eget arcu tempus, tempor augue et, maximus urna. Sed volutpat euismod nisi non varius. Suspendisse eu ante nec nisl mollis lacinia vel quis magna. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse eget arcu tempus, tempor augue et, maximus urna. Sed volutpat euismod nisi non varius. Suspendisse eu ante nec nisl mollis lacinia vel quis magna. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse eget arcu tempus, tempor augue et, maximus urna. Sed volutpat euismod nisi non varius. Suspendisse eu ante nec nisl mollis lacinia vel quis magna. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse eget arcu tempus, tempor augue et, maximus urna. Sed volutpat euismod nisi non varius. Suspendisse eu ante nec nisl mollis lacinia vel quis magna. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse eget arcu tempus, tempor augue et, maximus urna. Sed volutpat euismod nisi non varius. Suspendisse eu ante nec nisl mollis lacinia vel quis magna. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse eget arcu tempus, tempor augue et, maximus urna. Sed volutpat euismod nisi non varius. Suspendisse eu ante nec nisl mollis lacinia vel quis magna. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse eget arcu tempus, tempor augue et, maximus urna. Sed volutpat euismod nisi non varius. Suspendisse eu ante nec nisl mollis lacinia vel quis magna.  non purus. Curabitur dolor erat, lobortis sed odio ac, laoreet mattis mi. Ut at arcu diam. Vestibulum vitae pellentesque mi. Curabitur at tristique purus. Nulla accumsan, velit nec mollis molestie, nibh sem maximus ipsum, sit amet pharetra urna nulla semper nunc.";
        capitulos.add(texto5);

        //Cap1
        ArrayList<String> textosopciones  = new ArrayList<>();
        textosopciones.add("Si quiere matar al oso... vaya a ");
        textosopciones.add("Si quiere huir... vaya a ");
        textosopciones.add("Si quiere mirar... vaya a ");
        ArrayList<Integer> opciones  = new ArrayList<>();
        opciones.add(2)  ;
        opciones.add(4)  ;
        opciones.add(5)  ;
        ArrayList<Objeto> objetos1 = new ArrayList<>();
        Objeto pollo = new Objeto("Pollo goma");
        objetos1.add(pollo);

        Capitulo r = new Capitulo(1,textosopciones, opciones, objetos1);
        matrizpasos.put(1,r);

        //Cap 2
        ArrayList<String> textosopciones2  = new ArrayList<>();
        textosopciones2.add("Si coges el hacha, ve al capítulo ");
        textosopciones2.add("Si rehusas, ve al capítulo ");
        ArrayList<Integer> opciones2  = new ArrayList<>();
        opciones2.add(5)  ;
        opciones2.add(1)  ;
        ArrayList<Objeto> objetos2 = new ArrayList<>();

        Capitulo r2 = new Capitulo(2,textosopciones2, opciones2, objetos2);
        matrizpasos.put(2,r2);

        //Cap 3
        ArrayList<String> textosopciones3  = new ArrayList<>();
        textosopciones3.add("Si quieres hablar con el tabernero, ve al capítulo ");
        textosopciones3.add("Si quieres espiar al ladrón, ve al capítulo ");
        textosopciones3.add("Si quieres beber más, ve al capítulo ");
        textosopciones3.add("Si quieres irte de la taberna, ve al capítulo ");
        ArrayList<Integer> opciones3  = new ArrayList<>();
        opciones3.add(1)  ;
        opciones3.add(4)  ;
        opciones3.add(5)  ;
        opciones3.add(2)  ;
        ArrayList<Objeto> objetos3 = new ArrayList<>();

        Capitulo r3 = new Capitulo(3,textosopciones3, opciones3, objetos3);
        matrizpasos.put(3,r3);

        //Cap 4
        ArrayList<String> textosopciones4  = new ArrayList<>();
        textosopciones4.add("Si te bañas en el lago, ve al capítulo ");
        textosopciones4.add("Si prefieres ir a las montañas, ve al capítulo ");
        ArrayList<Integer> opciones4  = new ArrayList<>();
        opciones4.add(2)  ;
        opciones4.add(5)  ;
        ArrayList<Objeto> objetos4 = new ArrayList<>();

        Capitulo r4 = new Capitulo(4,textosopciones4, opciones4, objetos4);
        matrizpasos.put(4,r4);

        //Cap 5
        ArrayList<String> textosopciones5  = new ArrayList<>();
        textosopciones5.add("Si vas a la ciudad de Alderaan, ve al capítulo ");
        textosopciones5.add("Si quieres conocer al sabio, ve al capítulo ");
        textosopciones5.add("Si quieres entrar en la tienda, ve al capítulo ");
        ArrayList<Integer> opciones5  = new ArrayList<>();
        opciones5.add(2)  ;
        opciones5.add(4)  ;
        opciones5.add(1)  ;
        ArrayList<Objeto> objetos5 = new ArrayList<>();
        Objeto o = new Objeto("Varita");
        objetos5.add(o);
        Capitulo r5 = new Capitulo(5,textosopciones5, opciones5,objetos5);
        matrizpasos.put(5,r5);

    }

    public void botonPulsado(View view) {
        Button b = (Button) view;

        String loquehayenelboton = b.getText().toString();
        paginaactual = Integer.parseInt(loquehayenelboton);
        pintarCapitulo();
    }

    // onCreateOptionsMenu se invoca cuando se construye la actividad y se crea el menu. @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Aqui se “infla” el layoput qye se desee en el menu de esta actividad
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    // onOptionsItemSelected se invoca cuando se selecciona alguna de las opciones del menu
// recibe como parametro un objeto MenuItem (el pulsado)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // con el objeto MenuItem y su metodo getItemId() podemos saber el id del view del menu pulsa
        int id = item.getItemId();
        switch (id) {
            case (R.id.itemVerInventario):

                mostrarInventario();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void mostrarInventario(){
        Intent intent = new Intent(MainActivity.this, InventarioActivity.class);
        startActivity(intent);
    }




}

