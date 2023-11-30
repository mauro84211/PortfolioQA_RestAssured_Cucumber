package Steps;


import dataModel.Category;
import dataModel.Pet;
import dataModel.Status;
import dataModel.User;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import net.datafaker.Faker;

import java.util.ArrayList;
import java.util.List;

public  class  BaseTest {

    protected Response response;
    protected RequestSpecification request;
    protected ValidatableResponse json;
    Faker faker = new Faker();
    User userTest ;
    Pet petTest ;



    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public RequestSpecification getRequest() {
        return request;
    }


    public List<User> createUserList(){
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            User user = new User(
                    0,
                    faker.name().username(),
                    faker.name().firstName(),
                    faker.name().lastName(),
                    faker.internet().emailAddress(),
                    faker.regexify("[a-z1-9]{10}"),
                    faker.phoneNumber().cellPhone(),
                    0);
            users.add(user);
        }
        return users;
    }

    public Pet createPet(int id, String category, String name)
    {
        Category categoryPet = new Category(0, category);
        return new Pet(id,categoryPet, name, Status.available );
    }

}
