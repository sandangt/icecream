import React from "react";

function NavlistElementTitle(props) {
    let link = (props.url === undefined ? "#" : props.url);
    return (
<React.Fragment>
    <a href={link} className="dropdown-toggle">
        <i className={props.icon}/>
        <span className="menu-text">
            {props.content}
        </span>
        <b className="arrow fa fa-angle-down" />
    </a>
    <b className="arrow" />
</React.Fragment>
    );
}

export default NavlistElementTitle;