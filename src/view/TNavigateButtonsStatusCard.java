package view;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by hammer on 28.05.2018.
 */
public class TNavigateButtonsStatusCard extends JPanel {
    private ArrayList arrStatus = new ArrayList();
    private String[] listStatus;
    private ArrayList checkStatus = new ArrayList();

    private ActionStatus actionStatus = new ActionStatus();

    public TNavigateButtonsStatusCard() {
        setLayout(null);
        setLayout(new GridLayout(4, 1, 0, 0));
        setBorder(new TitledBorder("Статус карточки"));
        setMinimumSize(new Dimension(180, 130));
        setMaximumSize(new Dimension(180, 130));

        listStatus = new String[]{
                "Новая",
                "Выслано подтверждение",
                "Запрошенны документы",
                "Отказ"};

        for (int i = 0; i < listStatus.length; i++) {
            JCheckBox jCheckBox = new JCheckBox();
            jCheckBox.setText(listStatus[i]);
//            if (jCheckBox.getText().equals(STATUS_PERSON[1]) | jCheckBox.getText().equals(STATUS_PERSON[3])) {
            jCheckBox.setSelected(true);
            arrStatus.add(i);
//            }
            jCheckBox.setActionCommand(i + "");
            jCheckBox.addActionListener(actionStatus);
            add(jCheckBox);
            checkStatus.add(jCheckBox);

            colorStatus();
        }

    }

    private void colorStatus() {
        for (Object check : checkStatus) {
            String str = ((JCheckBox) check).getText();
            switch (str) {
                case "Удаленная":
                    ((JCheckBox) check).setForeground(Color.RED);
                    break;
                case "Активная":
                    ((JCheckBox) check).setForeground(Color.BLACK);
                    break;
                case "Черный список":
                    ((JCheckBox) check).setForeground(Color.MAGENTA);
                    break;
                case "VIP персона":
                    ((JCheckBox) check).setForeground(Color.GREEN);
                    break;
                case "RIP":
                    ((JCheckBox) check).setForeground(Color.GRAY);
                    break;
            }
        }
    }

    final ArrayList addStatusToArray(int status, ArrayList arrayList) {
        boolean deleted;
        if (arrayList.size() == 0) {
            arrayList.add(status);
        } else if (arrayList.size() > 0) {
            deleted = false;
            for (int i = 0; i < arrayList.size(); i++) {
                if ((int) arrayList.get(i) == status) {
                    if (arrayList.size() != 1)
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

    private class ActionStatus implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int i = Integer.parseInt(e.getActionCommand());
            System.out.println("STATUS press checkBox " + i + " current array " + arrStatus);
            arrStatus = addStatusToArray(i, arrStatus);
            System.out.println("STATUS array " + arrStatus);

            for (Object check : checkStatus) {
                for (int x = 0; x < arrStatus.size(); x++) {
                    if (Integer.parseInt(((JCheckBox) check).getActionCommand()) == (int) arrStatus.get(x)) {
                        ((JCheckBox) check).setSelected(true);
                    }
                }
            }
        }
    }
}
