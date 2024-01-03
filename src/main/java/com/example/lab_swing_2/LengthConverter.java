package com.example.lab_swing_2;

public class LengthConverter {

    private static final float KM_TO_MI = 0.6214F;
    private static final float MI_TO_KM = 1.609F;
    // коефіцієнти перетворення
    private static final int COEF_MI = 1;
    private static final int COEF_YD = 1760;
    private static final int COEF_FT = 5280;
    private static final int COEF_IN = 63360;
    private static final int COEF_BLT = 44;


    private Double _num;
    private String _valueCI, _valueUS;
    LengthConverter(Double num, String valueCI, String valueUS) {
        _num = num;
        _valueCI = valueCI;
        _valueUS = valueUS;
    }

    public Double get_num() {
        return _num;
    }
    public void set_num(Double num) {
        this._num = num;
    }
    public String get_valueCI() {
        return _valueCI;
    }
    public void set_valueCI(String valueCI) {
        this._valueCI = valueCI;
    }
    public String get_valueUS() {
        return _valueUS;
    }

    public void set_valueUS(String _valueUS) {
        this._valueUS = _valueUS;
    }



    public double convertToCI() {
        double result = convertSwitchCI() * _num * MI_TO_KM / convertSwitchUS();
        return Math.round(result * 1000000.0) / 1000000.0;
    }
    public double convertToUS() {
        double convertedValue = _num * KM_TO_MI * convertSwitchUS() / convertSwitchCI();
        return Math.round(convertedValue * 100000.0) / 100000.0;
    }
    public double convertSwitchUS() {
        int temp = switch (_valueUS) {
            case "миля(mi)" -> COEF_MI;
            case "ядр(yd)" ->  COEF_YD;
            case "фут(ft)" -> COEF_FT;
            case "дюйм(in)" -> COEF_IN;
            case "болт(blt)" -> COEF_BLT;
            default -> 0;
        };
        return temp;
    }

    public double convertSwitchCI() {
        int temp = switch (_valueCI) {
            case "кілометр(км)" -> 1;
            case "метр(м)" -> 1000;
            case "дециметр(дм)" -> 10000;
            case "сантиметр(см)" -> 100000;
            case "міліметр(мм)" -> 1000000;
            default -> 0;
        };
        return temp;
    }

}
