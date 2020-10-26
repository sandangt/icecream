import React from "react";
import NavlistElementTitle from "./NavlistElementTitle";
import NavlistSubElement from "./NavlistSubElement";

function Table() {
    return (
<li className="active open">
    <NavlistElementTitle icon="menu-icon fa fa-list" content="Tables"/>
    <ul className="submenu">
        <NavlistSubElement url="tables.html" content="Users" isActive="active"/>
        <NavlistSubElement url="jqgrid.html" content="Products"/>
        <NavlistSubElement url="jqgrid.html" content="Orders"/>
        <NavlistSubElement url="jqgrid.html" content="Categories"/>
        <NavlistSubElement url="jqgrid.html" content="FAQ"/>
        <NavlistSubElement url="jqgrid.html" content="Feedbacks"/>
    </ul>
</li>
    );
}

export default Table;