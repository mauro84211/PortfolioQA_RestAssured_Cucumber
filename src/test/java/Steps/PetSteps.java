package Steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dataModel.Status;
import gherkin.deps.com.google.gson.Gson;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PetSteps extends BaseTest {


    @Given("^a Manager add new \"([^\"]*)\" pet with \"([^\"]*)\"$")
    public void aManagerAddNewCategoryPetWithName(String category, String name) {
        petTest = createPet(faker.random().nextInt(1,500), category, name);
        String requestBody = new Gson().toJson(petTest);

        request = given()
                .header("Content-Type", "application/json")
                .body(requestBody);
                
    }

    @When("^a new pet is added$")
    public void aNewPetIsAdded() {
      response = request.when()
                .post("https://petstore.swagger.io/v2/pet");

    }

    @And("^the response includes correct pet data$")
    public void theResponseIncludesCorrectPetData() {
        response.then().assertThat().body("id", equalTo(petTest.getId()));
        response.then().assertThat().body("name", equalTo(petTest.getName()));
        response.then().assertThat().body("category.name", equalTo(petTest.getCategory().getName()));
    }

    @Then("the status code should be (\\d+)")
    public void theStatusCodeShouldBe(int statusCode) {
        response.then().assertThat().statusCode(statusCode);
    }

    @Given("a Pet exists with (\\d+) category \"([^\"]*)\" and name \"([^\"]*)\"$")
    public void aPetExistsWithCategoryAndName(int id, String category, String name) {
        petTest = createPet(id, category, name);
        String requestBody = new Gson().toJson(petTest);

        response = given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("https://petstore.swagger.io/v2/pet");

        response.then().assertThat().statusCode(200);
    }

    @When("a user retrieves the Pet details by (\\d+)")
    public void aUserRetrievesThePetDetailsBy(int id) {
        request = given().header("Content-Type", "application/json");
        response = request.when().get("https://petstore.swagger.io/v2/pet/" + id);

    }

    @When("a user updates \"([^\"]*)\" and \"([^\"]*)\"$")
    public void aUserUpdatesAnd(String second_name, String status) {
        try {
            Status newStatus = Status.valueOf(status);
            petTest.setName(second_name);
            petTest.setStatus(newStatus);

        } catch (IllegalArgumentException e) {
           throw new IllegalArgumentException("The Pet status" + status + "don't exist");
        }
        String requestBody = new Gson().toJson(petTest);
        response = given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("https://petstore.swagger.io/v2/pet");

    }

    @And("the new pet's data is correct")
    public void theNewPetSDataIsCorrect() {
        response.then().assertThat().body("name", equalTo(petTest.getName()));
        response.then().assertThat().body("status", equalTo(petTest.getStatus().getValue()));

    }

    @When("a user delete the pet")
    public void aUserDeleteThePet() {
        response = given()
                .header("Content-Type", "application/json")
                .when()
                .delete("https://petstore.swagger.io/v2/pet/" + petTest.getId());
    }

    @And("the response contains (\\d+) deleted")
    public void theResponseContainsDeleted(Integer id) {
        response.then().assertThat().body("message", equalTo(id.toString()));
    }
}
