import React from "react";
import NavlistElementTitle from "./NavlistElementTitle";
import NavlistSubElement from "./NavlistSubElement";

function UIAndElement() {
    return (
<li>
    <NavlistElementTitle icon="menu-icon fa fa-desktop" content="UI &amp; Elements"/>
    <ul className="submenu">
        <NavlistSubElement url="typography.html" content="Typography"/>
        <NavlistSubElement url="elements.html" content="Elements"/>
        <NavlistSubElement url="buttons.html" content="Buttons &amp; Icons"/>
        <NavlistSubElement url="content-slider.html" content="Content Sliders"/>
        <NavlistSubElement url="treeview.html" content="Treeview"/>
        <NavlistSubElement url="jquery-ui.html" content="jQuery UI"/>
        <NavlistSubElement url="nestable-list.html" content="Nestable Lists"/>
    </ul>
</li>
    );
}

export default UIAndElement;