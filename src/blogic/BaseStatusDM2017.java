package blogic;

import dal.PersonDAO;
import dal.PersonDAO2017;
import dal.PersonDAO_MySQL_Hibernate;
import dal.PersonDAO_MySQL_Hibernate2017;

import javax.swing.table.AbstractTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static valus.Strings.DB_AUTO_UPDATE;
import static valus.Strings.DB_UPDATE_TIME;

/**
 * Created by hammer on 26.05.2017.
 */
public class BaseStatusDM2017 extends AbstractTableModel {

    private PersonDAO2017 pd = null;
    private String[] bs = null;

    public BaseStatusDM2017() {
        pd = new PersonDAO_MySQL_Hibernate2017();
        bs = pd.checkBase();

        UpdateTable up = new UpdateTable();
        up.setDaemon(true);
        if (DB_AUTO_UPDATE) {
            up.start();
        }
    }

    public ActionRead bsRead = new ActionRead();

    @Override
    public int getRowCount() {
        return 1;
    }

    @Override
    public int getColumnCount() {
        return bs.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object ret = null;
        ret = "См. №";
        switch (columnIndex) {
            case 0:
                ret += "1 " + bs[0];
                break;
            case 1:
                ret += "2 " + bs[1];
                break;
            case 2:
                ret += "3 " + bs[2];
                break;
            case 3:
                ret += "4 " + bs[3];
                break;
            case 4:
                ret += "5 " + bs[4];
                break;
            case 5:
                ret += "6 " + bs[5];
                break;
            case 6:
                ret += "7 " + bs[6];
                break;
            case 7:
                ret += "8 " + bs[7];
                break;
            case 8:
                ret += "9 " + bs[8];
                break;
            case 9:
                ret += "10 " + bs[9];
                break;
            case 10:
                ret += "11 " + bs[10];
                break;
            case 11:
                ret += "12 " + bs[11];
                break;
            case 12:
                ret += "13 " + bs[12];
                break;
        }
        return ret;
    }

    private class ActionRead implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            bs = pd.checkBase();
            fireTableDataChanged();
        }
    }

    private class UpdateTable extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    bs = pd.checkBase();
                    fireTableDataChanged();
                    Thread.sleep(DB_UPDATE_TIME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
