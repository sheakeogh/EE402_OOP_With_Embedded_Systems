public class Student {
    private String name, address;
    private int idNum, phone;
    Boolean gender;

    Student(String nm, int id, int ph, Boolean gen, String add) {
        this.name = nm;
        this.idNum = id;
        this.phone = ph;
        this.gender = gen;
        this.address = add;
    }

    public void display() {
        System.out.println("Name: " + name);
        System.out.println("ID Number: " + idNum);
        System.out.println("Phone: " + phone);
        System.out.println("Gender: " + (gender ? "Male" : "Female"));
        System.out.println("Address: " + address);
    }

    public String getName() {
        return name;
    }

    public int getIdnum() {
        return idNum;
    }

    public int getPhone() {
        return phone;
    }

    public String getGender() {
        return ((gender)?"Male":"Female");
    }

    public String getAddress() {
        return address;
    }
};
