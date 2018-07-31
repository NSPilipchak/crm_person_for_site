package view;

import blogic.FindDoublePerson;
import blogic.FindDoublePerson2017;
import blogic.WriteIntoExcel;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by hammer on 11.07.2017.
 */
public class TPanelDoubleTable extends JDialog {

    public JTable tbl;
    public WriteIntoExcel wir;
    private boolean btnStatus = false;

    public TPanelDoubleTable(FindDoublePerson2017 dm){
        tbl = new JTable(dm);
        btnStatus = dm.getRowCount()==0 ? false : true;
        run();
    }

    public TPanelDoubleTable(FindDoublePerson dm) {
        tbl = new JTable(dm);
        btnStatus = dm.getRowCount()==0 ? false : true;
        run();
    }

    private void run() {
        Dimension sSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(50, 50, sSize.width - 200, sSize.height - 200);

        setLayout(null);

        JScrollPane scr = new JScrollPane(tbl);
        setLayout(new BorderLayout());
        add(scr, BorderLayout.CENTER);

        JPanel btn = new JPanel();
        btn.setLayout(new FlowLayout());

        JButton saveExcel = new JButton();
        saveExcel.setText("Сохранить в Excel");
        saveExcel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JFileChooser fd = new JFileChooser();
                    fd.resetChoosableFileFilters();
                    fd.setFileFilter(new FileNameExtensionFilter("XLS files", "xls"));
                    fd.setAcceptAllFileFilterUsed(false);
                    wir = new WriteIntoExcel();
                    if (fd.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                        wir.writeIntoExcel(fd.getSelectedFile(), tbl, 0);
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        saveExcel.setEnabled(btnStatus);

        btn.add(saveExcel);
        add(btn, BorderLayout.NORTH);
    }
}
