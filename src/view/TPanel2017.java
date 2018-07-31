package view;

import blogic.BaseStatusDM2017;
import blogic.BookingPersonDM2017;
import blogic.TableRenderers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TPanel2017 extends JPanel {

    BookingPersonDM2017 dm = null;
    BaseStatusDM2017 baseStatusDM = null;
    public int realRow;

    public TPanel2017() {

        setLayout(null);

        dm = new BookingPersonDM2017();
        baseStatusDM = new BaseStatusDM2017();

        JTable tbl = new JTable(dm);
        tbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = tbl.rowAtPoint(e.getPoint());
                    if (row > -1) {
                        realRow = tbl.convertRowIndexToModel(row);
                        dm.row = realRow;
                        dm.update();
                    } else if (e.getClickCount() == 1) {
                        row = tbl.rowAtPoint(e.getPoint());
                        if (row > -1) {
                            realRow = tbl.convertRowIndexToModel(row);
                            dm.row = realRow;
                        }
                    }
                }
            }
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getClickCount() == 1){
                    int row = tbl.rowAtPoint(e.getPoint());
                    if (row > -1) {
                        realRow = tbl.convertRowIndexToModel(row);
                        dm.row = realRow;
                    }
                }
            }
        });
        tbl.getTableHeader().setReorderingAllowed(false);
        tbl.setAutoCreateRowSorter(true);

        TableRenderers tableRenderer = new TableRenderers();
        for (int i = 0; i < tbl.getColumnCount(); i++)
            tbl.getColumnModel().getColumn(i).setCellRenderer(tableRenderer);

            tbl.getColumnModel().getColumn(0).setMaxWidth(40);
            tbl.getColumnModel().getColumn(0).setMinWidth(40);
            tbl.getColumnModel().getColumn(1).setMaxWidth(40);
            tbl.getColumnModel().getColumn(1).setMinWidth(40);
            tbl.getColumnModel().getColumn(2).setMaxWidth(40);
            tbl.getColumnModel().getColumn(2).setMinWidth(40);
            tbl.getColumnModel().getColumn(3).setMaxWidth(300);
            tbl.getColumnModel().getColumn(3).setMinWidth(150);
            tbl.getColumnModel().getColumn(4).setMaxWidth(80);
            tbl.getColumnModel().getColumn(4).setMinWidth(40);
            tbl.getColumnModel().getColumn(5).setMaxWidth(80);
            tbl.getColumnModel().getColumn(5).setMinWidth(40);
            tbl.getColumnModel().getColumn(6).setMaxWidth(80);
            tbl.getColumnModel().getColumn(6).setMinWidth(80);
            tbl.getColumnModel().getColumn(7).setMaxWidth(300);
            tbl.getColumnModel().getColumn(7).setMinWidth(150);
            tbl.getColumnModel().getColumn(8).setMaxWidth(80);
            tbl.getColumnModel().getColumn(8).setMinWidth(80);
            tbl.getColumnModel().getColumn(9).setMaxWidth(80);
            tbl.getColumnModel().getColumn(9).setMinWidth(70);
            tbl.getColumnModel().getColumn(10).setMaxWidth(70);
            tbl.getColumnModel().getColumn(10).setMinWidth(70);
            tbl.getColumnModel().getColumn(11).setMaxWidth(100);
            tbl.getColumnModel().getColumn(11).setMinWidth(70);
            tbl.getColumnModel().getColumn(12).setMaxWidth(40);
            tbl.getColumnModel().getColumn(12).setMinWidth(40);
            tbl.getColumnModel().getColumn(13).setMaxWidth(40);
            tbl.getColumnModel().getColumn(13).setMinWidth(40);
            tbl.getColumnModel().getColumn(14).setMaxWidth(0); //70
            tbl.getColumnModel().getColumn(14).setMinWidth(0); //70
            tbl.getColumnModel().getColumn(14).setPreferredWidth(0);
            tbl.getColumnModel().getColumn(15).setMaxWidth(40);
            tbl.getColumnModel().getColumn(15).setMinWidth(40);
            tbl.getColumnModel().getColumn(16).setMaxWidth(35);
            tbl.getColumnModel().getColumn(16).setMinWidth(35);



        JScrollPane scr = new JScrollPane(tbl);
        setLayout(new BorderLayout());
        add(scr, BorderLayout.CENTER);

        JTable tblStatus = new JTable(baseStatusDM);
        tblStatus.setEnabled(false);
        add(tblStatus, BorderLayout.SOUTH);

        add(new TFind(dm, tbl), BorderLayout.NORTH);

        add(new TButton(dm, baseStatusDM), BorderLayout.WEST);

        add(new TRadioButton(dm), BorderLayout.LINE_END);

        setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
    }
}
