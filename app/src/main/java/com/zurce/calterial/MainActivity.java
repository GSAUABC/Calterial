package com.zurce.calterial;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;


public class MainActivity extends Activity implements  communicate{
    public static boolean mTwoPane;
    public TestDatabaseActivity testData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testData = new TestDatabaseActivity();
        if(findViewById(R.id.activity_detail)!=null){
            mTwoPane = true;
            if(savedInstanceState ==null) {
              //  getFragmentManager().beginTransaction().add(R.id.container, new CalculatorFragment()).commit();
                getFragmentManager().beginTransaction().
                        replace(R.id.activity_detail, new TestFragment()).
                        commit();

            }
        }else{
            mTwoPane = false;
        }



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */

    public void sendData() {
        // TODO Auto-generated method stub
        testData.update();
        // From fragments we can call this method with the help of reference of communicate.
    }
}
