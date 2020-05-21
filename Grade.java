package com.example.melaniepeng.gradebook;

//Name: Hui Min Peng ID: 78923490
//Name: Yuan Xia ID: 91012117

public class Grade {
    private String name;
    private String grade;
    private String id;

    public Grade ()
    {
        name = "";
        grade = "";
    }

    public Grade (String name, String grade, String id)
    {
        this.name = name;
        this.grade = grade;
        this.id = id;
    }

    public String getGrade() {
        return grade;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String toString() {
        return name + "\t" + grade;
    }
}
