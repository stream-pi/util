package com.stream_pi.util.i18n;

import java.util.Locale;
import java.util.ResourceBundle;

public class I18N
{
    public static ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(I18N.class.getPackageName()+".lang");

    public static void init(Locale locale)
    {
        RESOURCE_BUNDLE = ResourceBundle.getBundle(I18N.class.getPackageName()+".lang", locale);
    }

    public static String getString(String key, Object... args)
    {
        String result;

        if (RESOURCE_BUNDLE.containsKey(key))
        {
            result = RESOURCE_BUNDLE.getString(key);
        }
        else
        {
            result = key;
        }

        if (args.length == 0)
        {
            return result;
        }
        else
        {
            return String.format(result, args);
        }
    }
}