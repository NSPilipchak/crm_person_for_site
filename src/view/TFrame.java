package view;

import autorization.LoginDialog;
import properties.AppSettings;
import view.MenuBar.GMenuBar;

import javax.swing.*;
import java.awt.*;
import java.io.File;

import static valus.Strings.WORK_USER_NAME;

public class TFrame extends JFrame {
    public TFrame() {
        try {
            File file = new File("./", "settings.xml");
            AppSettings.clear();
            AppSettings.load(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Dimension sSize = Toolkit.getDefaultToolkit().getScreenSize();

        setBounds(30, 30, sSize.width - 160, sSize.height - 160);
        setTitle(AppSettings.get("APPName") + " " + WORK_USER_NAME);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Image image = Toolkit.getDefaultToolkit().createImage(getClass().getResource("dd2.png"));
        setIconImage(image);

        new LoginDialog(new JFrame());
        setTitle(AppSettings.get("APPName") + " " + WORK_USER_NAME);

        //Запуск МенюБара
        GMenuBar mb = new GMenuBar();
        setJMenuBar(mb);
        mb.setEnabled(true);

        // Запуск таббетПанели
        add(new TTabbetPanel(mb));

        setVisible(true);
    }
}
