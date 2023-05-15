import React from "react";
import {Redirect} from "react-router-dom";
import {connect} from "react-redux";

import baseUrl from "baseUrl.js";
import authHeader from "services/authHeader.js";
import OrderDetailTuple from "components/tuples/OrderDetailTuple.jsx";

class OrderDetail extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            order_details: []
        };
    }

    componentDidMount() {
        this.getData();
    }
    
    getData = async () => {
        await baseUrl.get(`/orders/${this.props.match.params.id}/order-details`, {headers: authHeader()})
        .then( (response) => {  
            this.setState({
                order_details: response.data
            })
        })
        .catch( (error) => {
            console.log(error);
        });
    }


    renderTuples = () => {
        return this.state.order_details.map( (value, index) => {
            return <OrderDetailTuple obj={value} key={index}/>
        });
    }

    render() {
        if (!this.props.isLoggedIn) {
            return <Redirect to="/error"/>;
        }
        console.log(this.state.order_details);
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
                            <th>Order's code name</th>
                            <th>Product's name</th>
                            <th>Product's description</th>
                            <th>Quantity</th>
                            <th>Price</th>
                            <th>Last modified date</th>
                        </tr>
                    </thead>
                    <tbody id="tbody">
                        {this.renderTuples()}
                    </tbody>
                </table>
            </div>
            <button className="btn btn-primary" onClick={(e) => {
                e.preventDefault();
                if (this.props.user.roles.includes("ROLE_ADMIN")) {
                    this.props.history.push("/admin/orders");
                }
                else {
                    this.props.history.push("/history");
                }
            }}>
                Back
            </button>
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
export default connect(mapStateToProps)(OrderDetail);

