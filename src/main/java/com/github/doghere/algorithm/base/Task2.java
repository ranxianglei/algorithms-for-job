package com.github.doghere.algorithm.base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ranxianglei <ranxianglei@gmail.com>
 * Created on 2021-05-02
 * <p>
 * 编程语言是人和机器的媒介，机器怎么读都能懂，人怎么读都不懂。
 */
public class Task2 implements Task {

    /**
     * 由于知道rows已经按照(a,b)排序，所以可以进行二分查找，时间复杂度O(log(n))。
     * 实现含有重复元素的已排序数组的二分查找。对a列进行二分查找后，锁定命中行数，再对b继续进行二分查找。
     *
     * @param rows 行数据
     * @return 命中的行数据
     */
    @Override
    public List<Index> executeAsIndex(Row[] rows) {
        List<Index> indexList = new ArrayList<>();

        for (int thisV : orCondition()) {
            Index index = BinarySearch.search(rows, target
                    -> Integer.compare(thisV, target.getA()));
            if (index != null) {
                Index index2 = BinarySearch.search(rows, (k)
                        -> rangeCondition(k.getB()), index.getStart(), index.getStop());
                if (index2 != null) {
                    indexList.add(index2);
                }
            }
        }
        return indexList;
    }


    public static void main(String[] ar) {
        Row[] rows = new Row[] {
                new Row(1000, 10),
                new Row(1000, 11),
                new Row(1000, 61),
                new Row(1000, 72),

                new Row(2000, 10),
                new Row(2000, 12),
                new Row(2000, 22),
                new Row(2000, 33),

                new Row(3000, 10),
                new Row(3000, 12),
                new Row(3000, 90),
        };

        Task2 task2 = new Task2();
        rows = task2.execute(rows);
        task2.print(rows);

    }


    private static List<Integer> orCondition() {
        return Arrays.asList(1000, 2000, 3000);
    }

    private static int rangeCondition(int col) {
        if (col >= 10 && col <= 50) {
            return 0;
        } else if (col < 10) {
            return 1;
        } else {
            return -1;
        }
    }
}
