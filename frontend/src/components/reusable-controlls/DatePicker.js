import DateFnsUtils from "@date-io/date-fns";
import {
  KeyboardDatePicker,
  MuiPickersUtilsProvider,
} from "@material-ui/pickers";
import React from "react";

export default function DatePicker(props) {
  const { name, label, value, onChange, isUpdate } = props;

  const convertToDefEventPara = (name, value) => ({
    target: { name, value },
  });

  return (
    <MuiPickersUtilsProvider utils={DateFnsUtils}>
      <KeyboardDatePicker
        disabled={isUpdate.productId != 0 ? true : false}
        disableFuture={true}
        disableToolbar
        variant="inline"
        inputVariant="outlined"
        label={label}
        format="yyyy/MM/dd"
        name={name}
        value={value}
        onChange={(date) => {
          onChange(convertToDefEventPara(name, date));
        }}
      ></KeyboardDatePicker>
    </MuiPickersUtilsProvider>
  );
}
