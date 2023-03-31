import React, { useState, useEffect } from "react";
import ProductFormPage from "./ProductFormPage";
import PageHeader from "../../components/PageHeader";
import PopUp from "../../components/CommonPopUp";
import PeopleOutlineOutlinedIcon from "@material-ui/icons/PeopleOutlineOutlined";
import {
  Paper,
  Table,
  TableHead,
  TableBody,
  TableCell,
  TableRow,
  makeStyles,
  Toolbar,
  InputAdornment,
} from "@material-ui/core";
import Controlls from "../../components/reusable-controlls/Controlls";
import { DeleteOutline, EditOutlined, Search } from "@material-ui/icons";
import AddIcon from "@material-ui/icons/Add";
import Notification from "../../components/Notification";
import ConfirmDialog from "../../components/reusable-controlls/ConfirmDialog";
import ProductService from "../../services/ProductService";
import moment from "moment";

// JSS styles for table
const useStyles = makeStyles((theme) => ({
  table: {
    marginTop: theme.spacing(3),
    "& thead th": {
      fontWeight: "600",
      color: theme.palette.primary.main,
      backgroundColor: theme.palette.primary.light,
    },
    "& tbody td": {
      fontWeight: "300",
    },
    "& tbody tr:hover": {
      backgroundColor: "#fffbf2",
      cursor: "pointer",
    },
  },
  pageContent: {
    margin: theme.spacing(5),
    padding: theme.spacing(3),
  },
  searchInput: {
    width: "75%",
  },
  newButton: {
    position: "absolute",
    right: "10px",
  },
}));

// Table head cells with column width
const headCells = [
  { id: "productId", label: "Product ID", width: "5%" },
  { id: "productName", label: "Product Name", width: "10%" },
  { id: "scrumMasterName", label: "Scrum Master", width: "10%" },
  { id: "productOwnerName", label: "Product Owner", width: "10%" },
  { id: "developers", label: "Developer Names", width: "30%" },
  { id: "startDate", label: "Start Date", width: "10%" },
  { id: "methodology", label: "Methodology", width: "10%" },
  { id: "action", label: "Action", width: "15%" },
];

export default function ProductViewPage() {
  const classes = useStyles();
  const [records, setRecords] = useState([]);

  useEffect(() => {
    retrieveProducts();
  }, []);

  // load products
  const retrieveProducts = async () => {
    try {
      const res = await ProductService.getAllProducts();
      setRecords(res.data.data);
    } catch (e) {
      console.log(e);
      setNotify({
        isOpen: true,
        message: "Error Occured!",
        type: "error",
      });
    }
  };

  // add product
  const createProduct = (data, resetForm) => {
    const formattedData = formatStartDate(data);

    ProductService.createProduct(formattedData)
      .then((res) => {
        resetForm();
        setRecordForEdit(null);
        setOpenPopUp(false);
        retrieveProducts();
        setNotify({
          isOpen: true,
          message: "Submitted Successfully",
          type: "success",
        });
      })
      .catch((e) => {
        console.log(e);
        resetForm();
        setRecordForEdit(null);
        setOpenPopUp(false);
        setNotify({
          isOpen: true,
          message: "Error Occured!",
          type: "error",
        });
      });
  };

  // update product
  const updateProduct = (data, resetForm) => {
    const formattedData = formatStartDate(data);

    ProductService.updateProduct(formattedData.productId, data)
      .then((res) => {
        resetForm();
        setRecordForEdit(null);
        setOpenPopUp(false);
        retrieveProducts();
        setNotify({
          isOpen: true,
          message: "Updated Successfully",
          type: "success",
        });
      })
      .catch((e) => {
        console.log(e);
        resetForm();
        setRecordForEdit(null);
        setOpenPopUp(false);
        setNotify({
          isOpen: true,
          message: "Error Occured!",
          type: "error",
        });
      });
  };

  // delete product
  const deleteProduct = (productId) => {
    ProductService.removeProduct(productId)
      .then((res) => {
        retrieveProducts();
        setNotify({
          isOpen: true,
          message: "Deleted Successfully",
          type: "error",
        });
      })
      .catch((e) => {
        console.log(e);
        setNotify({
          isOpen: true,
          message: "Error Occured!",
          type: "error",
        });
      });
  };

  // format date, fix for date picker output date format issue
  const formatStartDate = (data) => {
    const { startDate } = data;
    const formattedStartDate = moment(new Date(startDate)).format("YYYY/MM/DD");
    return { ...data, startDate: formattedStartDate };
  };

  const [openPopUp, setOpenPopUp] = useState(false);
  const [recordForEdit, setRecordForEdit] = useState(null);
  const [notify, setNotify] = useState({
    isOpen: false,
    message: "",
    type: "",
  });

  const [confirmDialog, setConfirmDialog] = useState({
    isOpen: false,
    title: "",
  });

  // add or edit product based on product ID
  const addOrEdit = (product, resetForm) => {
    if (product.productId == 0) createProduct(product, resetForm);
    else updateProduct(product, resetForm);
  };

  // open PopUp
  const openInPopUp = (item) => {
    setRecordForEdit(item);
    setOpenPopUp(true);
  };

  // handle delete confirmation modal
  const onDelete = (productId) => {
    setConfirmDialog({
      ...confirmDialog,
      isOpen: false,
    });
    deleteProduct(productId);
  };

  const [searchValue, setSearchValue] = useState("");

  // handle search changes
  const handleSearchChange = (event) => {
    setSearchValue(event.target.value);
  };

  // filter records based on search
  const filterRecords = () => {
    return records.filter((record) => {
      return Object.values(record).some((value) =>
        value.toString().toLowerCase().includes(searchValue.toLowerCase())
      );
    });
  };

  return (
    <>
      <PageHeader
        title="BC's Government CITZ IMB Product Catalog"
        subTitle={`${filterRecords().length} products are availble in GitHub`}
        icon={<PeopleOutlineOutlinedIcon fontSize="large" />}
      />

      <Paper className={classes.pageContent}>
        <Toolbar>
          <Controlls.Input
            label="Search Product"
            className={classes.searchInput}
            InputProps={{
              startAdornment: (
                <InputAdornment position="start">
                  <Search />
                </InputAdornment>
              ),
            }}
            onChange={handleSearchChange}
          />
          <Controlls.Button
            text="Add New"
            className={classes.newButton}
            variant="outlined"
            startIcon={<AddIcon />}
            onClick={() => {
              setOpenPopUp(true);
              setRecordForEdit(null);
            }}
          />
        </Toolbar>
        <Table className={classes.table}>
          <TableHead>
            <TableRow>
              {headCells.map((headCell) => (
                <TableCell style={{ width: headCell.width }}>
                  {headCell.label}
                </TableCell>
              ))}
            </TableRow>
          </TableHead>
          <TableBody>
            {filterRecords().map((item) => (
              <TableRow key={item.productId}>
                <TableCell>{item.productId}</TableCell>
                <TableCell>{item.productName}</TableCell>
                <TableCell>{item.scrumMasterName}</TableCell>
                <TableCell>{item.productOwnerName}</TableCell>
                <TableCell>
                  {item.developers.map((dev) => dev).join(", ")}
                </TableCell>
                <TableCell>{item.startDate}</TableCell>
                <TableCell>
                  {item.methodology.charAt(0).toUpperCase() +
                    item.methodology.slice(1)}
                </TableCell>
                <TableCell>
                  <Controlls.ActionButton
                    onClick={() => {
                      openInPopUp(item);
                    }}
                    color="primary"
                  >
                    <EditOutlined fontSize="small" />
                  </Controlls.ActionButton>
                  <Controlls.ActionButton
                    color="secondary"
                    onClick={() => {
                      setConfirmDialog({
                        isOpen: true,
                        title: "Are you sure to delete this record?",
                        onConfirm: () => {
                          onDelete(item.productId);
                        },
                      });
                    }}
                  >
                    <DeleteOutline fontSize="small" />
                  </Controlls.ActionButton>
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </Paper>

      <PopUp
        title="Product Form"
        openPopUp={openPopUp}
        setOpenPopUp={setOpenPopUp}
      >
        <ProductFormPage recordForEdit={recordForEdit} addOrEdit={addOrEdit} />
      </PopUp>

      <Notification notify={notify} setNotify={setNotify} />

      <ConfirmDialog
        confirmDialog={confirmDialog}
        setConfirmDialog={setConfirmDialog}
      />
    </>
  );
}
