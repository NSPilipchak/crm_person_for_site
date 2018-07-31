package reporting;

import java.awt.*;
import java.util.*;

import blogic.BookingPerson;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JRViewer;
import net.sf.jasperreports.view.JasperDesignViewer;
import net.sf.jasperreports.view.JasperViewer;
import reporting.UtilsForReport;

import javax.swing.*;

import static valus.Strings.GENERATED_REPORT_PATH;
import static valus.Strings.REPORT_PATH;
import static valus.Strings.SAVE_GENERATED_REPORT;


/**
 * Created by hammer on 14.05.2018.
 * <p>
 * <p>
 * Формирование посадочного талона
 */
public class Report00 {

    private ArrayList list = new ArrayList();
    private static final String FILE = "report00";
    private Map<String, Object> map = new HashMap<>();

    /**
     * MAIN для тестирования отчета
     * public static void main(String[] args) {
     * new Report00();
     * map.put("Ticket_parent", "Сидоренко Петр Иванович");
     * map.put("Ticket_passportNum", "АА 987654");
     * map.put("Ticket_passportInfo", "Бабушкінським РВ ДМУ УМВС України в Дніпропетровській області");
     * map.put("Ticket_passportData", "30.04.2008");
     * map.put("Ticket_children", "Сидоренко Василий Петрович");
     * map.put("Ticket_ID", "6547-123789");
     * map.put("Ticket_birth", "15.05.2010");
     * map.put("Ticket_smenaStart", "04.06.2018");
     * map.put("Ticket_smenaEnd", "12.06.2018");
     * map.put("Ticket_parentInn", "9876543210");
     * createReport();
     * }
     */

    public Report00() {
    }

    public void putData(BookingPerson p) {
//        System.out.println(p.toString());
        map.put("Ticket_parent", p.getParentname());
        map.put("Ticket_passportNum", p.getPassportnum());
        map.put("Ticket_passportInfo", p.getPassportinfo());
        map.put("Ticket_passportData", p.getPassportdate());
        map.put("Ticket_children", p.getLastname() + " " + p.getFirstname() + " " + p.getMiddlename());
        map.put("Ticket_ID", p.getCode());
        map.put("Ticket_birth", p.getBirth());
        map.put("Ticket_smenaStart", p.getSmenadate());
        map.put("Ticket_smenaEnd", UtilsForReport.getLastDaySmena(p.getSmenadate()));
        map.put("Ticket_parentInn", p.getParentipn());
    }

    public void createReport() {

        list.add("Иван Иванов"); // заглушка для работы JasperReport, не хочет работаьь без передачи массива данных

        try {
            /******поиск задублированной библиотеки******/
            /******запуск метода*****/
            //            System.setProperty("jaxp.debug", "1");
            //            OutputJaxpImplementationInfo();

            //загружаем форму отчета для заполения коллекцией
            JasperReport jasperReport = JasperCompileManager.compileReport(REPORT_PATH + FILE + ".jrxml");
            //создаем коллекцию Jasper Report Bean Collection
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);

            // заполняем датасет отчета данными из коллекции
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,
                    map,
                    dataSource);

            if (SAVE_GENERATED_REPORT) {
                JasperExportManager.exportReportToPdfFile(jasperPrint,
                        GENERATED_REPORT_PATH
                                + FILE
                                + UtilsForReport.createFileName("" + map.get("Ticket_ID"))
                                + ".pdf");
//            JasperExportManager.exportReportToXmlFile(jasperPrint, REPORT_PATH + FILE + ".xml", false);
//            JasperExportManager.exportReportToHtmlFile(jasperPrint, REPORT_PATH + FILE + ".html");
            }
//            JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
//            jasperViewer.setTitle("Предварительный просмотр");
//            jasperViewer.setVisible(true);

            JDialog dialog = new JDialog();
            dialog.setLayout(null);
            dialog.setBounds(50, 50, 800, 800);
            dialog.setLayout(new BorderLayout());
            dialog.setTitle("Просмотр посадочного талона");
            dialog.add(new JRViewer(jasperPrint), BorderLayout.CENTER);
            dialog.setModal(true);
//            dialog.pack();
            dialog.setVisible(true);

        } catch (JRException e) {
            e.printStackTrace();
        }
    }

    /******поиск задублированной библиотеки******/
    /******непосредственно реализация метода*****/
//    private static void OutputJaxpImplementationInfo() {
//        System.out.println(getJaxpImplementationInfo("DocumentBuilderFactory", DocumentBuilderFactory.newInstance().getClass()));
//        System.out.println(getJaxpImplementationInfo("XPathFactory", XPathFactory.newInstance().getClass()));
//        System.out.println(getJaxpImplementationInfo("TransformerFactory", TransformerFactory.newInstance().getClass()));
//        System.out.println(getJaxpImplementationInfo("SAXParserFactory", SAXParserFactory.newInstance().getClass()));
//    }
//
//    private static String getJaxpImplementationInfo(String componentName, Class componentClass) {
//        CodeSource source = componentClass.getProtectionDomain().getCodeSource();
//        return MessageFormat.format(
//                "{0} implementation: {1} loaded from: {2}",
//                componentName,
//                componentClass.getName(),
//                source == null ? "Java Runtime" : source.getLocation());
//    }
}
