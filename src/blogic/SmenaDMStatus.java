package blogic;

import dal.LibraryDAO;
import dal.LibraryDAO_MySQL_Hibernate;
import dal.PersonDAO;
import dal.PersonDAO_MySQL_Hibernate;
import view.TPanelSmenaDialog;

import javax.swing.table.AbstractTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;

/**
 * Created by hammer on 18.07.2017.
 */
public class SmenaDMStatus extends AbstractTableModel {

    public PersonDAO pd = null;
    ArrayList<Smena> pp = null;
    public int row;
    private LibraryDAO libraryDAO = null;

    public SmenaDMStatus(PersonDAO pd) {
        this.pd = pd;
        pp = pd.readSmena();
        libraryDAO = new LibraryDAO_MySQL_Hibernate();
    }

    public void update() {
        try {
            TPanelSmenaDialog td = null;

            td = new TPanelSmenaDialog();

            td.setTitle("Управление сменой");
            td.setModal(true);
            int i = row;
            Smena smena = null;
            if (i >= 0) {
                smena = pp.get(i);
                td.setSmena(smena);
            }
            td.setVisible(true);
            if (td.ret.equals("Ok")) {
                pd.updateSmena(td.getSmena());
                pp = pd.readSmena();
                fireTableDataChanged();
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getRowCount() {
        return pp.size();
    }

    @Override
    public int getColumnCount() {
        return 9;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Smena p = pp.get(rowIndex);
        Object ret = null;
        switch (columnIndex) {
            case 0:
                ret = p.getId();
                break;
            case 1: //"зарегестрированно"
                ret = libraryDAO.countSmena(1, p.getId(), 1, 0);
                break;
            case 2: //"Открыто мест"
                ret = p.getPlaces();
                break;
            case 3: //"Доступно на сайте"
                ret = p.getPlaces() - libraryDAO.countSmena(0, p.getId(), 1, 0);
                break;
            case 4: //"Занято сайт"
                ret = libraryDAO.countSmena(0, p.getId(), 1, 0);
                break;
            case 5: //"Занято КП"
                ret = libraryDAO.countSmena(2, p.getId(), 1, 0);
                break;
            case 6: //"Имя смены"
                ret = p.getName();
                break;
            case 7:
                ret = p.getDate_start();
                break;
            case 8:
                ret = p.getDate_end();
                break;
        }
        if (ret.toString().equals("0")) {
            ret = "";
        }
        return ret;
    }

    @Override
    public String getColumnName(int col) {
        String[] str = {"Смена", "Зарегестрированно", "Открыто мест", "Свободно на сайте", "Занято сайт",
                "Занято КП", "Имя смены", "Дата начала",
                "Дата окончания"};
        return str[col];
    }
}

