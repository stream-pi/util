package com.stream_pi.util;

import com.stream_pi.util.i18n.I18N;
import com.stream_pi.util.version.Version;

import java.util.Locale;

public class Util
{
    public static final Version VERSION = new Version(1,0,0);

    public static void initI18n(Locale locale)
    {
        I18N.init(locale);
    }
}
