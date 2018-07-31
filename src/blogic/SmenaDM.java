package blogic;

import dal.LibraryDAO;
import dal.LibraryDAO_MySQL_Hibernate;
import dal.PersonDAO;
import dal.PersonDAO_MySQL_Hibernate;
import view.Direct.DgSmena;
import view.TPanelSmenaDialog;

import javax.swing.table.AbstractTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;

/**
 * Created by hammer on 18.07.2017.
 */
public class SmenaDM extends AbstractTableModel {

    public PersonDAO pd = null;
    ArrayList<Smena> pp = null;
    public int row;

    public ActionRead aRead = new ActionRead();
    public ActionUpdate aUpdate = new ActionUpdate();

    public SmenaDM() {
        pd = new PersonDAO_MySQL_Hibernate();
        pp = pd.readSmena();
    }

    public void refresh() {
        pp = pd.readSmena();
        fireTableDataChanged();
    }

    public void update() {
        try {
            DgSmena td = null;

            td = new DgSmena();

            td.setTitle("Редактирование смены");
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
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Smena p = pp.get(rowIndex);
        Object ret = null;
        switch (columnIndex) {
            case 0:
                ret = p.getId();
                break;
            case 1:
                ret = p.getPlaces();
                break;
            case 2:
                ret = p.getName();
                break;
            case 3:
                ret = p.getDate_start();
                break;
            case 4:
                ret = p.getDate_end();
                break;
        }
        return ret;
    }

    @Override
    public String getColumnName(int col) {
        String[] str = {"Смена №", "Открыто мест", "Имя смены", "Дата начала",
                "Дата окончания"};
        return str[col];
    }

    private class ActionUpdate implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            update();
        }
    }

    private class ActionRead implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            refresh();
        }
    }
}

