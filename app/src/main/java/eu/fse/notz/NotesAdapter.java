package eu.fse.notz;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.NotActiveException;
import java.util.ArrayList;

import eu.fse.notz.R;

/**
 * Created by Amministratore on 12/04/2018.
 */

public class NotesAdapter extends RecyclerView.Adapter {

    private ArrayList<Note> mDataset;
    private Context context;



    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView titleTv;
        public TextView descriptionTv;




        public ViewHolder(final View itemView) {
            super(itemView);//rappresenta intera riga

            titleTv=(TextView) itemView.findViewById(R.id.title_tv);
            descriptionTv= (TextView) itemView.findViewById(R.id.description_tv);




            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(context, NoteActivity.class);

                    String title = mDataset.get(getAdapterPosition()).getTitle();
                    String description = mDataset.get(getAdapterPosition()).getDescription();

                    intent.putExtra("title",title);
                    intent.putExtra("description",description);
                    intent.putExtra("position",getAdapterPosition());

                    ((MainActivity)context).startActivityForResult(intent,1001);



                }
            });
        }
    }

    public NotesAdapter(ArrayList<Note> myDataset, Context context){
        mDataset = myDataset;
        this.context=context;
    }

    public Note getNote(int index) {
        return mDataset.get(index);
    }


    public void updateNote(int index,Note note){
        mDataset.set(index,note);
        notifyItemChanged(index);
    }

    public void updateNote(int index,String title, String description){

        Note note = mDataset.get(index);

        note.setTitle(title);
        note.setDescription(description);
        notifyItemChanged(index);



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
        notifyItemInserted(0);
    }

    public void removeNote(Note index){
        this.mDataset.remove(index);
        notifyItemRemoved(index);
    }



    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
