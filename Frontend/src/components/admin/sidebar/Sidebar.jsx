import React from "react";

import SidebarShortcut from "components/admin/sidebar/SidebarShortcut.jsx";
import Navlist from "components/admin/sidebar/Navlist.jsx";
import SidebarCollapse from "components/admin/sidebar/SidebarCollapse.jsx";

class Sidebar extends React.Component {
    render() {
        return (
<div id="sidebar" className="sidebar responsive">
    <SidebarShortcut/>
    <Navlist/>
    <SidebarCollapse/>
</div>
        );
    }
}

export default Sidebar;