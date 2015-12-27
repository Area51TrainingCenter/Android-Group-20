package pe.area51.broadcastreceiverapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        final String message;
        if (intent.getAction() != null) {
            switch (intent.getAction()) {
                case Intent.ACTION_AIRPLANE_MODE_CHANGED:
                    if (intent.getBooleanExtra("state", false)) {
                        message = context.getString(R.string.airplane_mode_on);
                    } else {
                        message = context.getString(R.string.airplane_mode_off);
                    }
                    break;
                case Intent.ACTION_WALLPAPER_CHANGED:
                    message = context.getString(R.string.wallpaper_changed);
                    break;
                case LocationManager.PROVIDERS_CHANGED_ACTION:
                    message = context.getString(R.string.location_provader_state_changed);
                    break;
                case MainActivity.ACTION_NEW_VISITOR:
                    final String name = intent.getStringExtra(MainActivity.EXTRA_VISITOR_NAME);
                    message = context.getString(R.string.action_new_visitor, name);
                    break;
                default:
                    message = context.getString(R.string.na);
                    break;
            }
        } else {
            message = context.getString(R.string.no_action);
        }
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

}
