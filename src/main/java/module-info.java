module com.stream_pi.util {

    requires transitive org.kordamp.ikonli.javafx;
    requires transitive org.kordamp.ikonli.fontawesome5;
    requires transitive javafx.base;
    requires transitive java.logging;
    requires transitive javafx.controls;
    
    requires transitive java.xml;
    
    exports com.stream_pi.util.version;
    exports com.stream_pi.util.exception;
    exports com.stream_pi.util.platform;
    exports com.stream_pi.util.uihelper;
    exports com.stream_pi.util.startatboot;
    exports com.stream_pi.util.alert;
    exports com.stream_pi.util.combobox;
    exports com.stream_pi.util.xmlconfighelper;
    exports com.stream_pi.util.loggerhelper;
    exports com.stream_pi.util.iohelper;
}