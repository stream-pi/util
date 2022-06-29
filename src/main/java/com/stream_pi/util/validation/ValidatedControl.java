package com.stream_pi.util.validation;

import javafx.scene.control.Control;
import javafx.scene.control.Label;

public interface ValidatedControl
{
    public Control getControl();
    public Label getErrorLabel();
}
