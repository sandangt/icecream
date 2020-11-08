import React from "react";
import {Link} from "react-router-dom";
import authHeader from "services/authHeader.js";
import baseUrl from "baseUrl.js";

class UserTuple extends React.Component {
    delete = () => {
        baseUrl.delete(`/users/${this.props.obj.id}`, {headers: authHeader()})
            .then(console.log('Deleted'))
            .catch(err => console.log(err));
        window.location.reload();
    }
    renderActionButton = ()  => {
        if (this.props.obj.id !== this.props.currentUserId) {
            return (
        <div>
            <td>
                <Link to={`/admin/users/update/${this.props.obj.id}`} className="btn btn-primary">Edit</Link>
            </td>
            <td>
                <button onClick={this.delete} className="btn btn-danger">Delete</button>
            </td> 
        </div>
            );
        }
        return null;
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
        {this.props.obj.username}
    </td>
    <td>
        {this.props.obj.email}
    </td>
    <td>
        {this.props.obj.modifiedDate}
    </td>
    <td>
        {this.props.obj.status}
    </td>
    {this.renderActionButton()}
</tr>
        );
    }
}

export default UserTuple;