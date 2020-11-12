import React from "react";
import {NavLink} from "react-router-dom";

function UserNav() {
    return (
<ul className="navbar-nav mr-auto">
    <li className="nav-item">
        <NavLink className="nav-link" to="/home">Home</NavLink>
    </li>
    <li className="nav-item">
        <NavLink className="nav-link" to="/about">About</NavLink>
    </li>
    <li className="nav-item">
        <NavLink className="nav-link" to="/shop">Shop</NavLink>
    </li>
    <li className="nav-item">
        <NavLink className="nav-link" to="/order">Order</NavLink>
    </li>
    <li className="nav-item">
        <NavLink className="nav-link" to="/history">History</NavLink>
    </li>
    <li className="nav-item">
        <NavLink className="nav-link" to="/faq">FAQ</NavLink>
    </li>
</ul>
    );
}

export default UserNav;