import {useState} from "react";
import {Alert, Button, Col, Form, Row} from "react-bootstrap";

function SearchByID(props) {
    const [id, setId] = useState("");
    const [productBtn, setProductBtn] = useState(false);
    const handleSubmit = (event) => {
        event.preventDefault();
        props.searchProductById(id);
    }
    return (
        <>
            {productBtn && (
                <Row className={"padding-row"}>
                    <Col xl={2}>
                        <Form onSubmit={handleSubmit}>
                            <Form.Label>
                                <Form.Control type="id" placeholder="insert id" value={id}
                                              className={"border-margin"}
                                              onChange={(ev) => setId(ev.target.value)}/>
                            </Form.Label>
                        </Form>
                    </Col>
                    <Col xs={2}>
                        <Button variant={"outline-success"} className={"border-margin "}
                                onClick={handleSubmit}>
                            <i className="bi bi-search"/> Search
                        </Button>
                    </Col>
                    <Col xs={2}>
                        <Button variant={"outline-danger"} className={"border-margin "} id={"delete-btn"}
                                onClick={() => {
                                    setProductBtn(false);
                                    props.setProductSearch("");
                                    setId("");
                                }}>
                            <i className={"bi bi-backspace-fill"}/> Delete
                        </Button>
                    </Col>
                </Row>
            )}
            {!productBtn && (
                <Button variant="outline-warning" className={"border-margin"} onClick={() => setProductBtn(true)}>
                    Search product
                </Button>
            )}

            <div>
                {props.productSearch.id && productBtn && (
                    <Alert variant="success" className={"border-margin"} id={"search-result"}>
                        <Row>
                            <Col xs={"auto"}>
                                <p className={"fw-bold"}>ID: </p>
                            </Col>
                            <Col md={"auto"}>
                                <p>{props.productSearch.id}</p>
                            </Col>
                        </Row>
                        <Row>
                            <Col md={"auto"}>
                                <p className={"fw-bold"}>Name:</p>
                            </Col>
                            <Col md={"auto"}>
                                <p>{props.productSearch.name}</p>

                            </Col>
                        </Row>

                    </Alert>
                )}
            </div>
        </>
    );
}

export default SearchByID;