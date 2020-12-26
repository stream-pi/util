module com.StreamPi.Util {

    requires transitive org.kordamp.ikonli.javafx;
    requires transitive org.kordamp.ikonli.fontawesome5;
    requires transitive javafx.base;
    requires transitive java.logging;
    requires transitive javafx.controls;
    
    requires transitive java.xml;
    
    exports com.StreamPi.Util.Version;
    exports com.StreamPi.Util.Exception;
    exports com.StreamPi.Util.Platform;
    exports com.StreamPi.Util.FormHelper;
    exports com.StreamPi.Util.StartAtBoot;
    exports com.StreamPi.Util.Alert;
    exports com.StreamPi.Util.ComboBox;
    exports com.StreamPi.Util.XMLConfigHelper;
    exports com.StreamPi.Util.LoggerHelper;
}