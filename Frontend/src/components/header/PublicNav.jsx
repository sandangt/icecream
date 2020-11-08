import React from "react";
import {NavLink} from "react-router-dom";

function PublicNav() {
    return (
<ul className="navbar-nav mr-auto">
    <li className="nav-item">
        <NavLink className="nav-link" to="/home">Home</NavLink>
    </li>
    <li className="nav-item">
        <NavLink className="nav-link" to="/about">About</NavLink>
    </li>
    <li className="nav-item dropdown">
        <a className="nav-link dropdown-toggle" id="dropdown01" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Category</a>
        <div className="dropdown-menu" aria-labelledby="dropdown01">
            <NavLink className="dropdown-item" to="/">Action</NavLink>
            <NavLink className="dropdown-item" to="/">Another action</NavLink>
            <NavLink className="dropdown-item" to="/">Something else here</NavLink>
        </div>
    </li>
</ul>
    );
}

export default PublicNav;