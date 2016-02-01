package pe.area51.sharedpreferencestest;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String LAST_VISITOR_SHARED_PREFERENCES_NAME = "lastVisitor";
    private static final String KEY_LAST_VISITOR = "lastVisitor";

    private TextView messageTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        messageTextView = (TextView) findViewById(R.id.textview_message);
        final EditText nameEditText = (EditText) findViewById(R.id.edittext_name);
        findViewById(R.id.button_register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = nameEditText.getText().toString();
                showWelcome(name);
                saveVisitor(name);
            }
        });
        verifyLastVisitorAndShow();
    }

    private void verifyLastVisitorAndShow() {
        final String lastVisitorName = loadLastVisitor();
        if (lastVisitorName == null) {
            showFirstVisitorMessage();
        } else {
            showLastVisitor(lastVisitorName);
        }
    }

    private SharedPreferences getLastVisitorSharedPreferences() {
        /*
        Si el archivo no existe, entonces se crea automáticamente.
         */
        return getSharedPreferences(LAST_VISITOR_SHARED_PREFERENCES_NAME, MODE_PRIVATE);
    }

    private boolean saveVisitor(final String name) {
        /*
        Utilizamos el objeto Editor para almacenar en RAM la inforamción a ser guardada.
        Una vez tengamos todos los valores a ser guardados, utilizamos el método "commit"
        o "apply" para guardar la informacion en la memoria secundaria. El método "commit"
        almacena los valores de forma síncrona, mientras que el método "apply" lo hace de
        forma asíncrona.
         */
        return getLastVisitorSharedPreferences()
                .edit()
                .putString(KEY_LAST_VISITOR, name)
                .commit();
    }

    private String loadLastVisitor() {
        return getLastVisitorSharedPreferences().getString(KEY_LAST_VISITOR, null);
    }

    private void showWelcome(final String name) {
        messageTextView.setText(getString(R.string.welcome_message, name));
    }

    private void showFirstVisitorMessage() {
        messageTextView.setText(R.string.first_visitor);
    }

    private void showLastVisitor(final String name) {
        messageTextView.setText(getString(R.string.last_visitor, name));
    }

}