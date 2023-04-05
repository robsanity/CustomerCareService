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

/*async function getAllProducts(){
    const response = await fetch(new URL('products', APIURL));
    const productsJson = await response.json();
    let products = productsJson.map((p) => ({id: p.id, name: p.name}))
    if (response.ok) {
        return products;
    } else {
        throw productsJson;
    }
}*/
async function addProfile(profile){
    return new Promise((resolve, reject) => {
        fetch(new URL('profiles', APIURL), {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                profile: profile
            }),
        }).then((response) => {
            if(response.ok){
                resolve(null);
            } else{
                response.json()
                    .then((message) => { reject(message); })
                    .catch(() => { reject({ error: "Cannot parse server response." }) }); // something else
            }
        }).catch(() => { reject({ error: "Cannot communicate with the server." }) })

    });

}
async function updateProfile(profile){
    return new Promise((resolve, reject) => {
        fetch(new URL('profiles/' + profile.email , APIURL), {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                profile: profile,
            }),
        }).then((response) => {
            if (response.ok) {
                resolve(null);
            } else {
                response.json()
                    .then((message) => { reject(message); })
                    .catch(() => { reject({ error: "Cannot parse server response." }) });
            }
        }).catch(() => { reject({ error: "Cannot communicate with the server." }) });
})
}
const API = {
    getAllProducts, getProductById, getProfileByEmail, addProfile, updateProfile
};

export default API;