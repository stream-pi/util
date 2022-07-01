package com.stream_pi.util.uihelper;

import com.stream_pi.util.validation.ValidationResult;
import com.stream_pi.util.validation.ValidationSupport;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;


public class IntegerField extends ValidatedTextField {
    private Integer defaultValue = 0;
    private Integer minValue;
    private Integer maxValue;

    private ValidationSupport validationSupport;

    public IntegerField(int defaultValue) {
        this.defaultValue = defaultValue;
        init();
    }

    public IntegerField(ValidationSupport validationSupport, int minValue, int maxValue) {
        this.validationSupport = validationSupport;
        this.minValue = minValue;
        this.maxValue = maxValue;
        init();
    }

    public IntegerField(ValidationSupport validationSupport, int minValue) {
        this.validationSupport = validationSupport;
        this.minValue = minValue;
        init();
    }

    public IntegerField(ValidationSupport validationSupport) {
        this.validationSupport = validationSupport;
        init();
    }

    public IntegerField(){
        init();
    }

    public void setDefaultValue(int defaultValue) {
        this.defaultValue = defaultValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    public int getDefaultValue() {
        return defaultValue;
    }

    public int getMinValue() {
        return minValue;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public int getIntegerValue()
    {
        return getRefinedIntegerFromField(getText());
    }

    public void resetToDefaultValue()
    {
        if (defaultValue != null) {
            setText(defaultValue+"");
        }
    }

    private int getRefinedIntegerFromField(String value)
    {
        if(value.equals("-") || value.isEmpty())
        {
            return 0;
        }
        else
        {
            return Integer.parseInt(value);
        }
    }

    private void init()
    {
        getTextField().setPrefWidth(100);
        resetToDefaultValue();

        //TODO: Implement I18N support for below
        if (validationSupport != null && (minValue != null || maxValue!=null))
        {
            validationSupport.registerValidator(this, (control, newValue) -> {
                        ValidationResult validationResult = new ValidationResult();

                        if (minValue!=null)
                        {
                            validationResult.addErrorIf(
                                    "Value cannot be lower than "+minValue,
                                    getRefinedIntegerFromField(String.valueOf(newValue))<minValue
                            );
                        }

                        if(maxValue!=null)
                        {
                            validationResult.addErrorIf(
                                    "Value cannot be higher than "+maxValue,
                                    getRefinedIntegerFromField(String.valueOf(newValue))>maxValue
                            );
                        }

                        return validationResult;
                    }
            );
        }

        setTextFormatter(new TextFormatter<>(change -> {
            if(change.getText().isEmpty())
            {
                return change;
            }

            if(change.getText().matches("[\\d-]+"))
            {
                String newText = change.getControlNewText();

                if(change.getText().equals("-") && (!newText.startsWith("-") || (newText.length() - newText.replace("-","").length())>1))
                {
                    return null;
                }

                return change;
            }
            return null;
        }));

        focusedProperty().addListener((observableValue, oldValue, newValue) -> {
            if (oldValue != newValue && !newValue)
            {
                if(getText().equals("."))
                {
                    setText("0");
                }
            }
        });
    }
}
