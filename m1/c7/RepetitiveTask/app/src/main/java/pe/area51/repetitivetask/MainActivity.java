package pe.area51.repetitivetask;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int FREQUENCY_IN_MILLIS = 1;

    private Button toggleCounterButton;
    private TextView counterInfoTextView;
    private long counter;
    private RepetitiveTask repetitiveTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toggleCounterButton = (Button) findViewById(R.id.button_toggle_counter);
        counterInfoTextView = (TextView) findViewById(R.id.textview_counter);
        counter = 0;
        repetitiveTask = new RepetitiveTask(new Runnable() {
            @Override
            public void run() {
                counter++;
                showCount();
            }
        }, FREQUENCY_IN_MILLIS);
        toggleCounterButton.setText(R.string.start_counter);
        toggleCounterButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (repetitiveTask.isRunning()) {
            repetitiveTask.stop();
            toggleCounterButton.setText(R.string.start_counter);
        } else {
            repetitiveTask.start();
            toggleCounterButton.setText(R.string.stop_counter);
        }
    }

    private void showCount() {
        counterInfoTextView.setText(getString(R.string.count, counter));
    }
}