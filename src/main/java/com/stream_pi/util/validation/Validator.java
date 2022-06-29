package com.stream_pi.util.validation;

import javafx.scene.control.Control;
import javafx.scene.control.TextField;

public interface Validator
{
    public ValidationResult validate(ValidatedControl control, Object value);

    static Validator createEmptyValidator(String message)
    {
        return (validatedControl, value) -> new ValidationResult().addErrorIf(message, ((TextField)validatedControl.getControl()).getText().isEmpty());
    }

}
