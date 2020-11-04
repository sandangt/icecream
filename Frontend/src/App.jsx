import React from "react";
import {Router, Route, Redirect, Switch} from "react-router-dom";

import Header from "components/header/Header.jsx";
import Footer from "components/footer/Footer.jsx";

import Home from "pages/Home.jsx";
import Login from "pages/Login.jsx";
import Signup from "pages/Signup.jsx";
import Profile from "pages/Profile.jsx";


import history from "history.js";

class App extends React.Component {
    render() {
        return (
<div>
    <Router history={history}>
        <div>
            <Header/>
            <Switch>
                <Route exact path="/home" component={Home}/>
                <Route exact path="/profile" component={Profile}/>
                <Route exact path="/login" component={Login}/>
                <Route exact path="/signup" component={Signup}/>
                <Route exact path="/">
                    <Redirect to="/home" />
                </Route>
            </Switch>
            <Footer/>
        </div>
    </Router>
</div>
        );
    }
}

export default App;