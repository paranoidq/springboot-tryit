//package me.test.springboottryit.logs;
//
//import com.mongodb.BasicDBObject;
//import com.mongodb.MongoClient;
//import com.mongodb.MongoClientURI;
//import com.mongodb.client.MongoCollection;
//import com.mongodb.client.MongoDatabase;
//import lombok.Data;
//import org.apache.logging.log4j.core.Filter;
//import org.apache.logging.log4j.core.Layout;
//import org.apache.logging.log4j.core.LogEvent;
//import org.apache.logging.log4j.core.appender.AbstractAppender;
//
//import java.io.Serializable;
//
///**
// * @author paranoidq
// * @since 1.0.0
// */
//@Data
//public class MongoAppender extends AbstractAppender {
//
//    private String connectionUrl;
//    private String databaseName;
//    private String collectionName;
//
//    private volatile MongoClient                    mongoClient;
//    private volatile MongoDatabase                  mongoDatabase;
//    private volatile MongoCollection<BasicDBObject> logsCollection;
//
//
//    protected MongoAppender(String name, Filter filter, Layout<? extends Serializable> layout) {
//        super(name, filter, layout);
//    }
//
//    @Override
//    public void append(LogEvent logEvent) {
//        if (mongoDatabase == null) {
//            MongoClientURI connectionString = new MongoClientURI(connectionUrl);
//            mongoClient = new MongoClient(connectionString);
//            mongoDatabase = mongoClient.getDatabase(databaseName);
//            logsCollection = mongoDatabase.getCollection(collectionName, BasicDBObject.class);
//            logsCollection.insertOne((BasicDBObject) logEvent.getMessage());
//        }
//    }
//
//}
