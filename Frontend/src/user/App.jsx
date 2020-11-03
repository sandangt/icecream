import React from "react";
import {Router, Route, Redirect, Switch} from "react-router-dom";

import Header from "user/components/Header.jsx";
import Footer from "user/components/Footer.jsx";

import Home from "user/pages/Home.jsx";
import Login from "user/pages/Login.jsx";
import Signup from "user/pages/Signup.jsx";
import Profile from "user/pages/Profile.jsx";

import history from "./history.js";

class App extends React.Component {
    render() {
        console.log(history);
        return (
<div>
    <Router history={history}>
        <div>
            <Header/>
            <Switch>
                <Route path="/" exact component={Home}/>
                <Route path="/profile" exact component={Profile}/>
                <Route path="/login" exact component={Login}/>
                <Route path="/signup" exact component={Signup}/>
            </Switch>
            <Footer/>
        </div>
    </Router>
</div>
        );
    }
}


export default App;