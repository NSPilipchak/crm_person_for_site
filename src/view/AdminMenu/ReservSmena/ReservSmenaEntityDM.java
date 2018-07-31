package view.AdminMenu.ReservSmena;

import blogic.Smena;
import dal.LibraryDAO;
import dal.LibraryDAO_MySQL_Hibernate;
import dal.PersonDAO;
import dal.PersonDAO_MySQL_Hibernate;
import org.json.JSONArray;
import org.json.JSONException;
import reporting.UtilsForReport;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by hammer on 17.05.2018.
 */
public class ReservSmenaEntityDM extends AbstractTableModel {

    ArrayList<ReservSmenaEntity> rs = null;
    ArrayList<Smena> smenas = null;
    LibraryDAO lib = null;
    PersonDAO pd = null;
    public int row;
    private String[] smenaString;

    public ActionListenerEdit aAddSmena = new ActionListenerEdit();
    public ActionListenerEdit aUpdateSmena = new ActionListenerEdit();
    public ActionListenerEdit aDeleteSmena = new ActionListenerEdit();

    public ReservSmenaEntityDM(LibraryDAO lib) {
        this.lib = lib;
        pd = new PersonDAO_MySQL_Hibernate();
        smenas = pd.readSmena();
        rs = loadArray(lib.getValueByKey("ReservSmena"));
    }

    public void refresh() {
        rs = loadArray(lib.getValueByKey("ReservSmena"));
        fireTableDataChanged();
    }

    private ArrayList<ReservSmenaEntity> loadArray(String reservedSmena) {
        ArrayList<ReservSmenaEntity> arr = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(reservedSmena);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONArray jsonArray1 = jsonArray.getJSONArray(i);
                arr.add(new ReservSmenaEntity(jsonArray1.getInt(0), "", jsonArray1.getInt(1)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < arr.size(); i++) {
            int id = arr.get(i).getId();
            String data = smenas.get(id - 1).getDate_start();
            arr.get(i).setData(UtilsForReport.reversDateToEuro(data));
        }
        arr.sort(ReservSmenaEntity::compareTo);
        return arr;
    }

    public String getArrayString() {
        int[][] arr = new int[rs.size()][2];
        for (int i = 0; i < rs.size(); i++) {
            arr[i][0] = rs.get(i).getId();
            arr[i][1] = rs.get(i).getMax();
        }
        JSONArray mJSONArray = new JSONArray(Arrays.asList(arr));
        return mJSONArray.toString();
    }

    public void deleteSmena() {
        rs.remove(row);
    }

    private void loadSmenaString() {
        smenaString = new String[smenas.size()];
        for (int i = 0; i < smenas.size(); i++) {
            smenaString[i] = smenas.get(i).getId() + " смена с " + smenas.get(i).getDate_start();
        }
    }

    public void addSmena() {
//        System.out.println("addSmena " + row);
        loadSmenaString();
        Object result = JOptionPane.showInputDialog(null,
                "Выберите смену:",
                "Доступные смены",
                JOptionPane.QUESTION_MESSAGE,
                null, smenaString, smenaString[0]);
//        System.out.println(result);
        String str = result.toString();
        str = str.split(" ")[0];
        int i = Integer.parseInt(str);
        boolean add = true;
        for (int x = 0; x < rs.size(); x++) {
            if (rs.get(x).getId() == i) {
                add = false;
            }
        }
        if (add)
            rs.add(new ReservSmenaEntity(smenas.get(i - 1).getId(), smenas.get(i - 1).getDate_start(), 0));
    }

    public void updateSmena() {
        int max = rs.get(row).getMax();
        String str = JOptionPane.showInputDialog("Укажите кол-во доступных мест:", max);
        if (str != null)
            rs.get(row).setMax(Integer.parseInt(str));
    }

    @Override
    public int getRowCount() {
        if (rs == null)
            return 0;
        return rs.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public String getColumnName(int column) {
        String[] str = {"Номер смены", "Дата заезда", "Максимум мест", "Занято"};
        return str[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ReservSmenaEntity p = rs.get(rowIndex);
        Object ret = null;
        switch (columnIndex) {
            case 0:
                ret = p.getId();
                break;
            case 1:
                ret = p.getData();
                break;
            case 2:
                ret = p.getMax();
                break;
        }
        return ret;
    }

    private class ActionListenerEdit implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String str = e.getActionCommand();
            switch (str) {
                case "AddSmena":
                    addSmena();
                    break;
                case "UpdateSmena":
                    updateSmena();
                    break;
                case "DeleteSmena":
                    deleteSmena();
                    break;
            }
            fireTableDataChanged();
        }
    }
}