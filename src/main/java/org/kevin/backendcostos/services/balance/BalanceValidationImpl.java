package org.kevin.backendcostos.services.balance;

import lombok.RequiredArgsConstructor;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class BalanceValidationImpl implements BalanceValidation {

    private final Map<String, String> errors = new HashMap<>();

    private enum Headers {
        ANHO,
        SEDE,
        EMPRESA,
        NOMBRE_EMPRESA,
        CLASE,
        GRUPO,
        CUENTA,
        SUBCUENTA,
        CONCEPTO,
        DESCRIPCION,
        VALOR
    }

    @Override
    public Map<String, String> validateBalance(HSSFSheet sheet, String fileName) {

        //TODO: Aun faltan mas validaciones

        validateBalanceHeader(sheet);

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);

            for (int j = 0; j < row.getLastCellNum(); j++) {
                Cell cell = row.getCell(j);

                if (cell.getCellType().equals(CellType.NUMERIC)) {

                    System.out.println((int) cell.getNumericCellValue());

                    if ((int) cell.getNumericCellValue() < 0) {
                        errors.put(cell.getAddress().toString(), "El numero debe ser positivo");
                    }
                } else if (cell.getCellType().equals(CellType.STRING)) {
                    System.out.println(cell.getStringCellValue());
                }
            }
        }

        return errors;
    }

    private void validateBalanceHeader(HSSFSheet sheet) {
        Row header = sheet.getRow(0);

        for (int i = 0; i < header.getLastCellNum(); i++) {
            Cell cell = header.getCell(i);

            if (!cell.getStringCellValue().equals(Headers.values()[i].toString())) {
                errors.put(cell.getAddress().toString(), "Texto de encabezado incorrecto. Debe ser: "
                        + Headers.values()[i]);
            }
        }
    }

}
