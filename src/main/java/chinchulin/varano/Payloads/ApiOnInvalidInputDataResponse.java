package chinchulin.varano.Payloads;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class ApiOnInvalidInputDataResponse {

    private Integer status;

    private Map<String, String> fields;

    private Boolean success;
}
