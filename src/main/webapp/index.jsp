<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="myPackage.Medicine" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
<head>
    <title>Medicine Stock Management System</title>
  <link type="text/css" rel="stylesheet" href="style.css"/>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/docx/6.1.0/docx.min.js"></script>
  <script  src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.22/pdfmake.min.js"></script>
    <script  src="https://cdnjs.cloudflare.com/ajax/libs/html2canvas/0.4.1/html2canvas.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src=
"https://code.jquery.com/jquery-3.6.4.min.js">
    </script>
    <script src=
"//cdn.rawgit.com/rainabba/jquery-table2excel/1.1.0/dist/jquery.table2excel.min.js">
    </script>
    <style>
    body{
     background-color:#374399;
    overflow-x: hidden;
            font-family: Arial, sans-serif;
     }
      .card {
            border: 1px solid #ccc;
            border-radius: 10px;
            box-shadow: 0 0 8px rgba(0,0,0,0.1);
            background-color: #fefefe;
            padding: 20px;
            margin-bottom: 20px;
        }
     .container {
            max-width: 1200px;
            margin: 0 ;
            padding: 15px;
        }
        .container h1{
        text-align:center;
        background-color:#C9CBD8  ;
        width: 110vw;
        margin-left: calc(-50vw + 50%);
        height:50px;
        display:flex;
        justify-content:center;
        align-items:center;
        }
        /* The Modal (background) */
        .modal {
            display: none; /* Hidden by default */
            position: fixed; /* Stay in place */
            z-index: 1; /* Sit on top */
            left: 0;
            top: 0;
            width: 100%; /* Full width */
            height: 100%; /* Full height */
            overflow: auto; /* Enable scroll if needed */
            background-color: rgb(0,0,0); /* Fallback color */
            background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
        }

        /* Modal Content/Box */
        .modal-content {
            background-color: #fefefe;
            margin: 7% auto; /* 15% from the top and centered */
            padding: 20px;
            border: 1px solid #888;
            width: 30%; /* Could be more or less, depending on screen size */
            height: 60%;
        }
        .modal-content form input{
        margin: 5px;
        }
        
       

        /* The Close Button */
        .close {
            color: #aaa;
            float: right;
            font-size: 28px;
            font-weight: bold;
        }

        .close:hover,
        .close:focus {
            color: black;
            text-decoration: none;
            cursor: pointer;
        }
        .update-section{
         width: 55%;
           margin-left: 250px;
           position:absolute;
           top:100px;
           left:700px;
           height: 90%;
           margin-top: 30px;
        
        }
        .update-section form{
        
       
        width: 45%;
        height: 100%;
        border: 1px solid #ccc;
            border-radius: 10px;
            box-shadow: 0 0 8px rgba(0,0,0,0.1);
            background-color:#f2f2f2;
            padding: 12px;
            margin-left: 10px;
            
            
        }
        .update-section form input{
        margin: 10px;
        
        
        }
        .search-section {
    display: inline-block;
    margin: 10px;
    width: 20%;
    margin-left: 70px;
}

.search-section form {
    margin: 10px;
}

.search-section input[type="text"], .search-section input[type="date"] {
    width: 70%;
    padding: 10px;
    margin: 10px;
}
.search-section input[type="submit"] {
    width: 60%;
    padding: 10px;
    margin: 10px;
}
table{
width: 70%;
margin: 1px;
margin-top: 15px;
border-collapse: collapse;
         background-color: #ffffff;
}
 
    .view-medicines-section form input{
     height: 10%;
     margin: 10px;
    }
     
    
     th, td {
            padding: 15px;
            text-align: left;
            border: 2px solid #ddd;
        }
        th {
         background-color: #4CAF50;
         color: white;
     }
     td {
         background-color: #f2f2f2;
     }
        #addMedicineBtn{
        height: 5vh;
        
        margin: 10px;
        cursor:pointer;
        
        }
        #addMedicineBtn:hover{
        background-color:orange;
         color:white;
        }
        #view-btn{
        padding:5px;
        cursor:pointer;
        }
         #view-btn:hover{
         background-color:orange;
         color:white;
         }
        #update-btn{
        padding:5px;
        background-color:black;
        color:white;
        cursor:pointer;
        height:30px;
        }
        #update-btn:hover{
        background-color:blue;
         color:white;
        }
        #search-btn{
        padding:5px;
        
         cursor:pointer;
        }
        #search-btn:hover{
        background-color:orange;
         color:white;
        }
        #add-btn{
         padding:5px;
        
         cursor:pointer;
        }
        #add-btn:hover{
        background-color:orange;
         color:white;
        }
        #viewMedicineBtn{
        height:30px;
        cursor:pointer;
        }
        #viewMedicineBtn:hover{
          background-color:orange;
         color:white;
        }
        #dateRange-btn:hover{
          background-color:orange;
         color:white;
         cursor:pointer;
        }
        #downloadExcelBtn{
        margin:8px;
        padding:5px;
        }
        #downloadExcelBtn:hover{
        background-color:orange;
         color:white;
         cursor:pointer;
        }
       #downloadPDFbtn{
        margin:8px;
        padding:5px;
       }
       #downloadPDFbtn:hover{
        background-color:orange;
         color:white;
         cursor:pointer;
       }
       #downloadDocbtn{
         margin:8px;
        padding:5px;
       }
       #downloadDocbtn:hover{
        background-color:orange;
         color:white;
         cursor:pointer;
       }
       #emailbtn:hover{
        background-color:orange;
         color:white;
         cursor:pointer;
       }
       #del-btn:hover{
         background-color:orange;
         color:white;
         cursor:pointer;
       }
    </style>
   
</head>
<body>

<div class=container>
    <h1 >Medicine Stock Management System</h1>
   
  
     <button id="addMedicineBtn" onclick="addclick()" >+ Add New Medicine</button>
   
     
       <div class=" card search-section">
        <form action="search" method="get" id="searchform" >
            <input type="hidden" name="action" value="search" >
            
            <input type="text" id="query" name="query" placeholder="Medicine Type/Name" required><br>
            <input type="submit" value="Search Medicine" id="search-btn">
        </form>
        
   
       
    </div>
    <!-- New Date Range Form -->
    <div class="card search-section">
        <form action="searchByDate" method="get" id="dateRangeForm" >
            <label for="fromDate">From Date:</label>
            <input type="date" id="fromDate" name="fromDate" required><br>
            <label for="toDate">To Date:</label>
            <input type="date" id="toDate" name="toDate" required><br>
            <input type="submit" value="Search by Date" id="dateRange-btn">
            
        </form>
        
        
    </div>

    <!-- Display Search Results -->
    <div id="searchResult">
        <% 
            List<Medicine> medicines = (List<Medicine>) request.getAttribute("medicinesByDate");
            if (medicines != null && !medicines.isEmpty()) {
        %>
            
            <table id="medDataTable" border="1">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Type</th>
                        <th>Name</th>
                        <th>Company</th>
                        <th>Quantity</th>
                        <th>Purchase Date</th>
                        <th>Purchase Rate</th>
                        <th>Expiry Date</th>
                        <th>Supplier Name</th>
                       
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="medicine" items="${medicinesByDate}">
                        <tr>
                            <td>${medicine.id}</td>
                            <td>${medicine.type}</td>
                            <td>${medicine.name}</td>
                            <td>${medicine.company}</td>
                            <td>${medicine.quantity}</td>
                            <td>${medicine.purchaseDate}</td>
                            <td>${medicine.purchaseRate}</td>
                            <td>${medicine.expiryDate}</td>
                            <td>${medicine.supplierName}</td>
                            
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <button id="downloadExcelBtn" >Download Excel</button>
            <input type="button" id="downloadPDFbtn" value="Download PDF" />
            <button id="downloadDocbtn" onclick="ExportToDoc('medDataTable','medicine');">Download Doc</button>
               <h2 style="color:white;">Send Email with Attachment</h2>
    <form action="sendEmail" method="post">
    <label for="to" style="color:white;">To:</label>
    <input type="email" id="to" name="to" required><br><br>
    <input type="submit" value="Send Email" id="emailbtn">
</form>
            
                     
          <% } else if (request.getParameter("fromDate") != null && request.getParameter("toDate") != null) { %>
        <p style="color: ;">*No records found for the specified date range.</p>
    <% } %>
    
    </div>
    
 <!-- Button to Open the Modal -->
    
    <!-- The Modal -->
    <div id="addMedicineModal" class="modal">
        <!-- Modal content -->
        <div class="modal-content">
            <span class="close">&times;</span>
           <h2>Add medicine</h2>
            <form action="insert" method="post" onsubmit="return validateAddForm()" >
               
               <label for="med_type">Medicine Type:</label>
   <select id="med_type" name="med_type" required>
                    
                    <option value="syrup">Syrup</option>
                    <option value="tablet">Tablet</option>
                    <option value="spray">Spray</option>
                </select><br>
                <label for="med_name">Medicine Name:</label>
                <input type="text" id="med_name" name="med_name" required><br>
                <label for="company">Company:</label>
                <input type="text" id="company" name="company" required><br>
                <label for="quantity">Quantity:</label>
                <input type="number" id="quantity" name="quantity" required><br>
                <label for="purchaseDate">Purchase Date:</label>
                <input type="date" id="purchaseDate" name="purchaseDate" required><br>
                <label for="purchaseRate">Purchase Rate:</label>
                <input type="number" step="0.01" id="purchaseRate" name="purchaseRate" required><br>
                <label for="expiryDate">Expiry Date:</label>
                <input type="date" id="expiryDate" name="expiryDate" required><br>
                <label for="supplierName">Supplier Name:</label>
                <input type="text" id="supplierName" name="supplierName" required><br>
                <input type="submit" value="Add Medicine" id="add-btn" >
                
            </form>
            
      
        </div>
            <c:if test="${not empty errorMessage}">
        <div class="error-message" style="color: red;">
            ${errorMessage}
        </div>
    </c:if>
    </div>
  
    <div class="  view-medicine-section">
    <h2 style="color: white;">All Medicines</h2>
    <form action="UserSServlet" method="get">
        
        <input type="submit" value="View All Medicines" id="viewMedicineBtn" >
        
    </form>

    <!-- Display All Medicines -->
    <div id="medicines"  >
    <%
                // Fetch the list of medicines from the request attribute
                List<Medicine> listMedicine = (List<Medicine>) request.getAttribute("listMedicine");
                if (listMedicine != null && !listMedicine.isEmpty()) {
            %>
     <table border="1">
            <thead  >
                <tr>
                    <th>ID</th>
                    <th>Type</th>
                    <th>Name</th>
                    <th>Company</th>
                    <th>Quantity</th>
                    <th>Purchase Date</th>
                    <th>Purchase Rate</th>
                    <th>Expiry Date</th>
                    <th>Supplier Name</th>
                    <th>Actions</th>
                     
                </tr>
            </thead>
            <tbody>
                <c:forEach var="medicine" items="${listMedicine}">
                    <tr>
                        <td>${medicine.id}</td>
                        <td>${medicine.type}</td>
                        <td>${medicine.name}</td>
                        <td>${medicine.company}</td>
                        <td>${medicine.quantity}</td>
                        <td>${medicine.purchaseDate}</td>
                        <td>${medicine.purchaseRate}</td>
                        <td>${medicine.expiryDate}</td>
                        <td>${medicine.supplierName}</td>
                          <td>
            <form action="delete" method="post">
                                    <input type="hidden" name="med_id" value="${medicine.id}">
                                    <input type="submit" value="Delete" id="del-btn" >
                                </form>
        </td>                          
                  
                    </tr>
                </c:forEach>
            </tbody>
        </table>
          
            <% } %>
    </div>
   </div>
   <div class="  update-section">

<form action="update" method="post" onsubmit= "return validateUpdateForm()" id="form1" >
<h2>Update Medicine</h2>
    <input type="hidden" name="action" value="update">
    <label for="id">Medicine ID:</label>
    <input type="number" id="med_id" name="med_id" required><br><div id="med_idError" class="error"></div>
    <label for="med_type">Medicine Type:</label>
   <select id="med_type" name="med_type" required>
                    
                    <option value="syrup">Syrup</option>
                    <option value="tablet">Tablet</option>
                    <option value="spray">Spray</option>
                </select><br>
    
    <label for="med_name">Medicine Name:</label>
    <input type="text" id="med_name" name="med_name" required><br>
  
    <label for="company">Company:</label>
    <input type="text" id="company" name="company" required><br><div id="companyError"></div>
   
    <label for="quantity">Quantity:</label>
    <input type="number" id="quantity" name="quantity" required><br><div id="quantityError" class="error"></div>
   
    <label for="purchaseDate">Purchase Date:</label>
    <input type="date" id="purchaseDate" name="purchaseDate" required><br> <div id="purchaseDateError"></div>
    <label for="purchaseRate">Purchase Rate:</label>
    <input type="number" step="0.01" id="purchaseRate" name="purchaseRate" required><br><div id="purchaseRateError" class="error"></div>
    
    <label for="expiryDate">Expiry Date:</label>
    <input type="date" id="expiryDate" name="expiryDate" required><br><div id="expiryDateError"></div>
    <label for="supplierName">Supplier Name:</label>
    <input type="text" id="supplierName" name="supplierName" required><br><div id="supplierNameError"></div>
    
    <input type="submit" value="Update Medicine" id="update-btn">
    
</form>
<!-- Display Validation Messages -->
    <c:if test="${not empty message}">
        <p style="color:black;">${message}</p>
    </c:if>
   </div>
    <!-- Search Medicine by ID Form -->
   

    <!-- Display Search Result -->
</div>
   

    


<script>
var addMedicineModal = document.getElementById('addMedicineModal');

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];

// When the user clicks the button, open the modal 
 function addclick(){
    addMedicineModal.style.display = "block";
}

// When the user clicks on <span> (x), close the modal
span.onclick = function() {
    addMedicineModal.style.display = "none";
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == addMedicineModal) {
        addMedicineModal.style.display = "none";
    }
}

function validateAddForm() {
   
    var name = document.getElementById("med_name").value;
    var company = document.getElementById("company").value;
    var quantity = document.getElementById("quantity").value;
    var purchaseDate = document.getElementById("purchaseDate").value;
    var purchaseRate = document.getElementById("purchaseRate").value;
    var expiryDate = document.getElementById("expiryDate").value;
    var supplierName = document.getElementById("supplierName").value;
    var numberPattern = /\d/;  // Regular expression to check for digits

    if (company === "") {
        alert("Company name is required.");
        return false;
    } else if (numberPattern.test(company)) {
        alert("Company name should not contain numbers.");
        return false;
    }

    if (supplierName === "") {
        alert("Supplier name is required.");
        return false;
    } else if (numberPattern.test(supplierName)) {
        alert("Supplier name should not contain numbers.");
        return false;
    }
    
    if (isNaN(quantity) || quantity <= 0) {
        alert("Quantity should be a positive number.");
        return false;
    }
    if (purchaseDate === "") {
        alert("Please enter a valid purchase date.");
        return false;
    }
    if (isNaN(purchaseRate) || purchaseRate <= 0) {
        alert("Purchase rate should be a positive number.");
        return false;
    }
    if (expiryDate === "") {
        alert("Please enter a valid expiry date.");
        return false;
    } 
    var purchaseDateObj = new Date(purchaseDate);
    var expiryDateObj = new Date(expiryDate);

    if (expiryDateObj <= purchaseDateObj) {
        alert("Expiry date should be greater than the purchase date.");
        return false;
    }
    return true;
}
    function validateUpdateForm(){
    	
    	let isValid=true;
    	const fields=[
    		{ id:'med_id',name:'med_id'},
    		{id:'quantity',name:'quantity'},
    		{id:'purchaseRate',name:'purchaseRate'}
    	];
    	fields.forEach(field=>{
    		const elementPath= "#form1 "+"#"+field.id
    		const element= document.querySelector(elementPath);
    		const errorPath="#form1 "+"#"+field.id+ 'Error'
    		const errorElement = document.querySelector(errorPath);
    		if(field.id==='med_id'|| field.id==='quantity'|| field.id==="purchaseRate"){
    			if(element.value<=0){
    				errorElement.textContent="* "+field.name + " should be a positive numer";
    				isValid=false;
    			}else{
    				errorElement.textContent = '';
    			}
    		}
    	});
    	const companyElement= document.querySelector("#form1 #company");
    	const supplierNameElement= document.querySelector("#form1 #supplierName");
    	 const purchaseDateElement = document.querySelector("#form1 #purchaseDate");
    	    const expiryDateElement = document.querySelector("#form1 #expiryDate");
    	    const purchaseDateErrorElement = document.querySelector("#form1 #purchaseDateError");
    	    const expiryDateErrorElement = document.querySelector("#form1 #expiryDateError");
    	    const supplierNameErrorElement = document.querySelector("#form1 #supplierNameError");
    	    const companyErrorElement= document.querySelector("#form1 #companyError");

    	    const purchaseDateValue = purchaseDateElement.value.trim();
    	    const expiryDateValue = expiryDateElement.value.trim();
            const supplierNameValue = supplierNameElement.value.trim();
            const companyValue = companyElement.value.trim();
            if(!isNaN(companyValue)){
            	companyErrorElement.textContent= "* company name should not contain number";
            	isValid=false;
            }else{
            	companyErrorElement.textContent='';
            }
            if(!isNaN(supplierNameValue)){
            	supplierNameErrorElement.textContent="* supplier name should not be a number";
            	isValid=false;
            }else{
            	supplierNameErrorElement.textContent='';
            }
            
    	    if (!purchaseDateValue) {
    	        purchaseDateErrorElement.textContent = "* Purchase date is required";
    	        isValid = false;
    	    } else {
    	        purchaseDateErrorElement.textContent = '';
    	    }

    	    if (!expiryDateValue) {
    	        expiryDateErrorElement.textContent = "* Expiry date is required";
    	        isValid = false;
    	    } else {
    	        expiryDateErrorElement.textContent = '';
    	    }

    	    if (purchaseDateValue && expiryDateValue) {
    	        const purchaseDate = new Date(purchaseDateValue);
    	        const expiryDate = new Date(expiryDateValue);

    	        if (expiryDate <= purchaseDate) {
    	            expiryDateErrorElement.textContent = "* Expiry date should be later than purchase date";
    	            isValid = false;
    	        } else {
    	            expiryDateErrorElement.textContent = '';
    	        }
    	    }
    	return isValid;
    }
    function validateDateRangeForm() {
        var fromDate = new Date(document.getElementById("fromDate").value);
        var toDate = new Date(document.getElementById("toDate").value);

        if (toDate < fromDate) {
            alert("To date must be greater than or equal to from date.");
            return false;
        }

        return true;
    }
    $(document).ready(function () {
        $('#downloadExcelBtn').on('click', function () {
            $("#medDataTable").table2excel({
                filename: "medicineData.xls"
            });
        });
    });
    $("body").on("click", "#downloadPDFbtn", function () {
        html2canvas($('#medDataTable')[0], {
            onrendered: function (canvas) {
                var data = canvas.toDataURL();
                var docDefinition = {
                    content: [{
                        image: data,
                        width: 500
                    }]
                };
                pdfMake.createPdf(docDefinition).download("medicine-details.pdf");
            }
        });
    });
    function ExportToDoc(element, filename = '') {
        var table = document.getElementById(element);
        var html = "<html><head><meta charset='UTF-8'><style>table {border-collapse: collapse;} th, td {border: 1px solid black; padding: 8px;}</style></head><body>";
        html += "<table>";

        // Add table headers
        var headers = [];
        for (var i = 0; i < table.rows[0].cells.length; i++) {
            headers[i] = table.rows[0].cells[i].textContent.toLowerCase().replace(/ /gi, '');
        }
        html += "<tr>";
        for (var i = 0; i < headers.length; i++) {
            html += "<th>" + headers[i] + "</th>";
        }
        html += "</tr>";

        // Add table body
        for (var i = 1; i < table.rows.length; i++) {
            html += "<tr>";
            for (var j = 0; j < table.rows[i].cells.length; j++) {
                html += "<td>" + table.rows[i].cells[j].textContent + "</td>";
            }
            html += "</tr>";
        }

        html += "</table></body></html>";

        var blob = new Blob(['\ufeff', html], {
            type: 'application/msword'
        });

        var url = URL.createObjectURL(blob);
        var a = document.createElement('a');
        a.href = url;
        a.download = filename + '.doc';
        document.body.appendChild(a);
        a.click();
        document.body.removeChild(a);
        window.URL.revokeObjectURL(url);
    }   
    
  
</script>
</body>
</html>
