import React from "react";
import NavlistElementTitle from "./NavlistElementTitle";
import NavlistSubElement from "./NavlistSubElement";

function Forms() {
    return (
<li className>
        <NavlistElementTitle icon="menu-icon fa fa-pencil-square-o" content="Forms"/>
        <ul className="submenu">
            <NavlistSubElement content="Form Elements" url="form-elements.html"/>
            <NavlistSubElement content="Form Elements 2" url="form-elements-2.html"/>
            <NavlistSubElement content="Wizard &amp; Validation" url="form-wizard.html"/>
            <NavlistSubElement content="Wysiwyg &amp; Markdown" url="form-wizard.html"/>
            <NavlistSubElement content="Wysiwyg &amp; Markdown" url="wysiwyg.html"/>
            <NavlistSubElement content="Dropzone File Upload" url="dropzone.html"/>
        </ul>
    </li>
    );
}

export default Forms;