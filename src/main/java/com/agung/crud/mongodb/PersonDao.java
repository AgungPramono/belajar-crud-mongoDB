/*
 * Silahkan digunakan dengan bebas / dimodifikasi
 * Dengan tetap mencantumkan nama @author dan Referensi / Source
 * Terima Kasih atas Kerjasamanya.
 */
package com.agung.crud.mongodb;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Agung Pramono
 */
public class PersonDao {

    Mongo mongo = null;
    private DBCollection collection = null;

    public Mongo connect() {

        try {
            mongo = new Mongo();
            System.out.println("Koneksi Sukses\n");
        } catch (UnknownHostException ex) {
            Logger.getLogger(PersonDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MongoException ex) {
            ex.printStackTrace();
        }

        return mongo;
    }

    public void disconnect() {
        mongo.close();
    }

    public void selectDatabase() {
        Mongo mg = connect();
        DB db = mg.getDB("Kontak");

        //create collection / table
        collection = db.getCollection("kontak_mhs");
    }

    public void insert(Person person) {
        selectDatabase();
        BasicDBObject object = new BasicDBObject();
        object.put("npm", person.getNpm());
        object.put("nama", person.getNama());
        object.put("alamat", person.getAlamat());
        object.put("phone", person.getPhone());
        try {
            collection.insert(object);
            System.out.println("Insert Succsess\n");
        } catch (MongoException ex) {
            ex.printStackTrace();
        } finally {
            getAll();
            disconnect();
        }
    }

    public void update(Person person) {
        selectDatabase();

        BasicDBObject kriteria = new BasicDBObject();
        kriteria.put("npm", person.getNpm());

        BasicDBObject newData = new BasicDBObject();
        newData.put("npm", person.getNpm());
        newData.put("nama", person.getNama());
        newData.put("alamat", person.getAlamat());
        newData.put("phone", person.getPhone());

        try {
            collection.update(kriteria, newData);
            System.out.println("Update Succsesss\n");
        } catch (MongoException ex) {
            ex.printStackTrace();
        } finally {
            getAll();
            disconnect();
        }
    }

    public void delete(Person person) {
        selectDatabase();

        BasicDBObject kriteria = new BasicDBObject();
        kriteria.put("npm", person.getNpm());

        try {
            collection.remove(kriteria);
            System.out.println("Delete Succsess\n");
        } catch (MongoException ex) {
            ex.printStackTrace();
        } finally {
            getAll();
            disconnect();
        }
    }

    public void getAll() {
        selectDatabase();
       
        DBCursor cursor = collection.find();
        int i = 1;
        try {
            while (cursor.hasNext()) {
                System.out.println("data ke- " + i);
                DBObject object = cursor.next();
                System.out.println(object);
                i++;
            }
        } finally {
            cursor.close();
        }

        disconnect();
    }
}
