package com.github.doghere.algorithm.base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author ranxianglei <ranxianglei@gmail.com>
 * Created on 2021-05-02
 * <p>
 * 编程语言是人和机器的媒介，机器怎么读都能懂，人怎么读都不懂。
 */
public class Task3 implements Task {

    private static final int PART = 2;


    @Override
    public List<Index> executeAsIndex(Row[] rows) {
        Task2 task2 = new Task2();
        return task2.executeAsIndex(rows);
    }

    /**
     * 调用task2,对返回结果进行快速排序或者多路归并
     */
    @Override
    public Row[] execute(Row[] rows) {
        List<Index> indexList = executeAsIndex(rows);
        if (indexList.size() <= 1) {
            return fetchMatchedRow(rows, indexList);
        }
        // a列分组过少，b列直接快速排序
        if (rows.length / indexList.size() < PART) {
            Row[] matched = fetchMatchedRow(rows, indexList);
            Arrays.sort(matched, Comparator.comparingInt(Row::getB));
            return matched;
        }

        // 否则多路归并
        return merge(rows, indexList);
    }


    private Row[] merge(Row[] rows, List<Index> indexList) {
        Row[] result = new Row[indexLength(indexList)];

        int[] searchIndex = new int[indexList.size()];
        List<Row> minRows = new ArrayList<>(indexList.size());

        for (int j = 0; j < result.length; ) {
            for (int i = 0; i < indexList.size(); i++) {
                Index index = indexList.get(i);
                if (searchIndex[i] < index.length()) {
                    Row row = rows[index.getStart() + searchIndex[i]];
                    minRows.add(row);
                    searchIndex[i]++;
                }
            }
            minRows.sort(Comparator.comparingInt(Row::getB));
            for (Row minRow : minRows) {
                result[j++] = minRow;
            }
            minRows.clear();
        }
        return result;
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
                new Row(2000, 33)
        };

        Task3 task2 = new Task3();
        rows = task2.execute(rows);
        task2.print(rows);
    }


}
