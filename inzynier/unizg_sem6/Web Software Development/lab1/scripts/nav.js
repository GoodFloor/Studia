if (localStorage.currentCategory == null) {
    localStorage.currentCategory = data.categories[0].name;
}

let currentCategoryName = localStorage.currentCategory;
document.getElementById("currentCategoryName").innerHTML = currentCategoryName;

let categories = data.categories;
let currentCategoryData;
let menu = document.getElementById("menuCategories");
for (let i = 0; i < categories.length; i++) {
    let li = document.createElement("li");
    li.innerHTML = categories[i].name;
    if (categories[i].name != currentCategoryName) {
        li.addEventListener("click", function() {
            localStorage.currentCategory = categories[i].name;
            location.reload();
        })
    } else {
        li.classList = "disabled";
        currentCategoryData = categories[i];
    }
    menu.appendChild(li);
}
