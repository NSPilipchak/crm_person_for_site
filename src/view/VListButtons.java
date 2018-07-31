package view;

import blogic.UserDM;

import javax.swing.*;

public class VListButtons extends JPanel {
    JButton btnRefresh;
    JButton btnCreate;
    JButton btnUpdate;
    JButton btnDelete;

    public VListButtons(UserDM dm) {
        init();
        createBtn();
        btnRefresh.addActionListener(dm.aRead);
        btnCreate.addActionListener(dm.aCreate);
        btnUpdate.addActionListener(dm.aUpdate);
        btnDelete.addActionListener(dm.aDelete);

        addBtn();
    }

    private void addBtn() {
        add(btnRefresh);
        add(btnCreate);
        add(btnUpdate);
        add(btnDelete);
    }

    private void init() {
        setLayout(null);
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    }

    private void createBtn() {
        btnRefresh = new JButton("Обновить список");
        btnCreate = new JButton("Новая запись");
        btnUpdate = new JButton("Редактировать");
        btnDelete = new JButton("Удалить запись");
        btnDelete.setEnabled(false);
    }
}
