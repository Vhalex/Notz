package eu.fse.notz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Amministratore on 12/04/2018.
 */

public class NoteActivity extends AppCompatActivity {

        private EditText titleET;
        private EditText descriptionET;
        Button editConfirmBtn;
        Button editDelateBtn;
        Intent intent;
        String title, description;
        CheckBox favoriteCheck;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        titleET = (EditText) findViewById(R.id.title_edit);
        final Intent intent = getIntent();
        final String title = intent.getStringExtra("title");
        descriptionET = (EditText) findViewById(R.id.description_edit);
        Intent intent1 = getIntent();
        String description = intent1.getStringExtra("description");
        editConfirmBtn = (Button) findViewById(R.id.edit_confirm);
        editDelateBtn = (Button) findViewById(R.id.edit_delete);
        favoriteCheck=(CheckBox) findViewById(R.id.favorte_check);

        titleET.setText(title);
        descriptionET.setText(description);

        editConfirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String editedTitle = titleET.getText().toString();
                String editedDescription = descriptionET.getText().toString();


                int position = intent.getIntExtra("position", -1);


                Intent returnIntent = new Intent();
                returnIntent.putExtra("title", editedTitle);
                returnIntent.putExtra("description", editedDescription);
                returnIntent.putExtra("position", position);
                if(favoriteCheck.isChecked()) returnIntent.putExtra("favorite", "true");
                setResult(Activity.RESULT_OK, returnIntent);


                finish();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_note,menu);

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.edit_delete){



            int position = intent.getIntExtra("position", -1);

            Intent returnIntent = new Intent();
            returnIntent.putExtra("title", title);
            returnIntent.putExtra("description", description);
            returnIntent.putExtra("position", position);
            setResult(MainActivity.RESUL_REMOVE_NOTE, returnIntent);

            finish();
            return true;
        }
        if(item.getItemId() == R.id.ciao){

            Toast.makeText(this,"Ciao",Toast.LENGTH_LONG).show();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
