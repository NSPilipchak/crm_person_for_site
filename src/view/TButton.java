package view;

import blogic.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static valus.Strings.*;

/**
 * Created by hammer on 05.05.2017.
 */
public class TButton extends JPanel {
    BookingPersonDM bookingPersonDM = null;
    BookingPersonDM2017 bookingPersonDM2017 = null;
    BaseStatusDM baseStatusDM = null;
    BaseStatusDM2017 baseStatusDM2017 = null;


    public TButton(BookingPersonDM bookingPersonDM, BaseStatusDM baseStatusDM) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.bookingPersonDM = bookingPersonDM;
        this.baseStatusDM = baseStatusDM;

        JButton btnCreate = new JButton();
        btnCreate.setText("Создать");
        btnCreate.addActionListener(bookingPersonDM.aCreate);
        btnCreate.setEnabled(false);
        add(btnCreate);

        JButton btnRead = new JButton();
        btnRead.setText("Обновить таблицу");
        btnRead.addActionListener(bookingPersonDM.aRead);
        btnRead.addActionListener(baseStatusDM.bsRead);
        add(btnRead);

        JButton btnUpdate = new JButton();
        btnUpdate.setText("Редактировать запись");
        btnUpdate.addActionListener(bookingPersonDM.aUpdate);
        btnRead.addActionListener(baseStatusDM.bsRead);
        add(btnUpdate);

        JButton btnRemap = new JButton();
        btnRemap.setText("Перенести в др. смену");
        btnRemap.addActionListener(bookingPersonDM.aDelete);
        btnRemap.setEnabled(false);
        add(btnRemap);

        JButton btnDeleteLight = new JButton();
        btnDeleteLight.setText("Удалить запись");
        btnDeleteLight.addActionListener(bookingPersonDM.aDelete);
        btnDeleteLight.setEnabled(true);
        add(btnDeleteLight);

        JButton btnBlockPerson = new JButton();
        btnBlockPerson.setText("Заблокировать запись");
        btnBlockPerson.addActionListener(bookingPersonDM.aBlockPerson);
        btnBlockPerson.setEnabled(true);
        add(btnBlockPerson);

        JButton btnTableLitle = new JButton();
        btnTableLitle.setText("Вид таблицы");
        btnTableLitle.addActionListener(bookingPersonDM.aTableVisible);
        btnTableLitle.setEnabled(false);
        add(btnTableLitle);

        JButton btnTableBig = new JButton();
        btnTableBig.setText("Полная таблица");
        btnTableBig.addActionListener(bookingPersonDM.aTableVisible);
        btnTableBig.setEnabled(false);
        add(btnTableBig);

        JButton btnCheckDate = new JButton();
        btnCheckDate.setText("Правка дат");
        btnCheckDate.addActionListener(bookingPersonDM.actionCheckDate);
        btnCheckDate.setEnabled(false);
        add(btnCheckDate);

        JButton btnCheckDouble = new JButton();
        btnCheckDouble.setText("Поиск двойников по № свид");
        btnCheckDouble.setActionCommand("sv");
        btnCheckDouble.addActionListener(bookingPersonDM.actionFindDouble);
        btnCheckDouble.setEnabled(true);
        add(btnCheckDouble);

        JButton btnCheckDouble2 = new JButton();
        btnCheckDouble2.setText("Поиск одинаковых ИНН");
        btnCheckDouble2.setActionCommand("inn");
        btnCheckDouble2.addActionListener(bookingPersonDM.actionFindDouble);
        btnCheckDouble2.setEnabled(true);
        add(btnCheckDouble2);

        JButton btnCheckYear = new JButton();
        btnCheckYear.setText("Список на смену +2017");
        btnCheckYear.addActionListener(bookingPersonDM.actionFindLastYear);
        btnCheckYear.setEnabled(true);
        add(btnCheckYear);

        JButton btnTable = new JButton();
        btnTable.setText("Список на смену");
        btnTable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fullTable = 2;
                TPanelFullTable tdf = new TPanelFullTable(bookingPersonDM);
                tdf.setModal(true);
                tdf.setVisible(true);
                fullTable = 0;
                bookingPersonDM.refresh();
            }
        });
        btnTable.setEnabled(true);
        add(btnTable);

        JButton btnFullTable = new JButton();
        btnFullTable.setText("Полная таблица");
        btnFullTable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fullTable = 1;
                TPanelFullTable tdf = new TPanelFullTable(bookingPersonDM);
                tdf.setModal(true);
                tdf.setVisible(true);
                fullTable = 0;
                bookingPersonDM.refresh();
            }
        });
        btnFullTable.setEnabled(true);
        add(btnFullTable);

        JButton btnSmenaPlaces = new JButton();
        btnSmenaPlaces.setText("Места на сайте");
        btnSmenaPlaces.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TPanelSmena tp = new TPanelSmena(bookingPersonDM.pd);
                tp.setVisible(true);
            }
        });
        btnSmenaPlaces.setEnabled(false);
        add(btnSmenaPlaces);

        JButton btnUsers = new JButton();
        btnUsers.setText("Пользователи");
        btnUsers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VListUser vListUser = new VListUser();
                vListUser.setVisible(true);
            }
        });
        btnUsers.setEnabled(permissionAdmin(WORK_USER_PERMISSION));
        btnUsers.setEnabled(false);
        add(btnUsers);
    }

    //Корявое добавление 2017 года
    public TButton(BookingPersonDM2017 bookingPersonDM2017, BaseStatusDM2017 baseStatusDM2017) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.bookingPersonDM2017 = bookingPersonDM2017;
        this.baseStatusDM2017 = baseStatusDM2017;

        JButton btnCreate = new JButton();
        btnCreate.setText("Создать");
        btnCreate.addActionListener(bookingPersonDM2017.aCreate);
        btnCreate.setEnabled(false);
        add(btnCreate);

        JButton btnRead = new JButton();
        btnRead.setText("Обновить таблицу");
        btnRead.addActionListener(bookingPersonDM2017.aRead);
        btnRead.addActionListener(baseStatusDM2017.bsRead);
        add(btnRead);

        JButton btnUpdate = new JButton();
        btnUpdate.setText("Редактировать запись");
        btnUpdate.addActionListener(bookingPersonDM2017.aUpdate);
        btnUpdate.addActionListener(baseStatusDM2017.bsRead);
        add(btnUpdate);

        JButton btnRemap = new JButton();
        btnRemap.setText("Перенести в др. смену");
        btnRemap.addActionListener(bookingPersonDM2017.aDelete);
        btnRemap.setEnabled(false);
        add(btnRemap);

        JButton btnDeleteLight = new JButton();
        btnDeleteLight.setText("Удалить запись");
        btnDeleteLight.addActionListener(bookingPersonDM2017.aDelete);
        btnDeleteLight.setEnabled(false);
        add(btnDeleteLight);

        JButton btnBlockPerson = new JButton();
        btnBlockPerson.setText("Заблокировать запись");
        btnBlockPerson.addActionListener(bookingPersonDM2017.aBlockPerson);
        btnBlockPerson.setEnabled(false);
        add(btnBlockPerson);

        JButton btnTableLitle = new JButton();
        btnTableLitle.setText("Вид таблицы");
        btnTableLitle.addActionListener(bookingPersonDM2017.aTableVisible);
        btnTableLitle.setEnabled(false);
        add(btnTableLitle);

        JButton btnTableBig = new JButton();
        btnTableBig.setText("Полная таблица");
        btnTableBig.addActionListener(bookingPersonDM2017.aTableVisible);
        btnTableBig.setEnabled(false);
        add(btnTableBig);

        JButton btnCheckDate = new JButton();
        btnCheckDate.setText("Правка дат");
        btnCheckDate.addActionListener(bookingPersonDM2017.actionCheckDate);
        btnCheckDate.setEnabled(false);
        add(btnCheckDate);

        JButton btnCheckDouble = new JButton();
        btnCheckDouble.setText("Поиск двойников по № свид");
        btnCheckDouble.setActionCommand("sv");
        btnCheckDouble.addActionListener(bookingPersonDM2017.actionFindDouble);
        btnCheckDouble.setEnabled(false);
        add(btnCheckDouble);

        JButton btnCheckDouble2 = new JButton();
        btnCheckDouble2.setText("Поиск одинаковых ИНН");
        btnCheckDouble2.setActionCommand("inn");
        btnCheckDouble2.addActionListener(bookingPersonDM2017.actionFindDouble);
        btnCheckDouble2.setEnabled(false);
        add(btnCheckDouble2);

        JButton btnTable = new JButton();
        btnTable.setText("Список на смену");
        btnTable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fullTable = 2;
                TPanelFullTable tdf = new TPanelFullTable(bookingPersonDM2017);
                tdf.setModal(true);
                tdf.setVisible(true);
                fullTable = 0;
                bookingPersonDM2017.refresh();
            }
        });
        btnTable.setEnabled(true);
        add(btnTable);

        JButton btnFullTable = new JButton();
        btnFullTable.setText("Полная таблица");
        btnFullTable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fullTable = 1;
                TPanelFullTable tdf = new TPanelFullTable(bookingPersonDM2017);
                tdf.setModal(true);
                tdf.setVisible(true);
                fullTable = 0;
                bookingPersonDM2017.refresh();
            }
        });
        btnFullTable.setEnabled(true);
        add(btnFullTable);

        JButton btnSmenaPlaces = new JButton();
        btnSmenaPlaces.setText("Места на сайте");
        btnSmenaPlaces.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                TPanelSmena tp = new TPanelSmena(bookingPersonDM2017.pd);
//                tp.setVisible(true);
            }
        });
        btnSmenaPlaces.setEnabled(false);
        add(btnSmenaPlaces);

        JButton btnUsers = new JButton();
        btnUsers.setText("Пользователи");
        btnUsers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VListUser vListUser = new VListUser();
                vListUser.setVisible(true);
            }
        });
        btnUsers.setEnabled(permissionAdmin(WORK_USER_PERMISSION));
        btnUsers.setEnabled(false);
        add(btnUsers);
    }

}
