package blogic;

import dal.PersonDAO;
import dal.PersonDAO_MySQL_Hibernate;

import javax.swing.table.AbstractTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static valus.Strings.DB_AUTO_UPDATE;
import static valus.Strings.DB_UPDATE_TIME;

/**
 * Created by hammer on 26.05.2017.
 */
public class BaseStatusDM extends AbstractTableModel {

    private PersonDAO pd = null;
    private String[][] bs = null;
    private int[] reservCount = null;
    private ArrayList<Smena> smenas = null;

    public BaseStatusDM() {
        pd = new PersonDAO_MySQL_Hibernate();
        smenas = pd.readSmena();
        bs = pd.checkBase(smenas.size());
        reservCount = pd.checkReserv(smenas.size());

        UpdateTable up = new UpdateTable();
        up.setDaemon(true);
        up.start();
    }

    public ActionRead bsRead = new ActionRead();

    @Override
    public int getRowCount() {
        return 6;
    }

    @Override
    public int getColumnCount() {
        return bs.length + 1;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object ret = null;

        if (columnIndex == 0) {
            if (rowIndex == 0)
                ret = "№/Дата";
            else if (rowIndex == 1)
                ret = "Итого занято";
            else if (rowIndex == 2)
                ret = "Резерв";
            else if (rowIndex == 3)
                ret = "2-й этап";
            else if (rowIndex == 4)
                ret = "Выдано";
            else if (rowIndex == 5)
                ret = "Посетило";
            return ret;
        }

        if (rowIndex == 0) {
            if (columnIndex < 14)
                ret = "№" + (columnIndex) + "/" + smenas.get(columnIndex - 1).getDate_start();
            else
                ret = "Резерв";
            return ret;
        } else if (rowIndex == 1) {
            ret = bs[columnIndex - 1][0];
        } else if (rowIndex == 2) {
            ret = reservCount[columnIndex - 1];
        } else if (rowIndex == 3) {
            ret = bs[columnIndex - 1][1].substring(1, bs[columnIndex - 1][1].length() - 1);
        } else if (rowIndex == 4) {
            ret = bs[columnIndex - 1][2].substring(1, bs[columnIndex - 1][2].length() - 1);
        } else if (rowIndex == 5) {
            ret = bs[columnIndex - 1][3].substring(1, bs[columnIndex - 1][3].length() - 1);
        }
        return ret;
    }

    private class ActionRead implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            bs = pd.checkBase(smenas.size());
            reservCount = pd.checkReserv(smenas.size());
            fireTableDataChanged();
        }
    }

    private class UpdateTable extends Thread {
        @Override
        public void run() {
            while (DB_AUTO_UPDATE) {
                try {
                    bs = pd.checkBase(smenas.size());
                    reservCount = pd.checkReserv(smenas.size());
                    fireTableDataChanged();
                    Thread.sleep(DB_UPDATE_TIME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
