package com.github.doghere.algorithm.base;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ranxianglei <ranxianglei@gmail.com>
 * Created on 2021-05-02
 * <p>
 * 编程语言是人和机器的媒介，机器怎么读都能懂，人怎么读都不懂。
 */
public class Task1 implements Task {

    /**
     * 顺序查找，时间复杂度O(n)。由于不知道关于rows的任何信息，顺序查找代价最小。
     *
     * @param rows 行数据
     * @return 命中的行数据
     */
    @Override
    public List<Index> executeAsIndex(Row[] rows) {
        List<Index> indexList = new ArrayList<>();
        int startIndex = -1;
        int stopIndex = -1;
        for (int i = 0; i < rows.length; i++) {
            Row row = rows[i];
            if (matchColumnA(row.getA()) && matchColumnB(row.getB())) {
                if (startIndex == -1) {
                    startIndex = i;
                    stopIndex = i;
                } else {
                    if (i - 1 == stopIndex) {
                        stopIndex = i;
                    }
                }
            }

            if (startIndex != -1 && stopIndex != i) {
                indexList.add(new Index(startIndex, stopIndex));
                startIndex = -1;
                stopIndex = -1;
            }
        }

        if (startIndex != -1) {
            indexList.add(new Index(startIndex, stopIndex));
        }
        return indexList;
    }


    private static boolean matchColumnA(int col) {
        return col == 1000 ||
                col == 2000 ||
                col == 3000;
    }

    private static boolean matchColumnB(int col) {
        return col >= 10 && col <= 50;
    }

    public static void main(String[] args) {
        Row[] rows = new Row[] {
                new Row(1000, 20),
                new Row(1000, 23),
                new Row(2000, 16),
                new Row(4000, 16),
                new Row(3000, 60)
        };

        Task1 task1 = new Task1();
        rows = task1.execute(rows);
        task1.print(rows);
    }

}
