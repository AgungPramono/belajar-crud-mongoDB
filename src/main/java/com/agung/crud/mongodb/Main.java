/*
 * Silahkan digunakan dengan bebas / dimodifikasi
 * Dengan tetap mencantumkan nama @author dan Referensi / Source
 * Terima Kasih atas Kerjasamanya.
 */
package com.agung.crud.mongodb;

/**
 *
 * @author Agung Pramono
 */
public class Main {
    public static void main(String[]args)
    {
        Person person = new Person();
        PersonDao dao = new PersonDao();
        person.setNpm("0343");
        person.setNama("Hari");
        person.setAlamat("Magetan");
        person.setPhone("08783378667");
        dao.insert(person);
        //dao.update(person);
        //dao.delete(person);
        //dao.getAll();
    } 
}
