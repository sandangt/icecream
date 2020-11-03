import React from "react";
import NavlistElementTitle from "./NavlistElementTitle";
import NavlistSubElement from "./NavlistSubElement";

function NavlistElement(props) {
    return (
<li className={props.isActive}>
    <a href={link} className="dropdown-toggle">
        <i className={props.icon}/>
        <span className="menu-text">
            {props.content}
        </span>
        <b className="arrow fa fa-angle-down" />
    </a>
    <b className="arrow" />
    <ul className="submenu">
        {props.subElement.map( element => {
            return <NavlistSubElement url={element.url} content={element.content}/> ;   
        })}
    </ul>
</li>
    );
}

export default NavlistElement;