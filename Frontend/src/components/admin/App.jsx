import React from "react";
import {Router, Route, Redirect, Switch} from "react-router-dom";

import StreamCreate from "components/streams/StreamCreate.jsx";
import StreamDelete from "components/streams/StreamDelete.jsx";
import StreamEdit from "components/streams/StreamEdit.jsx";
import StreamList from "components/streams/StreamList.jsx";
import StreamShow from "components/streams/StreamShow.jsx";
import Header from "components/Header.jsx";

import TestRoute from "components/TestComponents.jsx";

class App extends React.Component {
	render() {
		return (
<div>
    <Router history={history}>
        <body className="no-skin">
            <Header/>
            <div className="main-container" id="main-container">
                <Sidebar/>
                    <Switch>
                        <Route path="/" exact component={StreamList}/>
                        <Route path="/streams/new" exact component={StreamCreate}/>
                        <Route path="/streams/edit/:id" exact component={StreamEdit}/>
                        <Route path="/streams/delete/:id" exact component={StreamDelete}/>
                        <Route path="/streams/show/:id" exact component={StreamShow}/>
                        <Route path="/streams/:id" exact component={TestRoute} />
                    </Switch>
                <Footer/>
            </div>
        </body>
    </Router>
</div>
		);
	}
}
