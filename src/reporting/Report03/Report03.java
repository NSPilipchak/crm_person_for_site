package reporting.Report03;

import dal.ReportDAO;
import dal.ReportDAO_Hibernate;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JRViewer;
import reporting.Report02.EntityR02;
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
public class Report03 {

    private ArrayList<EntityR03> list = new ArrayList<>();
    private static final String FILE = "report03";
    private Map<String, Object> map = new HashMap<>();
    private ReportDAO dao = null;

    public Report03() {
        dao = new ReportDAO_Hibernate();
    }

    public void createReport(int smenaNum) {
        list = dao.getListReport03(smenaNum);
        map.put("SmenaNum", smenaNum);
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
            dialog.setTitle("Резервный список на " + smenaNum + "-ю смену");
            dialog.add(new JRViewer(jasperPrint), BorderLayout.CENTER);
            dialog.setModal(true);
            dialog.setVisible(true);

        } catch (JRException e) {
            e.printStackTrace();
        }
    }
}
