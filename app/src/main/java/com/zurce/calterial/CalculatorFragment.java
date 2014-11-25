package com.zurce.calterial;

import android.app.Activity;
import android.app.Fragment;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zurce.calterial.database.ContentProviderDb;
import com.zurce.calterial.database.Result;
import com.zurce.calterial.database.ResultsDataSource;

import java.util.List;
import java.util.Random;

/**
 * Created by zurcezx on 11/20/14.
*/
public class CalculatorFragment extends Fragment {
    communicate cm;
    private ResultsDataSource datasource;
    List<Result> values;
    float  var1,varR,prem;
    String var2="";
    char operator=' ';

    TextView result2;
    TextView result1;

    Button b0,b1,b2,b3,b4,b5,b6,b7,b8,b9,bmas,bmenos,bpor,bentre,bclear,bback,bpot,bporce;
    RelativeLayout relativeLayout;


    public CalculatorFragment() {



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        new Thread(new Runnable() {
            public void run() {
                try {
                    initComponentes(rootView);
                } catch (Exception e) {
                    Toast.makeText(getActivity().getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                }
            }
        }).start();

        datasource = new ResultsDataSource(getActivity().getApplicationContext());
        datasource.open();

        cm = (communicate) getActivity();


        return rootView;


    }



    private  void initComponentes(View rootView){
        relativeLayout = (RelativeLayout) rootView.findViewById(R.id.relativeSwipes);

        relativeLayout.setOnTouchListener(new View.OnTouchListener() {

            int downX, upX;

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    downX = (int) event.getX();
                   // Log.i("event.getX()", " downX " + downX);
                    return true;
                }

                else if (event.getAction() == MotionEvent.ACTION_UP) {
                    upX = (int) event.getX();
                    //Log.i("event.getX()", " upX " + downX);
                    if (upX - downX > 100) {
                    Intent intent = new Intent(getActivity().getApplicationContext(), TestDatabaseActivity.class);
                        startActivityForResult(intent, 1);


                    }

                    else if (downX - upX > -100) {

                        Log.i("Movement:","Left");
                    }
                    return true;

                }
                return false;
            }
        });
        result1 = (TextView) rootView.findViewById(R.id.result1);
        result2 = (TextView) rootView.findViewById(R.id.result2);

        b0 = (Button) rootView.findViewById(R.id.zeroButton);
        b0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result2.setText(result2.getText()+"0");
                evaluate(0);
            }
        });

        b1 = (Button) rootView.findViewById(R.id.oneButton);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result2.setText(result2.getText()+"1");
                evaluate(1);

            }
        });

        b2 = (Button) rootView.findViewById(R.id.twoButton);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result2.setText(result2.getText()+"2");
                evaluate(2);

            }
        });

        b3 = (Button) rootView.findViewById(R.id.threeButton);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result2.setText(result2.getText()+"3");
                evaluate(3);

            }
        });

        b4 = (Button) rootView.findViewById(R.id.fourButton);
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result2.setText(result2.getText()+"4");
                evaluate(4);

            }
        });

        b5 = (Button) rootView.findViewById(R.id.fiveButton);
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result2.setText(result2.getText()+"5");
                evaluate(5);

            }
        });

        b6 = (Button) rootView.findViewById(R.id.sixButton);
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result2.setText(result2.getText()+"6");
                evaluate(6);

            }
        });

        b7 = (Button) rootView.findViewById(R.id.sevenButton);
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result2.setText(result2.getText()+"7");
                evaluate(7);

            }
        });

        b8 = (Button) rootView.findViewById(R.id.eightButton);
        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result2.setText(result2.getText()+"8");
                evaluate(8);

            }
        });

        b9 = (Button) rootView.findViewById(R.id.nineButton);
        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result2.setText(result2.getText()+"9");
                evaluate(9);

            }
        });

        bmas = (Button) rootView.findViewById(R.id.plusButton);
        bmas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // addResultToDB(result1.getText());
                result2.setText(result2.getText()+"+");
                operator = '+';
                var1=prem;
                var2 ="";

            }
        });

        bmenos = (Button) rootView.findViewById(R.id.minusButton);
        bmenos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // addResultToDB(result1.getText());
                result2.setText(result2.getText()+"-");
                operator = '-';
                var1=prem;
                var2 ="";

            }
        });

        bpor = (Button) rootView.findViewById(R.id.multiplyButton);
        bpor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   addResultToDB(result1.getText());
                result2.setText(result2.getText()+"x");
                operator = 'x';
                var1=prem;
                var2 ="";

            }
        });

        bentre = (Button) rootView.findViewById(R.id.divideButton);
        bentre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           //     addResultToDB(result1.getText());
                result2.setText(result2.getText()+"/");
                operator='/';
                var1=prem;

                var2 ="";

            }
        });

        bpot = (Button) rootView.findViewById(R.id.potentButton);
        bpot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             ///   addResultToDB(result1.getText());
                result2.setText(result2.getText()+"^");
                operator = '^';
                var1=prem;
                var2 ="";

            }
        });

        bporce = (Button) rootView.findViewById(R.id.percentButton);
        bporce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   addResultToDB(result1.getText());
                result2.setText(result2.getText()+"%");
                operator = '%';
                var1=prem;
                var2 ="";

            }
        });

        bclear = (Button) rootView.findViewById(R.id.clearButton);
        bclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           //     addResultToDB(result1.getText());
                result2.setText("");
                result1.setText("0");
                var1=0;
                varR=0;
                prem = 0;
                var2="";
                operator =' ';
            }
        });

        bback = (Button) rootView.findViewById(R.id.backspaceButton);
        bback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CharSequence backspace=result2.getText();
                if(backspace.length()>0){
                    result2.setText(backspace.subSequence(0,backspace.length()-1));
                    if(var2.length()>=1){
                       // Log.i("Tengo",""+var2);
                        var2 = var2.substring(0,var2.length()-1);
                        if(var2.isEmpty())
                            varR=0;
                        else
                            varR = Float.parseFloat(var2);
                        calculate();
                    }

                }else {
                    result2.setText("");
                    result1.setText("0");
                    var1=0;
                    varR=0;
                    prem = 0;
                    var2="";
                    operator =' ';
                }
            }
        });



    }

    public void evaluate(int n){

        if(operator==' '){
            if(var2==""){
                var1 = n;
                prem = n;
            }

            var2 = var2+n;
        }else {
            var2 =var2+n;
            varR = Float.parseFloat(var2);
        }


        calculate();
    }

    public void calculate(){
        switch (operator){
            case ' ': break;
            case '+': prem = var1 + varR; break;
            case '-': prem = var1 - varR; break;
            case 'x': prem = var1 * varR; break;
            case '/': prem = var1 / varR; break;
            case '%': prem = var1 * varR/100; break;
            case '^': prem = (float)Math.pow((double)var1,(double)varR); break;
        }

        if(operator!=' '){
            result1.setTag(Float.toString(prem));
            addResultToDB(result1.getText());
            new updateFields().execute(result1);
        }




    }

    public void addResultToDB(CharSequence sResult){

        if(!sResult.equals("0")) {
            ContentValues initialValues = new ContentValues();
            initialValues.put("comment", sResult.toString());
            Uri contentUri = Uri.withAppendedPath(ContentProviderDb.CONTENT_URI, "results");
            Uri resultUri = getActivity().getApplicationContext().getContentResolver().insert(contentUri, initialValues);

            cm.sendData();

        }


           //  datasource.createComment(sResult.toString());

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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode,resultCode,data);
        String result=data.getStringExtra("result");
        Log.i("Result:",""+requestCode);

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                result=data.getStringExtra("result");
                var1 = Float.parseFloat(result);
                var2 = result;
                prem = Float.parseFloat(result);
                result1.setTag(var2);
                result2.setTag(var2);
                new updateFields().execute(result1);
                new updateFields().execute(result2);


            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(savedInstanceState!=null){
            var1 = savedInstanceState.getFloat("var1");
            var2 = savedInstanceState.getString("var2");
            varR = savedInstanceState.getFloat("varR");
            prem = savedInstanceState.getFloat("prem");
            operator = savedInstanceState.getChar("operator");
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("var2", var2);
        outState.putFloat("var1",var1);
        outState.putFloat("varR",varR);
        outState.putFloat("prem",prem);
        outState.putChar("operator",operator);



    }
}
