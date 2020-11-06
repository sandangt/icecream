import React from "react";
import {Link, NavLink} from "react-router-dom";
import {connect} from "react-redux";
import {logout} from "actions/auth.js";

import AdminNav from "./AdminNav.jsx";
import UserNav from "./UserNav.jsx";
import AnonimousNav from "./AnonimousNav.jsx";

class Header extends React.Component {
	handleLogout = (e) => {
		e.preventDefault();
		this.props.logout();
		window.location.href="/";
	}
	renderNavBar = () => {
		if (this.props.isLoggedIn) {
			return this.props.user.roles.includes("ROLE_ADMIN") ? <AdminNav/> : <UserNav/>;
		}
		return <AnonimousNav/>;
	}
	renderAuthButton = () => {
		if (this.props.isLoggedIn) {
			return (
				// <button onClick={this.handleLogout} className="btn btn-outline-success my-2 my-sm-0">Logout</button>
				<div class="btn-group dropleft">
					<button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						{this.props.user.username}
					</button>
					<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
						<Link class="dropdown-item" to="/update-password">Change password</Link>
						<button class="dropdown-item" onClick={this.handleLogout}>Logout</button>
					</div>
				</div>
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
		{this.renderNavBar()}
		{this.renderAuthButton()}
	</div>
</nav>
		);
	}
}

const mapStateToProps = (state) => {
    return {
		isLoggedIn : state.auth.isLoggedIn,
		user: state.auth.user,
        message : state.message.message,
    };
};
export default connect(mapStateToProps, {logout:logout})(Header);



