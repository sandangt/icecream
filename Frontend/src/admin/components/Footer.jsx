import React from "react";

function Footer() {
	return (	
<div>
	<div className="footer">
		<div className="footer-inner">
			<div className="footer-content">
				<span className="bigger-120">
					<span className="blue bolder">Ace</span>
					Application © 2013-2014
				</span>
				&nbsp; &nbsp;
				<span className="action-buttons">
					<a href="#">
						<i className="ace-icon fa fa-twitter-square light-blue bigger-150" />
					</a>
					<a href="#">
						<i className="ace-icon fa fa-facebook-square text-primary bigger-150" />
					</a>
					<a href="#">
						<i className="ace-icon fa fa-rss-square orange bigger-150" />
					</a>
				</span>
			</div>
		</div>
	</div>
	<a href="#" id="btn-scroll-up" className="btn-scroll-up btn btn-sm btn-inverse">
		<i className="ace-icon fa fa-angle-double-up icon-only bigger-110" />
	</a>
</div>
	);
}

export default Footer;