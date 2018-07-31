package blogic;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = BookingPerson.tableName)
public class BookingPerson implements Comparable<BookingPerson> {

    //для подмены имени таблицы в базе MySQL
    public static final String tableName = "booking";

    @Id
    public int id;
    public String code; // varchar(64) COLLATE utf8_bin DEFAULT NULL, -- Код косадки   - id + 6 случайных цифр
    public String firstname; // varchar(250) COLLATE utf8_bin NOT NULL, -- Имя
    public String middlename;// varchar(250) COLLATE utf8_bin NOT NULL, -- Отчество
    public String lastname; // varchar(250) COLLATE utf8_bin NOT NULL, -- Фамилия
    public int sex; // int(11) NOT NULL DEFAULT '0', -- Пол
    public String birth; // date DEFAULT NULL, -- Дата рождения
    public String school; // varchar(250) COLLATE utf8_bin NOT NULL, -- № школы
    public String parentname; // varchar(250) COLLATE utf8_bin NOT NULL, -- ФИО родителя
    public String parentipn; // varchar(250) COLLATE utf8_bin NOT NULL, -- ИНН родителя
    public String passportnum; // varchar(250) COLLATE utf8_bin NOT NULL, -- Серия и № паспорта родителя
    public String passportinfo; // text COLLATE utf8_bin NOT NULL, -- Кем и когда выдан
    public String passportdate; // passportdate date // Пасспорт выдан
    public String mainphone; // varchar(250) COLLATE utf8_bin NOT NULL, -- Основной телефон
    public String addphone; // text COLLATE utf8_bin NOT NULL, -- Дополнительные телефоны
    public String city; // varchar(250) COLLATE utf8_bin NOT NULL, -- Город
    public String district; // district varchar(100) // Район
    public String street; // varchar(250) COLLATE utf8_bin NOT NULL, -- Улица
    public String house; // varchar(20) COLLATE utf8_bin NOT NULL, -- Дом
    public String corp; // varchar(20) COLLATE utf8_bin NOT NULL, -- Корпус, если указан
    public String flat; // varchar(20) COLLATE utf8_bin NOT NULL, -- Квартира
    public String svnum; // varchar(100) COLLATE utf8_bin NOT NULL, -- Серия и номер свидетельства о рождении
    public String svdate; // varchar(100) COLLATE utf8_bin NOT NULL, -- Когда выдан
    public String smenadate; // date NOT NULL, -- Дата смены
    public int smenanum; // int(11) NOT NULL DEFAULT '0', -- Номер смены
    public int ftime; // int(11) NOT NULL DEFAULT '0', -- 0 - первый раз, 1 - уже был
    public int gtype; // int(11) NOT NULL DEFAULT '0', -- Тип группы, 1-младшая, 2-средняя, 3-старшая
    public int gnum; // int(11) NOT NULL DEFAULT '0', -- Номер группы, пока не используется
    public String email; // varchar(200) COLLATE utf8_bin DEFAULT NULL, -- Email
    public String checkinn; // varchar(50) COLLATE utf8_bin DEFAULT NULL -- обработання строка для проверки дубликатов по св. о рождении
    public int sendmail; // sendmail int(11) default 0 // Отправлен Email
    public int status;// `status` int(11) NOT NULL DEFAULT '1',
    public int rtype;
    public int visit;
    public String comment;
    int age;
    String rsmena;
    public int statusCard; // 0 - Новая, 1 - Выдано направление, 2 - Запрошены документы, 3 - Отказ
    private String boardingPass;


    public BookingPerson() {

    }

    public BookingPerson(int id, String code, String firstname, String middlename, String lastname, int sex, String birth, String school, String parentname, String parentipn, String passportnum, String passportinfo, String passportdate, String mainphone, String addphone, String city, String district, String street, String house, String corp, String flat, String svnum, String svdate, String smenadate, int smenanum, int ftime, int gtype, int gnum, String email, String checkinn, int sendmail, int status, int rtype, int visit, String comment, int statusCard, String rsmena, int age, String boardingPass) {
        init(id, code, firstname, middlename, lastname, sex, birth, school, parentname, parentipn, passportnum, passportinfo, passportdate, mainphone, addphone, city, district, street, house, corp, flat, svnum, svdate, smenadate, smenanum, ftime, gtype, gnum, email, checkinn, sendmail, status, rtype, visit, comment, statusCard, rsmena, age, boardingPass);
    }


    public void init(int id, String code, String firstname, String middlename, String lastname, int sex, String birth, String school, String parentname, String parentipn, String passportnum, String passportinfo, String passportdate, String mainphone, String addphone, String city, String district, String street, String house, String corp, String flat, String svnum, String svdate, String smenadate, int smenanum, int ftime, int gtype, int gnum, String email, String checkinn, int sendmail, int status, int rtype, int visit, String comment, int statusCard, String rsmena, int age, String boardingPass) {
        this.id = id;
        this.code = code;
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.sex = sex;
        this.birth = birth;
        this.school = school;
        this.parentname = parentname;
        this.parentipn = parentipn;
        this.passportnum = passportnum;
        this.passportinfo = passportinfo;
        this.passportdate = passportdate;
        this.mainphone = mainphone;
        this.addphone = addphone;
        this.city = city;
        this.district = district;
        this.street = street;
        this.house = house;
        this.corp = corp;
        this.flat = flat;
        this.svnum = svnum;
        this.svdate = svdate;
        this.smenadate = smenadate;
        this.smenanum = smenanum;
        this.ftime = ftime;
        this.gtype = gtype;
        this.gnum = gnum;
        this.email = email;
        this.checkinn = checkinn;
        this.sendmail = sendmail;
        this.status = status;
        this.rtype = rtype;
        this.visit = visit;
        this.comment = comment;
        this.statusCard = statusCard;
        this.age = age;
        this.rsmena = rsmena;
        this.boardingPass = boardingPass;
    }

    @Override
    public String toString() {
        return "BookingPerson{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", firstname='" + firstname + '\'' +
                ", middlename='" + middlename + '\'' +
                ", lastname='" + lastname + '\'' +
                ", sex=" + sex +
                ", birth='" + birth + '\'' +
                ", school='" + school + '\'' +
                ", parentname='" + parentname + '\'' +
                ", parentipn='" + parentipn + '\'' +
                ", passportnum='" + passportnum + '\'' +
                ", passportinfo='" + passportinfo + '\'' +
                ", passportdate='" + passportdate + '\'' +
                ", mainphone='" + mainphone + '\'' +
                ", addphone='" + addphone + '\'' +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                ", street='" + street + '\'' +
                ", house='" + house + '\'' +
                ", corp='" + corp + '\'' +
                ", flat='" + flat + '\'' +
                ", svnum='" + svnum + '\'' +
                ", svdate='" + svdate + '\'' +
                ", smenadate='" + smenadate + '\'' +
                ", smenanum=" + smenanum +
                ", ftime=" + ftime +
                ", gtype=" + gtype +
                ", gnum=" + gnum +
                ", email='" + email + '\'' +
                ", checkinn='" + checkinn + '\'' +
                ", sendmail='" + sendmail + '\'' +
                ", status='" + status + '\'' +
                ", rtype='" + rtype + '\'' +
                ", visit='" + visit + '\'' +
                ", comment='" + comment + '\'' +
                ", statusCard='" + statusCard + '\'' +
                ", rsmena='" + rsmena + '\'' +
                ", age='" + age + '\'' +
                '}';
    }

    @Override
    public int compareTo(BookingPerson o) {
        return checkinn.compareToIgnoreCase(o.checkinn);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getBirth() {
        return EntityUtility.YYYYMMDDtoDDMMYYY(birth);
    }

    public void setBirth(String birth) {
        this.birth = EntityUtility.DDMMYYYYtoYYYYMMDD(birth);
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getParentname() {
        return parentname;
    }

    public void setParentname(String parentname) {
        this.parentname = parentname;
    }

    public String getParentipn() {
        return parentipn;
    }

    public void setParentipn(String parentipn) {
        this.parentipn = parentipn;
    }

    public String getPassportnum() {
        return passportnum;
    }

    public void setPassportnum(String passportnum) {
        this.passportnum = passportnum;
    }

    public String getPassportinfo() {
        return passportinfo;
    }

    public void setPassportinfo(String passportinfo) {
        this.passportinfo = passportinfo;
    }

    public String getMainphone() {
        return mainphone;
    }

    public void setMainphone(String mainphone) {
        this.mainphone = mainphone;
    }

    public String getAddphone() {
        return addphone;
    }

    public void setAddphone(String addphone) {
        this.addphone = addphone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getCorp() {
        return corp;
    }

    public void setCorp(String corp) {
        this.corp = corp;
    }

    public String getFlat() {
        return flat;
    }

    public void setFlat(String flat) {
        this.flat = flat;
    }

    public String getSvnum() {
        return svnum;
    }

    public void setSvnum(String svnum) {
        this.svnum = svnum;
    }

    public String getSvdate() {
        return EntityUtility.YYYYMMDDtoDDMMYYY(svdate);
    }

    public void setSvdate(String svdate) {
        this.svdate = EntityUtility.DDMMYYYYtoYYYYMMDD(svdate);
    }

    public String getSmenadate() {
        return EntityUtility.YYYYMMDDtoDDMMYYY(smenadate);
    }

    public void setSmenadate(String smenadate) {
        this.smenadate = EntityUtility.DDMMYYYYtoYYYYMMDD(smenadate);
    }

    public int getSmenanum() {
        return smenanum;
    }

    public void setSmenanum(int smenanum) {
        this.smenanum = smenanum;
    }

    public int getFtime() {
        return ftime;
    }

    public void setFtime(int ftime) {
        this.ftime = ftime;
    }

    public int getGtype() {
        return gtype;
    }

    public void setGtype(int gtype) {
        this.gtype = gtype;
    }

    public int getGnum() {
        return gnum;
    }

    public void setGnum(int gnum) {
        this.gnum = gnum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCheckinn() {
        return checkinn;
    }

    public void setCheckinn(String checkinn) {
        this.checkinn = checkinn;
    }

    public String getPassportdate() {
        return EntityUtility.YYYYMMDDtoDDMMYYY(passportdate);
    }

    public void setPassportdate(String passportdate) {
        this.passportdate = EntityUtility.DDMMYYYYtoYYYYMMDD(passportdate);
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public int getSendmail() {
        return sendmail;
    }

    public void setSendmail(int sendmail) {
        this.sendmail = sendmail;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getRtype() {
        return rtype;
    }

    public void setRtype(int rtype) {
        this.rtype = rtype;
    }

    public int getVisit() {
        return visit;
    }

    public void setVisit(int visit) {
        this.visit = visit;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getStatusCard() {
        return statusCard;
    }

    public void setStatusCard(int statusCard) {
        this.statusCard = statusCard;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getRsmena() {
        return rsmena;
    }

    public void setRsmena(String rsmena) {
        this.rsmena = rsmena;
    }

    public String getBoardingPass() {
        return boardingPass;
    }

    public void setBoardingPass(String boardingPass) {
        this.boardingPass = boardingPass;
    }
}
