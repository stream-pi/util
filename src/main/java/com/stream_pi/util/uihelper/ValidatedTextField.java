package com.stream_pi.util.uihelper;

import com.stream_pi.util.validation.ValidatedControl;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.VBox;

public class ValidatedTextField extends VBox implements ValidatedControl
{
    public ValidatedTextField()
    {
        this("");
    }

    private TextField textField;
    private Label errorLabel;

    public ValidatedTextField(String text)
    {
        textField = new TextField(text);
        errorLabel = new Label();
        errorLabel.getStyleClass().add("validated_text_field_error_prompt_label");

        setSpacing(5.0);
        getChildren().addAll(textField, errorLabel);
        getStyleClass().add("validated_text_field");
    }

    public String getText()
    {
        return textField.getText();
    }

    public void setText(String text)
    {
        textField.setText(text);
    }

    public void setTextFormatter(TextFormatter<?> textFormatter)
    {
        textField.setTextFormatter(textFormatter);
    }

    public TextField getTextField() {
        return textField;
    }

    @Override
    public Control getControl() {
        return getTextField();
    }

    @Override
    public Label getErrorLabel() {
        return errorLabel;
    }
}
