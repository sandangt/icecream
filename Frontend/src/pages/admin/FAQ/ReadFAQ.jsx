import React from "react";
import {Link} from "react-router-dom";

import baseUrl from "baseUrl.js";
import authHeader from "services/authHeader.js";
import FAQTuple from "components/tuple/FAQTuple.jsx";


class ReadFAQ extends React.Component {

    constructor(props) {
        super(props);
        this.state = {users: []};
    }

    componentDidMount() {
        baseUrl.get("/faq", {headers: authHeader()})
        .then( (response) => {
            console.log(response.data)
            this.setState({users: response.data})
        })
        .catch( (error) => {
            console.log(error);
        });
    }

    tuples = () => {
        return this.state.users.map( (value, index) => {
            return <FAQTuple obj={value} key={index}/>
        });
    }
    
    render() {
        return (
<main>
        <div className="container">
            <div className="container text-center">
                <div>
                    <h1 className="h1-view">FAQ</h1>
                </div>
                <p>
                    <input
                        type="text"
                        placeholder="Search FAQ by question..."
                        name="searchtext"
                    />
                </p>
                <p>
                    <button
                        id="search button"
                        type="submit"
                        className="btn btn-primary"
                    >
                        Search
                    </button>
                </p>
                <div className="d-flex table-data">
                    <table className="table table-striped scrollTable center"
                        border={1}
                        cellSpacing={1}
                    >
                        <thead className="thead-dark">
                            <tr>
                                <th>select</th>
                                <th>ID</th>
                                <th>Question</th>
                                <th>Answer</th>
                                <th>Last modified date</th>
                                <th colSpan="2">Action</th>
                            </tr>
                        </thead>
                        <tbody id="tbody">
                            {this.tuples()}
                        </tbody>
                    </table>
                </div>
                <nav aria-label="Page navigation">
                    <ul className="pagination" id="pagination" />
                </nav>
                <input
                    type="hidden"
                    defaultValue
                    id="page"
                    name="page"
                />
                <input
                    type="hidden"
                    defaultValue
                    id="maxItemsPerPage"
                    name="maxItemsPerPage"
                />
                <div className="btn">
                    <Link className="btn btn-primary" to="/admin/faq/create">
                        Create new FAQ
                    </Link>
                </div>
            </div>
        </div>
</main>
        );
    }
}

export default ReadFAQ;
