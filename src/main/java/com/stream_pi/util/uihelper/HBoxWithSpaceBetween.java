package com.stream_pi.util.uihelper;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class HBoxWithSpaceBetween extends HBox
{
    public HBoxWithSpaceBetween(Node node1, Node node2, double spacing)
    {
        make(node1, node2, spacing);
    }

    public HBoxWithSpaceBetween(Node node1, Node node2)
    {
        make(node1, node2, 5.0);
    }

    public HBoxWithSpaceBetween(String labelText, Node node2)
    {
        Label label = new Label(labelText);
        label.setWrapText(true);
        label.setAlignment(Pos.CENTER_LEFT);
        label.prefHeightProperty().bind(heightProperty());
        make(label, node2, 5.0);
    }

    public void make(Node node1, Node node2, double spacing)
    {
        getChildren().addAll(
                node1,
                SpaceFiller.horizontal(),
                node2
        );

        setSpacing(spacing);
    }

}
