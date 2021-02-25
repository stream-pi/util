/*
Stream-Pi - Free & Open-Source Modular Cross-Platform Programmable Macro Pad
Copyright (C) 2019-2021  Debayan Sutradhar (rnayabed),  Samuel Quiñones (SamuelQuinones)

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.
This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

Originally Written by : Debayan Sutradhar (rnayabed)
*/

module com.stream_pi.util
{
    requires transitive org.kordamp.ikonli.javafx;
    requires transitive org.kordamp.ikonli.fontawesome5;
    requires transitive javafx.base;
    requires transitive java.logging;
    requires transitive javafx.controls;
    requires transitive java.xml;
    requires com.google.gson;
    
    exports com.stream_pi.util.version;
    exports com.stream_pi.util.exception;
    exports com.stream_pi.util.platform;
    exports com.stream_pi.util.uihelper;
    exports com.stream_pi.util.startatboot;
    exports com.stream_pi.util.alert;
    exports com.stream_pi.util.checkforupdates;
    exports com.stream_pi.util.combobox;
    exports com.stream_pi.util.xmlconfighelper;
    exports com.stream_pi.util.loggerhelper;
    exports com.stream_pi.util.iohelper;
    exports com.stream_pi.util.comms;
}