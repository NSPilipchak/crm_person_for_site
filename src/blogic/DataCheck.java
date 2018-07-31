package blogic;

/**
 * Created by hammer on 30.05.2017.
 */
public class DataCheck {

    BookingPersonDM dm = null;

    public DataCheck() {
    }

    public String generateCheck(String str) {

        String first = "" + str.charAt(0);
        String second = "" + str.charAt(2) + str.charAt(3);
        String last = "" + str.substring(5, str.length());
        second = second.toLowerCase();
//        System.out.println("str: " + str + ", first:" + first + ", second:" + second+ ", last: " + last + ", new check: " + first+second+last);
        return first + second + last;
    }

    public boolean getCheckBoxStatus(int flag) {
//        System.out.println("flag == " + flag +" " + (flag != 0 ? true : false));
        return flag != 0 ? true : false;
    }

    public int getCheckBoxStatus(boolean flag) {
//        System.out.println("flag == " + flag +" " + (flag == true ? 1 : 0));
        return flag == true ? 1 : 0;
    }

    public String getPersonStatus(int flag, String chekinn) {
//        System.out.println("flag == " + flag +" " + (flag != 0 ? true : false));
        if (flag != 0) {
            return "Зарегестрирован";
        } else if (flag == 0 && chekinn.equals("-")) {
            return "Удаленный";
        }
        return "Чёрный список";
    }

    public int getPersonStatus(String flag) {
//        System.out.println("flag == " + flag +" " + (flag.equals("Зарегестрирован") ? 1 : 0));
        return flag.equals("Зарегестрирован") ? 1 : 0;
    }


    public void checkDateOnPerson(BookingPerson dm) {
//        System.out.print(dm.getId() + " Дата рождения ");
        dm.setBirth(checkDateMask(dm.getBirth()));
//        System.out.print(dm.getId() + " Паспорт ");
        dm.setPassportdate(checkDateMask(dm.getPassportdate()));
//        System.out.print(dm.getId() + " Cвидетельство ");
        dm.setSvdate(checkDateMask(dm.getSvdate()));
    }

    public void checkDateOnPerson(BookingPerson2017 dm) {
//        System.out.print(dm.getId() + " Дата рождения ");
        dm.setBirth(checkDateMask(dm.getBirth()));
//        System.out.print(dm.getId() + " Паспорт ");
        dm.setPassportdate(checkDateMask(dm.getPassportdate()));
//        System.out.print(dm.getId() + " Cвидетельство ");
        dm.setSvdate(checkDateMask(dm.getSvdate()));
    }

    public String checkDateMask(String date) {
        if (date.length() == 0)
            return date;

        char date2 = date.charAt(2);
//        System.out.println(" Текущая дата: " + date + ", третий знак в дате: \'" + date2 + "\', всего знаков в дате: " + date.length());
        if (date.length() == 10 && date2 == '.') {
            String dd = "" + date.charAt(0) + date.charAt(1);
            String mm = "" + date.charAt(3) + date.charAt(4);
            String yyyy = "" + date.charAt(6) + date.charAt(7) + date.charAt(8) + date.charAt(9);
            date = yyyy + "-" + mm + "-" + dd;
//            System.out.println("->> Процесс формирования строки: dd: " + dd + ", mm: " + mm + " , yyyy: " + yyyy + " = новая дата: " + date);
        }
        return date;
    }
}
