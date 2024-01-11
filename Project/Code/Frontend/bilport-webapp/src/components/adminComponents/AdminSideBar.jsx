import { Outlet, Link } from "react-router-dom";
import { Row, Col } from 'react-bootstrap';
import useLogout from "../../hooks/useLogout";

export default function AdminSideBar(){
    const logout = useLogout();
    
    const signOut = async () => {
      await logout();
    }

    return(
    <>
        <Row>
          <Col sm={3} md={2} className="Sidebar">
            <h1 class = "sidebar-header"><i class="fa-solid fa-shield-cat" style={{color: "#bf69ed"}}></i> Bilport</h1>
            <hr/>
            <ul class = "sidebar-list">
              <li class ="sidebar-item">
              <i class="material-icons">insert_chart</i>
                <Link to="/admin" class = "sidebar-link">Management</Link>
              </li>
              <li class ="sidebar-item">
              <i class="material-icons">account_circle</i>
                <Link to="/admin/profile" class = "sidebar-link">Profile</Link>
              </li>
              <li className="sidebar-item" onClick={signOut}>
                <i class="material-icons">logout</i> <Link className="sidebar-link"> Log Out</Link>
              </li>
            </ul>
          </Col>
        </Row>

    <Outlet/>
    </>
    );
}