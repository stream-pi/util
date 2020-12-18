package com.StreamPi.Util.Alert;

import org.kordamp.ikonli.javafx.FontIcon;

import javafx.scene.paint.Paint;

public enum StreamPiAlertType {
    INFORMATION("fas-info"),
    WARNING("fas-exclamation-triangle"),
    ERROR("fas-times");

    private final String fontAwesomeIconCode;

    StreamPiAlertType(String fontAwesomeIconCode)
    {
        this.fontAwesomeIconCode = fontAwesomeIconCode;
    }

    public String getIconCode()
    {
        return fontAwesomeIconCode;
    }
}
