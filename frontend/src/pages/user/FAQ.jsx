import React from "react";
import {Redirect} from "react-router-dom";
import {connect} from "react-redux";

import baseUrl from "baseUrl.js";
import authHeader from "services/authHeader.js";
import FAQTuple from "components/tuples/FAQTuple.jsx";
import Pagination from "components/pagination/Pagination.jsx";

import FAQCard from "components/collapse/FAQCard.jsx";

class FAQ extends React.Component { 

    constructor(props) {
        super(props);
        this.state = {
            faqs: [],
            totalRecords: null,
            currentPage: 1,
            totalPages: null,
            pageLimit: 3
        };
    }

    componentDidMount() {
        this.getDataByPage(this.state.currentPage, this.state.pageLimit);
    }
    
    getDataByPage = async (currentPage, pageLimit) => {
        await baseUrl.get(`/faq?page=${currentPage}&offset=${pageLimit}`, {headers: authHeader()})
        .then( (response) => {  
            this.setState({
                faqs: response.data.itemList,
                totalRecords: response.data.totalItems
            })
        })
        .catch( (error) => {
            console.log(error);
        });
    }
    
    getSearchData = async(searchText) => {
        await baseUrl.get(`/faq?search=${searchText}`, {headers: authHeader()})
        .then( (response) => {
            this.setState({
                faqs: response.data,
                totalRecords: null
            })
        })
        .catch( (error) => {
            console.log(error);
        });
    }

    renderTuples = () => {
        return this.state.faqs.map( (value, index) => {
            return <FAQTuple obj={value} key={index}/>
        });
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

    onPageChanged = data => {
        const { currentPage, pageLimit } = data;
        this.setState({ currentPage, pageLimit });
        this.getDataByPage(currentPage, pageLimit);
    } 

    renderCollapseFAQ = () => {
        return this.state.faqs.map( (value, index) => {
            return <FAQCard question={value.question} answer={value.answer} key={index}/>;
        });
    }

    render() {
        if (!this.props.isLoggedIn || !this.props.user.roles.includes("ROLE_USER")) {
            return <Redirect to="/error"/>
        }
        const { totalRecords, pageLimit } = this.state;
        return (
<div>
    <div className="text-center">
        <div>
            <h1 className="h1-view">FAQ</h1>
        </div>
        <p>
            <input
                type="text"
                placeholder="Search FAQ by question..."
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
    </div>

    <div id="accordion">
        {this.renderCollapseFAQ()}
    </div>    
            
    <div className="d-flex flex-row py-4 align-items-center">
        {totalRecords && <Pagination 
        totalRecords={totalRecords} 
        pageLimit={pageLimit} 
        pageNeighbours={1} 
        onPageChanged={this.onPageChanged}
        /> }
    </div>
</div>
        );
    }
}

const mapStateToProps = (state) => {
    return({
        isLoggedIn: state.auth.isLoggedIn,
        user: state.auth.user
    })
}
export default connect(mapStateToProps)(FAQ);