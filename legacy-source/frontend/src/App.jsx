import React from "react";
import {Router, Route, Switch, Redirect} from "react-router-dom";

import Header from "components/header/Header.jsx";
import Footer from "components/footer/Footer.jsx";

import Home from "pages/Home.jsx";
import Login from "pages/anonimous/Login.jsx";
import Signup from "pages/anonimous/Signup.jsx";
import Profile from "pages/logined/Profile.jsx";
import AboutUs from "pages/AboutUs.jsx";
import Contact from "pages/Contact.jsx";
import UpdatePassword from "pages/logined/UpdatePassword.jsx";
import NotFound from "pages/NotFound.jsx";

import ReadFAQ from "pages/admin/FAQ/ReadFAQ.jsx";
import CreateFAQ from "pages/admin/FAQ/CreateFAQ.jsx";
import UpdateFAQ from "pages/admin/FAQ/UpdateFAQ.jsx";

import ReadUser from "pages/admin/users/ReadUser.jsx";
import UpdateUser from "pages/admin/users/UpdateUser.jsx";

import ReadProduct from "pages/admin/products/ReadProduct.jsx";
import CreateProduct from "pages/admin/products/CreateProduct.jsx";
import UpdateProduct from "pages/admin/products/UpdateProduct.jsx";

import ReadCategory from "pages/admin/categories/ReadCategory.jsx";
import CreateCategory from "pages/admin/categories/CreateCategory.jsx";
import UpdateCategory from "pages/admin/categories/UpdateCategory.jsx";

import ReadOrder from "pages/admin/orders/ReadOrder.jsx";

import ReadFeedback from "pages/admin/feedbacks/ReadFeedback.jsx";

import Shop from "pages/user/Shop.jsx";
import ProductDetail from "pages/user/ProductDetail.jsx";
import Order from "pages/user/Order.jsx";
import OrderHistory from "pages/user/OrderHistory.jsx";
import OrderDetail from "pages/user/OrderDetail.jsx";
import UpdateFeedback from "pages/user/UpdateFeedback.jsx";
import FAQ from "pages/user/FAQ.jsx";

import history from "history.js";

class App extends React.Component {
    render() {
        return (
<div className="container">
    <Router history={history}>
        <div>
            <Route>
                <Header/>
                <Switch>
                    <Route exact path="/home" component={Home}/>
                    <Route exact path="/profile" component={Profile}/>
                    <Route exact path="/login" component={Login}/>
                    <Route exact path="/signup" component={Signup}/>
                    <Route exact path="/about" component={AboutUs}/>
                    <Route exact path="/contact" component={Contact}/>
                    <Route exact path="/update-password" component={UpdatePassword}/>
                    <Route exact path="/">
                        <Redirect to="/home" />
                    </Route>
                    
                    <Route exact path="/admin/users" component={ReadUser}/>
                    <Route exact path="/admin/users/update/:id" component={UpdateUser}/>

                    <Route exact path="/admin/faq" component={ReadFAQ}/>
                    <Route exact path="/admin/faq/create" component={CreateFAQ}/>
                    <Route exact path="/admin/faq/update/:id" component={UpdateFAQ}/>

                    <Route exact path="/admin/products" component={ReadProduct}/>
                    <Route exact path="/admin/products/create" component={CreateProduct}/>
                    <Route exact path="/admin/products/update/:id" component={UpdateProduct}/>

                    <Route exact path="/admin/categories" component={ReadCategory}/>
                    <Route exact path="/admin/categories/create" component={CreateCategory}/>
                    <Route exact path="/admin/categories/update/:id" component={UpdateCategory}/>
                    
                    <Route exact path="/admin/orders" component={ReadOrder}/>

                    <Route exact path="/admin/feedbacks" component={ReadFeedback}/>

                    <Route exact path="/shop" component={Shop}/>
                    <Route exact path="/product/:id" component={ProductDetail}/>
                    <Route exact path="/feedback/:id" component={UpdateFeedback}/>
                    <Route exact path="/order" component={Order}/>
                    <Route exact path="/order/detail/:id" component={OrderDetail}/>
                    <Route exact path="/history" component={OrderHistory}/>
                    <Route exact path="/faq" component={FAQ}/>

                    <Route exact path="/error" component={NotFound}/>
                    <Route>
                        <Redirect to="/error"/>
                    </Route>
                </Switch>
                <Footer/>
                </Route>
        </div>
    </Router>
</div>
        );
    }
}

export default App;