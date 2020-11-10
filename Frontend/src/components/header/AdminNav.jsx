import React from "react";
import {NavLink} from "react-router-dom";

function AdminNav() {
    return (
<ul className="navbar-nav mr-auto">
    <li className="nav-item">
        <NavLink className="nav-link" to="/home">Home</NavLink>
    </li>
    <li className="nav-item">
        <NavLink className="nav-link" to="/admin/users">User</NavLink>
    </li>
    <li className="nav-item">
        <NavLink className="nav-link" to="/admin/products">Product</NavLink>
    </li>
    <li className="nav-item">
        <NavLink className="nav-link" to="/admin/categories">Category</NavLink>
    </li>
    <li className="nav-item">
        <NavLink className="nav-link" to="/admin/orders">Order</NavLink>
    </li>
    <li className="nav-item">
        <NavLink className="nav-link" to="/admin/feedbacks">Feedback</NavLink>
    </li>
    <li className="nav-item">
        <NavLink className="nav-link" to="/admin/faq">FAQ</NavLink>
    </li>
    {/* <li className="nav-item dropdown">
        <Link className="nav-link dropdown-toggle" id="dropdown01" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Category</Link>
        <div className="dropdown-menu" aria-labelledby="dropdown01">
            <NavLink className="dropdown-item" to="/">Action</NavLink>
            <NavLink className="dropdown-item" to="/">Another action</NavLink>
            <NavLink className="dropdown-item" to="/">Something else here</NavLink>
        </div>
    </li> */}
</ul>
    );
}

export default AdminNav;