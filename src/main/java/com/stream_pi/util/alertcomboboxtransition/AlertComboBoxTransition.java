
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
