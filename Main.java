import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class Monopoly {
    public static void main(String[] args) {
        // Initialize game board with properties and their prices
        ArrayList<String> properties = new ArrayList<String>();
        Collections.addAll(properties, "Mediterranean Avenue", "Baltic Avenue", "Oriental Avenue", "Vermont Avenue", "Connecticut Avenue",
                "St. Charles Place", "States Avenue", "Virginia Avenue", "St. James Place", "Tennessee Avenue",
                "New York Avenue", "Kentucky Avenue", "Indiana Avenue", "Illinois Avenue", "Atlantic Avenue",
                "Ventnor Avenue", "Marvin Gardens", "Pacific Avenue", "North Carolina Avenue", "Pennsylvania Avenue",
                "Park Place", "Boardwalk");
        ArrayList<Integer> prices = new ArrayList<Integer>();
        Collections.addAll(prices, 60, 60, 100, 100, 120, 140, 140, 160, 180, 180, 200, 220, 220, 240, 260, 260, 280, 300, 300, 320, 350, 400);

        // Initialize players with their initial balance and position
        ArrayList<String> players = new ArrayList<String>();
        ArrayList<Integer> balances = new ArrayList<Integer>();
        ArrayList<Integer> positions = new ArrayList<Integer>();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of players: ");
        int numPlayers = scanner.nextInt();

        for (int i = 1; i <= numPlayers; i++) {
            players.add("Player " + i);
            balances.add(1500);
            positions.add(0);
        }

        int currentPlayer = 0;

        while (true) {
            System.out.println(players.get(currentPlayer) + "'s turn");
            System.out.println("Balance: " + balances.get(currentPlayer));

            System.out.print("Roll dice? (y/n): ");
            String input = scanner.next();

            if (input.equals("y")) {
                int dice1 = (int) (Math.random() * 6) + 1;
                int dice2 = (int) (Math.random() * 6) + 1;
                int roll = dice1 + dice2;

                System.out.println("Rolled " + dice1 + " and " + dice2);

                positions.set(currentPlayer, (positions.get(currentPlayer) + roll) % properties.size());
                int newPosition = positions.get(currentPlayer);
                String property = properties.get(newPosition);
                int price = prices.get(newPosition);

                System.out.println("Landed on " + property + " (" + price + ")");

                if (balances.get(currentPlayer) >= price) {
                    System.out.print("Buy " + property + "? (y/n): ");
                    input = scanner.next();

                    if (input.equals("y")) {
                        balances.set(currentPlayer, balances.get(currentPlayer) - price);
                        System.out.println("Bought " + property);
                    }
                } else {
                    System.out.println("Insufficient funds to buy " + property);
                }
            }

            currentPlayer = (currentPlayer + 1) % players.size();
        }
    }
}
