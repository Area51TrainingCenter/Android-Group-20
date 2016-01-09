package pe.area51.handlertest;

import android.os.Handler;

/**
 * Class to create a three step task.
 *
 * @param <Result>   Class of the response object of the background method.
 * @param <Progress> Class of the object to report the task progress.
 */
public abstract class AsyncTask<Result, Progress> {

    private boolean isRunning;
    private Handler callerThreadHandler;

    //Se ejecuta en el hilo desde donde se creó el objeto.
    protected abstract void onPreExecute();

    //Se ejecuta en el hilo secundario (Worker thread).
    protected abstract Result doInBackground();

    //Se ejecuta en el hilo desde donde se creó el objeto.
    protected abstract void onPostExecute(final Result result);

    //Se ejecuta en el hilo desde donde se creó el objeto.
    protected void onProgressUpdate(final Progress progress) {

    }

    protected void postProgress(final Progress progress) {
        callerThreadHandler.post(new Runnable() {
            @Override
            public void run() {
                onProgressUpdate(progress);
            }
        });
    }

    /**
     * Creates the async task object.
     */
    public AsyncTask() {
        this.isRunning = false;
        this.callerThreadHandler = new Handler();
    }

    /**
     * Executes this async task.
     *
     * @return True if the async task was executed.
     */
    public boolean execute() {
        if (!isRunning) {
            isRunning = true;
            onPreExecute();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    final Result result = doInBackground();
                    callerThreadHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            onPostExecute(result);
                            isRunning = false;
                        }
                    });
                }
            }).start();
            return true;
        } else {
            return false;
        }
    }

    /**
     * @return True if this async task is running.
     */
    public boolean isRunning() {
        return isRunning;
    }

}
