import './App.css';
import {useEffect, useState} from "react";
import API from "./API";
import ProductsTable from "./Components/ProductsTable";
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap-icons/font/bootstrap-icons.css";
import SearchByID from "./Components/ProductSearch";
import Profiles from "./Components/Profile";

function App() {

    const [products, setProducts] = useState([]);
    const [productSearch, setProductSearch] = useState("");
    const [profiles, setProfiles] = useState([])
    const [message, setMessage] = useState("")

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
        } catch (e) {
            throw new TypeError(e);
        }
    }

    function searchProfileByEmail(email) {
        API.getProfileByEmail(email).then((profile) => {
            setProfiles(profile)
        })
            .catch(err => console.log(err));
    }

    function addProfile(profile) {
        API.addProfile(profile).then(() => setMessage("Profile added"))
            .catch(err => console.log(err));
    }

    function updateProfile(profile) {
        API.updateProfile(profile).then(() => setMessage("Profile updated")).catch(err => console.log(err))
    }

    return (
        <>
            <ProductsTable products={products} searchProductById={searchProductById} productSearch={productSearch}/>

            <SearchByID products={products} searchProductById={searchProductById}
                        productSearch={productSearch} setProductSearch={setProductSearch}/>

            <Profiles profiles={profiles} searchProfileByEmail={searchProfileByEmail} addProfile={addProfile}
                      updateProfile={updateProfile} message={message} setMessage={setMessage}/>
        </>
    );
}

export default App;
