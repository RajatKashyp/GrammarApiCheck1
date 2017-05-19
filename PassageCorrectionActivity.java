package com.demo.touchstone.grammarapicheck;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

import static com.demo.touchstone.grammarapicheck.HttpCall.grammarResponse;

public class PassageCorrectionActivity extends AppCompatActivity {

    private RecyclerView correctionRecyclerView ;
    private TextView originalPassageTextView , errorFreeMsgTv , congratsTv ;
    private ScrollView scrollView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passage_correction);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initViews();

    }
    @TargetApi(Build.VERSION_CODES.N)
    private void initViews(){

        scrollView = (ScrollView)findViewById(R.id.scrollview) ;
        scrollView.smoothScrollTo(0,0);
        ArrayList<GrammarResponse> grammarlist = new ArrayList<>() ;
        grammarlist.add(grammarResponse) ;

        originalPassageTextView = (TextView)findViewById(R.id.originalpassagetv) ;
        correctionRecyclerView = (RecyclerView)findViewById(R.id.correctionrecyclerview) ;
        correctionRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        String originalPassage = getIntent().getStringExtra("passage") ;
        originalPassageTextView.setText(originalPassage);
        int errorSize = grammarResponse.getErrors().size() ;
        if (grammarResponse!= null && grammarResponse.isResult() && errorSize > 0) {
            RecyclerViewErrorListAdapter recyclerViewErrorListAdapter = new RecyclerViewErrorListAdapter(PassageCorrectionActivity.this, grammarlist );
            correctionRecyclerView.setAdapter(recyclerViewErrorListAdapter);
            recyclerViewErrorListAdapter.notifyDataSetChanged();// Notify the adapter
        }
        else {

            congratsTv = (TextView)findViewById(R.id.congratstextview) ;
            congratsTv.setVisibility(View.VISIBLE);
            errorFreeMsgTv = (TextView)findViewById(R.id.errorfreemsgtextview) ;
            errorFreeMsgTv.setVisibility(View.VISIBLE);
        }

    }
}


//for creating corrected passage


/* sizeOfErrors = grammarResponse.getErrors().size() ;
        String[] offsets = new String[sizeOfErrors] ;
        String[] bad = new String[sizeOfErrors] ;
        String[] better = new String[sizeOfErrors] ;
        int[] intOffsets = new int[sizeOfErrors];
        int[] intLengths = new int[sizeOfErrors] ;
        String[] lengths = new String[sizeOfErrors] ;
        int i ;


        for( i = 0; i < sizeOfErrors; i++) {

             offsets[i] = String.valueOf(grammarResponse.getErrors().get(i).getOffset());
            int length = Integer.parseInt(String.valueOf(grammarResponse.getErrors().get(i).getLength()));
            bad[i] = grammarResponse.getErrors().get(i).getBad();
            better[i] = grammarResponse.getErrors().get(i).getBetter().get(0);
            lengths[i] = String.valueOf(grammarResponse.getErrors().get(i).getLength()) ;
            intOffsets[i] = Integer.parseInt(offsets[i]);
            intLengths[i] = Integer.parseInt(lengths[i]) ;
        }

        for(int j = 1 ; j < sizeOfErrors ; j++) {


            if(intOffsets[j] == intOffsets[j-1]) {
            }
            else
                originalPassage = originalPassage.substring(0,intOffsets[j-1]) + originalPassage.substring(intOffsets[j-1]).replaceFirst(bad[j-1], better[j-1]);

        }

        String newPassage = originalPassage ;
        Log.e("new passage", newPassage) ;*/

