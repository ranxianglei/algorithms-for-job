package com.github.doghere.algorithm.base;

import java.util.List;


/**
 * @author ranxianglei <ranxianglei@gmail.com>
 * Created on 2021-05-02
 * <p>
 * 编程语言是人和机器的媒介，机器怎么读都能懂，人怎么读都不懂。
 */
public interface Task {
    default Row[] execute(Row[] rows) {
        List<Index> indexList = executeAsIndex(rows);
        return fetchMatchedRow(rows, indexList);
    }

    List<Index> executeAsIndex(Row[] rows);

    default int indexLength(List<Index> indexList) {
        int len = 0;
        for (Index index : indexList) {
            len += (index.getStop() - index.getStart() + 1);
        }
        return len;
    }

    default Row[] fetchMatchedRow(Row[] rows, List<Index> indexList) {

        int len = indexLength(indexList);
        Row[] matched = new Row[len];
        int j = 0;
        for (Index index : indexList) {
            for (int i = index.getStart(); i <= index.getStop(); i++) {
                matched[j++] = rows[i];
            }
        }
        return matched;
    }

    default void print(Row[] rows) {
        for (Row row : rows) {
            System.out.println(row);
        }
    }
}
