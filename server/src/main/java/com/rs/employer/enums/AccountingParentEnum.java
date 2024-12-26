package com.rs.employer.enums;

public enum AccountingParentEnum {
        ASSETS("Assets", "Tài sản"),
        CURRENT_ASSETS("Current Assets", "Tài sản ngắn hạn"),
        NON_CURRENT_ASSETS("Non-Current Assets", "Tài sản dài hạn"),

        LIABILITIES("Liabilities", "Nợ phải trả"),
        CURRENT_LIABILITIES("Current Liabilities", "Nợ ngắn hạn"),
        NON_CURRENT_LIABILITIES("Non-Current Liabilities", "Nợ dài hạn"),


        EQUITY("Equity", "Vốn chủ sở hữu"),


        REVENUE("Revenue", "Doanh thu"),


        EXPENSES("Expenses", "Chi phí"),


        PROFIT("Profit", "Lợi nhuận"),


        OWNER_EQUITY("Owner's Equity", "Vốn chủ sở hữu");

        private final String code;
        private final String description;

        AccountingParentEnum(String code, String description) {
                this.code = code;
                this.description = description;
        }

        public String getCode() {
                return code;
        }

        public String getDescription() {
                return description;
        }
}
