package com.to.eat.toeatAPI.repositories;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.DeleteTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.to.eat.toeatAPI.ToeatApiApplication;
import com.to.eat.toeatAPI.model.Food;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.web.WebAppConfiguration;
import java.util.List;

@SpringBootTest(classes = ToeatApiApplication.class)
@WebAppConfiguration
@ActiveProfiles("local")
@TestPropertySource(properties = {
        "amazon.dynamodb.endpoint=http://localhost:8000/",
        "amazon.aws.accesskey=test1",
        "amazon.aws.secretkey=test231" })
public class DynamodbIntegrationTest {
    @Autowired
    FoodRepository repository;

    private DynamoDBMapper dynamoDBMapper;

    @Autowired
    private AmazonDynamoDB amazonDynamoDB;

    private static final String EXPECTED_NAME = "Bread";

    @BeforeEach
    public void setup() {
        dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);

        CreateTableRequest tableRequest = dynamoDBMapper
                .generateCreateTableRequest(Food.class);
        tableRequest.setProvisionedThroughput(
                new ProvisionedThroughput(1L, 1L));
        amazonDynamoDB.createTable(tableRequest);

        dynamoDBMapper.batchDelete(repository.findAll());
    }

    @AfterEach
    public void teardown() {
        dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);

        DeleteTableRequest tableRequest = dynamoDBMapper
                .generateDeleteTableRequest(Food.class);
        amazonDynamoDB.deleteTable(tableRequest);
    }

    @Test
    public void givenItemWithExpectedCost_whenRunFindAll_thenItemIsFound() {
        Food productInfo = new Food("1", EXPECTED_NAME);
        repository.save(productInfo);
        List<Food> result = (List<Food>) repository.findAll();

        Assertions.assertEquals(1, result.stream().count());
        Assertions.assertEquals(EXPECTED_NAME, result.get(0).getName());
    }
}