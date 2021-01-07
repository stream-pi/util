package com.StreamPi.Util.Alert;

import com.StreamPi.Util.FormHelper.SpaceFiller;
import com.StreamPi.Util.FormHelper.SpaceFiller.FillerType;
import org.kordamp.ikonli.javafx.FontIcon;

import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class StreamPiAlert {
    private String title;
    private String[] buttons;
    private StreamPiAlertType streamPiAlertType;
    private Pane contentPane;

    private static StackPane stackPaneParent = null;

    public static void setParent(StackPane parent) {
        stackPaneParent = parent;

        stackPaneParent.getStyleClass().add("alert_pane_parent");

        stackPaneParent.getChildren().addListener(new ListChangeListener<Node>()
        {
            @Override
            public void onChanged(Change<? extends Node> c) {
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
            }
        });
    }

    public static StackPane getParent() {
        return stackPaneParent;
    }

    private StreamPiAlertListener streamPiAlertListener = null;

    public StreamPiAlert(String title, StreamPiAlertType streamPiAlertType,
     Pane contentPane) {
       set(title, streamPiAlertType, contentPane, new String[]{ "OK" });
    }

    public void setStreamPiAlertType(StreamPiAlertType streamPiAlertType)
    {
        this.streamPiAlertType = streamPiAlertType;
    }

    public StreamPiAlert(String title, String contentText)
    {
        this(title, contentText, StreamPiAlertType.INFORMATION);
    }
    
    public StreamPiAlert(String contentText, StreamPiAlertType alertType)
    {
        this("Alert", contentText, alertType);
    }
    

    public StreamPiAlert(String title, StreamPiAlertType streamPiAlertType, String... buttons)
    {
        set(title, streamPiAlertType, null, buttons);
    }

    public StreamPiAlert(String title, StreamPiAlertType streamPiAlertType, Pane contentPane, String... buttons)
    {
        set(title, streamPiAlertType, contentPane, buttons);
    }

    public StreamPiAlert(String title, String contentText, StreamPiAlertType streamPiAlertType)
    {
        Label label = new Label(contentText);
        label.setWrapText(true);

        VBox vBox = new VBox(label);
        
        set(title, streamPiAlertType, vBox, new String[]{ "OK" });
    }

    private void set(String title, StreamPiAlertType streamPiAlertType,
    Pane contentPane, String... buttons)
    {
        this.title = title;
        this.buttons = buttons;
        this.contentPane = contentPane;
        this.streamPiAlertType = streamPiAlertType;
    }


    public void setOnClicked(StreamPiAlertListener streamPiAlertListener) {
        this.streamPiAlertListener = streamPiAlertListener;
    }

    public String getTitle() {
        return title;
    }

    public String[] getButtons() {
        return buttons;
    }

    public void setAlertContent(Pane contentPane) {
        this.contentPane = contentPane;
    }

    public void setButtons(String... buttons) {
        this.buttons = buttons;
    }

    public VBox getAlertPane(String title, Pane alertPane) {
        VBox alertVBox = new VBox();
        alertVBox.getStyleClass().add("alert_pane");

        if(title.isEmpty())
            title = "Alert";           

        Label label = new Label(title);
        label.getStyleClass().add("alert_pane_header_text");

        FontIcon fontIcon = new FontIcon(streamPiAlertType.getIconCode());
        fontIcon.getStyleClass().addAll("alert_header_icon", streamPiAlertType.getIconStyleClassName());

        HBox header = new HBox(label, new SpaceFiller(FillerType.HBox), fontIcon);
        header.getStyleClass().add("alert_header");


        header.setPadding(new Insets(10));

        HBox buttonBar = new HBox();
        buttonBar.getStyleClass().add("alert_button_bar");

        for (String eachButtonString : buttons) {
            Button button = new Button(eachButtonString);
            button.setOnAction(event -> {
                if(this.streamPiAlertListener != null)
                    this.streamPiAlertListener.onClick(eachButtonString);

                destroy();
            });

            button.getStyleClass().add("alert_button");

            buttonBar.getChildren().add(button);
        }

        alertPane.getStyleClass().add("alert_content_pane");

        ScrollPane scrollPane = new ScrollPane(alertPane);
        scrollPane.getStyleClass().add("alert_scroll_pane");

        alertPane.prefWidthProperty().bind(scrollPane.widthProperty().subtract(10));

        scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);

        VBox.setVgrow(scrollPane, Priority.ALWAYS);

        alertVBox.getChildren().addAll(
            header,
            scrollPane,
            buttonBar
        );


        return alertVBox;
    }

    public Pane getContentPane()
    {
        return contentPane;
    }

    private Node popupNode;
    public void show()
    {
        Platform.runLater(()->{
            popupNode = getAlertPane(getTitle(), getContentPane());
            stackPaneParent.getChildren().add(popupNode);
        });
    }

    public void destroy()
    {
        Platform.runLater(()->{
            stackPaneParent.getChildren().remove(popupNode);
        });
    }
}
