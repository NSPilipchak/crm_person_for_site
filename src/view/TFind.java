package view;

import blogic.BookingPersonDM;
import blogic.BookingPersonDM2017;

import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.regex.PatternSyntaxException;

/**
 * Created by hammer on 29.05.2017.
 */
public class TFind extends JPanel {

    final JTextField findTextField;
    final TableRowSorter<?> sorter;

    public TFind(BookingPersonDM2017 dm, JTable tbl) {
        sorter = new TableRowSorter<>(dm);
        tbl.setRowSorter(sorter);
        findTextField = new JTextField();
        run();
    }

    public TFind(BookingPersonDM dm, JTable tbl) {
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        sorter = new TableRowSorter<>(dm);
        tbl.setRowSorter(sorter);
        findTextField = new JTextField();
        run();
    }

    private void run() {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        JLabel jLabel = new JLabel("Поиск: ");
        add(jLabel);


        findTextField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    findText();
                }
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    findClear();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        add(findTextField);


        JButton btnFind = new JButton("Поиск");
        btnFind.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                findText();
            }
        });
        add(btnFind);

        JButton btnFindClear = new JButton("Отмена");
        btnFindClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                findClear();
            }
        });
        add(btnFindClear);
    }

    private void findText() {
        String text = findTextField.getText();
        if (text.length() == 0) {
            sorter.setRowFilter(null);
        } else {
            try {
                sorter.setRowFilter(RowFilter.regexFilter(text));
            } catch (PatternSyntaxException pse) {
                System.err.println("Bad regex pattern");
            }
        }
    }

    private void findClear() {
        findTextField.setText(null);
        sorter.setRowFilter(null);
    }
}
