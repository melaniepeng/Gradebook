package com.example.melaniepeng.gradebook;

//Name: Hui Min Peng ID: 78923490
//Name: Yuan Xia ID: 91012117

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AddDeleteGradeActivity extends AppCompatActivity {

    private DatabaseReference myReference;
    private FirebaseDatabase database;
    private ChildEventListener childEventListener;
    private GradeAdapter adapter;
    private ArrayList<Grade> gList;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addgrade);
        database = FirebaseDatabase.getInstance();
        myReference = database.getReference("Grades");
        gList = new ArrayList<Grade>();

        childEventListener = new ChildEventListener(){
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                adapter.add(dataSnapshot.getValue(Grade.class));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {}

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {}

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {}

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };

        myReference.addChildEventListener(childEventListener);
        adapter = new GradeAdapter(this, gList);
        ListView results = findViewById(R.id.listView);
        results.setAdapter(adapter);

    }

    public void addData (View v)
    {
        EditText editName = findViewById(R.id.editTextName);
        String name = editName.getText().toString();
        EditText editGrade = findViewById(R.id.editTextGrade);
        String grade = editGrade.getText().toString();

        if( name.length() > 0 )
        {
            String key = myReference.push().getKey();
            Grade c = new Grade(name, grade, key);
            myReference.child(key).setValue(c);
            Toast.makeText(this, c.getName() + " successfully added.", Toast.LENGTH_LONG).show();
        }
        editName.setText("Name");
        editGrade.setText("Grade");
    }

    public void remove(View view) {
        boolean found = false;
        EditText name = (EditText) findViewById(R.id.editTextName);
        String searchName = name.getText().toString();
        EditText grade = findViewById(R.id.editTextGrade);
        String searchGrade = grade.getText().toString();
        ArrayList<Grade> del = new ArrayList<Grade>();
        for (Grade c : gList) {
            if (c.getName().equalsIgnoreCase(searchName) && c.getGrade().equalsIgnoreCase(searchGrade)) {
                found = true;
                myReference.child(c.getId()).removeValue();
                del.add(c);
                break;
            }
        }
        if (!found) {
            Toast.makeText(this, name.getText().toString() + " and " + grade.getText().toString() + " not found.", Toast.LENGTH_LONG).show();
        }
        else
        {
            for(Grade g: del)
            {
                gList.remove(g);
                adapter.remove(g);
            }

        }
        name.setText("Name");
        grade.setText("Grade");
    }

    public void home( View view)
    {
        Intent intent = new Intent( this, MainActivity.class);
        startActivity(intent);
    }

}