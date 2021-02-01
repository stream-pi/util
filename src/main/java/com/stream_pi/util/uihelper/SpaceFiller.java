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

package com.stream_pi.util.uihelper;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class SpaceFiller extends Region {

    public SpaceFiller(FillerType fillerType)
    {
        if(fillerType == FillerType.HBox)
        {
            HBox.setHgrow(this, Priority.ALWAYS);
        }
        else if(fillerType == FillerType.VBox)
        {
            VBox.setVgrow(this, Priority.ALWAYS);
        }
    }

    public enum FillerType
    {
        HBox, VBox
    }
}
