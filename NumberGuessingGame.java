import java.util.*;

class Calculate {
    int myguess(int maxAttempt, int target, Scanner sc, int range, String PlayerName) {
        int attempt = 0;
        int guess;
        boolean won = false; 

        while (attempt < maxAttempt) {

            try {
                attempt++;
                System.out.println("\nAttempt " + attempt + " of " + maxAttempt+" Enter a number:");
                guess = sc.nextInt();
            } 

            catch(InputMismatchException e) {
                System.out.println("\nInvalid input! Please enter a number.");
                sc.next();
                attempt--; 
                continue;
            }

            if(guess < 1 || guess > range) {
                System.out.println("\nOut of range! Enter a number between 1 and " + range + ".");
                attempt--;       
                continue;        
            }

            if (guess == target) {
                System.out.println("\nYour guess is correct!!");
                System.out.println("You got it in " + attempt + " attempts!");
                won = true;
                break;
            } else if (guess < target) {
                System.out.println("Too Low! Try again!!");
            } else {
                System.out.println("Too High! Try again!!");
            }
        }
        
        if (won) {
            System.out.println("---------------------------------------------");
            System.out.println("Congratulations! "+ PlayerName +" You won!!" );
            System.out.println("---------------------------------------------");
            return (maxAttempt - attempt + 1) * 10;
        } else {
            System.out.println("---------------------------------------------");
            System.out.println("Game Over! The number was " + target);
            System.out.println("Better luck next time " + PlayerName + "!");
            System.out.println("---------------------------------------------");
            return 0;
        }
    }
}

public class NumberGuessingGame {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        System.out.println("=============================================");
        System.out.println("             NUMBER GUESSING GAME            ");
        System.out.println("=============================================");
        System.out.print("Enter your name: ");
        String PlayerName = sc.next();
        System.out.println("Welcome, " + PlayerName + "!");

        int range=0, maxAttempts=0, score=0;
        int bestScore = 0;
        int choice;
        String playAgain;
        int roundsPlayed = 0;
        ArrayList<Integer> scores = new ArrayList<>();

        Calculate obj = new Calculate();

        do {
            System.out.println("\nChoose Difficulty:");
            System.out.println("1. Easy   -> 1 to 50,  10 attempts");
            System.out.println("2. Medium -> 1 to 100, 7 attempts");
            System.out.println("3. Hard   -> 1 to 200, 5 attempts");
            System.out.print("\nEnter 1, 2 or 3: ");

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    range = 50;
                    maxAttempts = 10;
                    System.out.println("Easy mode! Guess a number between 1 and 50.");
                    break;

                case 2:
                    range = 100;
                    maxAttempts = 7;
                    System.out.println("Medium mode! Guess a number between 1 and 100.");
                    break;

                case 3:
                    range = 200;
                    maxAttempts = 5;
                    System.out.println("Hard mode! Guess a number between 1 and 200.");
                    break;

                default:
                    range = 100;
                    maxAttempts = 7;
                    System.out.println("Invalid choice. Defaulting to Medium mode.");
            }

            int target = rand.nextInt(range) + 1;

            score = obj.myguess(maxAttempts, target, sc, range, PlayerName);

            roundsPlayed++;
            scores.add(score);

            if (score > bestScore) {
                bestScore = score;
            }

            System.out.println("\nYour Score: " + score);
            System.out.print("Play again? (yes/no): ");
            playAgain = sc.next();

        } while(playAgain.equalsIgnoreCase("yes"));

        int total = 0;
        for(int s : scores) {
            total += s;
        }
        int average = total / roundsPlayed;

        System.out.println("\n=============================================");
        System.out.println("               GAME SUMMARY                  ");
        System.out.println("=============================================");
        System.out.println("Player Name    : " + PlayerName);
        System.out.println("Rounds Played  : " + roundsPlayed);
        System.out.println("Best Score     : " + bestScore);
        System.out.println("Average Score  : " + average);
        
        System.out.println("\n=============================================");
        System.out.println("              Round-Wise Score               ");
        System.out.println("=============================================");
        for(int i=0; i < scores.size() ; i++){
            System.out.println("Round "+(i+1)+" : "+scores.get(i));
        }
        System.out.println("\nThanks for playing!");
    
        sc.close();
    }
}