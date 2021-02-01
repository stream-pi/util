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
