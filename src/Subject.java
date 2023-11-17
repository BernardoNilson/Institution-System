/**
 * Subject
 */
public class Subject {
    protected int id;
    protected String name;
    protected String description;
    protected int maxStudents;
    protected int workload;
    protected Degree requiredDegree;

    public Subject(int id, String name, String description, int maxStudents, int workload, Degree requiredDegree) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.maxStudents = maxStudents;
        this.workload = workload;
        this.requiredDegree = requiredDegree;
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

    public Degree getRequiredDegree() {
        return requiredDegree;
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
