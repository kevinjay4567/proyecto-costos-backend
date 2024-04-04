package org.kevin.backendcostos.services;

import lombok.RequiredArgsConstructor;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.kevin.backendcostos.repositories.AgrupacionesRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AgrupacionesService {

    private final AgrupacionesRepository agrupacionesRepository;

    public void storeAgrupaciones(XSSFSheet hoja) {

//        for (int i = 1; i <= hoja.getLastRowNum(); i++) {
//            Row row = hoja.getRow(i);
//
//            Agrupaciones agrupacion = new Agrupaciones();
//
//            agrupacion.setNombre(row.getCell(1).getStringCellValue());
//            agrupacion.setAgrupacion(row.getCell(2).getStringCellValue());
//            agrupacion.setAnho(Integer.parseInt(hoja.getSheetName()));
//
//            agrupacionesRepository.save(agrupacion);
//        }

        System.out.println(hoja.getLastRowNum());
    }
}
