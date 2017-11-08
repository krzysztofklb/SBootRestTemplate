import axios from "axios";

export function fetchItems() {
  return (dispatch) => {
    dispatch({ type: "GET_ITEMS" });

    axios.get("http://localhost:8080/items")
      .then(response => { dispatch({ type: "GET_ITEMS_FULFILLED", payload: response.data }) })
      .catch(err => { dispatch({ type: "GET_ITEMS_REJECTED", payload: err }) });
  };
}

export function addItem(item) {
  return (dispatch) => {
    dispatch({ type: "ADD_ITEM" });
    axios.post("http://localhost:8080/items", { "name": item.name, "price": item.price })
      .then(dispatch(fetchItems()))
  };
}

export function deleteItem(id) {
  return (dispatch) => {
    dispatch({ type: "DELETE_ITEM" });

    axios.delete("http://localhost:8080/items/" + id)
      .then(dispatch(fetchItems()))
  };
}

export function updateItem(item) {
  return (dispatch) => {
    dispatch({ type: "UPDATE_ITEM" });

    axios.patch("http://localhost:8080/items/" + item.id, { "name": item.name, "price": item.price })
      .then(dispatch(fetchItems()))
  };
}