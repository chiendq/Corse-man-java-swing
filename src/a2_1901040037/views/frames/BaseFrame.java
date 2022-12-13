package a2_1901040037.views.frames;


import javax.swing.*;

import static a2_1901040037.utils.DimensionFactory.baseScreenScale;

public class BaseFrame extends JFrame {

    public BaseFrame(){
        this.setVisible(false);
        this.setSize(baseScreenScale());
        initUI();
    }

    private void initUI() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem menuItem = new JMenuItem("Exit");
        menuItem.addActionListener(e -> this.setVisible(false));
        fileMenu.add(menuItem);
        menuBar.add(fileMenu);
        this.setJMenuBar(menuBar);
    }

}
