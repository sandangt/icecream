import React from "react";

import Header from "admin/components/header/Header.jsx";
import Footer from "admin/components/Footer.jsx";
import Sidebar from "admin/components/sidebar/Sidebar.jsx";
import Body from "admin/pages/Body.jsx";

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