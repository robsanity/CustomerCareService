import {Alert, Button, Form, Modal} from "react-bootstrap";
import React, {useState} from "react";

export const ProfileActionsMode = {
    CREATE: 0,
    UPDATE: 1
}
export function ProfileActions(props) {
    const [show, setShow] = useState(false);
    const [email, setEmail] = useState("");
    const [name, setName] = useState("");
    const [surname, setSurname] = useState("");

    const handleClose = () => {
        setShow(false);
        props.setModalError();
        setEmail();
        setName();
        setSurname();
    }
    const handleShow = () => setShow(true);

    const handleSubmit = (ev) => {
        ev.stopPropagation()
        ev.preventDefault()
        const profile = {
            "email": email,
            "name": name,
            "surname": surname
        }
        props.setModalError()

        if(props.action === ProfileActionsMode.CREATE)
            props.addProfile(profile)
        else
            props.updateProfile(profile)
    }

    return (
        <>
        <Button className={"me-4"} onClick={handleShow}>
            <i className="bi bi-person-add"/> {props.action === ProfileActionsMode.CREATE ? "Create" : "Update"} Profile
        </Button>

        <Modal show={show} onHide={handleClose}>
            <Modal.Header closeButton>
                <Modal.Title>{props.action === ProfileActionsMode.CREATE ? "Create" : "Update"} Profile</Modal.Title>
            </Modal.Header>
            <Modal.Body>
                {props.action === ProfileActionsMode.CREATE && props.modalError && (
                    <Alert variant={props.modalError.status === 409 ? "danger" : "success"}>
                        {props.modalError.status === 409 ? props.modalError.detail : "Profile created successfully!"}
                    </Alert>
                )}
                {props.action === ProfileActionsMode.UPDATE && props.modalError && (
                    <Alert variant={props.modalError.status === 404 ? "danger" : "success"}>
                        {props.modalError.status === 404 ? props.modalError.detail : "Profile updated successfully!"}
                    </Alert>
                )}
                <Form id={"createProfileForm"} onSubmit={handleSubmit}>
                    <Form.Group className="mb-3" controlId="createProfileEmail">
                        <Form.Label>Email address</Form.Label>
                        <Form.Control type="email" placeholder="info@example.com" required={true} value={email} onChange={(e) => setEmail(e.target.value)} />
                    </Form.Group>
                    <Form.Group className="mb-3" controlId="createProfileName" required value={name} onChange={(e) => setName(e.target.value)}>
                        <Form.Label>Name</Form.Label>
                        <Form.Control type="text" placeholder="Walter" />
                    </Form.Group>
                    <Form.Group className="mb-3" controlId="createProfileSurname" required value={surname} onChange={(e) => setSurname(e.target.value)}>
                        <Form.Label>Surname</Form.Label>
                        <Form.Control type="text" placeholder="White" />
                    </Form.Group>
                </Form>
            </Modal.Body>
            <Modal.Footer>
                <Button variant="secondary" onClick={handleClose}>
                    Close
                </Button>
                <Button variant="primary" form={"createProfileForm"} type={"submit"}>
                    Submit
                </Button>
            </Modal.Footer>
        </Modal>
        </>
    );
}