package com.xiaozhi.common.generate.bean;

import com.xiaozhi.common.generate.service.GenerateUtile;

import java.util.List;

public class GenerateTable {
    private String tableName;
    private String tableCommont;

    private String beanName;
    private String mapperName;
    private String serviceName;
    private String actionName;
    private List<GenerateColumn> columns;
    private GenerateColumn proColumn;
    private GenerateColumn orgIdColumn;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableCommont() {
        return tableCommont;
    }

    public void setTableCommont(String tableCommont) {
        this.tableCommont = tableCommont;
    }

    public String getBeanName() {
        this.beanName = GenerateUtile.formatName(tableName);
        beanName = beanName.replaceFirst(beanName.charAt(0) + "", Character.toUpperCase(beanName.charAt(0)) + "");
        return beanName;
    }

    public List<GenerateColumn> getColumns() {
        return columns;
    }

    public void setColumns(List<GenerateColumn> columns) {
        this.columns = columns;

        if (proColumn == null && columns != null) {
            int i = 0;
            for (; i < columns.size(); i++) {
                GenerateColumn column = columns.get(i);
                if ("PRI".equals(column.getColumnKey())) {
                    proColumn = column;
                    break;
                }
            }
        }

        if (orgIdColumn == null && columns != null) {
            int i = 0;
            for (; i < columns.size(); i++) {
                GenerateColumn column = columns.get(i);
                if ("orgId".equals(column.getFieldName())) {
                    orgIdColumn = column;
                    break;
                }
            }
        }
    }

    public GenerateColumn getProColumn() {
        return proColumn;
    }

    public GenerateColumn getOrgIdColumn() {
        return orgIdColumn;
    }

    public String getMapperName() {
        return this.getBeanName() + "Mapper";
    }

    public String getServiceName() {
        return this.getBeanName() + "Service";
    }

    public String getActionName() {
        return this.getBeanName() + "Action";
    }

}
