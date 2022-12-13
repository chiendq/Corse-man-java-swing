package a2_1901040037.views.frames.enrollment;

import a2_1901040037.models.Student;
import a2_1901040037.models.module.Module;
import a2_1901040037.models.module.ModuleDb;
import a2_1901040037.repositories.EnrollmentRepository;
import a2_1901040037.repositories.ModuleRepository;
import a2_1901040037.repositories.StudentRepository;
import a2_1901040037.views.frames.BaseFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class AddEnrollmentFrame extends BaseFrame {
    private JLabel studentLabel;
    private JLabel moduleLabel;
    private JLabel internalMarkLabel;
    private JTextField internalMarkTextField;
    private JLabel examLabel;
    private JTextField examMarkTextField;

    private EnrollmentRepository enrollmentRepository;

    private StudentRepository studentRepository;
    private ModuleRepository moduleRepository;
    private JComboBox<String> studentComboBox;

    private JComboBox<String> moduleComboBox;


    public AddEnrollmentFrame() {
        super();
        studentRepository = new StudentRepository();
        moduleRepository = new ModuleRepository();
        enrollmentRepository = new EnrollmentRepository();
        initUI();
    }

    private void initUI() {
        JPanel formPanel = new JPanel();
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GroupLayout layout = new GroupLayout(formPanel);
        formPanel.setLayout(layout);
        layout.setAutoCreateGaps(true);

        JLabel titleLabel = new JLabel("Enter enrollment detail", SwingConstants.CENTER);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        studentLabel = new JLabel("Student ID:");
        String[] studentIds = studentRepository.getAll().stream().map(Student::getId).toList().toArray(new String[0]);
        studentComboBox = new JComboBox<>(studentIds);
        moduleLabel = new JLabel("Module ID:");
        String[] moduleCodes = moduleRepository.getAll().stream().map(ModuleDb::getCode).toList().toArray(new String[0]);
        moduleComboBox = new JComboBox<>(moduleCodes);
        internalMarkLabel = new JLabel("Internal mark:");
        internalMarkTextField = new JTextField();
        examLabel = new JLabel("Exam mark:");
        examMarkTextField = new JTextField();

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(this::saveEnrollment);
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> this.setVisible(false));

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(studentLabel)
                        .addComponent(moduleLabel)
                        .addComponent(internalMarkLabel)
                        .addComponent(examLabel)
                        .addComponent(saveButton)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(studentComboBox)
                        .addComponent(moduleComboBox)
                        .addComponent(internalMarkTextField)
                        .addComponent(examMarkTextField)
                        .addComponent(cancelButton)
                )
        );

        // Vertically, we want to align each label with his textfield
        // on the baseline of the components
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(studentLabel)
                        .addComponent(studentComboBox))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(moduleLabel)
                        .addComponent(moduleComboBox))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(internalMarkLabel)
                        .addComponent(internalMarkTextField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(examLabel)
                        .addComponent(examMarkTextField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(saveButton)
                        .addComponent(cancelButton)
                )
        );

        this.add(titleLabel, BorderLayout.NORTH);
        this.add(formPanel, BorderLayout.CENTER);

    }

    private void saveEnrollment(ActionEvent actionEvent) {
        String studentId = (String) studentComboBox.getSelectedItem();
        String moduleId = (String) moduleComboBox.getSelectedItem();
        try{
            float internalMark = Float.parseFloat(internalMarkTextField.getText()) ;
            float examMark = Float.parseFloat(examMarkTextField.getText()) ;

            EnrollmentCreation enrollment = new EnrollmentCreation(
                    studentId,
                    moduleId,
                    internalMark,
                    examMark
            );

            enrollmentRepository.saveOne(enrollment);

            JFrame frame = new JFrame("Success");
            JOptionPane.showMessageDialog(frame,
                    "Save successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
            this.setVisible(false);
        }catch (NumberFormatException numberFormatException){
            JFrame frame = new JFrame("Error");
            JOptionPane.showMessageDialog(frame,
                    "Mark must be integer or double",
                    "Errors",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void clearInputData(){
        studentComboBox.setSelectedIndex(0);
        moduleComboBox.setSelectedIndex(0);
        internalMarkTextField.setText("");
        examMarkTextField.setText("");
    }

    @Override
    public synchronized void addComponentListener(ComponentListener l) {
        super.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentHidden(ComponentEvent e) {
                clearInputData();
            }
        });
    }
}
