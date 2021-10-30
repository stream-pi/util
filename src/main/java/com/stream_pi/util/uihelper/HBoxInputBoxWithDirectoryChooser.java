/*
 * Stream-Pi - Free & Open-Source Modular Cross-Platform Programmable Macro Pad
 * Copyright (C) 2019-2021  Debayan Sutradhar (rnayabed),  Samuel Quiñones (SamuelQuinones)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 */

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

public class HBoxInputBoxWithDirectoryChooser extends HBox
{
    public HBoxInputBoxWithDirectoryChooser(String labelText, TextField textField)
    {
        this(labelText, textField, null);
    }

    public HBoxInputBoxWithDirectoryChooser(String labelText, TextField textField, CheckBox enablerCheckBox)
    {
        this(labelText, textField, enablerCheckBox, 150, null);
    }

    public HBoxInputBoxWithDirectoryChooser(String labelText, TextField textField, CheckBox enablerCheckBox,
                                       File initialDirectory)
    {
        this(labelText, textField, enablerCheckBox, 150, initialDirectory);
    }

    public HBoxInputBoxWithDirectoryChooser(String labelText, TextField textField, CheckBox enablerCheckBox,
                                       int prefWidth,  File initialDirectory)
    {
        textField.setDisable(true);

        HBoxInputBox hBoxInputBox = new HBoxInputBox(labelText, textField, prefWidth);
        setHgrow(hBoxInputBox, Priority.ALWAYS);
        getChildren().addAll(hBoxInputBox);
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

        getChildren().add(button);

        if(enablerCheckBox!=null)
        {
            button.disableProperty().bind(enablerCheckBox.selectedProperty());
            HBox.setMargin(enablerCheckBox, new Insets(0, 0, 0, 45));
            getChildren().add(enablerCheckBox);
        }
    }
}
