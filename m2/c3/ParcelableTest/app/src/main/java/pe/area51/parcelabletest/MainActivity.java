package pe.area51.parcelabletest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_PERSON = "person";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button_show_person).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Person person = new Person("Pedro", "García", 24, "123", "Masculino", new Location("Perú", "Lima", "Avenida 123"));
                startActivity(new Intent(MainActivity.this, WelcomeActivity.class).putExtra(EXTRA_PERSON, person));
            }
        });
    }
}
