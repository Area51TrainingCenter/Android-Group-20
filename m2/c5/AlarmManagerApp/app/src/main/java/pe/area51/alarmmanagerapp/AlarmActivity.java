package pe.area51.alarmmanagerapp;

import android.media.Ringtone;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class AlarmActivity extends AppCompatActivity {

    private Ringtone ringtone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        initRingtone();
    }

    @Override
    protected void onResume() {
        super.onResume();
        playRingtone();
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopRingtone();
    }

    private void initRingtone() {
        ringtone = RingtoneManager.getRingtone(this, RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM));
        if (ringtone == null) {
            Toast.makeText(AlarmActivity.this, R.string.no_ringtone, Toast.LENGTH_SHORT).show();
        }
    }

    private void playRingtone() {
        if (ringtone != null) {
            ringtone.play();
        }
    }

    private void stopRingtone() {
        if (ringtone != null) {
            ringtone.stop();
        }
    }
}
