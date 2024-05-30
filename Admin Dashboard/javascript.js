


let nextUserId =6;
let nextOrderId =5;
let nextPlanId=3;



function setActive(element) {
    
    let links = document.querySelectorAll('.sidebar a');
    links.forEach(function(link) {
        link.classList.remove('active');
    });

    
    element.classList.add('active');
}

function confirmSignOut(){



    showLoginContent('Log-SignContainer');

}

function showPage(pageId) {
    // Hide all pages
    const pages = document.querySelectorAll('.page');
    pages.forEach(page => page.classList.add('hidden'));

    // Show the selected page
    const selectedPage = document.getElementById(pageId);
    selectedPage.classList.remove('hidden');
    renderTableClassUsers();
    renderTableClassOrders();
    renderTableClassProducts();
}









   

function openPopup(popupId) {
    document.getElementById(popupId).style.display = 'block';
}

// Function to close a popup
function closePopup(popupId) {
    document.getElementById(popupId).style.display = 'none';
}


class Plan{
    constructor(ProductID,Productname,Duration,Price){
this.ProductID = ProductID;
this.Productname= Productname;
this.Duration = Duration;

this.price = this.calculatePrice();

    }
    calculatePrice() {
        console.log("poipoi")
        const prices = {
            'basic': {
                '1-month': 50,
                '3-months': 120,
                '6-months': 200
            },
            'premium': {
                '1-month': 80,
                '3-months': 200,
                '6-months': 350
            }
        };
        return prices[this.Productname][this.Duration];
    }
}

let Products =[];

Products.push(new Plan(1,"basic","6-months"));
Products.push(new Plan(2,"premium","3-months"));
Products.push(new Plan(3,"premium","6-months"));


function renderTableClassProducts() {
    document.getElementById('ProductTABLE').innerHTML = '';
    Products.forEach(Product => {
        const tr = document.createElement('tr');
    
        const trcontent = `
        <td>${Product.ProductID}</td>
        <td>${Product.Productname}</td>
        <td>${Product.Duration}</td>
        
        <td>${Product.price}</td>
        
        
        `;
    console.log("igjkh")
        tr.innerHTML = trcontent;
        
       
document.getElementById('ProductTABLE').appendChild(tr);
    });
}

function addPlan() {
    let newProductName = document.getElementById('newProductName').value;
    let newDuration = document.getElementById('newProductDuration').value;

    document.getElementById('AddProducterrorError').classList.add('hidden');
    document.getElementById('AddProductDurationError').classList.add('hidden');
    
    // Validate input fields
    if (newProductName === "" || newDuration === "") {
        document.getElementById('AddProductDurationError').textContent = 'Please fill all values';
        document.getElementById('AddProductDurationError').classList.remove('hidden');
        return;
    }

    // Check if the plan is valid
    document.getElementById('AddProductDurationError').textContent = 'please enter valid duration.   1-month,2-months,3-months or 6-months';

    // Check if the duration is valid
    if (!isValidDurationonly(newDuration)) {
        document.getElementById('AddProductDurationError').classList.remove('hidden');
        return;
    }
    
    // Create a new Plan object
    let newPlan = new Plan(nextPlanId++, newProductName, newDuration);

    // Push the new plan to the Plans array (assuming it's defined globally)
    Products.push(newPlan);

    console.log('Adding new plan:', newPlan);
    

    // Close the popup after adding the plan
    closePopup('addPopupProduct');

    // Render the updated table of plans
    renderTableClassProducts(); // You need to implement this function to render the plan table
}

function editPlan() {
    let planId = parseInt(document.getElementById('ProductIdedit').value);
    let newProductName = document.getElementById('ProductNameedit').value;
    let newDuration = document.getElementById('newProductDurationedit').value;
   

   


    

    document.getElementById('EditErrorDuration').classList.add('hidden');
    document.getElementById('EditErrorProductname').classList.add('hidden');
    
    // Validate input fields
    if (newProductName === "" || newDuration === "") {
        document.getElementById('EditErrorDuration').textContent = 'Please fill both values';
        document.getElementById('EditErrorDuration').classList.remove('hidden');
        return;
    }

    // Check if the plan is valid
    document.getElementById('EditErrorDuration').textContent = 'Please enter 1-month, 2-month or 3 month';

    // Check if the duration is valid
    if (!isValidDurationonly(newDuration)) {
        document.getElementById('EditErrorDuration').classList.remove('hidden');
        return;
    }

    // Find the plan to edit by its ID
    console.log(Products)
    const planToEdit = Products.find(plan => plan.ProductID === planId);

    if (!planToEdit) {
        alert('Plan not found');
        return;
    }

    // Update the plan with new values
    planToEdit.Productname = newProductName;
    planToEdit.Duration = newDuration;

    console.log('Editing plan:', planToEdit);

    // Close the popup after editing the plan
    closePopup('editPopupProduct');

    // Render the updated table of plans
    renderTableClassProducts(); // You need to implement this function to render the plan table
}

function deletePlan() {
    // Get the plan ID to delete
    let planIdToDelete = parseInt(document.getElementById('pproductID').value);

    // Find the index of the plan to delete by its ID
    const indexToDelete = Products.findIndex(plan => plan.ProductID === planIdToDelete);
    document.getElementById('DeleteIDError').classList.add('hidden');

    // Check if the plan ID is valid
    if (isNaN(planIdToDelete)) {
        document.getElementById('DeleteIDError').classList.remove('hidden');
        return;
    }

    // Check if the plan exists
    if (indexToDelete === -1) {
        document.getElementById('DeleteIDError').classList.remove('hidden');
        return;
    }

    // Remove the plan from the Products array
    Products.splice(indexToDelete, 1);

    console.log('Deleting plan with ID:', planIdToDelete);

    // Close the popup after deleting the plan
    closePopup('deletePopupProduct');

    // Render the updated table of plans
    renderTableClassProducts();
}






class Order {
    constructor(planID,planType, duration) {
        this.planID=planID;
        this.planType = planType;
        this.duration = duration;
        this.price = this.calculatePrice();
    }

    calculatePrice() {
        const prices = {
            'basic': {
                '1-month': 50,
                '3-months': 120,
                '6-months': 200
            },
            'premium': {
                '1-month': 80,
                '3-months': 200,
                '6-months': 350
            }
        };
        return prices[this.planType][this.duration];
    }

     }


let Orders =[];

Orders.push(new Order(1,"basic","6-months"));
Orders.push(new Order(2,"premium","3-months"));
Orders.push(new Order(3,"premium","6-months"));
Orders.push(new Order(4,"basic","1-month"));


function addOrder() {
    
    let newPlan = document.getElementById('newPlan').value;
    let newDuration = document.getElementById('newDuration').value;

    document.getElementById('newPlanError').classList.add('hidden');
    document.getElementById('newDurationError').classList.add('hidden');
    // Validate input fields
   
   
    if ( newPlan === "" || newDuration === "") {
        document.getElementById('newDurationError').textContent='please fill both values';
        document.getElementById('newDurationError').classList.remove('hidden');

        return;
    }

    document.getElementById('newDurationError').textContent='Please enter Duration from 1 to 3 months';

 if(!isValidOrder(newPlan,newDuration)){
    
document.getElementById('newPlanError').classList.remove('hidden');
document.getElementById('newDurationError').classList.remove('hidden');
if (isValidPlan(newPlan)) {
    document.getElementById('newPlanError').classList.add('hidden');
}
if (isValidDuration(newDuration)) {
    document.getElementById('newDurationError').classList.add('hidden');
}
return;

 } 
    

    // Create a new Order object
    let newOrder = new Order(nextOrderId++, newPlan, newDuration);

    // Push the new order to the orders array (assuming it's defined globally)
    Orders.push(newOrder);

    console.log('Adding new order:', newOrder);
    console.log(Orders);

    // Close the popup after adding the order
    closePopup('addPopupOrder');

    // Render the updated table of orders
    renderTableClassOrders(); // You need to implement this function to render the order table
}

function editOrder() {
    let orderId = parseInt(document.getElementById('Ordertoedit').value);
    let newPlan = document.getElementById('newplanedit').value;
    let newDuration = document.getElementById('newDurationedit').value;

    document.getElementById('editIDError').classList.add('hidden');

    // Find the order to edit by its ID
    const orderToEdit = Orders.find(order => order.planID === orderId);

    if (!orderToEdit){

        document.getElementById('editIDError').classList.remove('hidden');
        return;
    }
   

   
 // Hide error messages initially
 document.getElementById('editPlanError').classList.add('hidden');
 document.getElementById('editDurationError').classList.add('hidden');

 // Validate input fields
 if (newPlan === "" || newDuration === ""|| orderId ==="") {
     document.getElementById('editDurationError').textContent = 'Please fill  values';
     document.getElementById('editDurationError').classList.remove('hidden');
     return;
 }
 document.getElementById('editDurationError').textContent = 'Please enter 1 to 3 months';
 // Check if the order is valid
 if (!isValidOrder(newPlan, newDuration)) {
     // Display error messages for invalid inputs
     document.getElementById('editPlanError').classList.remove('hidden');
     document.getElementById('editDurationError').classList.remove('hidden');
     
     // Hide error message if corresponding input is valid
     if (isValidPlan(newPlan)) {
         document.getElementById('editPlanError').classList.add('hidden');
     }
     if (isValidDuration(newPlan, newDuration)) {
         document.getElementById('editDurationError').classList.add('hidden');
     }
     return;
 }
    // Update the order with new values
    

    let newOrder = new Order(orderId,newPlan,newDuration);
    const index = Orders.findIndex(order => order.planID === orderId);
    Orders[index] = newOrder;


    // Close the popup after editing the order
    closePopup('editPopupOrder');

    // Render the updated table of orders
    renderTableClassOrders();
}

function deleteOrder() {
    let orderIdToDelete = parseInt(document.getElementById('OrderIDDelete').value);

    // Find the index of the order to delete by its ID
    const indexToDelete = Orders.findIndex(order => order.planID === orderIdToDelete);

    if (indexToDelete === -1) {
        document.getElementById('DeleteIDError').classList.remove('hidden');
        return;
    }

    // Remove the order from the orders array
    Orders.splice(indexToDelete, 1);

    console.log('Deleting order with ID:', orderIdToDelete);

    // Close the popup after deleting the order
    closePopup('deletePopupOrder');

    // Render the updated table of orders
    renderTableClassOrders();
}

function renderTableClassOrders() {
    document.getElementById('RecentOrdertable').innerHTML = '';
    Orders.forEach(order => {
        const tr = document.createElement('tr');
    
        const trcontent = `
        <td>${order.planID}</td>
        <td>${order.planType}</td>
        <td>${order.duration}</td>
        <td>${order.price}</td>
        
        
        `;
    
        tr.innerHTML = trcontent;
        
       
document.getElementById('RecentOrdertable').appendChild(tr);
    });
}
class User {
    constructor(id, username, password, role, email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.email = email; // New property for email
    }
}
class Client extends User {
    constructor(id, username, password, email) {
        super(id, username, password, 'Client', email);
    }
}

class Admin extends User {
    constructor(id, username, password, email) {
        super(id, username, password, 'Admin', email);
    }
}

function isPasswordSafe(password) {
    // Password must be at least 8 characters long and contain at least one uppercase letter,
    // one lowercase letter, one digit, and one special character
    const pattern =  /^(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{6,16}$/;
console.log("ljl");
    return pattern.test(password);
}

function isEmailValid(email) {
    const pattern = /^[a-z0-9]+(?!.*(?:\+{2,}|\-{2,}|\.{2,}))(?:[\.+\-]{0,1}[a-z0-9])*@gmail\.com$/;
    return pattern.test(email);
    
}  
function isValidDurationonly(duration) {
    // Define an array of valid durations
    const validDurations = ['1-month', '3-months', '6-months'];
    
    // Check if the provided duration exists in the valid durations array
    return validDurations.includes(duration);
}

function isValidOrder(planType,duration) {
    const prices = {
        'basic': {
            '1-month': 50,
            '3-months': 120,
            '6-months': 200
        },
        'premium': {
            '1-month': 80,
            '3-months': 200,
            '6-months': 350
        }
    };
    
    // Check if the planType and duration exist in the prices object
    if (!(planType in prices)) {
        return false; // Invalid planType
    }
    if (!(duration in prices[planType])) {
        return false; // Invalid duration for the given planType
    }
    
    // Order is valid if planType and duration are found in prices
    return true;
}
function isValidPlan(plan) {
    const prices = {
        'basic': {
            '1-month': 50,
            '3-months': 120,
            '6-months': 200
        },
        'premium': {
            '1-month': 80,
            '3-months': 200,
            '6-months': 350
        }
    };
    // Check if the plan exists in the prices object
    return prices.hasOwnProperty(plan);
}

function isValidDuration(plan, duration) {
    const prices = {
        'basic': {
            '1-month': 50,
            '3-months': 120,
            '6-months': 200
        },
        'premium': {
            '1-month': 80,
            '3-months': 200,
            '6-months': 350
        }
    };
    // Check if the duration exists for the given plan in the prices object
    return prices[plan] && prices[plan].hasOwnProperty(duration);
}



function login() {
    event.preventDefault(); 
    let username = document.getElementById('loginUsername').value;
    let password = document.getElementById('loginPassword').value;

    



    const user = users.find(user => user.username === username && user.password === password);
    console.log(user);
    if (user && user.role == "Admin") {
        document.getElementById('loginError').classList.add('hidden');
        document.getElementById('loginUsername').classList.remove('error-input');
    document.getElementById('loginPassword').classList.remove('error-input');
        showContent()
        renderTableClassOrders();
        addRecentViewer(user);
    } else if(user && user.role == "Client"){
        openHTMLFile();

    } else {
        document.getElementById('loginError').classList.remove('hidden');
        document.getElementById('loginUsername').classList.add('error-input');
    document.getElementById('loginPassword').classList.add('error-input');
    }
}
function openHTMLFile() {
    window.open('homepage.html', '_self');

}

function signup() {
    
    event.preventDefault();
    let username = document.getElementById('signupUsername').value;
    let email = document.getElementById('signupEmail').value;
    let password = document.getElementById('signupPassword').value;

    document.getElementById('signupUsername').classList.remove('error-input');
    document.getElementById('signupEmail').classList.remove('error-input');
    document.getElementById('signupPassword').classList.remove('error-input');


    document.getElementById('signupErrorEmail').classList.add('hidden');
    document.getElementById('signupErrorPassword').classList.add('hidden');
    if (username === "" || email === "" || password === "") {
        document.getElementById('signupErrorPassword').textContent = 'Please fill in all fields';
        document.getElementById('signupErrorPassword').classList.remove('hidden');

        document.getElementById('signupUsername').classList.add('error-input');
        document.getElementById('signupEmail').classList.add('error-input');
        document.getElementById('signupPassword').classList.add('error-input');
        return; // Prevent form submission
    }
   
    const existingUser = users.find(user => user.email === email);
    if (existingUser) {
        document.getElementById('signupErrorEmail').textContent = ('Email already exists!');
        document.getElementById('signupErrorEmail').classList.remove('hidden');
        return;
    }
   if(!isEmailValid(email)){
    document.getElementById('signupErrorEmail').textContent = ('Please enter correct Email format');
    document.getElementById('signupErrorEmail').classList.remove('hidden');
    document.getElementById('signupEmail').classList.add('error-input');    
    
    return;

   }
    if(!isPasswordSafe(password)){

        document.getElementById('signupErrorPassword').textContent = ('Password is not safe. It must be at least 8 characters long and contain at least one uppercase letter, one lowercase letter, one digit, and one special character.');
        document.getElementById('signupErrorPassword').classList.remove('hidden');
        document.getElementById('signupPassword').classList.add('error-input');

        return;
    }

    let newUser = new Client(nextUserId++, username, password,email);
    console.log(newUser);
    users.push(newUser);
    
   
    renderTableClassUsers();
    renderTableClassOrders();


    document.getElementById('signupEmail').value = '';
    document.getElementById('signupUsername').value = '';
    document.getElementById('signupPassword').value = '';
    openHTMLFile();
}
function showContent() {
    event.preventDefault();
    document.querySelector('.Log-SignContainer').classList.add('hidden');
    document.querySelector('.container').classList.remove('hidden');
}
function showLoginContent() {
    event.preventDefault();
    document.querySelector('.Log-SignContainer').classList.remove('hidden');
    document.querySelector('.container').classList.add('hidden');
}
function addData() {
    let newUsername = document.getElementById('newUsername').value;
    let newPassword = document.getElementById('newPassword').value;
    let newRole = document.getElementById('newRole').value;
    let newemail = document.getElementById('newEmail').value;
    
    document.getElementById('AddPasswordError').classList.add('hidden');
    document.getElementById('AddRoleError').classList.add('hidden');
    document.getElementById('AddErrorEmail').classList.add('hidden');



    const existingEmail = users.find(user => user.email === newemail);
    if (existingEmail) {
        document.getElementById('AddErrorEmail').classList.remove('hidden');
        return;
    }
    if (newUsername === "" || newPassword === "" || newRole === "" || newemail === "") {
        document.getElementById('AddRoleError').textContent = 'fill User Data';
        document.getElementById('AddRoleError').classList.remove('hidden');
        return;
    }
    if(!isPasswordSafe(newPassword)){

        document.getElementById('AddPasswordError').classList.remove('hidden');
return;
    }
    if(!isEmailValid(newemail)){
        document.getElementById('AddErrorEmail').textContent = 'Please enter correct Email';
        document.getElementById('AddErrorEmail').classList.remove('hidden');
        return;
    }
    
    if (newRole !== "Admin" && newRole !== "Client") {
        document.getElementById('AddRoleError').textContent = 'please enter Admin or Client';
        document.getElementById('AddRoleError').classList.remove('hidden');
        return;
    }
    
    let newUser;
    if (newRole == "Admin") {
        newUser = new Admin(nextUserId++, newUsername, newPassword,newemail);
    } else {
        newUser = new Client(nextUserId++, newUsername, newPassword,newemail);
    }

    users.push(newUser);

    console.log('Adding new data:', newUser);
    console.log(users);
console.log(newRole);
    // Close the popup after adding data
    closePopup('addPopup');

    renderTableClassUsers();
}

function editData() {
    let editUserId = parseInt(document.getElementById('UserIdedit').value);
    let newPassword = document.getElementById('newPasswordedit').value;
    let newRole = document.getElementById('newRoleedit').value;
    let newEmail = document.getElementById('newEmailedit').value;

    document.getElementById('EditErrorId').classList.add('hidden');
    document.getElementById('EditErrorPassword').classList.add('hidden');

    document.getElementById('EditErrorRole').classList.add('hidden');

    const userToEdit = users.find(user => user.id === editUserId);

    if (editUserId === "" || newPassword === "" || newRole === "" || newEmail === "") {
        document.getElementById('EditErrorRole').textContent = 'fill User Data';
        document.getElementById('EditErrorRole').classList.remove('hidden');
        return;
    }
    if (!userToEdit) {
        document.getElementById('EditErrorId').classList.remove('hidden');
        return;
    }
    if(!isPasswordSafe(newPassword)){
        document.getElementById('EditErrorPassword').classList.remove('hidden');
        return;
    }
    if(!isEmailValid(newEmail)){
        document.getElementById('newEmailedit').classList.remove('hidden');
        return;
    }
    if (newRole !== "Admin" && newRole !== "Client") {
        document.getElementById('EditErrorRole').textContent = 'Please enter Admin or Client'
        document.getElementById('EditErrorRole').classList.remove('hidden');
        return;
    }
    

    // If the user is an Admin, create an Admin object; otherwise, create a User object
    let editedUser;
    if (userToEdit instanceof Admin) {
        editedUser = new Admin(userToEdit.id, userToEdit.username, newPassword,newEmail);
    } else {
        editedUser = new Client(userToEdit.id, userToEdit.username, newPassword,newEmail);
    }

    
    const index = users.findIndex(user => user.id === editUserId);
    users[index] = editedUser;

    console.log('Editing data:', editedUser);
    console.log(users);

    
    closePopup('editPopup');

  
    renderTableClassUsers();
}

function deleteData() {
    let deleteUserId = parseInt(document.getElementById('ID').value);

    document.getElementById('DeleteErrorId').classList.add('hidden');

    const index = users.findIndex(user => user.id === deleteUserId);

    if (index === -1) {
        console.log("kjsdf")
        document.getElementById('DeleteErrorId').classList.remove('hidden');
        return;
    }
    users.splice(index, 1);
    console.log('Deleting data:', deleteUserId);
    console.log(users);

    closePopup('deletePopup');

 
    renderTableClassUsers();
}



let users =[];

users.push(new Admin(1,'ismail','ismail123','ismail@gmail.com'));
users.push(new Admin(2,'youssef','youssef123','youssef@gmail.com'));
users.push(new Admin(3,'mohanad','mohanad123','mohand@gmail.com'));
users.push(new Admin(4,'zeina','zeina123','zeina@gmail.com'));
users.push(new Client(5,'Ahmed','Ahmed123','ahmed@gmail.com'));



function addPlan() {
    let newProductName = parseInt(document.getElementById('newProductName').value);
    let newDuration = document.getElementById('newProductDuration').value;
let userIdAddTo = document.getElementById('newUserID').value;
    document.getElementById('AddProducterrorError').classList.add('hidden');
    document.getElementById('AddProductDurationError').classList.add('hidden');
    
    // Validate input fields
    if (newProductName === "" || newDuration === "") {
        document.getElementById('AddProductDurationError').textContent = 'Please fill all values';
        document.getElementById('AddProductDurationError').classList.remove('hidden');
        return;
    }

    // Check if the plan is valid
    document.getElementById('AddProductDurationError').textContent = 'please enter valid duration.   1-month,2-months,3-months or 6-months';

    // Check if the duration is valid
    if (!isValidDurationonly(newDuration)) {
        document.getElementById('AddProductDurationError').classList.remove('hidden');
        return;
    }
    const userToAddto = users.find(user => user.id === userIdAddTo);
    console.log(userIdAddTo)
    if(!userToAddto ){
console.log('dffd')
console.log(users)
        document.getElementById('AddUSerIdError').classList.remove('hidden');
        return;
    }
    document.getElementById('AddUSerIdError').classList.add('hidden');
    // Create a new Plan object
    let newPlan = new Plan(nextPlanId++, newProductName, newDuration,1);

    // Push the new plan to the Plans array (assuming it's defined globally)
    Products.push(newPlan);

    console.log('Adding new plan:', newPlan);
    

    // Close the popup after adding the plan
    closePopup('addPopupProduct');

    // Render the updated table of plans
    renderTableClassProducts(); // You need to implement this function to render the plan table
}


function renderTableClassUsers() {
    
    document.getElementById('USERTABLE').innerHTML = '';

   
    users.forEach(user => {
        const tr = document.createElement('tr');
        const trcontent = `
            <td>${user.id}</td>
            <td>${user.username}</td>
            <td>${user.password}</td>
            <td>${user.email}</td>
            <td>${user.role}</td>
        `;
        tr.innerHTML = trcontent;
        document.getElementById('USERTABLE').appendChild(tr);
    });
}

function addRecentViewer(user) {
    // Create a new div element for the recent viewer
    const newViewer = document.createElement('div');
    newViewer.classList.add('visitor');

    // Construct the HTML content for the recent viewer
    newViewer.innerHTML = `
        <div>
            <i class="fa-regular fa-user fa-lg"></i>
        </div>
        <div class="visited">
            <p>${user.username} has visited the website</p>
            <small class="text-muted">Just now</small>
        </div>
    `;

    // Insert the new recent viewer at the beginning of the recent viewers list
    const recentViewers = document.querySelector('.visitors');
    recentViewers.insertBefore(newViewer, recentViewers.firstChild);
}

const container = document.getElementById('Log-SignContainer');
const registerBtn = document.getElementById('register');
const loginBtn = document.getElementById('login');

registerBtn.addEventListener('click', () => {
    container.classList.add("active");
});

loginBtn.addEventListener('click', () => {
    container.classList.remove("active");
});

const sideMenu = document.querySelector("aside");
const menuBtn  = document.querySelector("#menu-btn");
const closebtn = document.querySelector(".close");
menuBtn.addEventListener('click',() => {
    sideMenu.style.display ='block';
})
closebtn.addEventListener('click', ()=>{
    sideMenu.style.display ='none';
})


/*
function addData() {
    let newUsername = document.getElementById('newUsername').value;
    let newPassword = document.getElementById('newPassword').value;
    let newRole = document.getElementById('newRole').value;

    const existingUser = data.find(user => user.username === newUsername);
    if (existingUser) {
        
        alert('Username already exists!');
        return;
    }
    if(newUsername ==="" || newPassword ===""){

        alert('fill username or password');
    }
    if(newRole!="Admin" && newRole!="Client"){

        alert('please enter correct Role');
        return;
    }
   
    let newData = {
        id: nextUserId++,
        username: newUsername,
        password: newPassword,
        Role: newRole
    };

    data.push(newData);
    
    console.log('Adding new data:', newUsername, newPassword, newRole);
    console.log(data);
    // Close the popup after adding data
    closePopup('addPopup');

    renderTable();
}




function editData() {
    let editUsername = document.getElementById('newUsernameedit').value;
    let newPassword = document.getElementById('newPasswordedit').value;
    let newRole = document.getElementById('newRoleedit').value;

  
    const index = data.findIndex(user => user.username === editUsername);
    if (index === -1) {
        
        alert('User not found!');
        return;
    }if(newRole!="Admin" && newRole!="Client"){

        alert('please enter correct Role');
        return;
    }

    
    data[index].password = newPassword;
    data[index].Role = newRole;

    console.log('Editing data:', editUsername, newPassword, newRole);
    console.log(data);

    
    closePopup('editPopup');

  
    renderTable();
}

function deleteData() {
    let deleteUserId = parseInt(document.getElementById('ID').value);

    // Find the index of the user to be deleted
    const index = data.findIndex(user => user.id === deleteUserId);
    if (index === -1) {
        // Handle case where user is not found
        alert('User not found!');
        return;
    }

    data.splice(index, 1);

    console.log('Deleting data:', deleteUserId);
    console.log(data);

    
    closePopup('deletePopup');

    
    renderTable();
}







function renderTable() {
    
    document.getElementById('USERTABLE').innerHTML = '';

   
    data.forEach(user => {
        const tr = document.createElement('tr');
        const trcontent = `
            <td>${user.id}</td>
            <td>${user.username}</td>
            <td>${user.password}</td>
            <td>${user.Role}</td>
        `;
        tr.innerHTML = trcontent;
        document.getElementById('USERTABLE').appendChild(tr);
    });
}
*/

/*  let data =[{
    id:1,
    username :'Ismail',
    password:'ismail123',
    Role:'Admin',
    
},{
    id:2,
    username :'Ismail',
    password:'ismail123',
    Role:'Admin',

},{

    id:3,
    username :'Ismail',
    password:'ismail123',
    Role:'Admin',
},{

    id:4,
    username :'Ismail',
    password:'ismail123',
    Role:'Admin',
}
,{

    id:5,
    username :'Ismail',
    password:'ismail123',
    Role:'Admin',
}]
*/