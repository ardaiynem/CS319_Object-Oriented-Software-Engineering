import { Route, Routes } from "react-router-dom"
import TASidebar from "./teachingAssistant/TASidebar"
import TAStudents from "./teachingAssistant/TAStudents"
import TASingleStudentPage from "./teachingAssistant/TASingleStudentPage"
import TAProfile from "./teachingAssistant/TAProfile"
import useAxiosPrivate from "../hooks/useAxiosPrivate";
import useAuth from '../hooks/useAuth';
import { useState, useEffect } from "react";

const TeachingAssistant = () => {

    const { auth } = useAuth();
    const [students, setStudents] = useState([]);
    const [ta, setTa] = useState({});
    const axiosPrivate = useAxiosPrivate();

    async function fetchUserTA(axiosInstance, auth, setTa) {
        try {
            const response = await axiosInstance.get(`/TAs/${auth.user}`);
            setTa(response.data);
            return response.data;
        } catch (error) {
            console.error(error);
        }
    }
    
    async function fetchAllStudents(axiosPrivate, course, setStudents) {
        try {
            const response = await axiosPrivate.get('/students/all/' + course);
            setStudents(response.data);
            console.log(response.data)
        } catch (err) {
            console.error(err);
        }
    }

    useEffect(() => {
        if (!ta.assignedCourse) {
            const taTmp = fetchUserTA(axiosPrivate, auth, setTa).then((taTmp) => {fetchAllStudents(axiosPrivate, taTmp.assignedCourse, setStudents);} );
        }
    }
        , [ta.assignedCourse]);


    return (
        <Routes>
            <Route path = "/" element={<TASidebar/>}>
                <Route index element={<TAStudents students={students} ta={ta} />} />
                <Route path = "students/:id" element ={<TASingleStudentPage ta={ta}/>} />
                <Route path = "profile" element ={<TAProfile/>} />
            </Route>
        </Routes>
      )
  }

export default TeachingAssistant