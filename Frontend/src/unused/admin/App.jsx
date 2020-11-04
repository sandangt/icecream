import React from "react";
import {BrowserRouter, Route, Redirect, Switch} from "react-router-dom";

import Header from "admin/components/Header.jsx";
import Footer from "admin/components/Footer.jsx";
import Sidebar from "admin/components/sidebar/Sidebar.jsx";
import Body from "admin/pages/Body.jsx";

class App extends React.Component {
    render() {
        return (
<div>
    <BrowserRouter>
        <body className="no-skin">
            <Header/>
            <div className="main-container" id="main-container">
                <Sidebar/>
                    <Switch>
                        <Route path="/" exact component={Body}/>
                    </Switch>
                <Footer/>
            </div>
        </body>
    </BrowserRouter>
</div>
        );
    }
}

export default App;