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

package com.stream_pi.util.alert;

import com.stream_pi.util.i18n.I18N;
import javafx.scene.control.Button;
import org.kordamp.ikonli.javafx.FontIcon;

public class StreamPiAlertButton
{
    private final String text;
    private final String fontIconCode;

    public StreamPiAlertButton(String text)
    {
        this(text, null);
    }

    public StreamPiAlertButton(String text, String fontIconCode)
    {
        this.text = text;
        this.fontIconCode = fontIconCode;
    }

    public Button getButton()
    {
        Button button = new Button(text);

        if(fontIconCode != null)
        {
            button.setGraphic(new FontIcon(fontIconCode));
        }

        return button;
    }

    public static final StreamPiAlertButton YES = new StreamPiAlertButton(I18N.getString("alert.StreamPiAlertButtonDefaults.yes"));
    public static final StreamPiAlertButton NO = new StreamPiAlertButton(I18N.getString("alert.StreamPiAlertButtonDefaults.no"));
    public static final StreamPiAlertButton OK = new StreamPiAlertButton(I18N.getString("alert.StreamPiAlertButtonDefaults.ok"));
    public static final StreamPiAlertButton CANCEL = new StreamPiAlertButton(I18N.getString("alert.StreamPiAlertButtonDefaults.cancel"));
}
