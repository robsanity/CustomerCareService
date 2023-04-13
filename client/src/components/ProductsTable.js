import {Button, Card, Col, Form, Row, Table} from "react-bootstrap"
import {useState} from "react";

function ProductsTable(props) {
    return (
        <Card body={true}>
            <Card.Title>
                Products
                <small className="text-body-secondary float-end">GET /api/products/</small>
            </Card.Title>
            <Table responsive>
                <thead>
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">Name</th>
                </tr>
                </thead>
                <tbody>
                {props.products.map((product) => (
                    <tr key={product.id}>
                        <td>{product.id}</td>
                        <td>{product.name}</td>
                    </tr>
                ))}
                </tbody>
            </Table>
        </Card>
    );
}

function SearchByID(props) {
    const [id, setId] = useState("");
    const handleSubmit = (event) => {
        event.preventDefault();
        props.searchProductById(id);

    }
    return (
        <>
            {!props.productSearch && (
                <Row>
                    <Col>
                        Search a product
                    </Col>
                    <Col>
                        <Form onSubmit={handleSubmit}>

                            <Form.Label>
                                <Form.Control type="id" placeholder="insert an id" value={id}
                                              onChange={(ev) => setId(ev.target.value)}/>
                            </Form.Label>
                            <Col>
                                <Button className="mb-3" variant="primary" type="submit">Search</Button>
                            </Col>
                        </Form>
                    </Col>
                </Row>
            )}
            {props.productSearch && (
                <div className={"card"}>
                    <div className={"card-header"}>
                        {props.productSearch.id}
                    </div>
                    <ul className={"list-group-item"}>
                        {props.productSearch.name}
                    </ul>

                </div>
            )}
        </>
    );
}

export default ProductsTable;