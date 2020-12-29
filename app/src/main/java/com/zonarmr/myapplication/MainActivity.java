package com.zonarmr.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.zonarmr.checksu.CheckSU;

public class MainActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;
    private boolean returnRootStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new AsyncTaskCheckRoot().execute(new Void[0]);
    }

    private void dismissProgressDialog() {
        if (progressDialog!= null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    public class AsyncTaskCheckRoot extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected void onPreExecute() {
            //mLista.clear();
            progressDialog = new ProgressDialog(MainActivity.this);
            //progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setCancelable(false);
            progressDialog.setMessage("analyzing...");
            progressDialog.show();
        }

        // 2 minutos para despues ejecutar CheckRootStatus
        @Override
        protected Boolean doInBackground(Void... params) {
            try {
                Thread.sleep( 2 * 1000 );
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
            returnRootStatus = CheckSU.checkRootAccess(getApplicationContext());
            return returnRootStatus;
        }

        @Override
        protected void onPostExecute(Boolean isRooted) {
            //ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
            progressDialog.dismiss();
            if(isRooted){
                Toast.makeText(getApplicationContext(), "Device rooted", Toast.LENGTH_LONG).show();
            } else{
                Toast.makeText(getApplicationContext(), "Device No rooted", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onDestroy() {
        Log.i("MainActivity", "Destroying...");
        dismissProgressDialog();
        super.onDestroy();
    }
}

