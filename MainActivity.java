package com.example.melaniepeng.gradebook;

//Name: Hui Min Peng ID: 78923490
//Name: Yuan Xia ID: 91012117

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void addGrade( View view)
    {
        Intent intent = new Intent( this, AddDeleteGradeActivity.class);
        startActivity(intent);
    }

    public void viewGPA(View view)
    {
        Intent intent = new Intent(this, OverAllGPAActivity.class);
        startActivity( intent );
    }


}
