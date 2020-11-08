import React from "react";
import {Link} from "react-router-dom";

function Footer() {
	return (
        <footer className="page-footer font-small indigo">
			<hr className="rgba-white-light"/>
			<div className="row text-center d-flex justify-content-center pt-5 mb-3">
				<div className="col-md-2 mb-3">
					<h6 className="text-uppercase font-weight-bold">
						<Link to="/about">About us</Link>
					</h6>
				</div>
				<div className="col-md-2 mb-3">
					<h6 className="text-uppercase font-weight-bold">
						<Link to="/contact">Contact</Link>
					</h6>
				</div>
			</div>
			<hr className="rgba-white-light"/>

			{/* <div className="row d-flex text-center justify-content-center mb-md-0 mb-4">
				<div className="col-md-8 col-12 mt-5">
					<p style={{ lineHeight: "1.7rem" }}>
						Sed perspiciatis ut unde omnis iste natus error sit
						voluptatem accusantium doloremque laudantium, totam
						rem aperiam, eaque ipsa quae ab illo inventore
						veritatis et quasi architecto beatae vitae dicta
						sunt explicabo.
					</p>
				</div>
			</div> */}

			<hr className="clearfix d-md-none rgba-white-light"/>
			<div className="row d-flex justify-content-center">
				<div className="row pb-3">
					<div className="col-md-12">
						<div className="mb-5 flex-center">
							<a className="fb-ic" href="https://www.facebook.com" target="_blank" rel="noopener noreferrer">
								<i className="fab fa-facebook-f fa-lg white-text mr-4">
									{" "}
								</i>
							</a>
							<a className="tw-ic" href="https://www.twitter.com" target="_blank" rel="noopener noreferrer">
								<i className="fab fa-twitter fa-lg white-text mr-4">
									{" "}
								</i>
							</a>
							<a className="gplus-ic" href="https://www.google.com" target="_blank" rel="noopener noreferrer">
								<i className="fab fa-google-plus-g fa-lg white-text mr-4">
									{" "}
								</i>
							</a>
							<a className="li-ic" href="https://www.linkedin.com" target="_blank" rel="noopener noreferrer">
								<i className="fab fa-linkedin-in fa-lg white-text mr-4">
									{" "}
								</i>
							</a>
							<a className="ins-ic" href="https://www.instagram.com" target="_blank" rel="noopener noreferrer">
								<i className="fab fa-instagram fa-lg white-text mr-4">
									{" "}
								</i>
							</a>
						</div>
					</div>
				</div>
			</div>

            <div className="footer-copyright text-center py-3">
                <Link to="/login">Icecream</Link>
                © 2020 Copyright
            </div>
        </footer>
    );
}

export default Footer;
