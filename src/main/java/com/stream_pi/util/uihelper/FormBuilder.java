package com.stream_pi.util.uihelper;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class FormBuilder extends GridPane
{
    private double maxSecondColumnWidth = 350;
    private int currentRow = 0;

    private Pos labelAlignment = Pos.TOP_LEFT;

    private Pos secondColumnHBoxAlignment = Pos.TOP_RIGHT;
    private double secondColumnHBoxSpacing = 5;

    public FormBuilder()
    {
        ColumnConstraints col0Constraints = new ColumnConstraints();
        col0Constraints.setHgrow(Priority.ALWAYS);
        col0Constraints.setHalignment(HPos.LEFT);

        ColumnConstraints col1Constraints = new ColumnConstraints();
        col1Constraints.setHgrow(Priority.ALWAYS);
        col1Constraints.setMaxWidth(maxSecondColumnWidth);
        col1Constraints.setHalignment(HPos.RIGHT);

        getColumnConstraints().addAll(col0Constraints, col1Constraints);
    }

    public FormBuilder addRow(String labelText, Node... nodes)
    {
        return addRow(new Label(labelText), nodes);
    }

    public FormBuilder addRow(Label label, Node... nodes)
    {
        label.setMaxHeight(Double.MAX_VALUE);
        label.setMaxWidth(Double.MAX_VALUE);
        label.setAlignment(labelAlignment);

        add(label, 0, currentRow);

        if (nodes.length == 1)
        {
            add(nodes[0], 1, currentRow);
        }
        else if (nodes.length > 1)
        {
            HBox hBox = new HBox();
            hBox.setAlignment(secondColumnHBoxAlignment);
            hBox.setSpacing(secondColumnHBoxSpacing);
            hBox.getChildren().addAll(nodes);
            add(hBox, 1, currentRow);
        }

        currentRow++;
        return this;
    }

    public void setMaxSecondColumnWidth(double maxSecondColumnWidth) {
        this.maxSecondColumnWidth = maxSecondColumnWidth;
    }

    public double getMaxSecondColumnWidth() {
        return maxSecondColumnWidth;
    }

    public int getCurrentRow() {
        return currentRow;
    }

    public void setLabelAlignment(Pos labelAlignment) {
        this.labelAlignment = labelAlignment;
    }

    public Pos getLabelAlignment() {
        return labelAlignment;
    }

    public void setSecondColumnHBoxAlignment(Pos secondColumnHBoxAlignment) {
        this.secondColumnHBoxAlignment = secondColumnHBoxAlignment;
    }

    public Pos getSecondColumnHBoxAlignment() {
        return secondColumnHBoxAlignment;
    }

    public void setSecondColumnHBoxSpacing(double secondColumnHBoxSpacing) {
        this.secondColumnHBoxSpacing = secondColumnHBoxSpacing;
    }

    public double getSecondColumnHBoxSpacing() {
        return secondColumnHBoxSpacing;
    }
}
