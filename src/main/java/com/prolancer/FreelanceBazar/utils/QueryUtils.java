package com.prolancer.FreelanceBazar.utils;

import com.prolancer.FreelanceBazar.filter.PageFilter;
import org.springframework.data.domain.Sort;

public class QueryUtils {


    public static void append(boolean sorted, PageFilter filter, StringBuilder sql) {
        sql.append(" order by ");
        if (sorted) {
            for (Sort.Order order : filter.formPageable().getSort()) {
                sql.append("t.").append(order.getProperty());
                if (order.isDescending()) {
                    sql.append(" desc");
                }
                sql.append(",");
            }
            sql.deleteCharAt(sql.length() - 1);
        }else {
            sql.append(" t.id");
        }
    }


}
