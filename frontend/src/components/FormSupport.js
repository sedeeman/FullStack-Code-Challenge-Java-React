import React, { useState } from "react";
import { makeStyles } from "@material-ui/core";

export function UseForm(initialFvalues, validateOnChange = false, validate) {
  const [values, setValues] = useState(initialFvalues);
  const [errors, setErrors] = useState({});

  // Handle inputChnage fields
  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setValues({
      ...values,
      [name]: value,
    });
    if (validateOnChange) validate({ [name]: value });
  };

  const resetForm = () => {
    setValues(initialFvalues);
    setErrors({});
  };

  // Handle keyDown func for adding developers
  const handleKeyDown = (event) => {
    const { key, target } = event;

    if (key === "Enter") {
      event.preventDefault();
      const newDeveloper = target.value.trim();
      if (newDeveloper) {
        handleAddDeveloper(newDeveloper);
        target.value = "";
      }
    }
  };

  // Handle add developer func
  const handleAddDeveloper = (newDeveloper) => {
    const { developers } = values;

    if (developers.length >= 5) {
      setErrors({
        ...errors,
        developers: "Maximum allowed developers limit exceeded!",
      });
      return;
    }

    const index = developers.indexOf(newDeveloper);
    const updatedDevelopers = [...developers];

    if (index !== -1) {
      // Developer already exists, update it
      updatedDevelopers[index] = newDeveloper;
    } else {
      // Add new developer
      updatedDevelopers.push(newDeveloper);
      // Reset errors, when atleast one developer has added.
      setErrors({
        ...errors,
        developers: "",
      });
    }

    setValues({
      ...values,
      developers: updatedDevelopers,
    });
  };

  // Handle remove Developer func
  const handleOnDelete = (index) => {
    const newDevelopers = [...values.developers];
    newDevelopers.splice(index, 1);
    setValues({ ...values, developers: newDevelopers });
    // Reset errors
    setErrors({
      ...errors,
      developers: "",
    });
  };

  return {
    values,
    setValues,
    errors,
    setErrors,
    handleInputChange,
    resetForm,
    handleKeyDown,
    handleOnDelete,
  };
}

// Uss JSS style
const useStyles = makeStyles((theme) => ({
  root: {
    "& .MuiFormControl-root": {
      width: "80%",
      margin: theme.spacing(1),
    },
    "& .MuiChip-root": {
      margin: theme.spacing(0.5),
    },
  },
}));

export function Form(props) {
  const classes = useStyles();
  const { children, ...other } = props;

  return (
    <form className={classes.root} autoComplete="off" {...other}>
      {props.children}
    </form>
  );
}
