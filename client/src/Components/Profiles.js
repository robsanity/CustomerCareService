import {useState} from "react";
import {Form} from "react-bootstrap";

/* TODO */
function Profiles(props) {
    const [profile, setProfile] = useState("");

    return (
        <>
            {!profile && <SearchByEmail profile={profile} setProfile={setProfile}/>}
        </>
    )
}

function SearchByEmail(props) {
    const handleSubmit = (event) => {
        event.preventDefault();
    }
    return (
        <>
            <Form>
                <Form.Label>
                    <Form.Control type="email" placeholder="insert an email" value={props.profile}
                    />
                </Form.Label>
            </Form>

        </>
    )
}

export default Profiles;