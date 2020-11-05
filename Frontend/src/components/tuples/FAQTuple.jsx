import React from "react";
import {Link} from "react-router-dom";
import authHeader from "services/authHeader.js";
import baseUrl from "baseUrl.js";

class FAQTuple extends React.Component {
    constructor(props) {
        super(props);
    }
    delete = () => {
        baseUrl.delete(`/faq/${this.props.obj.id}`, {headers: authHeader()})
            .then(console.log('Deleted'))
            .catch(err => console.log(err));
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
                    {this.props.obj.question}
                </td>
                <td>
                    {this.props.obj.answer}
                </td>
                <td>
                    {this.props.obj.modifiedDate}
                </td>
                <td>
                    <Link to={`/admin/faq/update/${this.props.obj.id}`} className="btn btn-primary">Edit</Link>
                </td>
                <td>
                    <button onClick={this.delete} className="btn btn-danger">Delete</button>
                </td>
            </tr>
        );
    }
}

export default FAQTuple;