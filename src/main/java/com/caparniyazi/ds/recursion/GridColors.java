package com.caparniyazi.ds.recursion;

import java.awt.*;

/**
 * An interface for colors.
 */
public interface GridColors {
    Color PATH = Color.GREEN;
    Color BACKGROUND = Color.WHITE;
    Color NON_BACKGROUND = Color.RED;   // Presence of abnormality.
    Color ABNORMAL = NON_BACKGROUND;
    Color TEMPORARY = Color.BLACK;
}
