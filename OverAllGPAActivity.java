package com.example.melaniepeng.gradebook;

//Name: Hui Min Peng ID: 78923490
//Name: Yuan Xia ID: 91012117

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class OverAllGPAActivity extends AppCompatActivity {

    private DatabaseReference myReference;
    private FirebaseDatabase database;
    private ChildEventListener childEventListener;
    private GpaAdapter gpaAdapter;
    private ArrayList<OverAllGPA> gpaList;
    ArrayList<OverAllGPA> tempList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_over_all_gpa);
        database = FirebaseDatabase.getInstance();
        myReference = database.getReference("Grades");
        gpaList = new ArrayList<OverAllGPA>();
        tempList = new ArrayList<OverAllGPA>();

        childEventListener = new ChildEventListener(){
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Grade newGrade = dataSnapshot.getValue(Grade.class);
                boolean same = false;
                for (OverAllGPA gpa : gpaList)
                {
                    if (newGrade.getName().equalsIgnoreCase(gpa.getName()))
                    {
                        Log.d("nameGrade",newGrade.getName());
                        gpa.AddGrade(newGrade.getGrade());
                        same = true;
                    }
                }
                if(!same)
                {
                    Log.d("originalG",newGrade.getGrade());
                    OverAllGPA newGPA = new OverAllGPA(newGrade.getName(), newGrade.getGrade());
                    Log.d("GPA",newGPA.getName());
                    Log.d("GPA1",Double.toString(newGPA.toGPA()));
                    tempList.add(newGPA);
                    gpaAdapter.add(newGPA);
                }
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
        for(OverAllGPA gpa: tempList)
        {
            if(!gpaList.contains(gpa))
            {
                gpaList.add(gpa);
            }
        }
        myReference.addChildEventListener(childEventListener);
        gpaAdapter = new GpaAdapter(this, gpaList);
        ListView results = findViewById(R.id.listViewGPA);
        results.setAdapter(gpaAdapter);

    }


    public void home( View view)
    {
        Intent intent = new Intent( this, MainActivity.class);
        startActivity(intent);
    }

}
