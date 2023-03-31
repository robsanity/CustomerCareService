const APIURL = "http://localhost:8080";

async function getAllProducts() {
    const url = APIURL + "/products/";
    try {
        const response = await fetch(url);
        if (response.ok) {
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

async function getProductById(id) {
    const url = APIURL + "/products/" + id;
    try {
        const response = await fetch(url);
        if (response.ok) {
            const prod = await response.json();
            return prod;
        } else {
            const text = await response.text();
            throw new TypeError(text)
        }
    } catch (err) {
        throw err;
    }

}

async function getProfileByEmail(email) {
    const url = APIURL + "/profiles/" + email;
    try {
        const response = await fetch(url);
        if (response.ok) {
            const profile = await response.json();
            return profile;
        } else {
            const text = await response.text();
            throw new TypeError(text);
        }
    } catch (err) {
        throw err;
    }
}

const API = {
    getAllProducts, getProductById, getProfileByEmail
};

export default API;