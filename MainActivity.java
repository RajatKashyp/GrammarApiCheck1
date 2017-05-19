package com.demo.touchstone.grammarapicheck;

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static com.demo.touchstone.grammarapicheck.HttpCall.grammarResponse;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button checkBtn;
    private EditText passageEditText;
    private TextView headingTextView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initViews();
    }

    private void initViews() {

        headingTextView = (TextView)findViewById(R.id.headtextview) ;

        Drawable img = getApplicationContext().getResources().getDrawable(
                R.mipmap.homeworkicon);
        img.setBounds(0, 0, 20, headingTextView.getMeasuredHeight());
        headingTextView.setCompoundDrawables(null,null,null,img);
        checkBtn = (Button) findViewById(R.id.checkgrammarbtn);
        passageEditText = (EditText) findViewById(R.id.passagetext);
        checkBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

       /*String s =passageEditText.getText().toString() ;
        String[] words = s.split("\\s+");
        for (int j = 0; j < words.length; j++) {

            words[j] = words[j].replaceAll("[^\\w]", "");
        }*/


        String passage = passageEditText.getText().toString().trim();
            if(passage != null && !(passage.isEmpty()) ) {

                if (InternetConnection.isDataConnectionAvailable(getApplicationContext()) == false) {

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                    alertDialogBuilder.setMessage("Please connect to the internet");
                    alertDialogBuilder.setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface arg0, int arg1) {

                                }
                            });
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                } else {
                    grammarResponse = HttpCall.homeWorkResponseCall(MainActivity.this, passage);
                }
            }
            else
            {

                Toast.makeText(getApplicationContext(), "Please fill up the passage", Toast.LENGTH_SHORT).show();
            }


        //  new checkResponse().execute() ;

    }
}
    /*private class checkResponse extends AsyncTask {
        String passage = passageEditText.getText().toString().trim() ;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("Loading...Please wait");
            progressDialog.show();


        }

        @Override
        protected Object doInBackground(Object[] objects) {
            //call the htpp service to get the any new NewNotice

            return null;
        }
        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);

            *//*if((grammarResponse != null) && (grammarResponse.isResult())){
                ArrayList<String> errorList = new ArrayList<>() ;
                ArrayList<String> correctionList = new ArrayList<>() ;
                for(int i=0; i<grammarResponse.getErrors().size(); i++) {
                    //   errors[i] = grammarResponse.getErrors().get(i).getBad();

                    errorList.add(grammarResponse.getErrors().get(i).getBad());

                    int size_of_better = grammarResponse.getErrors().get(i).getBetter().size() ;
                    for(int j= 0; j<size_of_better; j++) {
                        correctionList.add(grammarResponse.getErrors().get(i).getBetter().get(j));
                    }
                }
                Intent in =  new Intent(MainActivity.this, PassageCorrectionActivity.class) ;
                in.putExtra("passage",passage) ;
                in.putExtra("errorlist" ,  errorList) ;
                in.putExtra("correctionlist", correctionList) ;
                startActivityForResult(in, 1234);
            } else {
                //make visible empty layout when there is no message for the user
                Toast.makeText(MainActivity.this, "Too many mistakes, attempt again", Toast.LENGTH_SHORT).show();
            }
            if(progressDialog.isShowing() && progressDialog!= null)
            progressDialog.dismiss();*//*
        }
    }*/


