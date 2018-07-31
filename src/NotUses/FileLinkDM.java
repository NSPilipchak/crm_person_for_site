package NotUses;

import properties.AppSettings;

import javax.swing.table.AbstractTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

/**
 * Created by hammer on 05.07.2017.
 */
public class FileLinkDM extends AbstractTableModel {

    ArrayList<FileLink> fileLinks = null;
    private final static String FOLDER_FILE = "URL.FOLDER_DOCUM_FILE";

    public ActionRead aRead = new ActionRead();

    public FileLinkDM(int id) {
        fileLinks = fileList(id);
    }

    @Override
    public int getRowCount() {
        return fileLinks.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        FileLink fileLink = fileLinks.get(rowIndex);
        Object ret = null;
        switch (columnIndex) {
            case 0:
                ret = fileLink.getId();
                break;
            case 1:
                ret = fileLink.getName();
                break;
            case 2:
                ret = fileLink.getFile();
                break;
            case 3:
                ret = fileLink.getLink();
                break;
        }
        return ret;
    }

    private class ActionRead implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            fileLinks = fileList(100);
            fireTableDataChanged();
        }
    }

    private ArrayList<FileLink> fileList (int idPerson){
        ArrayList<FileLink> fileLinks = new ArrayList<>();
        String list[] = new File(AppSettings.get(FOLDER_FILE) + "/" + idPerson).list();
        String str = "";
        for(int i = 0 ; i < list.length ; i++){
            FileLink fl = new FileLink();
            fl.setId(i);
            fl.setFile(new File(list[i]));
            fl.setName(list[i]);
            str = FOLDER_FILE + "/" + list[i];
            fl.setLink(str);
            fileLinks.add(fl);
            System.out.println(list[i]);
        }
        return fileLinks;
    }
}
