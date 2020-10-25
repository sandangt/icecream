import React from "react";

class Footer extends React.Component {
    render() {
        return (	
<div>
	<div class="footer">
		<div class="footer-inner">
			<div class="footer-content">
				<p class="bigger-120">
					<span class="blue bolder">Ace </span>
				 	<span>Application copy 2013-2014</span>
				</p>
				<p class="action-buttons">
					<a href="#">
						<i class="ace-icon fa fa-twitter-square light-blue bigger-150"></i>
					</a>
					<a href="#">
						<i class="ace-icon fa fa-facebook-square text-primary bigger-150"></i>
					</a>
					<a href="#">
						<i class="ace-icon fa fa-rss-square orange bigger-150"></i>
					</a>
				</p>
			</div>
		</div>
	</div>
	<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
		<i className="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
	</a>
</div>
        );
    }
}

export default Footer;