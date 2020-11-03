import React from "react";
import {Link, NavLink} from "react-router-dom";

class Header extends React.Component {
	render() {
		return (
<nav className="navbar navbar-expand-md navbar-dark bg-dark fixed-top">
	<Link className="navbar-brand" to="/">Icecream</Link>
	<button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
		<span className="navbar-toggler-icon"></span>
	</button>
	<div className="collapse navbar-collapse" id="navbarsExampleDefault">
		<ul className="navbar-nav mr-auto">
			<li className="nav-item">
				<NavLink className="nav-link" to="/">Home</NavLink>
			</li>
			<li className="nav-item">
				<NavLink className="nav-link" to="/profile">Product</NavLink>
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
		<form className="form-inline my-2 my-lg-0">		  
			<Link className="btn btn-outline-success my-2 my-sm-0" to="/login">Login</Link>
			<Link className="btn btn-outline-success my-2 my-sm-0" to="/signup">Signup</Link>
		</form>
	</div>
</nav>
		);
	}
}

export default Header;



