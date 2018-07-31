package reporting;

import blogic.BookingPerson;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JRViewer;

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
public class Report01 {

    private ArrayList<EntityR01> list = new ArrayList<>();
    private static final String FILE = "report01";
    private Map<String, Object> map = new HashMap<>();

    public Report01() {
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
                                + UtilsForReport.createFileName("")
                                + ".pdf");
            }

            JDialog dialog = new JDialog();
            dialog.setLayout(null);
            dialog.setBounds(50, 50, 1200, 800);
            dialog.setLayout(new BorderLayout());
            dialog.setTitle("Список на выдачу путевок");
            dialog.add(new JRViewer(jasperPrint), BorderLayout.CENTER);
            dialog.setModal(true);
            dialog.setVisible(true);

        } catch (JRException e) {
            e.printStackTrace();
        }
    }

    public void putParameters() {
        System.out.println("putParameters: readSmenaNumber " + readSmenaNumber + ", readSmenaNumber " + readSmenaNumber);
        map.put("Smena_number", readSmenaNumber + "");
        map.put("Smena_dateStart", itemSmenaDateRus[readSmenaNumber ]);
        map.put("Smena_dateEnd", UtilsForReport.getLastDaySmena(itemSmenaDateRus[readSmenaNumber]));
    }

    public void putData(ArrayList<BookingPerson> dm) {
        String adress;
        for (int i = 0; i < dm.size(); i++) {
            adress = dm.get(i).getCity() + ", "
                    + dm.get(i).getStreet() + ", буд. " + dm.get(i).getHouse();
            if (dm.get(i).getCorp().length() != 0)
                adress += ", корп. " + dm.get(i).getCorp();
            if (dm.get(i).getFlat().length() != 0)
                adress += ", кв. " + dm.get(i).getFlat();

            list.add(new EntityR01(
                    i + 1,
                    dm.get(i).getParentname() + "",
                    dm.get(i).getPassportnum() + "",
                    adress + "",
                    dm.get(i).getLastname() + " " + dm.get(i).getFirstname() + " " + dm.get(i).getMiddlename(),
                    dm.get(i).getSvnum() + "",
                    ""));
        }
        UtilsForReport.sortArrayList(list);
    }
}
