package android.com.jamsand.io.databaseexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper myHelper;

    private ListView listView;

    private SimpleCursorAdapter adapter;

    public String[] from = new String[] { DatabaseHelper._ID,
            DatabaseHelper.NAME, DatabaseHelper.ADDRESS};

     int[] to = new int[]{ 0,1,2};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_emp_list);

        myHelper = new DatabaseHelper(this);
        myHelper.open();

        listView = (ListView) findViewById(R.id.list_view);
        listView.setEmptyView(findViewById(R.id.empty));
        Cursor c = myHelper.getAllEmployees();

        adapter = new SimpleCursorAdapter(this,R.layout.activity_view_record,c,from,to,0);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView idTextView = (TextView) view.findViewById(0);
                TextView nameTextView = (TextView) view.findViewById(0);
                TextView addressTextView = (TextView) view.findViewById(0);

                String id = idTextView.getText().toString();
                String title = nameTextView.getText().toString();
                String desc = addressTextView.getText().toString();

                Intent modify_intent = new Intent(getApplicationContext(),ModifyEmployeeActivity.class);
                modify_intent.putExtra("name",title);
                modify_intent.putExtra("address",title);
                modify_intent.putExtra("id",title);
                startActivity(modify_intent);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.add_record) {
            Intent add_mem = new Intent(this,AddEmployeeActivity.class);
            startActivity(add_mem);
        }
        return super.onOptionsItemSelected(item);
    }
}