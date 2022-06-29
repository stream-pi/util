package com.stream_pi.util.uihelper;

import com.stream_pi.util.validation.ValidationResult;
import com.stream_pi.util.validation.ValidationSupport;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

import java.util.List;

public class DoubleField extends ValidatedTextField {
    private Double defaultValue = 0.0;
    private Double minValue;
    private Double maxValue;

    private ValidationSupport validationSupport;

    public DoubleField(double defaultValue) {
        this.defaultValue = defaultValue;
        init();
    }

    public DoubleField(ValidationSupport validationSupport, double minValue, double maxValue) {
        this.validationSupport = validationSupport;
        this.minValue = minValue;
        this.maxValue = maxValue;
        init();
    }

    public DoubleField(ValidationSupport validationSupport, double minValue) {
        this.validationSupport = validationSupport;
        this.minValue = minValue;
        init();
    }

    public DoubleField(ValidationSupport validationSupport) {
        this.validationSupport = validationSupport;
        init();
    }

    public DoubleField(){
        init();
    }

    public void setDefaultValue(double defaultValue) {
        this.defaultValue = defaultValue;
    }

    public void setMinValue(double minValue) {
        this.minValue = minValue;
    }

    public void setMaxValue(double maxValue) {
        this.maxValue = maxValue;
    }

    public double getDefaultValue() {
        return defaultValue;
    }

    public double getMinValue() {
        return minValue;
    }

    public double getMaxValue() {
        return maxValue;
    }

    public double getDoubleValue()
    {
        return getRefinedDoubleFromField(getText());
    }

    public void resetToDefaultValue()
    {
        if (defaultValue != null) {
            setText(defaultValue+"");
        }
    }

    private double getRefinedDoubleFromField(String value)
    {
        if(List.of("-",".","-.").contains(getText()) || value.isEmpty())
        {
            return 0;
        }
        else
        {
            return Double.parseDouble(value);
        }
    }

    private void init()
    {

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
                                    getRefinedDoubleFromField(String.valueOf(newValue))<minValue
                            );
                        }

                        if(maxValue!=null)
                        {
                            validationResult.addErrorIf(
                                    "Value cannot be higher than "+maxValue,
                                    getRefinedDoubleFromField(String.valueOf(newValue))>maxValue
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

            if(change.getText().matches("[\\d.-]+"))
            {
                String newText = change.getControlNewText();

                if(change.getText().equals("-") && (!newText.startsWith("-") || (newText.length() - newText.replace("-","").length())>1))
                {
                    return null;
                }
                else if(change.getText().equals(".") && (newText.length() - newText.replace(".","").length())>1)
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
                if(List.of("-",".","-.").contains(getText()))
                {
                    setText("0.0");
                }
                else if(getText().endsWith("."))
                {
                    setText(getText()+"0");
                }
            }
        });
    }
}
