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
    public void allocAllToClasses() {
        // Para cada disciplina criada
        for (Subject subject : subjects) {

            // Verificamos os professores, dentro todos cadastrados, que pode dar aula para aquela disciplina
            // e possui horas disponiveis para assumir uma nova turma. No fim, cria-se um ArrayList e atribui à variável
            List<Teacher> tempTeachers = availableTeachersForSubject(subject);
            

            // Verificamos os alunos, dentro todos cadastrados, que desejam entrar naquela disiciplina
            // e possui horas disponiveis para assumir tal turma. No fim, cria-se um ArrayList e atribui à variável
            List<Student> tempStudents = availableStudentsForSubject(subject);
            
            // Cria-se um ArrayList para as turmas que v'ao ser criadas para a disciplina
            List<Class> tempClasses = new ArrayList<>();

            // Aqui, calculamos a quantidade de turmas, a quantidade de alunos por turma e quantos sobram (corrigimos a sobra ao adicionar os aluno nas turmas)
            int classQuantity = howManyClasses(tempStudents, subject);
            int studentsPerClass = tempStudents.size() / classQuantity;
            int remainder = tempStudents.size() % classQuantity;
            
            System.out.println("\nQuantos alunos? " + tempStudents.size());
            System.out.println("Quantas turmas? " + classQuantity);
            System.out.println("Quantos alunos por turma? " + studentsPerClass);
            System.out.println("Quantos alunos sobraram? " + remainder);

            for (int i = 0; i < classQuantity; i++) {
                int studentsQuantity = studentsPerClass;
                if (remainder > 0) { // Se ainda temos alunos sobrando, adicionamos um a mais nessa leva e retiramos da quantidade sobrando
                    studentsQuantity++;
                    remainder--;
                }

                // Pegamos os primeiros estudantes da lista
                List<Student> studentsToAlloc = tempStudents.subList(0, studentsQuantity);
               
                // Esse Stram é basicamente um tempStudents.removeAll(studentsToAlloc)
                // Tive que transformar em Stream pois gerava um erro de Concurrent Modification
                tempStudents = tempStudents.stream()
                        .filter(student -> !studentsToAlloc.contains(student))
                        .collect(Collectors.toList());

                // Por fim, criamos a turma com os estudantes
                Class tempClass = new Class(subject, studentsToAlloc);

                // DEPURAÇÃO //
                System.out.println("Estamos criando as turmas para a disciplina de " + subject.getName());
                System.out.println("Selecionamos os professores:\n" + tempTeachers.toString());
                System.out.println("Selecionamos os alunos:\n" + studentsToAlloc.toString());
                System.out.println("Sobraram os alunos para as próximas turmas:\n" + tempStudents.toString());

                // Se ainda houverem professores, alocamos ele na turma e retiramos da lista temporária
                setTeacherFromListToClass(tempTeachers, tempClass, subject);

                // Adicionamos no Array das turmas da disciplina
                tempClasses.add(tempClass);

                // Atualiza a carga horária dos estudantes
                for (Student student : studentsToAlloc) {
                    student.addUsedHours(subject.getWorkload());
                }
            }

            // Adicionamos de fato na lista do nosso programa
            classes.addAll(tempClasses);
        }
    }

    public int howManyClasses(List<Student> students, Subject subject) {
        return (students.size() / subject.getMaxStudents()) + 1;
    }

    public void setTeacherFromListToClass(List<Teacher> teachers, Class clazz, Subject subject) {
        if (!teachers.isEmpty()) {
            clazz.setTeacher(teachers.get(0));
            teachers.remove(0).addUsedHours(subject.getWorkload());
        } else {
            System.out.println("Precisa contratar um professor para " + subject.getName());
        }
    }
    
    public List<Teacher> availableTeachersForSubject(Subject subject){
        List<Teacher> listOfTeachers = new ArrayList<>();

        for (Teacher teacher : teachers) {
            if (teacher.getDegree() == subject.getRequiredDegree()
                    && teacher.getUsedHours() + subject.getWorkload() <= teacher.getMaxHours()) {
                listOfTeachers.add(teacher);
            }
        }

        return listOfTeachers;
    }

    public List<Student> availableStudentsForSubject(Subject subject){
        List<Student> listOfStudents = new ArrayList<>();

        for (Student student : students) {
            if (student.getSubjects().contains(subject)
                    && student.getUsedHours() + subject.getWorkload() <= student.getMaxHours()) {
                listOfStudents.add(student);
            }
        }

        return listOfStudents;
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

        for (Class clazz : classes) { // Java entende class como uma palavra dele :)
            s.append("\n" + clazz.toString() + "\n");
        }
        return s.toString();
    }


    public String viewSubjectDetails(Subject subject){
        return subject.getName();
    }

    public String viewTeacherDetails(Teacher teacher){
        return teacher.getName();
    }

    public String viewStudentDetails(Student student){
        return student.getName();
    }
}