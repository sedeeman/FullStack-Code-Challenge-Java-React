import http from "./HttpCommon";

const getAllProducts = () => {
  return http.get("/products");
};

const getProduct = (id) => {
  return http.get(`/products/${id}`);
};

const createProduct = (data) => {
  return http.post("/products", data);
};

const updateProduct = (id, data) => {
  return http.put(`/products/${id}`, data);
};

const removeProduct = (id) => {
  return http.delete(`/products/${id}`);
};

const ProductService = {
  getAllProducts,
  getProduct,
  createProduct,
  updateProduct,
  removeProduct,
};

export default ProductService;
