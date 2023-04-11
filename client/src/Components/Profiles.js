import {useState} from "react";
import {Button, Col, Form, Row} from "react-bootstrap";
import {compareArraysAsSet} from "@testing-library/jest-dom/dist/utils";

/* TODO */
function Profiles(props) {
    const[profile, setProfile] = useState([])
    const[upProfile, setUpProfile] = useState([])
    return (
        <>
            { <SearchByEmail upProfile={upProfile} setUpProfile={setUpProfile} profile={profile} setProfile={setProfile} searchProfileByEmail={props.searchProfileByEmail} addProfile={props.addProfile} updateProfile={props.updateProfile}/>  }
        </>
    )
}

function SearchByEmail(props) {
    const [op, setOp]= useState("")
    const [email, setEmail] =useState("")
    const handleSubmit = (event) => {
        event.preventDefault();
        if(op==="post") {
            props.addProfile(props.profile)
        }
        else if(op==="put"){
            props.updateProfile(props.upProfile)
        }
        else if(op==="search"){
            props.searchProfileByEmail(email)
        }
    }

    return (
        <>
            <Row>
                <Col>
                    Search a profile
                </Col>
                <Col>
                    <Form onSubmit={handleSubmit}>
                        <Col>
                        <Form.Label onChange={ event => setOp("search")}>
                            <Form.Control type="email" placeholder="Insert an email" value={email} onChange={event => setEmail(event.target.value)}></Form.Control>
                        </Form.Label>
                        </Col>
                <Col>
                        <Button className="mb-3" variant="primary" type="submit">Search</Button>
                </Col>
                    </Form>
                </Col>
                <Row> {props.profiles} </Row>
            </Row>
            <Row>
                <Col>Post a profile</Col>
                <Col>
                <Form  onSubmit={handleSubmit} >
                    <Col>
                    <Form.Label onChange={ event => setOp("post")}>
                        <Form.Control type="email" placeholder="Insert an email" value={props.profile.email} onChange={event => props.setProfile({"email": event.target.value})} />
                        <Form.Control type="string" placeholder="Insert a name" value={props.profile.name}  onChange={event => props.setProfile({"name": event.target.value})}/>
                        <Form.Control type="string" placeholder="Insert a surname" value={props.profile.surname} onChange={event => props.setProfile({"surname":event.target.value})}  />
                    </Form.Label>
                    </Col>
                    <Col>
                    <Button className="mb-3" variant="primary" type="submit">Post</Button>
                    </Col>
                    </Form>
                </Col>
            </Row>
            <Row>
                <Col>Update a profile</Col>
                <Col>
            <Form onSubmit={handleSubmit}>
                <Col>
                <Form.Label onChange={ event => setOp("put")}>
                    <Form.Control type="email" placeholder="Insert an email" value={props.upProfile.email} onChange={event => props.setUpProfile({"email": event.target.value})} />
                    <Form.Control type="string" placeholder="Insert a name" value={props.upProfile.name}  onChange={event => props.setUpProfile({"name": event.target.value})}/>
                    <Form.Control type="string" placeholder="Insert a surname" value={props.upProfile.surname} onChange={event => props.setUpProfile({"surname":event.target.value})}  />
                </Form.Label>
                </Col>
                <Col>
                <Button className="mb-3" variant="primary" type="submit" >Put</Button>
                </Col>
            </Form>
                </Col>
            </Row>
            <Row>{props.message}</Row>

        </>
    )
}

export default Profiles;