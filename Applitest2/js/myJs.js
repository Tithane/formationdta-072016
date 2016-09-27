// creation d'un stackage "local"
var stockageListe;

function obtenirObjet(monObjet){
	var monClient;
	monClient = stockageListe.find(function (unClient) {
		return unClient.id == monObjet.data.id;
	})
	if(monClient == null){
		stockageListe.add(monObjet.data);
	}else{
		//Modification du client de ma liste
	}
	//Ajout d'une fonction qui recrée mon html en le mettant a jours
}

//fonction qui affiche mon modal avec les info de la commande
function obtenirModal() {
	//recupère l'attribut 'data' de mon bouton
	var idClient = $(this).attr("data");
	var monClient;
	//recupère la commande dans mon 'stockage local' grace à son numero
	monClient = stockageListe.find(function (unClient) {
		return unClient.id == idClient;
	})
	//JQuery
	$("#ModalTitre").html("Modification Client : ");
	$("#monId").val(monClient.id);
	$("#ModalInfoClt").html("<h5>Modification Client</h5>" +
		"\t<p>Nom :" + monClient.nom + "</p>" +
		"\t<p>Prénom :" + monClient.prenom + "</p>" +
		"\t<p>E-Mail :" + monClient.mail + "</p>" +
		"\t<p>Solde :" + monClient.solde + "</p>"
		);
	//$("#myForm").attr("action", "/mvc/api/clients/" + monClient.id + "/solde");
	$('#myModal').modal();


}

//Jquery : document chargé
$(document).ready(function () {

				// asynchrone
				//appel ajax Jquery
				$.ajax({
		url: "http://localhost:8080/pizzeria-spring/mvc/api/clients",
		method: "GET",
		dataType: "JSON",
		success: function (clientsList, status) {
			if (clientsList === null) {
				$("#bodyTable").append("<tr><td colspan='7'>Aucun Client</td></tr>");
			} else {
				stockageListe = clientsList;
				clientsList.forEach(function (currentClient) {
					//documentation jquery $(XXx).truc()
					$("#bodyTable").append("<tr>" +
						"<td>" + currentClient.id + "</td>" +
						"<td>" + currentClient.nom + "</td>" +
						"<td>" + currentClient.prenom + "</td>" +
						"<td>" + currentClient.mail + "</td>" +
						"<td>" + currentClient.solde + "</td>" +
						"<td><a href='#' class='btn btn-warning btnUpdate' data='" + currentClient.id + "'>Modifier</a></td>" +
						"</tr>");
				});
				$(".btnUpdate").click(obtenirModal);

			}
		}
				});
				$("#myForm").submit(function (event) {
		event.preventDefault();
		var data = $("#myForm").serializeArray();
		var myJson;
		myJson = "{";
		var i = 0;
		var virgule = "";
		data.forEach(function (element) {
			if (i == 0) { virgule = "," } else { virgule = "" }
			myJson += '"' + element.name + '":"' + element.value + '"' + virgule;
			i++
		}, this);
		myJson += "}";
		console.log(myJson);


		$.ajax({
			url: "http://localhost:8080/pizzeria-spring/mvc/api/clients/" + $("#monId").val() + "/solde",
			data: myJson,
			contentType:"application/json",
			dataType: "JSON",
			type: "POST",
		})
		$('#myModal').modal('hide');
	})
});
