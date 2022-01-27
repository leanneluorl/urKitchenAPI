var API_URL = "http://127.0.0.1:49000/api/UrKitchen/Recipe/";


/*Search by IGDs*/
function searchRecipebyIGD(){
	var IGD1=$(".selection1").text();
	var IGD2=$(".selection2").text();
	var IGD3=$(".selection3").text();
	
	console.log(IGD1);
	console.log(IGD2);
	console.log(IGD3);
	
	var Selections = JSON.stringify({ 
		selection1:	IGD1,
		selection2: IGD2,
		selection3: IGD3

		});

		$.ajax({
			url: API_URL+"SearchRCPbyIGD",
			cache: false,
			type: 'POST',
			contentType: 'application/json; charset=utf-8',
			data: Selections, 
			statusCode: {
				201 /*Created*/: function (data) {
					alert("Success"); 
					$(".SearchResult_recipe").html("");
					$(".SearchResult_recipe").append(`<h2 class="Head2">Ur Recipes</h2>`+
													 `<div class="SearchRCPbyIGD_recipe_box"></div>`);
					
					var count = data.length;
					$(".SearchRCPbyIGD_recipe_box").append(`<h3 class="Head4"><span class="Count">`+count+`</span> Recipes, You can cook today</h3>`);
					for(i = 0; i < data.length; i++){ 
						$(".SearchRCPbyIGD_recipe_box").append(
							`<div id="`+data[i].RecipeID+`" class="recipes" onclick="load_recipeByID(`+data[i].RecipeID+`);">`+
								`<div class="recipe_title">`+data[i].RecipeTitle+`</div>`+
								`<div class="recipe_image" style="background-image: url(`+data[i].MainImage+`);"></div>`+
							`</div>`
						);
					}
				}
			}
		});

}


function showSearchRCPbyIGD(){		
			
	$.getJSON("http://127.0.0.1:49000/api/UrKitchen/admin/"+"Ingredient"+"/all"+"/foodtype", function(data) {
					$("#Drag-Ingredient").html("");
					$(".selection1,.selection2,.selection3").html("");
					
				
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
					const groupedF = groupBy(data, 'foodTypeID');
					console.log(groupedF);
	/*Get object name: */	
					for(var foodTypeID in groupedF) {
						
						var value = groupedF[foodTypeID];
						console.log(value);
						console.log(foodTypeID);
						
						
						$("#Drag-Ingredient").append(`<div class="drag-L-type">`+
													 `<p class="Head2-foodtype">`+groupedF[foodTypeID][0]['Information']+`</p></div>`+
													 `<div id="FT`+groupedF[foodTypeID][0]['foodTypeID']+`" class="drag-R-type">`+
													 `</div>`);
						
						for (i = 0; i < value.length; i++) {
						
						
						var divID="#FT"+groupedF[foodTypeID][0]['foodTypeID'];
							$(divID).append(
								`<div class="IGD-dragbox">`+
								`<div  class="IGD-Icon" draggable="true" ondragstart="drag(event)" id="IGD`+groupedF[foodTypeID][i]['itemID']+`" style="background-image: url(`+groupedF[foodTypeID][i]['FTimage']+`);" ><p class="IGD-Icon_font">`+groupedF[foodTypeID][i]['item']+`</p></div></div>`
							);
						}//for length emd
					}//for object
												  
				})//get json end
				.fail(
					function (xhr, textStatus, err) {
						$('#status').html('Error: ' + err);
				});
	}


function showPOPrecipe(keyword,order,sort){
	keyword="all"; order = "viewtimes" ; sort="desc";
	$(".POP_recipe_box").html("");
	$.getJSON(API_URL+keyword+"/"+order+"/"+sort, function(data) {
		for(i = 0; i < 4; i++){ 
			$(".POP_recipe_box").append(
				`<div id="`+data[i].RecipeID+`" class="recipes" onclick="load_recipeByID(`+data[i].RecipeID+`);">`+
				`<div class="recipe_title">`+data[i].RecipeTitle+`</div>`+
				`<div class="recipe_image" style="background-image: url(`+data[i].MainImage+`);"></div>`+
				`</div>`
			);
		}
	})
}



function searchRecipes(){
	var keyword = $("#searchRecipe").val();
	$("#page").html("");
	$("#page").load("searchRecipe.html");
}


function showRecipes(keyword,order,sort){
	var order = "viewtimes";
	var sort = "desc";
	$(".POP_recipe_box").html("");
	$.getJSON(API_URL+keyword+"/"+order+"/"+sort, function(data) {
		var count = data.length;
		$(".POP_recipe_box").append(`<h3 class="Head4"><span class="Count">`+count+`</span> Recipes about `+keyword+`</h3>`);
		for(i = 0; i < data.length; i++){ 
			$(".POP_recipe_box").append(
				`<div id="`+data[i].RecipeID+`" class="recipes" onclick="load_recipeByID(`+data[i].RecipeID+`);">`+
					`<div class="recipe_title">`+data[i].RecipeTitle+`</div>`+
					`<div class="recipe_image" style="background-image: url(`+data[i].MainImage+`);"></div>`+
				`</div>`
			);
		}
	})
}


function load_recipeByID(RID){
	var recipeID=RID;
	$("#page").html("");
	$("#page").load("Recipe_ID.html");
	showRCP(RID);
	showRCP_IGD(RID);
	showRCP_instru(RID);
}


function showRCP_IGD(RID){
			
			
			$.getJSON(API_URL+RID+"/IGD", function(data) {
					$(".IGD_box").html("");
					$(".IGD_content").remove();
					$(".RCPigdform_add").html("");
					$(".IGD_box").append('<p class="Head2">Ingredients</p>');
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
					const groupedF = groupBy(data, 'FoodTypeID');
					console.log(groupedF);
	/*Get object name: */	
					for(var foodtype in groupedF) {
						
						var value = groupedF[foodtype];
						console.log("RCP-IGD")
						console.log(foodtype);
						console.log(value);
						
						divIDtype=groupedF[foodtype][0]['FoodTypeID'];
						$(".IGD_box").append(`<div class="IGD_type_box">`+
												  `<div id="IGD_`+divIDtype+`" class="IGD_type">`+groupedF[foodtype][0]['FoodType']+`</div>`+
												  `<div id="IGD_`+foodtype+`_content" class="IGD_content"></div></div>`);
						
						for (i = 0; i < value.length; i++) {
								var divID="#IGD_"+divIDtype+"_content";
									$(divID).append(
									'<div class="recipe-Ingredient">'+groupedF[foodtype][i]['IngredientID']+'</div>'+
									'<div class="recipe-Ingredient-Amount">'+groupedF[foodtype][i]['Amount']+'</div>'+
									'<div class="recipe-Ingredient-Unit">'+groupedF[foodtype][i]['Unit']+'</div>'
								);
						}
					}
								  
				})
				.fail(
					function (xhr, textStatus, err) {
						$('#status').html('Error: ' + err);
				});
	}


	
function showRCP_instru(RID){
	
	$(".instru_box").remove();
	$.getJSON(API_URL+RID+"/Instru", 
		function(data) {
			$(".instru_box").html(" ");
			$(".instru_box").append('<p class="Head2">Instruction</p>');
			for (i = 0; i < data.length; i++) {
				if(i==0||i%2==0){
					$(".instru_box").append(
						`<div class="instru_image" style="background-image: url(`+data[i].instructionPic+`);"></div>`+
						`<div class="instru-step">`+
						`<h1 class="step">Step`+data[i].StepNo+`</h1>`+
						`<p class="instru">`+data[i].Instruction+`</p>`+
						`</div>`
					);
				}
				else{
					$(".instru_box").append(
						`<div class="instru_image2" style="background-image: url(`+data[i].instructionPic+`);"></div>`+
						`<div class="instru-step2">`+
						`<h1 class="step">Step`+data[i].StepNo+`</h1>`+
						`<p class="instru">`+data[i].Instruction+`</p>`+
						`</div>`
					);
				}
			}
			$("#addTotable").html(" ");
			$("#addTotable").append(`<div id="addTotable-button" onClick="addToUrTable(`+RID+`)">Add to Ur table</div>`);
		
		
	})
	.fail(
			function (xhr, textStatus, err) {
				$('#status').html('Error: ' + err);
	});
		
}


function showRCP(RID){
	
	
	$.getJSON(API_URL+RID, 
		function(data) {
			$("#RCP").html("");
			for (i = 0; i < data.length; i++) {
				$("#RCP").append(
					`<h1 class="Head1">`+data[i].RecipeTitle+`</h1>`+
					`<section class="RecipeID_image" style="background-image: url(`+data[i].MainImage+`);"></section>`+
					`<section class="Recipe_info">`+
					`<div class="Recipe_info-Top"><div class="centered"><p>Serving For</p><span class="recipe-main-info">`+data[i].PerServeing_For+`</span></div></div>`+
					`<div class="Recipe_info-Top"><div class="centered"><p>Cooking Time</p><span class="recipe-main-info">`+data[i].CookingTime+`</span></div></div>`+
					`<div class="Recipe_info-buttom"><div class="centered recipe-info">`+
					`<table class="recipe-info-detail"><tbody><tr>`+
					  `<td>Diet Type: `+data[i].DietTypeID+`</td>`+
					  `<td></td>`+
					  `<td>Cuisine: `+data[i].CuisineID+`</td>`+
					`</tr></tbody></table>`+
					data[i].RecipeInfo+
					`<table class="recipe-info-detail"><tbody><tr>`+
					  `<td>ViewTimes: `+data[i].ViewTimes+`</td>`+
					  `<td></td>`+
					  `<td>Create Date: `+data[i].CreateDate+`</td>`+
					`</tr></tbody></table>`+
					`</div></div></section>`
					);
				
			
			}
		
		
		
	})
	.fail(
			function (xhr, textStatus, err) {
				$('#status').html('Error: ' + err);
	});
		
}


function showRCP_Cat(table){
	
	var API="http://127.0.0.1:49000/api/UrKitchen/admin/";
	$.getJSON(API+table+"/all", 
		function(data) {
		var divID = "#"+table;
			$(divID).html("");
			
			for (i = 0; i < data.length; i++) {
				if(i==0||(i%4)==0||i==1||(i%4)==1){
					$(divID).append(
						`<div class="cat_type_25 cat_type" style="background-image: url(`+data[i].Information+`);" onclick="load_recipeByCatalog('`+table+`',`+data[i].itemID+`);">`+
						`<div class="centered cat_type_font">`+data[i].item+`</div></div>`
					);	
				}else{
					$(divID).append(
						`<div class="cat_type_50 cat_type" style="background-image: url(`+data[i].Information+`);" onclick="load_recipeByCatalog('`+table+`',`+data[i].itemID+`);">`+
						`<div class="centered cat_type_font">`+data[i].item+`</div></div>`
					);
					
				}
				
				
			
			}
		
		
		
	})
	.fail(
			function (xhr, textStatus, err) {
				$('#status').html('Error: ' + err);
	});
}

function load_recipeByCatalog(table,itemID){
	var ta = table;
	var it= itemID;
	$("#page").html("");
	$("#page").load("Recipe_Catalog.html");
	
	showRecipesByCatlog(table,itemID);
}

function showRecipesByCatlog(table,itemID){
	
	var API="http://127.0.0.1:49000/api/UrKitchen/admin/";
	$.getJSON(API+table+"/"+itemID, 
		function(data) {
			$("#catalog_type").html("");
			for(i = 0; i < data.length; i++){ 
				$("#catalog_type").append(
					`<div class="cat_type_100" style="background-image: url(`+data[i].Information+`);">`+
					`<div class="centered cat_type_font">`+data[i].item+`</div></div>`
				);
			}
	})
	
	$.getJSON(API_URL+"getRCPbyCatalog/"+table+"/"+itemID, function(data) {
		$(".POP_recipe_box").html("");
		var count = data.length;
		$(".POP_recipe_box").append(`<h3 class="Head4"><span class="Count">`+count+`</span> Recipes</h3>`);
		for(i = 0; i < data.length; i++){ 
			$(".POP_recipe_box").append(
				`<div id="`+data[i].RecipeID+`" class="recipes" onclick="load_recipeByID(`+data[i].RecipeID+`);">`+
					`<div class="recipe_title">`+data[i].RecipeTitle+`</div>`+
					`<div class="recipe_image" style="background-image: url(`+data[i].MainImage+`);"></div>`+
				`</div>`
			);
		}
	})
	
}
	