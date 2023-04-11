import {Form, Table} from "react-bootstrap"
import {useState} from "react";

function ProductsTable(props) {
    return (
        <>
            <th>Products</th>
            <Table className="table table-striped">
                <thead>
                <tr>
                    <th scope="col">Id</th>
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
            <SearchByID products={props.products} searchProductById={props.searchProductById}
                        productSearch={props.productSearch}/>
        </>
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
                <Form onSubmit={handleSubmit}>
                    <Form.Label>
                        <Form.Control type="id" placeholder="insert an id" value={id}
                                      onChange={(ev) => setId(ev.target.value)}/>
                    </Form.Label>
                </Form>
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