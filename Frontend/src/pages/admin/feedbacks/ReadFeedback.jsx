import React from "react";
import {connect} from "react-redux";
import {Redirect, Link} from "react-router-dom";

import authHeader from "services/authHeader.js";
import baseUrl from "baseUrl.js";
import FeedbackTuple from "components/tuples/FeedbackTuple.jsx";
import Pagination from "components/pagination/Pagination.jsx";
 
class ReadFeedback extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            feedbacks: [],
            totalRecords: null,
            currentPage: 1,
            totalPages: null,
            pageLimit: 5
        };
    }
    componentDidMount() {
        this.getDataByPage(this.state.currentPage, this.state.pageLimit);
    }    

    getDataByPage = async (currentPage, pageLimit) => {
        await baseUrl.get(`/feedbacks?page=${currentPage}&offset=${pageLimit}`, {headers: authHeader()})
        .then( (response) => {  
            this.setState({
                feedbacks: response.data.itemList,
                totalRecords: response.data.totalItems
            }, () => console.log(this.state.orders))
        })
        .catch( (error) => {
            console.log(error);
        });
    }

    renderTuples = () => {
        return this.state.feedbacks.map( (value, index) => {
            return <FeedbackTuple obj={value} key={index} onClickCheck={this.onClickCheck}/>
        });
    }

    onPageChanged = data => {
        const { currentPage, pageLimit } = data;
        this.setState({ currentPage, pageLimit });
        this.getDataByPage(currentPage, pageLimit);
    }


    render() {
        if (!this.props.isLoggedIn || !this.props.user.roles.includes("ROLE_ADMIN")) {
            return <Redirect to="/error"/>
        }
        const { totalRecords, pageLimit } = this.state;
        console.log(this.state.feedbacks);
        return (
    <div>
        <div className="text-center">
            <div>
                <h1 className="h1-view">Order detail</h1>
            </div>
            <div className="d-flex table-data">
                <table className="table table-striped scrollTable center"
                    border={1}
                    cellSpacing={1}
                >
                    <thead className="thead-dark">
                        <tr>
                            <th>ID</th>
                            <th>Title</th>
                            <th>Content</th>
                            <th>Username</th>
                            <th>Product's name</th>
                            <th>Last modified date</th>
                            <th colSpan="1">Action</th>
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
export default connect(mapStateToProps)(ReadFeedback);

