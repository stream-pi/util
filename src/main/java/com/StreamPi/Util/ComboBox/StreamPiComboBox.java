package com.StreamPi.Util.ComboBox;

import java.util.ArrayList;
import java.util.List;

import com.StreamPi.Util.FormHelper.SpaceFiller;
import com.StreamPi.Util.FormHelper.SpaceFiller.FillerType;

import org.kordamp.ikonli.javafx.FontIcon;

import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class StreamPiComboBox<T> extends HBox
{
    private List<T> options;

    private static StackPane stackPaneParent;

    public static void setParent(StackPane parent) {
        stackPaneParent = parent;

        stackPaneParent.getStyleClass().add("combobox_pane_parent");
    }
    
    public StreamPiComboBox(List<T> options)
    {
        setup();

        setOptions(options);
    }

    public StreamPiComboBox()
    {
        setup();
    }

    private Label currentSelectedLabel;
    private void setup()
    {
        buttons = new ArrayList<>();

        getStyleClass().add("combo_box");
        setOnMouseClicked(event->show());

        currentSelectedLabel = new Label();

        FontIcon fontIcon = new FontIcon();
        fontIcon.getStyleClass().add("combo_box_drop_down_icon");

        getChildren().addAll(
            currentSelectedLabel,
            new SpaceFiller(FillerType.HBox),
            fontIcon
        );
    }

    public void setOptions(List<T> options)
    {
        this.options = options;
        setCurrentSelectedItemIndex(0);
    }


    private int currentIndex = 0;
    private List<ToggleButton> buttons;

    public ScrollPane getPopupScrollPane()
    {
        buttons.clear();

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);

        scrollPane.getStyleClass().add("combo_box_popup");

        VBox vBox = new VBox();
        vBox.getStyleClass().add("combo_box_popup_vbox");
        vBox.prefWidthProperty().bind(scrollPane.widthProperty().subtract(10));
    
        scrollPane.setContent(vBox);

        for(int i = 0;i<options.size();i++)
        {
            T eachOptionObj = options.get(i);
            String displayText = streamPiComboBoxFactory.getOptionDisplayText(eachOptionObj);

            ToggleButton optionButton = new ToggleButton(displayText);
            optionButton.setSelected(i == currentIndex);
            optionButton.setUserData(i);
            optionButton.setOnAction(event->
            {
                setCurrentSelectedItemIndex((int) optionButton.getUserData());

                if(streamPiComboBoxListener != null)
                    streamPiComboBoxListener.onNewItemSelected(options.get(currentIndex));
                
                destroy();
            });
            vBox.getChildren().addAll(optionButton);
            buttons.add(optionButton);
        }

        return scrollPane;
    }

    public int getCurrentIndex()
    {
        return currentIndex;
    }

    public T getCurrentSelectedItem()
    {
        return options.get(currentIndex);
    }

    public void setCurrentSelectedItemIndex(int index)
    {
        this.currentIndex = index;

        setCurrentSelectedLabelText(streamPiComboBoxFactory.getOptionDisplayText(options.get(index)));
    }

    public void setCurrentSelectedItem(T object)
    {
        setCurrentSelectedItemIndex(options.indexOf(object));
    }

    private void setCurrentSelectedLabelText(String text)
    {
        currentSelectedLabel.setText(text);
    }

    private StreamPiComboBoxListener<T> streamPiComboBoxListener = null;

    public void setStreamPiComboBoxListener(StreamPiComboBoxListener<T> streamPiComboBoxListener)
    {
        this.streamPiComboBoxListener = streamPiComboBoxListener;
    }

    public void show()
    {
        stackPaneParent.getChildren().add(getPopupScrollPane());
        stackPaneParent.toFront();
        stackPaneParent.setVisible(true);
    }

    public void destroy()
    {
        stackPaneParent.getChildren().clear();
        stackPaneParent.toBack();
        stackPaneParent.setVisible(false);
    }

    private StreamPiComboBoxFactory<T> streamPiComboBoxFactory;
    public void setStreamPiComboBoxFactory(StreamPiComboBoxFactory<T> streamPiComboBoxFactory)
    {
        this.streamPiComboBoxFactory = streamPiComboBoxFactory;
    }
}