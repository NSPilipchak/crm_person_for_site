package autorization;

import blogic.User;
import dal.UserDAO;
import dal.UserDAO_MySQL_Hibernate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static valus.Strings.WORK_USER_NAME;
import static valus.Strings.WORK_USER_PERMISSION;

public class LoginDialog extends JDialog {

    private JComboBox tfLogin;
    public JPasswordField tfPassword;
    public JButton btnOk, btnCancel;
    private int howMany = 0;
    private UserDAO dm = new UserDAO_MySQL_Hibernate();


    public LoginDialog(JFrame parent) {
        super(parent, "Вход в систему");
        // При выходе из диалогового окна работа заканчивается
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                appClose();
            }
        });
        // добавляем расположение в центр окна
        getContentPane().add(createGUI());
        Dimension sSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(sSize.width / 2 - 200, sSize.height / 2 - 100, 0, 0);
        // задаем предпочтительный размер
        pack();
        // выводим окно на экран
        setModal(true);
        setVisible(true);
    }

    // этот метод будет возвращать панель с созданным расположением
    private JPanel createGUI() {
        // Создание панели для размещение компонентов
        JPanel panel = BoxLayoutUtils.createVerticalPanel();
        // Определение отступов от границ ранели. Для этого используем пустую рамку
        panel.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        // Создание панели для размещения метки и текстового поля логина
        JPanel name = BoxLayoutUtils.createHorizontalPanel();
        JLabel nameLabel = new JLabel("Имя:");
        name.add(nameLabel);
        name.add(Box.createHorizontalStrut(12));
//        tfLogin = new JTextField(15);
        try {
            tfLogin = new JComboBox(dm.users());
            tfLogin.setSelectedIndex(1);
        } catch (Throwable ex) {
            appBaseClose();

        }
        name.add(tfLogin);
        // Создание панели для размещения метки и текстового поля пароля
        JPanel password = BoxLayoutUtils.createHorizontalPanel();
        JLabel passwrdLabel = new JLabel("Пароль:");
        password.add(passwrdLabel);
        password.add(Box.createHorizontalStrut(12));
        tfPassword = new JPasswordField(15);

        tfPassword.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    appStart();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        password.add(tfPassword);
        // Создание панели для размещения кнопок управления
        JPanel flow = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        JPanel grid = new JPanel(new GridLayout(1, 2, 5, 0));
        btnOk = new JButton("OK");
        btnCancel = new JButton("Отмена");

        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                appStart();
            }
        });
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                appClose();
            }
        });

        grid.add(btnOk);
        grid.add(btnCancel);

        flow.add(grid);
        // Выравнивание вложенных панелей по горизонтали
        BoxLayoutUtils.setGroupAlignmentX(new JComponent[]{name, password, panel, flow},
                Component.LEFT_ALIGNMENT);
        // Выравнивание вложенных панелей по вертикали
        BoxLayoutUtils.setGroupAlignmentY(new JComponent[]{tfLogin, tfPassword, nameLabel, passwrdLabel},
                Component.CENTER_ALIGNMENT);

        // Определение размеров надписей к текстовым полям
        GUITools.makeSameSize(new JComponent[]{nameLabel, passwrdLabel});
        // Определение стандартного вида для кнопок
        GUITools.createRecommendedMargin(new JButton[]{btnOk, btnCancel});

        // Устранение "бесконечной" высоты текстовых полей
//        GUITools.fixTextFieldSize(tfLogin);
        GUITools.fixTextFieldSize(tfPassword);

        // Сборка интерфейса
        panel.add(name);
        panel.add(Box.createVerticalStrut(12));
        panel.add(password);
        panel.add(Box.createVerticalStrut(17));
        panel.add(flow);
        // готово
        return panel;
    }

    @Deprecated
    private void appStart() {
        if (howMany < 4) {
            String userName = tfLogin.getSelectedItem().toString();


            if (dm.checkUser(userName, tfPassword.getText().toString())) {
                User u = dm.getUser(userName);
                if (u.getStatus() == 0) {
                    JOptionPane.showMessageDialog(null, "Выбранный пользователь заблокирован.\n" +
                            "До новых встречь...", "Доступ закрыт", JOptionPane.ERROR_MESSAGE);
                    appClose();
                } else {
                    WORK_USER_NAME = u.getUser();
                    WORK_USER_PERMISSION = u.getPermission();
                    setVisible(false);
                }
            } else {
                howMany++;
                JOptionPane.showMessageDialog(null, "Осталось " + (5 - howMany) + " попытки", "Неверный пароль", JOptionPane.ERROR_MESSAGE);
                tfPassword.setText("");
            }
        } else {
            appClose();
        }
    }

    private void appClose() {
        JOptionPane.showMessageDialog(null, "До новых встречь...", "Неверный пароль", JOptionPane.ERROR_MESSAGE);
        dispose();
        System.exit(0);
    }

    private void appBaseClose() {
        JOptionPane.showMessageDialog(null, "Проверьте включен ли сервер. \n" +
                "Проверьте соединение с сервером.", "Соединение с базой отсутствует", JOptionPane.ERROR_MESSAGE);
        dispose();
        System.exit(0);
    }
}
