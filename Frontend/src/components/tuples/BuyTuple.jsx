import React from "react";
import {removeItemFromCartService} from "services/cartService.js";

class BuyTuple extends React.Component {
    delete = () => {
        removeItemFromCartService(this.props.obj);
        window.location.reload();
    }
    render() {
        return (
            <tr>
                <td>
                    {this.props.obj.id}
                </td>
                {/* <td>
                    <button onClick={() => onClickCheck({id:obj.id,data:obj.quantity+=1})}>+</button>
                        {this.props.obj.quantity}
                    <button onClick={()=> this.props.obj.quantity-=1}>-</button>
                </td> */}
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

export default BuyTuple;
