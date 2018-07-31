package view.Direct;

import blogic.Smena;

import javax.swing.*;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.ParseException;

/**
 * Created by hammer on 17.07.2017.
 */
public class DgSmena extends JDialog {

    public String ret = "Cancel";
    JButton btnOk = null;
    JButton btnCancel = null;
    DgSmenaTextField textField;
    private boolean editStatus = true;

    private PressBtnTrue aPressBtnTrue = new PressBtnTrue();
    private PressBtnFalse aPressBtnFalse = new PressBtnFalse();
    private PressKeyEsc aPressKeyEsc = new PressKeyEsc();

    public DgSmena() throws ParseException {

        setLayout(new BorderLayout());
        Dimension sSize = Toolkit.getDefaultToolkit().getScreenSize();
        int w = 450;
        int h = 450;
        int x = ((sSize.width - w) / 2);
        int y = ((sSize.height - h) / 2);
        setBounds(x, y, w, h);

        textField = new DgSmenaTextField(editStatus);
        add(textField, BorderLayout.CENTER);

        JPanel buttons = new JPanel();

        btnOk = new JButton("Ok");
        btnOk.setBounds(130, 330, 120, 25);
        btnOk.setEnabled(false);
        buttons.add(btnOk);
        btnOk.addActionListener(aPressBtnTrue);

        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(260, 330, 150, 25);
        buttons.add(btnCancel);
        btnCancel.addActionListener(aPressBtnFalse);
        add(buttons, BorderLayout.SOUTH);

        textField.name.getDocument().addDocumentListener(documentListener);
        textField.places.getDocument().addDocumentListener(documentListener);
        textField.dateStart.getDocument().addDocumentListener(documentListener);
        textField.dateEnd.getDocument().addDocumentListener(documentListener);

        textField.name.addKeyListener(aPressKeyEsc);
    }

    private DocumentListener documentListener = new DgDocumentListiner(this);

    public void setSmena(Smena p) {
        textField.id.setText("" + p.getId());
        textField.name.setText("" + p.getName());
        textField.places.setText("" + p.getPlaces());
        textField.dateStart.setText("" + p.getDate_start());
        textField.dateEnd.setText("" + p.getDate_end());
    }

    public Smena getSmena() {
        Smena p = new Smena();
        p.setId(Integer.parseInt(textField.id.getText()));
        p.setName(textField.name.getText());
        p.setPlaces(Integer.parseInt(textField.places.getText()));
        p.setDate_start(textField.dateStart.getText());
        p.setDate_end(textField.dateEnd.getText());
        return p;
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

    private class PressKeyEsc implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                ret = "Cancel";
                setVisible(false);
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }
}
