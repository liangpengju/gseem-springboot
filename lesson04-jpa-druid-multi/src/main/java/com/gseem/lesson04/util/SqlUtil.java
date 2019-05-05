package com.gseem.lesson04.util;

import org.springframework.util.StringUtils;

import java.util.List;

public class SqlUtil {

    private static final String COMMA = ",";

    public static StringBuilder appendCommaCds(StringBuilder sql, String field, String commaCds) {
        if (StringUtils.isEmpty(commaCds)) {
            return sql;
        }
        if (commaCds.contains(COMMA)) {
            String temp = StringUtils.replace(commaCds, COMMA, "'" + COMMA + "'");
            sql.append(" AND ").append(field).append(" IN ('").append(temp).append("')");
        } else {
            sql.append(" AND ").append(field).append(" = '").append(commaCds).append("'");
        }
        return sql;
    }

    public static StringBuilder appendList(StringBuilder sql, String field, List<String> cds) {
        if (StringUtils.isEmpty(cds)) {
            return sql;
        }
        if (cds.size() == 1) {
            sql.append(" AND ").append(field).append(" = '").append(cds.get(0)).append("'");
        } else {
            sql.append(" AND ").append(field).append(" IN (");
            for (String cd : cds) {
                sql.append(" '").append(cd.trim()).append("',");
            }
            sql.deleteCharAt(sql.lastIndexOf(COMMA)).append(")");
        }
        return sql;
    }

}
