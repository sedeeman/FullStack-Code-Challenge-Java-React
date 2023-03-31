import { makeStyles } from "@material-ui/core/styles";
import { TextField, Chip } from "@material-ui/core";

const useStyles = makeStyles({
  chipsContainer: {
    display: "flex",
    flexWrap: "wrap",
  },
  chip: {
    marginRight: "5px",
    marginBottom: "5px",
  },
});

export default function CuMultiChipInput(props) {
  const { label, values, handleKeyDown, handleOnDelete, error } = props;

  const classes = useStyles();

  return (
    <TextField
      label={label}
      variant="outlined"
      multiline
      maxRows={2}
      onKeyDown={handleKeyDown}
      InputProps={{
        startAdornment: (
          <div className={classes.chipsContainer}>
            {values.map((developer, index) => (
              <Chip
                key={index}
                variant="outlined"
                label={developer}
                onDelete={() => handleOnDelete(index)}
                className={classes.chip}
              />
            ))}
          </div>
        ),
      }}
      helperText={error}
      error={Boolean(error)}
    />
  );
}
