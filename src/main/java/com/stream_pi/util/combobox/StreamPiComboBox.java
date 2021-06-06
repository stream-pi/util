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

package com.stream_pi.util.combobox;

import java.util.List;

import com.stream_pi.util.uihelper.SpaceFiller;

import org.kordamp.ikonli.javafx.FontIcon;

import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * Custom Combo Box for Server and Client
 * @param <T> Combo Box List Type
 */
public class StreamPiComboBox<T> extends HBox
{
    private List<T> options;
    private static StackPane stackPaneParent;

    /**
     * Sets the parent where the Combo Box will be shown
     * @param parent StackPane where the Combo Box dialog will be shown
     */
    public static void setParent(StackPane parent)
    {
        stackPaneParent = parent;
        stackPaneParent.getStyleClass().add("combo_box_pane_parent");
        stackPaneParent.getChildren().addListener((ListChangeListener<Node>) c ->
        {
            if(stackPaneParent.getChildren().size() > 0)
            {
                stackPaneParent.setVisible(true);
                stackPaneParent.toFront();
            }
            else
            {
                stackPaneParent.setVisible(false);
                stackPaneParent.toBack();
            }
        });
    }

    /**
     * Constructor to create Combo Box with all the options
     * @param options List of Options
     */
    public StreamPiComboBox(List<T> options)
    {
        setup();
        setOptions(options);
    }

    /**
     * @return List of Combo Box Options
     */
    public List<T> getOptions()
    {
        return options;
    }

    /**
     * Constructor to create Combo Box with empty list
     */
    public StreamPiComboBox()
    {
        setup();
    }

    private Label currentSelectedLabel;

    /**
     * init of Combo Box
     */
    private void setup()
    {
        getStyleClass().add("combo_box");
        setOnMouseClicked(event -> show());

        currentSelectedLabel = new Label();
        currentSelectedLabel.getStyleClass().add("combo_box_current_selected_label");

        FontIcon fontIcon = new FontIcon();
        fontIcon.getStyleClass().add("combo_box_drop_down_icon");

        getChildren().addAll(
            currentSelectedLabel,
            SpaceFiller.horizontal(),
            fontIcon
        );
    }

    /**
     * Set Combo Box options
     * @param options Combo Box Options
     */
    public void setOptions(List<T> options)
    {
        this.options = options;
        setCurrentSelectedItemIndex(0);
    }

    private int currentIndex = 0;

    /**
     * @return Final Scroll Pane
     */
    public ScrollPane getPopupScrollPane()
    {
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
        scrollPane.getStyleClass().add("combo_box_popup");

        VBox vBox = new VBox();
        vBox.getStyleClass().add("combo_box_popup_vbox");
        vBox.prefWidthProperty().bind(scrollPane.widthProperty().subtract(10));

        scrollPane.maxHeightProperty().bind(vBox.heightProperty().add(20));

        scrollPane.setContent(vBox);

        //scrollPane.setMaxHeight(Double.NEGATIVE_INFINITY);

        for(int i = 0;i<options.size();i++)
        {
            T eachOptionObj = options.get(i);
            String displayText = streamPiComboBoxFactory.getOptionDisplayText(eachOptionObj);

            ToggleButton optionButton = new ToggleButton(displayText);
            optionButton.getStyleClass().add("combo_box_list_option_button");
            optionButton.setSelected(i == currentIndex);
            optionButton.setUserData(i);
            optionButton.setOnAction(event->
            {
                setCurrentSelectedItemIndex((int) optionButton.getUserData());

                if(streamPiComboBoxListener != null)
                    streamPiComboBoxListener.onNewItemSelected(options.get(currentIndex));
                
                destroy();
            });
            vBox.getChildren().addAll(optionButton);
        }

        return scrollPane;
    }

    /**
     * @return Current Selected Option Index
     */
    public int getCurrentIndex()
    {
        return currentIndex;
    }

    /**
     * @return Current Selected Item
     */
    public T getCurrentSelectedItem()
    {
        return options.get(currentIndex);
    }

    /**
     * @param index Current item index to be selected
     */
    public void setCurrentSelectedItemIndex(int index)
    {
        this.currentIndex = index;
        setCurrentSelectedLabelText(streamPiComboBoxFactory.getOptionDisplayText(options.get(index)));
    }

    /**
     * @param text Name of the option
     */
    private void setCurrentSelectedLabelText(String text)
    {
        currentSelectedLabel.setText(text);
    }

    private StreamPiComboBoxListener<T> streamPiComboBoxListener = null;

    /**
     * Set on click Listener
     * @param streamPiComboBoxListener Combo Box Listener, triggered when an option is clicked
     */
    public void setStreamPiComboBoxListener(StreamPiComboBoxListener<T> streamPiComboBoxListener)
    {
        this.streamPiComboBoxListener = streamPiComboBoxListener;
    }

    private Node popupNode;

    /**
     * Adds the Combo Box to the parent node
     */
    public void show()
    {
        Platform.runLater(()->{
            popupNode = getPopupScrollPane();
            stackPaneParent.getChildren().add(popupNode);
        });
    }

    /**
     * Removes the combo box from the parent pane
     */
    public void destroy()
    {
        Platform.runLater(()-> stackPaneParent.getChildren().remove(popupNode));
    }

    private StreamPiComboBoxFactory<T> streamPiComboBoxFactory;

    /**
     * @param streamPiComboBoxFactory Factory for the Combo Box
     */
    public void setStreamPiComboBoxFactory(StreamPiComboBoxFactory<T> streamPiComboBoxFactory)
    {
        this.streamPiComboBoxFactory = streamPiComboBoxFactory;
    }
}