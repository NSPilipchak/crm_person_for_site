package blogic;

import dal.PersonDAO2017;
import dal.PersonDAO_MySQL_Hibernate2017;
import view.TDialog.TDialogPanel2017;

import javax.swing.table.AbstractTableModel;
import java.text.ParseException;
import java.util.ArrayList;

import static valus.Strings.EDIT_LAST_YEAR;

/**
 * Created by hammer on 01.05.2018.
 */
public class PersonLastYearDM extends AbstractTableModel {

    private PersonDAO2017 pd = null;
    ArrayList<BookingPerson2017> pp = null;
    public int row;
    private String searchPerson = "";

    public PersonLastYearDM(String searchPerson) {
        this.searchPerson = searchPerson;
        pd = new PersonDAO_MySQL_Hibernate2017();
        pp = pd.getPersonByChekinn(searchPerson);
    }

    public void update() {
        TDialogPanel2017 td;
        try {
            td = new TDialogPanel2017(EDIT_LAST_YEAR);
            td.setModal(true);

            int i = row;
            BookingPerson2017 bp;

            if (i >= 0) {
                bp = pp.get(i);
                td.setTitle("Просмотр карточки: " + bp.getLastname() + " " + bp.getFirstname());
                td.setPerson(bp);
            }
            td.setVisible(true);
            if (td.ret.equals("Ok")) {
                pd.update(td.getPerson());
                pp = pd.getPersonByChekinn(searchPerson);
                fireTableDataChanged();
            }
        } catch (ParseException e1) {
            e1.printStackTrace();
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
        if (pp.size() == 0)
            return null;
        BookingPerson2017 p = pp.get(rowIndex);
        Object ret = null;
        switch (columnIndex) {
            case 0:
                ret = p.getId();
                break;
            case 1:
                ret = p.getFirstname() + " " + p.getLastname();
                break;
            case 2:
                ret = p.getSmenanum() + ". " + p.getSmenadate();
                break;
            case 3:
                ret = p.getSvnum();
                break;
            case 4:
                ret = p.getVisit();
                break;
        }
        return ret;
    }

    @Override
    public String getColumnName(int col) {
        String[] str = {"ИД", "ФИО", "Смена", "Свид. о.р.", "Визит"};
        return str[col];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (pp.size() == 0)
            return null;
        Class returnValue;
        if ((columnIndex >= 0) && (columnIndex < getColumnCount() - 1)) {
            returnValue = getValueAt(0, columnIndex).getClass();
        } else {
            returnValue = String.class;
        }
        return returnValue;
    }
}
