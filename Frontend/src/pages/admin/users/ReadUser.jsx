import React from "react";
import {Redirect} from "react-router-dom";
import {connect} from "react-redux";

import baseUrl from "baseUrl.js";
import authHeader from "services/authHeader.js";
import UserTuple from "components/tuples/UserTuple.jsx";
import Pagination from "components/pagination/Pagination.jsx";


class ReadUser extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            users: [],
            totalRecords: null,
            currentPage: 1,
            totalPages: null,
            pageLimit: 5,
            searchText: ""
        };
    }

    componentDidMount() {
      this.getDataByPage(this.state.currentPage, this.state.pageLimit);
    }

    getDataByPage = async (currentPage, pageLimit) => {
        await baseUrl.get(`/users?page=${currentPage}&offset=${pageLimit}`, {headers: authHeader()})
        .then( (response) => {  
            this.setState({
                users: response.data.itemList,
                totalRecords: response.data.totalItems
            })
        })
        .catch( (error) => {
            console.log(error);
        });
    }

    getSearchData = async(searchText) => {
        await baseUrl.get(`/users?search=${searchText}`, {headers: authHeader()})
        .then( (response) => {
            this.setState({
                users: response.data,
                totalRecords: null
            })
        })
        .catch( (error) => {
            console.log(error);
        });
    }

    renderTuples = (currentUserId) => {
        return this.state.users.map( (value, index) => {
            return <UserTuple obj={value} key={index} currentUserId={currentUserId}/>
        });
    }

    onPageChanged = data => {
        const { currentPage, pageLimit } = data;
        this.setState({ currentPage, pageLimit });
        this.getDataByPage(currentPage, pageLimit);
    } 
    
    onSearchButton = async (e) => {
        e.preventDefault();
        const {searchText} = this.state;
        if (searchText === "") {
            this.getDataByPage(1, this.state.pageLimit);
        }   
        else {
            this.getSearchData(searchText);
        }
    }

    onSearchBar = (e) => {
        e.preventDefault();
        this.setState({searchText: e.target.value});
    }
    
    // onHandlePageLimitSelection = async (e) => {
    //     e.preventDefault();
    //     this.setState({pageLimit: parseInt(e.target.value)});
    // }

    render() {
        if (!this.props.isLoggedIn || !this.props.user.roles.includes("ROLE_ADMIN")) {
            return <Redirect to="/error"/>;
        }

        const { totalRecords, pageLimit} = this.state;
        return (
    <div>
        <div className="text-center">
            <div>
                <h1 className="h1-view">User</h1>
            </div>
                <p>
                    <input
                        type="text"
                        placeholder="Search user by username..."
                        name="searchText"
                        onChange={this.onSearchBar}
                    />
                </p>
                <p>
                    <button onClick={this.onSearchButton} className="btn btn-primary">
                        Search
                    </button>
                </p>
            {/* </form> */}

            {/* <div className="d-flex flex-row align-items-center">
                Page size:
                <select defaultValue={pageLimit} onChange={this.onHandlePageLimitSelection}>
                    <option value="5">5</option>
                    <option value="10">10</option>
                    <option value="15">15</option>
                </select>
            </div><br/> */}

            <div className="d-flex table-data">
                <table className="table table-striped scrollTable center"
                    border={1}
                    cellSpacing={1}
                >
                    <thead className="thead-dark">
                        <tr>
                            {/* <th>select</th> */}
                            <th>ID</th>
                            <th>Username</th>
                            <th>Email</th>
                            <th>Roles</th>
                            <th>Last modified date</th>
                            <th>Status</th>
                            <th colSpan="2">Action</th>
                        </tr>
                    </thead>
                    <tbody id="tbody">
                        {this.renderTuples(this.props.user.id)}
                    </tbody>
                </table>
            </div>
            <div className="d-flex flex-row py-4 align-items-center">
                   {totalRecords && <Pagination 
                   totalRecords={totalRecords} 
                   pageLimit={pageLimit} 
                   pageNeighbours={1} 
                   onPageChanged={this.onPageChanged}
                    /> }
            </div>
            {/* <div className="btn">
                <Link className="btn btn-primary" to="/admin/users/create">
                    Create new User
                </Link>
            </div> */}
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
export default connect(mapStateToProps)(ReadUser);
