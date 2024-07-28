/**
 * 
 */
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



 
function validateSearchForm() {
    var query = document.getElementById("query").value;
    if (query.trim() === "") {
        alert("Please enter a valid query.");
        return false;
    }else if(!/^[a-zA-Z\s]+$/.test(query)){
    	alert("Medicine type/name cannot be a number");
    	return false;
    }
    return true;
}

function validateAddForm() {
   
    var name = document.getElementById("med_name").value;
    var company = document.getElementById("company").value;
    var quantity = document.getElementById("quantity").value;
    var purchaseDate = document.getElementById("purchaseDate").value;
    var purchaseRate = document.getElementById("purchaseRate").value;
    var expiryDate = document.getElementById("expiryDate").value;
    var supplierName = document.getElementById("supplierName").value;

    
   
    
    
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