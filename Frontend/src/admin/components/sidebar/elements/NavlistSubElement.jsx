import React from "react";

function NavlistSubElement(props) {
    return (
<li className={props.isActive}>
    <a href={props.url}>
        <i className="menu-icon fa fa-caret-right" />
        {props.content}
    </a>
    <b className="arrow"/>
</li>
    );
}

export default NavlistSubElement;