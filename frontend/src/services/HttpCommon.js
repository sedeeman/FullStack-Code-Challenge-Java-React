import axios from "axios";
// common properties for axios networking.
export default axios.create({
  baseURL: "http://localhost:3000/api",
  headers: {
    "Access-Control-Allow-Origin": "*",
    "Content-type": "application/json",
  },
});
