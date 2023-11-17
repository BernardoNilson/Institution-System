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

public class Interface {

    private JFrame frame;
    private Program program;

    // Construtor da Interface
    public Interface() {
        program = new Program();

        frame = new JFrame("Sistema de Gerenciamento da Instituição");
        // frame.setSize(900, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = frame.getContentPane();
        container.add(createMainPanel());

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // Panels
    private JPanel createMainPanel() {
        // Cria o painel principal
        JPanel mainPanel = panelWithBoxLayout();

        // Cria o painel central
        JPanel centerPanel = createCenterPanel();
        mainPanel.add(centerPanel);

        // Cria o painel inferior
        JPanel bottomPanel = createBottomPanel();
        mainPanel.add(bottomPanel);

        return mainPanel;
    }

    private JPanel createStudentPanel() {

        // Cadastro de aluno
        JPanel panelStudent = panelWithBoxLayout();

        panelStudent.add(panelWithDescription("CADASTRO DE ALUNO"));

        // Nome
        JPanel panelStudentName = new JPanel(new FlowLayout());
        panelStudentName.add(label("Nome:"));
        JTextField textStudentName = new JTextField(15);
        panelStudentName.add(textStudentName);
        panelStudent.add(panelStudentName);

        // Matrícula
        JPanel panelStudentId = new JPanel(new FlowLayout());
        panelStudentId.add(label("Matrícula:"));
        JTextField textStudentId = new JTextField(5);
        panelStudentId.add(textStudentId);
        panelStudent.add(panelStudentId);

        // Botão para cadastro de aluno
        JPanel panelStudentCreate = new JPanel(new FlowLayout());
        JButton buttonCreateStudent = new JButton("Cadastrar aluno");
        buttonCreateStudent.addActionListener(e -> {
            boolean result = program.createStudent(textStudentName.getText(),
                    Integer.parseInt(textStudentId.getText()));

            resultMessage(result);

            textStudentName.setText("");
            textStudentId.setText("");
        });
        panelStudentCreate.add(buttonCreateStudent);
        panelStudent.add(panelStudentCreate);

        // Alocação de alunos em disciplinas
        JPanel panelAllocStudent = createAllocToSubjectPanel();

        panelStudent.add(panelAllocStudent);

        return panelStudent;
    }

    private JPanel createAllocToSubjectPanel() {
        // Alocação de alunos em disciplinas
        JPanel panelAllocStudent = panelWithBoxLayout();

        panelAllocStudent.add(panelWithDescription("QUAL A DISCIPLINA O ALUNO DESEJA?"));

        // Matrícula do Aluno
        JPanel panelAllocStudentName = new JPanel();
        panelAllocStudentName.add(label("Matrícula do Aluno:"));

        JTextField textStudentAllocId = new JTextField(5);
        panelAllocStudentName.add(textStudentAllocId);
        panelAllocStudent.add(panelAllocStudentName);

        // Código da Disciplina
        JPanel panelAllocSubjectId = new JPanel();
        panelAllocSubjectId.add(label("Código da Disciplina:"));

        JTextField textSubjectId = new JTextField(5);
        panelAllocSubjectId.add(textSubjectId);
        panelAllocStudent.add(panelAllocSubjectId);

        // Botão para alocar aluno em disciplina
        JPanel panelAllocStudentButton = new JPanel();
        JButton buttonAllocStudent = new JButton("Adicionar");
        buttonAllocStudent.addActionListener(e -> {
            int studentId = Integer.parseInt(textStudentAllocId.getText());
            int subjectId = Integer.parseInt(textSubjectId.getText());

            boolean result = program.addSubjectToStudant(subjectId, studentId);

            resultMessage(result);

            textStudentAllocId.setText("");
            textSubjectId.setText("");
        });
        panelAllocStudentButton.add(buttonAllocStudent);
        panelAllocStudent.add(panelAllocStudentButton);

        return panelAllocStudent;
    }

    private JPanel createTeacherPanel() {
        // Cadastro de professor
        JPanel panelTeacher = new JPanel();
        panelTeacher.setLayout(new BoxLayout(panelTeacher, BoxLayout.Y_AXIS));

        panelTeacher.add(panelWithDescription("CADASTRO DE PROFESSOR"));

        // Nome
        JPanel panelTeacherName = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelTeacherName.add(label("Nome:"));

        JTextField textTeacherName = new JTextField(15);
        panelTeacherName.add(textTeacherName);
        panelTeacher.add(panelTeacherName);

        // Matrícula
        JPanel panelTeacherId = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelTeacherId.add(label("Matrícula:"));

        JTextField textTeacherId = new JTextField(5);
        panelTeacherId.add(textTeacherId);
        panelTeacher.add(panelTeacherId);

        // Formação
        JPanel panelTeacherDegree = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelTeacherDegree.add(label("Formação:"));

        JComboBox<Degree> teacherBoxDegree = new JComboBox<>(Degree.values());
        panelTeacherDegree.add(teacherBoxDegree);
        panelTeacher.add(panelTeacherDegree);

        // Botão de cadastro de professor
        JPanel panelTeacherCreate = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton buttonCreateTeacher = new JButton("Cadastrar professor");
        buttonCreateTeacher.addActionListener(e -> {
            boolean result = program.createTeacher(textTeacherName.getText(),
                    Integer.parseInt(textTeacherId.getText()), (Degree) teacherBoxDegree.getSelectedItem());

            resultMessage(result);

            textTeacherName.setText("");
            textTeacherId.setText("");
        });
        panelTeacherCreate.add(buttonCreateTeacher);
        panelTeacher.add(panelTeacherCreate);
        return panelTeacher;
    }

    private JPanel createSubjectPanel() {
        // Cadastro de professor
        JPanel panelSubject = panelWithBoxLayout();

        panelSubject.add(panelWithDescription("CADASTRO DE DISCIPLINA"));

        // Nome
        JPanel panelSubjectName = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelSubjectName.add(label("Nome:"));

        JTextField textSubjectName = new JTextField(15);
        panelSubjectName.add(textSubjectName);
        panelSubject.add(panelSubjectName);

        // Matrícula
        JPanel panelSubjectId = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelSubjectId.add(label("Código:"));

        JTextField textSubjectId = new JTextField(5);
        panelSubjectId.add(textSubjectId);
        panelSubject.add(panelSubjectId);

        // Descrição
        JPanel panelSubjectDescription = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelSubjectDescription.add(label("Descrição:"));

        JTextArea textAreaSubjectDescription = new JTextArea(2, 10);
        panelSubjectDescription.add(textAreaSubjectDescription);
        panelSubject.add(panelSubjectDescription);

        // Quantidade máxima de alunos
        JPanel panelSubjectMaxStudents = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelSubjectMaxStudents.add(label("Quantidade máxima de alunos:"));

        JTextField textSubjectMaxStudents = new JTextField(5);
        panelSubjectMaxStudents.add(textSubjectMaxStudents);
        panelSubject.add(panelSubjectMaxStudents);

        // Carga horária semanal
        JPanel panelSubjectWorkload = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelSubjectWorkload.add(label("Carga horária semanal:"));

        JTextField textSubjectWorkload = new JTextField(5);
        panelSubjectWorkload.add(textSubjectWorkload);
        panelSubject.add(panelSubjectWorkload);

        // Formação necessária
        JPanel panelSubjectDegree = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelSubjectDegree.add(label("Formação necessária:"));

        JComboBox<Degree> subjectBoxDegree = new JComboBox<>(Degree.values());
        panelSubjectDegree.add(subjectBoxDegree);
        panelSubject.add(panelSubjectDegree);

        // Botão de cadastro de disciplina
        JPanel panelSubjectCreate = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton buttonCreateSubject = new JButton("Cadastrar disciplina");
        buttonCreateSubject.addActionListener(e -> {

            String name = textSubjectName.getText();
            int id = Integer.parseInt(textSubjectId.getText());
            String description = textAreaSubjectDescription.getText();
            int maxStudents = Integer.parseInt(textSubjectMaxStudents.getText());
            int workload = Integer.parseInt(textSubjectWorkload.getText());
            Degree requiredDegree = (Degree) subjectBoxDegree.getSelectedItem();

            boolean result = program.createSubject(id, name, description, maxStudents, workload, requiredDegree);

            resultMessage(result);

            textSubjectName.setText("");
            textSubjectId.setText("");
            textAreaSubjectDescription.setText("");
            textSubjectMaxStudents.setText("");
            textSubjectWorkload.setText("");
        });

        panelSubjectCreate.add(buttonCreateSubject);
        panelSubject.add(panelSubjectCreate);

        return panelSubject;
    }

    private JPanel createCenterPanel() {
        // Cria o painel central
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(0, 3));

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

    private JPanel createButtonPanel(JComboBox<Subject> subjectBox, JComboBox<Teacher> teacherBox,
            JComboBox<Student> studentBox) {
        // Cria o botão de Refresh
        JPanel buttonPanel = new JPanel();
        JButton buttonRefresh = new JButton("Refresh");
        buttonRefresh.addActionListener(e -> updateView(subjectBox, teacherBox, studentBox));
        buttonPanel.add(buttonRefresh);

        // Cria o botão de exportação de dados .txt
        JButton buttonExport = new JButton("Exportar Dados");
        buttonExport.addActionListener(e -> {
            try {
                boolean exportSuccess = program.export();
                if (exportSuccess)
                    JOptionPane.showMessageDialog(null, "Seus arquivos estão disponíveis no seu diretório atual",
                            "Exportação Concluída", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception exc) {
                JOptionPane.showMessageDialog(null, "Ocorreu um erro ao exportar os dados.", "Erro de Exportação",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
        buttonPanel.add(buttonExport);

        // Cria o botão de alocação de turmas
        JButton buttonAlloc = new JButton("Alocar em turmas");
        buttonAlloc.addActionListener(e -> {
            boolean result = program.allocAllToClasses();
            System.out.println(program.toString());
            resultMessage(result);
        });
        buttonPanel.add(buttonAlloc);

        return buttonPanel;
    }

    private JPanel createViewPanel(JComboBox<Subject> subjectBox, JComboBox<Teacher> teacherBox,
            JComboBox<Student> studentBox) {
        JPanel viewPanel = new JPanel();
        viewPanel.setLayout(new GridLayout(3, 2));

        // Botão para visualizar detalhes de uma disciplina
        viewPanel.add(subjectBox);

        JButton buttonViewSubjectDetails = new JButton("Visualizar detalhes da disciplina");
        buttonViewSubjectDetails.addActionListener(e -> {
            // Obtém a disciplina selecionada
            Subject subject = (Subject) subjectBox.getSelectedItem();

            // Chama a função para visualizar os detalhes da disciplina
            String details = program.viewSubjectDetails(subject);

            // Exibe a mensagem
            JOptionPane.showMessageDialog(null, details, "Detalhes da disciplina", JOptionPane.INFORMATION_MESSAGE);
        });
        viewPanel.add(buttonViewSubjectDetails);

        // Botão para visualizar detalhes de um professor
        viewPanel.add(teacherBox);

        JButton buttonViewTeacherDetails = new JButton("Visualizar detalhes do professor");
        buttonViewTeacherDetails.addActionListener(e -> {
            // Obtém o professor selecionado
            Teacher selectedTeacher = (Teacher) teacherBox.getSelectedItem();

            // Chama a função para visualizar os detalhes do professor
            String details = program.viewTeacherDetails(selectedTeacher);

            // Exibe a mensagem
            JOptionPane.showMessageDialog(null, details, "Detalhes do professor", JOptionPane.INFORMATION_MESSAGE);
        });
        viewPanel.add(buttonViewTeacherDetails);

        // Botão para visualizar detalhes de um aluno
        viewPanel.add(studentBox);

        JButton buttonViewStudentDetails = new JButton("Visualizar detalhes do aluno");
        buttonViewStudentDetails.addActionListener(e -> {
            // Obtém o aluno selecionado
            Student selectedStudent = (Student) studentBox.getSelectedItem();

            // Chama a função para visualizar os detalhes do aluno
            String details = program.viewStudentDetails(selectedStudent);

            // Exibe a mensagem
            JOptionPane.showMessageDialog(null, details, "Detalhes do aluno", JOptionPane.INFORMATION_MESSAGE);
        });
        viewPanel.add(buttonViewStudentDetails);
        return viewPanel;
    }

    private JPanel createBottomPanel() {
        // Cria o painel inferior
        JPanel bottomPanel = panelWithBoxLayout();

        bottomPanel.add(panelWithDescription("VISUALIZAÇÃO DAS INFORMAÇÕES"));

        JComboBox<Subject> subjectBox = new JComboBox<>(program.getSubjects().toArray(new Subject[0])); // É o mesmo que
                                                                                                        // (Subject[])
                                                                                                        // program.getSubjects().toArray()
        JComboBox<Teacher> teacherBox = new JComboBox<>(program.getTeachers().toArray(new Teacher[0]));
        JComboBox<Student> studentBox = new JComboBox<>(program.getStudents().toArray(new Student[0]));

        // Cria o painel de visualização
        JPanel viewPanel = createViewPanel(subjectBox, teacherBox, studentBox);
        bottomPanel.add(viewPanel);

        // Cria o botão de Refresh
        JPanel buttonPanel = createButtonPanel(subjectBox, teacherBox, studentBox);

        bottomPanel.add(buttonPanel);

        return bottomPanel;
    }

    // Utilities
    private JPanel panelWithDescription(String desc) {
        JPanel panel = new JPanel();
        JLabel label = new JLabel(desc);
        panel.add(label);
        return panel;
    }

    private JPanel panelWithBoxLayout() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        return panel;
    }

    private JLabel label(String text) {
        return new JLabel(text);
    }

    // Mensagem de resultado
    private void resultMessage(boolean result) {
        JOptionPane.showMessageDialog(null,
                result ? "Operação bem sucedida!" : "Falha durante a operação, verifique as informações!", "Mensagem",
                JOptionPane.INFORMATION_MESSAGE);
    }

    // Atualiza as visualizações de dados
    private void updateSubjectView(JComboBox<Subject> subjectBox) {
        // Obtém os objetos mais recentes
        List<Subject> subjects = program.getSubjects();

        // Atualiza os JComboBox
        subjectBox.setModel(new DefaultComboBoxModel<>(subjects.toArray(new Subject[0])));
    }

    private void updateTeacherView(JComboBox<Teacher> teacherBox) {
        // Obtém os objetos mais recentes
        List<Teacher> teachers = program.getTeachers();

        // Atualiza os JComboBox
        teacherBox.setModel(new DefaultComboBoxModel<>(teachers.toArray(new Teacher[0])));
    }

    private void updateStudentView(JComboBox<Student> studentBox) {
        // Obtém os objetos mais recentes
        List<Student> students = program.getStudents();

        // Atualiza os JComboBox
        studentBox.setModel(new DefaultComboBoxModel<>(students.toArray(new Student[0])));
    }

    public void updateView(JComboBox<Subject> subjectBox, JComboBox<Teacher> teacherBox,
            JComboBox<Student> studentBox) {
        updateSubjectView(subjectBox);
        updateTeacherView(teacherBox);
        updateStudentView(studentBox);
        frame.repaint();
    }

    // Fecha a Interface
    public void close () {
        frame.dispose();
    }
}