/*
Stream-Pi - Free & Open-Source Modular Cross-Platform Programmable Macro Pad
Copyright (C) 2019-2021  Debayan Sutradhar (rnayabed),  Samuel Quiñones (SamuelQuinones)

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.
This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

Originally Written by : Debayan Sutradhar (rnayabed)
*/

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

public class HBoxInputBoxWithFileChooser extends HBox
{
    private static File initialDirectory = null;

    public HBoxInputBoxWithFileChooser(String labelText, TextField textField,
                                       FileChooser.ExtensionFilter... extensionFilters)
    {
        this(labelText, textField, null, true, extensionFilters);
    }

    public HBoxInputBoxWithFileChooser(String labelText, TextField textField, CheckBox enablerCheckBox,
                                       boolean useLast, FileChooser.ExtensionFilter... extensionFilters)
    {
        this(labelText, textField, enablerCheckBox, 150, useLast, true, extensionFilters);
    }

    public HBoxInputBoxWithFileChooser(String labelText, TextField textField, CheckBox enablerCheckBox,
                                       FileChooser.ExtensionFilter... extensionFilters)
    {
        this(labelText, textField, enablerCheckBox, 150, true, true, extensionFilters);
    }

    FileChooser fileChooser;

    public void setInitialDirectory(File initialDirectory)
    {
        fileChooser.setInitialDirectory(initialDirectory);
    }

    private final Button fileChooseButton;

    public Button getFileChooseButton()
    {
        return fileChooseButton;
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

    public HBoxInputBoxWithFileChooser(String labelText, TextField textField, CheckBox enablerCheckBox,
                                       int prefWidth, boolean useLast, boolean rememberThis,
                                       FileChooser.ExtensionFilter... extensionFilter)
    {
        this.useLast = useLast;
        this.rememberThis = rememberThis;

        textField.setDisable(true);

        HBoxInputBox hBoxInputBox = new HBoxInputBox(labelText, textField, prefWidth);
        setHgrow(hBoxInputBox, Priority.ALWAYS);
        getChildren().addAll(hBoxInputBox);
        setSpacing(5.0);

        fileChooseButton = new Button();
        FontIcon fontIcon = new FontIcon("far-folder");
        fileChooseButton.setGraphic(fontIcon);

        fileChooseButton.setOnAction(event -> {
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
                File selectedFile = fileChooser.showOpenDialog(fileChooseButton.getScene().getWindow());
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

        getChildren().add(fileChooseButton);

        if(enablerCheckBox!=null)
        {
            fileChooseButton.disableProperty().bind(enablerCheckBox.selectedProperty());
            HBox.setMargin(enablerCheckBox, new Insets(0, 0, 0, 45));
            getChildren().add(enablerCheckBox);
        }
    }
}
