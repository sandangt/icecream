import React from "react";

import {Link} from "react-router-dom"

class OrderDetailTuple extends React.Component {
    render() {
        console.log(this.props);
        return (
            <tr>
                <td>
                    {this.props.obj.id}
                </td>
                <td>
                    {this.props.obj.orderCode}
                </td>
                <td>
                    {this.props.obj.product.name}
                </td>
                <td>
                    {this.props.obj.product.description}
                </td>
                <td>
                    {this.props.obj.quantity}
                </td>
                <td>
                    {this.props.obj.totalPrice}
                </td>
                <td>
                    {this.props.obj.modifiedDate}
                </td>
            </tr>
        );
    }
}

export default OrderDetailTuple;
