package exercise.dto;

import java.util.Map;
import java.util.List;

import lombok.Getter;
import lombok.AllArgsConstructor;

import io.javalin.validation.ValidationError;

@AllArgsConstructor
@Getter
public class LoginPage {
    private String name;
    private Map<String, List<ValidationError<Object>>> errors;
}
