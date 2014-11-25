package com.zurce.calterial;

import android.os.AsyncTask;
import android.view.View;
import android.widget.TextView;

public class updateFields extends AsyncTask<TextView, String, Void> {
    TextView textView;
    @Override
    protected void onProgressUpdate(String... values) {
        // TODO Auto-generated method stub

    }


    @Override
    protected void onPostExecute(Void result) {
        // TODO Auto-generated method stub
        textView.setText(textView.getTag().toString());

        super.onPostExecute(result);
    }

    @Override
    protected Void doInBackground(TextView... params) {

         textView = params[0];
        return null;
    }

    @Override
    protected void onPreExecute() {
        // TODO Auto-generated method stub

        super.onPreExecute();
    }


}