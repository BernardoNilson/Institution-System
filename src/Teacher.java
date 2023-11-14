/*
 * Teacher
 */

import java.util.ArrayList;

public class Teacher extends Person {
    private Degree degree;
    private ArrayList<Class> classes;
    
    public Teacher(String name, int id, Degree degree){
        super(name, id, 12);
        this.degree = degree;
        classes = null;
    }

    // Getters
    public Degree getDegree() {
        return degree;
    }

    @Override
    public String toString() {
        String s = super.toString();
        s += "\nFormação: " + degree;
        return s;
    }
}