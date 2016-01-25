package pe.area51.sensortest;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private TextView sensorStatusTextView;
    private boolean isAccelerometerStarted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorStatusTextView = (TextView) findViewById(R.id.textview_accelerometer_status);
        findViewById(R.id.button_toggle_accelerometer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isAccelerometerStarted) {
                    stopAccelerometer();
                } else {
                    startAccelerometer();
                }
            }
        });
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        isAccelerometerStarted = false;
    }

    private void startAccelerometer() {
        final Sensor accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_GAME);
        isAccelerometerStarted = true;
    }

    private void stopAccelerometer() {
        sensorManager.unregisterListener(this);
        isAccelerometerStarted = false;
    }

    private void showSensorEvent(final SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            final float xAxisValue = sensorEvent.values[0];
            final float yAxisValue = sensorEvent.values[1];
            final float zAxisValue = sensorEvent.values[2];
            final String xAxisString = getString(R.string.x_axis, String.valueOf(xAxisValue));
            final String yAxisString = getString(R.string.y_axis, String.valueOf(yAxisValue));
            final String zAxisString = getString(R.string.z_axis, String.valueOf(zAxisValue));
            final String message = xAxisString + "\n" + yAxisString + "\n" + zAxisString;
            sensorStatusTextView.setText(message);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        showSensorEvent(event);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onPause() {
        super.onPause();
        stopAccelerometer();
    }

}
