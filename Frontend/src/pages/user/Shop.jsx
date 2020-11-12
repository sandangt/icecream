import baseUrl from "baseUrl";
import React from "react";
import {connect} from "react-redux";
import {Redirect} from "react-router-dom";

import authHeader from "services/authHeader";
import ProductCard from "components/ProductCard.jsx";
import Pagination from "components/pagination/Pagination.jsx";

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
            cart: 0
        };
    }
    
    componentDidMount() {
        this.getCategoryData();
        this.getPaginatedProductDataByCategory(this.state.categoryType, this.state.currentPage, this.state.pageLimit);
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
    // getAllProductDataByPage = async (currentPage, pageLimit) => {
    //     await baseUrl.get(`/products?page=${currentPage}&offset=${pageLimit}`, {headers: authHeader()})
    //     .then( (response) => {  
    //         this.setState({
    //             products: Object.values(response.data)[0],
    //             totalRecords: parseInt(Object.keys(response.data)[0]),
    //             getloading:true
    //         })
    //     })
    //     .catch( (error) => {
    //         console.log(error);
    //     });
    // }

    getPaginatedProductDataByCategory = async (categoryType, currentPage, pageLimit) => {
        const url = categoryType === 0 ? 
        `/products?page=${currentPage}&offset=${pageLimit}` :
        `/categories/${categoryType}/products?page=${currentPage}&offset=${pageLimit}`;
        await baseUrl.get(url, {headers: authHeader()})
        .then( (response) => {  
            this.setState({
                products: response.data.itemList,
                totalRecords: response.data.totalItems,
                getloading:true
            })
        })
        .catch( (error) => {
            console.log(error);
        });
    }

    addToCart = (cart) => {
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
                    addToCart={this.addToCart}
                    />);
            })
        );
    }

    onPageChanged = data => {
        const { currentPage, pageLimit } = data;
        this.setState({ currentPage, pageLimit });
        this.getPaginatedProductDataByCategory(this.state.categoryType, currentPage, pageLimit);
    }

    render() {
        if (!this.props.isLoggedIn || !this.props.user.roles.includes("ROLE_USER")) {
            return <Redirect to="/error"/>
        }
        return (
<div>
    <div className="row">
        <div className="col-lg-3">
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
                    this.getPaginatedProductDataByCategory(0, this.state.currentPage, this.state.pageLimit);}}>
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
                            this.getPaginatedProductDataByCategory(value.id, 1, this.state.pageLimit);
                        }}
                        >
                            {value.name}
                        </button>
                    );
                })}
                <h2>Cart: {this.state.cart}</h2>
            </div>
        </div>
        <div className="col-lg-9">
            <div className="row">
                {this.renderProductCardGrid()}
            </div>
        </div>
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