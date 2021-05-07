package com.github.doghere.algorithm.base;

/**
 * @author ranxianglei <ranxianglei@gmail.com>
 * Created on 2021-05-02
 * <p>
 * 编程语言是人和机器的媒介，机器怎么读都能懂，人怎么读都不懂。
 */
public class BinarySearch {


    public static <Key> Index search(Key[] array,
            CompareKey<Key> compareKey) {
        return search(array, compareKey, 0, array.length - 1);
    }

    public static <Key> Index search(Key[] array,
            CompareKey<Key> compareKey, int lo, int hi) {
        Range fd = searchTarget(array, compareKey, lo, hi);
        if (fd.mid < 0) {
            return null;
        }
        int fl = searchFloor(array, compareKey, fd.lo, fd.mid);
        int ci = searchCeil(array, compareKey, fd.mid, fd.hi);
        return new Index(fl, ci);
    }


    private static <Key> Range searchTarget(Key[] array
            , CompareKey<Key> compareKey, int lo, int hi) {
        if (array == null) {
            return new Range();
        }
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = compareKey.compareTo(array[mid]);
            if (cmp > 0) {
                lo = mid + 1;
            } else if (cmp < 0) {
                hi = mid - 1;
            } else {
                return new Range(lo, hi, mid);
            }
        }
        return new Range();
    }


    private static <Key> int searchFloor(Key[] array, CompareKey<Key> compareKey,
            int originLo,
            int originHi) {
        if (array == null) {
            return -1;
        }
        int lo = originLo, hi = originHi;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = compareKey.compareTo(array[mid]);
            if (cmp <= 0) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        if (lo <= originHi && compareKey.compareTo(array[lo]) == 0) {
            return lo;
        }
        return -1;
    }

    private static <Key> int searchCeil(Key[] array, CompareKey<Key> compareKey,
            int originLo,
            int originHi) {
        if (array == null) {
            return -1;
        }
        int lo = originLo, hi = originHi;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = compareKey.compareTo(array[mid]);
            if (cmp >= 0) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        if (hi >= originLo && compareKey.compareTo(array[hi]) == 0) {
            return hi;
        }
        return -1;
    }


    private static class Range {
        private int lo;
        private int hi;
        private int mid;

        Range() {
            this.lo = -1;
            this.hi = -1;
            this.mid = -1;
        }

        Range(int lo, int hi, int mid) {
            this.lo = lo;
            this.hi = hi;
            this.mid = mid;
        }

        @Override
        public String toString() {
            return "Range{" +
                    "lo=" + lo +
                    ", hi=" + hi +
                    ", mid=" + mid +
                    '}';
        }
    }


}
