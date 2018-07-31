package view.TDialog;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import static valus.Strings.*;
import static valus.Strings.WORK_USER_PERMISSION;


/**
 * Created by hammer on 29.05.2017.
 */
public class TDialogTextField extends JPanel {
    JLabel id;
    JLabel code; // varchar(64) COLLATE utf8_bin DEFAULT NULL, -- Код косадки   - id + 6 случайных цифр
    JTextField firstname; // varchar(250) COLLATE utf8_bin NOT NULL, -- Имя
    JTextField middlename;// varchar(250) COLLATE utf8_bin NOT NULL, -- Отчество
    JTextField lastname; // varchar(250) COLLATE utf8_bin NOT NULL, -- Фамилия
    JComboBox sex;
    JFormattedTextField birth; // date DEFAULT NULL, -- Дата рождения
    JTextField school; // varchar(250) COLLATE utf8_bin NOT NULL, -- № школы
    JTextField parentname; // varchar(250) COLLATE utf8_bin NOT NULL, -- ФИО родителя
    JTextField parentipn; // varchar(250) COLLATE utf8_bin NOT NULL, -- ИНН родителя
    JTextField passportnum; // varchar(250) COLLATE utf8_bin NOT NULL, -- Серия и № паспорта родителя
    JTextField passportinfo; // text COLLATE utf8_bin NOT NULL, -- Кем и когда выдан
    JFormattedTextField mainphone; // varchar(250) COLLATE utf8_bin NOT NULL, -- Основной телефон
    JTextField addphone; // text COLLATE utf8_bin NOT NULL, -- Дополнительные телефоны
    JTextField city; // varchar(250) COLLATE utf8_bin NOT NULL, -- Город
    JTextField street; // varchar(250) COLLATE utf8_bin NOT NULL, -- Улица
    JTextField house; // varchar(20) COLLATE utf8_bin NOT NULL, -- Дом
    JTextField corp; // varchar(20) COLLATE utf8_bin NOT NULL, -- Корпус, если указан
    JTextField flat; // varchar(20) COLLATE utf8_bin NOT NULL, -- Квартира
    JFormattedTextField svnum; // varchar(100) COLLATE utf8_bin NOT NULL, -- Серия и номер свидетельства о рождении
    JFormattedTextField svdate; // varchar(100) COLLATE utf8_bin NOT NULL, -- Когда выдан
    JComboBox smenadate; // date NOT NULL, -- Дата смены
    JLabel smenanum; // int(11) NOT NULL DEFAULT '0', -- Номер смены
    JComboBox ftime;
    JLabel gtype; // int(11) NOT NULL DEFAULT '0', -- Тип группы, 1-младшая, 2-средняя, 3-старшая
    JLabel gnum; // int(11) NOT NULL DEFAULT '0', -- Номер группы, пока не используется
    JTextField email; // varchar(200) COLLATE utf8_bin DEFAULT NULL, -- Email
    JLabel checkinn;
    JComboBox district;
    JFormattedTextField passportdate;
    JLabel sendmail;
    JLabel status;
    JTextField status2;
    JLabel rtype;
    JLabel age;
    JLabel rsmena;
    JCheckBox visit;
    JTextArea comment;
    JComboBox statusCard;
    JButton btnSendMail;
    JCheckBox boardingPass;

    int oldStatusSendMail = 0;

    public TDialogTextField(boolean editStatus, int dWidth, int dHeight, int year) throws ParseException {

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JPanel containerTextField = new JPanel();
        containerTextField.setLayout(new GridLayout(20, 4));
//        MaskFormatter textDate = new MaskFormatter("####-##-##");
        MaskFormatter textDate = new MaskFormatter("##.##.####");
        MaskFormatter phoneNumber = new MaskFormatter("+3 8(###) ###-##-##");
        MaskFormatter birthSvNumber = new MaskFormatter("A-UU ######");

        JLabel idText = new JLabel("Ид:");
        id = new JLabel();
        JLabel codeText = new JLabel("КодПосадки:");
        code = new JLabel();
        JLabel firstnameText = new JLabel("Имя:");
        firstname = new JTextField();
        firstname.setEditable(editStatus);
        JLabel middlenameText = new JLabel("Отчество:");
        middlename = new JTextField();
        middlename.setEditable(editStatus);
        JLabel lastnameText = new JLabel("Фамилия:");
        lastname = new JTextField();
        lastname.setEditable(editStatus);
        JLabel sexText = new JLabel("Пол:");
//        sex = new JTextField();
        sex = new JComboBox(itemSexChildren);
        sex.setEnabled(editStatus);
        JLabel birthText = new JLabel("День рождения:");
        birth = new JFormattedTextField(textDate);
        birth.setEditable(editStatus);
        JLabel schoolText = new JLabel("Номер школы:");
        school = new JTextField();
        school.setEditable(editStatus);
        JLabel parentnameText = new JLabel("ФИО Роителя:");
        parentname = new JTextField();
        parentname.setEditable(editStatus);
        JLabel parentipnText = new JLabel("ИНН Родителя:");
        parentipn = new JTextField();
        parentipn.setEditable(editStatus);
        JLabel passportnumText = new JLabel("Серия паспорта:");
        passportnum = new JTextField();
        passportnum.setEditable(editStatus);
        JLabel passportinfoText = new JLabel("Кем выдан:");
        passportinfo = new JTextField();
        passportinfo.setEditable(editStatus);
        JLabel passportdateText = new JLabel("Дата выдачи:");
        passportdate = new JFormattedTextField(textDate);
        passportdate.setEditable(editStatus);
        JLabel mainphoneText = new JLabel("Основной телефон:");
        mainphone = new JFormattedTextField(phoneNumber);
        mainphone.setEditable(editStatus);
        JLabel addphoneText = new JLabel("Дополнительный телефон:");
        addphone = new JTextField();
        addphone.setEditable(editStatus);
        JLabel cityText = new JLabel("Город:");
        city = new JTextField();
        city.setEditable(editStatus);
        JLabel districtText = new JLabel("Район:");
        district = new JComboBox(itemDistrict);
        district.setEnabled(editStatus);
        JLabel streetText = new JLabel("Улица:");
        street = new JTextField();
        street.setEditable(editStatus);
        JLabel houseText = new JLabel("Дом:");
        house = new JTextField();
        house.setEditable(editStatus);
        JLabel corpText = new JLabel("Корпус:");
        corp = new JTextField();
        corp.setEditable(editStatus);
        JLabel flatText = new JLabel("Квартира:");
        flat = new JTextField();
        flat.setEditable(editStatus);
        JLabel svnumText = new JLabel("Номер св.о рожд:");
        svnum = new JFormattedTextField(birthSvNumber);
        svnum.setEditable(editStatus);
        JLabel svdateText = new JLabel("Дата св.о рожд:");
        svdate = new JFormattedTextField(textDate);
        svdate.setEditable(editStatus);
        JLabel smenadateText = new JLabel("Дата смены:");

        smenadate = new JComboBox(itemSmenaDateRus2017);
        smenadate.setEnabled(editStatus);
        JLabel smenanumText = new JLabel("Номер смены:");
        smenanum = new JLabel();
        JLabel ftimeText = new JLabel("Был/не был:");
//        ftime = new JLabel();
        ftime = new JComboBox(itemFirstTime);
        ftime.setEnabled(editStatus);
        JLabel gtypeText = new JLabel("Тип группы:");
        gtype = new JLabel();
        JLabel gnumText = new JLabel("Номер группы:");
        gnum = new JLabel();
        JLabel emailText = new JLabel("Е-мейл:");
        email = new JTextField();
        email.setEditable(editStatus);
        JLabel sendmailText = new JLabel("2-й этап регистрации:");
//        sendmail = new JCheckBox();
        sendmail = new JLabel();
        sendmail.setEnabled(editStatus);
        JLabel checkinnText = new JLabel("Совпадения:");
        checkinn = new JLabel();
        JLabel statusText = new JLabel("Статус:");
        status = new JLabel();
        JLabel statusText2 = new JLabel("Корректировка статуса:");
        status2 = new JTextField();
        status2.setEditable(editStatus);
        status2.setEnabled(permissionAdmin(WORK_USER_PERMISSION));
        JLabel statusRtype = new JLabel("Ссылка:");
        rtype = new JLabel();
        JLabel visitText = new JLabel("Факт пребывания ребенка:");
        visit = new JCheckBox();
        visit.setEnabled(editStatus);
        JLabel boardingPassText = new JLabel("Посадочный талон выдан:");
        boardingPass = new JCheckBox();
        boardingPass.setEnabled(editStatus);
        JLabel commentText = new JLabel("Комментарий:");
        comment = new JTextArea(4, 50);

        JLabel statusCardText = new JLabel("Статус карточки:");
        statusCard = new JComboBox(itemStatusCard);
        statusCard.setEnabled(editStatus);

        btnSendMail = new JButton();
        btnSendMail.setText("Отправить подтверждение");
        btnSendMail.setEnabled(editStatus);

        JLabel ageText = new JLabel("Возраст:");
        age = new JLabel();

        JLabel rsmenaText = new JLabel("Резерв, желаемые смены:");
        rsmena = new JLabel();


        // 1
        containerTextField.add(idText);
        containerTextField.add(id);
        // 2
        containerTextField.add(firstnameText);
        containerTextField.add(firstname);
        // 3
        containerTextField.add(codeText);
        containerTextField.add(code);
        // 4
        containerTextField.add(middlenameText);
        containerTextField.add(middlename);
        // 5
        containerTextField.add(parentnameText);
        containerTextField.add(parentname);
        // 6
        containerTextField.add(lastnameText);
        containerTextField.add(lastname);
        // 7
        containerTextField.add(parentipnText);
        containerTextField.add(parentipn);
        // 8
        containerTextField.add(birthText);
        containerTextField.add(birth);
        // 9
        containerTextField.add(passportnumText);
        containerTextField.add(passportnum);
        // 10
        containerTextField.add(sexText);
        containerTextField.add(sex);
        // 11
        containerTextField.add(passportinfoText);
        containerTextField.add(passportinfo);
        // 12
        containerTextField.add(svnumText);
        containerTextField.add(svnum);
        // 13
        containerTextField.add(passportdateText);
        containerTextField.add(passportdate);
        // 14
        containerTextField.add(svdateText);
        containerTextField.add(svdate);
        // 15
        containerTextField.add(cityText);
        containerTextField.add(city);
        // 16
        containerTextField.add(mainphoneText);
        containerTextField.add(mainphone);
        // 17
        containerTextField.add(streetText);
        containerTextField.add(street);
        // 18
        containerTextField.add(addphoneText);
        containerTextField.add(addphone);
        // 19
        containerTextField.add(houseText);
        containerTextField.add(house);
        // 20
        containerTextField.add(smenadateText);
        containerTextField.add(smenadate);
        // 21
        containerTextField.add(corpText);
        containerTextField.add(corp);
        // 22
        containerTextField.add(ftimeText);
        containerTextField.add(ftime);
        // 23
        containerTextField.add(flatText);
        containerTextField.add(flat);
        // 24
        containerTextField.add(schoolText);
        containerTextField.add(school);
        // 25
        containerTextField.add(districtText);
        containerTextField.add(district);
        // 26
        containerTextField.add(smenanumText);
        containerTextField.add(smenanum);
        // 27
        containerTextField.add(emailText);
        containerTextField.add(email);
        // 28
        containerTextField.add(gtypeText);
        containerTextField.add(gtype);
        // 29
        containerTextField.add(checkinnText);
        containerTextField.add(checkinn);
        // 30
        containerTextField.add(gnumText);
        containerTextField.add(gnum);
        // 31
        containerTextField.add(statusRtype);
        containerTextField.add(rtype);
        // 32
        containerTextField.add(statusText);
        containerTextField.add(status);
        // 33
        containerTextField.add(sendmailText);
        containerTextField.add(sendmail);
        // 34
        containerTextField.add(statusText2);
        containerTextField.add(status2);
        //35
        containerTextField.add(new JLabel());
        containerTextField.add(btnSendMail);
        //36
        containerTextField.add(boardingPassText);
        containerTextField.add(boardingPass);
        // 37
        containerTextField.add(visitText);
        containerTextField.add(visit);
        // 38
        containerTextField.add(statusCardText);
        containerTextField.add(statusCard);
        // 39
        containerTextField.add(commentText);
        containerTextField.add(comment);
        //40
        containerTextField.add(ageText);
        containerTextField.add(age);
        //41
        containerTextField.add(rsmenaText);
        containerTextField.add(rsmena);

        JPanel containerComment = new JPanel();
        containerComment.setLayout(new BorderLayout());
        containerComment.setBorder(BorderFactory.createEmptyBorder(5, 12, 5, 12));
        containerComment.add(commentText, BorderLayout.NORTH);
        comment.setLineWrap(true);
        comment.setWrapStyleWord(true);
        containerComment.add(new JScrollPane(comment), BorderLayout.CENTER);
        containerComment.setSize(300, 100);

        containerComment.setBorder(BorderFactory.createEtchedBorder());
        containerTextField.setBorder(BorderFactory.createEtchedBorder());

        add(containerComment, BorderLayout.SOUTH);
        add(containerTextField, BorderLayout.CENTER);

        ActionListener smenaNumberChange = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox box = (JComboBox) e.getSource();
                int i = box.getSelectedIndex();
                smenanum.setText("" + itemSmenaNumber[i]);
            }
        };

        smenadate.addActionListener(smenaNumberChange);
    }
}
