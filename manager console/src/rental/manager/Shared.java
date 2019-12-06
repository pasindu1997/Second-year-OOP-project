package rental.manager;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.util.Scanner;

public  class Shared {
    public static int getNumberInput(Scanner scanner, int r1, int r2){
        int number;
        try {
            number = Integer.parseInt(scanner.next());
            if(number >= r1 && number <= r2){
                return number;
            } else {
                System.out.println("Number is out of the range");
                return getNumberInput(scanner, r1, r2);
            }
        } catch (NumberFormatException e){
            System.out.println("You should enter a number");
            return getNumberInput(scanner, r1, r2);
        }
    }
    public static int getNumber(Scanner scanner){
        int number;
        try{
            number = Integer.parseInt(scanner.next());
            return number;
        }catch (NumberFormatException e){
            System.out.println("you should enter a number");
            return getNumber(scanner);
        }

    }
    public static boolean getBoolean(Scanner scanner){
        String res = scanner.next();
        if ("y".equals(res)){
            return true;
        }else if ("n".equals(res)){
            return false;
        }else{
            System.out.println("enter y or n");
            return getBoolean(scanner);
        }
    }
    public static MongoCollection<org.bson.Document> connection(){
        MongoClient mongclient = new MongoClient("localhost", 27017);
        MongoDatabase db =  mongclient.getDatabase("hetti");
        System.out.println("connected");

        MongoCollection<org.bson.Document> myDatabaseRecords = db.getCollection("oopCW");
        return myDatabaseRecords;
    }
}
