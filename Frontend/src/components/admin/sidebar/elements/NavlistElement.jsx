import React from "react";
import NavlistElementTitle from "./NavlistElementTitle";
import NavlistSubElement from "./NavlistSubElement";

function NavlistElement(props) {
    return (
<li className={props.isActive}>
    <NavlistElementTitle icon={props.icon} content={props.content}/>
    <ul className="submenu">
        {props.subElement.map( element => {
            return <NavlistSubElement url={element.url} content={element.content}/> ;   
        })}
    </ul>
</li>
    );
}

export default NavlistElement;