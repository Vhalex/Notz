package eu.fse.notz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

/**
 * Created by Amministratore on 12/04/2018.
 */

public class NoteActivity extends AppCompatActivity {

        private EditText titleET;
        private EditText descriptionET;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        titleET=(EditText)findViewById(R.id.title_edit);
        Intent intent= getIntent();
        String title= intent.getStringExtra("title");
        descriptionET=(EditText) findViewById(R.id.description_edit);
        Intent intent1=getIntent();
        String description= intent1.getStringExtra("description");

    }
}
