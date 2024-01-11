import { Tab, Tabs } from "react-bootstrap";
import AdminStudents from "./AdminStudents";
import { useState, useEffect } from "react";
import AdminEvaluators from "./AdminEvaluators";
import useAuth from '../../hooks/useAuth';
import useAxiosPrivate from "../../hooks/useAxiosPrivate";

async function fetchUserAdmin(axiosInstance, auth, setAdmin) {
    try {
        const response = await axiosInstance.get(`/admins/${auth.user}`);
        setAdmin(response.data);
    } catch (error) {
        console.error(error);
    }
}

async function fetchAllStudents(axiosInstance, setStudents) {
    try {
        const response = await axiosInstance.get(`/students/all`);
        setStudents(response.data);
    } catch (error) {
        console.error(error);
    }
}

async function fetchAllEvaluators(axiosInstance, setEvaluator) {
    try {
        const response = await axiosInstance.get(`/evaluators`);
        setEvaluator(response.data);
        console.log(response.data);
    } catch (error) {
        console.error(error);
    }
}

export default function AdminManagement() {
    const { auth } = useAuth();
    const [admin, setAdmin] = useState([]);
    const axiosPrivate = useAxiosPrivate();

    const [evaluators, setEvaluators] = useState([]);

    const [students, setStudents] = useState([]);
    useEffect(() => {
        fetchUserAdmin(axiosPrivate, auth, setAdmin);
        fetchAllStudents(axiosPrivate, setStudents);
        fetchAllEvaluators(axiosPrivate, setEvaluators);
    }, []);

    return (
        <div style={{ marginLeft: '250px', padding: '20px' }}>
            <Tabs defaultActiveKey="adminStudents" className="mb-3" fill>
                <Tab eventKey="adminStudents" title="Students" tabClassName='coloredTab'>
                    <AdminStudents students={students} userType="admin" />
                </Tab>
                <Tab eventKey="adminEvaluators" title="Evaluators" tabClassName='coloredTab'>
                    <AdminEvaluators evaluators={evaluators} userType="admin" />
                </Tab>
            </Tabs>
        </div>
    );
};
