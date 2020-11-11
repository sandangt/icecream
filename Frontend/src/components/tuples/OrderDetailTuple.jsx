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
                {/* <td>
                    <input type="checkbox" />
                </td> */}
                <td>
                    {this.props.obj.id}
                </td>
                <td>
                    <button></button>{this.props.obj.quantity}
                </td>
                <td>
                    {this.props.obj.name}
                </td>
                <td>
                    {this.props.obj.price}
                </td>
                <td>
                    {this.props.obj.description}
                </td>
                <td>
                    <button onClick={this.delete} className="btn btn-danger">Delete</button>
                </td>
            </tr>
        );
    }
}

export default OrderDetailTuple;