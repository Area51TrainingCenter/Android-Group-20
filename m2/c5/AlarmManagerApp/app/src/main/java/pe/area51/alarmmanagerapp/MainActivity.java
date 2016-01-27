package pe.area51.alarmmanagerapp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private AlarmManager alarmManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        final EditText timeInSecondsEditText = (EditText) findViewById(R.id.edittext_time_in_seconds);
        findViewById(R.id.button_register_alarm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    final String userInputString = timeInSecondsEditText.getText().toString();
                    final int timeInSeconds = Integer.parseInt(userInputString);
                    registerAlarm(timeInSeconds);
                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, R.string.integer_numbers_only, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void registerAlarm(final int timeInSeconds) {
        final Intent operationIntent = new Intent(this, AlarmActivity.class);
        final PendingIntent operationPendingIntent = PendingIntent.getActivity(this, 0, operationIntent, PendingIntent.FLAG_ONE_SHOT);
        final long triggerAtMillis = SystemClock.elapsedRealtime() + timeInSeconds * 1000;
        final int alarmType = AlarmManager.ELAPSED_REALTIME_WAKEUP;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            alarmManager.setExact(alarmType, triggerAtMillis, operationPendingIntent);
        } else {
            alarmManager.set(alarmType, triggerAtMillis, operationPendingIntent);
        }
    }

}