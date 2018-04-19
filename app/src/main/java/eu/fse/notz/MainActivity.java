package eu.fse.notz;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AlertDialogLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import java.util.ArrayList;

import eu.fse.notz.R;

/**
 * Created by Amministratore on 12/04/2018.
 */

public class MainActivity extends AppCompatActivity  {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    //private String[] myDataset={"nota1", "nota2"};
    private ArrayList<Note> myDataset;
    private FloatingActionButton fab;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View v) {
            showDialod();
            }
                               });


        mRecyclerView= (RecyclerView) findViewById(R.id.notes_rv);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new StaggeredGridLayoutManager(2, 1);
        mRecyclerView.setLayoutManager(mLayoutManager);

        myDataset=new ArrayList<>();
        Note pinPalazzo=new Note("pin", "26634");
        myDataset.add(pinPalazzo);
        Note spesa=new Note("spesa", "latte");
        myDataset.add(spesa);

        mAdapter = new NotesAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);
    }

  private void showDialod(){
      AlertDialog.Builder builder = new AlertDialog.Builder(this);
      builder.setView(R.layout.activity_dialog);
      builder.setTitle(R.string.title_d);

      builder.setPositiveButton(R.string.)
      AlertDialog.show();

  }
}
