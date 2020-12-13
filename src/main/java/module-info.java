module com.StreamPi.Util {

    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.fontawesome5;
    requires javafx.base;
    requires javafx.controls;

    exports com.StreamPi.Util.Version;
    exports com.StreamPi.Util.Exception;
    exports com.StreamPi.Util.Platform;
    exports com.StreamPi.Util.FormHelper;
    exports com.StreamPi.Util.StartAtBoot;
}