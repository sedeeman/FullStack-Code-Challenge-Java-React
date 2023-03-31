import { TextField } from "@material-ui/core";
import React from "react";

export default function Input(props) {
  const {
    name,
    label,
    value,
    error = null,
    multiline = false,
    onKeyDown,
    onChange,
    ...other
  } = props;

  return (
    <TextField
      variant="outlined"
      multiline={multiline}
      label={label}
      onKeyDown={onKeyDown}
      name={name}
      value={value}
      onChange={onChange}
      {...other}
      {...(error && { error: true, helperText: error })}
    />
  );
}
