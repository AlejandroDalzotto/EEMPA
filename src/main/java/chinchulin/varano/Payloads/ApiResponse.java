package chinchulin.varano.Payloads;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse<T> {

    private Integer status;

    private String message;

    private Boolean success;

    private T data;
}
