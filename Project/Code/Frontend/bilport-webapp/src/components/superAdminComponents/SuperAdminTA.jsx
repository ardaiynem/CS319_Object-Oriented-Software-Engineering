import CourseTAInfo from "../commonComponents/CourseTAInfo";
import { Accordion, Form, Button } from "react-bootstrap";
import { useState, useEffect } from "react";
import useAxiosPrivate from "../../hooks/useAxiosPrivate";

export default function SuperAdminTA(){
    return(
        <div >
            <div style={{marginLeft: "0px !important"}}>
                <CourseTAInfo customStyle={{ marginLeft: 0, padding: 0 }} course="CS299" />
            </div>
            <br/>
            <SetCourseTA course="CS299"/>  
            <hr style={{color: "white"}}/>
            <div style={{marginLeft: "0px !important"}}>
                <CourseTAInfo customStyle={{ marginLeft: 0, padding: 0 }} course="CS399"  />
            </div>
            <br/>
            <SetCourseTA course="CS399"/>  
        </div>
    );

}

function SetCourseTA(props){
    const [newTa, setNewTa] = useState({});
    const axiosPrivate = useAxiosPrivate();

    async function fetchNewTa(axiosPrivate, newTa) {
        try {
            const response = await axiosPrivate.post('/courses/set/' + props.course, newTa);
            console.log(response.data)
        } catch (err) {
            console.error(err);
        }
    }

    return(
        <div>
            <Accordion>
                <Accordion.Item eventKey="0">
                    <Accordion.Header>Set Course TA for {props.course}</Accordion.Header>
                    <Accordion.Body>                   
                        <Form.Group className="mb-3" controlId="changeSupervisorInfo"  >
                            <Form.Label>Full name:</Form.Label>
                            <Form.Control type="text" placeholder="Enter the full name of the TA" onChange={(e) => setNewTa({ ...newTa, taFullName: e.target.value })}/>
                            <Form.Label>Email:</Form.Label>
                            <Form.Control type="text" placeholder="Enter the mail address of the TA" onChange={(e) => setNewTa({ ...newTa, taEmail: e.target.value })}/>
                            <Form.Label>ID:</Form.Label>
                            <Form.Control type="text" placeholder="Enter the Bilkent ID of the TA" onChange={(e) => setNewTa({ ...newTa, userName: e.target.value })}/>
                            <Form.Label>Initial Password:</Form.Label>
                            <Form.Control type="password" placeholder="Set the initial password of the TA" onChange={(e) => setNewTa({ ...newTa, userPassword: e.target.value })}/>
                        </Form.Group>
                        <Button variant="outline-primary" onClick={() => fetchNewTa(axiosPrivate, newTa)}> Submit</Button>
                    </Accordion.Body>
                </Accordion.Item>
            </Accordion>
        </div>
    );
}