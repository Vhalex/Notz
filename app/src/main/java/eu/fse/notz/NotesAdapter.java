package eu.fse.notz;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import eu.fse.notz.R;

/**
 * Created by Amministratore on 12/04/2018.
 */

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder>{

    private ArrayList<Note> mDataset;



    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextView;

        public ViewHolder(TextView v) {
            super(v);
            mTextView = v;
        }
    }

    public NotesAdapter(ArrayList<Note> myDataset){
        mDataset = myDataset;
    }


    @Override
    public NotesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TextView v = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
       NotesAdapter.ViewHolder noteVh=(NotesAdapter.ViewHolder)holder;
        Note currentNote= mDataset.get(position);
        noteVh.mTextView.setText(currentNote.getTitle());
    }



    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
