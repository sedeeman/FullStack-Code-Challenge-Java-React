import { Grid } from "@material-ui/core";
import React, { useEffect } from "react";
import { Controlls } from "../../components/reusable-controlls/Controlls";
import { UseForm, Form } from "../../components/FormSupport";

const methodologies = [
  { id: "waterfall", title: "Waterfall" },
  { id: "agile", title: "Agile" },
];

const initialFvalues = {
  productId: 0,
  productName: "",
  scrumMasterName: "",
  productOwnerName: "",
  developers: [],
  startDate: new Date(),
  methodology: "",
};

export default function ProductFormPage(props) {
  const { addOrEdit, recordForEdit } = props;

  // handle validation onChange
  const validate = (fieldValues = values) => {
    let temp = { ...errors };
    if ("productName" in fieldValues)
      temp.productName = fieldValues.productName
        ? ""
        : "This field is required";
    if ("scrumMasterName" in fieldValues)
      temp.scrumMasterName = fieldValues.scrumMasterName
        ? ""
        : "This field is required";
    if ("productOwnerName" in fieldValues)
      temp.productOwnerName = fieldValues.productOwnerName
        ? ""
        : "This field is required";
    if ("starDate" in fieldValues)
      temp.startDate = fieldValues.startDate ? "" : "This field is required";
    if ("developers" in fieldValues)
      temp.developers =
        fieldValues.developers.length > 0
          ? ""
          : "Minimum 1 developer is required";
    if ("methodology" in fieldValues)
      temp.methodology =
        fieldValues.methodology.length != 0 ? "" : "This field is required";
    setErrors({
      ...temp,
    });
    if (fieldValues == values) return Object.values(temp).every((x) => x == "");
  };

  // use common functions realted form submission from UseForm
  const {
    values,
    setValues,
    errors,
    setErrors,
    handleInputChange,
    handleKeyDown,
    handleOnDelete,
    resetForm,
  } = UseForm(initialFvalues, true, validate);

  const handleSubmit = (e) => {
    e.preventDefault();
    if (validate()) {
      addOrEdit(values, resetForm);
    }
  };

  useEffect(() => {
    if (recordForEdit != null)
      setValues({
        ...recordForEdit,
      });
  }, [recordForEdit, setValues]);

  // use same PopUp form with reusable material-ui custome components to add/update product
  return (
    <Form onSubmit={handleSubmit}>
      <Grid container>
        <Grid item xs={6}>
          {/* custom text input for adding/updating */}
          <Controlls.Input
            name="productName"
            label="Product Name"
            value={values.productName}
            onChange={handleInputChange}
            error={errors.productName}
          />

          {/* custom text input for adding/updating */}
          <Controlls.Input
            name="scrumMasterName"
            label="Scrum Master"
            value={values.scrumMasterName}
            onChange={handleInputChange}
            error={errors.scrumMasterName}
          />

          {/* custom text input for adding/updating */}
          <Controlls.Input
            name="productOwnerName"
            label="Product Owner"
            value={values.productOwnerName}
            onChange={handleInputChange}
            error={errors.productOwnerName}
          />

          {/* custom text input for adding/updating multiple develoers using chips */}
          <Controlls.MultiChipInput
            label="Add Developers"
            values={values.developers}
            handleKeyDown={handleKeyDown}
            handleOnDelete={handleOnDelete}
            error={errors.developers}
          />
        </Grid>

        <Grid item xs={6}>
          {/* custom select input for adding/updating methodologies */}
          <Controlls.Select
            name="methodology"
            label="Methodology"
            value={values.methodology.toLowerCase()}
            onChange={handleInputChange}
            options={methodologies}
            error={errors.methodology}
          />

          {/* custom date picker for adding previouse dates, assume project start date is a old date */}
          <Controlls.DatePicker
            name="startDate"
            label="Start Date"
            value={values.startDate}
            onChange={handleInputChange}
            isUpdate={values}
          />

          {/* custom  align button for adding/updating */}
          <div>
            <Controlls.Button type="submit" text="Submit" />
            <Controlls.Button
              text="Reset"
              color="default"
              onClick={resetForm}
            />
          </div>
        </Grid>
      </Grid>
    </Form>
  );
}
