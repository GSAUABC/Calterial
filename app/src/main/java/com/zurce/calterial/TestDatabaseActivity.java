package com.zurce.calterial;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ListActivity;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.zurce.calterial.database.Result;
import com.zurce.calterial.database.ResultsDataSource;

import java.util.List;
import java.util.Random;

public class TestDatabaseActivity extends Activity {
    private ResultsDataSource datasource;
    ArrayAdapter<Result> adapter;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_list);




        lv = (ListView) this.findViewById(R.id.listView);


        datasource = new ResultsDataSource(this);
        datasource.open();

        List<Result> values = datasource.getAllComments();

        // use the SimpleCursorAdapter to show the
        // elements in a ListView
        adapter = new ArrayAdapter<Result>(this,
                android.R.layout.simple_list_item_1, values);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Object item = lv.getAdapter().getItem(position);
                //   Toast.makeText(this, "message: "+item.toString(), Toast.LENGTH_SHORT).show();
                Intent returnIntent = new Intent();
                returnIntent.putExtra("result",item.toString());
                setResult(Activity.RESULT_OK, returnIntent);
                if(MainActivity.mTwoPane == false)
                    finish();

            }
        });
    }

    // Will be called via the onClick attribute
    // of the buttons in main.xml
    public void onClick(View view) {
        @SuppressWarnings("unchecked")
        ArrayAdapter<Result> adapter = (ArrayAdapter<Result>) lv.getAdapter();
        Result comment = null;
        switch (view.getId()) {

        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        datasource.open();
        super.onResume();
    }

    @Override
    public void onPause() {
        datasource.close();
        super.onPause();
    }



    public void update(){
        Log.i("Yay","Actualizo");

    }


    public void onListItemClick(ListView l, View v, int position, long id) {
        Object item = lv.getAdapter().getItem(position);
        Intent returnIntent = new Intent();
        returnIntent.putExtra("result",item.toString());
       setResult(Activity.RESULT_OK, returnIntent);
        if(MainActivity.mTwoPane == false)
            finish();
    }

}
