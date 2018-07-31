package reporting.Report04;

import blogic.BookingPerson;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JRViewer;
import reporting.EntityR01;
import reporting.UtilsForReport;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static valus.Strings.*;

/**
 * Created by hammer on 16.05.2018.
 * <p>
 * отчет для формирования спика на выдачу путевок
 */
public class Report04 {

    private ArrayList<EntityR04> list = new ArrayList<>();
    private static final String FILE = "Report04_manyPass";
    private Map<String, Object> map = new HashMap<>();

    public Report04() {
        map.put("s", "b");
    }

    public void createReport() {
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(REPORT_PATH + FILE + ".jrxml");
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,
                    map,
                    dataSource);

            if (SAVE_GENERATED_REPORT) {
//            if (false) {
                JasperExportManager.exportReportToPdfFile(jasperPrint,
                        GENERATED_REPORT_PATH
                                + FILE
                                + "multiPass_№"
                                + readSmenaNumber
                                + UtilsForReport.createFileName("")
                                + ".pdf");
            }

            JDialog dialog = new JDialog();
            dialog.setLayout(null);
            dialog.setBounds(50, 50, 1300, 800);
            dialog.setLayout(new BorderLayout());
            dialog.setTitle("Пакет посадочных путевок на смену №" +
                    readSmenaNumber +
                    " с " +
                    itemSmenaDateRus[readSmenaNumber] +
                    " по " +
                    UtilsForReport.getLastDaySmena(itemSmenaDateRus[readSmenaNumber]));
            dialog.add(new JRViewer(jasperPrint), BorderLayout.CENTER);
            dialog.setModal(true);
            dialog.setVisible(true);

        } catch (JRException e) {
            e.printStackTrace();
        }
    }

    public void putData(ArrayList<BookingPerson> dm) {
        for (int i = 0; i < dm.size(); i++) {
            list.add(new EntityR04(
                    dm.get(i).getId(),
                    dm.get(i).getCode() + "",
                    dm.get(i).getParentname() + "",
                    dm.get(i).getPassportnum() + "",
                    dm.get(i).getPassportinfo() + "",
                    dm.get(i).getPassportdate() + "",
                    dm.get(i).getLastname() + " " + dm.get(i).getFirstname() + " " + dm.get(i).getMiddlename(),
                    dm.get(i).getBirth() + "",
                    dm.get(i).getSmenadate() + "",
                    UtilsForReport.getLastDaySmena(dm.get(i).getSmenadate()) + "",
                    dm.get(i).getParentipn() + ""));
        }
        list.sort(EntityR04::compareTo);
    }
}
