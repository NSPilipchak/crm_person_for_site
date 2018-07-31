package reporting.Report02;

import blogic.BookingPerson;
import dal.ReportDAO;
import dal.ReportDAO_Hibernate;
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
 * отчет для формирования сквозного спика резервистов
 */
public class Report02 {

    private ArrayList<EntityR02> list = new ArrayList<>();
    private static final String FILE = "report02";
    private Map<String, Object> map = new HashMap<>();
    private ReportDAO dao = null;

    public Report02() {
        dao = new ReportDAO_Hibernate();
        map.put("s", "b");
    }

    public void createReport() {
        list = dao.getListReport02();
//        sortArrayList(list);

//        for (int i = 0; i < list.size(); i++) {
//            System.out.println(list.get(i).toString());
//        }

        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(REPORT_PATH + FILE + ".jrxml");
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list, false);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,
                    map,
                    dataSource);

            if (SAVE_GENERATED_REPORT) {
                JasperExportManager.exportReportToPdfFile(jasperPrint,
                        GENERATED_REPORT_PATH
                                + FILE
                                + UtilsForReport.createFileName("")
                                + ".pdf");
            }

            JDialog dialog = new JDialog();
            dialog.setLayout(null);
            dialog.setBounds(50, 50, 1200, 800);
            dialog.setLayout(new BorderLayout());
            dialog.setTitle("Сквозной список детей зарегестрировавшихся в резерв");
            dialog.add(new JRViewer(jasperPrint), BorderLayout.CENTER);
            dialog.setModal(true);
            dialog.setVisible(true);

        } catch (JRException e) {
            e.printStackTrace();
        }
    }

//    private ArrayList<EntityR02> sortArrayList(ArrayList<EntityR02> dm) {
//        dm.sort(EntityR02::compareTo);
//        for (int i = 0; i < dm.size(); i++) {
//            dm.get(i).setNumber(i + 1);
//        }
//        return dm;
//    }
}
