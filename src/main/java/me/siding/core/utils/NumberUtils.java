package me.siding.core.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class NumberUtils {
    private static final DecimalFormat formatter = new DecimalFormat("#");

    private static final DecimalFormat df = new DecimalFormat("#,##0");

    public static String formatMoney(long balance) {
        if (balance >= 1.0E12D) {
            if (balance % 1.0E12D == 0.0D)
                return formatter.format(balance / 1.0E12D) + "Trillion";
            return df.format(balance) + "";
        }
        if (balance >= 1.0E9D) {
            if (balance % 1.0E9D == 0.0D)
                return formatter.format(balance / 1.0E9D) + "Billion";
            return df.format(balance) + "";
        }
        if (balance >= 1000000.0D) {
            if (balance % 1000000.0D == 0.0D)
                return formatter.format(balance / 1000000.0D) + "Million";
            return df.format(balance) + "";
        }
        if (balance > 1000L) {
            if (balance % 1000L == 0L)
                return formatter.format(balance / 1000.0D) + "k";
            return df.format(balance) + "";
        }
        return df.format(balance) + "";
    }

    private static String insertCommas(BigDecimal number) {
        DecimalFormat df = new DecimalFormat("#,##0");
        return df.format(number);
    }

    private static String insertCommas(String number) {
        try {
            return insertCommas(new BigDecimal(number));
        } catch (Exception err) {
            return number;
        }
    }
}
