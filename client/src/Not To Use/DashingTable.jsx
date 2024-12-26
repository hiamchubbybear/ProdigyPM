import { HotTable } from "@handsontable/react";
import "handsontable/dist/handsontable.full.min.css";
import { registerAllModules } from "handsontable/registry";
import React, { useEffect, useRef, useState } from "react";
import axios from "../Api/axios";
import "../../Assets/css/Accounting.css";
const REQUEST_URI = "/api/account/all";

const DashingTable = () => {
  registerAllModules();
  const [data, setData] = useState([]);
  const [token, setToken] = useState("");

  useEffect(() => {
    const currentToken = localStorage.getItem("token");
    setToken(currentToken);

    axios
      .get(REQUEST_URI, {
        headers: {
          Authorization: `Bearer ${currentToken}`,
        },
      })
      .then((res) => {
        const fetchedData = res?.data?.data || [];
        console.log(fetchedData);

        setData(fetchedData);
      })
      .catch((err) => {
        console.log("Received failed ", err);
      });
  }, [token]);
  const createArrays = () => {
    const emptyRow = new Array(20).fill("");
    const dataWithEmptyRow = data.map((item) => {
      return [JSON.stringify(item)];
    });
    return [emptyRow, ...dataWithEmptyRow];
  };
  const newArrays = createArrays();

  const colHeaders = Array.from({ length: 20 }, (_, i) => {
    return String.fromCharCode(65 + i);
  });

  const columns = Array.from({ length: 20 }, (_, index) => ({
    data: index,
  }));
  const hotRef = useRef(null);
  const buttonClickCallback = () => {
    const hot = hotRef.current?.hotInstance;
    const exportPlugin = hot?.getPlugin("exportFile");

    exportPlugin?.downloadFile("csv", {
      bom: false,
      columnDelimiter: ",",
      columnHeaders: false,
      exportHiddenColumns: true,
      exportHiddenRows: true,
      fileExtension: "csv",
      filename: "Handsontable-CSV-file_[YYYY]-[MM]-[DD]",
      mimeType: "text/csv",
      rowDelimiter: "\r\n",
      rowHeaders: true,
    });
  };
  return (
    <div>
      <div className="example-controls-container">
        <div className="controls">
          <button id="export-file" onClick={() => buttonClickCallback()}>
            Download CSV
          </button>
        </div>
      </div>
      <HotTable
        ref={hotRef}
        data={newArrays}
        rowHeaderWidth={30}
        autoWrapRow={true}
        autoWrapCol={true}
        rowHeaders={true}
        readOnly={false}
        columns={columns}
        colHeaders={colHeaders}
        height="auto"
        width="auto"
        undoRedo={true}
        contextMenu={["copy", "cut"]}
        manualColumnResize={true}
        manualRowResize={true}
        colWidths={[
          300, 200, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100,
          100, 100, 100, 100, 100, 100,
        ]}
        cells={(row, col) => {
          if (col === 0 || row === 0) {
            return { readOnly: true };
          }
          return {};
        }}
        copyPaste={true}
        multiSelect={true}
        licenseKey="non-commercial-and-evaluation"
      ></HotTable>
    </div>
  );
};

export default DashingTable;
