import baseUrl from "baseUrl";
import React from "react";
import {connect} from "react-redux";
import {Redirect, Link} from "react-router-dom";

import authHeader from "services/authHeader";
import ProductCard from "components/ProductCard.jsx";
import Pagination from "components/pagination/Pagination.jsx";

import {countItemInCartService} from "services/cartService.js";

class Shop extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            categories: [],
            products: [],
            totalRecords: null,
            currentPage: 1,
            totalPages: null,
            pageLimit: 6,
            categoryType: 0,
            searchText: "",
            getloading: false,
            cart: countItemInCartService()
        };
    }
    
    componentDidMount() {
        this.getCategoryData();
        this.getAllProductDataByPage(this.state.currentPage, this.state.pageLimit);
    }

    componentDidUpdate(){
        setTimeout(() => this.setState({getloading: false}), 7500);
    }
    
    getCategoryData = async() => {
        await baseUrl.get(`/categories/name`,{headers: authHeader()})
        .then( (response) => {
            this.setState({
                categories: response.data
            })
        })
        .catch( (error) => {
            console.log(error);
        })
    }

    getAllProductDataByPage = async (currentPage, pageLimit) => {
        await baseUrl.get(`/products?page=${currentPage}&offset=${pageLimit}`, {headers: authHeader()})
        .then( (response) => {  
            this.setState({
                products: response.data.itemList,
                totalRecords: response.data.totalItems,
                getloading: true
            })
        })
        .catch( (error) => {
            console.log(error);
        });
    }

    getProductDataByCategory = async (categoryType) => {
        await baseUrl.get(`/categories/${categoryType}/products`, {headers: authHeader()})
        .then( (response) => {  
            this.setState({
                products: response.data,
                totalRecords: null,
                getloading:true
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
    
    onSearchButton = async (e) => {
        e.preventDefault();
        const {searchText} = this.state;
        if (searchText === "") {
            this.getAllProductDataByPage(1, this.state.pageLimit);
        }   
        else {
            this.getSearchData(searchText);
        }
    }
    
    countCart = (cart) => {
        this.setState({cart: cart+=1});
    }
    renderProductCardGrid = () => {
        return (
            this.state.products.map( value => {
                return (<ProductCard 
                    id={value.id}
                    name={value.name} 
                    image={value.image} 
                    description={value.description}
                    price={value.price}
                    status={value.status}
                    cart={this.state.cart}
                    countCart={this.countCart}
                    />);
            })
        );
    }

    onPageChanged = data => {
        const { currentPage, pageLimit } = data;
        this.setState({ currentPage, pageLimit });
        this.getAllProductDataByPage(currentPage, pageLimit);
    }

    render() {
        if (!this.props.isLoggedIn || !this.props.user.roles.includes("ROLE_USER")) {
            return <Redirect to="/error"/>
        }
        return (
<div>
    <div className="row">
        <div className="col-lg-3">
            <div style={{position:"fixed"}}>
            <h3 className="my-4">Category</h3>
            <div className="list-group">
                <button className="list-group-item text-left" 
                style={{color: "blue"}}
                onClick={(e) => {
                    e.preventDefault();
                    this.setState({
                        categoryType:0,
                        currentPage: 1
                    });
                    this.getAllProductDataByPage(this.state.currentPage, this.state.pageLimit);}}>
                    all
                </button>
                {this.state.categories.map((value) => {
                    return (
                        <button className="list-group-item text-left" 
                        style={{color: "blue"}}
                        onClick={(e) => {
                            e.preventDefault();
                            this.setState({
                                categoryType:value.id,
                                currentPage: 1
                            });
                            this.getProductDataByCategory(value.id);
                        }}
                        >
                            {value.name}
                        </button>
                    );
                })}
                <hr/>
                <div className="form-inline d-flex justify-content-center md-form form-sm active-pink active-pink-2 mt-2">
                    <input className="form-control form-control-sm ml-3 w-75" 
                    type="text"
                    placeholder="Search product by name..."
                    name="searchtext"
                    onChange={(e) => {
                        e.preventDefault();
                        this.setState({searchText: e.target.value});
                    }}
                    />
                    <button onClick={this.onSearchButton}>
                        <i className="fa fa-search" aria-hidden="true"></i>
                    </button>
                </div>
                <hr/>
                <h3>
                    <i className="fa fa-shopping-cart"/>
                    {this.state.cart}
                </h3>
                <hr/>
                <Link className="btn btn-primary" to="/order">Check</Link>
            </div>
        </div>
        </div>
        <div className="col-lg-9">
            <div className="row">
                {this.renderProductCardGrid()}
            </div>
            <hr/>
            <div className="d-flex flex-row py-4 align-items-center">
                    {this.state.totalRecords && <Pagination 
                    totalRecords={this.state.totalRecords} 
                    pageLimit={this.state.pageLimit} 
                    pageNeighbours={1} 
                    onPageChanged={this.onPageChanged}
                    /> }
                </div>
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

export default connect(mapStateToProps)(Shop);