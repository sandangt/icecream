import React from "react";

import NavlistElement from "./elements/NavlistElement.jsx";

class Navlist extends React.Component {
    render() {
        return (
<ul className="nav nav-list">
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
</ul>
        );
    }
}

export default Navlist;