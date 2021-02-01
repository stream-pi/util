package com.streampi.util.xmlconfighelper;

import java.io.File;
import java.util.logging.Logger;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class XMLConfigHelper {
    
    private static Logger logger = Logger.getLogger(XMLConfigHelper.class.getName());

    public static String getStringProperty(Element parentElement, String propertyName) throws Exception
    {
        return getProperty(parentElement, propertyName, false, null, null);
    }

    public static int getIntProperty(Element parentElement, String propertyName) throws Exception
    {
        return Integer.parseInt(getProperty(parentElement, propertyName, false, null, null));
    }


    public static double getDoubleProperty(Element parentElement, String propertyName) throws Exception
    {
        return Double.parseDouble(getProperty(parentElement, propertyName, false, null, null));
    }


    public static boolean getBooleanProperty(Element parentElement, String propertyName) throws Exception
    {
        return getProperty(parentElement, propertyName, false, null, null).equals("true");
    }

    public static String getProperty(Element parentElement, String propertyName, boolean createNewIfDoesntExist, Document document, File file) throws Exception
    {
        try {
               return parentElement.getElementsByTagName(propertyName).item(0).getTextContent();
        }
        catch (Exception e)
        {
            logger.warning("CANNOT get property "+parentElement.getNodeName()+"."+propertyName+"!");

            if(createNewIfDoesntExist)
            {
                logger.warning("Creating new property by that name ...");

                Element newProp = document.createElement(propertyName);
                parentElement.appendChild(newProp);
                
                save(document, file);
            }

            throw e;
        }
    }

    public static void save(Document document, File file) throws Exception
    {
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        Result output = new StreamResult(file);
        Source input = new DOMSource(document);

        transformer.transform(input, output);
    }

    public static String getStringProperty(Element parentElement, String propertyName, String ifNotPresent, boolean printStackTrace)
    {
        return getStringProperty(parentElement, propertyName, ifNotPresent, printStackTrace, false, null, null);
    }


    public static String getStringProperty(Element parentElement, String propertyName, String ifNotPresent, boolean printStackTrace, boolean createNewIfDoesntExist, 
        Document document, File file)
    {
        String tbr = ifNotPresent;

        try
        {
            tbr = getProperty(parentElement, propertyName, createNewIfDoesntExist, document, file);
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

    public static int getIntProperty(Element parentElement, String propertyName, int ifNotPresent, boolean printStackTrace)
    {
        return getIntProperty(parentElement, propertyName, ifNotPresent, printStackTrace, false, null, null);
    }


    public static int getIntProperty(Element parentElement, String propertyName, int ifNotPresent, boolean printStackTrace, boolean createNewIfDoesntExist, 
        Document document, File file)
    {
        int tbr = ifNotPresent;

        try
        {
            tbr = Integer.parseInt(getProperty(parentElement, propertyName, createNewIfDoesntExist, document, file));
        }
        catch(Exception e)
        {
            if(printStackTrace)
                e.printStackTrace();
        }


        return tbr;
    }

    public static double getDoubleProperty(Element parentElement, String propertyName, double ifNotPresent)
    {
        return getDoubleProperty(parentElement, propertyName, ifNotPresent, true, false, null, null);
    }

    public static double getDoubleProperty(Element parentElement, String propertyName, double ifNotPresent, boolean printStackTrace)
    {
        return getDoubleProperty(parentElement, propertyName, ifNotPresent, printStackTrace, false, null, null);
    }

    public static double getDoubleProperty(Element parentElement, String propertyName, double ifNotPresent, boolean printStackTrace, boolean createNewIfDoesntExist, 
        Document document, File file)
    {
        double tbr = ifNotPresent;

        try
        {
            tbr = Double.parseDouble(getProperty(parentElement, propertyName, createNewIfDoesntExist, document, file));
        }
        catch(Exception e)
        {
            if(printStackTrace)
                e.printStackTrace();
        }


        return tbr;
    }

    public static String getStringProperty(Element parentElement, String propertyName, String ifNotPresent)
    {
        return getStringProperty(parentElement, propertyName, ifNotPresent, true, false, null, null);
    }

    public static boolean getBooleanProperty(Element parentElement, String propertyName, boolean ifNotPresent, boolean printStackTrace)
    {
        return getBooleanProperty(parentElement, propertyName, ifNotPresent, printStackTrace, false, null, null);
    }


    public static boolean getBooleanProperty(Element parentElement, String propertyName, boolean ifNotPresent, boolean printStackTrace, boolean createNewIfDoesntExist, 
        Document document, File file)
    {
        boolean tbr = ifNotPresent;

        try
        {
            tbr = getProperty(parentElement, propertyName, createNewIfDoesntExist, document, file).equals("true");
        }
        catch(Exception e)
        {
            if(printStackTrace)
                e.printStackTrace();
        }

        return tbr;
    }

    public static boolean getBooleanProperty(Element parentElement, String propertyName, boolean ifNotPresent)
    {
        return getBooleanProperty(parentElement, propertyName, ifNotPresent, true, false, null, null);
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
