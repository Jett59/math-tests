package app.cleancode;

import java.util.BitSet;

public class VarNums {
    public static long toLong(BitSet bits) {
        long result = 0;
        for (int i = 0; i < bits.size(); i++) {
            if (bits.get(i)) {
                result |= 1l << i;
            }
        }
        return result;
    }

    public static BitSet fromLong(long l) {
        BitSet result = new BitSet(Long.SIZE);
        for (int i = 0; i < Long.SIZE; i++) {
            if ((l & (1l << i)) != 0) {
                result.set(i);
            }
        }
        return result;
    }

    private static char[] numberTable = new char[] {'0', '1', '2', '3', '4', '5', '6', '7', '8',
            '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static String toString(BitSet bits, int regex) {
        String result = "";
        BitSet bitsClone = (BitSet) bits.clone();
        long[] regexArray = new long[] {regex};
        BitSet regexBits = BitSet.valueOf(regexArray);
        do {
            div(regexBits, bitsClone);
            result += numberTable[(int) toLong(bitsClone)];
            bitsClone.clear();
            for (int i = 0; i < regexBits.length(); i++) {
                bitsClone.set(i, regexBits.get(i));
            }
            regexBits = BitSet.valueOf(regexArray);
        } while (!bitsClone.isEmpty());
        return new StringBuilder(result).reverse().toString();
    }

    public static String toString(BitSet bits) {
        return toString(bits, 10);
    }

    public static void add(BitSet src, BitSet dst) {
        if (src.size() != dst.size()) {
            throw new IllegalArgumentException("Differing sizes for parameters to add");
        }
        boolean carry = false;
        for (int i = 0; i < src.size(); i++) {
            boolean srcBit = src.get(i);
            boolean dstBit = dst.get(i);
            dst.set(i, srcBit ^ dstBit ^ carry);
            if ((srcBit && dstBit) || ((srcBit ^ dstBit) && carry)) {
                carry = true;
            } else {
                carry = false;
            }
        }
    }

    /*
     * Subtract src from dst
     */
    public static void sub(BitSet src, BitSet dst) {
        dst.flip(0, dst.size());
        add(src, dst);
        dst.flip(0, dst.size());
    }

    public static void mul(BitSet multiplier, BitSet dst) {
        BitSet origDst = (BitSet) dst.clone();
        BitSet one = new BitSet(multiplier.size());
        one.set(0);
        while (!multiplier.isEmpty()) {
            sub(one, multiplier);
            add(origDst, dst);
        }
    }

    /*
     * Divide two bit sets. The result of the division is stored in divisor. The remainder of the
     * division is stored in dividend.
     */
    public static void div(BitSet divisor, BitSet dividend) {
        boolean origSign = dividend.get(dividend.size() - 1);
        BitSet result = new BitSet(dividend.size());
        BitSet one = new BitSet(result.size());
        one.set(0);
        while (dividend.get(dividend.size() - 1) == origSign) {
            add(one, result);
            sub(divisor, dividend);
        }
        sub(one, result);
        add(divisor, dividend);
        for (int i = 0; i < result.size(); i++) {
            divisor.set(i, result.get(i));
        }
    }

    public static void main(String[] args) {
        BitSet a = fromLong(100000000000l);
        a = new BitSet(64);
        a.set(24);
        BitSet b = fromLong(899);
        System.out.printf("%s + %s = ", toString(a), toString(b));
        add(a, b);
        System.out.println(toString(b));
    }
}
