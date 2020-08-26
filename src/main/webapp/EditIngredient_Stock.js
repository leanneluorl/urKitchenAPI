var UserAPI_URL="http://127.0.0.1:49000/api/UrKitchen/user/";
	var UID=$("#account").text();

	function showEditStock_IGD(UID){
			
			
			$.getJSON(UserAPI_URL+UID+"/StockIGD", function(data) {
					$(".stockIGD_box").html("");
					$(".IGD_content").remove();
					$(".RCPstockIGDform_add").html("");
				
	/*Split object by property: */	
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
					const groupedF = groupBy(data, 'foodtypeID');
					console.log(groupedF);
	/*Get object name: */	
					for(var foodtype in groupedF) {
						
						var value = groupedF[foodtype];
						console.log(foodtype);
						console.log(value);
						
						divIDtype=groupedF[foodtype][0]['foodtypeID'];
						$(".stockIGD_box").append(`<div class="IGD_type_box">`+
												  `<div id="IGD_`+divIDtype+`" class="IGD_type">`+groupedF[foodtype][0]['foodtype']+`</div>`+
												  `<div id="IGD_`+divIDtype+`_content" class="IGD_content"></div></div>`);
						
						for (i = 0; i < value.length; i++) {
						var SDID=groupedF[foodtype][i]['SDID'];
						var amount_input= "amount_input"+SDID;
						var unit_input= "unit_input"+SDID; //unit is location
						var divID="#IGD_"+divIDtype+"_content";
							$(divID).append(
								`<div class="content_box">`+
								`<div class="Ingredient">`+groupedF[foodtype][i]['IngredientID']+`</div>`+
								`<div class="Ingredient-Amount"><input type="number" max="999999" min="0" id="`+amount_input+`" name="ViewTimes" value="`+groupedF[foodtype][i]['Amount']+`"/>gram</div>`+
								`<div class="Ingredient-Unit"><input type="text" id="`+unit_input+`" value="`+groupedF[foodtype][i]['LocationID']+`"/></div>`+
								`<div class="Ingredient-button"><input type="button" class="IGD-button stockIGD-button" value="Edit" onclick="EditStock_IGD(`+groupedF[foodtype][i]['SDID']+`,'`+UID+`');"/></div>`+
								`<div class="Ingredient-button"><input type="button" class="IGD-button stockIGD-button" value="Delete" onclick="DeleteStock_IGD(`+groupedF[foodtype][i]['SDID']+`,'`+UID+`');"/></div>`+
								`</div>`
							);
						}//for length emd
					}//for object
					$(".RCPstockIGDform_add").append(
						`<div class="addNewstockIGD block" onclick="showAddStock_IGD('`+UID+`');">Add new Food</div>`
					);
					
					
				
												  
				})
				.fail(
					function (xhr, textStatus, err) {
						$('#status').html('Error: ' + err);
				});
	}
	
//Show Add StockIngredient table	
	function showAddStock_IGD(UID){
		//var UID =$("#account").text();
		$(".RCPstockIGDform_add").html("");
		$(".RCPstockIGDform_add").append('<div id="" class="addNewIGD IGD_type_box">'+
										'<div id="addStockIGD_type" class="IGD_type">'+
										'Add new Ingredient</div>'+
										'<div id="addStockIGD_content" class="addStockIGD_content IGD_content"></div></div>');
		$(".addStockIGD_content").append(
								`<div class="Ingredient"><input type="text" class="addStockIGDIGD_input_`+UID+`" value="IngredientID or name"/></div>`+
								`<div class="Ingredient-Amount"><input type="number" max="999999" min="0" class="addStockIGDAmount_input_`+UID+`" value="Amount"/>gram</div>`+
								`<div class="Ingredient-Unit"><input type="text" class="addStockIGDUnit_input_`+UID+`" value="Location"/></div>`+//代替location
								`<div class="Ingredient-button"><input type="button" class="IGD-button" value="Add" onclick="AddStock_IGD('`+UID+`');"/></div>`+
								`<div class="Ingredient-button"><input type="button" class="IGD-button" value="Cancel" onclick="showEditStock_IGD('`+UID+`');"/></div>`);
			
		
		
	}


//Add Recipe Instruction Row
	function AddStock_IGD(UID){
			
			var addStockIGDIGD_input_new = ".addStockIGDIGD_input_"+UID+":visible";
			var addStockIGDAmount_input_new = ".addStockIGDAmount_input_"+UID+":visible";
			var addStockIGDUnit_input_new = ".addStockIGDUnit_input_"+UID+":visible";
 		
			var Stock_IGDdata = JSON.stringify({ 
			
			IngredientID: $(addStockIGDIGD_input_new).val(),
			Amount: $(addStockIGDAmount_input_new).val(),
			LocationID: $(addStockIGDUnit_input_new).val()
			});
			
			$.ajax({
				url: UserAPI_URL+"StockIGD/Create/"+UID,
				cache: false,
				type: 'POST',
				contentType: 'application/json; charset=utf-8',
				data: Stock_IGDdata, 
				statusCode: {
					201 /*Created*/: function (data) {
						
						showEditStock_IGD(UID);
					}
				}
			});
		}	

 
//Edit Recipe Ingredient value
	function EditStock_IGD(SDID, UID){
			var UID = $("#account").text();
			var amount_input = "#amount_input"+SDID;
			var unit_input = "#unit_input"+SDID;
 		
			var StockIGD_update = JSON.stringify({ 
			Amount: $(amount_input).val(),
			LocationID: $(unit_input).val()
			});
			
			
			
			$.ajax({
				url: UserAPI_URL+"SDID/"+SDID,
				cache: false,
				type: 'PUT',
				contentType: 'application/json; charset=utf-8',
				data: StockIGD_update, 
				statusCode: {
					201 /*Created*/: function (data) {
						showEditStock_IGD(UID);
					}
				}
			});
		}	


//Delete Recipe Ingredient
	function DeleteStock_IGD(SDID,UID){
 						
			$.ajax({
				url: UserAPI_URL+"/stockIGD/"+SDID,
				cache: false,
				type: 'DELETE',
				contentType: 'application/json; charset=utf-8',
				//data: , 
				statusCode: {
					201 /*Created*/: function (data) {
						showEditStock_IGD(UID);
					}
				}
			});
		}



//Display User Stock
	function displayUserStock(UID){
		var UID=$("#account").text();
		$.getJSON(UserAPI_URL+UID+"/StockIGD", function(data) {
					$("#UrStock").html("");
					
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
					const groupedL = groupBy(data, 'LocationID');
					console.log(groupedL);
			
					for(var name in groupedL) {
						
						var value = groupedL[name];
						//console.log(name);
						//console.log(value);
						//console.log([value][0]["IngredientID"]);
						for(i=0;i<value.length;i++){
							
							if(i===0){
								$("#UrStock").append(
									`<div id="`+name+`" class="location" style="background-image: url(`+groupedL[name][i]['LocationImage']+`)">`+
									`<h3 class="Location_Head">`+groupedL[name][i]['LocationID']+`</h3></div>`
								);
							}
							var location=name;
							var divID="#"+location;
							$(divID).append(
								`<div id="" class="stockIGD-item" style="background-image: url(`+groupedL[name][i]['IGDImage']+`)"><div class="centered IGD_font"><span>`+groupedL[name][i]['IngredientID']+`</span><br>`+groupedL[name][i]['Amount']+`gram</div></div>	`
							);
							
						}
					}
		});
		
	} 