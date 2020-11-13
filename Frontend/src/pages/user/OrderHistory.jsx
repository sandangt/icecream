import React from "react";
import {Redirect} from "react-router-dom";
import {connect} from "react-redux";

import baseUrl from "baseUrl.js";
import authHeader from "services/authHeader.js";
import OrderTuple from "components/tuples/OrderTuple.jsx";

class OrderHistory extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            orders: []
        };
    }

    componentDidMount() {
        if (this.props.isLoggedIn)
            this.getData();
    }

    
    getData = async () => {
        await baseUrl.get(`/users/${this.props.user.id}/orders`, {headers: authHeader()})
        .then( (response) => {  
            this.setState({
                orders: response.data
            })
        })
        .catch( (error) => {
            console.log(error);
        });
    }


    renderTuples = () => {
        return this.state.orders.map( (value, index) => {
            return <OrderTuple obj={value} key={index}/>
        });
    }

    render() {
        if (!this.props.isLoggedIn || !this.props.user.roles.includes("ROLE_USER")) {
            return <Redirect to="/error"/>;
        }
        return (
    <div>
        <div className="text-center">
            <div>
                <h1 className="h1-view">Order history</h1>
            </div>
            <div className="d-flex table-data">
                <table className="table table-striped scrollTable center"
                    border={1}
                    cellSpacing={1}
                >
                    <thead className="thead-dark">
                        <tr>
                            <th>ID</th>
                            <th>Code name</th>
                            <th>Payment method</th>
                            <th>Username</th>
                            <th>Total price</th>
                            <th>Last modified date</th>
                            <th colSpan="1">Action</th>
                        </tr>
                    </thead>
                    <tbody id="tbody">
                        {this.renderTuples()}
                    </tbody>
                </table>
            </div>
        </div>
    </div>
        );
    }
}

const mapStateToProps = (state) => {
    return {
        isLoggedIn: state.auth.isLoggedIn,
        user: state.auth.user
    };
}
export default connect(mapStateToProps)(OrderHistory);

