import React from "react";

function Header() {
	return (
	<nav className="navbar navbar-expand-md navbar-dark bg-dark fixed-top">
		<a className="navbar-brand" href="#">Icecream</a>
		<button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
			<span className="navbar-toggler-icon"></span>
		</button>
		
		<div className="collapse navbar-collapse" id="navbarsExampleDefault">
			<ul className="navbar-nav mr-auto">
			<li className="nav-item active">
				<a className="nav-link" href="#">Home<span className="sr-only">(current)</span></a>
			</li>
			<li className="nav-item">
				<a className="nav-link" href="#">Product</a>
			</li>
			  <li className="nav-item dropdown">
				<a className="nav-link dropdown-toggle" href="https://example.com" id="dropdown01" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Category</a>
				<div className="dropdown-menu" aria-labelledby="dropdown01">
				  <a className="dropdown-item" href="#">Action</a>
				  <a className="dropdown-item" href="#">Another action</a>
				  <a className="dropdown-item" href="#">Something else here</a>
				</div>
			  </li>
			</ul>
        <form className="form-inline my-2 my-lg-0">		  
          <button className="btn btn-outline-success my-2 my-sm-0" type="submit">Login</button>
          <button className="btn btn-outline-success my-2 my-sm-0" type="submit">Signup</button>
        </form>
      </div>
    </nav>
	);
}

export default Header;



