package view.Direct;

import blogic.SmenaDM;

import javax.swing.*;

/**
 * Created by hammer on 22.07.2017.
 */
public class VListButtons extends JPanel {
    JButton btnRefresh;
    JButton btnCreate;
    JButton btnUpdate;
    JButton btnDelete;

    public VListButtons(SmenaDM dm) {
        init();
        createBtn();
        btnRefresh.addActionListener(dm.aRead);
//        btnCreate.addActionListener(dm.aCreate);
        btnUpdate.addActionListener(dm.aUpdate);
//        btnDelete.addActionListener(dm.aDelete);
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
        btnCreate.setEnabled(false);
        btnUpdate = new JButton("Редактировать");
        btnDelete = new JButton("Удалить запись");
        btnDelete.setEnabled(false);
    }
}
