import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

public class Interface {

    private JFrame frame;
    private Program program;

    public Interface() {
        program = new Program();

        frame = new JFrame("Sistema de Gerenciamento da Instituição");
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = frame.getContentPane();
        container.add(createMainPanel());

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private JPanel createMainPanel() {
        //  Cria o painel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Cria o painel central
        JPanel centerPanel = createCenterPanel();
        mainPanel.add(centerPanel);
        
        // Cria o painel inferior
        JPanel bottomPanel = createBottomPanel();
        mainPanel.add(bottomPanel);

        return mainPanel;
    }

    public JPanel createStudentPanel() {
         // Cadastro de aluno
        JPanel panelStudent = new JPanel();
        panelStudent.setLayout(new FlowLayout(FlowLayout.LEFT));

        JPanel panel = new JPanel();
        JLabel label = new JLabel("CADASTRO DE ALUNO");
        panel.add(label);
        panelStudent.add(panel);

        // Nome
        JLabel labelStudentName = new JLabel("Nome:");
        panelStudent.add(labelStudentName);
        JTextField textFieldStudentName = new JTextField(15);
        panelStudent.add(textFieldStudentName);

        // Matrícula
        JLabel labelStudentId = new JLabel("Matrícula:");
        panelStudent.add(labelStudentId);
        JTextField textFieldStudentId = new JTextField(5);
        panelStudent.add(textFieldStudentId);

        // Botão para cadastro de aluno
        JButton buttonCreateStudent = new JButton("Cadastrar aluno");
        buttonCreateStudent.addActionListener(e -> {
            boolean result = program.createStudent(textFieldStudentName.getText(), Integer.parseInt(textFieldStudentId.getText()));
            
            resultMessage(result);
            
            textFieldStudentName.setText("");
            textFieldStudentId.setText("");
        });
        panelStudent.add(buttonCreateStudent);
        return panelStudent;
    }

    public JPanel createTeacherPanel() {
        // Cadastro de professor
        JPanel panelTeacher = new JPanel();
        panelTeacher.setLayout(new BoxLayout(panelTeacher, BoxLayout.Y_AXIS));

        JPanel panel = new JPanel();
        JLabel label = new JLabel("CADASTRO DE PROFESSOR");
        panel.add(label);
        panelTeacher.add(panel);

        // Nome
        JLabel labelTeacherName = new JLabel("Nome:");
        panelTeacher.add(labelTeacherName);
        JTextField textFieldTeacherName = new JTextField(15);
        panelTeacher.add(textFieldTeacherName);

        // Matrícula
        JLabel labelTeacherId = new JLabel("Matrícula:");
        panelTeacher.add(labelTeacherId);
        JTextField textFieldTeacherId = new JTextField(5);
        panelTeacher.add(textFieldTeacherId);

        // Formação
        JLabel labelTeacherDegree = new JLabel("Formação:");
        panelTeacher.add(labelTeacherDegree);
        JComboBox<Degree> selectTeacherDegree = new JComboBox<>(Degree.values());
        panelTeacher.add(selectTeacherDegree);

        //  Botão de cadastro de professor
        JButton buttonCreateTeacher = new JButton("Cadastrar professor");
        buttonCreateTeacher.addActionListener(e -> {
            boolean result = program.createTeacher(textFieldTeacherName.getText(), Integer.parseInt(textFieldTeacherId.getText()), (Degree) selectTeacherDegree.getSelectedItem());
            
            resultMessage(result);
            
            textFieldTeacherName.setText("");
            textFieldTeacherId.setText("");
        });
        panelTeacher.add(buttonCreateTeacher);
        return panelTeacher;
    }

    public JPanel createSubjectPanel() {
        // Cadastro de professor
        JPanel panelSubject = new JPanel();
        panelSubject.setLayout(new BoxLayout(panelSubject, BoxLayout.Y_AXIS));

        JPanel panel = new JPanel();
        JLabel label = new JLabel("CADASTRO DE DISCIPLINA");
        panel.add(label);
        panelSubject.add(panel);

        // Nome
        JLabel labelSubjectName = new JLabel("Nome:");
        panelSubject.add(labelSubjectName);
        JTextField textFieldSubjectName = new JTextField(15);
        panelSubject.add(textFieldSubjectName);

        // Matrícula
        JLabel labelSubjectId = new JLabel("Código:");
        panelSubject.add(labelSubjectId);
        JTextField textFieldSubjectId = new JTextField(5);
        panelSubject.add(textFieldSubjectId);

        // Descrição
        JLabel labelSubjectDescription = new JLabel("Descrição:");
        panelSubject.add(labelSubjectDescription);
        JTextArea textAreaSubjectDescription = new JTextArea(2, 10);
        panelSubject.add(textAreaSubjectDescription);

        // Quantidade máxima de alunos
        JLabel labelSubjectMaxStudents = new JLabel("Quantidade máxima de alunos:");
        panelSubject.add(labelSubjectMaxStudents);
        JTextField textFieldSubjectMaxStudents = new JTextField(5);
        panelSubject.add(textFieldSubjectMaxStudents);

        // Carga horária semanal
        JLabel labelSubjectWorkload = new JLabel("Carga horária semanal:");
        panelSubject.add(labelSubjectWorkload);
        JTextField textFieldSubjectWorkload = new JTextField(5);
        panelSubject.add(textFieldSubjectWorkload);

        // Formação necessária
        JLabel labelSubjectDegree = new JLabel("Formação necessária para o professor:");
        panelSubject.add(labelSubjectDegree);
        JComboBox<Degree> selectSubjectDegree = new JComboBox<>(Degree.values());
        panelSubject.add(selectSubjectDegree);

        //  Botão de cadastro de disciplina
        JButton buttonCreateSubject = new JButton("Cadastrar disciplina");
        buttonCreateSubject.addActionListener(e -> {
            
            String name = textFieldSubjectName.getText();
            int id = Integer.parseInt(textFieldSubjectId.getText());
            String description = textAreaSubjectDescription.getText();
            int maxStudents = Integer.parseInt(textFieldSubjectMaxStudents.getText());
            int workload = Integer.parseInt(textFieldSubjectWorkload.getText());
            Degree requiredDegree = (Degree) selectSubjectDegree.getSelectedItem();

            boolean result = program.createSubject(id, name, description, maxStudents, workload, requiredDegree);

            resultMessage(result);

            textFieldSubjectName.setText("");
            textFieldSubjectId.setText("");
            textAreaSubjectDescription.setText("");
            textFieldSubjectMaxStudents.setText("");
            textFieldSubjectWorkload.setText("");
        });
        panelSubject.add(buttonCreateSubject);
        return panelSubject;
    }

    public JPanel createCenterPanel() {
        // Cria o painel central
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(1, 3));

        // Cadastro de aluno
        JPanel panelStudent = createStudentPanel();
        centerPanel.add(panelStudent);

        // Cadastro de professor
        JPanel panelteachers = createTeacherPanel();
        centerPanel.add(panelteachers);

        // Cadastro de disciplinas
        JPanel panelSubject = createSubjectPanel();
        centerPanel.add(panelSubject);
        return centerPanel;
    }

    public JPanel createBottomPanel() {
        // Cria o painel inferior
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));

        JPanel viewPanel = new JPanel();
        viewPanel.setLayout(new GridLayout(1, 3));

        // Botão para visualizar detalhes de uma disciplina
        JComboBox<Subject> selectSubject = new JComboBox<>(program.getSubjects().toArray(new Subject[0])); // É o mesmo que (Subject[]) program.getSubjects().toArray()
        viewPanel.add(selectSubject);

        JButton buttonViewSubjectDetails = new JButton("Visualizar detalhes de uma disciplina");
        buttonViewSubjectDetails.addActionListener(e -> {
            // Obtém a disciplina selecionada
            Subject subject = (Subject) selectSubject.getSelectedItem();

            // Chama a função para visualizar os detalhes da disciplina
            String details = program.viewSubjectDetails(subject);

            // Exibe a mensagem
            JOptionPane.showMessageDialog(null, details, "Detalhes da disciplina", JOptionPane.INFORMATION_MESSAGE);
        });
        viewPanel.add(buttonViewSubjectDetails);

        // Botão para visualizar detalhes de um professor
        JComboBox<Teacher> selectTeacher = new JComboBox<>(program.getTeachers().toArray(new Teacher[0]));
        viewPanel.add(selectTeacher);

        JButton buttonViewTeacherDetails = new JButton("Visualizar detalhes de um professor");
        buttonViewTeacherDetails.addActionListener(e -> {
            // Obtém o professor selecionado
            Teacher selectedTeacher = (Teacher) selectTeacher.getSelectedItem();

            // Chama a função para visualizar os detalhes do professor
            String details = program.viewTeacherDetails(selectedTeacher);

            // Exibe a mensagem
            JOptionPane.showMessageDialog(null, details, "Detalhes do professor", JOptionPane.INFORMATION_MESSAGE);
        });
        viewPanel.add(buttonViewTeacherDetails);

        // Botão para visualizar detalhes de um aluno
        JComboBox<Student> selectStudent = new JComboBox<>(program.getStudents().toArray(new Student[0]));
        viewPanel.add(selectStudent);

        JButton buttonViewStudentDetails = new JButton("Visualizar detalhes de um aluno");
        buttonViewStudentDetails.addActionListener(e -> {
            // Obtém o aluno selecionado
            Student selectedStudent = (Student) selectStudent.getSelectedItem();

            // Chama a função para visualizar os detalhes do aluno
            String details = program.viewStudentDetails(selectedStudent);

            // Exibe a mensagem
            JOptionPane.showMessageDialog(null, details, "Detalhes do aluno", JOptionPane.INFORMATION_MESSAGE);
        });
        viewPanel.add(buttonViewStudentDetails);

        bottomPanel.add(viewPanel);

        //  Cria o botão de Refresh
        JPanel refreshPanel = new JPanel();
        JButton buttonRefresh = new JButton("Refresh");
        buttonRefresh.addActionListener(e -> updateView(selectSubject, selectTeacher, selectStudent));
        refreshPanel.add(buttonRefresh);
        bottomPanel.add(refreshPanel);

        return bottomPanel;
    }

    public void resultMessage(boolean result) {
        JOptionPane.showMessageDialog(null, result ? "Sucesso no cadastro!" : "Falha durante o cadastro, verifique as informações!", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
    }

    public void updateSubjectView(JComboBox<Subject> selectSubject) {
        // Obtém os objetos mais recentes
        List<Subject> subjects = program.getSubjects();

        // Atualiza os JComboBox
        selectSubject.setModel(new DefaultComboBoxModel<>(subjects.toArray(new Subject[0])));
    }

    public void updateTeacherView(JComboBox<Teacher> selectTeacher) {
        // Obtém os objetos mais recentes
        List<Teacher> teachers = program.getTeachers();

        // Atualiza os JComboBox
        selectTeacher.setModel(new DefaultComboBoxModel<>(teachers.toArray(new Teacher[0])));
    }

    public void updateStudentView(JComboBox<Student> selectStudent) {
        // Obtém os objetos mais recentes
        List<Student> students = program.getStudents();

        // Atualiza os JComboBox
        selectStudent.setModel(new DefaultComboBoxModel<>(students.toArray(new Student[0])));
    }

    public void updateView(JComboBox<Subject> selectSubject, JComboBox<Teacher> selectTeacher, JComboBox<Student> selectStudent){
        updateSubjectView(selectSubject);
        updateTeacherView(selectTeacher);
        updateStudentView(selectStudent);
        frame.repaint();
    }

}