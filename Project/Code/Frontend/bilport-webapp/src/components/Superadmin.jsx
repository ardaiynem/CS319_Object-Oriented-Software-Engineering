import { Routes, Route } from 'react-router-dom';
import SuperAdminSidebar from "./superAdminComponents/SuperAdminSidebar";
import SuperAdminManagement from "./superAdminComponents/SuperAdminManagement";
import SuperAdminSingularStudentPage from "./superAdminComponents/SuperAdminSingularStudentPage";
import SuperAdminProfile from "./superAdminComponents/SuperAdminProfile";

const Superadmin = () => {
    return (
        <Routes>
            <Route path = "/" element={<SuperAdminSidebar/>}>
                <Route index element={<SuperAdminManagement/>} />
                <Route path = "students/:id" element ={<SuperAdminSingularStudentPage/>} />
                <Route path = "profile" element = {<SuperAdminProfile/>} />
            </Route>
        </Routes>
      )
  }

export default Superadmin