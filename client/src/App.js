import './App.css';
import React, {useEffect, useState} from "react";
import API from "./API";
import ProductsTable from "./components/ProductsTable";
import { Route } from 'react-router-dom';
import "bootstrap/dist/css/bootstrap.min.css";
import {Button, Card, Col, Container, Row} from "react-bootstrap";
import * as PropTypes from "prop-types";
import "bootstrap-icons/font/bootstrap-icons.css";
import ProductSearch from "./components/ProductSearch";
import ProfileSearch from "./components/ProfileSearch";
import { ProfileCreate, ProfileUpdate } from "./components/ProfileActions";

Route.propTypes = {
    path: PropTypes.string,
    element: PropTypes.any
};

function App() {

    const [products, setProducts] = useState([]);
    const [productSearch, setProductSearch] = useState("");
    const [profileSearch, setProfileSearch] = useState("");
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
        API.getProductById(id).then((product) => {
            setProductSearch(product);
        }).catch(err => {
            setProductSearch(undefined)
            throw new TypeError(err);
        })
    }

    const searchProfileByEmail = async (email) => {
        API.getProfileByEmail(email).then((profile) => {
            setProfileSearch(profile)
        }).catch(err => {
            setProfileSearch(undefined)
            throw new TypeError(err)
        });
    }

    function addProfile(profile) {
        API.addProfile(profile).then(() => setMessage("Profile added"))
            .catch(err => console.log(err));
    }

    function updateProfile(profile) {
        API.updateProfile(profile).then(() => setMessage("Profile updated")).catch(err => console.log(err))
    }

    return (
        <Container className={"py-5 px-3"}>
            <Row>
                <ProductsTable products={products} searchProductById={searchProductById} productSearch={productSearch}
                               message={message} setMessage={setMessage}/>
            </Row>
            <Row className={"my-4"}>
                <Col className={"ps-0"}>
                    <ProductSearch products={products} searchProductById={searchProductById}
                                   productSearch={productSearch} setProductSearch={setProductSearch}/>
                </Col>
                <Col className={"pe-0"}>
                    <ProfileSearch searchProfileByEmail={searchProfileByEmail} profileSearch={profileSearch}
                                   setProfileSearch={setProfileSearch}/>
                </Col>
            </Row>
            <Row className={"text-center"}>
                <Col></Col>
                <Col>
                    <ProfileCreate/>
                    <ProfileUpdate/>
                </Col>
            </Row>
        </Container>
    );
}

export default App;
