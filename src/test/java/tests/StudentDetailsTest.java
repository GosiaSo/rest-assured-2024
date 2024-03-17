package tests;

import dataStore.StudentResponse;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import model.Student;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;


public class StudentDetailsTest {
    String url = "https://thetestingworldapi.com";
    String studentsDetails = "/api/studentsDetails";
    String requestBody = """
            {
                "id": 1,
                "first_name": "Angelina",
                "middle_name": "Jolie",
                "last_name": "Camila",
                "date_of_birth": "01/03/1977"
              }
            """;

    Student student;

    @Test
    public void shouldPostNewStudent() {
        given()
                .baseUri("https://thetestingworldapi.com")
                .basePath("/api/studentsDetails")
                .contentType(ContentType.JSON)
                .log()
                .all()
                .body(requestBody)
                .post()
                .then()
                .statusCode(201)
                .log().all();
    }

    @Test
    public void shouldGetNewStudent() {
        String studentId = "10093350";

        given()
                .baseUri("https://thetestingworldapi.com")
                .basePath("/api/studentsDetails")
                .pathParam("studentId", studentId)
                .contentType(ContentType.JSON)
                .log()
                .all()
                .get("/{studentId}")
                .then()
                .statusCode(200)
                .log()
                .all();
    }


    @Test
    public void shouldDeleteNewStudent() {
        String studentId = "10093350";

        given()
                .baseUri("https://thetestingworldapi.com")
                .basePath("/api/studentsDetails")
                .pathParam("studentId", studentId)
                .contentType(ContentType.JSON)
                .log()
                .all()
                .delete("/{studentId}")
                .then()
                .statusCode(200)
                .log()
                .all();
    }

    @Test
    public void shouldPostOtherNewStudent() {
//        student = new Student("Salma", "Hayek", "Dorota", "01/05/1980");
        student = Student.builder()
                .first_name("Salma")
                .last_name("Hayek")
                .middle_name("Dorota")
                .date_of_birth("01/05/1980")
                .build();
        given()
                .baseUri(url)
                .basePath(studentsDetails)
                .contentType(ContentType.JSON)
                .log()
                .all()
                .body(student)
                .post()
                .then()
                .statusCode(201)
                .log()
                .all();
    }

    @Test
    public void shouldGetNewStudentAndExtractAsTypeRef() {
        StudentResponse studResp = given()
                .baseUri(url)
                .basePath(studentsDetails)
                .pathParam("studentId", "10093444")
                .contentType(ContentType.JSON)
                .log()
                .all()
                .get("/{studentId}")
                .then()
                .statusCode(200)
                .log()
                .all()
                .extract()
                .as(new TypeRef<StudentResponse>() {});
//                .as(StudentResponse.class);

        System.out.println(studResp);
    }
}
