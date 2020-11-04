import React from "react";

class UserCrud extends React.Component {
    
    render() {
        return (
            <main>
                <form
                    id="formSubmit"
                    action="<c:url value='/admin/major'/>"
                    method="GET"
                >
                    <div className="container">
                        <div className="container text-center">
                            <div>
                                <h1 className="h1-view">Major</h1>
                            </div>
                            <p>
                                <input
                                    type="text"
                                    placeholder="Search major by name..."
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
                            <div className="d-flex table-data">
                                <c:foreach
                                    items="${major.getListItems()}"
                                    var="majorTuple"
                                >
                                    <table
                                        className="table table-striped scrollTable center"
                                        border={1}
                                        cellSpacing={1}
                                    >
                                        <thead className="thead-dark">
                                            <tr>
                                                <th>select</th>
                                                <th>ID</th>
                                                <th>Major</th>
                                                <th>Number of courses</th>
                                                <th>
                                                    Number of learning paths
                                                </th>
                                                <th />
                                                <th />
                                            </tr>
                                        </thead>
                                        <tbody id="tbody">
                                            <tr>
                                                <td>
                                                    <input type="checkbox" />
                                                </td>
                                                <td>
                                                    ${"{"}
                                                    majorTuple.getMajor_ID()
                                                    {"}"}
                                                </td>
                                                <td>
                                                    ${"{"}
                                                    majorTuple.getMajorName()
                                                    {"}"}
                                                </td>
                                                <td>
                                                    ${"{"}
                                                    majorTuple.getCoursesNumber()
                                                    {"}"}
                                                </td>
                                                <td>
                                                    ${"{"}
                                                    majorTuple.getPathsNumber()
                                                    {"}"}
                                                </td>
                                                <td>
                                                    <a
                                                        className="btn btn-info"
                                                        href="<c:url value='/admin/major-update?id=${majorTuple.getMajor_ID()}&MajorName=${majorTuple.getMajorName()}'/>"
                                                    >
                                                        Edit
                                                    </a>
                                                </td>
                                                <td>
                                                    <form
                                                        method="post"
                                                        action="<c:url value='/admin/major'/>"
                                                    >
                                                        <input
                                                            type="hidden"
                                                            name="procedure"
                                                            defaultValue="DELETE"
                                                        />
                                                        <input
                                                            type="hidden"
                                                            name="Major_ID"
                                                            defaultValue="${majorTuple.getMajor_ID()}"
                                                        />
                                                        <button
                                                            className="btn btn-danger"
                                                            type="submit"
                                                        >
                                                            Delete
                                                        </button>
                                                    </form>
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </c:foreach>
                            </div>
                            <nav aria-label="Page navigation">
                                <ul className="pagination" id="pagination" />
                            </nav>
                            <input
                                type="hidden"
                                defaultValue
                                id="page"
                                name="page"
                            />
                            <input
                                type="hidden"
                                defaultValue
                                id="maxItemsPerPage"
                                name="maxItemsPerPage"
                            />
                            <div className="btn">
                                <a
                                    className="btn btn-primary"
                                    href="<c:url value='/admin/major-create'/>"
                                >
                                    Add major
                                </a>
                            </div>
                        </div>
                    </div>
                </form>
            </main>
        );
    }
}

export default UserCrud;
