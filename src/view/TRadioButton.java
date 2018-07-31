package view;

import blogic.BookingPersonDM;
import blogic.BookingPersonDM2017;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static valus.Strings.readSmenaNumber;
import static valus.Strings.readStatusPerson;

/**
 * Created by hammer on 31.05.2017.
 */
public class TRadioButton extends JPanel {

    BookingPersonDM bookingPersonDM = null;
    BookingPersonDM2017 bookingPersonDM2017 = null;

    static String sm0str = "Все смены";
    static String sm1str = "Смена №1";
    static String sm2str = "Смена №2";
    static String sm3str = "Смена №3";
    static String sm4str = "Смена №4";
    static String sm5str = "Смена №5";
    static String sm6str = "Смена №6";
    static String sm7str = "Смена №7";
    static String sm8str = "Смена №8";
    static String sm9str = "Смена №9";
    static String sm10str = "Смена №10";
    static String sm11str = "Смена №11";
    static String sm12str = "Смена №12";
    static String sm13str = "Смена №13";
    static String sm99str = "Резерв";

    static String allstr = "Без ограничений";
    static String activstr = "Активные";
    static String deletedstr = "Удаленные";
    static String blackliststr = "Заблокированные";

    public TRadioButton(BookingPersonDM2017 bookingPersonDM2017) {
        super(new BorderLayout());
        this.bookingPersonDM2017 = bookingPersonDM2017;
        run();
    }

    public TRadioButton(BookingPersonDM bookingPersonDM) {
        super(new BorderLayout());
        this.bookingPersonDM = bookingPersonDM;
        run();
    }

    private void refreshDM() {
        try {
            bookingPersonDM.refresh();
            bookingPersonDM2017.refresh();
        } catch (Exception ex) {

        }

    }

    private void run() {

        JRadioButton btnSm0 = new JRadioButton(sm0str);
        btnSm0.setActionCommand("0");

        JRadioButton btnSm1 = new JRadioButton(sm1str);
        btnSm1.setActionCommand("1");
        btnSm1.setSelected(true);

        JRadioButton btnSm2 = new JRadioButton(sm2str);
        btnSm2.setActionCommand("2");

        JRadioButton btnSm3 = new JRadioButton(sm3str);
        btnSm3.setActionCommand("3");

        JRadioButton btnSm4 = new JRadioButton(sm4str);
        btnSm4.setActionCommand("4");

        JRadioButton btnSm5 = new JRadioButton(sm5str);
        btnSm5.setActionCommand("5");

        JRadioButton btnSm6 = new JRadioButton(sm6str);
        btnSm6.setActionCommand("6");

        JRadioButton btnSm7 = new JRadioButton(sm7str);
        btnSm7.setActionCommand("7");

        JRadioButton btnSm8 = new JRadioButton(sm8str);
        btnSm8.setActionCommand("8");

        JRadioButton btnSm9 = new JRadioButton(sm9str);
        btnSm9.setActionCommand("9");

        JRadioButton btnSm10 = new JRadioButton(sm10str);
        btnSm10.setActionCommand("10");

        JRadioButton btnSm11 = new JRadioButton(sm11str);
        btnSm11.setActionCommand("11");

        JRadioButton btnSm12 = new JRadioButton(sm12str);
        btnSm12.setActionCommand("12");

        JRadioButton btnSm13 = new JRadioButton(sm13str);
        btnSm13.setActionCommand("13");

        JRadioButton btnSm99 = new JRadioButton(sm99str);
        btnSm99.setActionCommand("99");

        ButtonGroup group = new ButtonGroup();
        group.add(btnSm0);
        group.add(btnSm1);
        group.add(btnSm2);
        group.add(btnSm3);
        group.add(btnSm4);
        group.add(btnSm5);
        group.add(btnSm6);
        group.add(btnSm7);
        group.add(btnSm8);
        group.add(btnSm9);
        group.add(btnSm10);
        group.add(btnSm11);
        group.add(btnSm12);
        group.add(btnSm13);
        group.add(btnSm99);

        btnSm0.addActionListener(aChangeSmena);
        btnSm1.addActionListener(aChangeSmena);
        btnSm2.addActionListener(aChangeSmena);
        btnSm3.addActionListener(aChangeSmena);
        btnSm4.addActionListener(aChangeSmena);
        btnSm5.addActionListener(aChangeSmena);
        btnSm6.addActionListener(aChangeSmena);
        btnSm7.addActionListener(aChangeSmena);
        btnSm8.addActionListener(aChangeSmena);
        btnSm9.addActionListener(aChangeSmena);
        btnSm10.addActionListener(aChangeSmena);
        btnSm11.addActionListener(aChangeSmena);
        btnSm12.addActionListener(aChangeSmena);
        btnSm13.addActionListener(aChangeSmena);
        btnSm99.addActionListener(aChangeSmena);

        JPanel radioPanelSmena = new JPanel(new GridLayout(0, 1));
        radioPanelSmena.add(btnSm0);
        radioPanelSmena.add(btnSm1);
        radioPanelSmena.add(btnSm2);
        radioPanelSmena.add(btnSm3);
        radioPanelSmena.add(btnSm4);
        radioPanelSmena.add(btnSm5);
        radioPanelSmena.add(btnSm6);
        radioPanelSmena.add(btnSm7);
        radioPanelSmena.add(btnSm8);
        radioPanelSmena.add(btnSm9);
        radioPanelSmena.add(btnSm10);
        radioPanelSmena.add(btnSm11);
        radioPanelSmena.add(btnSm12);
        radioPanelSmena.add(btnSm13);
        radioPanelSmena.add(btnSm99);

        radioPanelSmena.setBorder(new TitledBorder("Выбор смены"));

        JRadioButton btnAll = new JRadioButton(allstr);
        btnAll.setActionCommand("0");

        JRadioButton btnActiv = new JRadioButton(activstr);
        btnActiv.setActionCommand("1");
        btnActiv.setSelected(true);

        JRadioButton btnDeleted = new JRadioButton(deletedstr);
        btnDeleted.setActionCommand("2");

        JRadioButton btnBlackList = new JRadioButton(blackliststr);
        btnBlackList.setActionCommand("3");

        ButtonGroup groupStatus = new ButtonGroup();
        groupStatus.add(btnAll);
        groupStatus.add(btnActiv);
        groupStatus.add(btnDeleted);
        groupStatus.add(btnBlackList);

        btnAll.addActionListener(aChangeStatus);
        btnActiv.addActionListener(aChangeStatus);
        btnDeleted.addActionListener(aChangeStatus);
        btnBlackList.addActionListener(aChangeStatus);

        JPanel radioPanelStatus = new JPanel(new GridLayout(0, 1));
        radioPanelStatus.add(btnAll);
        radioPanelStatus.add(btnActiv);
        radioPanelStatus.add(btnBlackList);
        radioPanelStatus.add(btnDeleted);

        radioPanelStatus.setBorder(new TitledBorder("Статус ребенка"));

        add(radioPanelSmena, BorderLayout.CENTER);
        add(radioPanelStatus, BorderLayout.SOUTH);
//        add(new TNavigateButtonsStatusCard(), BorderLayout.NORTH);

    }

    public ActionChangeSmena aChangeSmena = new ActionChangeSmena();
    public ActionStatus aChangeStatus = new ActionStatus();


    private class ActionChangeSmena implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String cmd = e.getActionCommand();
            switch (cmd) {
                case "0":
                    readSmenaNumber = 0;
                    break;
                case "1":
                    readSmenaNumber = 1;
                    break;
                case "2":
                    readSmenaNumber = 2;
                    break;
                case "3":
                    readSmenaNumber = 3;
                    break;
                case "4":
                    readSmenaNumber = 4;
                    break;
                case "5":
                    readSmenaNumber = 5;
                    break;
                case "6":
                    readSmenaNumber = 6;
                    break;
                case "7":
                    readSmenaNumber = 7;
                    break;
                case "8":
                    readSmenaNumber = 8;
                    break;
                case "9":
                    readSmenaNumber = 9;
                    break;
                case "10":
                    readSmenaNumber = 10;
                    break;
                case "11":
                    readSmenaNumber = 11;
                    break;
                case "12":
                    readSmenaNumber = 12;
                    break;
                case "13":
                    readSmenaNumber = 13;
                    break;
                case "99":
                    readSmenaNumber = 99;
                    break;
            }
            refreshDM();
        }
    }

    private class ActionStatus implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String cmd = e.getActionCommand();
            switch (cmd) {
                case "0":
                    readStatusPerson = 0; //без ограничений
                    break;
                case "1":
                    readStatusPerson = 1; // Активные
                    break;
                case "2":
                    readStatusPerson = 2; // Удаленные
                    break;
                case "3":
                    readStatusPerson = 3; // Заблокированные
                    break;
            }
            refreshDM();
        }
    }
}
