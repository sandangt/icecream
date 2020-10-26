import React from "react";

import Dashboard from "./elements/Dashboard.jsx";
import NavlistElement from "./elements/NavlistElement.jsx";

class Navlist extends React.Component {
    render() {
        return (
<ul className="nav nav-list">
    <Dashboard/>
    {/*UI & Element*/}
    <NavlistElement icon="menu-icon fa fa-desktop" content="UI &amp; Elements"
        subElement={[{
            url: "typography.html",
            content: "Typography"
        },{
            url: "elements.html",
            content: "Elements"
        },{
            url: "buttons.html",
            content: "Buttons & Icons"
        },{
            url: "content-slider.html",
            content: "Content Sliders"
        },{
            url: "treeview.html",
            content: "Treeview"
        }]}
    />
    {/*Table*/}
    <NavlistElement icon="menu-icon fa fa-list" content="Tables" isActive="Active open"
        subElement={[{
            url: "tables.html",
            content: "Users"
        },{
            url: "jqgrid.html",
            content: "Products"
        },{
            url: "jqgrid.html",
            content: "Orders"
        },{
            url: "jqgrid.html",
            content: "Categories"
        },{
            url: "jqgrid.html",
            content: "FAQ"
        },{
            url: "jqgrid.html",
            content: "Feedbacks"
        }]}
    />
    {/*Form*/}
    <NavlistElement icon="menu-icon fa fa-pencil-square-o" content="Forms" 
        subElement={[{
            url: "form-elements.html",
            content: "Form Elements"
        },{
            url: "jqgrid.html",
            content: "Products"
        },{
            url: "jqgrid.html",
            content: "Orders"
        },{
            url: "jqgrid.html",
            content: "Wizard &amp; Validation"
        },{
            url: "jqgrid.html",
            content: "Wysiwyg &amp; Markdown"
        },{
            url: "jqgrid.html",
            content: "Feedbacks"
        }]}
    />
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