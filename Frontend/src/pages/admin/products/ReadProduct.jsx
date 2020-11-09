import React from "react";
import {Link, Redirect} from "react-router-dom";
import {connect} from "react-redux";

import baseUrl from "baseUrl.js";
import authHeader from "services/authHeader.js";
import Pagination from "components/pagination/Pagination.jsx";

import ProductTuple from "components/tuples/ProductTuple.jsx";

class ReadProduct extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            products: [],
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
        await baseUrl.get(`/products?page=${currentPage}&offset=${pageLimit}`, {headers: authHeader()})
        .then( (response) => {  
            this.setState({
                products: Object.values(response.data)[0],
                totalRecords: parseInt(Object.keys(response.data)[0])
            })
        })
        .catch( (error) => {
            console.log(error);
        });
    }

    getSearchData = async(searchText) => {
        await baseUrl.get(`/products?search=${searchText}`, {headers: authHeader()})
        .then( (response) => {
            this.setState({
                products: response.data,
                totalRecords: null
            })
        })
        .catch( (error) => {
            console.log(error);
        });
    }

    renderTuples = () => {
        return this.state.products.map( (value, index) => {
            return <ProductTuple obj={value} key={index}/>
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
    
    render() {
        if (!this.props.isLoggedIn) {
            return <Redirect to="/error"/>
        }
        const { totalRecords, pageLimit } = this.state;
        return (
    <div className="container">
        
        <div className="container text-center">
            <div>
                <h1 className="h1-view">Product</h1>
            </div>
            <p>
                <input
                    type="text"
                    placeholder="Search Product by name..."
                    name="searchtext"
                    onChange={this.onSearchBar}
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
                            {/* <th>select</th> */}
                            <th>ID</th>
                            <th>Name</th>
                            <th>Price</th>
                            <th>Description</th>
                            <th>Category</th>
                            <th>Status</th>
                            <th>Last modified date</th>
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

            <div className="btn">
                <Link className="btn btn-primary" to="/admin/products/create">
                    Create new product
                </Link>
            </div>
        </div>
    </div>
        );
    }
}

const mapStateToProps = (state) => {
    return {
        isLoggedIn: state.auth.isLoggedIn
    };
}
export default connect(mapStateToProps)(ReadProduct);

