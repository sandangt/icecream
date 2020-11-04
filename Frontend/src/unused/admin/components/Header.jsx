import React from "react";
import {Link} from "react-router-dom";

class Header extends React.Component {
    render() {
        return (
<div id="navbar" className="navbar navbar-default">
    <div className="navbar-container" id="navbar-container">
        <div className="navbar-header pull-left">
            <Link to="/" className="navbar-brand">
                <small>
                    <i className="fa fa-leaf" />
                    Ace Admin
                </small>
            </Link>
        </div>
        <div className="navbar-buttons navbar-header pull-right" role="navigation">
            <ul className="nav ace-nav">
                <li className="light-blue">
                    <a data-toggle="dropdown" href="#" className="dropdown-toggle">
                        <img className="nav-user-photo" src="assets/avatars/user.jpg" alt="Jason's Photo"/>
                        <span className="user-info">
                            <small>Welcome,</small>
                            Jason
                        </span>
                        <i className="ace-icon fa fa-caret-down" />
                    </a>
                    <ul className="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
                        <li>
                            <Link href="profile.html">
                                <i className="ace-icon fa fa-user" />
                                Profile
                            </Link>
                        </li>
                        <li className="divider" />
                        <li>
                            <Link href="#">
                                <i className="ace-icon fa fa-power-off" />
                                Logout
                            </Link>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</div>
        );
    }
}

export default Header;
