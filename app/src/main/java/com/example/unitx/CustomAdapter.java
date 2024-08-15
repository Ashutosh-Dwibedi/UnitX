package com.example.unitx;


import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    private final ArrayList<String> localDataSet;
    private final OnNoteListener mOnNoteListener;

    @SuppressLint("NotifyDataSetChanged")
    public CustomAdapter(ArrayList<String> localDataSet, OnNoteListener mOnNoteListener) {
        this.localDataSet = localDataSet;
        this.mOnNoteListener = mOnNoteListener;
        notifyDataSetChanged();
    }


    /**
     * Providing a reference to the type of views that we are using
     * (custom ViewHolder).
     */
    public static  class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private final TextView textView;
        OnNoteListener onNoteListener;
        public ViewHolder(View view,OnNoteListener onNoteListener) {
            super(view);
            // Defining click listener for the ViewHolder's View

            textView = view.findViewById(R.id.textView2);
            this.onNoteListener=onNoteListener;
            itemView.setOnClickListener(this);
        }

        public TextView getTextView() {
            return textView;
        }

        @Override
        public void onClick(View v) {
            onNoteListener.onNoteClick(textView.getText().toString());
//            onNoteListener.onNoteClick(getAdapterPosition());
        }
    }

    // Creating new views (invoked by the layout manager)
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Creating a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.text_row_item, viewGroup, false);

        return new ViewHolder(view, mOnNoteListener);
    }

    // Replacing the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Getting an element from my dataset at this position and replacing the
        // contents of the view with that element
        viewHolder.getTextView().setText(localDataSet.get(position));
    }

    // Returning the size of our dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }

    public interface OnNoteListener{
        void onNoteClick(String clicked_item);
//        void onNoteClick(int position);
    }
}
