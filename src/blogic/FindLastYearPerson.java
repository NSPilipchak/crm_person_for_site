package blogic;

import dal.PersonDAO;
import dal.PersonDAO2017;
import dal.PersonDAO_MySQL_Hibernate2017;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

/**
 * Created by hammer on 19.06.2017.
 */
public class FindLastYearPerson extends AbstractTableModel {

    private ArrayList<BookingPerson> bpArray = null;
    private ArrayList<BookingPerson2017> bp2017Array = new ArrayList<>();

    private PersonDAO2017 pd2017;
    private BookingPerson2017 p2017;

    public FindLastYearPerson(PersonDAO pd, ArrayList<BookingPerson> bpArray) {
        this.bpArray = bpArray;
        pd2017 = new PersonDAO_MySQL_Hibernate2017();
    }

    @Override
    public int getRowCount() {
        return bpArray.size();
    }

    @Override
    public int getColumnCount() {
        return 12;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (bpArray.size() == 0)
            return null;

        BookingPerson p = bpArray.get(rowIndex);
        bp2017Array = pd2017.getPersonByChekinn(p.checkinn);
        p2017 = null;
        if (bp2017Array.size() != 0)
            p2017 = bp2017Array.get(0);

        Object ret = null;
        switch (columnIndex) {
            case 0: //"ID"
                ret = p.getId();
                break;
            case 1: //"Номер регистрации"
                ret = p.getCode();
                break;
            case 2: //"ФИО родителя"
                ret = p.getParentname();
                break;
            case 3: //"Серия паспорта"
                ret = p.getPassportnum().toUpperCase();// + " от " + p.getPassportdate();
                break;
            case 4: //"Прописка"
                ret = p.getCity() + ", "
                        + p.getDistrict() + ", "
                        + p.getStreet() + ", буд. " + p.getHouse();
                if (p.getCorp().length() != 0)
                    ret += ", корп. " + p.getCorp();
                if (p.getFlat().length() != 0)
                    ret += ", кв. " + p.getFlat();
                break;
            case 5: //"ИНН"
                ret = p.getParentipn();
                break;
            case 6: //"Телефон"
                ret = p.getMainphone();
                break;
            case 7: //"ФИО ребенка"
                ret = p.getFirstname() + " " + p.getMiddlename() + " " + p.getLastname();
                break;
            case 8: //"Дата рождения"
                ret = p.getBirth();
                break;
            case 9: //"Свидетельство о рождении"
                ret = p.getSvnum().toUpperCase();// + " от " + p.getSvdate();
                break;
            case 10: //выборка прошлогоднего номера и смены
                if (p2017 != null)
                    ret = p2017.getId() + ", " +
                            p2017.getLastname() + " " +
                            p2017.getFirstname() + ", " +
                            p2017.getSvnum() + ", " +
                            p2017.getSmenanum() + "." +
                            p2017.getSmenadate();
                else
                    ret = "";
                break;
            case 11: //"Примечание"
                ret = p.getComment();
                break;
        }
        return ret;
    }

    @Override
    public String getColumnName(int col) { //"passportdate" ,"district","sendmail"
        String[] str = {"ID", "Номер регистрации",
                "ФИО родителя", "Серия паспорта", "Прописка", "ИНН", "Телефон",
                "ФИО ребенка", "Дата рождения", "Свидетельство о рождении", "Прошлый год", "Примечание"};
        return str[col];
    }
}
