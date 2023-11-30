package Steps;

import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dataModel.User;
import gherkin.deps.com.google.gson.Gson;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class UsersSteps extends BaseTest {

    @Before
    public void updateUser(){
        userTest= new User(
                8425,
                faker.name().username(),
                faker.name().firstName(),
                faker.name().lastName(),
                faker.bothify("????##@gmail.com"),
                faker.regexify("[a-z1-9]{10}"),
                faker.phoneNumber().cellPhone(),
                0
        );
    }

    @Given("^an user provide information \"([^\"]*)\"$")
    public void aUserProvideInformationUsername(String username) {
        userTest.setUsername(username);
        String requestBody = new Gson().toJson(userTest);

        request = given()
                .header("Content-Type", "application/json")
                .body(requestBody);
    }


    @When("^a user send the information$")
    public void aUserSendTheInformation() {
        response = request.when().post("https://petstore.swagger.io/v2/user");
    }



    @And("^the response includes correct type$")
    public void theResponseIncludesCorrectType() {
        response.then().assertThat().body("type", equalTo("unknown"));
    }

    @Given("^a user provide users information$")
    public void aUserProvideUsersInformation() {
        String requestBody = new Gson().toJson(createUserList());
        System.out.println(requestBody);

        request = given()
                .header("Content-Type", "application/json")
                .body(requestBody);
    }

    @When("^the users information is sent$")
    public void theUsersInformationIsSent() {
        response = request.when().post("https://petstore.swagger.io/v2/user/createWithArray");
    }

    @And("^the response includes correct \"([^\"]*)\"$")
    public void theResponseIncludesCorrectMessage(String message) {
        response.then().assertThat().body("message", equalTo(message));
    }

    @Given("^a user exists with an \"([^\"]*)\"$")
    public void aUserExistsWithAnUsername(String username) {
        userTest.setUsername(username);
        String requestBody = new Gson().toJson(userTest);
        response = given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when().post("https://petstore.swagger.io/v2/user");
        response.then().assertThat().statusCode(200);

    }

    @When("^a user retrieves the user details by \"([^\"]*)\"$")
    public void aUserRetrievesTheUserDetailsByUsername(String username) {
        request = given().header("Content-Type", "application/json");
        response = request.when().get("https://petstore.swagger.io/v2/user/" + username);
    }

    @And("^the response includes correct user data$")
    public void theResponseIncludesCorrectUserData() {
        response.then().assertThat().body("id", equalTo(userTest.getId()));
        response.then().assertThat().body("username", equalTo(userTest.getUsername()));
        response.then().assertThat().body("firstName", equalTo(userTest.getFirstName()));
        response.then().assertThat().body("lastName", equalTo(userTest.getLastName()));
        response.then().assertThat().body("email", equalTo(userTest.getEmail()));
        response.then().assertThat().body("password", equalTo(userTest.getPassword()));
        response.then().assertThat().body("phone", equalTo(userTest.getPhone()));
        response.then().assertThat().body("userStatus", equalTo(userTest.getUserStatus()));
    }


    @Then("the status code is (\\d+)")
    public void theStatusCodeIs(int statusCode) {
        response.then().assertThat().statusCode(statusCode);
    }


}