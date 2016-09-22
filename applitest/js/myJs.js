// creation d'un stackage "local"
var stockageListe ;

//fonction qui affiche mon modal avec les info de la commande
function obtenirModal(){
	//recupère l'attribut 'data' de mon bouton
	var idCommande = $(this).attr("data");
	var maCommande;
	//recupère la commande dans mon 'stockage local' grace à son numero
	maCommande = stockageListe.find(function(uneCommande){
		return uneCommande.numero_commande === idCommande;
	})
	//JQuery
	$("#ModalTitre").html("Commande : "+maCommande.numero_commande);
	$("#ModalInfoClt").html("<h5>Information Client</h5>"+
	"\t<p>Nom :"+maCommande.client_id.nom+"</p>"+
	"\t<p>Prénom :"+maCommande.client_id.prenom+"</p>"+
	"\t<p>E-Mail :"+maCommande.client_id.mail+"</p>");
	var presentationPizza="";
	var prixFinal =0;
	maCommande.pizzas.forEach(function(element) {
		prixFinal += element.prix;
		presentationPizza += "<tr>"
		+"<td>"+element.code+"</td>"
		+"<td>"+element.nom+"</td>"
		+"<td>"+element.categorie+"</td>"
		+"<td>"+element.prix+"</td><td></td></tr>"
		
	}, this);
	
	var entete = "<legend>Liste de Pizzas :</legend><thead><tr><th>Code</th><th>Nom</th><th>Categorie</th><th>Prix</th></tr></thead>";
	$("#ModalInfoCom").html("<h5>Information Commande</h5>"+
	"<table height='50px' class='table-bordered' style='text-align: center'>"+entete+"<tbody>"+presentationPizza+"<tr><td colspan = '2'>Prix Total</td><td colspan = '2'>"+prixFinal+"€</td></tr></tbody></table>");
	$('#myModal').modal();

	//log du navigateur
	console.log(maCommande);
}

//Jquery : document chargé
$(document).ready(function(){

				// asynchrone
				//appel ajax Jquery
				$.ajax({
					url:"http://localhost:8080/pizzeria-admin-web/api/rest/commande",
					method:"GET",
					dataType:"JSON",
					success:function(commandesList,status){
						if(commandesList === null){
							$("#bodyTable").append("<tr><td colspan='7'>Aucune commande</td></tr>");
						}else{
							stockageListe = commandesList;
							commandesList.forEach(function(currentCommande) {
								
								var livreur;
								if(currentCommande.livreur_id === null){
									livreur = "<td>Aucun</td>";
								}else{
									livreur = "<td>"+currentCommande.livreur_id.nom+"</td>" ;
								}
								//documentation jquery $(XXx).truc()
								$("#bodyTable").append("<tr>"+
								"<td>"+currentCommande.numero_commande+"</td>"+
								"<td>"+currentCommande.client_id.nom+"</td>"+
								livreur+
								"<td>"+currentCommande.date_commande+"</td>"+
								"<td>"+currentCommande.statut+"</td>"+
								"<td><a href='#' class='btn btn-primary btnView' data='"+currentCommande.numero_commande+"'>Voir</a></td>"+
								"<td><a href='#' class='btn btn-warning'>Modifier</a></td>"+
								"</tr>");
							});
							$(".btnView").click(obtenirModal);
							
						}
					}
				});
			});