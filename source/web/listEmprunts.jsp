

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">

        <title>Gestion d'une mediatheque</title>
    </head>

    <body>
        <div class="container" style="border: 2px blueviolet solid;border-radius: 10px;">
            <h1>Gestion d'une médiatheque</h1>
            <ul>
                <li><a href="index.jsp">Accueil</a></li>
                <li><a href="rechercherOeuvre.jsp">Rechercher Oeuvre</a></li>
                <!--<li><a href="listEmprunts.jsp">Consulter mes emprunts </a></li>-->
                <li><a href="Test">Remplir la database</a></li>
            </ul>

            <div class="panel-group" id="accordion" style="float: right;width: 30%">
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="headingOne">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                                Mon Panier
                            </a>
                        </h4>
                    </div>
                    <div id="collapseOne" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
                        <div class="panel-body">
                            <form role="form" action="panier" method="post">
                                <div class="form-group">
                                    <label for="InputNom">Nom</label>
                                    <input type="nom" class="form-control" id="nom" name="nom" placeholder="Entrer votre nom">
                                </div>
                                <div class="form-group">
                                    <label for="InputPrenom">Prenom</label>
                                    <input type="prenom" class="form-control" id="prenom" name="prenom" placeholder="Entrer votre prenom">
                                </div>
                                <div class="form-group">
                                    <label for="InputDateNaissance">Date de Naissance</label>
                                    <input type="date" class="form-control" id="date" name="date" placeholder="DD/MM/YYYY">
                                </div>
                                <button type="submit" id="panier" value="panier" class="btn btn-default" style="float: right">Consulter mon panier</button>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="headingTwo">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="true" aria-controls="collapseOne">
                                Mes Emprunts
                            </a>
                        </h4>
                    </div>
                    <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
                        <div class="panel-body">
                            <form role="form" action="listEmprunts" method="post">
                                <div class="form-group">
                                    <label for="InputNom">Nom</label>
                                    <input type="nom" class="form-control" id="nom" name="nom" placeholder="Entrer votre nom">
                                </div>
                                <div class="form-group">
                                    <label for="InputPrenom">Prenom</label>
                                    <input type="prenom" class="form-control" id="prenom" name="prenom" placeholder="Entrer votre prenom">
                                </div>
                                <div class="form-group">
                                    <label for="InputDateNaissance">Date de Naissance</label>
                                    <input type="date" class="form-control" id="date" name="date" placeholder="DD/MM/YYYY">
                                </div>
                                <button type="submit" id="listEmprunts" value="listEmprunts" class="btn btn-default" style="float: right">Consulter Mes emprunts</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="panel-body">
            <form role="form" action="rendreEmprunt" method="post">
                <h3>Liste des oeuvres empruntées par ${requestScope.nameAdherent} ${requestScope.prenomAdherent}</h3>
                <table class="table table-striped"> 

                    <input type="hidden" id="adherentNom" name="adherentNom" value="${requestScope.nameAdherent}" >
                    <input type="hidden" id="adherentPrenom" name="adherentPrenom" value="${requestScope.prenomAdherent}" >
                    <input type="hidden" id="adherentDate" name="adherentDate" value="${requestScope.dateAdherent}" >
                    <thead>
                        <tr">
                            <th>#</th>
                            <th>Titre</th>
                            <th>Auteur</th>
                            <th>Type Oeuvre</th>
                            <th>Date d'emprunt</th>
                            <th>Retourner</th>
                        </tr>
                    </thead>
                    <tbody>

                        <c:forEach var="emprunt" begin="0" items="${requestScope.listEmprunt}">   
                            <tr>
                                <td>${emprunt.item.oeuvre.id}&nbsp;&nbsp;</td> 
                                <td>${emprunt.item.oeuvre.titre}&nbsp;&nbsp;</td> 
                                <td>${emprunt.item.oeuvre.auteur}&nbsp;&nbsp;</td> 
                                <td>${emprunt.item.oeuvre.typeOeuvre}&nbsp;&nbsp;</td> 
                                <td>${emprunt.dateEmprunt}&nbsp;&nbsp;</td> 
                                <td><input type="checkbox" name="check" value="${emprunt.item.idItem}" /></td>
                            </tr> 
                        </c:forEach>
                    </tbody>
                </table>
                <button type="submit" id="retourEmprunt" value="retourEmprunt" class="btn btn-default" style="float: right">Retourner à la médiathèque</button>
            </form>
        </div>
    </body>
</html>
