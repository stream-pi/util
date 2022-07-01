package com.stream_pi.util.uihelper;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.DirectoryChooser;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.File;
import java.util.Objects;

public class DirectoryChooserField extends HBox
{
    private final double DEFAULT_PREF_WIDTH = 300;
    public DirectoryChooserField(TextField textField)
    {
        init(null, textField, null, DEFAULT_PREF_WIDTH, null);
    }

    public DirectoryChooserField(ValidatedTextField validatedTextField)
    {
        init(validatedTextField, validatedTextField.getTextField(), null, DEFAULT_PREF_WIDTH, null);
    }

    public DirectoryChooserField(TextField textField, CheckBox enablerCheckBox)
    {
        init(null, textField, enablerCheckBox, DEFAULT_PREF_WIDTH, null);
    }

    public DirectoryChooserField(ValidatedTextField validatedTextField, CheckBox enablerCheckBox)
    {
        init(validatedTextField, validatedTextField.getTextField(), enablerCheckBox, DEFAULT_PREF_WIDTH, null);
    }

    public DirectoryChooserField(TextField textField, CheckBox enablerCheckBox,
                                            File initialDirectory)
    {
        init(null, textField, enablerCheckBox, DEFAULT_PREF_WIDTH, initialDirectory);
    }

    public DirectoryChooserField(ValidatedTextField validatedTextField, CheckBox enablerCheckBox,
                                            File initialDirectory)
    {
        init(validatedTextField, validatedTextField.getTextField(), enablerCheckBox, DEFAULT_PREF_WIDTH, initialDirectory);
    }


    public DirectoryChooserField(TextField textField, CheckBox enablerCheckBox,
                                            int prefWidth,  File initialDirectory)
    {
        init(null, textField, enablerCheckBox, prefWidth, initialDirectory);
    }

    public DirectoryChooserField(ValidatedTextField validatedTextField, CheckBox enablerCheckBox,
                                            int prefWidth,  File initialDirectory)
    {
        init(validatedTextField, validatedTextField.getTextField(), enablerCheckBox, prefWidth, initialDirectory);
    }

    private void init(ValidatedTextField validatedTextField, TextField textField, CheckBox enablerCheckBox,
                      double prefWidth, File initialDirectory)
    {
        textField.setDisable(true);
        setPrefWidth(prefWidth);
        setSpacing(5.0);

        Button button = new Button();
        FontIcon fontIcon = new FontIcon("far-folder");
        button.setGraphic(fontIcon);

        button.setOnAction(event -> {
            DirectoryChooser directoryChooser = new DirectoryChooser();

            if(initialDirectory!=null)
            {
                directoryChooser.setInitialDirectory(initialDirectory);
            }

            try
            {
                File selectedDirectory = directoryChooser.showDialog(button.getScene().getWindow());
                textField.setText(selectedDirectory.getAbsolutePath());
            }
            catch (NullPointerException e)
            {
                // No folder selected
            }
        });

        getChildren().addAll(Objects.requireNonNullElse(validatedTextField, textField), button);

        if(enablerCheckBox!=null)
        {
            button.disableProperty().bind(enablerCheckBox.selectedProperty());
            HBox.setMargin(enablerCheckBox, new Insets(0, 0, 0, 45));
            getChildren().add(enablerCheckBox);
        }
    }
}
