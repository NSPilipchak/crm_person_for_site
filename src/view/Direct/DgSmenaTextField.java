package view.Direct;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;

/**
 * Created by hammer on 17.07.2017.
 */
public class DgSmenaTextField extends JPanel {

    JLabel id;
    JTextField name;
    JTextField places;
    JTextField dateStart;
    JTextField dateEnd;

    void setColorTextField(JTextField comp) {
        comp.setBackground(Color.LIGHT_GRAY);
    }

    public DgSmenaTextField(boolean editStatus) throws ParseException {

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        MaskFormatter dateFormat = new MaskFormatter("##.##.####");
        MaskFormatter dateFormat2 = new MaskFormatter("##.##.####");
        MaskFormatter placesFormat = new MaskFormatter("***");
        placesFormat.setValidCharacters("0123456789");
        MaskFormatter numberFormat = new MaskFormatter("**");
        numberFormat.setValidCharacters("0123456789");

        JLabel idText = new JLabel("Номер смены:");
        id = new JLabel();
        id.setText("0");

        JLabel nameText = new JLabel("Название:");
        name = new JTextField();
        name.setEditable(editStatus);

        JLabel openText = new JLabel("Открыто мест:");
        places = new JTextField();//new JFormattedTextField(placesFormat);
        places.setEditable(editStatus);

        JLabel dateStartText = new JLabel("Дата заезда:");
        dateStart = new JFormattedTextField(dateFormat);
        dateStart.setEditable(editStatus);

        JLabel dateEndText = new JLabel("Дата выезда:");
        dateEnd = new JFormattedTextField(dateFormat2);
        dateEnd.setEditable(editStatus);

        setColorTextField(name);
        setColorTextField(places);
        setColorTextField(dateStart);
        setColorTextField(dateEnd);

        JPanel containerTextField = new JPanel();
        containerTextField.setLayout(new GridLayout(11, 4));

        containerTextField.add(idText);
        containerTextField.add(id);
        containerTextField.add(nameText);
        containerTextField.add(name);
        containerTextField.add(openText);
        containerTextField.add(places);
        containerTextField.add(dateStartText);
        containerTextField.add(dateStart);
        containerTextField.add(dateEndText);
        containerTextField.add(dateEnd);

        add(containerTextField, BorderLayout.CENTER);
    }
}
