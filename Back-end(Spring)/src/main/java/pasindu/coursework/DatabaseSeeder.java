package pasindu.coursework;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Date;

public class DatabaseSeeder {
    //from this method we can reuse the connection.
    private static MongoCollection<Document> connection(){
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase db =  mongoClient.getDatabase("hetti");

        return db.getCollection("oopCW");
    }
    //retrieving all the records rom the collection
    public ArrayList<Document> getAllRecord() {

        FindIterable<Document> fi = connection().find();// getting all the records
        MongoCursor<Document> iterator = fi.iterator();//iterating through all the records one by one
        ArrayList<Document> allRecords = new ArrayList<>();
        while (iterator.hasNext()) {
            Document doc = iterator.next();//putting the record to Document type doc
            allRecords.add(doc); //now adding the record to the all records array
        }
        return allRecords; //returning allRecords array

    }
    //updating the database with the schedule in the relevant vehicle record
    public  void updateSchedule(int plateNumber, Date pickDate, Date dropoff,String firstName, String lastName, String contactNumber, String email, Date bokkedDateTime){
        //making a document type identifier to append the key plateNumber and value plateNumber
        Document identifier = new Document();
        identifier.append("plateNumber",plateNumber);
        //making the Document
        Document settingQuery = new Document();
        settingQuery.append("schedule",new BasicDBObject().append("firstName",firstName).append("lastName",lastName).append("contactNumber",contactNumber).append("email",email).append("pickup",pickDate).append("dropoff",dropoff).append("booked_date",bokkedDateTime));
        //combining settingQuery to make a complete query
        Document put = new Document();
        put.append("$addToSet",settingQuery);
        //finally updating with identifier and the prepared document
        connection().updateOne(identifier,put);
    }

}
