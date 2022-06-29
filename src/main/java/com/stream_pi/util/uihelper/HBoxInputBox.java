/*
 * Stream-Pi - Free, Open-Source, Modular, Cross-Platform and Programmable Macro Pad
 * Copyright (C) 2019-2022 Debayan Sutradhar (rnayabed),  Samuel Quiñones (SamuelQuinones)
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
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

// TODO: Remove this after remove of references of this from Server/Client
public class HBoxInputBox extends HBox {

    private TextField textField;

    public HBoxInputBox(String labelText, ValidatedTextField validatedTextField, double prefWidth, CheckBox enablerCheckBox)
    {
        init(labelText, validatedTextField, validatedTextField.getTextField(), prefWidth, enablerCheckBox);
    }

    public HBoxInputBox(String labelText, TextField textField, double prefWidth, CheckBox enablerCheckBox)
    {
        init(labelText, null, textField, prefWidth, enablerCheckBox);
    }


    public HBoxInputBox(String labelText, TextField textField, CheckBox enablerCheckBox)
    {
        init(labelText, null, textField, 100, enablerCheckBox);
    }

    public HBoxInputBox(String labelText, ValidatedTextField validatedTextField, CheckBox enablerCheckBox)
    {
        init(labelText, validatedTextField, validatedTextField.getTextField(), 100, enablerCheckBox);
    }

    public HBoxInputBox(String labelText, TextField textField)
    {
        init(labelText, null, textField, 100, null);
    }

    public HBoxInputBox(String labelText, ValidatedTextField validatedTextField)
    {
        init(labelText, validatedTextField, validatedTextField.getTextField(), 100, null);
    }

    public HBoxInputBox(String labelText, TextField textField, int prefWidth)
    {
        init(labelText, null, textField, prefWidth, null);
    }

    public HBoxInputBox(String labelText, ValidatedTextField validatedTextField, int prefWidth)
    {
        init(labelText, validatedTextField, validatedTextField.getTextField(), prefWidth,null);
    }

    private void init(String labelText, ValidatedTextField enclosure, TextField textField, double prefWidth, CheckBox enablerCheckBox)
    {
        textField.setPrefWidth(prefWidth);
        Label label = new Label(labelText);
        label.setWrapText(true);
        label.setAlignment(Pos.CENTER_LEFT);
        label.prefHeightProperty().bind(heightProperty());
        getChildren().addAll(label, SpaceFiller.horizontal(), (enclosure == null) ? textField : enclosure);

        if(enablerCheckBox != null)
        {
            textField.disableProperty().bind(enablerCheckBox.selectedProperty());
            HBox.setMargin(enablerCheckBox, new Insets(0, 0, 0, 45));
            getChildren().add(enablerCheckBox);
        }
        this.textField = textField;
    }

    public TextField getTextField()
    {
        return textField;
    }
}
