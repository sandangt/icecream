import React from "react";
import {Link, Redirect} from "react-router-dom";
import {connect} from "react-redux";

import baseUrl from "baseUrl.js";
import authHeader from "services/authHeader.js";
import FAQTuple from "components/tuples/FAQTuple.jsx";
import Pagination from "components/pagination/Pagination.jsx";

class ReadFAQ extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            faqs: [],
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
        await baseUrl.get(`/faq?page=${currentPage}&offset=${pageLimit}`, {headers: authHeader()})
        .then( (response) => {  
            this.setState({
                faqs: Object.values(response.data)[0],
                totalRecords: parseInt(Object.keys(response.data)[0])
            })
        })
        .catch( (error) => {
            console.log(error);
        });
    }

    // getData = async() => {
    //     await baseUrl.get(`/faq?number=0&size=${this.state.pageLimit}`, {headers: authHeader()})
    //     .then( (response) => {  
    //         this.setState({
    //             faqs: Object.values(response.data)[0],
    //             totalRecords: parseInt(Object.keys(response.data)[0])
    //         })
    //     })
    //     .catch( (error) => {
    //         console.log(error);
    //     });
    // }

    renderTuples = () => {
        return this.state.faqs.map( (value, index) => {
            return <FAQTuple obj={value} key={index}/>
        });
    }

    onPageChanged = data => {
        const { currentPage, pageLimit } = data;
        this.setState({ currentPage, pageLimit });
        this.getDataByPage(currentPage, pageLimit);
    }

    render() {
        if (!this.props.isLoggedIn) {
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
                            <th>Question</th>
                            <th>Answer</th>
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
                <Link className="btn btn-primary" to="/admin/users/create">
                    Create new FAQ
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
export default connect(mapStateToProps)(ReadFAQ);

