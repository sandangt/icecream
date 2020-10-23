import React from "react";

class Header extends React.Component {
  render() {
    return (
<nav className="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
  <div className="container">
    <a className="navbar-brand" href="#">spring</a>
    <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
      <span className="navbar-toggler-icon"></span>
    </button>
    <div className="collapse navbar-collapse" id="navbarResponsive">
      <ul className="navbar-nav ml-auto">
        <li className="nav-item">
          <a id="HomePage" className="nav-link" href="<c:url value='/admin/home'/>" >Home
            <span className="sr-only">(current)</span>
          </a>
        </li>
        <li className="nav-item">
          <a id="UserPage" className="nav-link" href="<c:url value='/admin/major'/>" >User</a>
        </li>
        <li className="nav-item">
          <a id="ProductPage" className="nav-link" href="<c:url value='/admin/curriculum'/>" >Product</a>
        </li>
        <li className="nav-item">
          <a id="OrderPage" className="nav-link" href="<c:url value='/admin/course'/>" >Order</a>
        </li>
        <li className="nav-item">
          <a id="CategoryPage" className="nav-link" href="<c:url value='/admin/course'/>" >Category</a>
        </li>
        <li className="nav-item">
          <a id="FAQPage" className="nav-link" href="<c:url value='/admin/course'/>" >FAQ</a>
        </li>
        <li className="nav-item">
          <a id="FeedbackPage" className="nav-link" href="<c:url value='/admin/course'/>" >Feedback</a>
        </li>
        <li className="nav-item">
          <a id="FeedbackPage" className="nav-link" href="<c:url value='/admin/course'/>" >Logout</a>
        </li>
      </ul>
    </div>
  </div>
</nav>
    );
  }
}

export default Header;