package org.kevin.backendcostos.services;

import lombok.RequiredArgsConstructor;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ExcelValidation {

    private final AgrupacionesService agrupacionesService;

    public void Validate(XSSFSheet hoja, String fileName) {

        switch (fileName) {
            case "Agrupaciones 2022.xlsx":
                agrupacionesService.storeAgrupaciones(hoja);
                break;
        }
    }
}
