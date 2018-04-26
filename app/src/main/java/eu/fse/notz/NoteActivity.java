package eu.fse.notz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Amministratore on 12/04/2018.
 */

public class NoteActivity extends AppCompatActivity {

        private EditText titleET;
        private EditText descriptionET;
        Button editConfirmBtn;
        Button editDelateBtn;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        titleET=(EditText)findViewById(R.id.title_edit);
        final Intent intent= getIntent();
        final String title= intent.getStringExtra("title");
        descriptionET=(EditText) findViewById(R.id.description_edit);
        Intent intent1=getIntent();
        String description= intent1.getStringExtra("description");
        editConfirmBtn=(Button) findViewById(R.id.edit_confirm);
        editDelateBtn=(Button) findViewById(R.id.edit_delete);

        titleET.setText(title);
        descriptionET.setText(description);

        editConfirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String editedTitle = titleET.getText().toString();
                String editedDescription = descriptionET.getText().toString();


                int position = intent.getIntExtra("position",-1);

                Intent returnIntent = new Intent();
                returnIntent.putExtra("title",editedTitle);
                returnIntent.putExtra("description",editedDescription);
                returnIntent.putExtra("position",position);
                setResult(Activity.RESULT_OK,returnIntent);


                finish();
            }
        });
        editDelateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String editedTitle = titleET.getText().toString();
                String editedDescription = descriptionET.getText().toString();


                int position = intent.getIntExtra("position",-1);

                Intent returnIntent = new Intent();
                returnIntent.putExtra("title",editedTitle);
                returnIntent.putExtra("description",editedDescription);
                returnIntent.putExtra("position",position);
                setResult(Activity.RESULT_CANCELED,returnIntent);

                finish();

                }
        });

    }

}
