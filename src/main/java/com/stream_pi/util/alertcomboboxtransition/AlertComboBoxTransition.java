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
 */

package com.stream_pi.util.alertcomboboxtransition;

import javafx.animation.*;
import javafx.scene.Node;
import javafx.util.Duration;

public class AlertComboBoxTransition
{
    public static Animation createShowTransition(Node node)
    {
        Timeline showTimeline = new Timeline();
        showTimeline.setCycleCount(1);

        showTimeline.getKeyFrames().addAll(
                new KeyFrame(Duration.millis(0.0D),
                        new KeyValue(node.opacityProperty(),
                                0.0D, Interpolator.LINEAR)
                ),
                new KeyFrame(Duration.millis(100.0D),
                        new KeyValue(node.opacityProperty(),
                                1.0D, Interpolator.LINEAR)
                )
        );

        return showTimeline;
    }

    public static Animation createCloseTransition(Node node)
    {
        Timeline closeTimeline = new Timeline();
        closeTimeline.setCycleCount(1);

        closeTimeline.getKeyFrames().addAll(
                new KeyFrame(Duration.millis(0.0D),
                        new KeyValue(node.opacityProperty(),
                                1.0D, Interpolator.LINEAR)
                ),
                new KeyFrame(Duration.millis(100.0D),
                        new KeyValue(node.opacityProperty(),
                                0.0D, Interpolator.LINEAR)
                )
        );

        return closeTimeline;
    }
}
