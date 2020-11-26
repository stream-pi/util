module com.StreamPi.Util {
    requires org.apache.commons.configuration2;
    requires org.slf4j;

    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.fontawesome5;
    requires javafx.base;
    requires javafx.controls;

    exports com.StreamPi.Util.Version;
    exports com.StreamPi.Util.Exception;
    exports com.StreamPi.Util.Platform;
    exports com.StreamPi.Util.FormHelper;
}