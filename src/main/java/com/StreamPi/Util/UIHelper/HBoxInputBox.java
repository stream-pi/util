package com.StreamPi.Util.FormHelper;

import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

public class HBoxInputBox extends HBox {
    private TextField textField;
    public HBoxInputBox(String labelText, TextField textField, int prefWidth, CheckBox enablerCheckBox)
    {
        textField.setPrefWidth(prefWidth);

        Label label = new Label(labelText);


        getChildren().addAll(label, new SpaceFiller(SpaceFiller.FillerType.HBox), textField);

        if(enablerCheckBox != null)
        {
            textField.disableProperty().bind(enablerCheckBox.selectedProperty());
            HBox.setMargin(enablerCheckBox, new Insets(0, 0, 0, 45));
            getChildren().add(enablerCheckBox);
        }

        this.textField = textField;
    }

    public HBoxInputBox(String labelText, TextField textField, CheckBox enablerCheckBox)
    {
        this(labelText, textField, 100, enablerCheckBox);
    }

    public HBoxInputBox(String labelText, TextField textField)
    {
        this(labelText, textField, 100, null);
    }

    public HBoxInputBox(String labelText, TextField textField, int prefWidth)
    {
        this(labelText, textField, prefWidth, null);
    }


    public TextField getTextField()
    {
        return textField;
    }
}
