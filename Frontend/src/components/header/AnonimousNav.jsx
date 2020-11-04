import React from "react";
import {Link, NavLink} from "react-router-dom";

function AnonimousNav() {
    return (
<ul className="navbar-nav mr-auto">
    <li className="nav-item">
        <NavLink className="nav-link" to="/">Home</NavLink>
    </li>
    <li className="nav-item">
        <NavLink className="nav-link" to="/admin">Product</NavLink>
    </li>
    <li className="nav-item dropdown">
        <Link className="nav-link dropdown-toggle" id="dropdown01" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Category</Link>
        <div className="dropdown-menu" aria-labelledby="dropdown01">
            <NavLink className="dropdown-item" to="/">Action</NavLink>
            <NavLink className="dropdown-item" to="/">Another action</NavLink>
            <NavLink className="dropdown-item" to="/">Something else here</NavLink>
        </div>
    </li>
</ul>
    );
}

export default AnonimousNav;