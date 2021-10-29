package com.stream_pi.util.i18n;

import java.util.Locale;

public class Language
{
    private final String displayName;
    private final Locale locale;

    public Language( Locale locale, String displayName)
    {
        this.displayName = displayName;
        this.locale = locale;
    }

    public String getDisplayName()
    {
        return displayName;
    }

    public Locale getLocale()
    {
        return locale;
    }
}
