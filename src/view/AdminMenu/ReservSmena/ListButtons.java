package view.AdminMenu.ReservSmena;

import javax.swing.*;

/**
 * Created by hammer on 22.07.2017.
 */
public class ListButtons extends JPanel {

    JButton btnCreate;
    JButton btnUpdate;
    JButton btnDelete;
    private boolean editStatus;
    ReservSmenaEntityDM rs = null;

    public ListButtons(boolean editStatus, ReservSmenaEntityDM rs) {
        this.editStatus = editStatus;
        this.rs = rs;
        init();
        createBtn();
        btnCreate.setActionCommand("AddSmena");
        btnCreate.addActionListener(rs.aAddSmena);
        btnUpdate.setActionCommand("UpdateSmena");
        btnUpdate.addActionListener(rs.aUpdateSmena);
        btnDelete.setActionCommand("DeleteSmena");
        btnDelete.addActionListener(rs.aDeleteSmena);
        addBtn();
    }

    private void addBtn() {
        add(btnCreate);
        add(btnUpdate);
        add(btnDelete);
    }

    private void init() {
        setLayout(null);
        setBounds(0, 0, 136, 20);
    }

    private void createBtn() {
        btnCreate = new JButton("+");
        btnCreate.setToolTipText("Добавить смену в резервный список");
        btnCreate.setEnabled(editStatus);
        btnCreate.setBounds(0, 0, 45, 25);

        btnUpdate = new JButton("*");
        btnUpdate.setToolTipText("Редактировать выбранную смену");
        btnUpdate.setEnabled(editStatus);
        btnUpdate.setBounds(45, 0, 45, 25);

        btnDelete = new JButton("-");
        btnDelete.setToolTipText("Удалить смену из резервного списка");
        btnDelete.setEnabled(editStatus);
        btnDelete.setBounds(90, 0, 45, 25);
    }
}
