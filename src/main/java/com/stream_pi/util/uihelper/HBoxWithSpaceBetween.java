package com.stream_pi.util.uihelper;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class HBoxWithSpaceBetween extends HBox
{
    public HBoxWithSpaceBetween(Node node1, Node node2, double spacing)
    {
        getChildren().addAll(
                node1,
                SpaceFiller.horizontal(),
                node2
        );

        setSpacing(spacing);
    }

    public HBoxWithSpaceBetween(Node node1, Node node2)
    {
        this(node1, node2, 5.0);
    }

    public HBoxWithSpaceBetween(String labelText, Node node2)
    {
        this(new Label(labelText), node2);
    }
}
