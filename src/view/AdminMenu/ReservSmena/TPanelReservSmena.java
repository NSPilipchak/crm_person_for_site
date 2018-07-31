package view.AdminMenu.ReservSmena;

import blogic.App;
import dal.LibraryDAO;
import dal.LibraryDAO_MySQL_Hibernate;
import dal.PersonDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by hammer on 18.07.2017.
 */
public class TPanelReservSmena extends JDialog {

    JButton btnOk = null;
    JButton btnCancel = null;
    JButton btnApply = null;
    LibraryDAO libraryDAO = null;
    JTextField multiChoise;
    JLabel textNow;
    ListReservSmena listReservSmena;

    private PressBtnOk aPressBtnOk = new PressBtnOk();
    private PressBtnFalse aPressBtnFalse = new PressBtnFalse();
    private PressBtnApply aPressBtnApply = new PressBtnApply();

    public TPanelReservSmena(PersonDAO pd) {
        setLayout(new BorderLayout());
        Dimension sSize = Toolkit.getDefaultToolkit().getScreenSize();
        int w = 325;
        int h = 470;
        int x = ((sSize.width - w) / 2);
        int y = ((sSize.height - h) / 2);
        setBounds(x, y, w, h);
        setTitle("Управление резервной сменой");
        setResizable(false);
        setModal(true);

        libraryDAO = new LibraryDAO_MySQL_Hibernate();

        add(addReservSmenaChoise(), BorderLayout.CENTER);


        add(addMultipleChoise(), BorderLayout.NORTH);
        setMultiChoise();

        JPanel buttons = new JPanel();
        btnOk = new JButton("Ок");
        btnCancel = new JButton("Закрыть");
        btnApply = new JButton("Применить");
        buttons.add(btnOk);
        buttons.add(btnCancel);
        buttons.add(btnApply);
        btnOk.addActionListener(aPressBtnOk);
        btnCancel.addActionListener(aPressBtnFalse);
        btnApply.addActionListener(aPressBtnApply);
        add(buttons, BorderLayout.SOUTH);
    }

    private void update() {
        setMultiChoise();
        repaint();
//        System.out.println(listReservSmena.rs.getArrayString());
        listReservSmena.rs.refresh();

    }

    private void setMultiChoise() {
        textNow.setText(libraryDAO.getValueByKey("ReservMultiChoise"));
        multiChoise.setText(libraryDAO.getValueByKey("ReservMultiChoise"));
    }

    private JPanel addReservSmenaChoise() {
        JPanel ret = new JPanel();
        ret.setLayout(null);
        ret.setLayout(new BoxLayout(ret, BoxLayout.Y_AXIS));

        listReservSmena = new ListReservSmena(libraryDAO);
        ret.add(listReservSmena);
        ret.setBorder(BorderFactory.createTitledBorder("Список доступных смен в резерве на сайте"));
        return ret;
    }

    private JPanel addMultipleChoise() {
        JPanel ret = new JPanel();
        ret.setLayout(null);
        ret.setLayout(new BoxLayout(ret, BoxLayout.Y_AXIS));

        JLabel text2 = new JLabel("Текущее значение:");
        ret.add(text2);
        textNow = new JLabel();
        ret.add(textNow);
        JLabel text4 = new JLabel("Установить:");
        ret.add(text4);
        multiChoise = new JTextField();
        ret.add(multiChoise);
        ret.setBorder(BorderFactory.createTitledBorder("Максимальный выбор (MultiChoise)"));
        return ret;
    }

    private class PressBtnOk implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            App a = libraryDAO.getAppByKey("ReservMultiChoise");
            a.setValue(multiChoise.getText());
            libraryDAO.updateApp(a);
            a = libraryDAO.getAppByKey("ReservSmena");
            a.setValue(listReservSmena.rs.getArrayString());
            libraryDAO.updateApp(a);
//            System.out.println("Ok");
            setVisible(false);
        }
    }

    private class PressBtnFalse implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
//            System.out.println("Cancel");
            setVisible(false);
        }
    }

    private class PressBtnApply implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            App a = libraryDAO.getAppByKey("ReservMultiChoise");
            a.setValue(multiChoise.getText());
            libraryDAO.updateApp(a);
            a = libraryDAO.getAppByKey("ReservSmena");
            a.setValue(listReservSmena.rs.getArrayString());
            libraryDAO.updateApp(a);
            update();
        }
    }
}
