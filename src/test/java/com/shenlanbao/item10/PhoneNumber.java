package com.shenlanbao.item10;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PhoneNumber implements Cloneable{
    private final short areaCode, prefix, lineNum;
    private int hashCode;

    public PhoneNumber(int areaCode, int prefix, int lineNum) {
        this.areaCode = rangeCheck(areaCode, 999, "area code");
        this.prefix = rangeCheck(prefix, 999, "prefix");
        this.lineNum = rangeCheck(lineNum, 9999, "line num");
    }

    private static short rangeCheck(int val, int max, String arg) {
        if (val < 0 || val > max) {
            throw new IllegalArgumentException(arg + ": " + val);
        }
        return ((short) val);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof PhoneNumber)) {
            return false;
        }

        PhoneNumber phoneNumber = (PhoneNumber) obj;
        return phoneNumber.lineNum == lineNum
                && phoneNumber.prefix == prefix
                && phoneNumber.areaCode == areaCode;
    }

/*    @Override
    public int hashCode() {
        int result = Short.hashCode(areaCode);
        result = 31 * result + Short.hashCode(prefix);
        result = 31 * result + Short.hashCode(lineNum);
        return result;
    }*/

/*    @Override
    public int hashCode() {
        return Objects.hash(lineNum, prefix, areaCode);
    }*/

    @Override
    public int hashCode() {
        int result = hashCode;

        if (result == 0) {
            result = Short.hashCode(areaCode);
            result = 31 * result + Short.hashCode(prefix);
            result = 31 * result + Short.hashCode(lineNum);
            hashCode = result;
        }
        return result;
    }

    /**
     * Returns the string representation of this phone number.
     * The string consists of twelve characters whose format is
     * "XXX-YYY-ZZZZ", where XXX is the area code, YYY is the
     * prefix, and ZZZZ is the line number. Each of the capital
     * letters represents a single decimal digit.
     **
     If any of the three parts of this phone number is too small
     * to fill up its field, the field is padded with leading zeros.
     * For example, if the value of the line number is 123, the last
     * four characters of the string representation will be "0123".
     */
    @Override
    public String toString() {
        return String.format("%03d-%03d-%04d", areaCode, prefix, lineNum);
    }

    @Override
    protected PhoneNumber clone() throws CloneNotSupportedException {
//        return new PhoneNumber(areaCode, prefix, lineNum);
        return (PhoneNumber) super.clone();
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        Map<PhoneNumber, String> map = new HashMap<>();
        PhoneNumber key = new PhoneNumber(707, 867, 5309);
        map.put(key, "Jenny");
        PhoneNumber phoneNumber = new PhoneNumber(707, 867, 5309);
        System.out.println(key.equals(phoneNumber));
        System.out.println(map.get(key));
        System.out.println(map.get(phoneNumber));

        PhoneNumber clone = phoneNumber.clone();
        System.out.println(clone);

        System.out.println(clone != phoneNumber);
        System.out.println(clone.getClass() == phoneNumber.getClass());
        System.out.println(clone.equals(phoneNumber));
    }
}

