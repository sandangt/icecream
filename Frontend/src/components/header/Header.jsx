import React from "react";
import {Link} from "react-router-dom";
import {connect} from "react-redux";
import {logout} from "actions/auth.js";

import AdminNav from "./AdminNav.jsx";
import UserNav from "./UserNav.jsx";
import PublicNav from "./PublicNav.jsx";

class Header extends React.Component {
	constructor(props) {
		super(props);
		this.state = {
			view: 0
		};
	}

	handleLogout = (e) => {
		e.preventDefault();
		this.props.logout();
		window.location.href="/home";
	}
	renderNavBar = () => {
		if (this.props.isLoggedIn) {
			if (this.props.user.roles.includes("ROLE_ADMIN") && this.props.user.roles.includes("ROLE_USER")) {
				return this.state.view === 1 ? <UserNav/> : <AdminNav/>;
			}
			else if (this.props.user.roles.includes("ROLE_USER")) {
				return <UserNav/>;
			}
			return <AdminNav/>;
		}
		return <PublicNav/>;
	}
	renderAuthButton = () => {
		if (this.props.isLoggedIn) {
			return (
			<div class="btn-group dropleft">
				<button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					{this.props.user.username}
				</button>
				<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
					{ this.props.user.roles.includes("ROLE_ADMIN") && this.props.user.roles.includes("ROLE_USER") ?
					(<React.Fragment>
						<button className="dropdown-item" onClick={(e) => {
							e.preventDefault();
							this.setState({view:0});
						}}>Admin's view</button>
						<button className="dropdown-item" onClick={(e) => {
							e.preventDefault();
							this.setState({view:1});
						}}>User's view</button>
					</React.Fragment>) : null }
					<Link className="dropdown-item" to="/profile">Profile</Link>
					<Link className="dropdown-item" to="/update-password">Change password</Link>
					<button className="dropdown-item" onClick={this.handleLogout}>Logout</button>
				</div>
			</div>
			);
		}
		return (
		<div class="btn-group"> 
			<Link className="btn btn-outline-success my-2 my-sm-0" to="/login">Login</Link>
			<Link className="btn btn-outline-success my-2 my-sm-0" to="/signup">Signup</Link>
		</div>
		);
	}
	render() {
		return (
<nav className="navbar navbar-expand-md navbar-dark bg-dark fixed-top">
	<Link className="navbar-brand" to="/home">Icecream</Link>
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



