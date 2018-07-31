package view;

import blogic.*;
import view.MenuBar.GMenuBar;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static valus.Strings.TABLE_ROW_HEIGHT;

public class TPanel extends JPanel {

    BookingPersonDM dm = null;
    BaseStatusDM baseStatusDM = null;
    public int realRow;
    private GMenuBar mb;

    public TPanel(GMenuBar mb) {
        this.mb = mb;
        mb.settPanel(this);
        setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));

        setLayout(null);

        dm = new BookingPersonDM();
        baseStatusDM = new BaseStatusDM();

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
                if (e.getClickCount() == 1) {
                    int row = tbl.rowAtPoint(e.getPoint());
                    if (row > -1) {
                        realRow = tbl.convertRowIndexToModel(row);
                        dm.row = realRow;
                    }
                }
            }
        });
        tbl.setRowHeight(TABLE_ROW_HEIGHT);
        tbl.getTableHeader().setReorderingAllowed(false);
        tbl.setAutoCreateRowSorter(true);

        TableRenderers tableRenderer = new TableRenderers();
        CheckBoxRenderer checkBoxRenderer = new CheckBoxRenderer();
        for (int i = 0; i < tbl.getColumnCount(); i++)
            if (i >= 16)
                tbl.getColumnModel().getColumn(i).setCellRenderer(checkBoxRenderer);
            else
                tbl.getColumnModel().getColumn(i).setCellRenderer(tableRenderer);

        tbl.getColumnModel().getColumn(0).setMaxWidth(60);
        tbl.getColumnModel().getColumn(0).setMinWidth(40);
        tbl.getColumnModel().getColumn(1).setMaxWidth(80);
        tbl.getColumnModel().getColumn(1).setMinWidth(40);
        tbl.getColumnModel().getColumn(2).setMaxWidth(40);
        tbl.getColumnModel().getColumn(2).setMinWidth(40);
        tbl.getColumnModel().getColumn(3).setMaxWidth(300);
        tbl.getColumnModel().getColumn(3).setMinWidth(150);
        tbl.getColumnModel().getColumn(4).setMaxWidth(120);
        tbl.getColumnModel().getColumn(4).setMinWidth(40);
        tbl.getColumnModel().getColumn(5).setMaxWidth(120);
        tbl.getColumnModel().getColumn(5).setMinWidth(40);
        tbl.getColumnModel().getColumn(6).setMaxWidth(120);
        tbl.getColumnModel().getColumn(6).setMinWidth(80);
        tbl.getColumnModel().getColumn(7).setMaxWidth(300);
        tbl.getColumnModel().getColumn(7).setMinWidth(150);
        tbl.getColumnModel().getColumn(8).setMaxWidth(160);
        tbl.getColumnModel().getColumn(8).setMinWidth(80);
        tbl.getColumnModel().getColumn(9).setMaxWidth(160);
        tbl.getColumnModel().getColumn(9).setMinWidth(70);
        tbl.getColumnModel().getColumn(10).setMaxWidth(160);
        tbl.getColumnModel().getColumn(10).setMinWidth(70);
        tbl.getColumnModel().getColumn(11).setMaxWidth(140);
        tbl.getColumnModel().getColumn(11).setMinWidth(70);
        tbl.getColumnModel().getColumn(12).setMaxWidth(80);
        tbl.getColumnModel().getColumn(12).setMinWidth(40);
        tbl.getColumnModel().getColumn(13).setMaxWidth(80);
        tbl.getColumnModel().getColumn(13).setMinWidth(40);
        tbl.getColumnModel().getColumn(14).setMaxWidth(0); //70
        tbl.getColumnModel().getColumn(14).setMinWidth(0); //70
        tbl.getColumnModel().getColumn(14).setPreferredWidth(0);
        tbl.getColumnModel().getColumn(15).setMaxWidth(80);
        tbl.getColumnModel().getColumn(15).setMinWidth(40);
        tbl.getColumnModel().getColumn(16).setMaxWidth(70);
        tbl.getColumnModel().getColumn(16).setMinWidth(35);
        tbl.getColumnModel().getColumn(17).setMaxWidth(70);
        tbl.getColumnModel().getColumn(17).setMinWidth(35);


        JScrollPane scr = new JScrollPane(tbl);
        setLayout(new BorderLayout());
        add(scr, BorderLayout.CENTER);


        Thread statusLine = new Thread(new Runnable() {
            @Override
            public void run() {
                add(statusBase(), BorderLayout.SOUTH);
            }
        });
        statusLine.setDaemon(true);
        statusLine.start();

        add(new TFind(dm, tbl), BorderLayout.NORTH);

        add(new TButton(dm, baseStatusDM), BorderLayout.WEST);

        add(new TRadioButton(dm), BorderLayout.LINE_END);

        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    }

    public BookingPersonDM getDm() {
        return dm;
    }

    private JPanel statusBase() {
        JPanel ret = new JPanel();
        ret.setLayout(null);
        ret.setLayout(new BorderLayout());
        ret.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createEtchedBorder());

        JTable tblStatus = new JTable(baseStatusDM);
        tblStatus.setEnabled(false);
        tblStatus.setRowHeight(TABLE_ROW_HEIGHT - 5);
        TableRenderersBaseStatus tableRenderer = new TableRenderersBaseStatus();
        for (int i = 0; i < tblStatus.getColumnCount(); i++)
            tblStatus.getColumnModel().getColumn(i).setCellRenderer(tableRenderer);

        panel.add(tblStatus, BorderLayout.CENTER);
        ret.add(panel, BorderLayout.CENTER);
        return ret;
    }
}
