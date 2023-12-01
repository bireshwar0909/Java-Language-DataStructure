package DataStructure.D_HashTables.A_HashTable_ArrayImplementation;

public class SimpleHashtable {

    //when called we are making an array. A different array which takes key value pair.

    private Employee[] hashtable;

    public SimpleHashtable() {
        hashtable = new Employee[10];
    }

    public void put(String key, Employee employee) {
        int hashedKey = hashKey(key);
        if (hashtable[hashedKey] != null) {
            System.out.println("Sorry, there's already an employee at position " + hashedKey);
        }
        else {
            hashtable[hashedKey] = employee;
        }
    }


    private int hashKey(String key) {
        return key.length() % hashtable.length;
    }


    public Employee get(String key) {
        int hashedKey = hashKey(key);
        return hashtable[hashedKey];
    }


    public void printHashtable() {
        for (int i = 0; i < hashtable.length; i++) {
            System.out.println(hashtable[i]);
        }
    }

}
