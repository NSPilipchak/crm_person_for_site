package view;

import view.MenuBar.GMenuBar;

import javax.swing.*;

/**
 * Created by hammer on 26.04.2018.
 */
public class TTabbetPanel extends JTabbedPane {
    public TTabbetPanel(GMenuBar mb) {
        add("Регистрация 2018", new TPanel(mb));
        add("Регистрация 2017", new TPanel2017());
        setVisible(true);
    }
}
