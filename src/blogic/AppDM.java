package blogic;

import dal.LibraryDAO;
import dal.LibraryDAO_MySQL_Hibernate;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class AppDM extends AbstractTableModel {
    ArrayList<App> pp = null;
    public int row;
    LibraryDAO dm;

    public AppDM() {
        dm = new LibraryDAO_MySQL_Hibernate();
        pp = dm.getAppList();
    }

    @Override
    public int getRowCount() {
        return pp.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        App a = pp.get(rowIndex);
        Object ret = null;
        switch (columnIndex) {
            case 0:
                ret = a.getMkey();
                break;
            case 1:
                ret = a.getValue();
                break;
        }
        return ret;
    }

    @Override
    public String getColumnName(int col) {
        String[] str = {"Key", "Value"};
        return str[col];
    }
}
