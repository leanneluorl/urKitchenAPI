var UserAPI_URL="http://127.0.0.1:49000/api/UrKitchen/user/";

function clearStatus() {
			$('#status').html('');
		}


function deleteUrtableRCP(UrtableID){
					
		$.ajax({
			url: UserAPI_URL+"/Urtable/"+UrtableID,
			cache: false,
			type: 'DELETE',
			contentType: 'application/json; charset=utf-8',
			//data: , 
			statusCode: {
				201 /*Created*/: function (data) {
					var UID =$("#account").text();
					showUrtableRCP(UID);
				}
			}
		});
	
}


function showUrtableRCP(UID){
	var UserID=UID;
	
	/*Get today date*/
	var today = new Date();
	var dd = String(today.getDate()).padStart(2, '0');
	var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
	var yyyy = today.getFullYear();

	today = mm + dd  + yyyy;
	console.log(today);
	
	$("#dish").html("");
	$.getJSON(UserAPI_URL+UserID+"/UrTable/"+today, function(data) {
		
		for(i = 0; i < data.length; i++){ 
			$("#dish").append(
				`<div class="table-recipe">`+
				`<div class="recipe_title dish">`+data[i].RecipeTitle+`</div>`+
				`<div class="recipe_image" style="background-image: url(`+data[i].MainImage+`);" onclick="load_recipeByID(`+data[i].RecipeID+`);"></div>`+
				`<button type="button" width="150px" onclick="deleteUrtableRCP(`+data[i].UrtableID+`)">Delete</button></div>`
			);
		}
	})
	.fail(
				function (xhr, textStatus, err) {
					$('#status').html('Error: ' + err);
				});

}



function addToUrTable(RID){
	var UserID=$("#account").text();
	if(UserID===null){alert("You have to Login!");}
	/*Get today date*/
	var today = new Date();
	var dd = String(today.getDate()).padStart(2, '0');
	var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
	var yyyy = today.getFullYear();

	today = mm + dd  + yyyy;
	console.log(today);
	
	var User_addUrTable = JSON.stringify({ 
			
			UserID: UserID,
			today: today,
			RecipeID: RID,
			});
			
			$.ajax({
				url: UserAPI_URL+"addToUrTable",
				cache: false,
				type: 'POST',
				contentType: 'application/json; charset=utf-8',
				data: User_addUrTable, 
				statusCode: {
					201 /*Created*/: function (data) {
						alert("Recipe added"); 
						
					}
				}
			});
	
}

			
function showEditUser(UserID) {
			clearStatus();
			var Uid=UserID;
						
			//利用Get方式取得。
			$.getJSON(UserAPI_URL+UserID, function (data) {
				$("#User_data_box").html("");
					$("#User_data_box").append(
						`<div class="UserItem-title line">User ID</div>`+
						`<div class="UserItem-Input line">`+data.UserID+`</div>`+
						`<div class="UserItem-title line">Email</div>`+
						`<div class="UserItem-Input line">`+data.Email+`</div>`+
						`<div class="UserItem-title line">First Name</div>`+
						`<div class="UserItem-Input line"><input type="text" id="FirstName" value="`+data.FirstName+`"/></div>`+
						`<div class="UserItem-title line">Last Name</div>`+
						`<div class="UserItem-Input line"><input type="text" id="LastName" value="`+data.LastName+`"/></div>`+
						`<div class="UserItem-title line">Mobile No</div>`+
						`<div class="UserItem-Input line"><input type="text" id="MobileNo" value="`+data.MobileNo+`"/></div>`
					);
					$(".UserItem-button").html("");
					$(".UserItem-button").append(`<input type="button" value="Submit" onclick="editUser('`+Uid+`');" />`);
			})
			.fail(
				function (xhr, textStatus, err) {
					$('#status').html('Error: ' + err);
				});
}

		
function editUser(UserID){
	var Uid=UserID;
	var User_update = JSON.stringify({ 
			FirstName: $("#FirstName").val(),
			LastName: $("#LastName").val(),
			MobileNo: $("#MobileNo").val(),
			});
			
			$.ajax({
				url: UserAPI_URL+UserID,
				cache: false,
				type: 'PUT',
				contentType: 'application/json; charset=utf-8',
				data: User_update, 
				statusCode: {
					201 /*Created*/: function (data) {
						alert("Data has been changed."); 
						showEditUser(Uid);
						loadinfo(Uid);
					}
				}
			});
	
}


function checkUserID(){
	var input = $("#Uid").val();
	$.getJSON(UserAPI_URL+input, function (data) {
			if(data.UserID===undefined){
			$("#ID_input_err").html("");
			$("#Uid").css("background-color", "white");
			}
			else if(data.UserID!=undefined&&data.UserID.toLowerCase()==input.toLowerCase()){
				$("#Uid").css("background-color", "pink");
				$("#ID_input_err").html("User ID: "+data.UserID+" is already existed!");
			}
		})
		.fail(
			function (xhr, textStatus, err) {
				$('#status').html('Error: ' + err);
			});
	
}

function checkUserEmail(){
	var input2 = $("#Email").val();
	$.getJSON(UserAPI_URL+input2, function (data) {
			if(data.Email===undefined){
			$("#Email_input_err").html("");
			$("#Email").css("background-color", "white");
			}
			else if(data.Email!=undefined&&data.Email.toLowerCase()==input2.toLowerCase()){
				$("#Email").css("background-color", "pink");
				$("#Email_input_err").html("Email: "+data.Email+" is already existed!");
			}
		})
		.fail(
			function (xhr, textStatus, err) {
				$('#status').html('Error: ' + err);
			});
	
}


function addUser(){
	
	var User_create = JSON.stringify({ 
			UserID: $("#Uid").val(),
			Email: $("#Email").val(),
			UserPWD: $("#pwd").val(),
			FirstName: $("#FirstName").val(),
		
			});
			
			$.ajax({
				url: UserAPI_URL+"Create",
				cache: false,
				type: 'POST',
				contentType: 'application/json; charset=utf-8',
				data: User_create, 
				statusCode: {
					201 /*Created*/: function (data) {
						alert(data.msg); 
						load_userLoginPage();
					}
				}
			});
	
}



/**Rummage**/
function showSearchRCPbyStockIGD(){		
		$("#Drag-Ingredient").html("");
		var UID=$("#account").text();
		$.getJSON(UserAPI_URL+UID+"/StockIGD", function(data) {
	
					function groupBy(objectArray, property) {
					   return objectArray.reduce((acc, obj) => {
						  const key = obj[property];
						  if (!acc[key]) {
							 acc[key] = [];
						  }
						  // Add object to list for given key's value
						  acc[key].push(obj);
						  return acc;
					   }, {});
					}
					const groupedSIGD = groupBy(data, 'LocationID');
					console.log(groupedSIGD);
			
					for(var LocationID in groupedSIGD) {
						
						var value = groupedSIGD[LocationID];
						console.log(name);
						//console.log(value);
						
						
						
						
						$("#Drag-Ingredient").append(`<div class="drag-L-type" style="background-image: url(`+groupedSIGD[LocationID][0]['LocationID']+`);">`+
													 `<p class="Head2-foodtype">`+groupedSIGD[LocationID][0]['LocationID']+`</p></div>`+
													 `<div id="`+groupedSIGD[LocationID][0]['LocationID']+`" class="drag-R-type"></div>`);
						
						for (i = 0; i < value.length; i++) {
						
						
						var divID="#"+groupedSIGD[LocationID][0]['LocationID'];
							$(divID).append(
								`<div class="IGD-dragbox">`+
								`<div  class="IGD-Icon" draggable="true" ondragstart="drag(event)" id="`+groupedSIGD[LocationID][i]['SDID']+`" style="background-image: url(`+groupedSIGD[LocationID][i]['IGDImage']+`);" ><p class="IGD-Icon_font">`+groupedSIGD[LocationID][i]['IngredientID']+`</p></div></div>`
							);
						}//for length emd
					}//for object
												  
				})//get json end
				.fail(
					function (xhr, textStatus, err) {
						$('#status').html('Error: ' + err);
				});
	}