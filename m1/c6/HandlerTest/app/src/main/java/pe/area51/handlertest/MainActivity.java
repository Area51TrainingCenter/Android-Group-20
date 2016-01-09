package pe.area51.handlertest;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button_execute_task)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        runLongTaskOnAsyncTask();
                    }
                });
    }

    private void runLongTaskOnAsyncTask() {
        new AsyncTask<Boolean, Integer>() {

            private ProgressDialog progressDialog;

            @Override
            protected void onPreExecute() {
                progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setIndeterminate(false);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressDialog.setMax(100);
                progressDialog.setProgress(0);
                progressDialog.setCancelable(false);
                progressDialog.setTitle(R.string.task_progress_title);
                progressDialog.setMessage(getString(R.string.task_progress_message));
                progressDialog.show();
            }

            @Override
            protected Boolean doInBackground() {
                longTask();
                postProgress(10);
                longTask();
                postProgress(20);
                longTask();
                postProgress(30);
                longTask();
                postProgress(40);
                longTask();
                postProgress(50);
                longTask();
                postProgress(60);
                longTask();
                postProgress(70);
                longTask();
                postProgress(80);
                longTask();
                postProgress(90);
                longTask();
                postProgress(100);
                return true;
            }

            @Override
            protected void onPostExecute(Boolean aBoolean) {
                progressDialog.dismiss();
            }

            @Override
            protected void onProgressUpdate(Integer integer) {
                progressDialog.setProgress(integer);
            }

        }.execute();
    }

    private void longTask() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}