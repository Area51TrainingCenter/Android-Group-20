package pe.area51.repetitivetask;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int FREQUENCY_IN_MILLIS = 500;

    private Button toggleCounterButton;
    private TextView counterInfoTextView;
    private long handlerTicksCounter;
    private RepetitiveTask repetitiveTask;

    private long beginTime;
    private long elapsedSecondsCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toggleCounterButton = (Button) findViewById(R.id.button_toggle_counter);
        counterInfoTextView = (TextView) findViewById(R.id.textview_counter);
        handlerTicksCounter = 0;
        repetitiveTask = new RepetitiveTask(new Runnable() {
            @Override
            public void run() {
                handlerTicksCounter++;
                updateElapsedTime();
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
            handlerTicksCounter = 0;
            beginTime = SystemClock.elapsedRealtime();
            repetitiveTask.start();
            toggleCounterButton.setText(R.string.stop_counter);
        }
    }

    private void updateElapsedTime() {
        elapsedSecondsCounter = (SystemClock.elapsedRealtime() - beginTime) / 1000;
    }

    private void showCount() {
        final String handlerTicks = getString(R.string.handler_tick_count, handlerTicksCounter);
        final String elapsedTime = getString(R.string.elapsed_seconds, elapsedSecondsCounter);
        counterInfoTextView.setText(handlerTicks + "\n" + elapsedTime);
    }
}