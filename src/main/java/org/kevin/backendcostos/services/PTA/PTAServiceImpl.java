package org.kevin.backendcostos.services.PTA;

import lombok.RequiredArgsConstructor;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.kevin.backendcostos.entities.PTAEntity;
import org.kevin.backendcostos.repositories.PTARepository;
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
public class PTAServiceImpl implements PTAService{

    private final PTARepository repository;
    private final ValidationServiceImpl validator;

    @Override
    public List<PTAEntity> getAll() {
        return repository.findAll();
    }

    @Override
    public ResponseEntity<String> store(MultipartFile file) {

        try (InputStream inputStream = file.getInputStream()) {
            Workbook book = new HSSFWorkbook(inputStream);
            Sheet sheet = book.getSheetAt(0);

            if (sheet != null) {

                Map<String, String> errors = validator.validate(sheet);

                if (!errors.isEmpty()) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Datos invalidos");
                }

                for (int i = 1; i < sheet.getLastRowNum(); i++) {
                    save(sheet.getRow(i));
                }

                return ResponseEntity.ok("Archivo agregado correctamente");
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

    private void save(Row row) {

        // TODO: Pensar una mejor manera de hacer esto

        String categoriaDocente = row.getCell(0).getStringCellValue();
        int item = (int) row.getCell(1).getNumericCellValue();
        String actividadItem = row.getCell(2).getStringCellValue();
        String sede = row.getCell(3).getStringCellValue();
        String tipo = row.getCell(4).getStringCellValue();
        long valor = (long) row.getCell(5).getNumericCellValue();

        PTAEntity ptaEntity = PTAEntity.builder().categoria_docente(categoriaDocente)
                .item(item).actividad_item(actividadItem).sede(sede).tipo(tipo)
                .valor(valor).build();

        repository.save(ptaEntity);
    }
}
