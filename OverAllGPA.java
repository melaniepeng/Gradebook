package com.example.melaniepeng.gradebook;

//Name: Hui Min Peng ID: 78923490
//Name: Yuan Xia ID: 91012117

import java.util.ArrayList;

public class OverAllGPA {
    private String name;
    private ArrayList<String> grades = new ArrayList<String>();;
    private double GPA;

    public OverAllGPA()
    {
        name = "";
        GPA = 0.0;

    }

    public OverAllGPA(String name, String grade)
    {
        this.name = name;
        this.grades.add(grade);

    }

    public void AddGrade(String grade)
    {
        this.grades.add(grade);
    }


    public ArrayList<String> getGrades()
    {
        return this.grades;
    }

    public String getName(){
        return this.name;
    }

    public double toGPA()
    {
        double sum = 0;
        for(int i = 0; i < this.grades.size(); i++)
        {
            String g = this.grades.get(i);
            if(g.equalsIgnoreCase("a"))
            {
                sum += 4.0;
            }
            else if ( g.equalsIgnoreCase("b"))
            {
                sum += 3.0;
            }
            else if (g.equalsIgnoreCase("c"))
            {
                sum += 2.0;
            }
            else if(g.equalsIgnoreCase("d"))
            {
                sum += 1.0;
            }
        }
        return sum/this.grades.size();

    }

    public String toString()
    {
        return name + "\t" + Double.toString(toGPA());
    }


}
