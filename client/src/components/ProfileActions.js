import {Button} from "react-bootstrap";
import React from "react";

function ProfileCreate() {
    return (
        <Button className={"me-4"}>
            <i className="bi bi-person-add"/> Create Profile
        </Button>
    );
}

function ProfileUpdate() {
    return (
        <Button>
            <i className="bi bi-person-up"/> Update Profile
        </Button>
    );
}

export {ProfileCreate, ProfileUpdate}