function updateCategories()
{
	  var xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	    	  var res = this.responseText.split(",");
	    	  var temp="";   
	    	  temp+="<select class=\"form-control\" >"
	    	  for(i in res)
	    	  {
	    		  temp+="<option> " ;
	    		  temp+=res[i];
	    		  temp+="</option> "
	    	  }
	    	  temp+="</select>"
	    	  document.getElementById("CATEGORY").innerHTML+=temp;
	    	  console.log(temp);
	    }
	  };
	  xhttp.open("GET", "/LifeManagement/GetCategories", true);
	  xhttp.send();
}

function updateGoals()
{
	  var xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	    	  var res = this.responseText.split(",");
	    	  var temp="";   
	    	  temp+="<select class=\"form-control\" >"
	    	  for(i in res)
	    	  {
	    		  temp+="<option> " ;
	    		  temp+=res[i];
	    		  temp+="</option> "
	    	  }
	    	  temp+="</select>"
	    	  document.getElementById("PARENT_GOAL_ID").innerHTML+=temp;
	    	  console.log(temp);
	    }
	  };
	  xhttp.open("GET", "/LifeManagement/GetGoals", true);
	  xhttp.send();
}


function createGoal()
{
	$(document).ready(function(){
	    $("button").click(function(){
	        $.post("/LifeManagement/CreateTask",
	        {
	        	GOAL : document.getElementById("GOAL").value;
	        	CATEGORY : document.getElementById("CATEGORY").value;
	        	TYPE : document.getElementById("TYPE").value;
	        	START_DATE : document.getElementById("START_DATE").value;
	        	DUE_DATE : document.getElementById("DUE_DATE").value;
	        },
	        function(data,status){
	            alert("Data: " + data + "\nStatus: " + status);
	        });
	    });
	});	
}

function saveBloodPressure()
{
	
}

function saveWeight()
{
	
}


function savePulse()
{
	
}

function updateTypes()
{
	  var xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	    	  var res = this.responseText.split(",");
	    	  var temp="";   
	    	  temp+="<select class=\"form-control\" >"
	    	  for(i in res)
	    	  {
	    		  temp+="<option> " ;
	    		  temp+=res[i];
	    		  temp+="</option> "
	    	  }
	    	  temp+="</select>"
	    	  document.getElementById("TYPE").innerHTML+=temp;
	    	  console.log(temp);
	    }
	  };
	  xhttp.open("GET", "/LifeManagement/GetTypes", true);
	  xhttp.send();
}