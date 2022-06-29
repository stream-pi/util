package com.stream_pi.util.uihelper;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.FileChooser;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.File;
import java.util.Objects;

public class FileChooserField extends HBox
{
    public FileChooserField(TextField textField,
                                       FileChooser.ExtensionFilter... extensionFilters)
    {
        init(null, textField, null, 150, true, true,extensionFilters);
    }

    public FileChooserField(ValidatedTextField validatedTextField,
                                       FileChooser.ExtensionFilter... extensionFilters)
    {
        init(validatedTextField, validatedTextField.getTextField(), null, 150, true, true,extensionFilters);
    }

    public FileChooserField(TextField textField, CheckBox enablerCheckBox,
                                       boolean useLast, FileChooser.ExtensionFilter... extensionFilters)
    {
        init(null, textField, enablerCheckBox, 150, useLast, true, extensionFilters);
    }

    public FileChooserField(ValidatedTextField validatedTextField, CheckBox enablerCheckBox,
                                       boolean useLast, FileChooser.ExtensionFilter... extensionFilters)
    {
        init(validatedTextField, validatedTextField.getTextField(), enablerCheckBox, 150, useLast, true, extensionFilters);
    }

    public FileChooserField(TextField textField, CheckBox enablerCheckBox,
                                       FileChooser.ExtensionFilter... extensionFilters)
    {
        init(null, textField, enablerCheckBox, 150, true, true, extensionFilters);
    }

    public FileChooserField(ValidatedTextField validatedTextField, CheckBox enablerCheckBox,
                                       FileChooser.ExtensionFilter... extensionFilters)
    {
        init(validatedTextField, validatedTextField.getTextField(), enablerCheckBox, 150, true, true, extensionFilters);
    }


    private static File initialDirectory = null;

    FileChooser fileChooser;

    public void setInitialDirectory(File initialDirectory)
    {
        fileChooser.setInitialDirectory(initialDirectory);
    }

    private Button button;

    public Button getFileChooseButton()
    {
        return button;
    }

    private boolean useLast,rememberThis;

    public void setUseLast(boolean useLast)
    {
        this.useLast = useLast;
    }

    public void setRememberThis(boolean rememberThis)
    {
        this.rememberThis = rememberThis;
    }

    public void init(ValidatedTextField validatedTextField, TextField textField, CheckBox enablerCheckBox,
                     int prefWidth, boolean useLast, boolean rememberThis,
                     FileChooser.ExtensionFilter... extensionFilter)
    {
        this.useLast = useLast;
        this.rememberThis = rememberThis;

        textField.setDisable(true);

        setSpacing(5.0);

        button = new Button();
        FontIcon fontIcon = new FontIcon("far-folder");
        button.setGraphic(fontIcon);

        button.setOnAction(event -> {
            fileChooser = new FileChooser();

            if(useLast && initialDirectory != null)
            {
                setInitialDirectory(initialDirectory);
            }

            if(extensionFilter!=null)
            {
                fileChooser.getExtensionFilters().addAll(
                        extensionFilter
                );
            }

            try
            {
                File selectedFile = fileChooser.showOpenDialog(button.getScene().getWindow());
                if(rememberThis)
                {
                    initialDirectory = selectedFile.getParentFile();
                }
                textField.setText(selectedFile.getAbsolutePath());
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
