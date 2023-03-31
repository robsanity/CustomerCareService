import logo from './logo.svg';
import './App.css';
import {useEffect, useState} from "react";
import API from "./API";

function App() {

  const [products, setProducts] = useState([]);

  useEffect(() => {
    function loadProducts() {
      API.getAllProducts().then((list) => {
        setProducts(list);
      })
    }
    loadProducts();
  }, []);

  return (
    <div>
      <p>porcodio</p>
    </div>
  );
}

export default App;
