import { Row, Col, Card, Container } from "react-bootstrap";
import ChangePassword from "../commonComponents/ChangePassword";
import useAuth from '../../hooks/useAuth';
import useAxiosPrivate from "../../hooks/useAxiosPrivate";
import { useState } from "react";
import { useEffect } from "react";
import {Spinner} from "react-bootstrap";

async function fetchUserAdmin(axiosInstance, auth, setAdmin) {
    try {
        const response = await axiosInstance.get(`/admins/${auth.user}`);
        setAdmin(response.data);
    } catch (error) {
        console.error(error);
    }
}

export default function AdminProfile() {
    const { auth } = useAuth();
    const [admin, setAdmin] = useState({});
    const axiosPrivate = useAxiosPrivate();

    useEffect(() => { fetchUserAdmin(axiosPrivate, auth, setAdmin); }, []);
    console.log(admin);

    useEffect(() => {
        console.log(admin);
    }, [admin]);

    return (
        <div style={{ marginLeft: '250px', padding: '20px' }}>
            <h1 class = "bigPageTitle"> Profile Page </h1>
            <Card>
                <Card.Body>
                    <Container fluid>
                        <Row>
                            <Col lg = {2} style={{ color: "purple"}}>Full name:</Col>
                            <Col lg={10}><div class="text-secondary">{admin.adminName} {admin.adminSurname}</div></Col>
                        </Row>
                        <hr/>
                        <Row>
                            <Col lg = {2} style={{ color: "purple"}}>Email:</Col>
                            <Col lg = {10}><div class = "text-secondary">{admin.adminEmail}</div></Col>
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
