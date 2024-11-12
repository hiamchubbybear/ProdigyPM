package com.rs.employer.enums;

public enum AccountEnum {

        // Group 1: Current Assets
        CASH("111", "Cash"),
        BANK_DEPOSIT("112", "Bank Deposits"),
        MONEY_IN_TRANSIT("113", "Money in Transit"),
        INVESTMENT("121", "Investments Held to Maturity"),
        RECEIVABLE_CUSTOMERS("131", "Receivables from Customers"),
        RECEIVABLE_INTERNAL("132", "Receivables from Internal"),
        VAT_DEDUCTIBLE("133", "VAT Receivable"),
        ADVANCE("141", "Advances"),
        PREPAID_EXPENSES("142", "Short-term Prepaid Expenses"),
        GOODS_IN_TRANSIT("151", "Goods in Transit"),
        RAW_MATERIAL("152", "Raw Materials"),
        TOOLS("153", "Tools and Supplies"),
        IN_PROGRESS_COST("154", "Work-in-progress Costs"),
        MERCHANDISE("156", "Merchandise"),
        GOODS_SENT_FOR_SALE("157", "Goods Sent for Sale"),
        REAL_ESTATE("158", "Real Estate"),
        SHORT_TERM_LOANS("161", "Short-term Loans"),
        SHORT_TERM_PAYABLE("162", "Short-term Payables"),

        // Group 2: Non-Current Assets
        FIXED_ASSETS("211", "Tangible Fixed Assets"),
        INTANGIBLE_ASSETS("212", "Intangible Assets"),
        IN_PROGRESS_CONSTRUCTION("213", "Work in Progress"),
        LEASED_FIXED_ASSETS("214", "Leased Fixed Assets"),
        INVESTMENTS_IN_SUBSIDIARIES("221", "Investments in Subsidiaries"),
        INVESTMENTS_IN_ASSOCIATES("222", "Investments in Associates"),
        OTHER_LONG_TERM_INVESTMENTS("223", "Other Long-term Investments"),

        // Group 3: Equity
        EQUITY("311", "Equity"),
        CHARTER_CAPITAL("312", "Charter Capital"),
        OWNER_CONTRIBUTION("313", "Owner Contributions"),
        RETAINED_EARNINGS("314", "Retained Earnings"),
        BONUS_WELFARE_FUNDS("315", "Bonus and Welfare Funds"),

        // Group 4: Other Liabilities
        OWNER_EQUITY("411", "Owner's Equity"),
        FINANCIAL_RESERVES("412", "Financial Reserves"),
        BUSINESS_DEVELOPMENT_FUNDS("413", "Business Development Funds"),
        INVESTMENT_DEVELOPMENT_FUNDS("414", "Investment and Development Funds"),

        // Group 5: Revenue
        SALES_REVENUE("511", "Sales Revenue and Service Revenue"),
        MERCHANDISE_SALES("512", "Sales of Goods"),
        SERVICE_REVENUE("513", "Service Revenue"),
        ASSET_DISPOSAL_REVENUE("515", "Revenue from Asset Disposal"),
        OTHER_REVENUE("516", "Other Revenues"),
        FINANCIAL_REVENUE("517", "Financial Revenue"),

        // Group 6: Expenses
        COST_OF_GOODS_SOLD("611", "Cost of Goods Sold"),
        SELLING_EXPENSES("612", "Selling Expenses"),
        PRODUCTION_COST("621", "Production Costs"),
        ADMINISTRATIVE_EXPENSES("622", "Administrative Expenses"),
        OTHER_ADMIN_EXPENSES("623", "Other Administrative Expenses"),
        INTEREST_EXPENSES("625", "Interest Expenses"),
        ADMINISTRATIVE_FEES("627", "Administrative Fees"),
        LABOR_COST("631", "Labor Costs"),
        MATERIAL_COST("632", "Material Costs"),

        // Group 7: Profit
        OPERATING_PROFIT("711", "Operating Profit"),
        FINANCIAL_PROFIT("712", "Financial Profit"),
        OTHER_PROFIT("713", "Other Profit"),

        // Group 8: Taxes and Payables
        VAT_PAYABLE("811", "VAT Payable"),
        PERSONAL_INCOME_TAX_PAYABLE("812", "Personal Income Tax Payable"),
        CORPORATE_TAX_PAYABLE("813", "Corporate Income Tax Payable"),

        // Group 9: Receivables and Payables
        ACCOUNTS_RECEIVABLE("911", "Accounts Receivable"),
        ACCOUNTS_PAYABLE("912", "Accounts Payable");

        private final String code;
        private final String description;

        AccountEnum(String code, String description) {
            this.code = code;
            this.description = description;
        }

        public String getCode() {
            return code;
        }

        public String getDescription() {
            return description;
        }

        @Override
        public String toString() {
            return this.code + ": " + this.description;
        }


}
