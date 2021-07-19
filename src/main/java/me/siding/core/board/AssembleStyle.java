package me.siding.core.board;

import lombok.Getter;

@Getter
public enum AssembleStyle {

    KOHI(true, 15),
    VIPER(true, -1),
    MODERN(false, 1);

    private final boolean decending;
    private final int startNumber;

    AssembleStyle(boolean decending, int startNumber) {
        this.decending = decending;
        this.startNumber = startNumber;
    }
}
