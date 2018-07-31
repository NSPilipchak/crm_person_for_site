package view.MenuBar;

import reporting.Report01;
import reporting.Report02.Report02;
import reporting.Report03.Report03;
import reporting.Report04.Report04;
import reporting.Report05_listBirth.Report05_listBirth;
import view.AdminMenu.ReservSmena.TPanelReservSmena;
import view.Direct.VListSmena;
import view.TPanel;
import view.TPanelSmena;
import view.VListUser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static valus.Strings.WORK_USER_PERMISSION;
import static valus.Strings.permissionAdmin;
import static valus.Strings.readSmenaNumber;

/**
 * Created by hammer on 14.05.2018.
 */
public class GMenuBar extends JMenuBar {

    private ActionQuit actionQuit = new ActionQuit();
    private ActionAdmin actionAdmin = new ActionAdmin();
    private ActionReport actionReport = new ActionReport();
    private TPanel tPanel;


    public GMenuBar() {

        JMenu mFile = new JMenu("Файл");
        JMenuItem mfQuit = new JMenuItem("Выход");

        JMenu mAction = new JMenu("Отчеты");
        JMenuItem maReport1 = new JMenuItem("Сформировать список для выдачи путевок");
        JMenuItem maReport2 = new JMenuItem("Сформировать сквозной резервный список");
        JMenuItem maReport3 = new JMenuItem("Сформировать резервный список по смене");
        JMenuItem maReport4 = new JMenuItem("Печать путевок на смену");
        JMenuItem maReport5 = new JMenuItem("Список именинников");

        JMenu mLib = new JMenu("Справочники");
        JMenuItem directSmena = new JMenuItem("Справочник 'Cмены'");

        JMenu mAdmin = new JMenu("Администрирование");
        JMenuItem mAdminSmena = new JMenuItem("Управление сменами на сайте");
        JMenuItem mAdminReservSmena = new JMenuItem("Управление резервными сменами");
        JMenuItem mAdminUsers = new JMenuItem("Пользователи");

        JMenu mInfo = new JMenu("?");

        directSmena.setActionCommand("smena");
        maReport1.setActionCommand("report01");
        maReport2.setActionCommand("report02");
        maReport3.setActionCommand("report03");
        maReport4.setActionCommand("report04");
        maReport5.setActionCommand("report05");
        mAdminSmena.setActionCommand("adminSmena");
        mAdminReservSmena.setActionCommand("adminReservSmena");
        mAdminUsers.setActionCommand("adminUsers");

        mfQuit.addActionListener(actionQuit);
        directSmena.addActionListener(actionAdmin);
        directSmena.setEnabled(permissionAdmin(WORK_USER_PERMISSION));
        maReport1.addActionListener(actionReport);
        maReport1.setEnabled(permissionAdmin(WORK_USER_PERMISSION));
        maReport2.addActionListener(actionReport);
        maReport2.setEnabled(permissionAdmin(WORK_USER_PERMISSION));
        maReport3.addActionListener(actionReport);
        maReport3.setEnabled(permissionAdmin(WORK_USER_PERMISSION));
        maReport4.addActionListener(actionReport);
        maReport4.setEnabled(permissionAdmin(WORK_USER_PERMISSION));
        maReport5.addActionListener(actionReport);
        maReport5.setEnabled(permissionAdmin(WORK_USER_PERMISSION));
        mAdminSmena.addActionListener(actionAdmin);
        mAdminSmena.setEnabled(permissionAdmin(WORK_USER_PERMISSION));
        mAdminReservSmena.addActionListener(actionAdmin);
        mAdminReservSmena.setEnabled(permissionAdmin(WORK_USER_PERMISSION));
        mAdminUsers.addActionListener(actionAdmin);
        mAdminUsers.setEnabled(permissionAdmin(WORK_USER_PERMISSION));

        mFile.add(mfQuit);
        mLib.add(directSmena);
        mAction.add(maReport1);
        mAction.add(maReport2);
        mAction.add(maReport3);
        mAction.add(maReport4);
        mAction.add(maReport5);
        mAdmin.add(mAdminSmena);
        mAdmin.add(mAdminReservSmena);
        mAdmin.add(mAdminUsers);

        add(mFile);
        add(mAction);
        add(mLib);
        add(mAdmin);
        add(mInfo);

    }

    private class ActionAdmin implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            switch (command) {
                case "adminSmena":
                    TPanelSmena tp = new TPanelSmena(tPanel.getDm().pd);
                    tp.setVisible(true);
                    break;
                case "adminReservSmena":
                    TPanelReservSmena trp = new TPanelReservSmena(tPanel.getDm().pd);
                    trp.setVisible(true);
                    break;
                case "smena":
                    VListSmena vListSmena = new VListSmena();
                    vListSmena.setModal(true);
                    vListSmena.setVisible(true);
                case "adminUsers":
                    VListUser vListUser = new VListUser();
                    vListUser.setModal(true);
                    vListUser.setVisible(true);
            }
        }
    }

    public void settPanel(TPanel tPanel) {
        this.tPanel = tPanel;
    }

    private class ActionQuit implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int i = JOptionPane.showConfirmDialog(null, "Завершение работы приложения", "Завершение работы", JOptionPane.YES_NO_OPTION);
            if (i == 0)
                System.exit(0);
        }
    }

    private class ActionReport implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            switch (command) {
                case "report01":
                    if (readSmenaNumber != 99 & readSmenaNumber != 0) {
                        Report01 report01 = new Report01();
                        report01.putData(tPanel.getDm().getPp());
                        report01.putParameters();
                        report01.createReport();
                    } else {
                        JOptionPane.showMessageDialog(null, "Необходимо выбирить смену!", "Что то пошло не так...", JOptionPane.INFORMATION_MESSAGE);
                    }
                    break;
                case "report02":
                    Report02 report02 = new Report02();
                    report02.createReport();
                    break;
                case "report03":
                    Report03 report03 = new Report03();
                    report03.createReport(readSmenaNumber);
                    break;
                case "report04":
                    if (readSmenaNumber != 99 & readSmenaNumber != 0) {
                        System.out.println("Start: readSmenaNumber " + readSmenaNumber + ", readSmenaNumber " + readSmenaNumber);
                        Report04 report04 = new Report04();
                        report04.putData(tPanel.getDm().getPp());
                        report04.createReport();
                    } else {
                        JOptionPane.showMessageDialog(null, "Необходимо выбирить смену!", "Что то пошло не так...", JOptionPane.INFORMATION_MESSAGE);
                    }
                    break;
                case "report05":
                    if (readSmenaNumber != 99 & readSmenaNumber != 0) {
                        System.out.println("Start: readSmenaNumber " + readSmenaNumber + ", readSmenaNumber " + readSmenaNumber);
                        Report05_listBirth report05 = new Report05_listBirth();
                        report05.putData(tPanel.getDm().getPp());
                        report05.createReport();
                    } else {
                        JOptionPane.showMessageDialog(null, "Необходимо выбирить смену!", "Что то пошло не так...", JOptionPane.INFORMATION_MESSAGE);
                    }
                    break;
            }
        }
    }
}
