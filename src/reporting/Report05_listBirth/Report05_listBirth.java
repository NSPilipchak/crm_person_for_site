package reporting.Report05_listBirth;

import blogic.BookingPerson;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JRViewer;
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
public class Report05_listBirth {

    private ArrayList<EntityR05> list = new ArrayList<>();
    private static final String FILE = "Report05_listBirth";
    private Map<String, Object> map = new HashMap<>();

    public Report05_listBirth() {
        System.out.println("putParameters: readSmenaNumber " + readSmenaNumber + ", readSmenaNumber " + readSmenaNumber);
        map.put("Smena_number", readSmenaNumber + "");
        map.put("Smena_dateStart", itemSmenaDateRus[readSmenaNumber]);
        map.put("Smena_dateEnd", UtilsForReport.getLastDaySmena(itemSmenaDateRus[readSmenaNumber]));
    }

    public void createReport() {
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(REPORT_PATH + FILE + ".jrxml");
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,
                    map,
                    dataSource);

            if (SAVE_GENERATED_REPORT) {
                JasperExportManager.exportReportToPdfFile(jasperPrint,
                        GENERATED_REPORT_PATH
                                + FILE
                                + "listBirth_№"
                                + readSmenaNumber
                                + UtilsForReport.createFileName("")
                                + ".pdf");
            }

            JDialog dialog = new JDialog();
            dialog.setLayout(null);
            dialog.setBounds(50, 50, 1300, 800);
            dialog.setLayout(new BorderLayout());
            dialog.setTitle("Список именинников на смену №" +
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
            if (UtilsForReport.birthday(itemSmenaDateRus[readSmenaNumber],
                    UtilsForReport.getLastDaySmena(itemSmenaDateRus[readSmenaNumber]),
                    dm.get(i).getBirth())) {
                list.add(new EntityR05(
                        dm.get(i).getId(),
                        dm.get(i).getCode() + "",
                        dm.get(i).getLastname() + " " + dm.get(i).getFirstname() + " " + dm.get(i).getMiddlename(),
                        dm.get(i).getBirth() + ""));
            }
        }
        list.sort(EntityR05::compareTo);
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setId(i + 1);
        }
    }


}
