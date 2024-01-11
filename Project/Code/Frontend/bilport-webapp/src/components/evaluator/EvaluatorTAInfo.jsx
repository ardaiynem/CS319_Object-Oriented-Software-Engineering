import { Card, Row, Col, Container } from "react-bootstrap";

import useAuth from '../../hooks/useAuth';
import useAxiosPrivate from "../../hooks/useAxiosPrivate";
import { useState } from "react";
import { useEffect } from "react";
import { Spinner } from "react-bootstrap";
import axios from "../../api/axios";
import CourseTAInfo from "../commonComponents/CourseTAInfo";

export default function EvaluatorTAInfo() {
    return (
        <div style={{ ...{ marginLeft: '250px', padding: '20px' } }}>
            <h1 class="bigPageTitle">  </h1>
            <div className="standaloneCard">
                <CourseTAInfo customStyle={{ marginLeft: 0, padding: 0 }} course="CS299" />
                <CourseTAInfo customStyle={{ marginLeft: 0, padding: 0 }} course="CS399" />
            </div>
        </div>
    );
}
