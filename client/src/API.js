const APIURL = "http://localhost:8080";

async function getAllProducts() {
    const url = APIURL + "/products/";
    try{
        const response = await fetch(url);
        if(response.ok){
            const list = await response.json();
            return list;
        } else {
            const text = await response.text();
            throw new TypeError(text);
        }
    } catch (err) {
        throw err;
    }
}

const API = {
    getAllProducts,
};

export default API;