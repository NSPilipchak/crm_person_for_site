package view.TDialog;

import blogic.BookingPerson2017;
import blogic.DataCheck;
import properties.AppSettings;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.URI;
import java.text.ParseException;

import static valus.Strings.*;

/**
 * Created by hammer on 29.05.2017.
 */
public class TDialogPanel2017 extends JDialog {
    private final static String FOLDER_FILE = "URL.FOLDER_DOCUM_FILE";
    public String ret = "Cancel";
    JButton btnOk = null;
    JButton btnCancel = null;
    JButton btnDialogFile = null;
    TDialogTextField textField;
    DataCheck dataCheck = new DataCheck();

    int dWidth = 850;
    int dHeight = 510;

    private PressBtnTrue aPressBtnTrue = new PressBtnTrue();
    private PressBtnFalse aPressBtnFalse = new PressBtnFalse();
    private PressBtnDialogFile aPressBtnDialogFile = new PressBtnDialogFile();
    private PressKeyEsc aPressKeyEsc = new PressKeyEsc();
    private ChangeStatusCard aChangeStatusCard = new ChangeStatusCard();

    int x, y;
    boolean fileVisibleList = false;

    public TDialogPanel2017(boolean editStatus) throws ParseException {

        setLayout(new BorderLayout());
        Dimension sSize = Toolkit.getDefaultToolkit().getScreenSize();
        x = ((sSize.width - dWidth) / 2);
        y = ((sSize.height - dHeight) / 2);
        setBounds(x, y, dWidth, dHeight);

        textField = new TDialogTextField(editStatus, dWidth, dHeight, 2017);
        add(textField, BorderLayout.CENTER);

        JPanel buttons = new JPanel();
        btnDialogFile = new JButton("Документи");
        btnDialogFile.setBounds(30, 330, 100, 25);
        btnDialogFile.addActionListener(aPressBtnDialogFile);
        buttons.add(btnDialogFile);

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

        textField.firstname.getDocument().addDocumentListener(documentListener);
        textField.middlename.getDocument().addDocumentListener(documentListener);
        textField.lastname.getDocument().addDocumentListener(documentListener);
        textField.birth.getDocument().addDocumentListener(documentListener);
        textField.school.getDocument().addDocumentListener(documentListener);
        textField.parentname.getDocument().addDocumentListener(documentListener);
        textField.parentipn.getDocument().addDocumentListener(documentListener);
        textField.passportnum.getDocument().addDocumentListener(documentListener);
        textField.passportinfo.getDocument().addDocumentListener(documentListener);
        textField.passportdate.getDocument().addDocumentListener(documentListener);
        textField.mainphone.getDocument().addDocumentListener(documentListener);
        textField.city.getDocument().addDocumentListener(documentListener);
        textField.street.getDocument().addDocumentListener(documentListener);
        textField.house.getDocument().addDocumentListener(documentListener);
        textField.svnum.getDocument().addDocumentListener(documentListener);
        textField.svdate.getDocument().addDocumentListener(documentListener);
        textField.email.getDocument().addDocumentListener(documentListener);

        textField.firstname.addKeyListener(aPressKeyEsc);

        textField.statusCard.addActionListener(aChangeStatusCard);

        // обработка нажатия кнопки отправка подтверждения 2-го этапа
        textField.btnSendMail.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (getStatusSendMail(textField.sendmail.getText()) == 2) {
                    textField.sendmail.setText(itemStatusSecondLevel[1]);
                } else if (getStatusSendMail(textField.sendmail.getText()) == 0) {
                    textField.sendmail.setText(itemStatusSecondLevel[1]);
                } else if (getStatusSendMail(textField.sendmail.getText()) == 1) {
                    textField.sendmail.setText(itemStatusSecondLevel[textField.oldStatusSendMail]);
                }
            }
        });
    }

    DocumentListener documentListener = new DocumentListener() {
        public void check(TDialogTextField textField) {
            if (textField.firstname.getText().equals("") || textField.middlename.getText().equals("") ||
                    textField.lastname.getText().equals("") ||
                    textField.birth.getText().equals("") || textField.school.getText().equals("") ||
                    textField.parentname.getText().equals("") || textField.parentipn.getText().equals("") ||
                    textField.passportnum.getText().equals("") || textField.passportinfo.getText().equals("") ||
                    textField.passportdate.getText().equals("") || textField.mainphone.getText().equals("") ||
                    textField.city.getText().equals("") ||
                    textField.street.getText().equals("") || textField.house.getText().equals("") ||
                    textField.svnum.getText().equals("") || textField.svdate.getText().equals("") ||
                    textField.gtype.getText().equals("") ||
                    textField.gnum.getText().equals("") || textField.email.getText().equals("")
                    ) {
                btnOk.setEnabled(false);
            } else {
                btnOk.setEnabled(true);
            }
        }


        @Override
        public void changedUpdate(DocumentEvent e) {
            check(textField);
        }

        @Override
        public void insertUpdate(DocumentEvent e) {
            check(textField);
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            check(textField);
        }
    };

    public void setPerson(BookingPerson2017 p) {
        textField.id.setText("" + p.getId());
        textField.code.setText("" + p.getCode());
        textField.firstname.setText("" + p.getFirstname());
        textField.middlename.setText("" + p.getMiddlename());
        textField.lastname.setText("" + p.getLastname());
        textField.sex.setSelectedIndex(p.getSex());
        textField.birth.setText("" + p.getBirth());
        textField.school.setText("" + p.getSchool());
        textField.parentname.setText("" + p.getParentname());
        textField.parentipn.setText("" + p.getParentipn());
        textField.passportnum.setText("" + p.getPassportnum());
        textField.passportinfo.setText("" + p.getPassportinfo());
        textField.passportdate.setText("" + p.getPassportdate());
        textField.mainphone.setText("" + p.getMainphone());
        textField.addphone.setText("" + p.getAddphone());
        textField.city.setText("" + p.getCity());
        textField.district.setSelectedItem(p.getDistrict());
        textField.street.setText("" + p.getStreet());
        textField.house.setText("" + p.getHouse());
        textField.corp.setText("" + p.getCorp());
        textField.flat.setText("" + p.getFlat());
        textField.svnum.setText("" + p.getSvnum());
        textField.svdate.setText("" + p.getSvdate());
        textField.smenadate.setSelectedItem("" +
                (p.getSmenanum() == 99 ?
                        itemSmenaDateRus2017[itemSmenaDateRus2017.length - 1] :
                        itemSmenaDateRus2017[p.getSmenanum() - 1]));
        textField.smenanum.setText("" + p.getSmenanum());
        textField.ftime.setSelectedIndex(p.getFtime());
        textField.gtype.setText("" + p.getGtype());
        textField.gnum.setText("" + p.getGnum());
        textField.email.setText("" + p.getEmail());
        textField.sendmail.setText(itemStatusSecondLevel[p.getSendmail()]);
        textField.oldStatusSendMail = p.getSendmail();
        textField.checkinn.setText("" + p.getCheckinn());
        textField.status2.setText("" + p.getStatus());
        textField.status.setText("" + dataCheck.getPersonStatus(p.getStatus(), p.getCheckinn()));
        textField.rtype.setText("" + p.getRtype());
        textField.visit.setSelected(dataCheck.getCheckBoxStatus(Integer.parseInt("" + p.getVisit())));
        textField.comment.setText("" + p.getComment());
        textField.statusCard.setSelectedIndex(p.getStatusCard());
    }

    public String chekinn() {
        String str = dataCheck.generateCheck(textField.svnum.getText());
        return str;
    }

    public BookingPerson2017 getPerson() {
        BookingPerson2017 p = new BookingPerson2017();
        p.setId(Integer.parseInt(textField.id.getText()));
        p.setCode(textField.code.getText());
        p.setFirstname(textField.firstname.getText());
        p.setMiddlename(textField.middlename.getText());
        p.setLastname(textField.lastname.getText());
        p.setSex(textField.sex.getSelectedIndex());
        p.setBirth(textField.birth.getText());
        p.setSchool(textField.school.getText());
        p.setParentname(textField.parentname.getText());
        p.setParentipn(textField.parentipn.getText());
        p.setPassportnum(textField.passportnum.getText());
        p.setPassportinfo(textField.passportinfo.getText());
        p.setPassportdate(textField.passportdate.getText());
        p.setMainphone(textField.mainphone.getText());
        p.setAddphone(textField.addphone.getText());
        p.setCity(textField.city.getText());
        p.setDistrict("" + textField.district.getSelectedItem());
        p.setStreet(textField.street.getText());
        p.setHouse(textField.house.getText());
        p.setCorp(textField.corp.getText());
        p.setFlat(textField.flat.getText());
        p.setSvnum(textField.svnum.getText());
        p.setSvdate(textField.svdate.getText());
        p.setSmenadate(Integer.parseInt(textField.smenanum.getText()) == 99 ?
                itemSmenaDate2017[itemSmenaDate2017.length - 1] :
                itemSmenaDate2017[Integer.parseInt(textField.smenanum.getText())]
        );
        p.setSmenanum(Integer.parseInt(textField.smenanum.getText()));
        p.setFtime(textField.ftime.getSelectedIndex());
        p.setGtype(Integer.parseInt(textField.gtype.getText()));
        p.setGnum(Integer.parseInt(textField.gnum.getText()));
        p.setEmail(textField.email.getText());
        p.setSendmail(getStatusSendMail(textField.sendmail.getText()));
        p.setStatus(Integer.parseInt(textField.status2.getText()));

        if (p.getStatus() == 0) {
            p.setCheckinn(textField.checkinn.getText());
        } else {
            p.setCheckinn(dataCheck.generateCheck(textField.svnum.getText()));
        }

        p.setRtype(Integer.parseInt(textField.rtype.getText()));
        p.setVisit(dataCheck.getCheckBoxStatus(textField.visit.isSelected()));
        p.setComment(textField.comment.getText());
        p.setStatusCard(textField.statusCard.getSelectedIndex());
        return p;
    }

    private int getStatusSendMail(String str) {
        for (int i = 0; i < itemStatusSecondLevel.length; i++)
            if (itemStatusSecondLevel[i].equals(str))
                return i;
        return 0;
    }

    private class ChangeStatusCard implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (textField.statusCard.getSelectedIndex() != 1) {
                textField.sendmail.setEnabled(false);
                textField.visit.setEnabled(false);
                textField.btnSendMail.setEnabled(false);
            } else {
                textField.sendmail.setEnabled(true);
                textField.visit.setEnabled(true);
                textField.btnSendMail.setEnabled(true);
            }
        }
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

    private class PressBtnDialogFile implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Desktop desktop = null;
            if (Desktop.isDesktopSupported()) {
                desktop = Desktop.getDesktop();
            }
            try {
                desktop.browse(URI.create(AppSettings.get(FOLDER_FILE) + "/" + Integer.parseInt(textField.id.getText())));
//                desktop.open((File) fldm.getValueAt(0,2));
            } catch (IOException ioe) {
                JOptionPane.showMessageDialog(null, "Текущая карточка отсутствует", "Что то пошло не так...", JOptionPane.ERROR_MESSAGE);
//                ioe.printStackTrace();
            }
        }
    }
}
