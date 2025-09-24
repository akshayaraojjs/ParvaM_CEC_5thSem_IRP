public class Employee {
    String name, department;
    float experience;
    int employee_id;

    // Constructor
    // Same name as that of the Class
    // Default Constructor
    public Employee() {
        name = "N/A";
        department = "N/A";
        experience = 0;
        employee_id = 0;
        System.out.println("Constructor has been Invoked");
    }

    // Parameterized Constructor
    public Employee(String name, String empDept, float empExp, int empID){
        // this keyword is used to give clarity that name in the left side refers to the data member/ attribute, the name on the right side refers to the data given by the user
        this.name = name;
        department = empDept;
        experience = empExp;
        employee_id = empID;
        System.out.println("Parameterized Constructor has initialized the values");
    }

    public void showEmpDetails(){
        System.out.println("Details of Employee " + employee_id + ":");
        System.out.println("Employee Name:" + name);
        System.out.println("Department:" + department);
        System.out.println("Experience:" + experience);
    }
}

// Parts of Function/Method
    // Function Declaration
    // Function Definition
    // Function Call

// public void addName();  // Declaration
// name and age are called as Parameters
// public void addName(String name, int age){
// name = "Akshay"; // Definition
// }

// addName("Akshay", 25);  // Function Call
// Akshay & 25 are called as Arguments