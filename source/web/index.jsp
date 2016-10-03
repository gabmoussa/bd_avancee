<!--
  Copyright (c) 2010, Oracle. All rights reserved.

  Redistribution and use in source and binary forms, with or without
  modification, are permitted provided that the following conditions are met:

  * Redistributions of source code must retain the above copyright notice,
    this list of conditions and the following disclaimer.

  * Redistributions in binary form must reproduce the above copyright notice,
    this list of conditions and the following disclaimer in the documentation
    and/or other materials provided with the distribution.

  * Neither the name of Oracle nor the names of its contributors
    may be used to endorse or promote products derived from this software without
    specific prior written permission.

  THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
  AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
  IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
  ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
  LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
  SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
  INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
  CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
  ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
  THE POSSIBILITY OF SUCH DAMAGE.
-->

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%--
The taglib directive below imports the JSTL library. If you uncomment it,
you must also add the JSTL library to the project. The Add Library... action
on Libraries node in Projects view can be used to add the JSTL 1.1 library.
--%>
<%--
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
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
            <a href="rechercherOeuvre.jsp" class="btn btn-primary btn-lg align-center" role="button" style="margin-right: 40%;margin-left: 40%">
                Rechercher une oeuvre <span class="glyphicon glyphicon-search"></span>
            </a>

        </div>
    </body>
</html>
