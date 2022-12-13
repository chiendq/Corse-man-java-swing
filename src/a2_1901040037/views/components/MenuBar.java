package a2_1901040037.views.components;

import a2_1901040037.repositories.EnrollmentRepository;
import a2_1901040037.repositories.ModuleRepository;
import a2_1901040037.repositories.StudentRepository;
import a2_1901040037.views.frames.BaseFrame;
import a2_1901040037.views.frames.DataTableFrame;
import a2_1901040037.views.frames.enrollment.AddEnrollmentFrame;
import a2_1901040037.views.frames.module.AddModuleFrame;
import a2_1901040037.views.frames.student.AddStudentFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static a2_1901040037.utils.Utils.shutDown;

public class MenuBar extends JMenuBar implements ActionListener {
    private JMenu fileMenu;

    private JMenu studentMenu;

    private JMenu moduleMenu;

    private JMenu enrolmentMenu;

    private JMenuItem exitMenuItem;

    private JMenuItem newStudentMenuItem;

    private JMenuItem listStudentsMenuItem;

    private JMenuItem newModuleMenuItem;

    private JMenuItem listModulesMenuItem;

    private JMenuItem newEnrolmentMenuItem;

    private JMenuItem initialReportMenuItem;

    private JMenuItem assessmentReportMenuItem;

    private BaseFrame newStudentFrame;

    private BaseFrame listStudentsFrame;

    private BaseFrame newModuleFrame;

    private BaseFrame listModulesFrame;

    private BaseFrame newEnrolmentFrame;

    private BaseFrame initialReportFrame;

    private BaseFrame assessmentReportFrame;

    public MenuBar() {
        initUI();
        newStudentFrame = new AddStudentFrame();

        newModuleFrame = new AddModuleFrame();


        newEnrolmentFrame = new AddEnrollmentFrame();


    }

    private void initUI() {
        fileMenu = new JMenu("File");
        exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(e -> shutDown());
        fileMenu.add(exitMenuItem);

        studentMenu = new JMenu("Student");
        newStudentMenuItem = new JMenuItem("New student");
        newStudentMenuItem.addActionListener(e -> newStudentFrame.setVisible(true));
        listStudentsMenuItem = new JMenuItem("List students");
        listStudentsMenuItem.setActionCommand("LIST_STUDENT_FRAME");
        listStudentsMenuItem.addActionListener(this);
        studentMenu.add(newStudentMenuItem);
        studentMenu.add(listStudentsMenuItem);

        moduleMenu = new JMenu("Module");
        newModuleMenuItem = new JMenuItem("New module");
        newModuleMenuItem.addActionListener(e -> newModuleFrame.setVisible(true));
        listModulesMenuItem = new JMenuItem("List modules");
        listModulesMenuItem.setActionCommand("LIST_MODULE_FRAME");
        listModulesMenuItem.addActionListener(this);

        moduleMenu.add(newModuleMenuItem);
        moduleMenu.add(listModulesMenuItem);

        enrolmentMenu = new JMenu("Enrolment");
        newEnrolmentMenuItem = new JMenuItem("New enrolment");
        newEnrolmentMenuItem.addActionListener(e -> newEnrolmentFrame.setVisible(true));
        initialReportMenuItem = new JMenuItem("Initial report");
        initialReportMenuItem.setActionCommand("LIST_INITIAL_REPORT_FRAME");
        initialReportMenuItem.addActionListener(this);

        assessmentReportMenuItem = new JMenuItem("Assessment report");
        assessmentReportMenuItem.setActionCommand("LIST_ASSESSMENT_REPORT_FRAME");
        assessmentReportMenuItem.addActionListener(this);

        enrolmentMenu.add(newEnrolmentMenuItem);
        enrolmentMenu.add(initialReportMenuItem);
        enrolmentMenu.add(assessmentReportMenuItem);

        this.add(fileMenu);
        this.add(studentMenu);
        this.add(moduleMenu);
        this.add(enrolmentMenu);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        switch (actionCommand) {
            case "LIST_STUDENT_FRAME":
                if (listStudentsFrame == null) {
                    listStudentsFrame = new DataTableFrame(new String[]{
                            "Student ID",
                            "NAME",
                            "Date of birth",
                            "Address",
                            "Email",
                    }, () -> {
                        StudentRepository studentRepository = new StudentRepository();
                        return studentRepository.getAllStudentReport();
                    });
                    listStudentsFrame.setTitle("Student list");
                } else if (!listStudentsFrame.isVisible()) {
                    listStudentsFrame.setVisible(true);
                }
                break;
            case "LIST_MODULE_FRAME":
                if (listModulesFrame == null) {
                    listModulesFrame = new DataTableFrame(new String[]{
                            "No",
                            "Module code",
                            "Module name",
                            "Module type",
                            "Semester",
                            "Credits",
                            "Department name",
                    }, () -> {
                        ModuleRepository moduleRepository = new ModuleRepository();
                        return moduleRepository.getAllModuleReport();
                    });
                    listModulesFrame.setTitle("Module list");
                } else if (!listModulesFrame.isVisible()) {
                    listModulesFrame.setVisible(true);
                }
                break;
            case "LIST_INITIAL_REPORT_FRAME":
                if (initialReportFrame == null) {
                    initialReportFrame = new DataTableFrame(new String[]{
                            "Enrollment ID",
                            "Student ID",
                            "Student name",
                            "Module code",
                            "Module name",
                    }, () -> {
                        EnrollmentRepository enrollmentRepository = new EnrollmentRepository();
                        return enrollmentRepository.getAllInitialReport();
                    });
                    initialReportFrame.setTitle("Initial report");
                } else if (!initialReportFrame.isVisible()) {
                    initialReportFrame.setVisible(true);
                }
                break;
            case "LIST_ASSESSMENT_REPORT_FRAME":
                if (assessmentReportFrame == null) {
                    assessmentReportFrame = new DataTableFrame(new String[]{
                            "Enrollment ID",
                            "Student ID",
                            "Module code",
                            "Internal mark",
                            "Exam mark",
                            "Final grade"
                    }, () -> {
                        EnrollmentRepository enrollmentRepository = new EnrollmentRepository();
                        return enrollmentRepository.getAllAssessmentReport();
                    });
                    assessmentReportFrame.setTitle("Assessment report");
                } else if (!assessmentReportFrame.isVisible()) {
                    assessmentReportFrame.setVisible(true);
                }
                break;
        }
    }
}
