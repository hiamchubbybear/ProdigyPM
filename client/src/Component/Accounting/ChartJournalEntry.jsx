import {
  CategoryScale,
  Chart as ChartJS,
  Legend,
  LinearScale,
  LineElement,
  PointElement,
  Title,
  Tooltip,
} from "chart.js";
import React from "react";
import { Line } from "react-chartjs-2";
import "../../Assets/css/ChartJournalEntry.css";
// Đăng ký các thành phần cần thiết của Chart.js
ChartJS.register(
  CategoryScale, // Scale category cho trục X
  LinearScale, // Scale cho trục Y
  PointElement,
  LineElement,
  Title,
  Tooltip,
  Legend
);

// Giả sử bạn có một mảng journalEntries với thông tin về credit và debit
const journalEntries = [
  { entryDate: "2024-01-15", credit: 100, debit: 50 },
  { entryDate: "2024-02-10", credit: 200, debit: 30 },
  { entryDate: "2024-03-12", credit: 150, debit: 70 },
  { entryDate: "2024-05-25", credit: 250, debit: 120 },
  { entryDate: "2024-06-14", credit: 180, debit: 60 },
  { entryDate: "2024-07-05", credit: 120, debit: 50 },
  { entryDate: "2024-09-20", credit: 220, debit: 100 },
  { entryDate: "2024-10-08", credit: 300, debit: 150 },
  { entryDate: "2024-11-18", credit: 130, debit: 80 },
  { entryDate: "2024-12-02", credit: 210, debit: 90 },
];

// Mảng các tháng từ 1 đến 12
const months = [
  "January",
  "February",
  "March",
  "April",
  "May",
  "June",
  "July",
  "August",
  "September",
  "October",
  "November",
  "December",
];

// Hàm để tính tổng số tiền credit, debit và balance cho mỗi tháng
const getMonthlyData = (journalEntries) => {
  const creditData = new Array(12).fill(0); // Mảng chứa tổng tiền credit của mỗi tháng
  const debitData = new Array(12).fill(0); // Mảng chứa tổng tiền debit của mỗi tháng

  journalEntries.forEach((entry) => {
    const month = new Date(entry.entryDate).getMonth(); // Lấy tháng từ entryDate (0-11)
    creditData[month] += entry.credit; // Cộng dồn số tiền credit vào tháng tương ứng
    debitData[month] += entry.debit; // Cộng dồn số tiền debit vào tháng tương ứng
  });

  return { creditData, debitData };
};

// Hàm tính tổng credit, debit và số dư
const getTotals = (journalEntries) => {
  let totalCredit = 0;
  let totalDebit = 0;

  journalEntries.forEach((entry) => {
    totalCredit += entry.credit;
    totalDebit += entry.debit;
  });

  const balance = totalCredit - totalDebit; // Số dư = Tổng credit - Tổng debit
  return { totalCredit, totalDebit, balance };
};

const Dashboard = () => {
  const { creditData, debitData } = getMonthlyData(journalEntries); // Lấy dữ liệu monthly cho credit và debit
  const { totalCredit, totalDebit, balance } = getTotals(journalEntries); // Lấy tổng credit, debit và balance

  const data = {
    labels: months, // Các tháng từ 1 đến 12
    datasets: [
      {
        label: "Credit",
        data: creditData, // Dữ liệu cho credit
        borderColor: "rgb(75, 192, 192)",
        backgroundColor: "rgba(75, 192, 192, 0.2)",
        fill: false, // Không tô màu dưới đường
      },
      {
        label: "Debit",
        data: debitData, // Dữ liệu cho debit
        borderColor: "rgb(255, 99, 132)",
        backgroundColor: "rgba(255, 99, 132, 0.2)",
        fill: false, // Không tô màu dưới đường
      },
    ],
  };

  return (
    <div>
      <Line data={data} />
      <div style={{ marginTop: "20px" }}>
        <h4>Total Credit: {totalCredit}</h4>
        <h4>Total Debit: {totalDebit}</h4>
        <h4>Balance: {balance}</h4>
      </div>
    </div>
  );
};

export default Dashboard;
