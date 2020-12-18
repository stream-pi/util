package com.StreamPi.Util.Alert;

import org.kordamp.ikonli.javafx.FontIcon;

import javafx.scene.paint.Paint;

public enum StreamPiAlertType {
    INFORMATION("fas-info"),
    ALERT("fas-exclamation-triangle"),
    ERROR("fas-times");

    private final String fontAwesomeIconCode;

    StreamPiAlertType(String fontAwesomeIconCode)
    {
        this.fontAwesomeIconCode = fontAwesomeIconCode;
    }

    public FontIcon getIcon()
    {
        return getIcon("#000000",13);
    }

    public FontIcon getIcon(String colorHex, int size)
    {
        FontIcon fontIcon = new FontIcon(fontAwesomeIconCode);
        fontIcon.setIconSize(size);
        fontIcon.setIconColor(Paint.valueOf(colorHex));

        return fontIcon;
    }
}
