import React from "react";
import authHeader from "services/authHeader.js";
import baseUrl from "baseUrl.js";

class FeedbackTuple extends React.Component {
    delete = () => {
        baseUrl.delete(`/feedbacks/${this.props.obj.id}`, {headers: authHeader()})
            .then(console.log('Deleted'))
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
                    {this.props.obj.title}
                </td>
                <td>
                    {this.props.obj.content}
                </td>
                <td>
                    {this.props.obj.user.username}
                </td>
                <td>
                    {this.props.obj.productName}
                </td>
                <td>
                    {this.props.obj.modifiedDate}
                </td>
                <td>
                    <button onClick={this.delete} className="btn btn-danger">Delete</button>
                </td>
            </tr>
        );
    }
}

export default FeedbackTuple;