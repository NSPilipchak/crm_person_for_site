package blogic;

import dal.PersonDAO2017;
import dal.PersonDAO_MySQL_Hibernate2017;
import view.TDialog.TDialogPanel2017;
import view.TPanelDoubleTable;

import javax.swing.table.AbstractTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;

import static valus.Strings.*;

public class BookingPersonDM2017 extends AbstractTableModel {

    public PersonDAO2017 pd = null;
    ArrayList<BookingPerson2017> pp = null;

    public int row;

    public BookingPersonDM2017() {
        pd = new PersonDAO_MySQL_Hibernate2017();
        pp = pd.read();

        UpdateTable up = new UpdateTable();
        up.setDaemon(true);
        up.start();
    }

    public void refresh() {
        pp = pd.read();
        if (pp.size() != 0)
            fireTableDataChanged();
    }

    public ActionRead aRead = new ActionRead();
    public ActionCreate aCreate = new ActionCreate();
    public ActionUpdate aUpdate = new ActionUpdate();
    public ActionDelete aDelete = new ActionDelete();
    public ActionBlockPerson aBlockPerson = new ActionBlockPerson();
    public ActionTableVisible aTableVisible = new ActionTableVisible();
    public ActionCheckDate actionCheckDate = new ActionCheckDate();
    public ActionFindDouble actionFindDouble = new ActionFindDouble();

    @Override
    public int getRowCount() {
        return pp.size();
    }

    @Override
    public int getColumnCount() {
        if (fullTable == 1)
            return 35; // полная таблица
        else if (fullTable == 2)
            return 11;

        return 17;
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

    @Override
    public String getColumnName(int col) { //"passportdate" ,"district","sendmail"
        if (fullTable == 1) {
            //полный перечень (35 столбцов)
            String[] str = {"ID", "Code", "FirstName", "MiddleName", "LastName", "Sex",
                    "birth", "school", "parentname", "parentipn", "passportnum",
                    "passportinfo", "mainphone", "addphone", "city", "street", "house", "corp",
                    "flat", "svnum", "svdate", "smenadate", "smenanum", "ftime", "gtype", "gnum", "email", "checkinn",
                    "passportdate", "district", "sendmail", "status", "rtype", "visit", "comment"};
            return str[col];
        } else if (fullTable == 2) {
            //таблица для списков на автобус (11столбцов)
            String[] str = {"ID", "Номер регистрации",
                    "ФИО родителя", "Серия паспорта", "Прописка", "ИНН", "Телефон",
                    "ФИО ребенка", "Дата рождения", "Свидетельство о рождении", "Примечание"};
            return str[col];
        } else {
            //Краткий перечень c объеденением значений (17 столбцов)
            String[] str = {"ID", "Статус", "Смена",
                    "Фамилия Имя ребенка", "ДеньРождения",
                    "св.о рождении", "дата св.рожд",
                    "ФИО родителя", "ИНН", "Паспорт", "Паспорт дата", "E-mail",
                    "sendmail", "status", "checkinn", "rtype", "visit"};
            return str[col];
        }
    }

    @Override
    public Object getValueAt(int row, int col) {
        if (pp.size() == 0)
            return null;
        BookingPerson2017 p = pp.get(row);
        Object ret = null;
        if (fullTable == 1) {
//        Полный перечень (35 столбцов)
            switch (col) {
                case 0:
                    ret = p.id;
                    break;
                case 1:
                    ret = p.code;
                    break;
                case 2:
                    ret = p.firstname;
                    break;
                case 3:
                    ret = p.middlename;
                    break;
                case 4:
                    ret = p.lastname;
                    break;
                case 5:
                    ret = p.sex;
                    break;
                case 6:
                    ret = p.birth;
                    break;
                case 7:
                    ret = p.school;
                    break;
                case 8:
                    ret = p.parentname;
                    break;
                case 9:
                    ret = p.parentipn;
                    break;
                case 10:
                    ret = p.passportnum;
                    break;
                case 11:
                    ret = p.passportinfo;
                    break;
                case 12:
                    ret = p.mainphone;
                    break;
                case 13:
                    ret = p.addphone;
                    break;
                case 14:
                    ret = p.city;
                    break;
                case 15:
                    ret = p.street;
                    break;
                case 16:
                    ret = p.house;
                    break;
                case 17:
                    ret = p.corp;
                    break;
                case 18:
                    ret = p.flat;
                    break;
                case 19:
                    ret = p.svnum;
                    break;
                case 20:
                    ret = p.svdate;
                    break;
                case 21:
                    ret = p.smenadate;
                    break;
                case 22:
                    ret = p.smenanum;
                    break;
                case 23:
                    ret = p.ftime;
                    break;
                case 24:
                    ret = p.gtype;
                    break;
                case 25:
                    ret = p.gnum;
                    break;
                case 26:
                    ret = p.email;
                    break;
                case 27:
                    ret = p.checkinn;
                    break;
                case 28:
                    ret = p.passportdate;
                    break;
                case 29:
                    ret = p.district;
                    break;
                case 30:
                    ret = p.sendmail;
                    break;
                case 31:
                    ret = p.status;
                    break;
                case 32:
                    ret = p.rtype;
                    break;
                case 33:
                    ret = p.visit;
                    break;
                case 34:
                    ret = p.comment;
                    break;
            }
            return ret;
        } else if (fullTable == 2) {
            //таблица на смены/автобус (11 столбцов)
            switch (col) {
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
//                            + p.getDistrict() + ", "
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
                    ret = p.getLastname() + " " + p.getFirstname() + " " + p.getMiddlename();
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
        } else {
            //Краткий перечень (17 столбцов)
            switch (col) {
                case 0:
                    ret = p.getId();
                    break;
                case 1:
                    ret = itemStatusCard[p.getStatusCard()];
                    break;
                case 2:
                    ret = p.getSmenanum();
                    break;
                case 3:
                    ret = p.getLastname() + " " + p.getFirstname();
                    break;
                case 4:
                    ret = p.getBirth();
                    break;
                case 5:
                    ret = p.getSvnum();
                    break;
                case 6:
                    ret = p.getSvdate();
                    break;
                case 7:
                    ret = p.getParentname();
                    break;
                case 8:
                    ret = p.getParentipn();
                    break;
                case 9:
                    ret = p.getPassportnum();
                    break;
                case 10:
                    ret = p.getPassportdate();
                    break;
                case 11:
                    ret = p.getEmail();
                    break;
                case 12:
                    ret = p.getSendmail();
                    break;
                case 13:
                    ret = p.getStatus();
                    break;
                case 14:
                    ret = p.getCheckinn();
                    break;
                case 15:
                    ret = p.getRtype();
                    break;
                case 16:
                    ret = p.getVisit();
                    break;
            }
            return ret;
        }
    }

    public void update() {
        TDialogPanel2017 td = null;
        try {
            td = new TDialogPanel2017(EDIT_LAST_YEAR);
            td.setTitle("Оновлення картки");
            td.setModal(true);

            int i = row;
            BookingPerson2017 bp = null;

            if (i >= 0) {
                bp = pp.get(i);
                td.setPerson(bp);
            }
            td.setVisible(true);
            if (td.ret.equals("Ok")) {
                pd.update(td.getPerson());
                pp = pd.read();
                fireTableDataChanged();
            }
        } catch (ParseException e1) {
            e1.printStackTrace();
        }
    }

    private class ActionRead implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            pp = pd.read();
            fireTableDataChanged();

        }
    }

    private class ActionCreate implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            TDialogPanel2017 td = null;
            try {
                td = new TDialogPanel2017(false);
                td.setTitle("Create");
                td.setModal(true);
                td.setVisible(true);
                if (td.ret.equals("Ok")) {
                    pd.create(td.getPerson());
                    pp = pd.read();
                    fireTableDataChanged();
                }
            } catch (ParseException e1) {
                e1.printStackTrace();
            }
        }
    }

    private class ActionUpdate implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            update();
        }
    }

    private class ActionBlockPerson implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            TDialogPanel2017 dd = null;
            try {
                dd = new TDialogPanel2017(false);
                dd.setTitle("Додавання до \"BlackList\"");
                dd.setModal(true);

                int i = row;
                BookingPerson2017 bp = null;
                if (i >= 0) {
                    bp = pp.get(i);
                    dd.setPerson(bp);
                }
                dd.setVisible(true);
                if (dd.ret.equals("Ok")) {
                    bp.setComment(dd.getPerson().getComment());
                    bp.setStatus(0);
                    bp.setCheckinn(dd.chekinn());
                    pd.update(bp);
                    pp = pd.read();
                    fireTableDataChanged();
                }
            } catch (ParseException e1) {
                e1.printStackTrace();
            }
        }
    }

    private class ActionDelete implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            TDialogPanel2017 dd = null;
            try {
                dd = new TDialogPanel2017(false);
                dd.setTitle("DELETE");
                dd.setModal(true);

                int i = row;
                BookingPerson2017 bp = null;
                if (i >= 0) {
                    bp = pp.get(i);
                    dd.setPerson(bp);
                }
                dd.setVisible(true);
                if (dd.ret.equals("Ok")) {
//                    pd.delete(dd.getPerson());  // Удаление непосредственно из базы данных
                    bp.setComment(dd.getPerson().getComment());
                    bp.setStatus(0);            //пометка записи как "удаленная" в базе без физического удаления записи
                    bp.setCheckinn("-");        //--//---
                    pd.update(bp);              //--//--

                    pp = pd.read();
                    fireTableDataChanged();
                }
            } catch (ParseException e1) {
                e1.printStackTrace();
            }
        }
    }

    private class ActionTableVisible implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (fullTable == 0) {
                fullTable = 1;
            } else {
                fullTable = 0;
            }
            pp = pd.read();
            fireTableDataChanged();
        }
    }

    private class ActionCheckDate implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            BookingPerson2017 bp = null;
            DataCheck dc = new DataCheck();
            for (int i = 0; i < pp.size(); i++) {
                bp = pp.get(i);
                dc.checkDateOnPerson(bp);
                if (pp.get(i).getStatus() == 1) {
//                    if (pp.get(i).getBirth().charAt(2) == '.' || pp.get(i).getSvdate().charAt(2) == '.' || pp.get(i).getPassportdate().charAt(2) == '.') {
                    pd.update(bp);
                    pp = pd.read();
                    fireTableDataChanged();
//                    }
                }
            }
        }
    }

    private class ActionFindDouble implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            //Поиск двойников путем вывода списка персон с повторением Серии свидетельства о рождении
            FindDoublePerson2017 fpd = new FindDoublePerson2017(pp);
            String str = e.getActionCommand();
            fpd.goCheckArray(str);
            TPanelDoubleTable table = new TPanelDoubleTable(fpd);
            table.setModal(true);
            table.setVisible(true);
            fireTableDataChanged();
        }
    }

    private class UpdateTable extends Thread {
        @Override
        public void run() {
            while (DB_AUTO_UPDATE) {
                try {
                    refresh();
                    Thread.sleep(DB_UPDATE_TIME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}