import './App.css';
import {useEffect, useState} from "react";
import API from "./API";
import ProductsTable from "./Components/ProductsTable";
import "bootstrap/dist/css/bootstrap.min.css";

function App() {

    const [products, setProducts] = useState([]);
    const [productSearch, setProductSearch] = useState("");

    useEffect(() => {
        function loadProducts() {
            API.getAllProducts().then((list) => {
                setProducts(list);
            })
        }

        loadProducts();
    }, []);

    const searchProductById = async (id) => {
        try {
            const prod = await API.getProductById(id);
            setProductSearch(prod);
            console.log(productSearch)
        } catch (e) {
            throw new TypeError(e);
        }
    }

    return (
        <>
            <ProductsTable products={products} searchProductById={searchProductById} productSearch={productSearch}/>
        </>
    );
}

export default App;
