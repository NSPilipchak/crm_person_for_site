package blogic;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;

import static valus.Strings.TABLE_FONT_SIZE;
import static valus.Strings.itemStatusCard;

/**
 * Created by hammer on 23.05.2017.
 */
public class TableRenderers extends DefaultTableCellRenderer implements TableCellRenderer {

    private int send;
    private int status;
    private String chekin;
    private int visit;
    private int statusCard;
    private int boardingPass;

    private Color colorVisit = Color.LIGHT_GRAY; //new Color(140, 140, 140);
    //    private Color colorVisit = new Color(42, 181, 29);
    private Color colorBoardingPass = new Color(183, 245, 154);
    private Color colorIsSelected = Color.GRAY; //new Color(100, 100, 100);

    public TableRenderers() {
        super();
    }

    @Override
    public Component getTableCellRendererComponent(JTable table,
                                                   Object value,
                                                   boolean isSelected,
                                                   boolean hasFocus,
                                                   int row,
                                                   int column) {

        final Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        send = table.getValueAt(row, 12) != null ? (int) table.getValueAt(row, 12) : 0;
        status = table.getValueAt(row, 13) != null ? (int) table.getValueAt(row, 13) : 1;
        chekin = table.getValueAt(row, 14) != null ? (String) table.getValueAt(row, 14) : "";
        visit = table.getValueAt(row, 16) != null ? (int) table.getValueAt(row, 16) : 0;
        statusCard = table.getValueAt(row, 1) != null ? getCellStatusCard((String) table.getValueAt(row, 1)) : 0;

        if (table.getColumnCount() > 17)
            boardingPass = table.getValueAt(row, 17) != null ? (int) table.getValueAt(row, 17) : 0;
        else
            boardingPass = 0;

        Map fontSize = new HashMap();
        fontSize.put(TextAttribute.FAMILY, "Dialog");
        fontSize.put(TextAttribute.SIZE, TABLE_FONT_SIZE);
        setFont(getFont().deriveFont(fontSize));

        setForeground(getColor(getStatusRow()));
        if (isSelected == false) {
            if (column == 1 && statusCard != 0 && statusCard != 1) {
                setBackground(getColorStatusCard(statusCard));
                setForeground(Color.BLACK);
            } else if (column == 1 && statusCard == 1) {
                setBackground(getColorStatusCard(statusCard));
                setForeground(Color.WHITE);
            } else {
//                setBackground(boardingPass == 1 ? colorVisit : (visit == 1 ? colorBoardingPass : Color.WHITE));
                setBackground(visit == 1 ? colorVisit : (boardingPass == 1 ? colorBoardingPass : Color.WHITE));
//                if (visit == 1 && boardingPass == 1) {
//                    setBackground(colorBoardingPass);
//                } else if (visit == 1 && boardingPass == 0) {
//                    setBackground(colorVisit);
//                } else {
//                    setBackground(Color.WHITE);
//                }
            }
        } else {
            if (column == 1 && statusCard != 0 && statusCard != 1) {
                setBackground(getColorStatusCard(statusCard));
                setForeground(Color.BLACK);
            } else if (column == 1 && statusCard == 1) {
                setBackground(getColorStatusCard(statusCard));
                setForeground(Color.WHITE);
            } else {
                setBackground(colorIsSelected);
            }
        }

        return cell;
    }

    private int getStatusRow() {
        int color = 0;
        if (status == 0) {
            if (chekin.length() == 1) {
                color = 2;          // Красный - удаленная запись
            } else {
                color = 3;          // Фиолетовый - черный список
            }
        } else {
            if (send == 1) {        // Зеленый - письмо в очереди на отправку
                color = 4;
            } else if (send == 2) {
                color = 1;         // Синий - письмо отправленно
            }
        }
        return color;
    }

    private Color getColor(int color) {
        switch (color) {
            case 0:
                return Color.BLACK;
            case 1:
                return Color.BLUE;
            case 2:
                return Color.RED;
            case 3:
                return Color.MAGENTA;
            case 4:
                return Color.GREEN;
        }
        return Color.BLACK;
    }

    private int getCellStatusCard(String statusCard) {

        int ret = 0;
        for (int i = 0; i < itemStatusCard.length; i++) {
            if (itemStatusCard[i].equals(statusCard)) {
                return i;
            }
        }
        return ret;
    }

    private Color getColorStatusCard(int color) {
        switch (color) {
            case 0:
                return Color.WHITE;
            case 1:
                return Color.BLUE;
            case 2:
                return Color.YELLOW;
            case 3:
                return Color.RED;
        }
        return Color.WHITE;
    }
}
