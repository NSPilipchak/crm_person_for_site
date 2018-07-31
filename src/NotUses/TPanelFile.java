package NotUses;

import properties.AppSettings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;

/**
 * Created by hammer on 04.07.2017.
 */
public class TPanelFile extends JPanel {
    private final static String FOLDER_FILE = "URL.FOLDER_DOCUM_FILE";
    JButton btnOk = null;

    FileLinkDM fldm = null;
    private int uri;

    private PressBtnTrue aPressBtnTrue = new PressBtnTrue();

    public TPanelFile(int id){
        uri = id;
        setLayout(new BorderLayout());
        setSize(50,50);

        fldm = new FileLinkDM(id);
        JTable jTable = new JTable(fldm);
        JScrollPane jScrollPane = new JScrollPane(jTable);
        add(jScrollPane, BorderLayout.CENTER);

        btnOk = new JButton("Open");
        btnOk.addActionListener(aPressBtnTrue);
        add(btnOk, BorderLayout.NORTH);

    }
    private class PressBtnTrue implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            Desktop desktop = null;
            if(Desktop.isDesktopSupported()){
                desktop = Desktop.getDesktop();
            }
            try {
                desktop.browse(URI.create(AppSettings.get(FOLDER_FILE) + "/"  + uri));
            } catch (IOException ioe){
                ioe.printStackTrace();
            }
        }
    }
}
