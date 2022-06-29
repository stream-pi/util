/*
 * Stream-Pi - Free, Open-Source, Modular, Cross-Platform and Programmable Macro Pad
 * Copyright (C) 2019-2022 Debayan Sutradhar (rnayabed),  Samuel Quiñones (SamuelQuinones)
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

module com.stream_pi.util
{
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.fontawesome5;
    requires javafx.controls;
    requires java.logging;
    requires java.xml;
    requires org.controlsfx.controls;

    exports com.stream_pi.util;
    exports com.stream_pi.util.version;
    exports com.stream_pi.util.exception;
    exports com.stream_pi.util.platform;
    exports com.stream_pi.util.uihelper;
    exports com.stream_pi.util.startonboot;
    exports com.stream_pi.util.alert;
    exports com.stream_pi.util.checkforupdates;
    exports com.stream_pi.util.combobox;
    exports com.stream_pi.util.xmlconfighelper;
    exports com.stream_pi.util.loggerhelper;
    exports com.stream_pi.util.iohelper;
    exports com.stream_pi.util.comms;
    exports com.stream_pi.util.links;
    exports com.stream_pi.util.rootchecker;
    exports com.stream_pi.util.i18n.language;
    exports com.stream_pi.util.validation;

}