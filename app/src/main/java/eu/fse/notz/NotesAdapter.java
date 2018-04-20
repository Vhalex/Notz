package eu.fse.notz;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import eu.fse.notz.R;

/**
 * Created by Amministratore on 12/04/2018.
 */

public class NotesAdapter extends RecyclerView.Adapter {

    private ArrayList<Note> mDataset;



    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView titleTv;
        public TextView descriptionTv;



        public ViewHolder(View itemView) {
            super(itemView);
            titleTv=(TextView) itemView.findViewById(R.id.title_tv);
            descriptionTv= (TextView) itemView.findViewById(R.id.description_tv);
        }
    }

    public NotesAdapter(ArrayList<Note> myDataset){
        mDataset = myDataset;
    }


    @Override
    public NotesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
     View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_note, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        NotesAdapter.ViewHolder noteVh=(NotesAdapter.ViewHolder)holder;
        Note currentNote= mDataset.get(position);
        noteVh.titleTv.setText(currentNote.getTitle());
        noteVh.descriptionTv.setText(currentNote.getDescription());
    }

    public void addNote(Note note){
        this.mDataset.add(0,note);
        //notifyDataSetChanged();
        notifyItemInserted(0);
    }



    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
