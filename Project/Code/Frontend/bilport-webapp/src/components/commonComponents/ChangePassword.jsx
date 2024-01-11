import { Form, Card, Button } from "react-bootstrap";

export default function ChangePassword(){
    return(
        <Card>
            <Card.Body>
                <Card.Title>Change Password</Card.Title>
                <hr/>
                <Form.Group className="mb-3" controlId="changePasswordOldPassword">
                    <Form.Label>Old password:</Form.Label>
                    <Form.Control type="password" placeholder="Enter your old password" />
                </Form.Group>
                <Form.Group className="mb-3" controlId="changePasswordNewPassword">
                    <Form.Label>New password:</Form.Label>
                    <Form.Control type="password" placeholder="Enter your new password" />
                </Form.Group>
                <Button variant="warning"> Change</Button>
            </Card.Body>
        </Card>
    );
}
