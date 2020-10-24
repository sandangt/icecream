import React from "react";

import "./Header.css";

class Header extends React.Component {
  render() {
    return (
<div>
	<nav className="navbar fixed-top navbar-expand-md flex-nowrap navbar-new-top">
		<a href="/" className="navbar-brand"><img src="#" alt=""/>Bootstrap 4</a>
		<ul className="nav navbar-nav mr-auto"></ul>
		<ul className="navbar-nav flex-row">
			<li className="nav-item">
				<a className="nav-link px-2">Link</a>
			</li>
			<li className="nav-item">
			    <a className="nav-link px-2">Link</a>
			</li>
			<li className="nav-item">
			    <button type="button"  className="header-btn">Button</button>
			</li>
		</ul>
		<button className="navbar-toggler ml-auto" type="button" data-toggle="collapse" data-target="#navbar2">
			<span className="navbar-toggler-icon"></span>
		</button>
	</nav>

	<nav className="navbar fixed-top navbar-expand-md navbar-new-bottom">
		<div className="navbar-collapse collapse pt-2 pt-md-0" id="navbar2">

			<ul className="navbar-nav w-100 justify-content-center px-3">
				<li className="nav-item active">
					<a className="nav-link" href="#">Link</a>
				</li>
			    <li className="nav-item">
			        <a className="nav-link">Link</a>
			    </li>
			    <li className="nav-item">
			        <a className="nav-link">Link</a>
			    </li>
			    <li className="nav-item">
			        <a className="nav-link">Link</a>
			    </li>
			    <li className="nav-item">
			        <a className="nav-link">Link</a>
			    </li>
			    <li className="nav-item">
			        <a className="nav-link">Link</a>
			    </li>
			</ul>
		</div>
	</nav>
</div>
    );
  }
}

export default Header;
