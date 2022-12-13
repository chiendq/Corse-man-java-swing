package a2_1901040037.views.frames.student;

import a2_1901040037.models.Student;
import a2_1901040037.repositories.StudentRepository;
import a2_1901040037.views.frames.BaseFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import static a2_1901040037.utils.Utils.CURRENT_YEAR;
import static a2_1901040037.utils.Validator.validateDate_DD_MM_YYYY;
import static a2_1901040037.utils.Validator.validateEmail;


public class AddStudentFrame extends BaseFrame {

    private StudentRepository studentRepository;

    private JTextField studentIdTextField;
    private JTextField nameTextField;
    private JTextField dobTextField;
    private JTextField addressTextField;
    private JTextField emailTextField;


    public AddStudentFrame() {
        super();
        studentRepository = new StudentRepository();
        initUI();
    }

    private void initUI() {
        JPanel formPanel = new JPanel();
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GroupLayout layout = new GroupLayout(formPanel);
        formPanel.setLayout(layout);
        layout.setAutoCreateGaps(true);

        JLabel titleLabel = new JLabel("Enter student details", SwingConstants.CENTER);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel studentIdLabel = new JLabel("Student ID:");
        int total = studentRepository.count();
        String id = "S" + (CURRENT_YEAR + total);
        studentIdTextField = new JTextField(id);
        studentIdTextField.setEnabled(false);
        JLabel nameLabel = new JLabel("Name:");
        nameTextField = new JTextField();
        JLabel dobLabel = new JLabel("Date of birth:");
        dobTextField = new JTextField();
        JLabel addressLabel = new JLabel("Address:");
        addressTextField = new JTextField();
        JLabel emailLabel = new JLabel("Email:");
        emailTextField = new JTextField();


        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(this::saveNewStudent);
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> this.setVisible(false));

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(studentIdLabel)
                        .addComponent(nameLabel)
                        .addComponent(dobLabel)
                        .addComponent(addressLabel)
                        .addComponent(emailLabel)
                        .addComponent(saveButton)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(studentIdTextField)
                        .addComponent(nameTextField)
                        .addComponent(dobTextField)
                        .addComponent(addressTextField)
                        .addComponent(emailTextField)
                        .addComponent(cancelButton)
                )
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(studentIdLabel)
                        .addComponent(studentIdTextField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(nameLabel)
                        .addComponent(nameTextField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(dobLabel)
                        .addComponent(dobTextField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(addressLabel)
                        .addComponent(addressTextField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(emailLabel)
                        .addComponent(emailTextField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(saveButton)
                        .addComponent(cancelButton)
                )
        );

        this.add(titleLabel, BorderLayout.NORTH);
        this.add(formPanel, BorderLayout.CENTER);
    }

    private void saveNewStudent(ActionEvent actionEvent) {
        String nameInput = nameTextField.getText();
        String dobInput = dobTextField.getText();

        String addressInput = addressTextField.getText();
        String emailInput = emailTextField.getText();

        if (!validateDate_DD_MM_YYYY(dobInput)) {
            showErrorDialog("Some thing went wrong!", "Invalid date format! Please using dd/mm/yyyy format.");
            return;
        }

        if (!validateEmail(emailInput)){
            showErrorDialog("Some thing went wrong!", "Invalid date email format!");
            return;
        }

        int total = studentRepository.count();
        String id = "S" + (CURRENT_YEAR + total);
        Student student = new Student(id, nameInput, dobInput, addressInput, emailInput);
        studentRepository.saveOne(student);
        showInforDialog("Save successfully!", "Success");
        this.setVisible(false);
    }

    private void showErrorDialog(String title, String message) {
        JFrame frame = new JFrame("JOptionPane showMessageDialog example");
        JOptionPane.showMessageDialog(frame, message, title, JOptionPane.ERROR_MESSAGE);
    }

    private void showInforDialog(String title, String message) {
        JFrame frame = new JFrame("JOptionPane showMessageDialog example");
        JOptionPane.showMessageDialog(frame, title, message, JOptionPane.INFORMATION_MESSAGE);
    }

    private void clearInputData() {
        nameTextField.setText("");
        dobTextField.setText("");
        addressTextField.setText("");
        emailTextField.setText("");
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
