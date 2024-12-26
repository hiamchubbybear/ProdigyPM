import { DataGrid } from "@mui/x-data-grid";
import { jwtDecode } from "jwt-decode";
import React, { useEffect, useState } from "react";
import { updateJournalEntryRequest } from "../../Api/apiRequest";
import axios from "../../Api/axios";
import "../../Assets/css/Dashboard.css";

const Dashboard = () => {
  const [rowData, setRowData] = useState([]);
  const [page, setPage] = useState(0);
  const [pageSize, setPageSize] = useState(10);
  const [status, setStatus] = useState("");

  useEffect(() => {
    const customer = jwtDecode(localStorage.getItem("token"));
    console.log(customer);

    const REQUEST_URI = `/api/journal-entries/all?page=${page}&size=${pageSize}`;
    axios
      .get(REQUEST_URI, {
        headers: {
          Authorization: `Bearer ${localStorage.getItem("token")}`,
          "Content-Type": "application/json",
        },
      })
      .then((res) => {
        const formattedData = res?.data?.data.map((entry) => ({
          id: entry.entryId,
          entryID: entry.entryId,
          entryDate: entry.entryDate,
          description: entry.description,
          status: entry.status,
          createdBy: entry.createdBy?.username,
          createdDate: entry.createdDate,
        }));
        setRowData(formattedData);
      })
      .catch((err) => {
        console.error("Error fetching journal entries:", err);
      });
  }, [page, pageSize]);

  const handleAction = (row) => {
    const dataTrans = {
      entryId: row.entryID,
      entryDate: row.entryDate,
      description: row.description,
      status: row.status,
      createBy: row.createdBy,
      createdDate: row.createdDate,
    };
    console.log(row);

    console.log("Loại ", typeof row.entryID);

    updateJournalEntryRequest(dataTrans)
      .then((response) => {
        setStatus("Update success");
        console.log("Update success", response.data);
      })
      .catch((err) => {
        setStatus("Error updating entry");
        console.error("Error updating entry:", err);
      });
  };

  const columns = [
    { field: "entryID", headerName: "ID", width: 180 },
    { field: "entryDate", headerName: "Entry Date", width: 180 },
    {
      field: "description",
      headerName: "Description",
      width: 250,
      editable: true,
    },
    {
      field: "status",
      headerName: "Status",
      width: 150,
      editable: (param) => param.row.status !== 1,
    },
    { field: "createdBy", headerName: "Created By", width: 200 },
    { field: "createdDate", headerName: "Created Date", width: 230 },
    {
      field: "update",
      headerName: "Update",
      width: 100,
      renderCell: (params) => (
        <button
          style={{
            padding: "5px 10px",
            backgroundColor: " #1D6F85",
            color: "#fff",
            border: "none",
            borderRadius: "5px",
            cursor: "pointer",
          }}
          onClick={() => handleAction(params.row)}
        >
          Update
        </button>
      ),
    },
  ];

  return (
    <div
      style={{
        height: "100vh",
        width: "100%",
        fontFamily: "Cascadia Code, sans-serif",
      }}
    >
      <DataGrid
        rows={rowData}
        columns={columns}
        pageSize={pageSize}
        page={page}
        onPageChange={(newPage) => setPage(newPage)}
        onPageSizeChange={(newSize) => setPageSize(newSize)}
        pagination
        paginationMode="server"
        style={{
          fontFamily: "Cascadia Code, sans-serif",
        }}
      />
      {status && (
        <div
          style={{
            marginTop: "20px",
            padding: "10px",
            backgroundColor: status === "Update success" ? "green" : "red",
            color: "white",
            borderRadius: "5px",
          }}
        >
          {status}
        </div>
      )}
    </div>
  );
};

export default Dashboard;
