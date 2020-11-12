import React from "react";
import {Link} from "react-router-dom";
import {connect} from "react-redux";

import authHeader from "services/authHeader.js";
import baseUrl from "baseUrl.js";

class OrderTuple extends React.Component {
    delete = () => {
        baseUrl.delete(`/orders/${this.props.obj.id}`, {headers: authHeader()})
            .then(() => console.log('Deleted'))
            .catch(err => console.log(err));
        window.location.reload();
    }
    render() {
        return (
            <tr>
                <td>
                    {this.props.obj.id}
                </td>
                <td>
                    {this.props.obj.code}
                </td>
                <td>
                    {this.props.obj.paymentMethod}
                </td>
                <td>
                    {this.props.obj.user.username}
                </td>
                <td>
                    {this.props.obj.totalPrice.toFixed(3)}
                </td>
                <td>
                    {this.props.obj.modifiedDate}
                </td>
                { this.props.user.roles.includes("ROLE_ADMIN") ? 
                (<td>
                    <button className="btn btn-danger" onClick={this.delete}>Delete</button>
                </td>) : null }                
                <td>
                    <Link to={`/order/detail/${this.props.obj.id}`} className="btn btn-primary">Detail</Link>
                </td>
            </tr>
        );
    }
}

const mapStateToProps = (state) => {
    return {
        isLoggedIn: state.auth.isLoggedIn,
        user: state.auth.user
    }
}
export default connect(mapStateToProps)(OrderTuple);
