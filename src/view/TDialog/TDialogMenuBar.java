package view.TDialog;

import reporting.Report00;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by hammer on 14.05.2018.
 */
public class TDialogMenuBar extends JMenuBar {

    private ActionTicket action = new ActionTicket();
    private TDialogPanel td;

    public TDialogMenuBar(TDialogPanel td) {
        this.td = td;

        JMenu mForm = new JMenu("Форма");
        JMenuItem mfOk = new JMenuItem("Сохранить и закрыть");
        JMenuItem mfCancel = new JMenuItem("Закрыть без сохранения");
        JMenu mAction = new JMenu("Возможности");
        JMenuItem maTicket = new JMenuItem("Посадочный талон");
        JMenuItem maDocument = new JMenuItem("Документы");
        JMenu mActionPlus = new JMenu("Дополнительно");
        JMenuItem mapReserv = new JMenuItem("Редактировать резерв");
        JMenuItem mapSendMail = new JMenuItem("Продублировать письмо");

        mfOk.setActionCommand("ok");
        mfCancel.setActionCommand("cancel");
        maTicket.setActionCommand("report0");
        maDocument.setActionCommand("docum");
        mapReserv.setActionCommand("editReserv");
        mapSendMail.setActionCommand("sendMail");

        mfOk.addActionListener(action);
        mfCancel.addActionListener(action);
        maTicket.addActionListener(action);
        maDocument.addActionListener(action);
        mapReserv.addActionListener(action);
        mapSendMail.addActionListener(action);

        mForm.add(mfOk);
        mForm.add(mfCancel);

        mAction.add(maTicket);
        mAction.add(maDocument);

        mActionPlus.add(mapReserv);
        mActionPlus.add(mapSendMail);

        add(mForm);
        add(mAction);
        add(mActionPlus);

    }

    private class ActionTicket implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            switch (command) {
                case "report0":
                    Report00 report00 = new Report00();
                    report00.putData(td.getPerson());
                    report00.createReport();
                    break;
                case "docum":
                    td.openDictionary();
                    break;
                case "ok":
                    td.pressBtnTrue();
                    break;
                case "cancel":
                    td.pressBtnFalse();
                    break;
                case "editReserv":
                    EditReserv editReserv = new EditReserv(td);
                    editReserv.setModal(true);
                    editReserv.setVisible(true);
                    break;
                case "sendMail":
                    System.out.println("Повтороно отправить письмо");
                    break;
            }
        }
    }
}
