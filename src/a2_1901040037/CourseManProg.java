package a2_1901040037;

import a2_1901040037.repositories.EnrollmentRepository;
import a2_1901040037.views.components.MenuBar;
import a2_1901040037.views.frames.DataTableFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static a2_1901040037.utils.FrameFactory.createBaseFrame;
import static a2_1901040037.utils.Utils.shutDown;

public class CourseManProg extends WindowAdapter implements ActionListener {

    private JFrame mainFrame;

    private MenuBar menuBar;


    public CourseManProg() {
        initUI();
    }

    private void initUI() {
        mainFrame = createBaseFrame("Course man");

        menuBar = new MenuBar();
        mainFrame.addWindowListener(this);

        mainFrame.setJMenuBar(menuBar);
        mainFrame.setVisible(true);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
    }

    @Override
    public void windowClosing(WindowEvent e) {
        shutDown();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CourseManProg courseMan = new CourseManProg();
        });
    }
}
