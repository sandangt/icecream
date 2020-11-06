import React from "react";
import {Link} from "react-router-dom";

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
            currentPage: null,
            totalPages: null,
            pageLimit: 5,
            searchText: ""
        };
    }

    componentDidMount() {
      this.getData();
    }

    getData = async() => {
        await baseUrl.get(`/users?number=0&size=${this.state.pageLimit}`, {headers: authHeader()})
        .then( (response) => {  
            this.setState({
                users: Object.values(response.data)[0],
                totalRecords: parseInt(Object.keys(response.data)[0])
            })
        })
        .catch( (error) => {
            console.log(error);
        });
    }

    renderTuples = () => {
        return this.state.users.map( (value, index) => {
            return <UserTuple obj={value} key={index}/>
        });
    }

    onPageChanged = data => {
        const { currentPage, pageLimit } = data;
        this.setState({ currentPage, pageLimit });
        baseUrl.get(`/users?number=${currentPage-1}&size=${pageLimit}`, {headers: authHeader()})
        .then( (response) => {
            this.setState({
                users: Object.values(response.data)[0],
                totalRecords: parseInt(Object.keys(response.data)[0])
            })
        })
        .catch( (error) => {
            console.log(error);
        });
    } 
    
    onSearchSubmitForm = async () => {
        // e.preventDefault();
        const {searchText} = this.state;    
        console.log(searchText);
        await baseUrl.get(`/users?search=${searchText}`, {headers: authHeader()})
        .then( (response) => {
            this.setState({
                users: response.data,
                totalRecords: null
            })
          
        });
     
    }

    onSearchText = (e) => {
        // e.preventDefault();
        this.setState({searchText: e.target.value});
    }
    
    onHandlePageLimitSelection = async (e) => {
        e.preventDefault();
        this.setState({pageLimit: parseInt(e.target.value)});
    }

    render() {
        const { totalRecords, pageLimit, searchText } = this.state;
        return (
    <div className="container">
        
        <div className="text-center">
            <div>
                <h1 className="h1-view">User</h1>
            </div>
            {/* <form onSubmit={this.onSearchForm} method="get" action="#"> */}
                <p>
                    <input
                        type="text"
                        placeholder="Search user by username..."
                        // name="search"
                        name="searchText"
                        onChange={this.onSearchText}
                    />
                </p>
                <p>
                    <button
                        // id="search button"
                        // type="submit"
                        onClick={()=>this.onSearchSubmitForm()}
                        className="btn btn-primary"
                    >
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
                            <th>Last modified date</th>
                            <th>Status</th>
                            <th colSpan="2">Action</th>
                        </tr>
                    </thead>
                    <tbody id="tbody">
                        {this.renderTuples()}
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
                <h1>{searchText}</h1>
            <div className="btn">
                <Link className="btn btn-primary" to="/admin/faq/create">
                    Create new FAQ
                </Link>
            </div>
        </div>
    </div>
        );
    }
}

export default ReadUser;
