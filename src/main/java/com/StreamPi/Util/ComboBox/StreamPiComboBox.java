package com.StreamPi.Util.ComboBox;

import java.util.List;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class StreamPiComboBox extends HBox
{
    private List options;

    private static StackPane stackPaneParent;

    public static void setParent(StackPane parent) {
        stackPaneParent = parent;

        stackPaneParent.getStyleClass().add("combobox_pane_parent");
    }
    
    public StreamPiComboBox(List options)
    {
        setup();
        this.options = options;
    }

    public StreamPiComboBox()
    {
        setup();
    }

    private Label currentSelected;
    private void setup()
    {
        getStyleClass().add("streampi_combo_box");

        getChildren().add()
    }

    public void setOptions(List options)
    {
        this.options = options;
    }

    public void popup()
    {

    }

    public void 
}