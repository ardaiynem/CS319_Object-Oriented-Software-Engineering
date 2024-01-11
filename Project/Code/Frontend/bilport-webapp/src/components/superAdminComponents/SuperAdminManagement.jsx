import { Tab, Tabs } from "react-bootstrap";
import { useState } from "react";
import SuperAdminStudents from "./SuperAdminStudents";
import SuperAdminEvaluators from "./SuperAdminEvaluators";
import SuperAdminTA from "./SuperAdminTA";
import { useEffect } from "react";
import useAuth from "../../hooks/useAuth";
import useAxiosPrivate from "../../hooks/useAxiosPrivate";

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

async function fetchUserSuperAdmin(axiosInstance, auth, setSuperadmin) {
    try {
        const response = await axiosInstance.get(`/superadmins/${auth.user}`);
        setSuperadmin(response.data);
    } catch (error) {
        console.error(error);
    }
}


export default function SuperAdminManagement(){
    
    const { auth } = useAuth();
    // This is not working for some reason
    const [superAdmin, setSuperadmin] = useState([]);
    const axiosPrivate = useAxiosPrivate();


    const [evaluators, setEvaluators] = useState([]);

    const [students, setStudents] = useState([]);
    useEffect(() => {
        fetchUserSuperAdmin(axiosPrivate, auth, setSuperadmin);
        fetchAllStudents(axiosPrivate, setStudents);
        fetchAllEvaluators(axiosPrivate, setEvaluators);
    }, []);

    useEffect(() => {
        console.log(superAdmin);
    }, [superAdmin]);

    return ( 
        <div style={{ marginLeft: '250px', padding: '20px' }}>
            <Tabs defaultActiveKey="superAdminStudents" className="mb-3" fill>
                <Tab eventKey="superAdminStudents" title = "Students" tabClassName='coloredTab'>
                    <SuperAdminStudents students = {students} />
                </Tab>
                <Tab eventKey="superAdminEvaluators" title = "Evaluators" tabClassName='coloredTab'>
                    <SuperAdminEvaluators evaluators = {evaluators} /> 
                </Tab>
                <Tab eventKey="superAdminTA" title = "Teaching Assistant" tabClassName='coloredTab'>
                    <SuperAdminTA/>
                </Tab>    
            </Tabs>
        </div>
    );
};