<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Laboratoire 1 - AMT | Location Véhicules </title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>

<jsp:include page="_menu.jsp"></jsp:include>

<div class="container">
    <div class="row">
        <h1> Coût de location</h1>
        <table class="table table-striped">
            <thead>
            <tr>
                <th scope="col"></th>
                <th scope="col">Moto</th>
                <th scope="col">Berline</th>
                <th scope="col">Fourgon</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th scope="row">0 - 59 mins</th>
                <td>1.70/km</td>
                <td>2.95/km</td>
                <td>3.60/km</td>
            </tr>
            <tr>
                <th scope="row">60 - 179 mins</th>
                <td>1.50/km</td>
                <td>2.60/km</td>
                <td>3.00/km</td>
            </tr>
            <tr>
                <th scope="row">180 - </th>
                <td >1.50/km</td>
                <td >2.30/km</td>
                <td>2.80/km</td>
            </tr>
            </tbody>
        </table>
    </div>

    <div class="row">
        <h1> Que Voulez-vous faire?</h1>
        <div class="container">
            <div class="row">
                <div class="col">
                    <button type="button" class="btn btn-outline-info btn-lg"><a href="stationEtEmplacment">Afficher les Stations</a></button>
                </div>
                <div class="col">
                    <button type="button" class="btn btn-outline-info btn-lg"><a href="employeeTask">Utiliser un véhicule</a></button>
                </div>
                <div class="col">
                    <button type="button" class="btn btn-outline-info btn-lg"><a href="rendreVoiture">Rendre un véhicule</a></button>
                </div>
                <div class="col">
                    <button type="button" class="btn btn-outline-info btn-lg"><a href="login">Login</a></button>
                </div>
                <div class="col">
                    <button type="button" class="btn btn-outline-info btn-lg"><a href="logout">Logout</a></button>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <h1>Effectuer des tâches administratives</h1>
        <div class="container">
            <div class="row">
                <div class="col">
                    <button type="button" class="btn btn-outline-danger btn-lg"><a href="managerTask">Gére les abonnés</a> </button>
                </div>

            </div>
        </div>
    </div>

</div>
</body>
</html>