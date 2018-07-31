package NotUses;

import javax.swing.*;
import java.awt.*;

/**
 * Created by hammer on 29.05.2017.
 */
public class TDialogJLabel extends JPanel {
    JLabel id;
    JLabel code; // varchar(64) COLLATE utf8_bin DEFAULT NULL, -- Код косадки   - id + 6 случайных цифр
    JTextField firstname; // varchar(250) COLLATE utf8_bin NOT NULL, -- Имя
    JTextField middlename;// varchar(250) COLLATE utf8_bin NOT NULL, -- Отчество
    JTextField lastname; // varchar(250) COLLATE utf8_bin NOT NULL, -- Фамилия
    JTextField sex; // int(11) NOT NULL DEFAULT '0', -- Пол
    JTextField birth; // date DEFAULT NULL, -- Дата рождения
    JTextField school; // varchar(250) COLLATE utf8_bin NOT NULL, -- № школы
    JTextField parentname; // varchar(250) COLLATE utf8_bin NOT NULL, -- ФИО родителя
    JTextField parentipn; // varchar(250) COLLATE utf8_bin NOT NULL, -- ИНН родителя
    JTextField passportnum; // varchar(250) COLLATE utf8_bin NOT NULL, -- Серия и № паспорта родителя
    JTextField passportinfo; // text COLLATE utf8_bin NOT NULL, -- Кем и когда выдан
    JTextField mainphone; // varchar(250) COLLATE utf8_bin NOT NULL, -- Основной телефон
    JTextField addphone; // text COLLATE utf8_bin NOT NULL, -- Дополнительные телефоны
    JTextField city; // varchar(250) COLLATE utf8_bin NOT NULL, -- Город
    JTextField street; // varchar(250) COLLATE utf8_bin NOT NULL, -- Улица
    JTextField house; // varchar(20) COLLATE utf8_bin NOT NULL, -- Дом
    JTextField corp; // varchar(20) COLLATE utf8_bin NOT NULL, -- Корпус, если указан
    JTextField flat; // varchar(20) COLLATE utf8_bin NOT NULL, -- Квартира
    JTextField svnum; // varchar(100) COLLATE utf8_bin NOT NULL, -- Серия и номер свидетельства о рождении
    JTextField svdate; // varchar(100) COLLATE utf8_bin NOT NULL, -- Когда выдан
    JTextField smenadate; // date NOT NULL, -- Дата смены
    JTextField smenanum; // int(11) NOT NULL DEFAULT '0', -- Номер смены
    JTextField ftime; // int(11) NOT NULL DEFAULT '0', -- 0 - первый раз, 1 - уже был
    JTextField gtype; // int(11) NOT NULL DEFAULT '0', -- Тип группы, 1-младшая, 2-средняя, 3-старшая
    JTextField gnum; // int(11) NOT NULL DEFAULT '0', -- Номер группы, пока не используется
    JTextField email; // varchar(200) COLLATE utf8_bin DEFAULT NULL, -- Email
    JLabel checkinn;
    JTextField district;
    JTextField passportdate;
    JCheckBox sendmail;
    JLabel status;
    JLabel rtype;
    JCheckBox visit;
    JTextArea comment;

    public TDialogJLabel(){
        setLayout(new GridLayout(18, 4));
        setBounds(300, 300, 850, 510);

        JLabel idText = new JLabel("Ид:");
        id = new JLabel();
        JLabel codeText = new JLabel("КодПосадки:");
        code = new JLabel();
        JLabel firstnameText = new JLabel("Имя:");
        firstname = new JTextField();
        JLabel middlenameText = new JLabel("Отчество:");
        middlename = new JTextField();
        JLabel lastnameText = new JLabel("Фамилия:");
        lastname = new JTextField();
        JLabel sexText = new JLabel("Пол:");
        sex = new JTextField();
        JLabel birthText = new JLabel("День рождения:");
        birth = new JTextField();
        JLabel schoolText = new JLabel("Номер школы:");
        school = new JTextField();
        JLabel parentnameText = new JLabel("ФИО Роителя:");
        parentname = new JTextField();
        JLabel parentipnText = new JLabel("ИНН Родителя:");
        parentipn = new JTextField();
        JLabel passportnumText = new JLabel("Серия паспорта:");
        passportnum = new JTextField();
        JLabel passportinfoText = new JLabel("Кем выдан:");
        passportinfo = new JTextField();
        JLabel passportdateText = new JLabel("Дата выдачи:");
        passportdate = new JTextField();
        JLabel mainphoneText = new JLabel("Основной телефон:");
        mainphone = new JTextField();
        JLabel addphoneText = new JLabel("Дополнительный телефон:");
        addphone = new JTextField();
        JLabel cityText = new JLabel("Город:");
        city = new JTextField();
        JLabel districtText = new JLabel("Район:");
        district = new JTextField();
        JLabel streetText = new JLabel("Улица:");
        street = new JTextField();
        JLabel houseText = new JLabel("Дом:");
        house = new JTextField();
        JLabel corpText = new JLabel("Корпус:");
        corp = new JTextField();
        JLabel flatText = new JLabel("Квартира:");
        flat = new JTextField();
        JLabel svnumText = new JLabel("Номер св.о рожд:");
        svnum = new JTextField();
        JLabel svdateText = new JLabel("Дата св.о рожд:");
        svdate = new JTextField();
        JLabel smenadateText = new JLabel("Дата смены:");
        smenadate = new JTextField();
        JLabel smenanumText = new JLabel("Номер смены:");
        smenanum = new JTextField();
        JLabel ftimeText = new JLabel("Был/не был:");
        ftime = new JTextField();
        JLabel gtypeText = new JLabel("Тип группы:");
        gtype = new JTextField();
        JLabel gnumText = new JLabel("Номер группы:");
        gnum = new JTextField();
        JLabel emailText = new JLabel("Е-мейл:");
        email = new JTextField();
        JLabel sendmailText = new JLabel("Письмо отправленно:");
        sendmail = new JCheckBox();
        JLabel checkinnText = new JLabel("Совпадения:");
        checkinn = new JLabel();
        JLabel statusText = new JLabel("Статус:");
        status = new JLabel();
        JLabel statusRtype = new JLabel("Ссылка:");
        rtype = new JLabel();
        JLabel visitText = new JLabel("Визит:");
        visit = new JCheckBox();
        JLabel commentText = new JLabel("Комментарий:");
        comment = new JTextArea();

        add(idText);
        add(id);
        add(codeText);
        add(code);
        add(firstnameText);
        add(firstname);
        add(middlenameText);
        add(middlename);
        add(lastnameText);
        add(lastname);
        add(sexText);
        add(sex);
        add(birthText);
        add(birth);
        add(schoolText);
        add(school);
        add(parentnameText);
        add(parentname);
        add(parentipnText);
        add(parentipn);
        add(passportnumText);
        add(passportnum);
        add(passportinfoText);
        add(passportinfo);
        add(passportdateText);
        add(passportdate);
        add(mainphoneText);
        add(mainphone);
        add(addphoneText);
        add(addphone);
        add(cityText);
        add(city);
        add(districtText);
        add(district);
        add(streetText);
        add(street);
        add(houseText);
        add(house);
        add(corpText);
        add(corp);
        add(flatText);
        add(flat);
        add(svnumText);
        add(svnum);
        add(svdateText);
        add(svdate);
        add(smenadateText);
        add(smenadate);
        add(smenanumText);
        add(smenanum);
        add(ftimeText);
        add(ftime);
        add(gtypeText);
        add(gtype);
        add(gnumText);
        add(gnum);
        add(emailText);
        add(email);
        add(sendmailText);
        add(sendmail);
        add(checkinnText);
        add(checkinn);
        add(statusText);
        add(status);
        add(statusRtype);
        add(rtype);
        add(visitText);
        add(visit);
        add(commentText);
        add(comment);
    }
}
