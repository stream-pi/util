package com.stream_pi.util.i18n;

import com.stream_pi.util.exception.SevereException;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class I18N
{
    public static ResourceBundle RESOURCE_BUNDLE;

    public static void init(Locale locale)
    {
        try
        {
            RESOURCE_BUNDLE = ResourceBundle.getBundle(I18N.class.getPackageName()+".lang", locale);
        }
        catch (MissingResourceException e)
        {
            Logger.getLogger("").log(Level.SEVERE, "No language found for "+locale.toLanguageTag()+" ! Using default", e);

            RESOURCE_BUNDLE = ResourceBundle.getBundle(I18N.class.getPackageName()+".lang");
        }
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