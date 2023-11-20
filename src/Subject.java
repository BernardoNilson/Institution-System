import java.util.ArrayList;
import java.util.List;

/**
 * Subject
 */
public class Subject {
    protected int id;
    protected String name;
    protected String description;
    protected int maxStudents;
    protected int workload;
    protected List<Teacher> teachers;

    public Subject(int id, String name, String description, int maxStudents, int workload, List<Teacher> teachers) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.maxStudents = maxStudents;
        this.workload = workload;
        this.teachers = new ArrayList<>(teachers);
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getMaxStudents() {
        return maxStudents;
    }

    public int getWorkload() {
        return workload;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    @Override
    public String toString() {
        String s = "";
        s += " Nome: " + name;
        s += "\n Descrição: " + description;
        s += "\n Código: " + id;
        s += "\n Carga-horária: " + workload;
        s += "\n Quantidade máxima de alunos: " + maxStudents;
        return s;
    }
}
