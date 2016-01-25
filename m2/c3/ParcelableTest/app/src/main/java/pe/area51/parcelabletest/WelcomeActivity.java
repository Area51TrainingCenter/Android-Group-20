package pe.area51.parcelabletest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_layout);
        final Person person = getIntent().getParcelableExtra(MainActivity.EXTRA_PERSON);
        final TextView infoTextView = (TextView) findViewById(R.id.textview_info);
        infoTextView.setText(person.toString());
    }
}
