/*
 * Stream-Pi - Free & Open-Source Modular Cross-Platform Programmable Macro Pad
 * Copyright (C) 2019-2021  Debayan Sutradhar (rnayabed),  Samuel Quiñones (SamuelQuinones)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 */

package com.stream_pi.util.uihelper;

import javafx.geometry.Orientation;

public class ActionGridRowsAndColsCalculator
{
    private int rows, cols;

    public ActionGridRowsAndColsCalculator(Orientation orientation, double actionSize, double actionGap, double stageWidth, double stageHeight)
    {
        double pre = actionSize + (actionGap * 4);

        rows = (int) (stageHeight/pre);
        cols = (int) (stageWidth/pre);

        if (orientation == Orientation.VERTICAL)
        {
            int tmp = rows;
            rows = cols;
            cols = tmp;
        }
    }

    public int getRows()
    {
        return rows;
    }

    public int getCols()
    {
        return cols;
    }
}
