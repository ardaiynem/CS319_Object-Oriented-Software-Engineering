import { useRef, useState, useEffect, useLayoutEffect } from 'react';
import useAuth from '../hooks/useAuth';
import { Link, useNavigate, useLocation } from 'react-router-dom';
import { Button, Alert, Form, Card } from 'react-bootstrap';

import axios from '../api/axios';

const LOGIN_URL = '/auth/signin';

const Login = () => {
    const { auth, setAuth } = useAuth();

    const navigate = useNavigate();
    const location = useLocation();
    const from = location.state?.from?.pathname || "/";

    const userRef = useRef();
    const errRef = useRef();

    const [user, setUser] = useState('');
    const [pwd, setPwd] = useState('');
    const [errMsg, setErrMsg] = useState('');

    useEffect(() => {
        userRef.current.focus();
    }, [])

    useEffect(() => {
        setErrMsg('');
    }, [user, pwd])

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            const encodedBase64Token = btoa(user + ":" + pwd);

            const response = await axios.get(LOGIN_URL,
                {
                    headers: { 'Content-Type': 'application/json', 'Authorization': `Basic ${encodedBase64Token}` },
                    withCredentials: true
                }
            );
            console.log(JSON.stringify(response?.data));
            //console.log(JSON.stringify(response));
            const accessToken = response?.data?.accessToken;
            const roles = response?.data?.roles;
            //const username = response?.data?.username;
            setAuth({ user, pwd, roles, accessToken });
            setUser('');
            setPwd('');
            navigate(from, { replace: true });
        } catch (err) {
            if (!err?.response) {
                setErrMsg('No Server Response');
            } else if (err.response?.status === 400) {
                setErrMsg('Missing Username or Password');
            } else if (err.response?.status === 401) {
                setErrMsg('Unauthorized');
            } else {
                setErrMsg('Login Failed');
            }
            errRef.current.focus();
        }
    }

    return (
        <section className='loginPage'>
            <Card style={{padding: "50px"}}>
                <Card.Body className="d-flex flex-column align-items-center">
                    <Alert ref={errRef} className={errMsg ? "errmsg" : "offscreen"} aria-live="assertive">{errMsg}</Alert>
                    <h1><i class="fa-solid fa-shield-cat fa-xl" style={{color: "#801ba7"}}></i></h1>
                    <h1>Bilport</h1>
                    <Form onSubmit={handleSubmit}>
                        <Form.Label htmlFor="username">Username (ID):</Form.Label>
                        <Form.Control
                            as="input"
                            type="text"
                            id="username"
                            ref={userRef}
                            autoComplete="off"
                            onChange={(e) => setUser(e.target.value)}
                            value={user}
                            required
                        />

                        <Form.Label htmlFor="password">Password:</Form.Label>
                        <Form.Control
                            as="input"
                            type="password"
                            id="password"
                            onChange={(e) => setPwd(e.target.value)}
                            value={pwd}
                            required
                        />

                        <style type="text/css">
                            {`
            .btn-flat {
            background-color: purple;
            color: white;
            }
            `}
                        </style>
                        <br/>
                        <div style={{ display: 'flex', justifyContent: 'center' }}>
                        <Button variant="flat" type="submit" size='lg'>Sign In</Button>
                        </div>
                    </Form>
                </Card.Body>
            </Card>
        </section>
    )
}

export default Login