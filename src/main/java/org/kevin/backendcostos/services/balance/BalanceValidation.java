package org.kevin.backendcostos.services.balance;

import org.apache.poi.hssf.usermodel.HSSFSheet;

import java.util.Map;

public interface BalanceValidation {

    Map<String, String> validateBalance(HSSFSheet sheet, String fileName);
}
