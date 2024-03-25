package org.kevin.backendcostos.services;

import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.kevin.backendcostos.dto.OkResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@RequiredArgsConstructor
@Service
public class DatosPersonasService {

    // TODO: termianr implementacion del guardado de datos en la tabla datos personas
    public ResponseEntity<String> storeFile(MultipartFile file, int anho) {

        try (InputStream inputStream = file.getInputStream()) {
            XSSFWorkbook book = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = book.getSheetAt(0);

            if (sheet != null) {

                for (int i = 0; i <= sheet.getLastRowNum(); i ++) {
                    Row row = sheet.getRow(i);

                    int counter = 0;

                    while (counter < row.getLastCellNum()) {
                        System.out.println(row.getCell(counter));
                        counter ++;
                    }

                    System.out.println(anho);
                }

                return ResponseEntity.ok(new OkResponseDto("Archivo agregado correctamente").toString());
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Hoja no encontrada en el archivo");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al subir el archivo");
        }
    }
}
