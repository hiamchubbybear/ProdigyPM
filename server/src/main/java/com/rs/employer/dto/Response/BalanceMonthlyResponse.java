package com.rs.employer.dto.Response;

public class BalanceMonthlyResponse {
    private MonthData[] months;

    public BalanceMonthlyResponse() {
        this.months = new MonthData[12];
    }

    public MonthData[] getMonths() {
        return months;
    }

    public void setMonths(MonthData[] months) {
        this.months = months;
    }

    public static class MonthData {
        private String month;
        private double credit;
        private double debit;
        private double balance;

        public MonthData(String month, double credit, double debit, double balance) {
            this.month = month;
            this.credit = credit;
            this.debit = debit;
            this.balance = balance;
        }

        public String getMonth() {
            return month;
        }

        public void setMonth(String month) {
            this.month = month;
        }

        public double getCredit() {
            return credit;
        }

        public void setCredit(double credit) {
            this.credit = credit;
        }

        public double getDebit() {
            return debit;
        }

        public void setDebit(double debit) {
            this.debit = debit;
        }

        public double getBalance() {
            return balance;
        }

        public void setBalance(double balance) {
            this.balance = balance;
        }
    }
}