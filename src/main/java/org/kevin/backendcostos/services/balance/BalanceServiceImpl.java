package org.kevin.backendcostos.services.balance;

import lombok.RequiredArgsConstructor;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.kevin.backendcostos.entities.*;
import org.kevin.backendcostos.repositories.*;
import org.kevin.backendcostos.response.BalanceResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class BalanceServiceImpl implements BalanceService {

    private final BalanceRepository balanceRepository;
    private final BalanceValidationImpl balanceValidation;
    private final AgrupacionRepository agrupacionRepository;
    private final SedeRepository sedeRepository;
    private final ConceptoRepository conceptoRepository;
    private final EmpresaRepository empresaRepository;
    private final SubcuentaRepository subcuentaRepository;

    @Override
    public List<BalanceEntity> getAll() {
        return balanceRepository.findAll();
    }

    @Override
    public ResponseEntity<BalanceResponse> store(MultipartFile file) {
        try (InputStream inputStream = file.getInputStream()) {
            HSSFWorkbook book = new HSSFWorkbook(inputStream);
            HSSFSheet sheet = book.getSheetAt(0);

            if (sheet != null) {
                Map<String, String> errors = balanceValidation.validateBalance(sheet, Objects.requireNonNull(file.getOriginalFilename()));

                if (!errors.isEmpty())
                    return ResponseEntity.badRequest().body(new BalanceResponse("La validacion fallo", errors));

                for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                    save(sheet.getRow(i));
                }

                return ResponseEntity.ok(new BalanceResponse("Archivo agregado correctamente"));
            } else {
                return ResponseEntity.badRequest().body(new BalanceResponse("Hoja no encontrada"));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new BalanceResponse("Error al subir el archivo"));
        }
    }

    private void save(Row row) {

        // TODO: Pensar una mejor manera de hacer esto

        int anho = Integer.parseInt(row.getCell(0).getStringCellValue());
        int valor = (int) row.getCell(10).getNumericCellValue();
        Long sedeId = (long) row.getCell(1).getNumericCellValue();
        Long empresaId = (long) row.getCell(2).getNumericCellValue();
        long subcuentaId;
        if (row.getCell(7).getCellType().equals(CellType.STRING)) {
            subcuentaId = Long.parseLong(row.getCell(7).getStringCellValue());
        } else {
            subcuentaId = (long) row.getCell(7).getNumericCellValue();
        }

        String empresaNombre = row.getCell(3).getStringCellValue();
        String conceptoNombre = row.getCell(8).getStringCellValue();
        String subcuentaDescripcion = row.getCell(9).getStringCellValue();

        AgrupacionEntity agrupacion = AgrupacionEntity.builder().id(1L).nombre("Agrupacion Ejemplo").build();

        agrupacionRepository.save(agrupacion);

        SedeEntity sede = SedeEntity.builder().id(sedeId)
                .nombre("Sede " + sedeId).build();

        sedeRepository.save(sede);

        EmpresaEntity empresa = EmpresaEntity.builder()
                .id(empresaId).nombre(empresaNombre).build();

        empresaRepository.save(empresa);

        ConceptoEntity concepto = ConceptoEntity.builder()
                .id(1L)
                .nombre(conceptoNombre).build();

        conceptoRepository.save(concepto);

        SubcuentaEntity subcuenta = SubcuentaEntity.builder()
                .id(subcuentaId).descripcion(subcuentaDescripcion).agrupacion(agrupacion)
                .agrupacion_id(agrupacion.getId()).build();

        subcuentaRepository.save(subcuenta);

        BalanceEntity balanceEntity = BalanceEntity.builder()
                .anho(anho).valor(valor).concepto(concepto).concepto_id(concepto.getId())
                .empresa(empresa).empresa_id(empresa.getId()).sede(sede)
                .sede_id(sede.getId()).subcuenta(subcuenta).subcuenta_id(subcuenta.getId()).build();

        balanceRepository.save(balanceEntity);
    }
}
