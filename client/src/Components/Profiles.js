import {useState} from "react";
import {Form} from "react-bootstrap";

function Profiles(props) {
    const [profile, setProfile] = useState("");

    return (
        <>
            {!profile && <SearchByEmail profile={profile} setProfile={setProfile()}/>}
        </>
    )
}

function SearchByEmail(props) {
    const handleSubmit = (event) => {
        event.preventDefault();
        // TODO: add props.searchProfileByEmail(props.profile);
    }
    return (
        <>
            <Form>
                <Form.Label>
                    <Form.Control type="id" placeholder="insert an id" value={props.profile}
                    />
                </Form.Label>
            </Form>
        </>
    )
}

export default Profiles;