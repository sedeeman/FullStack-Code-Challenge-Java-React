import { Paper, Card, Typography, makeStyles } from "@material-ui/core";
import React from "react";

const useStyles = makeStyles((theme) => ({
  root: {
    backgroundColor: "#fdfdff",
  },
  pageHeader: {
    padding: theme.spacing(3),
    display: "flex",
    marginBottom: theme.spacing(1),
  },
  pageIcon: {
    display: "inline-block",
    padding: theme.spacing(1),
    color: "#3c44b1",
  },
  pageTitle: {
    paddingLeft: theme.spacing(3),
    "& .MuiTypography-subtitle2": {
      opacity: "0.6",
    },
  },
}));

// page header for all over the project
export default function PageHeader(props) {
  const classes = useStyles();
  const { title, subTitle, icon } = props;
  return (
    <Paper elevation={0} square>
      <div className={classes.pageHeader}>
        <Card className={classes.pageIcon}>{icon}</Card>
        <div className={classes.pageTitle}>
          <Typography variant="h6" component="div">
            {title}
          </Typography>
          <Typography variant="subtitle1" component="div">
            {subTitle}
          </Typography>
        </div>
      </div>
    </Paper>
  );
}
