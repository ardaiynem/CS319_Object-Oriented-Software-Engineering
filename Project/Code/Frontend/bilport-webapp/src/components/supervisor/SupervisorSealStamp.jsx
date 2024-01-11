import { Card, Button, Form, Stack } from "react-bootstrap";

export default function SupervisorSealStamp(){
    return(
        <div style={{ marginLeft: '250px', padding: '20px' }}>
            <Card>
                <Card.Body>
                    <Card.Title>Upload Seal/Stamp</Card.Title>
                    <Stack direction="horizontal" gap={3}>
                        <Form.Control type="file"></Form.Control>
                        <Form.Text>If you already have a seal/stamp uploaded, new upload will override the previous one.</Form.Text>
                        <div className="vr" />
                        <Button> Upload </Button>
                    </Stack>
                    <hr/> 
                    <Card.Title>Download Seal/Stamp</Card.Title>
                    <Button>Download</Button>
                </Card.Body>
            </Card>
        </div>
    );
}