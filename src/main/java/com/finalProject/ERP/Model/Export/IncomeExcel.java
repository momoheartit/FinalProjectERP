package com.finalProject.ERP.Model.Export;

import com.finalProject.ERP.Model.IncomeEntity;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class IncomeExcel {

    public static void exportToExcel(List<IncomeEntity> data, String filePath) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Income Data");

            Row headerRow = sheet.createRow(0);
            String[] columns = {"ID", "Partner", "Amount", "Project", "Created", "Approved"};

            for (int i = 0; i < columns.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columns[i]);
            }

            int rowNum = 1;
            for (IncomeEntity income : data) {
                Row row = sheet.createRow(rowNum++);

                row.createCell(0).setCellValue(income.getId());
                row.createCell(1).setCellValue(income.getPartnerName());
                row.createCell(2).setCellValue(income.getAmount());
                row.createCell(3).setCellValue(income.getProject());
                row.createCell(4).setCellValue(income.getCreated());//.toString());
                if (income.getApproved() != null) {
                    row.createCell(5).setCellValue(income.getApproved().toString());
                }
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
