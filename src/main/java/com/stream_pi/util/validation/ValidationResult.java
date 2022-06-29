package com.stream_pi.util.validation;

import java.util.ArrayList;

public class ValidationResult
{
    private ValidationResultType resultType;
    private final ArrayList<String> messages;

    public ValidationResult()
    {
        this.resultType = ValidationResultType.OK;
        this.messages = new ArrayList<>();
    }

    public ValidationResult addErrorIf(String message, boolean condition)
    {
        if(condition)
        {
            resultType = ValidationResultType.ERROR;
            messages.add(message);
        }

        return this;
    }

    public ArrayList<String> getMessages() {
        return messages;
    }

    public ValidationResultType getResultType() {
        return resultType;
    }
}
