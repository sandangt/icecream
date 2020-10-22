import React from "react";

function Footer() {
	return(  
	<nav class="navbar fixed-bottom navbar-expand-sm navbar-dark bg-dark">
		  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		  </button>
		  <div class="collapse navbar-collapse" id="navbarCollapse">
			<ul class="navbar-nav mr-auto">
			  <li class="nav-item active">
				<a class="nav-link" href="#">FAQ<span class="sr-only">(current)</span></a>
			  </li>
			  <li class="nav-item">
				<a class="nav-link" href="#">About us</a>
			  </li>
			  <li class="nav-item">
				<a class="nav-link disabled" href="#">Contact</a>
			  </li>
			</ul>
		  </div>
	</nav>
  );
}

export default Footer;