package pe.area51.locationapp;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements LocationListener {

    private LocationManager locationManager;

    private boolean isReceivingLocationUpdates;

    private TextView locationTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        locationTextView = (TextView) findViewById(R.id.textview_location);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        isReceivingLocationUpdates = false;
    }

    private void startLocationUpdates() {
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);
        locationManager.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER, 0, 0, this);
        isReceivingLocationUpdates = true;
    }

    private void stopLocationUpdates() {
        locationManager.removeUpdates(this);
        isReceivingLocationUpdates = false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_toggle_location:
                if (isReceivingLocationUpdates) {
                    stopLocationUpdates();
                } else {
                    startLocationUpdates();
                }
                return true;
            default:
                return false;
        }
    }

    private void showLocation(final Location location) {
        final String provider = getString(R.string.provider, location.getProvider());
        final String latitude = getString(R.string.latitude, String.valueOf(location.getLatitude()));
        final String longitude = getString(R.string.longitude, String.valueOf(location.getLongitude()));
        final String altitude = getString(R.string.altitude, String.valueOf(location.getAltitude()));
        final String accuracy = getString(R.string.accuracy, String.valueOf(location.getAccuracy()));
        final String timestamp = getString(R.string.timestamp, String.valueOf(location.getTime()));
        final String locationInfo = provider + "\n"
                + latitude + "\n"
                + longitude + "\n"
                + altitude + "\n"
                + accuracy + "\n"
                + timestamp;
        locationTextView.setText(locationInfo);
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopLocationUpdates();
    }

    @Override
    public void onLocationChanged(Location location) {
        showLocation(location);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
