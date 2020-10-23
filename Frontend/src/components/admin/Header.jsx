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
          <a id="MajorPage" className="nav-link" href="<c:url value='/admin/major'/>" >Major</a>
        </li>
        <li className="nav-item">
          <a id="CurriculumPage" className="nav-link" href="<c:url value='/admin/curriculum'/>" >Curriculum</a>
        </li>
        <li className="nav-item">
          <a id="CoursePage" className="nav-link" href="<c:url value='/admin/course'/>" >Course</a>
        </li>
      </ul>
    </div>
  </div>
</nav>
    );
  }
}

export default Header;