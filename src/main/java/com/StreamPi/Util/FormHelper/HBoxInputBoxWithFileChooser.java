package com.StreamPi.Util.FormHelper;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.File;

public class HBoxInputBoxWithFileChooser extends HBox {
    public HBoxInputBoxWithFileChooser(String labelText, TextField textField, CheckBox enablerCheckBox, FileChooser.ExtensionFilter extensionFilter)
    {
        textField.setDisable(true);

        HBoxInputBox hBoxInputBox = new HBoxInputBox(labelText, textField, 300);
        setHgrow(hBoxInputBox, Priority.ALWAYS);
        getChildren().addAll(hBoxInputBox);
        setSpacing(5.0);

        Button button = new Button();
        FontIcon fontIcon = new FontIcon("far-folder");
        //fontIcon.setIconColor(Paint.valueOf(iconColorHex));
        //fontIcon.setIconSize(iconSize);
        button.setGraphic(fontIcon);

        button.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();

            fileChooser.getExtensionFilters().addAll(
                    extensionFilter
            );


            try {
                File selectedDirectory = fileChooser.showOpenDialog(button.getScene().getWindow());
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
