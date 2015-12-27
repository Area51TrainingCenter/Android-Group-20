package pe.area51.broadcastsenderapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button_broadcast_name).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText nameEditText = ((EditText) findViewById(R.id.edittext_name));
                final String name = nameEditText.getText().toString();
                broadcastName(name);
            }
        });
    }

    private void broadcastName(final String name) {
        sendBroadcast(new Intent("pe.area51.broadcastreceiverapp.NEW_VISITOR")
                .putExtra("pe.area51.broadcastreceiverapp.VISITOR_NAME", name));
    }

}
