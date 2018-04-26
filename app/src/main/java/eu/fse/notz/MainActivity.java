package eu.fse.notz;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
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
    public static final int RESUL_REMOVE_NOTE=1002;
    private RecyclerView mRecyclerView;
    private NotesAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    //private String[] myDataset={"nota1", "nota2"};
    private ArrayList<Note> myDataset;
    private FloatingActionButton addNoteButton;



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

        myDataset=new ArrayList<>();

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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == EDIT_REQUEST) {

            if (resultCode == Activity.RESULT_OK) {

                //getPosition from returnIntent
                int editedNotePosition = data.getIntExtra("position", -1);

                mAdapter.updateNote(editedNotePosition,
                        data.getStringExtra("title"),
                        data.getStringExtra("description"));


            }

        }
        if(resultCode==RESUL_REMOVE_NOTE){
            
        }


    }


    private void showDialog(){

        final AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        final View dialogView= LayoutInflater.from(this).inflate(R.layout.dialog_add_note,null);

        alertBuilder.setView(dialogView);
        alertBuilder.setTitle(R.string.titolo_add_note);


        final EditText titleET=(EditText) dialogView.findViewById(R.id.dialog_title_et);
        final EditText descriptionET=(EditText) dialogView.findViewById(R.id.dialog_description_et);

        alertBuilder.setPositiveButton(R.string.dialog_positive_button,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //
                        String insertTitle=titleET.getText().toString();
                        String insertDescription=descriptionET.getText().toString();

                        Note note = new Note(insertTitle, insertDescription);
                        mAdapter.addNote(note);

                    }
                });

        alertBuilder.setNegativeButton(R.string.dialog_negative_button,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {


                    }
                });

        alertBuilder.show();


    }
}
