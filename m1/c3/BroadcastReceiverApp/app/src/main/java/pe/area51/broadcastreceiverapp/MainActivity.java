package pe.area51.broadcastreceiverapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String ACTION_NEW_VISITOR = "pe.area51.broadcastreceiverapp.NEW_VISITOR";
    public static final String EXTRA_VISITOR_NAME = "pe.area51.broadcastreceiverapp.VISITOR_NAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button_broadcast_implicit_intent).setOnClickListener(this);
        findViewById(R.id.button_broadcast_explicit_intent).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_broadcast_implicit_intent:
                final String visitorName = ((EditText) findViewById(R.id.edittext_visitor_name)).getText().toString();
                sendBroadcast(new Intent(ACTION_NEW_VISITOR)
                        .putExtra(EXTRA_VISITOR_NAME, visitorName));
                break;
            case R.id.button_broadcast_explicit_intent:
                sendBroadcast(new Intent(MainActivity.this, MyBroadcastReceiver.class));
                break;
        }
    }
}
