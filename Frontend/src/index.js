import React from "react";
import ReactDOM from "react-dom";
import { Provider } from "react-redux";
// import store from "user/store.js";
import App from "admin/App.jsx";

// ReactDOM.render(
// <Provider store={store}>
//     <App/>
// </Provider>,
// document.getElementById("root"));
ReactDOM.render(<App/>, document.getElementById("root"));