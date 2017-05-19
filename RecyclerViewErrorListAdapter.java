package com.demo.touchstone.grammarapicheck;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by touchstone on 4/5/2017.
 */

public class RecyclerViewErrorListAdapter extends RecyclerView.Adapter<RecyclerViewErrorListAdapter.RecyclerViewErrorListHolder>{
    protected Context context ;
    protected ArrayList<GrammarResponse> responseList ;
    protected int sno = 0 ;



    public RecyclerViewErrorListAdapter(Context context, ArrayList<GrammarResponse> list) {
        this.context = context;
        this.responseList = list;


    }


    @Override
    public RecyclerViewErrorListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewGroup mainViewGroup;
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        mainViewGroup = (ViewGroup) layoutInflater.inflate(R.layout.error_list_view_layout, parent, false);
        RecyclerViewErrorListHolder recyclerViewHolder = new RecyclerViewErrorListHolder(mainViewGroup);

        return recyclerViewHolder;

    }

    @Override
    public void onBindViewHolder(RecyclerViewErrorListHolder holder, int position) {


        ArrayList<String> correctionList = new ArrayList<>();
        int size_of_better = responseList.get(0).getErrors().get(position).getBetter().size();

        for (int i = 0; i < size_of_better; i++) {
            correctionList.add(responseList.get(0).getErrors().get(position).getBetter().get(i));

        }
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(context,android.R.layout.simple_list_item_1 , correctionList);
        holder.possibleCorrectionsSpinner.setAdapter(spinnerAdapter);
        holder.errorText.setText(responseList.get(0).getErrors().get(position).getBad());

        holder.serialNoText.setText( ++position + "") ;

    }

    @Override
    public int getItemCount() {
        return responseList.get(0).getErrors().size() ;
    }

     class RecyclerViewErrorListHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView errorText, serialNoText ;
        public Spinner possibleCorrectionsSpinner ;
        public RecyclerViewErrorListHolder(View itemView) {
            super(itemView);
            errorText = (TextView)itemView.findViewById(R.id.errorstextview);
            possibleCorrectionsSpinner = (Spinner) itemView.findViewById(R.id.possiblecorrectionsspinner);
            serialNoText = (TextView)itemView.findViewById(R.id.serialnotv) ;
        }
        @Override
        public void onClick(View v) {

        }
    }
}
