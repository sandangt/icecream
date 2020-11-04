import React from "react";

function Breadcrum() {
    return (
<div className="breadcrumbs" id="breadcrumbs">
    <ul className="breadcrumb">
        <li>
            <i className="ace-icon fa fa-home home-icon" />
            <a href="#">Home</a>
        </li>
        <li>
            <a href="#">Tables</a>
        </li>
        <li className="active">
            Simple &amp; Dynamic
        </li>
    </ul>
    <div className="nav-search" id="nav-search">
        <form className="form-search">
            <span className="input-icon">
                <input
                    type="text"
                    placeholder="Search ..."
                    className="nav-search-input"
                    id="nav-search-input"
                    autoComplete="off"
                />
                <i className="ace-icon fa fa-search nav-search-icon" />
            </span>
        </form>
    </div>
</div>
    );
}

export default Breadcrum;