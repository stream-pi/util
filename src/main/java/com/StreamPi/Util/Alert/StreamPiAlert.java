package com.StreamPi.Util.Alert;

import com.StreamPi.Util.FormHelper.SpaceFiller;
import com.StreamPi.Util.FormHelper.SpaceFiller.FillerType;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class StreamPiAlert {
    private String title;
    private String[] buttons;
    private StreamPiAlertType streamPiAlertType;
    private Node contentNode;

    private static StackPane stackPaneParent;

    public static void setParent(StackPane parent) {
        stackPaneParent = parent;
    }

    public static StackPane getParent() {
        return stackPaneParent;
    }

    private StreamPiAlertListener streamPiAlertListener = null;

    public StreamPiAlert(String title, StreamPiAlertType streamPiAlertType,
     Node contentNode, String... buttons) {
       set(title, streamPiAlertType, contentNode, buttons);
    }

    public void setStreamPiAlertType(StreamPiAlertType streamPiAlertType)
    {
        this.streamPiAlertType = streamPiAlertType;
    }

    public StreamPiAlert(String title, String contentText, String... buttons)
    {
        Label label = new Label(contentText);
        label.setWrapText(true);
        
        set(title, StreamPiAlertType.INFORMATION, label, buttons);
    }

    public StreamPiAlert(String title, String contentText)
    {
        Label label = new Label(contentText);
        label.setWrapText(true);
        
        set(title, StreamPiAlertType.INFORMATION, label, new String[]{ "OK" });
    }

    private void set(String title, StreamPiAlertType streamPiAlertType,
    Node contentNode, String... buttons)
    {
        this.title = title;
        this.buttons = buttons;
        this.contentNode = contentNode;
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

    public void setAlertContent(Node contentNode) {
        this.contentNode = contentNode;
    }

    public void setButtons(String... buttons) {
        this.buttons = buttons;
    }

    public VBox getAlertPane(String title, Node alertPane) {
        VBox alertVBox = new VBox();
        alertVBox.getStyleClass().addAll("alert-pane");

        Label label = new Label(title);
        label.getStyleClass().add("alert-pane-header-text");

        HBox header = new HBox(label, new SpaceFiller(FillerType.HBox), streamPiAlertType.getIcon());

        header.setPadding(new Insets(10));

        HBox buttonBar = new HBox();
        buttonBar.getStyleClass().add("alert-button-bar");

        for (String eachButtonString : buttons) {
            Button button = new Button(eachButtonString);
            button.setOnAction(event -> {
                if(this.streamPiAlertListener != null)
                    this.streamPiAlertListener.onClick(eachButtonString);
            });

            button.getStyleClass().add("alert-button");

            buttonBar.getChildren().add(button);
        }

        alertPane.getStyleClass().addAll("alert-content-pane");

        alertVBox.getChildren().addAll(
            header,
            alertPane,
            buttonBar
        );


        return alertVBox;
    }

    public Node getContentNode()
    {
        return contentNode;
    }

    public void show()
    {
        stackPaneParent.getChildren().add(getAlertPane(getTitle(), getContentNode()));
        stackPaneParent.toFront();
        stackPaneParent.setVisible(true);
    }

    public void destroy()
    {
        stackPaneParent.getChildren().clear();
        stackPaneParent.toBack();
        stackPaneParent.setVisible(false);
    }
}
