module com.streampi.util {

    requires transitive org.kordamp.ikonli.javafx;
    requires transitive org.kordamp.ikonli.fontawesome5;
    requires transitive javafx.base;
    requires transitive java.logging;
    requires transitive javafx.controls;
    
    requires transitive java.xml;
    
    exports com.streampi.util.version;
    exports com.streampi.util.exception;
    exports com.streampi.util.platform;
    exports com.streampi.util.uihelper;
    exports com.streampi.util.startatboot;
    exports com.streampi.util.alert;
    exports com.streampi.util.combobox;
    exports com.streampi.util.xmlconfighelper;
    exports com.streampi.util.loggerhelper;
    exports com.streampi.util.iohelper;
}