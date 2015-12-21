package pe.area51.myfirstapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class HelloActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);
        /*
        Obtenemos el objeto Intent que inició este Activity y obtenemos de él
        su parámetro extra identificado por el valor de "MainActivity.EXTRA_NAME".
        Guardamos este valor en la variable "name".
         */
        final String name = getIntent().getStringExtra(MainActivity.EXTRA_NAME);
        final TextView welcomeEditText = (TextView) findViewById(R.id.textview_hello);
        welcomeEditText.setText(name);
    }
}
