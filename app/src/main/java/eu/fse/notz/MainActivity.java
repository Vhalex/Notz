package eu.fse.notz;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AlertDialogLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

import eu.fse.notz.R;

/**
 * Created by Amministratore on 12/04/2018.
 */

public class MainActivity extends AppCompatActivity  {

    public static final int EDIT_REQUEST=1001;
    public static final int RESUL_REMOVE_NOTE=RESULT_FIRST_USER +1;
    private RecyclerView mRecyclerView;
    private NotesAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    //private String[] myDataset={"nota1", "nota2"};
    private ArrayList<Note> myDataset;
    private FloatingActionButton addNoteButton;
    private DatabaseHandler db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView= (RecyclerView) findViewById(R.id.notes_rv);
        mRecyclerView.setHasFixedSize(true);
        addNoteButton = (FloatingActionButton) findViewById(R.id.fab);

        mLayoutManager = new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        db= new DatabaseHandler(this);
        myDataset=new ArrayList<>();

       // myDataset.addAll(db.getAllNotes());







        mAdapter = new NotesAdapter(myDataset, this);
        mRecyclerView.setAdapter(mAdapter);

        addNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == EDIT_REQUEST) {

            if (resultCode == Activity.RESULT_OK) {

                //getPosition from returnIntent
                int editedNotePosition = data.getIntExtra("position", -1);

                mAdapter.updateNote(editedNotePosition,
                        data.getStringExtra("title"),
                        data.getStringExtra("description"));
                db.updateNote(mAdapter.getNote(editedNotePosition));
            }
            if(resultCode == RESUL_REMOVE_NOTE){
                final int editedNotePosition = data.getIntExtra("position", -1);
                db.deletNote(mAdapter.getNote(editedNotePosition));
                mAdapter.removeNote(editedNotePosition);


                Snackbar.make(mRecyclerView,getString(R.string.delete),Snackbar.LENGTH_LONG)
                        .setAction(R.string.cancel, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                Note note = new Note(data.getStringExtra("title"),
                                        data.getStringExtra("description"));

                                mAdapter.addNote(editedNotePosition,note);
                                db.addNote(note);
                            }
                        })
                        .show();
            }
        }
    }
    private void showDialog(){
        showDialog(null);
    }



    private void showDialog(String title){

        final AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        final View dialogView= LayoutInflater.from(this).inflate(R.layout.dialog_add_note,null);


        final EditText titleET=(EditText) dialogView.findViewById(R.id.dialog_title_et);
        final EditText descriptionET=(EditText) dialogView.findViewById(R.id.dialog_description_et);

        if(title!= null) titleET.setText(title);

        alertBuilder.setView(dialogView)
                .setTitle(R.string.titolo_add_note)
        .setPositiveButton(R.string.dialog_positive_button,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //
                        String insertTitle=titleET.getText().toString();
                        String insertDescription=descriptionET.getText().toString();
                        Note.NoteBuilder noteBuilder = new Note.NoteBuilder();
                        noteBuilder
                                .setTitle(insertTitle)
                                .setDescription(insertDescription)
                                .setId(12)
                                .setShownOnTop(true);


                        mAdapter.addNote(noteBuilder.build());
                        db.addNote(noteBuilder.build());
                    }
                })

       .setNegativeButton(R.string.dialog_negative_button,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {



                    }
                })
                .create()
        .show();
    }
}
