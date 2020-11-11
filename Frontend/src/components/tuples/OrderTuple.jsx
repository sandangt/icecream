import React from "react";
import {Link} from "react-router-dom";

class OrderTuple extends React.Component {
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
                <td>
                    <Link to={`/order/detail/${this.props.obj.id}`} className="btn btn-primary">View Detail</Link>
                </td>
            </tr>
        );
    }
}

export default OrderTuple;
