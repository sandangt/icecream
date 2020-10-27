import React from "react";

import Header from "components/admin/header/Header.jsx";
import Footer from "components/admin/Footer.jsx";
import Body from "components/admin/body/Body.jsx";
import Sidebar from "components/admin/sidebar/Sidebar.jsx";

class Home extends React.Component {
    render() {
        return (
<div>
    <body className="no-skin">
        <Header/>
        <div className="main-container" id="main-container">
            <Sidebar/>
            <Body/>
            <Footer/>
        </div>
    </body>
</div>
        );
    }
}

export default Home;