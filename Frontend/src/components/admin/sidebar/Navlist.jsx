import React from "react";

import Dashboard from "./elements/Dashboard.jsx";
import NavlistElement from "./elements/NavlistElement.jsx";

class Navlist extends React.Component {
    render() {
        return (
<ul className="nav nav-list">
    {/*<Dashboard/>*/}
    {/*UI & Element
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
    */}
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
    {/*Form
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
    */}
</ul>
        );
    }
}

export default Navlist;