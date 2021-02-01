package com.stream_pi.util.combobox;

public abstract class StreamPiComboBoxFactory<T>
{
    public abstract String getOptionDisplayText(T object);
}