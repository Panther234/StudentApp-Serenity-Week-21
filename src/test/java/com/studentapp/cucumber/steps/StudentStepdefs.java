package com.studentapp.cucumber.steps;
/*
 * Created By: Hiren Patel
 * Project Name: StudentApp-Serenity-Week-21
 */

import com.studentapp.studentinfo.StudentSteps;
import com.studentapp.utils.TestUtils;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.Matchers.hasValue;

public class StudentStepdefs {


    static String email = null;
    static int studentId;
    static ValidatableResponse response;

    @Steps
    StudentSteps studentSteps;

    @When("^User sends a GET request to list endpoint$")
    public void userSendsAGETRequestToListEndpoint() {
        response = studentSteps.getAllStudent();
    }

    @Then("^User must get back a valid status code 200$")
    public void userMustGetBackAValidStatusCode() {
        response.statusCode(200);
    }

    @When("^I create a new student by providing the information firstName \"([^\"]*)\" lastName \"([^\"]*)\" email \"([^\"]*)\" programme \"([^\"]*)\" courses \"([^\"]*)\"$")
    public void iCreateANewStudentByProvidingTheInformationFirstNameLastNameEmailProgrammeCourses(String firstName, String lastName, String _email, String programme, String courses) {
        List<String> courseList = new ArrayList<>();
        courseList.add(courses);
        email = TestUtils.getRandomValue() + _email;
        studentSteps.createStudent(firstName, lastName, email, programme, courseList);
        response.log().all().statusCode(200);
    }

    @Then("^I verify that the student with \"([^\"]*)\" is created$")
    public void iVerifyThatTheStudentWithIsCreated(String _email) {
        response.statusCode(200);
        HashMap<String, Object> studentInfo = studentSteps.getStudentInfoByEmail(email);
        Assert.assertThat(studentInfo, hasValue(email));
        studentId = (int) studentInfo.get("id");
        System.out.println("student Id is: " + studentId);

    }

    @When("^I update student firstName \"([^\"]*)\" lastName \"([^\"]*)\" email \"([^\"]*)\" programme \"([^\"]*)\" courses \"([^\"]*)\"$")
    public void iUpdateStudentFirstNameLastNameEmailProgrammeCourses(String firstName, String lastName, String _email, String programme, String courses) {
        List<String> courseList = new ArrayList<>();
        courseList.add(courses);
        ValidatableResponse response = studentSteps.updateStudent(studentId,firstName,lastName,email,programme,courseList);
        response.statusCode(200);

    }

    @Then("^I get student data by Id and verify \"([^\"]*)\" and \"([^\"]*)\" detail updated$")
    public void iGetStudentDataByIdAndVerifyAndDetailUpdated(String firstName, String courses)  {
        ValidatableResponse response = studentSteps.getStudentById(studentId);
        response.statusCode(200);
        Assert.assertEquals(firstName, "Bob" );
        Assert.assertEquals(courses, "Chemistry" );
    }

    @When("^I delete single student data$")
    public void iDeleteSingleStudentData() {
        studentSteps.deleteStudent(studentId).statusCode(204);
    }

    @Then("^I verify that same student data was deleted by getting data by studentId$")
    public void iVerifyThatSameStudentDataWasDeletedByGettingDataByStudentId() {
        studentSteps.getStudentById(studentId).statusCode(404);
    }
}
