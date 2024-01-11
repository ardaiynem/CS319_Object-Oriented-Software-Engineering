import { useEffect, useState } from "react";
import StudentsPage from "../commonComponents/StudentsPage";
import useAxiosPrivate from "../../hooks/useAxiosPrivate";
import useAuth from '../../hooks/useAuth';



async function fetchAllStudents(axiosPrivate, auth, setStudents) {
    try {
        const response = await axiosPrivate.get('/evaluators/students/' + auth.user);
        setStudents(response.data);
        console.log(response.data)
    } catch (err) {
        console.error(err);
    }
}

// async function fetchReports(axiosPrivate, auth, setReports) {
//     try {
//         const response = await axiosPrivate.get('/evaluators/reports/' + auth.user);
//         setReports(response.data);
//         console.log(response.data)
//     } catch (err) {
//         console.error(err);
//     }
// }

async function fetchSubmissions(axiosPrivate, auth, setSubmissions) {
    try {
        const response = await axiosPrivate.get('/evaluators/submissions/' + auth.user);
        setSubmissions(response.data);
        console.log(response.data)
    } catch (err) {
        console.error(err);
    }
}

export default function EvaluatorStudents() {
    const { auth } = useAuth();
    const [students, setStudents] = useState([]);
    //const [reports, setReports] = useState([]);
    const [submissions, setSubmissions] = useState([]);

    const axiosPrivate = useAxiosPrivate();

    useEffect(() => {
        //fetchReports(axiosPrivate, auth, setReports);
        fetchAllStudents(axiosPrivate, auth, setStudents);
        //fetchSubmissions(axiosPrivate, auth, setSubmissions);
    }
        , []);

    // useEffect(() => {
    //     console.log(studentz);
    // }
    //     , [studentz]);



    // Obviously, this will be replaced with the actual students of the evaluator

    // const tmpStudents = [
    //     {id: 220000, course: "CS299", name: "Osman Osmanovic", last_submission_date: "2001-01-01", status: "Assigned to course", email: "temp@gmail.com"},
    //     {id: 220001, course: "CS299", name: "Feridun Feridunovski", last_submission_date: "2001-01-02", status: "Waiting TA", email: "temp@gmail.com"},
    //     {id: 220002, course: "CS399", name: "Bedük", last_submission_date: "2000-01-01", status: "Decision", email: "temp@gmail.com"},
    //     {id: 220003, course: "CS399", name: "Jonathan Jonathan", last_submission_date: "2002-03-02", status: "Waiting evaluator", email: "temp@gmail.com"},
    //     {id: 220004, course: "CS299", name: "Arda Yurdunuçokseven", last_submission_date: "1999-01-01", status: "Requested resubmission", email: "temp@gmail.com"}
    // ];

    // const modifiedStudents = tmpStudents.map(item => {
    //     const container = {};

    //     container[item.name] = item.likes;
    //     container.age = item.name.length * 10;

    //     return container;
    // })



    // const [students] = useState([
    //     { id: 220000, course: "CS299", name: "Osman Osmanovic", last_submission_date: "2001-01-01", status: "Assigned to course", email: "temp@gmail.com" },
    //     { id: 220001, course: "CS299", name: "Feridun Feridunovski", last_submission_date: "2001-01-02", status: "Waiting TA", email: "temp@gmail.com" },
    //     { id: 220002, course: "CS399", name: "Bedük", last_submission_date: "2000-01-01", status: "Decision", email: "temp@gmail.com" },
    //     { id: 220003, course: "CS399", name: "Jonathan Jonathan", last_submission_date: "2002-03-02", status: "Waiting evaluator", email: "temp@gmail.com" },
    //     { id: 220004, course: "CS299", name: "Arda Yurdunuçokseven", last_submission_date: "1999-01-01", status: "Requested resubmission", email: "temp@gmail.com" }
    // ]);

    return (
        <div style={{ marginLeft: '250px', padding: '20px' }}>
            <h1 className="bigPageTitle"> Your assigned students </h1>
            <StudentsPage userType="evaluator" students={students} />
            {/*<StudentsPage userType="evaluator" students={students}/>*/}
        </div>
    )
}


