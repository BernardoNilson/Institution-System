import java.util.ArrayList;
import java.util.List;

/**
 * Class
 */
public class Class extends Subject{
    private List<Student> students;
    private Teacher teacher;

    public Class(Subject subject, List<Student> students) {
        super(subject.getId(), subject.getName(), subject.getDescription(), subject.getMaxStudents(), subject.getWorkload(), subject.getRequiredDegree());
        this.students = students;
        this.teacher = null;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public List<Student> getStudents() {
        return students;
    }

    @Override
    public synchronized String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("\nProfessor: ").append(teacher.getName());
        sb.append("\nAlunos:");

        students.stream()
                .map(Student::getName) // Extract student IDs
                .forEach(name -> sb.append("\n- ").append(name));

        return sb.toString();
    }
}
