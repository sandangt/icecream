import React from "react";

import Header from "components/admin/header/Header.jsx";
import Footer from "components/admin/Footer.jsx";
import Body from "components/admin/Body.jsx";
    

class Home extends React.Component {
    render() {
        return (
<div>
    <Header/>
    <Body/>
    <Footer/>
</div>
        );
    }
}

export default Home;