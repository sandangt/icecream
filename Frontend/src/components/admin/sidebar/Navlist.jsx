import React from "react";

import Dashboard from "./elements/Dashboard.jsx";
import UIAndElement from "./elements/UIAndElement.jsx";
import Table from "./elements/Table.jsx";

class Navlist extends React.Component {
    render() {
        return (
<ul className="nav nav-list">
    <Dashboard/>
    <UIAndElement/>
    <Table/>
    <li className>
        <a href="#" className="dropdown-toggle">
            <i className="menu-icon fa fa-pencil-square-o" />
            <span className="menu-text">
                {" "}
                Forms{" "}
            </span>
            <b className="arrow fa fa-angle-down" />
        </a>
        <b className="arrow" />
        <ul className="submenu">
            <li className>
                <a href="form-elements.html">
                    <i className="menu-icon fa fa-caret-right" />
                    Form Elements
                </a>
                <b className="arrow" />
            </li>
            <li className>
                <a href="form-elements-2.html">
                    <i className="menu-icon fa fa-caret-right" />
                    Form Elements 2
                </a>
                <b className="arrow" />
            </li>
            <li className>
                <a href="form-wizard.html">
                    <i className="menu-icon fa fa-caret-right" />
                    Wizard &amp; Validation
                </a>
                <b className="arrow" />
            </li>
            <li className>
                <a href="wysiwyg.html">
                    <i className="menu-icon fa fa-caret-right" />
                    Wysiwyg &amp; Markdown
                </a>
                <b className="arrow" />
            </li>
            <li className>
                <a href="dropzone.html">
                    <i className="menu-icon fa fa-caret-right" />
                    Dropzone File Upload
                </a>
                <b className="arrow" />
            </li>
        </ul>
    </li>
    <li className>
        <a href="widgets.html">
            <i className="menu-icon fa fa-list-alt" />
            <span className="menu-text">
                {" "}
                Widgets{" "}
            </span>
        </a>
        <b className="arrow" />
    </li>
    <li className>
        <a href="calendar.html">
            <i className="menu-icon fa fa-calendar" />
            <span className="menu-text">
                Calendar
                <span
                    className="badge badge-transparent tooltip-error"
                    title="2 Important Events"
                >
                    <i className="ace-icon fa fa-exclamation-triangle red bigger-130" />
                </span>
            </span>
        </a>
        <b className="arrow" />
    </li>
    <li className>
        <a href="gallery.html">
            <i className="menu-icon fa fa-picture-o" />
            <span className="menu-text">
                {" "}
                Gallery{" "}
            </span>
        </a>
        <b className="arrow" />
    </li>
    <li className>
        <a href="#" className="dropdown-toggle">
            <i className="menu-icon fa fa-tag" />
            <span className="menu-text">
                {" "}
                More Pages{" "}
            </span>
            <b className="arrow fa fa-angle-down" />
        </a>
        <b className="arrow" />
        <ul className="submenu">
            <li className>
                <a href="profile.html">
                    <i className="menu-icon fa fa-caret-right" />
                    User Profile
                </a>
                <b className="arrow" />
            </li>
            <li className>
                <a href="inbox.html">
                    <i className="menu-icon fa fa-caret-right" />
                    Inbox
                </a>
                <b className="arrow" />
            </li>
            <li className>
                <a href="pricing.html">
                    <i className="menu-icon fa fa-caret-right" />
                    Pricing Tables
                </a>
                <b className="arrow" />
            </li>
            <li className>
                <a href="invoice.html">
                    <i className="menu-icon fa fa-caret-right" />
                    Invoice
                </a>
                <b className="arrow" />
            </li>
            <li className>
                <a href="timeline.html">
                    <i className="menu-icon fa fa-caret-right" />
                    Timeline
                </a>
                <b className="arrow" />
            </li>
            <li className>
                <a href="email.html">
                    <i className="menu-icon fa fa-caret-right" />
                    Email Templates
                </a>
                <b className="arrow" />
            </li>
            <li className>
                <a href="login.html">
                    <i className="menu-icon fa fa-caret-right" />
                    Login &amp; Register
                </a>
                <b className="arrow" />
            </li>
        </ul>
    </li>
    <li className>
        <a href="#" className="dropdown-toggle">
            <i className="menu-icon fa fa-file-o" />
            <span className="menu-text">
                Other Pages
                <span className="badge badge-primary">
                    5
                </span>
            </span>
            <b className="arrow fa fa-angle-down" />
        </a>
        <b className="arrow" />
        <ul className="submenu">
            <li className>
                <a href="faq.html">
                    <i className="menu-icon fa fa-caret-right" />
                    FAQ
                </a>
                <b className="arrow" />
            </li>
            <li className>
                <a href="error-404.html">
                    <i className="menu-icon fa fa-caret-right" />
                    Error 404
                </a>
                <b className="arrow" />
            </li>
            <li className>
                <a href="error-500.html">
                    <i className="menu-icon fa fa-caret-right" />
                    Error 500
                </a>
                <b className="arrow" />
            </li>
            <li className>
                <a href="grid.html">
                    <i className="menu-icon fa fa-caret-right" />
                    Grid
                </a>
                <b className="arrow" />
            </li>
            <li className>
                <a href="blank.html">
                    <i className="menu-icon fa fa-caret-right" />
                    Blank Page
                </a>
                <b className="arrow" />
            </li>
        </ul>
    </li>
</ul>
        );
    }
}

export default Navlist;