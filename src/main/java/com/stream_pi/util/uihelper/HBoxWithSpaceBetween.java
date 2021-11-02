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
