import React from "react";
import {Link} from "react-router-dom";

import baseUrl from "baseUrl.js";
import authHeader from "services/authHeader.js";

class ProductTuple extends React.Component {
    delete = () => {
        baseUrl.delete(`/products/${this.props.obj.id}`, {headers: authHeader()})
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
        {this.props.obj.name}
    </td>
    <td>
        {this.props.obj.price}
    </td>
    <td>
        {this.props.obj.description}
    </td>
    <td>
        {this.props.obj.categoryName}
    </td>
    <td>
        {this.props.obj.status}
    </td>
    <td>
        {this.props.obj.modifiedDate}
    </td>
    <td>
        <Link to={`/admin/products/update/${this.props.obj.id}`} className="btn btn-primary">Edit</Link>
    </td>
    <td>
        <button onClick={this.delete} className="btn btn-danger">Delete</button>
    </td> 
</tr>
        );
    }
}
export default ProductTuple;