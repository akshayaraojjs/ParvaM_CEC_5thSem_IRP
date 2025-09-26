public class ConditionalStatements {
    public static void main(String[] args) {
        int num = 27;

        // If Statement
        if (num % 2 == 0) {
            System.out.println(" " + num + " is an Even Number");
        }

        // If-Else Statement
        if (num % 2 == 0) {
            System.out.println(" " + num + " is an Even Number");
        } else {
            System.out.println(" " + num + " is an Odd Number");
        }

        // Nested If-Else Statement
        if (num % 2 == 0) {            
            if (num % 5 == 0){
                System.out.println(" " + num + " is an Even Number and it is also Divisible by 5");
            } else {
                System.out.println(" " + num + " is an Even Number but not Divisible by 5");
            }
        } else {
            System.out.println(" " + num + " is an Odd Number");
        }

        // If-Else Ladder
        if (num % 2 == 0) {            
            System.out.println(" " + num + " is divisible by 2");
        } else if (num % 5 == 0) {
            System.out.println(" " + num + " is divisible by 5");
        } else {
            System.out.println(" " + num + " is not divisible by both 2 & 5");
        }

    }
}