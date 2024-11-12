package com.rs.employer.enums;

public enum AccountingParrentEnum {

        // Group: Assets
        CURRENT_ASSETS("Current Assets", "Assets"), // Tài sản ngắn hạn
        NON_CURRENT_ASSETS("Non-Current Assets", "Assets"), // Tài sản dài hạn

        // Group: Liabilities
        CURRENT_LIABILITIES("Current Liabilities", "Liabilities"), // Nợ ngắn hạn
        NON_CURRENT_LIABILITIES("Non-Current Liabilities", "Liabilities"), // Nợ dài hạn

        // Group: Equity
        EQUITY("Owner's Equity", "Equity"), // Vốn chủ sở hữu

        // Group: Revenue
        REVENUE("Revenue", "Revenue"), // Doanh thu

        // Group: Expenses
        EXPENSES("Expenses", "Expenses"); // Chi phí

        private final String description;
        private final String group;

        // Constructor
        AccountingParrentEnum(String description, String group) {
                this.description = description;
                this.group = group;
        }

        // Getter for description
        public String getDescription() {
                return description;
        }
        public String getGroup() {
                return group;
        }

}
