import React from "react";
import {Router, Route, Redirect, Switch} from "react-router-dom";

import Header from "components/header/Header.jsx";
import Footer from "components/footer/Footer.jsx";

import Home from "pages/Home.jsx";
import Login from "pages/Login.jsx";
import Signup from "pages/Signup.jsx";
import Profile from "pages/Profile.jsx";

import ReadFAQ from "pages/admin/FAQ/ReadFAQ.jsx";
import CreateFAQ from "pages/admin/FAQ/CreateFAQ.jsx";
import UpdateFAQ from "pages/admin/FAQ/UpdateFAQ.jsx";

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
                <Route exact path="/admin/faq" component={ReadFAQ}/>
                <Route exact path="/admin/faq/create" component={CreateFAQ}/>
                <Route exact path="/admin/faq/update/:id" component={UpdateFAQ}/>
            </Switch>
            <Footer/>
        </div>
    </Router>
</div>
        );
    }
}

export default App;