export const menuStructure = [
  {
    name: "Account",
    id: "account",
    path: "/account",
    children: [
      {
        name: "Account Management",
        id: "account-management",
        path: "/account-management",
      },
      {
        name: "Add Transaction",
        id: "add-transaction",
        path: "/accounting/add-transaction",
      },
      {
        name: "Find Transaction",
        id: "find-transaction",
        path: "accounting/search",
      },
      {
        name: "Financial Report",
        id: "financial-report",
        path: "/financial-report",
      },
      { name: "Budget Check", id: "budget-check", path: "/budget-check" },
      {
        name: "Transaction Management",
        id: "transaction-management",
        path: "/transaction-management",
      },
    ],
  },
  {
    name: "HRM",
    id: "hrm",
    path: "/hrm",
    children: [
      {
        name: "Employee Management",
        id: "employee-management",
        path: "/employee-management",
      },
      { name: "Payroll", id: "payroll", path: "/payroll" },
      {
        name: "Employee Training",
        id: "employee-training",
        path: "/employee-training",
      },
      { name: "Recruitment", id: "recruitment", path: "/recruitment" },
    ],
  },
  {
    name: "Manufacture",
    id: "manufacture",
    path: "/manufacture",
    children: [
      {
        name: "Production Plan",
        id: "production-plan",
        path: "/production-plan",
      },
      {
        name: "Production Tracking",
        id: "production-tracking",
        path: "/production-tracking",
      },
      {
        name: "Production Report",
        id: "production-report",
        path: "/production-report",
      },
      {
        name: "Material Management",
        id: "material-management",
        path: "/material-management",
      },
    ],
  },
  {
    name: "Purchase",
    id: "purchase",
    path: "/purchase",
    children: [
      {
        name: "Supplier Management",
        id: "supplier-management",
        path: "/supplier-management",
      },
      {
        name: "Create Purchase Order",
        id: "create-purchase-order",
        path: "/create-purchase-order",
      },
      {
        name: "Purchase Order Check",
        id: "purchase-order-check",
        path: "/purchase-order-check",
      },
      {
        name: "Contract Management",
        id: "contract-management",
        path: "/contract-management",
      },
    ],
  },
  {
    name: "Sales",
    id: "sales",
    path: "/sales",
    children: [
      {
        name: "Customer Management",
        id: "customer-management",
        path: "/customer-management",
      },
      {
        name: "Create Sales Order",
        id: "create-sales-order",
        path: "/create-sales-order",
      },
      {
        name: "Sales Order Tracking",
        id: "sales-order-tracking",
        path: "/sales-order-tracking",
      },
      { name: "Revenue Report", id: "revenue-report", path: "/revenue-report" },
    ],
  },
  {
    name: "Warehouse",
    id: "warehouse",
    path: "/warehouse",
    children: [
      {
        name: "Inventory Management",
        id: "inventory-management",
        path: "/inventory-management",
      },
      { name: "Stock Tracking", id: "stock-tracking", path: "/stock-tracking" },
      {
        name: "Warehouse Report",
        id: "warehouse-report",
        path: "/warehouse-report",
      },
      {
        name: "Inventory Check",
        id: "inventory-check",
        path: "/inventory-check",
      },
    ],
  },
];
