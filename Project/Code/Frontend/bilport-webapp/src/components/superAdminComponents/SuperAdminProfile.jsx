import { Row, Col, Card, Container } from "react-bootstrap";
import ChangePassword from "../commonComponents/ChangePassword";
import useAuth from '../../hooks/useAuth';
import useAxiosPrivate from "../../hooks/useAxiosPrivate";
import { useState } from "react";
import { useEffect } from "react";

async function fetchUserSuperadmin(axiosInstance, auth, setSuperAdmin) {
    try {
        const response = await axiosInstance.get(`/superadmins/${auth.user}`);
        setSuperAdmin(response.data);
    } catch (error) {
        console.error(error);
    }
}

export default function SuperAdminProfile(){
    const { auth } = useAuth();
    const [superadmin, setSuperAdmin] = useState({});
    const axiosPrivate = useAxiosPrivate();

    useEffect(() => { fetchUserSuperadmin(axiosPrivate, auth, setSuperAdmin); }, []);

    return (
        <div style={{ marginLeft: '250px', padding: '20px' }}>
            <h1 class = "bigPageTitle"> Profile Page </h1>
            <Card>
                <Card.Body>
                    <Container fluid>
                        <Row>
                            <Col lg = {2} style={{ color: "purple"}}>Full name:</Col>
                            <Col lg = {10}><div class = "text-secondary">{superadmin.superadminName + " " + superadmin.superadminSurname}</div></Col>
                        </Row>
                        <hr/>
                        <Row>
                            <Col lg = {2} style={{ color: "purple"}}>Email:</Col>
                            <Col lg = {10}><div class = "text-secondary">{superadmin.superadminEmail}</div></Col>
                        </Row>
                    </Container>
                </Card.Body>
            </Card>

            <div style={{ marginTop: '50px  ' }}>
                <ChangePassword/>
            </div>
        </div>
    );
}