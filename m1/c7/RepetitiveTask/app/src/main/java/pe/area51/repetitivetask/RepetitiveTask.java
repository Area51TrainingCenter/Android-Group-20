package pe.area51.repetitivetask;


import android.os.Handler;

public class RepetitiveTask {

    private boolean isRunning;
    private final long frequencyInMillis;
    private final Handler callerThreadHandler;
    private final Runnable repetitiveRunnable;

    public RepetitiveTask(final Runnable task, final long frequencyInMillis) {
        isRunning = false;
        this.frequencyInMillis = frequencyInMillis;
        callerThreadHandler = new Handler();
        this.repetitiveRunnable = new Runnable() {
            @Override
            public void run() {
                task.run();
                callerThreadHandler.postDelayed(this, frequencyInMillis);
            }
        };
    }

    public boolean start() {
        if (!isRunning) {
            repetitiveRunnable.run();
            isRunning = true;
            return true;
        } else {
            return false;
        }
    }

    public boolean stop() {
        if (isRunning) {
            callerThreadHandler.removeCallbacks(repetitiveRunnable);
            isRunning = false;
            return true;
        } else {
            return false;
        }
    }

    public boolean isRunning() {
        return isRunning;
    }

    public long getFrequencyInMillis() {
        return frequencyInMillis;
    }
}
