package pl.samouczekprogramisty.szs.floatingpoint;

import java.math.BigDecimal;

public class FloatingPoint {

    public enum Format {
        SINGLE_PRECISION,
        DOUBLE_PRECISION
    }

    public enum Sign {
        POSITIVE,
        NEGATIVE
    }

    private final Format format;

    private final Sign sign;
    private final String exponent;
    private final String fraction;

    private final BigDecimal realValue;
    private final BigDecimal ieee754Value;

    public static FloatingPoint instantiate(Format format, String decimalRepresentation) {
        switch (format) {
            case DOUBLE_PRECISION:
                return new FloatingPoint(
                    new BigDecimal(decimalRepresentation),
                    Double.parseDouble(decimalRepresentation)
                );
            default:
                return new FloatingPoint(
                    new BigDecimal(decimalRepresentation),
                    Float.parseFloat(decimalRepresentation)
                );
        }
    }

    private FloatingPoint(Format format, BigDecimal realValue, BigDecimal ieee754Value, String binaryRepresentation) {
        sign = Sign.values()[Integer.parseInt(binaryRepresentation.substring(0, 1))];
        this.format = format;
        this.realValue = realValue;
        this.ieee754Value = ieee754Value;

        switch (format) {
            case SINGLE_PRECISION:
                exponent = binaryRepresentation.substring(1, 1 + 8);
                fraction = binaryRepresentation.substring(1 + 8);
                break;
            default:
                exponent = binaryRepresentation.substring(1, 1 + 11);
                fraction = binaryRepresentation.substring(1 + 11);
                break;
        }
    }

    private FloatingPoint(BigDecimal realValue, float ieee754Value) {
        this(
                Format.SINGLE_PRECISION,
                realValue,
                new BigDecimal(ieee754Value),
                String.format(
                        "%32s",
                        Integer.toBinaryString(Float.floatToRawIntBits(ieee754Value))
                ).replace(' ', '0')
        );
    }

    private FloatingPoint(BigDecimal realValue, double ieee754Value) {
        this(
                Format.DOUBLE_PRECISION,
                realValue,
                new BigDecimal(ieee754Value),
                String.format(
                        "%64s",
                        Long.toBinaryString(Double.doubleToRawLongBits(ieee754Value))
                ).replace(' ', '0')
        );
    }

    public Format getFormat() {
        return format;
    }

    public Sign getSign() {
        return sign;
    }

    public String getExponent() {
        return exponent;
    }

    public String getFraction() {
        return fraction;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s", sign.ordinal(), exponent, fraction);
    }

    public BigDecimal getError() {
        return realValue.subtract(ieee754Value).abs();
    }
}
