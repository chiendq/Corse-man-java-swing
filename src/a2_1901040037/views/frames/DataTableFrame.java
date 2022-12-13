package a2_1901040037.views.frames;

import a2_1901040037.views.frames.enrollment.InitialReportTableModel;
import a2_1901040037.utils.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;

public class DataTableFrame extends BaseFrame implements ActionListener {

    private JTable moduleTable;

    private JScrollPane scrollPane;

    private DefaultTableModel tableModel;

    private String[] columnsName;

    private final Callable callable;

    public DataTableFrame(String[] columnsName, Callable callable) {
        super();
        this.columnsName = columnsName;
        initUI();
        this.callable = callable;
        bindData();
    }

    private void initUI() {
        tableModel = new DefaultTableModel(new Object[][]{}, this.columnsName);
        moduleTable = new JTable(tableModel);
        moduleTable.setBounds(30, 40, 200, 300);
        scrollPane = new JScrollPane(moduleTable);
        this.add(scrollPane);
        this.setVisible(true);
    }

    private void bindData(){
        Object[][] data = this.callable.fetchData();
        tableModel.setRowCount(0);

        for (Object[] item: data) {
            tableModel.addRow(item);
        }
    }

    @Override
    public synchronized void addComponentListener(ComponentListener l) {
        super.addComponentListener(new ComponentAdapter() {
            public void componentShown(ComponentEvent e) {
                bindData();
            }
        });
    }

    public void setColumnsName(String[] columnsName) {
        this.columnsName = columnsName;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
    }
}
