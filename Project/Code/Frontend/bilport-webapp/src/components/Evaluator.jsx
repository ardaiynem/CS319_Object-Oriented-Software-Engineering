import EvaluatorSideBar from './evaluator/EvaluatorSideBar';
import EvaluatorStudents from './evaluator/EvaluatorStudents';
import EvaluatorProfile from './evaluator/EvaluatorProfile';
import CourseTAInfo from './commonComponents/CourseTAInfo';
import AssignedStudentPage from './evaluator/AssignedStudentPage';
import { Routes, Route } from 'react-router-dom';
import EvaluatorTAInfo from './evaluator/EvaluatorTAInfo';

const Evaluator = () => {
    return (
      <Routes>
          <Route path = "/" element={<EvaluatorSideBar/>}>
              <Route index element={<EvaluatorStudents/>} />
              <Route path = "students/:id" element ={<AssignedStudentPage/>} />
              <Route path = "profile" element = {<EvaluatorProfile/>} />
              <Route path = "course_ta_info" element = {<EvaluatorTAInfo/>} />
          </Route>
      </Routes>
    )
}

export default Evaluator
