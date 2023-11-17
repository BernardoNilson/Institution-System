/*
 * Student
 */

import java.util.ArrayList;
import java.util.List;

public class Student extends Person {
    private List<Subject> subjects;

    public Student(String name, int id) {
        super(name, id, 28);
        subjects = null;
    }

    public List<Subject> getSubjects() {
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
        s += "\nDisciplias:" + subjects.size();
        /* for (Subject subject : getSubjects()) {
            s += "\n- " + subject.getName();
        } */
        return s;
    }
}
