/*
 * Stream-Pi - Free, Open-Source, Modular, Cross-Platform and Programmable Macro Pad
 * Copyright (C) 2019-2022 Debayan Sutradhar (rnayabed),  Samuel Quiñones (SamuelQuinones)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * Author: Debayan Sutradhar (@rnayabed)
 */

package com.stream_pi.util.uihelper;

import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.io.ByteArrayInputStream;

/**
 * All in One Background record utility.
 * This is used as background image of each folder (or default background of profile) and each action.
 */
public record BackgroundHelper(byte[] image, double width, double height, boolean preserveRatio, boolean smooth,
                               BackgroundRepeat repeatX,
                               BackgroundRepeat repeatY,
                               BackgroundPosition position, boolean contain, boolean cover)
{
    /*
    Returns JavaFX Background that can be directly used as action/profile/folder background.
     */
    public Background getBackground()
    {
        return new Background(
                new BackgroundImage(
                        new Image(new ByteArrayInputStream(image), width, height, preserveRatio, smooth),
                        repeatX, repeatY, position, new BackgroundSize(100, 100, true, true, contain, cover)
                )
        );
    }
}
