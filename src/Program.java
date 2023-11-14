import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Program {

    private ArrayList<Teacher> teachers;
    private ArrayList<Student> students;
    private ArrayList<Subject> subjects;
    private ArrayList<Class> classes;

    /**
     * Construtor do Programa
     */
    public Program() {
        this.teachers = new ArrayList<>();
        this.students = new ArrayList<>();
        this.subjects = new ArrayList<>();
        this.classes = new ArrayList<>();
    }

    /*
     * Getters
     */
    public List<Teacher> getTeachers() {
        return teachers;
    }

    public List<Student> getStudents() {
        return students;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public List<Class> getClasses() {
        return classes;
    }

    /*
     * Métodos de busca
     */
    public Student findStudentById(int id) {
        for (Student student : students) {
            if (student.getId() == id)
                return student;
        }
        return null;
    }

    public Subject findSubjectById(int id) {
        for (Subject subject : subjects) {
            if (subject.getId() == id)
                return subject;
        }
        return null;
    }

    public Teacher findTeacherById(int id) {
        for (Teacher teacher : teachers) {
            if (teacher.getId() == id)
                return teacher;
        }
        return null;
    }

    /**
     * Cria o professor
     */
    public boolean createTeacher(String name, int id, Degree degree) {
        if (name == null || id < 0 || degree == null) {
            throw new IllegalArgumentException("Informações de professor invalidas!");
        }

        for (Teacher teacher : teachers) {
            if (teacher.getId() == id)
                return false;
        }

        Teacher teacher = new Teacher(name, id, degree);
        return teachers.add(teacher);
    }

    /**
     * Cria o aluno
     */
    public boolean createStudent(String name, int id) {
        if (name == null || id < 0) {
            throw new IllegalArgumentException("Informações de aluno invalidas!");
        }

        for (Student student : students) {
            if (student.getId() == id)
                return false;
        }

        Student student = new Student(name, id);
        return students.add(student);
    }

    public boolean addSubjectToStudant(int subjectId, int studentId) {
        if (studentId < 0 || subjectId < 0) {
            throw new IllegalArgumentException("Informações inválidas!");
        }

        Student student = findStudentById(studentId);
        Subject subject = findSubjectById(subjectId);

        return student.addSubject(subject);
    }

    /**
     * Cria a disciplina
     */
    public boolean createSubject(int id, String name, String description, int maxStudents, int workload,
            Degree requiredDegree) {
        if (id < 0 || name == null || description == null || maxStudents < 0 || workload < 0
                || requiredDegree == null) {
            throw new IllegalArgumentException("Informações de disciplina invalidas!");
        }

        for (Subject subject : subjects) {
            if (subject.getId() == id)
                return false;
        }

        Subject subject = new Subject(id, name, description, maxStudents, workload, requiredDegree);
        return subjects.add(subject);
    }

    /**
     * Cria a turma
     */
    public void allocAllClasses() {
        for (Subject subject : subjects) {

            ArrayList<Teacher> tempTeachers = teachers.stream()
                    .filter(teacher -> teacher.getDegree() == subject.getRequiredDegree()
                            && teacher.getUsedHours() + subject.getWorkload() <= teacher.getMaxHours())
                    .collect(Collectors.toCollection(ArrayList::new));

            /*
             * ArrayList<Teacher> tempTeachers = new ArrayList<>();
             * 
             * for (Teacher teacher : teachers) {
             * if (teacher.getDegree() == subject.getRequiredDegree()
             * && teacher.getUsedHours() + subject.workload <= teacher.maxHours) {
             * tempTeachers.add(teacher);
             * }
             * }
             */

            List<Student> tempStudents = students.stream()
                    .filter(student -> student.getSubjects().contains(subject)
                            && student.getUsedHours() + subject.getWorkload() <= student.getMaxHours())
                    .collect(Collectors.toCollection(ArrayList::new));

            /*
             * ArrayList<Student> tempStudents = new ArrayList<>();
             * 
             * for (Student student : students) {
             * if (student.getSubjects().contains(subject)
             * && student.getUsedHours() + subject.getWorkload() <= student.getMaxHours()) {
             * tempStudents.add(student);
             * }
             * }
             */

            ArrayList<Class> tempClasses = new ArrayList<>();

            System.out.println(tempStudents.size());
            int classQuantity = (tempStudents.size() / subject.getMaxStudents()) + 1;
            System.out.println(classQuantity);
            int studentsPerClass = tempStudents.size() / classQuantity;
            System.out.println(studentsPerClass);
            int remainder = tempStudents.size() % classQuantity;
            System.out.println(remainder);

            for (int i = 0; i < classQuantity; i++) {
                int studentsQuantity = studentsPerClass - 1;
                if (remainder > 0) {
                    studentsQuantity++;
                    remainder--;
                }

                List<Student> studentsToAlloc = tempStudents.subList(0, studentsQuantity);
                // tempStudents.removeAll(studentsToAlloc);
                tempStudents = tempStudents.stream()
                        .filter(student -> !studentsToAlloc.contains(student))
                        .collect(Collectors.toList());

                Class tempClass = new Class(subject, studentsToAlloc);

                if (!tempTeachers.isEmpty()) {
                    tempClass.setTeacher(tempTeachers.get(0));
                    /* tempTeachers.remove(0).addUsedHours(subject.getWorkload()); */
                } else {
                    System.out.println("Precisa contratar um professor para " + subject.getName());
                }

                tempClasses.add(tempClass);

                /*
                 * for (Student student : studentsToAlloc) {
                 * student.addUsedHours(subject.getWorkload());
                 * }
                 */
            }

            /*
             * tempClasses.forEach(c -> {
             * c.getTeacher().addUsedHours(subject.getWorkload());
             * });
             * tempClasses.forEach(c -> {
             * c.getStudents().forEach(s -> s.addUsedHours(subject.getWorkload()));
             * });
             */
            classes.addAll(tempClasses);
        }
    }

    /**
     * Export / Salva em arquivo TXT
     */
    public void exportStudents() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("ExportStudents.txt"))) {
            String stream = students.stream().map(Student::toString).collect(Collectors.joining("\n\n"));
            writer.write(stream);
        } catch (IOException e) {
            throw new IOException("Ocorreu algum erro ao exportar o arquivo :", e);
        }
    }

    public void exportTeachers() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("ExportTeacher.txt"))) {
            String stream = teachers.stream().map(Teacher::toString).collect(Collectors.joining("\n\n"));
            writer.write(stream);
        } catch (IOException e) {
            throw new IOException("Ocorreu algum erro ao exportar o arquivo :", e);
        }
    }

    public void export() throws IOException {
        this.exportStudents();
        this.exportTeachers();
    }

    /**
     * toString
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();

        s.append("\n-> Professores :\n");

        for (Teacher teacher : teachers) {
            s.append("\n" + teacher.toString() + "\n");
        }

        s.append("\n-> Alunos :\n");

        for (Student student : students) {
            s.append("\n" + student.toString() + "\n");
        }

        s.append("\n-> Disciplinas :\n");

        for (Subject subject : subjects) {
            s.append("\n" + subject.toString() + "\n");
        }

        s.append("\n-> Turmas :\n");

        for (Class clazz : classes) { // Java entende class como uma palavra dele
            s.append("\n" + clazz.toString() + "\n");
        }
        return s.toString();
    }
}