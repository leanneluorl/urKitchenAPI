var API_URL = "http://127.0.0.1:49000/api/UrKitchen/Recipe/";

	
	
	function showEditRCP_IGD(RID){
			
			$.getJSON(API_URL+RID+"/IGD", function(data) {
					$(".IGD_box").html("");
					$(".IGD_content").remove();
					$(".RCPigdform_add").html("");
			
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
					const groupedF = groupBy(data, 'FoodType');
					console.log(groupedF);
	/*Get object name: */	
					for(var foodtype in groupedF) {
						
						var value = groupedF[foodtype];
						console.log(foodtype);
						console.log(value);
						
						divIDtype=groupedF[foodtype][0]['FoodTypeID'];
						$(".IGD_box").append(`<div class="IGD_type_box">`+
												  `<div id="IGD_`+divIDtype+`" class="IGD_type">`+groupedF[foodtype][0]['FoodType']+`</div>`+
												  `<div id="IGD_`+divIDtype+`_content" class="IGD_content"></div></div>`);
						
						for (i = 0; i < value.length; i++) {
						var SDID=groupedF[foodtype][i]['RecipeIngredientID'];
						var amount_input= "amount_input"+SDID;
						var unit_input= "unit_input"+SDID; //unit is location
						var divID="#IGD_"+divIDtype+"_content";
							$(divID).append(
								`<div class="content_box">`+
								`<div class="Ingredient">`+groupedF[foodtype][i]['IngredientID']+`</div>`+
								`<div class="Ingredient-Amount"><input type="number" max="999999" min="0" step="0.1" id="`+amount_input+`" name="ViewTimes" value="`+groupedF[foodtype][i]['Amount']+`"/></div>`+
								`<div class="Ingredient-Unit"><input type="text" id="`+unit_input+`" value="`+groupedF[foodtype][i]['Unit']+`"/></div>`+
								`<div class="Ingredient-button"><input type="button" class="IGD-button IGD-button" value="Edit" onclick="EditRCP_IGD(`+groupedF[foodtype][i]['RecipeIngredientID']+`,'`+RID+`');"/></div>`+
								`<div class="Ingredient-button"><input type="button" class="IGD-button IGD-button" value="Delete" onclick="DeleteRCP_IGD(`+groupedF[foodtype][i]['RecipeIngredientID']+`,'`+RID+`');"/></div>`+
								`</div>`
							);
						}//for length emd
					}//for object
					$(".RCPigdform_add").append(
						`<div class="addNewIGD block" onclick="showAddRCP_IGD('`+RID+`');">Add new Food</div>`
					);
					
					
				
												  
				})
				.fail(
					function (xhr, textStatus, err) {
						$('#status').html('Error: ' + err);
				});
	}



/*
	function showEditRCP_IGD(RID){
			
			
			$.getJSON(API_URL+RID+"/IGD", function(data) {
					$(".IGD_box").html("");
					$(".IGD_content").remove();
					$(".RCPigdform_add").html("");
					var m=""; var v=""; var sf=""; var sa=""; var sp=""; var g=""; var d="";
					for(i = 0; i < data.length; i++){ 
						if(data[i].FoodType=="Meat and Poultry"){m="Meat and Poultry";}
						else if(data[i].FoodType=="Vegetable"){v="Vegetable";}
						else if(data[i].FoodType=="Seafood"){sf="Seafood";}	
						else if(data[i].FoodType=="Condiment"){sa="Condiment";}
						else if(data[i].FoodType=="Fruit"){sp="Fruit";}
						else if(data[i].FoodType=="Grains, Bean	s and Nuts"){g="Grains, Beans and Nuts";}
					  	else if(data[i].FoodType=="Dairy Foods"){d="Dairy Foods";}
					}
				
					if(data.length==0){
								$(".RCPigdform_add").append(
									'<div class="addNewIGD"><input type="button" class="IGD-button" value="Add new Ingrdient" onclick="showAddRCP_IGD('+RID+');"/></div>'
								);
					}
					var divID="";
					if(m=="Meat and Poultry"){	
						divID=m;
						$(".IGD_box").append('<div class="IGD_type_box"><div id="" class="IGD_Meat IGD_type">'+divID+'</div>'+
												'<div id="" class="IGD_Meat_content IGD_content"></div></div>');
					}
					if(sf=="Seafood"){	
						divID=sf;
						$(".IGD_box").append('<div class="IGD_type_box"><div id="" class="IGD_'+divID+' IGD_type">'+divID+'</div>'+
											 '<div id="" class="IGD_'+divID+'_content IGD_content"></div></div>');
					}
					if(v=="Vegetable"){	
						divID=v;
						$(".IGD_box").append('<div class="IGD_type_box"><div id="" class="IGD_'+divID+' IGD_type">'+divID+'</div>'+
											 '<div id="" class="IGD_'+divID+'_content IGD_content"></div></div>');
					}
					if(g=="Grains, Beans and Nuts"){	
						divID=g;
						$(".IGD_box").append('<div class="IGD_type_box"><div id="" class="IGD_Grain IGD_type">'+divID+'</div>'+
											 '<div id="" class="IGD_Grain_content IGD_content"></div></div>');
					}
					if(d=="Dairy Foods"){	
						divID=d;
						$(".IGD_box").append('<div class="IGD_type_box"><div id="" class="IGD_Dairy IGD_type">'+divID+'</div>'+
											 '<div id="" class="IGD_Dairy_content IGD_content"></div></div>');
					}
					
					
					if(sa=="Condiment"){	
						divID=sa;
						$(".IGD_box").append('<div class="IGD_type_box"><div id="IGD_'+divID+'" class="IGD_type">'+divID+'</div>'+
											 '<div id="" class="IGD_'+divID+'_content IGD_content"></div></div>');
					}
					if(sp=="Fruit"){	
						divID=sp;
						$(".IGD_box").append('<div class="IGD_type_box"><div id="IGD_'+divID+'" class="IGD_type">'+divID+'</div>'+
											 '<div id="" class="IGD_'+divID+'_content IGD_content"></div></div>');
					}
				
					
				
					for (i = 0; i < data.length; i++) {
							var amount_input= "amount_input"+data[i].RecipeIngredientID;
							var unit_input= "unit_input"+data[i].RecipeIngredientID;
						 	if(data[i].FoodType =="Meat and Poultry"){
								$(".IGD_Meat_content").append(
									'<div class="Ingredient">'+data[i].IngredientID+'</div>'+
									'<div class="Ingredient-Amount"><input type="number" max="999999" min="0" id="'+amount_input+'" name="ViewTimes" value="'+data[i].Amount+'"/></div>'+
									'<div class="Ingredient-Unit"><input type="text" id="'+unit_input+'" name="DietType" value="'+data[i].Unit+'"/></div>'+
									'<div class="Ingredient-button"><input type="button" class="IGD-button" value="Edit" onclick="EditRCP_IGD('+data[i].RecipeIngredientID+","+RID+');"/></div>'+
									'<div class="Ingredient-button"><input type="button" class="IGD-button" value="Delete" onclick="DeleteRCP_IGD('+data[i].RecipeIngredientID+","+RID+');"/></div>'
								);
							}
							if(data[i].FoodType =="Vegetable"){
								$(".IGD_Vegetable_content").append(
									'<div class="Ingredient">'+data[i].IngredientID+'</div>'+
									'<div class="Ingredient-Amount"><input type="number" max="999999" min="0" id="'+amount_input+'" name="ViewTimes" value="'+data[i].Amount+'"/></div>'+
									'<div class="Ingredient-Unit"><input type="text" id="'+unit_input+'" name="DietType" value="'+data[i].Unit+'"/></div>'+
									'<div class="Ingredient-button"><input type="button" class="IGD-button" value="Edit" onclick="EditRCP_IGD('+data[i].RecipeIngredientID+","+RID+');"/></div>'+
									'<div class="Ingredient-button"><input type="button" class="IGD-button" value="Delete" onclick="DeleteRCP_IGD('+data[i].RecipeIngredientID+","+RID+');"/></div>');
							}
							if(data[i].FoodType =="Seafood"){
								$(".IGD_Seafood_content").append(
									'<div class="Ingredient">'+data[i].IngredientID+'</div>'+
									'<div class="Ingredient-Amount"><input type="number" max="999999" min="0" id="'+amount_input+'" name="ViewTimes" value="'+data[i].Amount+'"/></div>'+
									'<div class="Ingredient-Unit"><input type="text" id="'+unit_input+'" name="DietType" value="'+data[i].Unit+'"/></div>'+
									'<div class="Ingredient-button"><input type="button" class="IGD-button" value="Edit" onclick="EditRCP_IGD('+data[i].RecipeIngredientID+","+RID+');"/></div>'+
									'<div class="Ingredient-button"><input type="button" class="IGD-button" value="Delete" onclick="DeleteRCP_IGD('+data[i].RecipeIngredientID+","+RID+');"/></div>');
							}
							if(data[i].FoodType =="Condiment"){
								$(".IGD_Condiment_content").append(
									'<div class="Ingredient">'+data[i].IngredientID+'</div>'+
									'<div class="Ingredient-Amount"><input type="number" max="999999" min="0" id="'+amount_input+'" name="ViewTimes" value="'+data[i].Amount+'"/></div>'+
									'<div class="Ingredient-Unit"><input type="text" id="'+unit_input+'" name="DietType" value="'+data[i].Unit+'"/></div>'+
									'<div class="Ingredient-button"><input type="button" class="IGD-button" value="Edit" onclick="EditRCP_IGD('+data[i].RecipeIngredientID+","+RID+');"/></div>'+
									'<div class="Ingredient-button"><input type="button" class="IGD-button" value="Delete" onclick="DeleteRCP_IGD('+data[i].RecipeIngredientID+","+RID+');"/></div>');
							}
							if(data[i].FoodType =="Fruit"){
								$(".IGD_Fruit_content").append(
									'<div class="Ingredient">'+data[i].IngredientID+'</div>'+
									'<div class="Ingredient-Amount"><input type="number" max="999999" min="0" id="'+amount_input+'" name="ViewTimes" value="'+data[i].Amount+'"/></div>'+
									'<div class="Ingredient-Unit"><input type="text" id="'+unit_input+'" name="DietType" value="'+data[i].Unit+'"/></div>'+
									'<div class="Ingredient-button"><input type="button" class="IGD-button" value="Edit" onclick="EditRCP_IGD('+data[i].RecipeIngredientID+","+RID+');"/></div>'+
									'<div class="Ingredient-button"><input type="button" class="IGD-button" value="Delete" onclick="DeleteRCP_IGD('+data[i].RecipeIngredientID+","+RID+');"/></div>');
							}
							if(data[i].FoodType =="Grains, Beans and Nuts"){
								$(".IGD_Grain_content").append(
									'<div class="Ingredient">'+data[i].IngredientID+'</div>'+
									'<div class="Ingredient-Amount"><input type="number" max="999999" min="0" id="'+amount_input+'" name="ViewTimes" value="'+data[i].Amount+'"/></div>'+
									'<div class="Ingredient-Unit"><input type="text" id="'+unit_input+'" name="DietType" value="'+data[i].Unit+'"/></div>'+
									'<div class="Ingredient-button"><input type="button" class="IGD-button" value="Edit" onclick="EditRCP_IGD('+data[i].RecipeIngredientID+","+RID+');"/></div>'+
									'<div class="Ingredient-button"><input type="button" class="IGD-button" value="Delete" onclick="DeleteRCP_IGD('+data[i].RecipeIngredientID+","+RID+');"/></div>');
							}
							if(data[i].FoodType =="Dairy"){
								$(".IGD_Dairy_content").append(
									'<div class="Ingredient">'+data[i].IngredientID+'</div>'+
									'<div class="Ingredient-Amount"><input type="number" max="999999" min="0" id="'+amount_input+'" name="ViewTimes" value="'+data[i].Amount+'"/></div>'+
									'<div class="Ingredient-Unit"><input type="text" id="'+unit_input+'" name="DietType" value="'+data[i].Unit+'"/></div>'+
									'<div class="Ingredient-button"><input type="button" class="IGD-button" value="Edit" onclick="EditRCP_IGD('+data[i].RecipeIngredientID+","+RID+');"/></div>'+
									'<div class="Ingredient-button"><input type="button" class="IGD-button" value="Delete" onclick="DeleteRCP_IGD('+data[i].RecipeIngredientID+","+RID+');"/></div>');
							}
							if(i==data.length-1||data.length==0){
								$(".RCPigdform_add").append(
									'<div class="addNewIGD"><input type="button" class="IGD-button" value="Add new Ingrdient" onclick="showAddRCP_IGD('+RID+');"/></div>'
								);
							}
					}							  
				})
				.fail(
					function (xhr, textStatus, err) {
						$('#status').html('Error: ' + err);
				});
	}
*/
//Show Add Recipe Ingredient table	
	function showAddRCP_IGD(RID){
		
		$(".RCPigdform_add").html("");
		$(".RCPigdform_add").append('<div id="" class="addNewIGD IGD_type_box">'+
										'<div id="addIGD_type" class="IGD_type">'+
										'Add new Ingredient</div>'+
										'<div id="addIGD_content" class="addIGD_content IGD_content"></div></div>');
		$(".addIGD_content").append(
								'<div class="Ingredient"><input type="text" class="addIGDIGD_input_'+RID+'" value="IngredientID or name"/></div>'+
								'<div class="Ingredient-Amount"><input type="number" max="999999" min="0" class="addIGDAmount_input_'+RID+'" value="Amount"/></div>'+
								'<div class="Ingredient-Unit"><input type="text" class="addIGDUnit_input_'+RID+'" value="gram"/></div>'+
								'<div class="Ingredient-button"><input type="button" class="IGD-button" value="Add" onclick="AddRCP_IGD('+RID+');"/></div>'+
								'<div class="Ingredient-button"><input type="button" class="IGD-button" value="Cancel" onclick="showEditRCP_IGD('+RID+');"/></div>');
			
		
		
	}


//Add Recipe Instruction Row
	function AddRCP_IGD(RID){
			
			var addIGDIGD_input_new = ".addIGDIGD_input_"+RID+":visible";
			var addIGDAmount_input_new = ".addIGDAmount_input_"+RID+":visible";
			var addIGDUnit_input_new = ".addIGDUnit_input_"+RID+":visible";
 		
			var RCP_IGDdata = JSON.stringify({ 
			
			IngredientID: $(addIGDIGD_input_new).val(),
			Amount: $(addIGDAmount_input_new).val(),
			Unit: $(addIGDUnit_input_new).val()
			});
			
			
			
			$.ajax({
				url: API_URL+"IGD/Create/"+RID,
				cache: false,
				type: 'POST',
				contentType: 'application/json; charset=utf-8',
				data: RCP_IGDdata, 
				statusCode: {
					201 /*Created*/: function (data) {
						
						ShowEditRCP(RID);
						showEditRCP_IGD(RID)
					}
				}
			});
		}	

 
//Edit Recipe Ingredient value
	function EditRCP_IGD(IGDID, RecipeID){
			var RID = RecipeID;
			var amount_input = "#amount_input"+IGDID;
			var unit_input = "#unit_input"+IGDID;
 		
			var RCPIGD_update = JSON.stringify({ 
			Amount: $(amount_input).val(),
			Unit: $(unit_input).val()
			});
			
			
			
			$.ajax({
				url: API_URL+"IGD/"+IGDID,
				cache: false,
				type: 'PUT',
				contentType: 'application/json; charset=utf-8',
				data: RCPIGD_update, 
				statusCode: {
					201 /*Created*/: function (data) {
						ShowEditRCP(RID);
					}
				}
			});
		}	


//Delete Recipe Ingredient
	function DeleteRCP_IGD(IGDID,RecipeID){
 			var RID = RecipeID;				
			$.ajax({
				url: API_URL+"IGD/"+IGDID,
				cache: false,
				type: 'DELETE',
				contentType: 'application/json; charset=utf-8',
				//data: , 
				statusCode: {
					201 /*Created*/: function (data) {
						ShowEditRCP(RID);
					}
				}
			});
		}

	
	
