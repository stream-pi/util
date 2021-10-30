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


package com.stream_pi.util.xmlconfighelper;

import java.io.File;
import java.util.logging.Level;
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
    
    private static final Logger logger = Logger.getLogger(XMLConfigHelper.class.getName());

    public static String getStringProperty(Node parentElement, String propertyName) throws Exception
    {
        return getProperty(parentElement, propertyName, false, null, null,null);
    }

    public static int getIntProperty(Node parentElement, String propertyName) throws Exception
    {
        return Integer.parseInt(getProperty(parentElement, propertyName, false, null, null, null));
    }


    public static double getDoubleProperty(Node parentElement, String propertyName) throws Exception
    {
        return Double.parseDouble(getProperty(parentElement, propertyName, false, null,null, null));
    }


    public static boolean getBooleanProperty(Node parentElement, String propertyName) throws Exception
    {
        return getProperty(parentElement, propertyName, false, null, null, null).equals("true");
    }

    public static String getProperty(Node parentElement, String propertyName, boolean createNewIfDoesntExist, String defaultValue, Document document, File file) throws Exception
    {
        try 
        {
            if(parentElement instanceof Document)
            {
                return ((Document) parentElement).getElementsByTagName(propertyName).item(0).getTextContent();
            }
            else if(parentElement instanceof Element)
            {
                return ((Element) parentElement).getElementsByTagName(propertyName).item(0).getTextContent();
            }
            else
            {
                throw new Exception("Passed parentElement asking for property "+propertyName+" is not Document/Element");
            }
        }
        catch (Exception e)
        {
            logger.warning("CANNOT get property "+parentElement.getNodeName()+"."+propertyName+"!");

            if (createNewIfDoesntExist)
            {
                logger.warning("Creating new property by that name ...");
                Element newProp = document.createElement(propertyName);
                newProp.setTextContent(defaultValue);
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

    public static String getStringProperty(Node parentElement, String propertyName, String ifNotPresent, boolean printStackTrace)
    {
        return getStringProperty(parentElement, propertyName, ifNotPresent, printStackTrace, false, null, null);
    }


    public static String getStringProperty(Node parentElement, String propertyName, String ifNotPresent, boolean printStackTrace, boolean createNewIfDoesntExist, 
        Document document, File file)
    {
        String tbr = ifNotPresent;
        try
        {
            tbr = getProperty(parentElement, propertyName, createNewIfDoesntExist, ifNotPresent, document, file);
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

    public static int getIntProperty(Node parentElement, String propertyName, int ifNotPresent, boolean printStackTrace)
    {
        return getIntProperty(parentElement, propertyName, ifNotPresent, printStackTrace, false, null, null);
    }


    public static int getIntProperty(Node parentElement, String propertyName, int ifNotPresent, boolean printStackTrace, boolean createNewIfDoesntExist, 
        Document document, File file)
    {
        int tbr = ifNotPresent;

        try
        {
            tbr = Integer.parseInt(getProperty(parentElement, propertyName, createNewIfDoesntExist, ifNotPresent+"", document, file));
        }
        catch(Exception e)
        {
            if(printStackTrace)
                e.printStackTrace();
        }
        return tbr;
    }

    public static double getDoubleProperty(Node parentElement, String propertyName, double ifNotPresent)
    {
        return getDoubleProperty(parentElement, propertyName, ifNotPresent, true, false, null, null);
    }

    public static double getDoubleProperty(Node parentElement, String propertyName, double ifNotPresent, boolean printStackTrace)
    {
        return getDoubleProperty(parentElement, propertyName, ifNotPresent, printStackTrace, false, null, null);
    }

    public static double getDoubleProperty(Node parentElement, String propertyName, double ifNotPresent, boolean printStackTrace, boolean createNewIfDoesntExist, 
        Document document, File file)
    {
        double tbr = ifNotPresent;

        try
        {
            tbr = Double.parseDouble(getProperty(parentElement, propertyName, createNewIfDoesntExist,ifNotPresent+"",  document, file));
        }
        catch(Exception e)
        {
            if(printStackTrace)
                e.printStackTrace();
        }
        return tbr;
    }

    public static String getStringProperty(Node parentElement, String propertyName, String ifNotPresent)
    {
        return getStringProperty(parentElement, propertyName, ifNotPresent, true, false, null, null);
    }

    public static boolean getBooleanProperty(Node parentElement, String propertyName, boolean ifNotPresent, boolean printStackTrace)
    {
        return getBooleanProperty(parentElement, propertyName, ifNotPresent, printStackTrace, false, null, null);
    }

    public static boolean getBooleanProperty(Node parentElement, String propertyName, boolean ifNotPresent, boolean printStackTrace, boolean createNewIfDoesntExist,
        Document document, File file)
    {
        boolean tbr = ifNotPresent;

        try
        {
            tbr = getProperty(parentElement, propertyName, createNewIfDoesntExist,ifNotPresent+"", document, file).equals("true");
        }
        catch(Exception e)
        {
            if(printStackTrace)
                e.printStackTrace();
        }
        return tbr;
    }

    public static boolean getBooleanProperty(Node parentElement, String propertyName, boolean ifNotPresent)
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
