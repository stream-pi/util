package com.stream_pi.util.alert;

public enum StreamPiAlertType {
    INFORMATION("fas-info", "alert_information_icon"),
    WARNING("fas-exclamation-triangle", "alert_warning_icon"),
    ERROR("fas-times", "alert_error_icon");

    private final String fontAwesomeIconCode;
    private final String alertIconStyleClassName;

    StreamPiAlertType(String fontAwesomeIconCode, String alertIconStyleClassName)
    {
        this.fontAwesomeIconCode = fontAwesomeIconCode;
        this.alertIconStyleClassName = alertIconStyleClassName;
    }

    public String getIconCode()
    {
        return fontAwesomeIconCode;
    }

    public String getIconStyleClassName()
    {
        return alertIconStyleClassName;
    }
}
