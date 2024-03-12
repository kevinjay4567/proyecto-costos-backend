package org.kevin.backendcostos.services;

import lombok.RequiredArgsConstructor;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.kevin.backendcostos.entities.File;
import org.kevin.backendcostos.repositories.FileRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class FileService {

    private final FileRepository fileRepository;

    public List<File> getAllFiles() {
        return fileRepository.findAll();
    }

    /**
     * Controla que funcion de guardado se debe ejecutar dependiendo la extension
     *
     * @param file Archivo de Excel a guardar
     * @return Devuelve una respuesta de el estado del guardado
     */
    public ResponseEntity<String> storeFile(MultipartFile file) {

        return switch (getExtFile(file)) {
            case "xlsx" -> storeFileXlsx(file);
            case "xls" -> storeFileXls(file);
            default -> ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Extension de archivo erronea");
        };
    }

    /**
     * Guarda los archivos de Excel que tengan una extension .xlsx
     *
     * @param file Archivo con extension .xlsx
     * @return Devuelve una respuesta del estado de guardado del archivo
     */
    private ResponseEntity<String> storeFileXlsx(MultipartFile file) {

        try (InputStream inputStream = file.getInputStream()) {
            XSSFWorkbook book = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = book.getSheetAt(0);

            if (sheet != null) {
                File newFile = new File();
                newFile.setFileName(file.getOriginalFilename());
                newFile.setTotalRegisters(sheet.getLastRowNum());

                fileRepository.save(newFile);

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

    /**
     * Guarda los archivos de Excel que tengan una extension .xls
     *
     * @param file Archivo con extension .xls
     * @return Devuelve una respuesta del estado de guardado del archivo
     */
    private ResponseEntity<String> storeFileXls(MultipartFile file) {

        try (InputStream inputStream = file.getInputStream()) {
            HSSFWorkbook book = new HSSFWorkbook(inputStream);
            HSSFSheet sheet = book.getSheetAt(0);

            if (sheet != null) {
                File newFile = new File();
                newFile.setFileName(file.getOriginalFilename());
                newFile.setTotalRegisters(sheet.getLastRowNum());

                fileRepository.save(newFile);

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

    /**
     * Obtiene la extension del archivo
     *
     * @param file Archivo de Excel
     * @return Devuelve una cadena de texto con el nombre de la extension del archivo
     */
    private String getExtFile(MultipartFile file) {
        int indexLastPoint = Objects.requireNonNull(file.getOriginalFilename()).lastIndexOf(".");
        return file.getOriginalFilename().substring(indexLastPoint + 1);
    }
}
