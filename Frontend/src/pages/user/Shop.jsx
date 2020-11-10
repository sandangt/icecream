import baseUrl from "baseUrl";
import React from "react";
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
            getloading: false
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
                products: Object.values(response.data)[0],
                totalRecords: parseInt(Object.keys(response.data)[0]),
                getloading:true
            })
        })
        .catch( (error) => {
            console.log(error);
        });
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
                    })
                    this.getPaginatedProductDataByCategory(0, this.state.currentPage, this.state.pageLimit);
                }}
                >
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

export default Shop;