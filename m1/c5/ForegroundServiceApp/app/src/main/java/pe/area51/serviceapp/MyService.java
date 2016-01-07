package pe.area51.serviceapp;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.NotificationCompat;
import android.widget.Toast;

public class MyService extends Service {

    private final static int FOREGROUND_ID = 1;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        showText("onCreate()");
        super.onCreate();
        startInForegroundMode();
    }

    private void startInForegroundMode() {
        //Para iniciar nuestro servicio en modo Foreground, necesitamos primero una notificación.
        final NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this);
        notificationBuilder
                .setContentTitle("My Service")
                .setContentText("The service has been started!")
                .setSmallIcon(R.mipmap.ic_launcher);
        /*
        Si iniciamos el servicio en modo Foreground, entonces el sistema le dará más prioridad a nuestro servicio,
        Siendo mucho más improbable que lo mate (por problemas de carga de batería, falta de memoria RAM, etc.).
        Este modo de inicio no es obligatorio. Cabe resaltar que este modo de inicio muestra una notificación en el sistema.
         */
        startForeground(FOREGROUND_ID, notificationBuilder.build());
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
