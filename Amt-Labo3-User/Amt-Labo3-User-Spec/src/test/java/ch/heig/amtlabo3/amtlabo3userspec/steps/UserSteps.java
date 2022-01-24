package ch.heig.amtlabo3.amtlabo3userspec.steps;

import ch.heig.amtlabo3.ApiException;
import ch.heig.amtlabo3.ApiResponse;
import ch.heig.amtlabo3.api.UsersEndPointApi;
import ch.heig.amtlabo3.api.dto.User;
import ch.heig.amtlabo3.api.dto.UserSummary;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.swagger.annotations.Api;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;


import static org.junit.Assert.assertEquals;

public class UserSteps {

    private final UsersEndPointApi api = new UsersEndPointApi();
    private User user;
    private User userAdmin;
    private UserSummary userSummary;
    private int statusCode;

    private String tokenJWT = "";
    private String url = "http://localhost:8080/api";


    public UserSteps(){ api.getApiClient().setBasePath(url);}

    @Given("^I have an user payload$")
    public void i_have_an_user_payload() throws Throwable{
        //id ?
        user = new User();
        user.setId(0);
        user.setUserName("Pseudo");
        user.setUserPassword("password");
        user.setSolde(BigDecimal.valueOf(100));
        user.setIsAdmin(false);
        user.setIsBlocked(false);
    }

    @Given("^I have an userAdmin payload$")
    public void i_have_an_user_admin_payload() throws Throwable{
        //id ?
        userAdmin = new User();
        userAdmin.setId(0);
        userAdmin.setUserName("admin");
        userAdmin.setUserPassword("admin");
        userAdmin.setSolde(BigDecimal.valueOf(100));
        userAdmin.setIsAdmin(true);
        userAdmin.setIsBlocked(false);
    }

    @Given("^I have an userSummary payload$")
    public void i_have_an_userSummary_payload() throws Throwable{
        userSummary = new UserSummary();
        userSummary.setIsBlocked(true);
        userSummary.setSolde(BigDecimal.valueOf(0));
    }

    @Given("^I have a token$")
    public void i_have_token() throws Throwable{
        System.out.println("  Token found : " + tokenJWT);
    }

    @When("I Post it to the \\/authenticate endpoint")
    public void i_post_it_to_the_authenticate_endpoint() throws Throwable{

        try {
            //String compatible JSON
            String data = "{\"username\":\"" + userAdmin.getUserName() +
                    "\",\"password\":\"" + userAdmin.getUserPassword() + "\"}";

            HttpClient client = HttpClient.newBuilder().build();
            HttpRequest request = HttpRequest.newBuilder()
                    .header("Content-Type", "application/json")
                    .uri(URI.create(url + "/authenticate"))
                    .POST(HttpRequest.BodyPublishers.ofString(data))
                    .build();

            //pour récuperer le body lors de la réponse au POST
            HttpResponse<String> bodyResponse =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            tokenJWT = new String();

            tokenJWT = bodyResponse.body();
            tokenJWT = tokenJWT.substring(8);
            tokenJWT = tokenJWT.substring(0, tokenJWT.length() - 2);

            statusCode = bodyResponse.statusCode();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @When("I Get it to the \\/users endpoint")
    public void i_GET_to_the_endpoint() throws Throwable{

        try {
            String auth = "";
            if (!tokenJWT.equals(""))
                auth = "Bearer " + tokenJWT;

            HttpClient client = HttpClient.newBuilder().build();
            HttpRequest request = HttpRequest.newBuilder()
                    .header("Authorization", auth)
                    .uri(URI.create(url + "/users"))
                    .GET()
                    .build();

            //pour récuperer le body lors de la réponse au POST
            HttpResponse<String> bodyResponse =
                    client.send(request, HttpResponse.BodyHandlers.ofString());


            statusCode = bodyResponse.statusCode();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @When("^I Get it to the \\/users\\/\\{id} endpoint$")
    public void i_get_it_to_the_users_endpoint()  throws Throwable{

        try {
            String auth = "";
            if (!tokenJWT.equals(""))
                auth = "Bearer " + tokenJWT;

            HttpClient client = HttpClient.newBuilder().build();
            HttpRequest request = HttpRequest.newBuilder()
                    .header("Authorization", auth)
                    .uri(URI.create(url + "/users/" + 1))
                    .GET()
                    .build();

            //pour récuperer le body lors de la réponse au POST
            HttpResponse<String> bodyResponse =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            statusCode = bodyResponse.statusCode();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @When("^I POST it to the /users endpoint$")
    public void i_POST_it_to_the_endpoint() throws Throwable{

        try {
            String auth = "";
            if (!tokenJWT.equals(""))
                auth = "Bearer " + tokenJWT;

            //String compatible JSON
            String data = "{\"id\":" + user.getId() + ",\"userName\":\"" + user.getUserName() + "\",\"userPassword\":" +
                    user.getUserPassword() + "\"TemporaryPassword\",\"solde\":" + user.getSolde() + ",\"isAdmin\":" +
                    user.getIsAdmin() + ",\"isBlocked\":" + user.getIsBlocked() + "}";

            data = "{\"id\":0,\"userName\":\"temporaryUser\",\"userPassword\":\"TemporaryPassword\",\"solde\":0.0,\"isAdmin\":false,\"isBlocked\":false}";

            HttpClient client = HttpClient.newBuilder().build();
            HttpRequest request = HttpRequest.newBuilder()
                    .header("Content-Type", "application/json")
                    .header("Authorization", auth)
                    .uri(URI.create(url + "/users"))
                    .POST(HttpRequest.BodyPublishers.ofString(data))
                    .build();

            //pour récuperer le body lors de la réponse au POST
            HttpResponse<String> bodyResponse =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            if (!tokenJWT.equals("")) {
                String numero = bodyResponse.headers().toString();
                numero = numero.substring(numero.lastIndexOf("/") + 1);
                numero = numero.substring(0, numero.length() - 4);
                int newId = Integer.parseInt(numero);
                user.setId(newId);
                System.out.println(user.getId());

            }

            statusCode = bodyResponse.statusCode();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }


    }

    @When("^I Delete it by the id to the /users endpoint$")
    public void i_DELETE_it_to_the_endpoint() throws Throwable{

        try {
            String auth = "";
            int id = 0;
            if (!tokenJWT.equals("")) {
                id = user.getId();
                auth = "Bearer " + tokenJWT;
                System.out.println(user.getId());
            }


            HttpClient client = HttpClient.newBuilder().build();
            HttpRequest request = HttpRequest.newBuilder()
                    .header("Authorization", auth)
                    .uri(URI.create(url + "/users/" + id))
                    .DELETE()
                    .build();

            //pour récuperer le body lors de la réponse au POST
            HttpResponse<String> bodyResponse =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            statusCode = bodyResponse.statusCode();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @When("^I Update it by the id to the /users endpoint$")
    public void i_UPDATE_it_to_the_endpoint() throws Throwable{
        try {
            String auth = "";
            int id = 0;
            if (!tokenJWT.equals("")) {
                id = user.getId();
                auth = "Bearer " + tokenJWT;
                System.out.println(user.getId());
            }

            //String compatible JSON
            String data = "{\"solde\":" + 1000 + ",\"isBlocked\":" + true + "}";

            HttpClient client = HttpClient.newBuilder().build();
            HttpRequest request = HttpRequest.newBuilder()
                    .header("Content-Type", "application/json")
                    .header("Authorization", auth)
                    .uri(URI.create(url + "/users/" + id))
                    .PUT(HttpRequest.BodyPublishers.ofString(data))
                    .build();

            //pour récuperer le body lors de la réponse au POST
            HttpResponse<String> bodyResponse =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            statusCode = bodyResponse.statusCode();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Then("^I receive a (\\d+) status code$")
    public void i_receive_a_status_code(int arg1) throws Throwable{
        assertEquals(arg1,statusCode);
    }
}
