/*
 * Student
 */

import java.util.ArrayList;

public class Student extends Person {
    private ArrayList<Subject> subjects;

    public Student(String name, int id) {
        super(name, id, 28);
        subjects = null;
    }

    public ArrayList<Subject> getSubjects() {
        return subjects;
    }

    public boolean addSubject(Subject subject) {
        if (subject == null) return false;
        if (subjects == null) subjects = new ArrayList<>();
        return subjects.add(subject);
    }

    @Override
    public String toString(){
        String s = super.toString();
        s += "\nTurmas:";
        for (Subject subject : subjects) {
            s += "\n- " + subject.getName();
        }
        return s;
    }
}
