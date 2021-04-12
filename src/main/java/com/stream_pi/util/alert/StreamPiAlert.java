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

package com.stream_pi.util.alert;

import com.stream_pi.util.uihelper.SpaceFiller;
import javafx.stage.Stage;
import org.kordamp.ikonli.javafx.FontIcon;

import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 * Custom Alert Dialog for Server and Client
 */
public class StreamPiAlert
{
    private String title;
    private String[] buttons;
    private StreamPiAlertType streamPiAlertType;
    private Pane contentPane;

    private static StackPane stackPaneParent = null;

    /**
     * Sets the parent where the alert will be shown
     * @param parent StackPane where the alert dialog will be shown
     */
    public static void setParent(StackPane parent)
    {
        stackPaneParent = parent;
        stackPaneParent.getStyleClass().add("alert_pane_parent");
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
     * Returns the parent node
     * @return StackPane parent where the alert boxes will be shown
     */
    public static StackPane getParent()
    {
        return stackPaneParent;
    }

    private StreamPiAlertListener streamPiAlertListener = null;


    public StreamPiAlert(String contextText)
    {
        this("Alert", contextText);
    }

    /**
     * Public constructor to make an alert with just title, pre-made content pane (mainly for forms, complex dialogs)
     * @param title Heading of the Alert
     * @param streamPiAlertType Alert Type
     * @param contentPane The main body of the alert
     */
    public StreamPiAlert(String title, StreamPiAlertType streamPiAlertType,
     Pane contentPane)
    {
       set(title, streamPiAlertType, contentPane, "OK");
    }

    /**
     * Sets the Alert type
     * @param streamPiAlertType Alert Type
     */
    public void setStreamPiAlertType(StreamPiAlertType streamPiAlertType)
    {
        this.streamPiAlertType = streamPiAlertType;
    }

    /**
     * Constructor for very simple alert, with just title and body text
     * Default AlertType will be INFORMATION
     * @param title Heading
     * @param contentText Body Text
     */
    public StreamPiAlert(String title, String contentText)
    {
        this(title, contentText, StreamPiAlertType.INFORMATION);
    }

    /**
     * Constructor for alert with just "Alert" heading,
     * content text and alert type
     * @param contentText Body Text
     * @param alertType Alert Type
     */
    public StreamPiAlert(String contentText, StreamPiAlertType alertType)
    {
        this("Alert", contentText, alertType);
    }

    /**
     * Constructor to create Alert box with title, Alert Type and button choices
     * @param title Heading
     * @param streamPiAlertType Alert Type
     * @param buttons Button choices
     */
    public StreamPiAlert(String title, StreamPiAlertType streamPiAlertType, String... buttons)
    {
        set(title, streamPiAlertType, null, buttons);
    }

    /**
     * Constructor to create Alert Box with title, Alert Type, Body and button choices
     * @param title Heading of the alert box
     * @param streamPiAlertType Alert Type
     * @param contentPane Alert Body
     * @param buttons Button choices
     */
    public StreamPiAlert(String title, StreamPiAlertType streamPiAlertType, Pane contentPane, String... buttons)
    {
        set(title, streamPiAlertType, contentPane, buttons);
    }

    /**
     * Constructor to create Alert Box with Heading, content text, Alert Type
     * @param title Heading
     * @param contentText Body Text
     * @param streamPiAlertType Alert Type
     */
    public StreamPiAlert(String title, String contentText, StreamPiAlertType streamPiAlertType)
    {
        Label label = new Label(contentText);
        label.setWrapText(true);
        VBox vBox = new VBox(label);
        set(title, streamPiAlertType, vBox, "OK");
    }

    /**
     * Sets the alert properties
     * @param title Heading
     * @param streamPiAlertType Alert Type
     * @param contentPane Body
     * @param buttons Button choices
     */
    private void set(String title, StreamPiAlertType streamPiAlertType,
    Pane contentPane, String... buttons)
    {
        this.title = title;
        this.buttons = buttons;
        this.contentPane = contentPane;
        this.streamPiAlertType = streamPiAlertType;
    }

    /**
     * Set on click Listener
     * @param streamPiAlertListener Alert Listener, triggered when a button is clicked
     */
    public void setOnClicked(StreamPiAlertListener streamPiAlertListener)
    {
        this.streamPiAlertListener = streamPiAlertListener;
    }

    /**
     * @return Heading of the Alert Box
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * @return Button Choices
     */
    public String[] getButtons()
    {
        return buttons;
    }

    /**
     * @param contentPane Alert Body Node
     */
    public void setAlertContent(Pane contentPane)
    {
        this.contentPane = contentPane;
    }

    /**
     * Set all the button choices
     * @param buttons Array of button choices
     */
    public void setButtons(String... buttons)
    {
        this.buttons = buttons;
    }

    /**
     * Create and return the final AlertBox Pane, which will be added to the parent node
     * @param title Heading
     * @param contentPane The Alert Body Pane
     * @return Final VBox to be shown in the parent Stack Pane
     */
    private VBox getAlertPane(String title, Pane contentPane)
    {
        if(title.isEmpty())
            title = "Alert";           

        Label label = new Label(title);
        label.getStyleClass().add("alert_pane_header_text");

        FontIcon fontIcon = new FontIcon(streamPiAlertType.getIconCode());
        fontIcon.getStyleClass().addAll("alert_header_icon", streamPiAlertType.getIconStyleClassName());

        HBox header = new HBox(label, SpaceFiller.horizontal(), fontIcon);
        header.getStyleClass().add("alert_header");


        header.setPadding(new Insets(10));

        HBox buttonBar = new HBox();
        buttonBar.getStyleClass().add("alert_button_bar");

        for (String eachButtonString : buttons)
        {
            Button button = new Button(eachButtonString);
            button.setOnAction(event -> {
                if(this.streamPiAlertListener != null)
                {
                    this.streamPiAlertListener.onClick(eachButtonString);
                }
                destroy();
            });

            button.getStyleClass().add("alert_button");
            buttonBar.getChildren().add(button);
        }

        ScrollPane scrollPane = new ScrollPane(contentPane);
        scrollPane.prefHeightProperty().bind(contentPane.heightProperty().add(20));
        scrollPane.getStyleClass().add("alert_scroll_pane");
        scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
        VBox.setVgrow(scrollPane, Priority.ALWAYS);

        contentPane.getStyleClass().add("alert_content_pane");
        contentPane.prefWidthProperty().bind(scrollPane.widthProperty().subtract(10));

        VBox alertVBox = new VBox( header, scrollPane, buttonBar);
        alertVBox.getStyleClass().add("alert_pane");
        alertVBox.setMaxHeight(Double.NEGATIVE_INFINITY);

        return alertVBox;
    }

    /**
     * @return The body node of the Alert Box
     */
    public Pane getContentPane()
    {
        return contentPane;
    }

    /**
     * Adds the Alert Box to the parent node
     */
    private Node popupNode;
    public void show()
    {
        Platform.runLater(()->
        {
            popupNode = getAlertPane(getTitle(), getContentPane());
            stackPaneParent.getChildren().add(popupNode);

            Stage stage = (Stage) stackPaneParent.getScene().getWindow();

            if(!stage.isShowing())
                stage.show();

            if(stage.isIconified())
                stage.setIconified(false);

            stage.setAlwaysOnTop(true);
            stage.setAlwaysOnTop(false);
        });
    }

    /**
     * Removes the alert from the parent pane
     */
    public void destroy()
    {
        Platform.runLater(()-> stackPaneParent.getChildren().remove(popupNode));
    }
}
