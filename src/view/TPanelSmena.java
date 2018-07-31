package view;

import blogic.SmenaDMStatus;
import dal.PersonDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by hammer on 18.07.2017.
 */
public class TPanelSmena extends JDialog {

    JButton btnCancel = null;
    SmenaDMStatus dm = null;
    public int realRow;

    private PressBtnFalse aPressBtnFalse = new PressBtnFalse();

    public TPanelSmena(PersonDAO pd) {
        setLayout(new BorderLayout());
        Dimension sSize = Toolkit.getDefaultToolkit().getScreenSize();
        int w = 900;
        int h = 320;
        int x = ((sSize.width - w) / 2);
        int y = ((sSize.height - h) / 2);
        setBounds(x, y, w, h);
        setTitle("Места на сайте");
        setModal(true);


        dm = new SmenaDMStatus(pd);

        JTable tbl = new JTable(dm);

        JScrollPane scr = new JScrollPane(tbl);
        setLayout(new BorderLayout());
        add(scr, BorderLayout.CENTER);
        tbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = tbl.rowAtPoint(e.getPoint());
                    if (row > -1) {
                        realRow = tbl.convertRowIndexToModel(row);
                        dm.row = realRow;
                    }
                    dm.update();
                }
            }
        });
        tbl.getTableHeader().setReorderingAllowed(false);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
//        tbl.setDefaultRenderer(Integer.class, centerRenderer);

        for (int j = 0; j < tbl.getColumnCount(); j++) {
            tbl.getColumnModel().getColumn(j).setCellRenderer(centerRenderer);
        }

        JPanel buttons = new JPanel();
        btnCancel = new JButton("Закрыть");
        buttons.add(btnCancel);
        btnCancel.addActionListener(aPressBtnFalse);
        add(buttons, BorderLayout.SOUTH);

    }

    private class PressBtnFalse implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
        }
    }
}
