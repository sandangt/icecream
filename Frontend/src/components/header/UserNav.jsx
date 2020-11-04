import React from "react";
import {Link, NavLink} from "react-router-dom";

function UserNav() {
    return (
<ul className="navbar-nav mr-auto">
    <li className="nav-item">
        <NavLink className="nav-link" to="/">Home</NavLink>
    </li>
    <li className="nav-item">
        <NavLink className="nav-link" to="/">Product</NavLink>
    </li>
    <li className="nav-item">
        <NavLink className="nav-link" to="/profile">Profile</NavLink>
    </li>
    <li className="nav-item dropdown">
        <a className="nav-link dropdown-toggle" id="dropdown01" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Category</a>
        <div className="dropdown-menu" aria-labelledby="dropdown01">
            <a className="dropdown-item" to="/">Action</a>
            <a className="dropdown-item" to="/">Another action</a>
            <a className="dropdown-item" to="/">Something else here</a>
        </div>
    </li>
</ul>
    );
}

export default UserNav;