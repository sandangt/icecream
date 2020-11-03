import React from "react";
import {Link, NavLink} from "react-router-dom";
import {connect} from "react-redux";
import {logout} from "user/actions";

class Header extends React.Component {
	handleLogout = (e) => {
		e.preventDefault();
		this.props.logout();
	}
	renderAuthButton = () => {
		let result = null;
		if (this.props.isLoggedIn) {
			return (
				<button onClick={this.handleLogout} className="btn btn-outline-success my-2 my-sm-0" to="/logout">Logout</button>
			);
		}
		return (
			<div>		  
				<Link className="btn btn-outline-success my-2 my-sm-0" to="/login">Login</Link>
				<Link className="btn btn-outline-success my-2 my-sm-0" to="/signup">Signup</Link>
			</div>
		);
	}
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
		{this.renderAuthButton()}
	</div>
</nav>
		);
	}
}

const mapStateToProps = (state) => {
	console.log(state);
    return {
        isLoggedIn : state.auth.isLoggedIn,
        message : state.message,
    };
};
export default connect(mapStateToProps, {logout:logout})(Header);



