package org.kevin.backendcostos.services.datospersona;

import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.kevin.backendcostos.entities.DatosPersonaEntity;
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
}
