package com.basket.statistics.dialect;

public class MariaDBDialect extends org.hibernate.dialect.MariaDBDialect {
        @Override
        public String getTableTypeString() {
            return super.getTableTypeString() + " ROW_FORMAT=DYNAMIC";
        }



}
