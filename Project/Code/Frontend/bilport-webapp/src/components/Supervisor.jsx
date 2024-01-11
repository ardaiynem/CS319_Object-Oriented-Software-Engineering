import { Routes, Route } from 'react-router-dom';
import SupervisorProfile from './supervisor/SupervisorProfile';
import SupervisorSidebar from './supervisor/SupervisorSidebar';
import SupervisorEvaluationForm from './supervisor/SupervisorEvaluationForm';
import SupervisorSealStamp from './supervisor/SupervisorSealStamp';


const Supervisor = () => {
    return (
        <Routes>
            <Route path = "/" element={<SupervisorSidebar/>}>
                <Route index element={<SupervisorEvaluationForm/>} />
                <Route path = "profile" element={<SupervisorProfile/>} />
                <Route path = "seal_stamp" element={<SupervisorSealStamp/>} />
            </Route>
        </Routes>
      )
  }

export default Supervisor