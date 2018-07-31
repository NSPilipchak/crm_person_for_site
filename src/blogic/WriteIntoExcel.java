package blogic;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;

import static valus.Strings.*;

/**
 * Created by hammer on 16.06.2017.
 */
public class WriteIntoExcel {

    JTable table;
    Workbook book;
    Sheet sheet;
    Row row;
    int currentRow;
    boolean saveFullTable;
    private int lastYear;

    public WriteIntoExcel() {
        if (fullTable == 1)
            saveFullTable = true;
//        JOptionPane.showMessageDialog(null, null, "WriteIntoExcel", JOptionPane.INFORMATION_MESSAGE);
    }

    private void init(JTable tbl) {
        currentRow = 0;
        table = tbl;
        book = new HSSFWorkbook();
        sheet = book.createSheet("Список");
        row = sheet.createRow(currentRow);
    }

    @SuppressWarnings("deprecation")
    public void writeIntoExcel(File file, JTable tbl, int lastYear) throws IOException {
        this.lastYear = lastYear;
        init(tbl);
        autor();
        headTable();
        loadTable();
        sizeColumn();
        writeFile(file);
    }


    @SuppressWarnings("deprecation")
    public void writeBusIntoExcel(File file, JTable tbl) throws IOException {
        init(tbl);
        autor();
        headDoc();

        int start = 0;
        int currentEnd = 0;
        int end = table.getRowCount();

        for (int i = 0; i < listBus.length; i++) {
            currentRow++;
            row = sheet.createRow(currentRow);
            row.createCell(0).setCellValue("Автобус №" + (i + 1) + " кiлькість місць " + listBus[i] + "ч.");
            sheet.addMergedRegion(new CellRangeAddress(
                    currentRow, //first row (0-based)
                    currentRow, //last row (0-based)
                    0, //first column (0-based)
                    10 //last column (0-based)
            ));
            HSSFFont font = (HSSFFont) book.createFont();
            HSSFCellStyle style = (HSSFCellStyle) book.createCellStyle();
            font.setFontHeightInPoints((short) 12);
            font.setBold(true);
            style.setFont(font);
            style.setAlignment(HorizontalAlignment.CENTER);
            setStyle(row, style);

            currentRow++;
            headTable();
            currentEnd = currentEnd + Integer.parseInt(listBus[i]);
            if (currentEnd > end)
                currentEnd = end;

            loadTable(start, currentEnd);
            start = currentEnd;
        }
        sizeColumn();
        writeFile(file);
    }

    private void writeFile(File file) {
        try (FileOutputStream out = new FileOutputStream(file)) {
            book.write(out);
            book.close();
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Не удалось сохранить файл", "Упс, что то не так...", JOptionPane.ERROR_MESSAGE);
        }
        JOptionPane.showMessageDialog(null, file, "Файл сохранен", JOptionPane.INFORMATION_MESSAGE);
    }

    private void sizeColumn() {
        for (int i = 1; i < 11; i++) {
            sheet.autoSizeColumn(i);
        }
    }

    private void loadTable(int start, int end) {
        HSSFCellStyle style = (HSSFCellStyle) book.createCellStyle();
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        for (int i = start; i < end; i++) {
            row = sheet.createRow(currentRow++);
            for (int j = 0; j < 11; j++) {
                row.createCell(j).setCellValue(table.getValueAt(i, j) == null ? "" :
                        table.getValueAt(i, j).toString().equals("null") ? "" :
                                table.getValueAt(i, j).toString());
            }
            setStyle(row, style);
        }
    }

    private void loadTable() {
        HSSFCellStyle style = (HSSFCellStyle) book.createCellStyle();
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());

        int width = 11;
        if (lastYear == 1)
            width = 12;
        if (saveFullTable)
            width = 35;

        for (int i = 0; i < table.getRowCount(); i++) {
            row = sheet.createRow(currentRow++);
            for (int j = 0; j < width; j++) {
                row.createCell(j).setCellValue(table.getValueAt(i, j) == null ? "" :
                        table.getValueAt(i, j).toString().equals("null") ? "" :
                                table.getValueAt(i, j).toString());
            }
            setStyle(row, style);
        }
    }

    private void headDoc() {
        currentRow++;
        row = sheet.createRow(currentRow);
        row.createCell(0).setCellValue("Перелік дітей для відвідування дитячого патріотичного табору \"Діти Дніпра\" на " + readSmenaNumber + " зміну.");
        sheet.addMergedRegion(new CellRangeAddress(
                currentRow, //first row (0-based)
                currentRow, //last row (0-based)
                0, //first column (0-based)
                10 //last column (0-based)
        ));
        HSSFFont font = (HSSFFont) book.createFont();
        HSSFCellStyle style = (HSSFCellStyle) book.createCellStyle();
        font.setFontHeightInPoints((short) 15);
        font.setBold(true);
        font.setItalic(true);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);
        setStyle(row, style);
        currentRow++;
    }

    private void headTable() {
        // создаем шрифт
        HSSFFont font = (HSSFFont) book.createFont();
        // создаем стиль для ячейки
        HSSFCellStyle style = (HSSFCellStyle) book.createCellStyle();
        // указываем, что хотим его видеть жирным
        font.setBold(true);
        // и применяем к этому стилю жирный шрифт
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setFont(font);

        row = sheet.createRow(currentRow);
        if (saveFullTable) {
            row.createCell(0).setCellValue("ID");
            row.createCell(1).setCellValue("Code записи");
            row.createCell(2).setCellValue("Имя");
            row.createCell(3).setCellValue("Отчество");
            row.createCell(4).setCellValue("Фамилия");
            row.createCell(5).setCellValue("Пол (1 мальчик, 2 девочка)");
            row.createCell(6).setCellValue("День рождения");
            row.createCell(7).setCellValue("№школы");
            row.createCell(8).setCellValue("ФИО Родителя");
            row.createCell(9).setCellValue("ИНН  Родителя");
            row.createCell(10).setCellValue("Серия паспорта");
            row.createCell(11).setCellValue("Кем выдан");
            row.createCell(12).setCellValue("Основной телефон");
            row.createCell(13).setCellValue("Дополнительный телефон");
            row.createCell(14).setCellValue("Город");
            row.createCell(15).setCellValue("Улица");
            row.createCell(16).setCellValue("Дом");
            row.createCell(17).setCellValue("Корпус");
            row.createCell(18).setCellValue("Квартира");
            row.createCell(19).setCellValue("Номер св.о рожд.");
            row.createCell(20).setCellValue("Дата св. о рожд.");
            row.createCell(21).setCellValue("Дата заезда смены");
            row.createCell(22).setCellValue("№смены");
            row.createCell(23).setCellValue("был/небыл ранее, в прошлых годах");
            row.createCell(24).setCellValue("Тип группы, 1-младшая, 2-средняя, 3-старшая");
            row.createCell(25).setCellValue("Номер группы (не используется)");
            row.createCell(26).setCellValue("E-mail");
            row.createCell(27).setCellValue("проверка совпадений. служебное поле для поиска задвоений. если стоит прочерк, и статус 0 - запись считается удаленной, если присутсвет серия св. и статус 0 - запись попадает в черный список");
            row.createCell(28).setCellValue("дата выдачи паспорта");
            row.createCell(29).setCellValue("Район");
            row.createCell(30).setCellValue("письмо отправленно. 1 - отправленно, 0 - нет. если установить 0, в течении некоторго времени письмо будет автоматически отправленно и установится значение 1");
            row.createCell(31).setCellValue("статус. 0 - удаленная, 1 - живая запись. также статус зависит от поля 'Проверка совпадений'");
            row.createCell(32).setCellValue("Тип ссылки. При выдаче уникальных ссылок группам/кружкам для регистрацции, выставляется порядковый номер уникальной ссылки.");
            row.createCell(33).setCellValue("Визит. отметка о фактическом пребывании ребенка в лагере, которая проставляется сотрудниками кол.центра после получения подписанных родителями документов при посадке на автобус. 1 - был, 0 - нет");
            row.createCell(34).setCellValue("Комментарий");

        } else {
            row.createCell(0).setCellValue("ИД");
            row.createCell(1).setCellValue("№ регистрации");
            row.createCell(2).setCellValue("ФИО родителя");
            row.createCell(3).setCellValue("Паспорт");
            row.createCell(4).setCellValue("Адрес прописки");
            row.createCell(5).setCellValue("ИНН");
            row.createCell(6).setCellValue("Телефон");
            row.createCell(7).setCellValue("ФИО ребенка");
            row.createCell(8).setCellValue("Дата рождения");
            row.createCell(9).setCellValue("Св.о рождении");
            if (lastYear == 0)
                row.createCell(10).setCellValue("Примечание");
            else {
                row.createCell(10).setCellValue("Прошлый год");
                row.createCell(11).setCellValue("Примечание");
            }
        }
        setStyle(row, style);
        currentRow++;
    }

    private void autor() {
        HSSFFont font = (HSSFFont) book.createFont();
        // создаем стиль для ячейки
        HSSFCellStyle style = (HSSFCellStyle) book.createCellStyle();
        font.setFontHeightInPoints((short) 8);
        font.setItalic(true);
//        font.set;
        style.setFont(font);
        SimpleDateFormat formatDate = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        row.createCell(0).setCellValue("Создан " + formatDate.format(new java.util.Date()) + " пользователем " + WORK_USER_NAME + ".");
        setStyle(row, style);
        currentRow++;
    }

    private void setStyle(Row row, HSSFCellStyle style) {
        for (int i = 0; i < row.getPhysicalNumberOfCells(); i++) {
            // применяем созданный выше стиль к каждой ячейке
            row.getCell(i).setCellStyle(style);
        }
    }


}
