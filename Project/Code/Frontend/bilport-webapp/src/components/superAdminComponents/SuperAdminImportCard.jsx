import { Card, Form, Stack, Button } from "react-bootstrap";

export default function SuperAdminImportCard(props){
    return(
        <div class="standaloneCard">
            <Card
                style={{color: "#008000"}}
            >
                <Card.Body>
                    <Form.Group>
                        <Form.Label>Import {props.importUserType} from Excel spreadsheet:</Form.Label>
                        <Stack direction="horizontal" gap={3}>
                            <Form.Control type="file" />
                            <hr/>
                            <Button variant="success">Import</Button>
                        </Stack>
                    </Form.Group>
                </Card.Body>
            </Card>
        </div>
    );
}