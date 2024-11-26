package com.rs.employer.enums;

public enum AccountEnum {
        // Current Assets
        CASH("111", "Cash", "Current Assets"),
        BANK_DEPOSIT("112", "Bank Deposits", "Current Assets"),
        MONEY_IN_TRANSIT("113", "Money in Transit", "Current Assets"),
        INVESTMENT("121", "Investments Held to Maturity", "Current Assets"),
        RECEIVABLE_CUSTOMERS("131", "Receivables from Customers", "Current Assets"),
        VAT_DEDUCTIBLE("133", "VAT Receivable", "Current Assets"),
        ADVANCE("141", "Advances", "Current Assets"),
        PREPAID_EXPENSES("142", "Short-term Prepaid Expenses", "Current Assets"),
        GOODS_IN_TRANSIT("151", "Goods in Transit", "Current Assets"),
        RAW_MATERIAL("152", "Raw Materials", "Current Assets"),
        TOOLS("153", "Tools and Supplies", "Current Assets"),
        IN_PROGRESS_COST("154", "Work-in-progress Costs", "Current Assets"),
        MERCHANDISE("156", "Merchandise", "Current Assets"),
        GOODS_SENT_FOR_SALE("157", "Goods Sent for Sale", "Current Assets"),
        REAL_ESTATE("158", "Real Estate", "Current Assets"),
        SHORT_TERM_LOANS("161", "Short-term Loans", "Current Assets"),
        SHORT_TERM_PAYABLE("162", "Short-term Payables", "Current Assets"),

        // Non-Current Assets
        FIXED_ASSETS("211", "Tangible Fixed Assets", "Non-Current Assets"),
        INTANGIBLE_ASSETS("212", "Intangible Assets", "Non-Current Assets"),
        IN_PROGRESS_CONSTRUCTION("213", "Work in Progress", "Non-Current Assets"),
        LEASED_FIXED_ASSETS("214", "Leased Fixed Assets", "Non-Current Assets"),
        INVESTMENTS_IN_SUBSIDIARIES("221", "Investments in Subsidiaries", "Non-Current Assets"),
        INVESTMENTS_IN_ASSOCIATES("222", "Investments in Associates", "Non-Current Assets"),
        OTHER_LONG_TERM_INVESTMENTS("223", "Other Long-term Investments", "Non-Current Assets"),

        // Equity
        EQUITY("311", "Equity", "Owner's Equity"),
        CHARTER_CAPITAL("312", "Charter Capital", "Owner's Equity"),
        OWNER_CONTRIBUTION("313", "Owner Contributions", "Owner's Equity"),
        RETAINED_EARNINGS("314", "Retained Earnings", "Owner's Equity"),
        BONUS_WELFARE_FUNDS("315", "Bonus and Welfare Funds", "Owner's Equity"),

        // Other Liabilities
        OWNER_EQUITY("411", "Owner's Equity", "Liabilities"),
        FINANCIAL_RESERVES("412", "Financial Reserves", "Liabilities"),
        BUSINESS_DEVELOPMENT_FUNDS("413", "Business Development Funds", "Liabilities"),
        INVESTMENT_DEVELOPMENT_FUNDS("414", "Investment and Development Funds", "Liabilities"),

        // Revenue
        SALES_REVENUE("511", "Sales Revenue and Service Revenue", "Revenue"),
        MERCHANDISE_SALES("512", "Sales of Goods", "Revenue"),
        SERVICE_REVENUE("513", "Service Revenue", "Revenue"),
        ASSET_DISPOSAL_REVENUE("515", "Revenue from Asset Disposal", "Revenue"),
        OTHER_REVENUE("516", "Other Revenues", "Revenue"),
        FINANCIAL_REVENUE("517", "Financial Revenue", "Revenue"),

        // Expenses
        COST_OF_GOODS_SOLD("611", "Cost of Goods Sold", "Expenses"),
        SELLING_EXPENSES("612", "Selling Expenses", "Expenses"),
        PRODUCTION_COST("621", "Production Costs", "Expenses"),
        ADMINISTRATIVE_EXPENSES("622", "Administrative Expenses", "Expenses"),
        OTHER_ADMIN_EXPENSES("623", "Other Administrative Expenses", "Expenses"),
        INTEREST_EXPENSES("625", "Interest Expenses", "Expenses"),
        ADMINISTRATIVE_FEES("627", "Administrative Fees", "Expenses"),
        LABOR_COST("631", "Labor Costs", "Expenses"),
        MATERIAL_COST("632", "Material Costs", "Expenses"),

        // Profit
        OPERATING_PROFIT("711", "Operating Profit", "Profit"),
        FINANCIAL_PROFIT("712", "Financial Profit", "Profit"),
        OTHER_PROFIT("713", "Other Profit", "Profit");

        // Taxes and Payables
//        VAT_PAYABLE("811", "VAT Payable", "Payables"),
//        PERSONAL_INCOME_TAX_PAYABLE("812", "Personal Income Tax Payable", "Payables"),
//        CORPORATE_TAX_PAYABLE("813", "Corporate Income Tax Payable", "Payables"),
//
//        // Receivables and Payables
//        ACCOUNTS_RECEIVABLE("911", "Accounts Receivable", "Receivables"),
//        ACCOUNTS_PAYABLE("912", "Accounts Payable", "Payables");


        private final String code;
        private final String description;
        private final String parentCode;

        AccountEnum(String code, String description, String parentCode) {
                this.code = code;
                this.description = description;
                this.parentCode = parentCode;
        }

        public String getCode() {
                return code;
        }

        public String getDescription() {
                return description;
        }

        public String getParentCode() {
                return parentCode;
        }
}
