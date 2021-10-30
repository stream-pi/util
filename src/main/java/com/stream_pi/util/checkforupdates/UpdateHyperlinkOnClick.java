/*
 * Stream-Pi - Free & Open-Source Modular Cross-Platform Programmable Macropad
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

package com.stream_pi.util.checkforupdates;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * Handler for after update check succeeds
 */
public abstract class UpdateHyperlinkOnClick implements EventHandler<ActionEvent>
{
    private String URL;

    /**
     * @param URL URL to download page for new release
     */
    public void setURL(String URL)
    {
        this.URL = URL;
    }

    /**
     * @return Returns URL to download page for new release
     */
    public String getURL()
    {
        return URL;
    }
}
