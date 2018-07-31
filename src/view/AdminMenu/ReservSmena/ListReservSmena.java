package view.AdminMenu.ReservSmena;

import dal.LibraryDAO;
import dal.PersonDAO;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by hammer on 17.05.2018.
 */
public class ListReservSmena extends JPanel {

    ReservSmenaEntityDM rs = null;

    int realRow;

    public ListReservSmena(LibraryDAO pd) {
        setLayout(null);
        rs = new ReservSmenaEntityDM(pd);
        setBounds(2, 2, 300, 280);

        JLabel text = new JLabel("Доступные смены:");
        text.setBounds(10, 2, 160, 30);
        add(text);

        ListButtons listButtons = new ListButtons(true, rs);
        listButtons.setBounds(160, 4, 136, 30);
        add(listButtons);

        JTable table = new JTable(rs);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = table.rowAtPoint(e.getPoint());
                    if (row > -1) {
                        realRow = table.convertRowIndexToModel(row);
                        rs.row = realRow;
                    }
                    rs.updateSmena();
                } else if (e.getClickCount() == 1) {
                    int row = table.rowAtPoint(e.getPoint());
                    if (row > -1) {
                        realRow = table.convertRowIndexToModel(row);
                        rs.row = realRow;
                    }
                }
            }
        });

        table.getTableHeader().setReorderingAllowed(false);
        table.setAutoCreateRowSorter(true);
        JScrollPane scr = new JScrollPane(table);
        scr.setBounds(2, 34, 296, 244);
        add(scr);
    }


}
