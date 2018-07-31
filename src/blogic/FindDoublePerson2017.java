package blogic;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

/**
 * Created by hammer on 19.06.2017.
 */
public class FindDoublePerson2017 extends AbstractTableModel {

    private ArrayList<BookingPerson2017> checkArray = null;
    private ArrayList<BookingPerson2017> doubleArray = new ArrayList<>();

    public FindDoublePerson2017(ArrayList<BookingPerson2017> checkArray) {
        this.checkArray = checkArray;
    }

    public void goCheckArray(String str) {
        switch (str) {
            case "sv":
                findDoubleSv();
                break;
            case "inn":
                findDoubleInn();
                break;
            default:
                break;
        }
    }

    private void findDoubleInn() {

        for (int i = 0; i < checkArray.size(); i++) {
            for (int j = i + 1; j < checkArray.size(); j++) {
                if (checkArray.get(i).getStatus() == 1 && checkArray.get(j).getStatus() == 1) {
                    if (checkArray.get(i).getParentipn().equals(checkArray.get(j).getParentipn())) {
                        doubleArray.add(checkArray.get(i));
                        doubleArray.add(checkArray.get(j));
                    }
                }
            }
        }
    }

    private void findDoubleSv() {
        for (int i = 0; i < checkArray.size(); i++) {
            for (int j = i + 1; j < checkArray.size(); j++) {
//                System.out.println("i = " + i + " Svnum " + checkArray.get(i).getCheckinn() + " equals ==>> j = " + j + " Svnum " + checkArray.get(j).getCheckinn());
                if (checkArray.get(i).getStatus() == 1 && checkArray.get(j).getStatus() == 1) {
                    if (checkArray.get(i).getCheckinn().equals(checkArray.get(j).getCheckinn())) {
                        doubleArray.add(checkArray.get(i));
                        doubleArray.add(checkArray.get(j));
                    }
                }
            }
        }
    }

    @Override
    public int getRowCount() {
        return doubleArray.size();
    }

    @Override
    public int getColumnCount() {
        return 11;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        BookingPerson2017 p = doubleArray.get(rowIndex);
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
            case 10: //"Примечание"
                ret = p.getComment();
                break;
        }
        return ret;
    }
}
