package bf.io.openshop.views;

import java.math.*;

private enum NumberType
{
    BIG_DECIMAL, 
    BYTE, 
    DOUBLE, 
    FLOAT, 
    INTEGER, 
    LONG, 
    SHORT;
    
    public static <E extends Number> NumberType fromNumber(final E e) throws IllegalArgumentException {
        if (e instanceof Long) {
            return NumberType.LONG;
        }
        if (e instanceof Double) {
            return NumberType.DOUBLE;
        }
        if (e instanceof Integer) {
            return NumberType.INTEGER;
        }
        if (e instanceof Float) {
            return NumberType.FLOAT;
        }
        if (e instanceof Short) {
            return NumberType.SHORT;
        }
        if (e instanceof Byte) {
            return NumberType.BYTE;
        }
        if (e instanceof BigDecimal) {
            return NumberType.BIG_DECIMAL;
        }
        throw new IllegalArgumentException("Number class '" + e.getClass().getName() + "' is not supported");
    }
    
    public Number toNumber(final double n) {
        switch (this) {
            default: {
                throw new InstantiationError("can't convert " + this + " to a Number object");
            }
            case LONG: {
                return (long)n;
            }
            case DOUBLE: {
                return n;
            }
            case INTEGER: {
                return (int)n;
            }
            case FLOAT: {
                return (float)n;
            }
            case SHORT: {
                return (short)n;
            }
            case BYTE: {
                return (byte)n;
            }
            case BIG_DECIMAL: {
                return BigDecimal.valueOf(n);
            }
        }
    }
}
