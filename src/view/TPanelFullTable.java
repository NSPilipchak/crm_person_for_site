package view;

import blogic.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import static valus.Strings.fullTable;

/**
 * Created by hammer on 01.06.2017.
 */
public class TPanelFullTable extends JDialog {

    public JTable tbl;
    public WriteIntoExcel wir;
    private int lastYear = 0;

    public TPanelFullTable(FindLastYearPerson dm) {
        tbl = new JTable(dm);
        lastYear = 1;
        run();
    }

    public TPanelFullTable(BookingPersonDM2017 dm) {
        tbl = new JTable(dm);
        run();
    }

    public TPanelFullTable(BookingPersonDM dm) {
        tbl = new JTable(dm);
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
                        File file = new File(fd.getSelectedFile() + ".xls");
                        wir.writeIntoExcel(file, tbl, lastYear);
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        JButton saveBusExcel = new JButton();
        saveBusExcel.setText("Сохранить в Excel с разбивкой по автобусам");
        saveBusExcel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JFileChooser fd = new JFileChooser();
                    fd.resetChoosableFileFilters();
                    fd.setFileFilter(new FileNameExtensionFilter("XLS files", "xls"));
                    fd.setAcceptAllFileFilterUsed(false);
                    wir = new WriteIntoExcel();
                    if (fd.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                        File file = new File(fd.getSelectedFile() + ".xls");
                        wir.writeBusIntoExcel(file, tbl);
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        btn.add(saveExcel);
        if (fullTable == 2 && lastYear == 0)
            btn.add(saveBusExcel);
        add(btn, BorderLayout.NORTH);
    }
}
