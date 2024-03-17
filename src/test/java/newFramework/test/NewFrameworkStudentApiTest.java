package newFramework.test;

import newFramework.base.NewFrameworkTestBase;
import newFramework.client.ApiClient;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NewFrameworkStudentApiTest extends NewFrameworkTestBase {

    private final String baseUri = "https://thetestingworldapi.com";
    private ApiClient api;

    @BeforeEach
    public void setUpClient(){
        api = createApiClient(baseUri);
    }

    //10093344 //10093375 //10093384

    @Test
    public void shouldGetStudent(){
        assertThat( api.getRealStudent("10093344")
                .execute()
                .getStatusCode()).isEqualTo(HttpStatus.OK_200);
    }
}
