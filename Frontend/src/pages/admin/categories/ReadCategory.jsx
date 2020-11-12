import React from "react";
import {Link, Redirect} from "react-router-dom";
import {connect} from "react-redux";

import baseUrl from "baseUrl.js";
import authHeader from "services/authHeader.js";
import CategoryTuple from "components/tuples/CategoryTuple.jsx";

class ReadCategory extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            categories: [],
            searchText: ""
        };
    }

    componentDidMount() {
        this.getData();
    }

    getData = async () => {
        await baseUrl.get(`/categories`, {headers: authHeader()})
        .then( (response) => {  
            this.setState({
                categories: response.data
            })
        })
        .catch( (error) => {
            console.log(error);
        });
    }

    getSearchData = async (searchText) => {
        await baseUrl.get(`/categories?search=${searchText}`, {headers: authHeader()})
        .then( (response) => {
            this.setState({
                categories: response.data
            })
        })
        .catch( (error) => {
            console.log(error);
        });
    }

    onSearchButton = (e) => {
        e.preventDefault();
        const {searchText} = this.state;
        if (searchText === "") {
            this.getData();
        }   
        else {
            this.getSearchData(searchText);
        }
    }

    renderTuples = () => {
        return this.state.categories.map( (value, index) => {
            return <CategoryTuple obj={value} key={index}/>
        });
    }
    
    render() {
        if (!this.props.isLoggedIn || !this.props.user.roles.includes("ROLE_ADMIN")) {
            return <Redirect to="/error"/>
        }
        return (
    <div>
        <div className="text-center">
            <div>
                <h1 className="h1-view">Category</h1>
            </div>
            <p>
                <input
                    type="text"
                    placeholder="Search Category by name..."
                    name="searchtext"
                    onChange={(e) => {
                        e.preventDefault();
                        this.setState({searchText: e.target.value});
                    }}
                />
            </p>
            <p>
                <button
                    id="search button"
                    type="submit"
                    className="btn btn-primary"
                    onClick={this.onSearchButton}
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
                            <th>ID</th>
                            <th>Category</th>
                            <th>Last modified date</th>
                            <th colSpan="2">Action</th>
                        </tr>
                    </thead>
                    <tbody id="tbody">
                        {this.renderTuples()}
                    </tbody>
                </table>
            </div>
            
            <div className="btn">
                <Link className="btn btn-primary" to="/admin/categories/create">
                    Create new Category
                </Link>
            </div>
        </div>
    </div>
        );
    }
}

const mapStateToProps = (state) => {
    return {
        isLoggedIn: state.auth.isLoggedIn,
        user: state.auth.user
    };
}
export default connect(mapStateToProps)(ReadCategory);

