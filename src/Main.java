import java.util.LinkedList;
import java.util.Scanner;

public class Main{

    record Place(String town, int distance){

        @Override
        public String toString() {
            return String.format("%s (%d)", town, distance);
        }
    }


    public static void main(String[] args) {

        LinkedList<Place> list = new LinkedList<>();

        addPlace(list, new Place("Adelaide", 1374));
        addPlace(list, new Place("Brisbane", 917));
        addPlace(list, new Place("Perth", 3923));
        addPlace(list, new Place("Alice Springs", 2771));
        addPlace(list, new Place("Darwin", 3972));
        addPlace(list, new Place("Melbourne", 877));
        list.addFirst(new Place("Sydney", 0));
        System.out.println(list);

        Scanner scanner = new Scanner(System.in);
        var iterator = list.listIterator();
        boolean quit = false;
        boolean forward = true;

        printMenu();

        while(!quit){
            if(!iterator.hasPrevious()){
                System.out.println("Originating : " + iterator.next());
                forward = true;
            }
            if(!iterator.hasNext()){
                System.out.println("Final : " + iterator.next());
                forward = false;
            }
            System.out.print("Enter Value: ");
            String menuItem = scanner.nextLine().toUpperCase().substring(0,1);

            switch (menuItem){
                case "F":
                    System.out.println("User wants to go forward");
                    if(!forward){
                        forward = true;
                        if(iterator.hasNext()){
                            iterator.next();
                        }
                    }
                    if(iterator.hasNext()){
                        System.out.println(iterator.next());
                    }
                    break;
                case "B":
                    System.out.println("User wants to go backwards");
                    if(forward){
                        forward = false;
                        if(iterator.hasPrevious()){
                            iterator.previous();
                        }
                    }
                    if(iterator.hasPrevious()){
                        System.out.println(iterator.previous());
                    }
                    break;
                case "M":
                    printMenu();
                    break;
                case "L":
                    System.out.println(list);
                    break;
                default:
                    quit = true;
                    break;
            }
        }

    }

    private static void addPlace(LinkedList<Place> list, Place place){

        if(list.contains(place)){
            System.out.println("Found duplicate: " + place);
            return;
        }

        for(Place p : list){
            if(p.town().equalsIgnoreCase(place.town())){
                System.out.println("Found duplicate: " + place);
                return;
            }
        }

        int matchedIndex = 0;
        for(var listPlace: list){
            if(place.distance() < listPlace.distance()){
                list.add(matchedIndex, place);
                return;
            }
            matchedIndex++;
        }
        list.add(place);
    }

    private static void printMenu(){
        System.out.println("""
                Available actions (select word or letter)
                (F)orwards
                (B)ackwards
                (L)ist Places
                (M)enu
                (Q)uit
                """);
    }
}
