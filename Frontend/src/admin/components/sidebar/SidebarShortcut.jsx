import React from "react";

function SidebarShortcut() {
    return (
<div className="sidebar-shortcuts" id="sidebar-shortcuts">
    <div className="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
        <button className="btn btn-success">
            <i className="ace-icon fa fa-signal" />
        </button>
        <button className="btn btn-info">
            <i className="ace-icon fa fa-pencil" />
        </button>
        <button className="btn btn-warning">
            <i className="ace-icon fa fa-users" />
        </button>
        <button className="btn btn-danger">
            <i className="ace-icon fa fa-cogs" />
        </button>
    </div>
    <div className="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
        <span className="btn btn-success" />
        <span className="btn btn-info" />
        <span className="btn btn-warning" />
        <span className="btn btn-danger" />
    </div>
</div>
    );
}

export default SidebarShortcut;