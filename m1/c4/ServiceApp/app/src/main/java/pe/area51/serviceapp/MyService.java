package pe.area51.serviceapp;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

public class MyService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        showText("onCreate()");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        showText("onStartCommand()");
        /*
        Este flag es muy importante:
        -START_NOT_STICKY: Si el sistema mata al servicio, este no se reiniciará.
        -START_STICKY: Si el sistema mata al servicio, este se reiniciará pero no se reenviará el último Intent que se envió al servicio.
        -START_REDELIVER_INTENT: Si el sistema mata al servicio, este se reiniciará y se reenviará el último Intent que recibió el servicio.
         */
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        showText("onDestroy()");
        super.onDestroy();
    }

    private void showText(final String text) {
        Toast.makeText(MyService.this, text, Toast.LENGTH_SHORT).show();
    }
}
