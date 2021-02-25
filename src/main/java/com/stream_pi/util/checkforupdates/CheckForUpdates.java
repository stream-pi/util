package com.stream_pi.util.checkforupdates;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.stream_pi.util.alert.StreamPiAlert;
import com.stream_pi.util.alert.StreamPiAlertType;
import com.stream_pi.util.platform.PlatformType;
import com.stream_pi.util.version.Version;
import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class CheckForUpdates extends Task<Void>
{

    private final Button checkForUpdatesButton;
    private final HostServices hostServices;
    private final PlatformType platformType;
    private final Version currentVersion;

    public CheckForUpdates(Button checkForUpdatesButton, HostServices hostServices,
                           PlatformType platformType, Version currentVersion)
    {
        this.checkForUpdatesButton = checkForUpdatesButton;
        this.hostServices = hostServices;
        this.platformType = platformType;
        this.currentVersion = currentVersion;

        new Thread(this).start();
    }

    @Override
    protected Void call()
    {
        try
        {
            Platform.runLater(()->checkForUpdatesButton.setDisable(true));

            String url_pre = "https://stream-pi.com/API/get_latest.php?TYPE=";

            if(platformType == PlatformType.SERVER)
            {
                url_pre+="SERVER";
            }
            else
            {
                url_pre+="CLIENT";
            }

            String content = readUrl(url_pre);


            JsonObject jsonObject = JsonParser.parseString(content).getAsJsonObject();

            String latestVersionRaw = jsonObject.get("Version").getAsString();
            String releasePage = jsonObject.get("Release Page").getAsString();

            Version latestVersion = new Version(latestVersionRaw);

            if(latestVersion.isBiggerThan(currentVersion))
            {
                VBox vBox = new VBox();

                Hyperlink urlLabel = new Hyperlink(releasePage);
                urlLabel.setOnAction(event->hostServices.showDocument(releasePage));

                Label label = new Label(
                        "New Version "+latestVersionRaw+" Available.\n" +
                                "Current Version "+currentVersion.getText()+".\n"+
                                "Changelog and install instructions are included in the release page.\n" +
                                "It is recommended to update to ensure maximum stability and least bugs.");
                label.setWrapText(true);

                vBox.setSpacing(5);
                vBox.getChildren().addAll(
                        urlLabel,
                        label
                );

                new StreamPiAlert("New Update Available!", StreamPiAlertType.INFORMATION, vBox).show();
            }
            else
            {
                new StreamPiAlert("Up to Date", "System is up to date. ("+currentVersion.getText()+")", StreamPiAlertType.INFORMATION).show();;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            new StreamPiAlert("Uh Oh", "Update Check Failed. \n\nMessage : "+e.getMessage(), StreamPiAlertType.ERROR).show();;
        }
        finally
        {
            Platform.runLater(()->checkForUpdatesButton.setDisable(false));
        }
        return null;
    }


    private String readUrl(String urlString) throws Exception
    {
        URL url = new URL(urlString);
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
        StringBuilder buffer = new StringBuilder();
        int read;
        char[] chars = new char[1024];
        while ((read = reader.read(chars)) != -1)
            buffer.append(chars, 0, read);

        reader.close();
        return buffer.toString();
    }
}
