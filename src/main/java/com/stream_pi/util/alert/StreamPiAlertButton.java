package com.stream_pi.util.alert;

import com.stream_pi.util.i18n.I18N;
import javafx.scene.control.Button;
import org.kordamp.ikonli.javafx.FontIcon;

public class StreamPiAlertButton
{
    private final String title;
    private final String fontIconCode;

    public StreamPiAlertButton(String title)
    {
        this(title, null);
    }

    public StreamPiAlertButton(String title, String fontIconCode)
    {
        this.title = title;
        this.fontIconCode = fontIconCode;
    }

    public Button getButton()
    {
        Button button = new Button(title);

        if(fontIconCode != null)
        {
            button.setGraphic(new FontIcon(fontIconCode));
        }

        return button;
    }

    public static StreamPiAlertButton YES()
    {
        return new StreamPiAlertButton(I18N.getString("alert.StreamPiAlertButtonDefaults.yes"));
    }

    public static StreamPiAlertButton NO()
    {
        return new StreamPiAlertButton(I18N.getString("alert.StreamPiAlertButtonDefaults.no"));
    }

    public static StreamPiAlertButton OK()
    {
        return new StreamPiAlertButton(I18N.getString("alert.StreamPiAlertButtonDefaults.ok"));
    }

    public static StreamPiAlertButton CANCEL()
    {
        return new StreamPiAlertButton(I18N.getString("alert.StreamPiAlertButtonDefaults.cancel"));
    }
}
