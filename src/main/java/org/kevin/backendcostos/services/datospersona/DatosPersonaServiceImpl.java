package org.kevin.backendcostos.services.datospersona;

import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.kevin.backendcostos.entities.*;
import org.kevin.backendcostos.repositories.DatosPersonaRepository;
import org.kevin.backendcostos.response.DatosPersonaResponse;
import org.kevin.backendcostos.services.validation.ValidationServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class DatosPersonaServiceImpl implements DatosPersonaService {

    private final DatosPersonaRepository repository;
    private final ValidationServiceImpl validator;

    @Override
    public List<DatosPersonaEntity> getAll() {
        return repository.findAll();
    }

    @Override
    public ResponseEntity<DatosPersonaResponse> store(MultipartFile file) {

        try (InputStream inputStream = file.getInputStream()) {
            Workbook book = new XSSFWorkbook(inputStream);
            Sheet sheet = book.getSheetAt(0);

            if (sheet != null) {

                Map<String, String> errors = validator.validate(sheet);

                if (!errors.isEmpty()) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new DatosPersonaResponse("Datos invalidos", errors));
                }

                for (int i = 1; i < sheet.getLastRowNum(); i++) {
                    save(sheet.getRow(i));
                }

                return ResponseEntity.ok(new DatosPersonaResponse("Archivo agregado correctamente"));
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new DatosPersonaResponse("Hoja no encontrada en el archivo"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new DatosPersonaResponse("Error al subir el archivo"));
        }
    }

    private void save(Row row) {

        // TODO: Pensar una mejor manera de hacer esto

        String sede = row.getCell(0).getStringCellValue();
        String vinculacion = row.getCell(1).getStringCellValue();
        String actividad = row.getCell(2).getStringCellValue();
        String facultad = row.getCell(3).getStringCellValue();
        String programa = row.getCell(4).getStringCellValue();
        float promedio = (float) row.getCell(5).getNumericCellValue();
        String nivel = row.getCell(6).getStringCellValue();

        DatosPersonaEntity datosPersonaEntity = DatosPersonaEntity.builder()
                .sede(sede).vinculacion(vinculacion).actividad(actividad).facultad(facultad)
                .programa(programa).promedio(promedio).nivel(nivel).build();

        repository.save(datosPersonaEntity);
    }
}
