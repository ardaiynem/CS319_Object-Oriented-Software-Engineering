import { Routes, Route } from 'react-router-dom';
import StudentSideBar from '../components/student/StudentSideBar';
import EvaluatorTAInfo from '../components/student/EvaluatorTAInfo';
import Reports from '../components/student/Reports';
import StudentProfile from '../components/student/StudentProfile';
import SupervisorInfo from '../components/student/SupervisorInfo';
import useAxiosPrivate from "../hooks/useAxiosPrivate";
import useAuth from '../hooks/useAuth';
import { useState, useEffect } from "react";

const User = () => {
    async function fetchUserStudent(axiosInstance, auth, setStudent) {
        try {
            const response = await axiosInstance.get(`/students/${auth.user}`);
            setStudent(response.data);
        } catch (error) {
            console.error(error);
        }
    }

    const { auth } = useAuth();
    const [student, setStudent] = useState([]);

    const axiosPrivate = useAxiosPrivate();

    
    useEffect(() => { fetchUserStudent(axiosPrivate, auth, setStudent); }, []);

    return (
        <Routes>
            <Route path="/" element={<StudentSideBar />}>
                <Route index element={<Reports />} />
                <Route path="profile" element={<StudentProfile />} />
                <Route path="evaluator_ta_info" element={<EvaluatorTAInfo />} />
                <Route path="supervisor_info" element={<SupervisorInfo student={student}/>} />
            </Route>
        </Routes>
    )
}

export default User