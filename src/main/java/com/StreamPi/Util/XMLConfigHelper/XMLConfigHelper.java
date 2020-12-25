package com.StreamPi.Util.XMLConfigHelper;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class XMLConfigHelper {

    public static String getStringProperty(Element parentElement, String propertyName) throws Exception
    {
        return getProperty(parentElement, propertyName);
    }

    public static int getIntProperty(Element parentElement, String propertyName) throws Exception
    {
        return Integer.parseInt(getProperty(parentElement, propertyName));
    }

    public static boolean getBooleanProperty(Element parentElement, String propertyName) throws Exception
    {
        return getProperty(parentElement, propertyName).equals("true");
    }

    public static String getProperty(Element parentElement, String propertyName) throws Exception
    {
        return parentElement.getElementsByTagName(propertyName).item(0).getTextContent();
    }


    public static String getStringProperty(Element parentElement, String propertyName, String ifNotPresent, boolean printStackTrace)
    {
        String tbr = ifNotPresent;

        try
        {
            tbr = getProperty(parentElement, propertyName);
        }
        catch(Exception e)
        {
            if(printStackTrace)
                e.printStackTrace();
        }


        return tbr;
    }


    public static void removeChilds(Node node) {
        while (node.hasChildNodes())
            node.removeChild(node.getFirstChild());
    }

    public static String getStringProperty(Element parentElement, String propertyName, String ifNotPresent)
    {
        return getStringProperty(parentElement, propertyName, ifNotPresent, true);
    }

    public static int getIntProperty(Element parentElement, String propertyName, int ifNotPresent)
    {
        int tbr = ifNotPresent;

        try
        {
            tbr = Integer.parseInt(getProperty(parentElement, propertyName));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }


        return tbr;
    }

    public static boolean getBooleanProperty(Element parentElement, String propertyName, boolean ifNotPresent)
    {
        boolean tbr = ifNotPresent;

        try
        {
            tbr = getProperty(parentElement, propertyName).equals("true");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return tbr;
    }

    public static boolean doesElementExist(Element parent, String nameOfElement)
    {
        return parent.getElementsByTagName(nameOfElement).getLength() > 0;
    }

    public static boolean doesElementExist(Document document, String nameOfElement)
    {
        return document.getElementsByTagName(nameOfElement).getLength() > 0;
    }

}
