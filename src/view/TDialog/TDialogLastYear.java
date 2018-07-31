package view.TDialog;

import blogic.PersonLastYearDM;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by hammer on 01.05.2018.
 */
public class TDialogLastYear extends JPanel {

    PersonLastYearDM dm = null;
    int realRow;

    public TDialogLastYear(String svnum) {
        setLayout(null);
        dm = new PersonLastYearDM(svnum);
        setBounds(0, 0, 450, 100);

        JLabel text = new JLabel("Предыдущий год:");
        text.setBounds(15, 2, 160, 30);
        add(text);

        JTable tbl = new JTable(dm);
        tbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = tbl.rowAtPoint(e.getPoint());
                    if (row > -1) {
                        realRow = tbl.convertRowIndexToModel(row);
                        dm.row = realRow;
                    }
                    dm.update();
                } else if (e.getClickCount() == 1) {
                    int row = tbl.rowAtPoint(e.getPoint());
                    if (row > -1) {
                        realRow = tbl.convertRowIndexToModel(row);
                        dm.row = realRow;
                    }
                }
            }
        });

        tbl.getColumnModel().getColumn(0).setMaxWidth(30);
        tbl.getColumnModel().getColumn(0).setMinWidth(30);
        tbl.getColumnModel().getColumn(1).setMaxWidth(150);
        tbl.getColumnModel().getColumn(1).setMinWidth(250);
        tbl.getColumnModel().getColumn(2).setMaxWidth(100);
        tbl.getColumnModel().getColumn(2).setMinWidth(150);
        tbl.getColumnModel().getColumn(3).setMaxWidth(100);
        tbl.getColumnModel().getColumn(3).setMinWidth(150);
        tbl.getColumnModel().getColumn(4).setMaxWidth(50);
        tbl.getColumnModel().getColumn(4).setMinWidth(50);

        tbl.getTableHeader().setReorderingAllowed(false);
        tbl.setAutoCreateRowSorter(false);
        JScrollPane scr = new JScrollPane(tbl);
        scr.setBounds(12, 30, 430, 80);
        add(scr);
    }
}
