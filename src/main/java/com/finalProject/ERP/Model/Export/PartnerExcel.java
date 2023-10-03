package com.finalProject.ERP.Model.Export;

import com.finalProject.ERP.Model.PartnerEntity;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class PartnerExcel {
    public static void exportToExcel(List<PartnerEntity> data, String filePath) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Income Data");

            Row headerRow = sheet.createRow(0);
            String[] columns = {"ID", "Name", "Contact"};

            for (int i = 0; i < columns.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columns[i]);
            }

            int rowNum = 1;
            for (PartnerEntity partner : data) {
                Row row = sheet.createRow(rowNum++);

                row.createCell(0).setCellValue(partner.getId());
                row.createCell(1).setCellValue(partner.getName());
                row.createCell(2).setCellValue(partner.getContact());
            }

            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                workbook.write(fileOut);
                System.out.println("Excel fájl mentve: " + filePath);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
