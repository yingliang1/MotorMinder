
var webSocket = new WebSocket("ws://localhost:8080/MotorMinder/DashboardTableServlet");
var target_area = document.getElementById("data_fieldset");
target_area.style.color = '#000';
webSocket.onopen = function(message) {processOpen(message);};
webSocket.onmessage = function(message) {processMessage(message);};
webSocket.onclose = function(message) {processClose(message);};
webSocket.onerror = function(message) {processError(message);};
var username = document.getElementsByTagName("span")[0].innerHTML;
console.log(username);
sendMessage("car," + username);
function processOpen(message) {
	console.log(message);
}

function processMessage(message) {
	console.log(message.data);
	if (message.data.slice(0, 3) == "car") {
		var cars = JSON.parse(message.data.slice(3));
		cars.forEach(function (el) {
			var temp_table = document.createElement("table");
			temp_table.setAttribute("id", el["vin"]);
			var temp_tr = document.createElement("tr");
			
			console.log(el["vin"]);
			target_area.appendChild(temp_table);
			temp_table.appendChild(temp_tr);
			var td_vin = document.createElement("td");
			td_vin.innerHTML = el["vin"];
			var td_year = document.createElement("td");
			td_year.innerHTML = el["year"];
			var td_make = document.createElement("td");
			td_make.innerHTML = el["make"];
			var td_model = document.createElement("td");
			td_model.innerHTML = el["model"];
			var td_nickname = document.createElement("td");
			td_nickname.innerHTML = el["nickName"];
			temp_tr.appendChild(td_vin);
			temp_tr.appendChild(td_year);
			temp_tr.appendChild(td_make);
			temp_tr.appendChild(td_model);
			temp_tr.appendChild(td_nickname);
			temp_tr.appendChild(document.createElement("td"));

			temp_tr.onclick = function() {
				sendMessage(el["vin"]);
			};
		})	
	} else {
		var services = JSON.parse(message.data);
		var temp_table = document.getElementById(services[0]["vin"]);

		if (temp_table.children.length == 1) {
			services.forEach(function (el) {
				
				var temp_tr = document.createElement("tr");
				
				temp_table.appendChild(temp_tr);
				temp_tr.setAttribute("class", "service");
				var td_vin = document.createElement("td");
				td_vin.innerHTML = el["vin"];
				var td_type = document.createElement("td");
				td_type.innerHTML = el["type"];
				var td_shop = document.createElement("td");
				td_shop.innerHTML = el["shop"];
				var td_cost = document.createElement("td");
				td_cost.innerHTML = el["cost"];
				var td_service_date = document.createElement("td");
				td_service_date.innerHTML = el["service_date"];
				var td_insured = document.createElement("td");
				td_insured.innerHTML = el["insured"];

				temp_tr.appendChild(td_vin);
				temp_tr.appendChild(td_type);
				temp_tr.appendChild(td_shop);
				temp_tr.appendChild(td_cost);
				temp_tr.appendChild(td_service_date);
				temp_tr.appendChild(td_insured);
				console.log("appending")
				console.log(temp_tr);		
				console.log(temp_table);
				temp_table.appendChild(temp_tr);

			}); 
		} else {
			console.log("deleting");
			while (temp_table.childNodes[1]) {
				temp_table.removeChild(temp_table.childNodes[1]);
			}
		}
	}
}

function insertAfter(el, referenceNode) {
    referenceNode.parentNode.insertBefore(el, referenceNode.nextSibling);
}

function sendMessage(message) {
	
	setTimeout(function() {
		setTimeout(webSocket.send(message), 100);

	}, 100);
//	webSocket.send(message);
}

function processClose(message) {
	console.log(message);

}

function processError(message) {
	console.log(message);

}
