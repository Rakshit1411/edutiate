package com.example.rakshitsharma.edutiate.Home.my_batches.batches_cards;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.transition.Fade;
import android.support.transition.TransitionManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.example.rakshitsharma.edutiate.R;
import com.example.rakshitsharma.edutiate.GetAllData.loadingData1;
import com.example.rakshitsharma.edutiate.GetAllData.loadingData2;

import java.util.ArrayList;
import java.util.Random;

import static com.example.rakshitsharma.edutiate.Home.my_batches.batches_cards.my_batches_in_home.col;
import static com.example.rakshitsharma.edutiate.Home.my_batches.batches_cards.my_batches_in_home.v1;


/**
 * Created by Rakshit Sharma on 4/4/2017.
 */

public class my_batches_in_home_adapter extends RecyclerView
        .Adapter<my_batches_in_home_adapter
        .DataObjectHolder>  {

    private static String LOG_TAG = "my_batches_adapter";
    public static ArrayList<my_batches_in_home_object> mDataset;
    private static my_batches_in_home_adapter.MyClickListener myClickListener;
    public static CardView cv;
    public static TextView subCode;
    public static TextView subName;
    public static int cardNumber;
    public static TextView Teacher;
    public static TextDrawable drawable1;
    public static int indexer;
    public static String title_subject;
    public static int title;

    public static class DataObjectHolder extends RecyclerView.ViewHolder
            implements View
            .OnClickListener {

        public DataObjectHolder(final View itemView) {
            super(itemView);
            subCode = (TextView) itemView.findViewById(R.id.subCode);
            subName = (TextView) itemView.findViewById(R.id.subjectName);
            Teacher = (TextView) itemView.findViewById(R.id.Teacher);
            ColorGenerator generator = ColorGenerator.MATERIAL;
            int randomColor = generator.getRandomColor();



            Log.i(LOG_TAG, "Adding Listener");
            final String trans = itemView.getContext().getString(R.string.transition);

//-------------Shared element------------
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    title=getAdapterPosition();
                      Fade fade = new Fade();
                    fade.setDuration(2000);
                    cardNumber = getLayoutPosition();
                    title_subject = loadingData1.subName.get(getLayoutPosition()).toString();
                    TransitionManager.beginDelayedTransition(v1,fade);
                    Intent intent = new Intent(itemView.getContext(),loadingData2.class);
                    intent.putExtra("viewNumber",getLayoutPosition());
                    itemView.getContext().startActivity(intent);
                   // ((Activity)itemView.getContext()).finish();


                    }
            });
        }
//---------------shared element-------------


        @Override
        public void onClick(View v) {

            myClickListener.onItemClick(getAdapterPosition(), v);
        }
    }

    public void setOnItemClickListener(my_batches_in_home_adapter.MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    public my_batches_in_home_adapter(ArrayList<my_batches_in_home_object> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_batch_card, parent, false);
        cv = (CardView)view.findViewById(R.id.card_view);
        ColorGenerator generator = ColorGenerator.MATERIAL;
        int randomColor = generator.getRandomColor();
        Random rand = new Random();
        int x = rand.nextInt(17);
        cv.setCardBackgroundColor(Color.parseColor(col[x]));
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Activity)view.getContext()).finish();
            }
        });

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;

    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
        subName.setText(mDataset.get(position).getmText1());
        subCode.setText(mDataset.get(position).getmText2());
        Teacher.setText(mDataset.get(position).getmText3());
        indexer = position;



    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public void addItem(my_batches_in_home_object dataObj, int index) {
        mDataset.add(index, dataObj);
        notifyItemInserted(index);
    }

    public void deleteItem(int index) {
        mDataset.remove(index);
        notifyItemRemoved(index);
    }


    public interface MyClickListener {
        public void onItemClick(int position, View v);

    }
}
