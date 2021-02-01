package com.streampi.util.combobox;

public abstract class StreamPiComboBoxFactory<T>
{
    public abstract String getOptionDisplayText(T object);
}