package com.bkopellhill.gj.comisionlibre.core.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Locale;

/**
 * @author Luis Hernandez
 * @version 0.0.1
 */

public final class Calculate {

    private static Calculate calculate;

    private final double byCommission=0.12;
    private final double commission = 1.0-byCommission;

    private static double amout;
    private static double percentage;
    private static boolean high;

    public static Calculate with(String a){
        amout = Double.parseDouble(a);
        calculate = new Calculate();
        return calculate;
    }

    private static Calculate getCalculate() {
        return calculate;
    }

    public static Calculate with(String a, String p,boolean h){
        amout = Double.parseDouble(a);
        percentage = Double.parseDouble(p);
        high = h;
        calculate = new Calculate();
        return calculate;
    }

    public String getResultValue(){
        if (getCalculate() == null)
            throw new IllegalArgumentException("No init Class calculate");
        return String.format(Locale.GERMANY,"%1$,.2f", BigDecimal.valueOf(amout/commission).setScale(2, RoundingMode.HALF_UP).doubleValue());
    }

    public String getResultWithPercantageValue(){
        if (getCalculate() == null)
            throw new IllegalArgumentException("No init Class calculate");
        return String.format(Locale.GERMANY,"%1$,.2f", BigDecimal.valueOf(getValueWithPercentage()).setScale(2, RoundingMode.HALF_UP).doubleValue());
    }

    public String getResultComissionValue(){
        if (getCalculate() == null)
            throw new IllegalArgumentException("No init Class calculate");
        double resultTemp = getValueWithPercentage();
        return String.format(Locale.GERMANY,"%1$,.2f",BigDecimal.valueOf(resultTemp-(resultTemp*byCommission)).setScale(2,RoundingMode.HALF_UP).doubleValue());
    }

    public String getResultImportValue(){
        if (getCalculate() == null)
            throw new IllegalArgumentException("No init Class calculate");
        double resultTemp = getValueWithPercentage();
        return String.format(Locale.GERMANY,"%1$,.2f",BigDecimal.valueOf(resultTemp/commission).setScale(2,RoundingMode.HALF_UP).doubleValue());
    }

    private double getValueWithPercentage(){
        if (getCalculate() == null)
            throw new IllegalArgumentException("No init Class calculate");
        if (high){
            return amout+(amout*(percentage/100));
        } else {
            return amout-(amout*(percentage/100));
        }
    }
}
