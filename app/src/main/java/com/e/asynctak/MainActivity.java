package com.e.asynctak;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity" ;
    TextView textView;
    Button uploadTask;
    ProgressBar progressBarIndicator,progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView =findViewById(R.id.textView);
        uploadTask =findViewById(R.id.uploadData);
        progressBar =findViewById(R.id.progressBar);
        progressBarIndicator =findViewById(R.id.progressIndicator);


        textView.setText("");
        progressBar.setVisibility(View.INVISIBLE);
    }

    public void uploadTask(View view) {
//        UploadTask uploadTask =new UploadTask();
//        uploadTask.execute();

        new UploadTask().execute("this is the String");


    }
    class UploadTask extends AsyncTask<String,Integer,String>{



        // runs before doInBackground
        @Override
        protected void onPreExecute() {

            Log.i(TAG, "onPreExecute: "+Thread.currentThread().getName());
            textView.setText("uploading data.....");
            progressBar.setVisibility(View.VISIBLE);
            uploadTask.setEnabled(false);

        }

        @Override
        protected String doInBackground(String... strings) {

            Log.i(TAG, "doInBackground: String passed:"+ strings[0]);
            Log.i(TAG, "doInBackground: "+Thread.currentThread().getName());

            for (int i =0;i<10;i++){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                publishProgress(i);
            }
            return "finally task is complete";
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            progressBarIndicator.setProgress(values[0]+1);
        }

        @Override
        protected void onPostExecute(String string) {
            Log.i(TAG, "onPostExecute: "+Thread.currentThread().getName());
            textView.setText(string);
            progressBar.setVisibility(View.INVISIBLE);
            uploadTask.setEnabled(true);
        }
    }
}