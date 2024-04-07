package org.kevin.backendcostos.services.validation;

import org.apache.poi.ss.usermodel.Sheet;

import java.util.Map;

public interface ValidationService {

    Map<String, String> validate(Sheet sheet);
}
