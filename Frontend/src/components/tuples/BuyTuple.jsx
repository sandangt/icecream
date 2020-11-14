import React from "react";
import {removeItemFromCartService} from "services/cartService.js";

class BuyTuple extends React.Component {
    delete = () => {
        removeItemFromCartService(this.props.obj);
        window.location.reload();
        // console.log(this.props.obj);
    }
    render() {
        const {increaseQuantity, decreaseQuantity, obj} = this.props;
        return (
            <tr>
                <td>
                    {this.props.obj.id}
                </td>
                <td>
                    {this.props.obj.name}
                </td>
                <td>
                    <button onClick={() => increaseQuantity({ id:obj.id, quantity:obj.quantity+=1 })}>+</button>
                        {this.props.obj.quantity}
                    <button onClick={() => {
                        if (obj.quantity > 1)
                            decreaseQuantity({ id: obj.id, quantity:obj.quantity-=1 });
                    }}>-</button>
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

export default BuyTuple;
