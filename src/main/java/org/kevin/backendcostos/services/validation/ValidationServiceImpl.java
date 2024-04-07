package org.kevin.backendcostos.services.validation;

import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class ValidationServiceImpl implements ValidationService {

    private final Map<String, String> errors = new HashMap<>();

    @Override
    public Map<String, String> validate(Sheet sheet) {

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);

            for (int j = 0; j < row.getLastCellNum(); j++) {
                Cell cell = row.getCell(j);

                if (cell.getCellType().equals(CellType.NUMERIC)) {
                    System.out.println(cell.getNumericCellValue());
                    if ((int) cell.getNumericCellValue() < 0) {
                        errors.put(cell.getAddress().toString(), "El numero debe ser positvio");
                    }
                } else if (cell.getCellType().equals(CellType.STRING)) {
                    System.out.println(cell.getStringCellValue());
                }
            }
        }
        return errors;
    }
}
