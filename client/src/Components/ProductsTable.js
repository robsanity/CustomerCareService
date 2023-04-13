import {Table} from "react-bootstrap"

function ProductsTable(props) {
    return (
        <>
            <h2>PRODUCTS TABLE</h2>
            <Table className="table table-striped resize border-margin">
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
        </>
    );
}


export default ProductsTable;