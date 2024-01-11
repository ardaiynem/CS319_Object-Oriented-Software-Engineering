import AdminEvaluators from "../adminComponents/AdminEvaluators";
import SuperAdminImportCard from "./SuperAdminImportCard";

import React, { useState, useEffect } from 'react';
import { Form, Card, Button, Stack } from 'react-bootstrap';
import useAxiosPrivate from "../../hooks/useAxiosPrivate";

export default function SuperAdminEvaluators(props){
    const [selectedFile, setSelectedFile] = useState(null);
    const [uploadStatus, setUploadStatus] = useState(null);
    
    const axiosPrivate = useAxiosPrivate();
    
    const handleFileChange = (event) => {
        const file = event.target.files[0];
        setSelectedFile(file);
    };
    
    const handleSubmit = async (event) => {
        event.preventDefault();
    
        if (selectedFile) {
          const formData = new FormData();
          formData.append('file', selectedFile);
          console.log('a');
      
        
          try {
              const response = await axiosPrivate.post('/superadmins/evaluatorImport',
                  formData,
                  {
                      headers: { "Content-Type": "multipart/form-data" },
                  }
              );
              setUploadStatus(response.data);
              console.log('of of komur gibi yanıyorum');
          } catch (err) {
              console.error(err);
          }
        }
    }; 

    return (
        <div className="standaloneCard">
        <Card style={{ color: '#008000' }}>
          <Card.Body>
            <Form.Group>
              <Form.Label>Import {props.importUserType} from Excel spreadsheet:</Form.Label>
              <Stack direction="horizontal" gap={3}>
                <Form.Control type="file" accept=".xlsx, .xls" onChange={handleFileChange} />
                <hr />
                <Button variant="success" onClick={handleSubmit}>
                  Import
                </Button>
              </Stack>
              </Form.Group>
                {uploadStatus && (
                <p> {typeof uploadStatus === 'string' ? uploadStatus : 'Failed to import data'}</p>
              )}
            </Card.Body>
          </Card>
          <br/>
            <AdminEvaluators evaluators = {props.evaluators}/>
        </div>
      );
}