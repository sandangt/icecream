import React from "react";

import Navlist from "admin/components/sidebar/Navlist.jsx";
import SidebarCollapse from "admin/components/sidebar/SidebarCollapse.jsx";

class Sidebar extends React.Component {
    render() {
        return (
<div id="sidebar" className="sidebar responsive">
    <Navlist/>
    <SidebarCollapse/>
</div>
        );
    }
}

export default Sidebar;