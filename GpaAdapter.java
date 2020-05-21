package com.example.melaniepeng.gradebook;

//Name: Hui Min Peng ID: 78923490
//Name: Yuan Xia ID: 91012117

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

public class GpaAdapter extends ArrayAdapter<OverAllGPA> {
    private Context mContext;
    private List<OverAllGPA> gpaList = new ArrayList<OverAllGPA>();


    public GpaAdapter( Context context, ArrayList<OverAllGPA> list)
    {
        super(context, 0, list);
        mContext = context;
        gpaList = list;
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        View item = convertView;
        if(item == null)
            item = LayoutInflater.from(mContext).inflate(R.layout.gpa_view,parent,false);
        OverAllGPA gpa = gpaList.get(position);
        TextView name = (TextView) item.findViewById(R.id.textView1);
        name.setText(gpa.getName());
        TextView letterGrade = (TextView) item.findViewById(R.id.textView2);
        letterGrade.setText(Double.toString(gpa.toGPA()));
        return item;
    }
}
