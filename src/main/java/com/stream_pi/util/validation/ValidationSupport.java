package com.stream_pi.util.validation;

import com.stream_pi.util.uihelper.ValidatedTextField;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;
import javafx.geometry.Bounds;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.logging.Logger;

/*
Stream-Pi Validator class
 */

//TODO: Cleanup, rename CSS Style classes

public class ValidationSupport
{
    private ArrayList<ValidatedControl> controls;
    private SimpleBooleanProperty invalid = new SimpleBooleanProperty(false);

    private ObservableMap<ValidatedControl, ChangeListener<?>> controlChangeListeners;
    private ObservableMap<ValidatedControl, ValidationResult> validationResultHashMap;

    public ValidationSupport()
    {
        this.controls = new ArrayList<>();
        this.controlChangeListeners = FXCollections.observableHashMap();
        this.validationResultHashMap = FXCollections.observableHashMap();

        this.validationResultHashMap.addListener(new MapChangeListener<ValidatedControl, ValidationResult>() {
            @Override
            public void onChanged(Change<? extends ValidatedControl, ? extends ValidationResult> change) {

                if(change.getValueRemoved() == null || change.getValueAdded().getResultType() == change.getValueRemoved().getResultType())
                {
                    return;
                }

                ValidatedControl control = change.getKey();
                ValidationResult validationResult = change.getValueAdded();
                if (validationResult.getResultType() == ValidationResultType.ERROR)
                {
                    control.getErrorLabel().setText(String.join(" ", validationResult.getMessages()));
                }
                else
                {
                    control.getErrorLabel().setText("");
                }

                boolean isInvalid = false;

                for(ValidatedControl c : validationResultHashMap.keySet())
                {
                    if (validationResultHashMap.get(c).getResultType() == ValidationResultType.ERROR)
                    {
                        isInvalid = true;
                        break;
                    }
                }

                invalid.set(isInvalid);
            }
        });
    }

    public ArrayList<ValidatedControl> getControls() {
        return controls;
    }

    public boolean isInvalid() {
        return invalid.get();
    }

    public ReadOnlyBooleanProperty invalidProperty() {
        return invalid;
    }

    public void registerValidator(ValidatedControl validatedControl, Validator validator)
    {
        if(validatedControl instanceof ValidatedTextField)
        {
            ChangeListener<? super String> changeListener = (observableValue, oldValue, newValue) -> {
                ValidationResult validationResult = validator.validate(validatedControl, newValue);
                validationResultHashMap.put(validatedControl, validationResult);
            };

            controlChangeListeners.put(validatedControl, changeListener);

            ((TextField) validatedControl.getControl()).textProperty().addListener(changeListener);
        }
        else
        {
            Logger.getLogger(ValidationSupport.class.getName()).warning("Register support not implemented for other fields!");
        }
    }

    public void deRegisterValidator(ValidatedControl validatedControl)
    {
        if(validatedControl instanceof ValidatedControl)
        {
            ((TextField) validatedControl.getControl()).textProperty().removeListener((ChangeListener<? super String>) controlChangeListeners.get(validatedControl));
        }
        else
        {
            Logger.getLogger(ValidationSupport.class.getName()).warning("De register support not implemented for other fields!");
        }

        controlChangeListeners.remove(validatedControl);
        validationResultHashMap.remove(validatedControl);
    }
}
