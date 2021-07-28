package spring.ru.otus.library;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import com.mongodb.client.result.UpdateResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
public class MongoDbTest {
    private static final String COLLECTION_NAME = "books";

    @Autowired
    private MongoTemplate mongoTemplate;

    @DisplayName(" должен добавлять объект в коллекцию")
    @Test
    void shouldAddObjectToCollection() {
        DBObject objectToSave = BasicDBObjectBuilder.start()
                .add("key", "value")
                .get();
        mongoTemplate.save(objectToSave, COLLECTION_NAME);
        assertThat(mongoTemplate.findAll(DBObject.class, COLLECTION_NAME)).extracting("key")
                .containsOnly("value");
    }

    @DisplayName(" должен изменять объект в коллекции")
    @Test
    void shouldUpdateObjectToCollection() {
        DBObject objectToSave = BasicDBObjectBuilder.start()
                .add("key", "value")
                .get();
        mongoTemplate.save(objectToSave, COLLECTION_NAME);
        Query query = new Query();
        query.addCriteria(new Criteria("key").is("value"));
        Update update = new Update();
        update.set("key", "newValue");
        final UpdateResult updateResult = mongoTemplate.updateFirst(query, update, DBObject.class, COLLECTION_NAME);
        assertThat(updateResult.getModifiedCount()).isEqualTo(1);
    }


    @DisplayName(" должен удалять объект в коллекции")
    @Test
    void shouldDeleteObjectToCollection() {
        DBObject objectToSave = BasicDBObjectBuilder.start()
                .add("key", "value")
                .get();
        mongoTemplate.save(objectToSave, COLLECTION_NAME);
        mongoTemplate.remove(objectToSave, COLLECTION_NAME);
        assertThat(mongoTemplate.findAll(DBObject.class, COLLECTION_NAME)).isEmpty();
    }
}
