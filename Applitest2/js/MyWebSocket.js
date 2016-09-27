wsUri = " ws://localhost:8080/pizzeria-spring/mvc/api/myHandler",

websocket = new WebSocket(wsUri);
websocket.onopen = function (evt) {
	console.log("Connexion établie.");
};
websocket.onmessage = function (evt) {
				console.log("Message reçu : ", evt);
};
websocket.onerror = function (evt) {
				console.log("Une erreur est survenu :",evt)
};
