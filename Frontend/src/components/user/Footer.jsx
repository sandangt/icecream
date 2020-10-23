import React from "react";

function Footer() {
	return(  
	<nav className="navbar fixed-bottom navbar-expand-sm navbar-dark bg-dark">
		  <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
			<span className="navbar-toggler-icon"></span>
		  </button>
		<div className="collapse navbar-collapse" id="navbarCollapse">
			<ul className="navbar-nav mr-auto">
				<li className="nav-item active">
					<a className="nav-link" href="#">FAQ<span className="sr-only">(current)</span></a>
				</li>
				<li className="nav-item">
					<a className="nav-link" href="#">About us</a>
				</li>
				<li className="nav-item">
					<a className="nav-link disabled" href="#">Contact</a>
				</li>
			</ul>
		</div>
	</nav>
  );
}

export default Footer;