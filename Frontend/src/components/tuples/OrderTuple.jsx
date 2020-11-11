import React from "react";
import {Link} from "react-router-dom";
import {removeItemFromCartService} from "services/cartService.js";

class OrderDetailTuple extends React.Component {
    delete = () => {
        // baseUrl.delete(`/faq/${this.props.obj.id}`, {headers: authHeader()})
        //     .then(console.log('Deleted'))
        //     .catch(err => console.log(err));
        removeItemFromCartService(this.props.obj);
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
                    {this.props.obj.totalPrice}
                </td>
                <td>
                    {this.props.obj.modifiedDate}
                </td>
                <td>
                    <button onClick={this.delete} className="btn btn-primary">View Detail</button>
                </td>
            </tr>
        );
    }
}

export default OrderDetailTuple;
