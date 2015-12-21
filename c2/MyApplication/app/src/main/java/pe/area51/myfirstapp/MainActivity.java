package pe.area51.myfirstapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_NAME = "name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Establecemos el layout "activity_main" como el View de nuestro Activity.
        setContentView(R.layout.activity_main);
        /*
        En el View establecido, buscamos el View identificado como "button_name"
        y establecemos su objeto OnClickListener con las rutinas que deseamos
        se ejecuten cuando hagamos "click" en él.
        (recordar que un View puede contener a su vez otros View).
        */
        findViewById(R.id.button_name).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                Obtenemos la referencia del EditText identificado como "edittext_name" y
                convertimos el objeto View devuelto a EditText, ya que el método "getText()"
                que necesitamos se encuentra en la clase EditText y no en la clase View.
                Guardamos la referencia en la variable "nameEditText".
                 */
                final EditText nameEditText = (EditText) findViewById(R.id.edittext_name);
                //Obtenemos el texto del EditText y lo guardamos en la variable "name".
                final String name = nameEditText.getText().toString();
                /*
                Iniciamos el Activity "HelloActivity". Para esto creamos un objeto Intent donde
                indicamos el Activity a ejecutarse, además le pasamos el nombre como valor extra.
                Este tipo de Intent se llama "explicit intent", ya que indicamos de forma explícita
                el componente a se ejecutado.
                 */
                /*
                Más información sobre intents explícitos e implícitos:
                http://developer.android.com/intl/es/guide/components/intents-filters.html#Building
                 */
                startActivity(
                        new Intent(MainActivity.this, HelloActivity.class)
                                .putExtra(EXTRA_NAME, name)
                );
            }
        });
    }

}
