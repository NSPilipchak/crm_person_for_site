package view;

import blogic.Smena;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

/**
 * Created by hammer on 18.07.2017.
 */
public class TPanelSmenaDialog extends JDialog {
    public String ret = "Cancel";
    JButton btnOk = null;
    JButton btnCancel = null;
    private PressBtnTrue aPressBtnTrue = new PressBtnTrue();
    private PressBtnFalse aPressBtnFalse = new PressBtnFalse();
    JLabel smena = null;
    JTextField place = null;
    JTextField name = null;
    JTextField date_start = null;
    JTextField date_end = null;
    private int smenaNumb = 1;

    public TPanelSmenaDialog() throws ParseException {
        setLayout(new BorderLayout());
        Dimension sSize = Toolkit.getDefaultToolkit().getScreenSize();
        int w = 250;
        int h = 130;
        int x = ((sSize.width - w) / 2);
        int y = ((sSize.height - h) / 2);
        setBounds(x, y, w, h);
        setTitle("Корректировка мест на сайте");

        JPanel conteiner = new JPanel();
        conteiner.setLayout(null);
        smena = new JLabel();
        smena.setBounds(10, 5, 220, 20);
        place = new JTextField();
        place.setBounds(100, 35, 50, 20);
        name = new JTextField();
        date_start = new JTextField();
        date_end = new JTextField();

        conteiner.add(smena, conteiner);
        conteiner.add(place, conteiner);

        add(conteiner, BorderLayout.CENTER);

        JPanel buttons = new JPanel();
        btnOk = new JButton("Ok");
        btnOk.setBounds(130, 330, 120, 25);
        buttons.add(btnOk);
        btnOk.addActionListener(aPressBtnTrue);

        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(260, 330, 150, 25);
        buttons.add(btnCancel);
        btnCancel.addActionListener(aPressBtnFalse);

        add(buttons, BorderLayout.SOUTH);
    }

    public void setSmena(Smena s) {
        smenaNumb = s.getId();
        place.setText("" + s.getPlaces());
        smena.setText("Места на сайте для смены № " + s.getId());
        name.setText("" + s.getName());
        date_start.setText("" + s.getDate_start());
        date_end.setText("" + s.getDate_end());
    }

    public Smena getSmena() {
        Smena s = new Smena();
        s.setId(smenaNumb);
        s.setName(name.getText());
        s.setDate_start(date_start.getText());
        s.setDate_end(date_end.getText());
        try {
            s.setPlaces(Integer.parseInt(place.getText()));
        } catch (Throwable e) {
            JOptionPane.showMessageDialog(null, "Неверно введенные данные.\n" +
                    "Доступны для ввода только цифровые значения.\n" +
                    "Значение для смены № " + smenaNumb + " обнулено!", "Что то пошло не так...", JOptionPane.ERROR_MESSAGE);
            s.setPlaces(0);
        }
        return s;
    }

    private class PressBtnTrue implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ret = "Ok";
            setVisible(false);
        }
    }

    private class PressBtnFalse implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ret = "Cancel";
            setVisible(false);
        }
    }
}
