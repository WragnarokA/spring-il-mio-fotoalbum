/****CONSTANTI****/
const apiUrl = "http://localhost:8080/api/v1/photos";
const root = document.getElementById("root")
console.log(root);

/****FUNZIONI****/
// funzione che renderizza le category
const renderCategories = (categories) => {
    console.log(categories);
    let content;
    if (categories.length === 0) {
        content = 'No categories';
    } else {
        content = '<ul class="list-unstyled">';
        categories.forEach((cat) => {
            content += `<li>${cat.name}</li>`;
        });
        content += '</ul>';
    }
    return content;
};


// funzione che renderizza la card della photo
const renderPhoto = (element) => {
    console.log(element);
    return `
    <div class="d-flex  flex-wrap justify-content-between mt-3">
        <div class="card" style="width: 18rem;">
            <img src="${element.url}" class="card-img-top" style="max-height: 140px;">
            <div class="card-body">
              <h5 class="card-title">${element.title}</h5>
              <p class="card-text">${element.description}</p>
            </div>
            <ul class="list-group list-group-flush  mb-3" >
                <li class="list-group-item " aria-current="true"><h5>Categories:</h5></li>
                <li class="list-group-item"> ${renderCategories(element.categories)} </li>
            </ul>
        </div>
    </div>
     `
}

// funzione che renderizza la gallery di card 
const renderPhotoList = (data) => {
    let content;
    console.log(data);
    if (data.length > 0) {
        content = '<div class="row">'
        //itero sull array delle photo
        data.forEach((element) => {
            content += '<div class="col-3">'
            content += renderPhoto(element)
            content += '</div>';
        });
        content += '</div>';
    } else {
        content = '<div class="alert alert-info"> the list is empty </div>';
    }
    root.innerHTML = content;
}

// funzione che chiama l'api e ritorna un array di photo
const getPhoto = async () => {
    try {
        const response = await axios.get(apiUrl);
        renderPhotoList(response.data);
    } catch (error) {
        console.error(error)
    }
}


// cosice global che viene eseguito al Load dello scrip

getPhoto();















