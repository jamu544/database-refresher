package android.com.jamsand.io.databaseexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddEmployeeActivity extends AppCompatActivity {

    private EditText nameEditText;
    private EditText addressEditText;

    private DatabaseHelper myHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);

      //  nameEditText = (EditText) findViewById(R.id.name);
        addressEditText = (EditText) findViewById(R.id.address_edittext);

        myHelper = new DatabaseHelper(this);
        myHelper.open();

    }

    public void addButtonPressed(View view) {

        String name = nameEditText.getText().toString();
        String address = addressEditText.getText().toString();

        myHelper.add(name,address);

        Intent main = new Intent(this, MainActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(main);
    }
}