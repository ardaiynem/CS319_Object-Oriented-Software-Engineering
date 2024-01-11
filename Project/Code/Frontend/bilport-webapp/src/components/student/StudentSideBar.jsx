import { Outlet, Link } from "react-router-dom";
import { Row, Col } from 'react-bootstrap';
import useLogout from "../../hooks/useLogout";

export default function StudentSideBar() {
  const logout = useLogout();
    
  const signOut = async () => {
    await logout();
  }

  return (
    <>
      <Row>
        <Col sm={3} md={2} className="Sidebar">
          <h1 className="sidebar-header">
            <i class="fa-solid fa-shield-cat" style={{color: "#bf69ed"}}></i> Bilport
          </h1>
          <hr/>
          <ul className="sidebar-list">
            <li className="sidebar-item">
            <i className="material-icons">book</i>
              <Link to="/student" className="sidebar-link">Reports</Link>
            </li>
            <li className="sidebar-item">
              <i className="material-icons">account_circle</i>
              <Link to="/student/profile" className="sidebar-link">Profile</Link>
            </li>
            <li className="sidebar-item">
              <i class="fa-solid fa-person-chalkboard"></i>
              <Link to="/student/evaluator_ta_info" className="sidebar-link">Evaluator/TA Info</Link>
            </li>
            <li className="sidebar-item">
              <i className="material-icons">assignment_ind</i>
              <Link to="/student/supervisor_info" className="sidebar-link">Supervisor Info</Link>
            </li>
            <li className="sidebar-item" onClick={signOut}>
                <i class="material-icons">logout</i> <Link className="sidebar-link"> Log Out</Link>
            </li>
          </ul>
        </Col>
      </Row>

      <Outlet />
    </>
  );
}