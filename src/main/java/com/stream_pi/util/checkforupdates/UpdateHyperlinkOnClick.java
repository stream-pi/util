package com.stream_pi.util.checkforupdates;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public abstract class UpdateHyperlinkOnClick implements EventHandler<ActionEvent>
{
    private String URL;
    public void setURL(String URL)
    {
        this.URL = URL;
    }

    public String getURL()
    {
        return URL;
    }
}
