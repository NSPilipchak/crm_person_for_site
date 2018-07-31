package view.TDialog;

import blogic.Smena;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by hammer on 25.05.2018.
 */
public class EditReserv extends JDialog {
    private ArrayList checkSmena = new ArrayList();
    private ArrayList arrSmena;
    private ArrayList<Smena> listSmenas;

    private ActionSmena actionSmena = new ActionSmena();
    private ActionBtnTrue actionBtnTrue = new ActionBtnTrue();
    private ActionBtnFalse actionBtnFalse = new ActionBtnFalse();

    private JPanel panelCheckBox;
    private JButton pressBtnTrue;
    private JButton pressBtnFalse;
    private TDialogPanel td;

    public EditReserv(TDialogPanel td) {
        this.td = td;
        listSmenas = td.pd.readSmena();

        setLayout(null);
        setBounds(300, 100, 250, 420);
        setTitle("Выбор желаемой смены");
        setLayout(new BorderLayout());

        JScrollPane scr = new JScrollPane(initCheckBox());
        scr.setBorder(new TitledBorder("Доступные смены"));
        add(scr, BorderLayout.CENTER);
        add(initButtons(), BorderLayout.SOUTH);

        loadReservedSmena();
        updateIsSelected();
    }

    private void loadReservedSmena() {
        arrSmena = new ArrayList();
        String str = td.getPerson().getRsmena();
        if (str.length() >= 1 && !str.equals("0")) {
            String[] arrStr = str.split(",");
            for (int i = 0; i < arrStr.length; i++) {
                arrSmena.add(Integer.parseInt(arrStr[i]));
            }
        }
    }

    private void updateIsSelected() {
        for (Object check : checkSmena) {
            ((JCheckBox) check).setSelected(false);
            ((JCheckBox) check).setForeground(Color.BLACK);
            ((JCheckBox) check).setFont(getFont().deriveFont(Font.PLAIN));
            for (int x = 0; x < arrSmena.size(); x++) {
                if (Integer.parseInt(((JCheckBox) check).getActionCommand()) == (int) arrSmena.get(x)) {
                    ((JCheckBox) check).setSelected(true);
                    ((JCheckBox) check).setForeground(Color.BLUE);
                    ((JCheckBox) check).setFont(getFont().deriveFont(Font.BOLD));
                }
            }
        }
    }

    private JPanel initButtons() {
        JPanel jPanelButton = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pressBtnTrue = new JButton();
        pressBtnTrue.setText("Ок");
        pressBtnTrue.addActionListener(actionBtnTrue);
        jPanelButton.add(pressBtnTrue);
        pressBtnFalse = new JButton();
        pressBtnFalse.setText("Отмена");
        pressBtnFalse.addActionListener(actionBtnFalse);
        jPanelButton.add(pressBtnFalse);

        return jPanelButton;
    }

    private JPanel initCheckBox() {
        panelCheckBox = new JPanel();
        panelCheckBox.setLayout(null);
        panelCheckBox.setLayout(new BoxLayout(panelCheckBox, BoxLayout.Y_AXIS));
        for (int i = 0; i < listSmenas.size(); i++) {
            JCheckBox jCheckBox = new JCheckBox();
            jCheckBox.setText(listSmenas.get(i).getId() + "нед. с " + listSmenas.get(i).getDate_start());

            jCheckBox.setActionCommand(listSmenas.get(i).getId() + "");
            jCheckBox.setSelected(false);
            jCheckBox.addActionListener(actionSmena);
            panelCheckBox.add(jCheckBox);
            checkSmena.add(jCheckBox);
        }
        return panelCheckBox;
    }

    final ArrayList addStatusToArray(int status, ArrayList arrayList) {
        boolean deleted;
        if (arrayList.size() == 0) {
            arrayList.add(status);
        } else if (arrayList.size() > 0) {
            deleted = false;
            for (int i = 0; i < arrayList.size(); i++) {
                if ((int) arrayList.get(i) == status) {
                    if (arrayList.size() != 0)
                        arrayList.remove(i);
                    deleted = true;
                    break;
                }
            }
            if (!deleted) {
                arrayList.add(status);
            }
        }
        return arrayList;
    }

    private class ActionSmena implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int i = Integer.parseInt(e.getActionCommand());
            arrSmena = addStatusToArray(i, arrSmena);
            updateIsSelected();
        }
    }

    private class ActionBtnTrue implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            arrSmena.sort(null);
            String rsmena;
            if (arrSmena.size() == 0) {
                rsmena = "0";
            } else {
                rsmena = arrSmena.toString();
                rsmena = rsmena.substring(1, rsmena.length() - 1);
                rsmena = rsmena.replaceAll("\\s", "");
            }
            updateIsSelected();
            td.textField.rsmena.setText(rsmena);
            setVisible(false);
        }
    }

    private class ActionBtnFalse implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
        }
    }
}
