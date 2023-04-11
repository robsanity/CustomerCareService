import './App.css';
import React, {useEffect, useState} from "react";
import API from "./API";
import ProductsTable from "./Components/ProductsTable";
import { BrowserRouter as Router, Routes, Route, Navigate, useNavigate } from 'react-router-dom';
import Profiles from "./Components/Profiles";
import "bootstrap/dist/css/bootstrap.min.css";
import {Container, Row} from "react-bootstrap";
import * as PropTypes from "prop-types";


Route.propTypes = {
    path: PropTypes.string,
    element: PropTypes.any
};

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
            console.log(productSearch)
        } catch (e) {
            throw new TypeError(e);
        }
    }

    function searchProfileByEmail(email){
        API.getProfileByEmail(email).then((profile) => {setProfiles(profile) })
            .catch(err => console.log(err));
    }
    function addProfile(profile) {
        API.addProfile(profile).then( () => setMessage("Profile added"))
            .catch(err => console.log(err));
    }
    function updateProfile(profile) {
        API.updateProfile(profile).then(() => setMessage("Profile updated")).catch(err => console.log(err))
    }
    return (
        <>
            <Container>
            <Row>
            <ProductsTable products={products} searchProductById={searchProductById} productSearch={productSearch}/>
            </Row>
            <Row>
                <Profiles profiles={profiles} setProfiles={setProfiles} searchProfileByEmail={searchProfileByEmail} addProfile={addProfile} updateProfile={updateProfile} message={message} setMessage={setMessage} />
            </Row>
            </Container>
            </>
    );
}

export default App;
