package com.example.melaniepeng.gradebook;

//Name: Hui Min Peng 
//Name: Yuan Xia 

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class GradeAdapter extends ArrayAdapter<Grade> {

    private Context mContext;
    private List<Grade> GradeList = new ArrayList<Grade>();


    public GradeAdapter( Context context, ArrayList<Grade> list)
    {
        super( context, 0, list);
        mContext = context;
        GradeList = list;
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        View item = convertView;
        if(item == null)
            item = LayoutInflater.from(mContext).inflate(R.layout.grade_view,parent,false);
        Grade grade = GradeList.get(position);
        TextView name = (TextView) item.findViewById(R.id.textView1);
        name.setText(grade.getName());
        TextView letterGrade = (TextView) item.findViewById(R.id.textView2);
        letterGrade.setText(grade.getGrade());
        return item;
    }
}
