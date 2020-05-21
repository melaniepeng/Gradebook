package com.example.melaniepeng.gradebook;

//Name: Hui Min Peng ID: 78923490
//Name: Yuan Xia ID: 91012117

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    private DatabaseReference myReference;
    private FirebaseDatabase database;
    private ChildEventListener childEventListener;
    private GradeAdapter adapter;
    private ArrayList<Grade> gList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
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
        ListView results = findViewById(R.id.listViewSearch);
        results.setAdapter(adapter);
    }

    public void searches(View v)
    {
        boolean inList = false;
        EditText name = (EditText) findViewById(R.id.editText);
        String searchName = name.getText().toString();
        ArrayList<Grade> wanted = new ArrayList<Grade>();
        for (Grade c : gList) {
            Log.d("cName",c.getName());
            Log.d("searchName2", searchName);
            if (c.getName().equalsIgnoreCase(searchName)) {
                inList = true;
                wanted.add(c);
            }
        }
        if (!inList)
        {
            adapter.clear();
            Toast.makeText(this, name.getText().toString() + " not found.", Toast.LENGTH_LONG).show();
        }
        else
        {
            adapter.clear();
            for(Grade g: wanted)
            {
                adapter.add(g);
            }
        }
        name.setText("");
    }

    public void home( View view)
    {
        Intent intent = new Intent( this, MainActivity.class);
        startActivity(intent);
    }
}
