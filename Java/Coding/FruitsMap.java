// Stream API - Map, Filter, Reduce
import java.util.List;
import java.util.stream.Collectors;

public class FruitsMap {
    public static void main(String[] args) {
        List<String> fruits = List.of("apple", "banana", "cherry", "strawberry", "grapes");

        System.out.println("Fruits in Lowercase:" + fruits);

        // Passing the list of Fruits to the Stream API, it is going to map each and every fruit (item), then we will convert all items to Uppercase individually, next we will convert it back to List
        List<String> upperCase = fruits.stream()
                                       .map(String::toUpperCase)
                                       .collect(Collectors.toList());

        System.out.println("Fruits in Uppercase:" + upperCase);
    }
}