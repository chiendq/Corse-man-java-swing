package a2_1901040037.views.frames.module;

import a2_1901040037.models.module.Module;
import a2_1901040037.repositories.ModuleRepository;
import a2_1901040037.services.CourseManService;
import a2_1901040037.views.frames.BaseFrame;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.*;
import java.text.NumberFormat;

public class AddModuleFrame extends BaseFrame {
    private JLabel departmentLabel;

    private JTextField departmentTextField;

    private JComboBox<String> moduleTypeComboBox;
    private JLabel codeLabel;
    private JTextField codeTextField;

    private JLabel nameLabel;
    private JTextField nameTextField;
    private JLabel semesterLabel;
    private JSpinner semesterSpinner;
    private JLabel creditsLabel;
    private JSpinner creditsSpinner;

    private ModuleRepository moduleRepository;

    private CourseManService courseManService;

    public AddModuleFrame(){
        super();
        moduleRepository = new ModuleRepository();
        courseManService = new CourseManService();
        initUI();
    }

    private void initUI() {
        JPanel formPanel = new JPanel();
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GroupLayout layout = new GroupLayout(formPanel);
        formPanel.setLayout(layout);
        layout.setAutoCreateGaps(true);

        JLabel titleLabel = new JLabel("Enter module detail", SwingConstants.CENTER);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create the components we will put in the form
        codeLabel = new JLabel("Code:");
        codeTextField = new JTextField(courseManService.generateModuleCode(1));
        codeTextField.setEnabled(false);
        JLabel moduleTypeLabel = new JLabel("Module type:");
        String[] moduleTypes = {"Compulsory", "Elective"};
        moduleTypeComboBox = new JComboBox<>(moduleTypes);
        nameLabel = new JLabel("Name:");
        nameTextField = new JTextField();
        semesterLabel = new JLabel("Semester:");

        SpinnerModel spinnerModel = new SpinnerNumberModel(1,1,10,1);
        semesterSpinner = new JSpinner(spinnerModel);
        ((JSpinner.DefaultEditor) semesterSpinner.getEditor()).getTextField().setEditable(false);
        ((JSpinner.DefaultEditor) semesterSpinner.getEditor()).getTextField().setHorizontalAlignment(JTextField.LEFT);
        semesterSpinner.addChangeListener(e -> {
            int semester = (int) ((JSpinner) e.getSource()).getValue();
            codeTextField.setText(courseManService.generateModuleCode(semester));
        });

        creditsLabel = new JLabel("Credits:");
        creditsSpinner = new JSpinner(spinnerModel);
        ((JSpinner.DefaultEditor) creditsSpinner.getEditor()).getTextField().setEditable(false);
        ((JSpinner.DefaultEditor) creditsSpinner.getEditor()).getTextField().setHorizontalAlignment(JTextField.LEFT);
        departmentLabel = new JLabel("Department name:");
        departmentLabel.setVisible(false);
        departmentTextField = new JTextField();
        departmentTextField.setVisible(false);

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(this::saveModule);
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> this.setVisible(false));

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(codeLabel)
                        .addComponent(moduleTypeLabel)
                        .addComponent(nameLabel)
                        .addComponent(semesterLabel)
                        .addComponent(creditsLabel)
                        .addComponent(saveButton)
                        .addComponent(departmentLabel)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(codeTextField)
                        .addComponent(moduleTypeComboBox)
                        .addComponent(nameTextField)
                        .addComponent(semesterSpinner)
                        .addComponent(creditsSpinner)
                        .addComponent(cancelButton)
                        .addComponent(departmentTextField)
                )
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(codeLabel)
                        .addComponent(codeTextField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(moduleTypeLabel)
                        .addComponent(moduleTypeComboBox))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(nameLabel)
                        .addComponent(nameTextField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(semesterLabel)
                        .addComponent(semesterSpinner))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(creditsLabel)
                        .addComponent(creditsSpinner))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(departmentLabel)
                        .addComponent(departmentTextField)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(saveButton)
                        .addComponent(cancelButton)
                )
        );

        moduleTypeComboBox.addItemListener(this::moduleTypeComboBoxChanged);

        this.add(titleLabel, BorderLayout.NORTH);
        this.add(formPanel, BorderLayout.CENTER);

    }

    private void saveModule(ActionEvent actionEvent) {
        String moduleType = String.valueOf(moduleTypeComboBox.getSelectedItem());

        String name = nameTextField.getText();
        int semester = (Integer) semesterSpinner.getValue();
        int credits = (Integer) creditsSpinner.getValue();
        String departmentName = departmentTextField.getText();
        String code = courseManService.generateModuleCode(semester);
        ModuleCreation module = new ModuleCreation(code,
                                                    name,
                                                    semester,
                                                    departmentName,
                                                    moduleType,
                                                    credits);
        moduleRepository.saveOne(module);

        JFrame frame = new JFrame("JOptionPane showMessageDialog example");
        JOptionPane.showMessageDialog(frame,
                "Save successfully!",
                "Success",
                JOptionPane.INFORMATION_MESSAGE);
        this.setVisible(false);
    }

    public void moduleTypeComboBoxChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            String moduleType = "Elective";
            if(moduleType.equalsIgnoreCase(e.getItem().toString())){
                departmentLabel.setVisible(true);
                departmentTextField.setVisible(true);
            }else{
                departmentLabel.setVisible(false);
                departmentTextField.setVisible(false);
            }
        }
    }

    @Override
    public synchronized void addComponentListener(ComponentListener l) {
        super.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentHidden(ComponentEvent e) {
                resetForm();
            }
        });
    }

    private void resetForm(){
        moduleTypeComboBox.setSelectedIndex(0);
        codeTextField.setText(courseManService.generateModuleCode(1));
        nameTextField.setText("");
        semesterSpinner.setValue(1);
        creditsSpinner.setValue(1);
        departmentTextField.setText("");
        departmentLabel.setVisible(false);
        departmentTextField.setVisible(false);
    }
}
