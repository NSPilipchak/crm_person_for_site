package blogic;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;

import static valus.Strings.COUNT_LEVEL_STATUS_BAR;
import static valus.Strings.TABLE_FONT_SIZE;
import static valus.Strings.itemStatusCard;

/**
 * Created by hammer on 23.05.2017.
 */
public class TableRenderersBaseStatus extends DefaultTableCellRenderer implements TableCellRenderer {

    private int count = 0;
    private Color colorGreen = new Color(0, 130, 59);
    private Color colorGreyLight = new Color(230, 230, 230);

    public TableRenderersBaseStatus() {
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

//        2 Можно выделить жирным цыфры и добавить изменение цвета цифр при приближении к целевому значению.


        Map fontSize = new HashMap();
        fontSize.put(TextAttribute.FAMILY, "Dialog");
        fontSize.put(TextAttribute.SIZE, TABLE_FONT_SIZE);

        Map fontSizeDeflt = new HashMap();
        fontSizeDeflt.put(TextAttribute.FAMILY, "Dialog");
        fontSizeDeflt.put(TextAttribute.SIZE, 12);


        if (row == 1 && column != 0) { //получение значения ячейки
//        if (row == 1) {
            String str = table.getValueAt(row, column) != null ? (String) table.getValueAt(row, column) : "[0]";
            str = str.substring(1, str.length() - 1);
            count = Integer.parseInt(str);
        }
        setHorizontalAlignment(JLabel.CENTER); // отцентровка всех ячеек
        if (row == 0 || column == 0) {
            setFont(getFont().deriveFont(fontSizeDeflt));
            setForeground(Color.BLACK);
            setBackground(Color.WHITE);
        } else if (row == 1 && column != 0) {
            setFont(getFont().deriveFont(fontSize));
            setBackground(colorGreyLight);
            setFont(getFont().deriveFont(Font.BOLD));
            setForeground(column != (table.getColumnCount() - 1) ? colorForCount(count) : Color.BLACK);
        } else if (row >= 2) {
            setFont(getFont().deriveFont(fontSize));
            setBackground(Color.WHITE);
            setForeground(Color.BLACK);
        }
//        setBackground(getColorStatusCard(statusCard));
//        setForeground(Color.BLACK);
        return cell;
    }

    private Color colorForCount(int count) {
        if (count == COUNT_LEVEL_STATUS_BAR)
            return Color.BLUE;
        if (count > (COUNT_LEVEL_STATUS_BAR))
            return Color.RED;
        if (count < COUNT_LEVEL_STATUS_BAR && count > (COUNT_LEVEL_STATUS_BAR - 10))
            return colorGreen;
        return Color.BLACK;
    }
}
