import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/*===============================
 * Z-INVENTORY
 *===============================*/
/**
 * This is a single class file intended to mimic a program for a zoo
 * with enclosure numbers. All animals have details specified to them such as their age, species,
 * name, and enclosure number.
 *
 * Inspired by Shubaruuu's Z-Inventory GitHub repo,
 * refactored into reusable components which still abides to project constraints
 * (No advanced data structures like Objects, etc.)
 *
 * @author Dekxisosta
 * created on 2025/08/31
 *
 * <p><b>Dependencies:</b></p>
 * <ul>
 *  <li>{@link java.io.BufferedReader} reads text from a character-input stream</li>
 *  <li>{@link java.io.InputStreamReader}</li>
 *  <li>{@link java.io.IOException} signals that an I/O exception occurs in the program</li>
 * </ul>
 */
public class App {
    /*===============================
     * INSTANCE FIELDS
     *===============================*/
    /** The fixed size of the parallel data structure */
    private final int FIXED_SIZE = 50;

    /** Reads inputs from the console to be used in the program */
    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    /** Contains animal names */
    private final String[] animalNames = new String[FIXED_SIZE];

    /** Contains animal specie types */
    private final String[] animalSpecies = new String[FIXED_SIZE];

    /** Contains animal ages */
    private final int[] animalAges = new int[FIXED_SIZE];

    /** Contains animal enclosure numbers */
    private final int[] animalEnclosureNums = new int[FIXED_SIZE];

    /** Used as a pointer value for accessing the parallel data structure */
    private int size = 0;

    /** Minimum enclosure number */
    private final int minEnclosureNum = 0;

    /** Maximum enclosure number */
    private final int maxEnclosureNum = 4;

    /** Limit of an animal's age */
    private final int ageLimit = 100;

    /** Determines the continuity of the program */
    private boolean isContinueProgram = true;

    /*===============================
     * PROGRAM ENTRY-POINT
     *===============================*/
    /**
     * Entry point of the program, also catches severe level exceptions
     * <p><b>Dependencies:</b></p>
     * <ul>
     *  <li>{@link #run()} contains the z-inventory's program flow</li>
     * </ul>
     * @param args Command-line args (not used)
     * @see #run()
     */
    public static void main(String[] args) {
        try{
            new App().run();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    /** Prevents instantiation for only one single class file is used */
    private App(){}

    /*===============================
     * PROGRAM FLOW
     *===============================*/
    /** 
     * Runs the program
     * <p><b>Dependencies:</b></p>
     * <ul>
     *  <li>{@link #actionMenu(int)} performs an action based on user choice</li>
     * </ul>
     * @see #actionMenu(int) 
     */
    private void run(){
        while(isContinueProgram){
            showMenuOptions();
            showEnterPrompt("option");
            actionMenu(readIntWithRange(0, 5));
        }
        printf("Thank you for using the program! - Dekxisosta");
    }

    /*===============================
     * ACTION MENU
     *===============================*/
    /**
     * Adds an animal based on {@link #size}
     * <p><b>Dependencies:</b></p>
     * <ul>
     *  <li>{@link #addAnimal} – adds an animal on the parallel data structure</li>
     *  <li>{@link #viewAllAnimalDetails} - views all animals with their corresponding details </li>
     *  <li>{@link #searchAnimalsBasedOnName} - searches animal/s with the name inputted by the user </li>
     *  <li>{@link #countAnimalsBasedOnSpecie} - counts animals of the same specie and displays it </li>
     *  <li>{@link #viewAnimalsEnclosure} - views animals on the desired enclosure </li>
     *  <li>{@link #promptContinueProgram} - prompts for the continuity of the program </li>
     * </ul>
     * @param choice the index of the action the user wants to perform
     * @see #addAnimal
     * @see #viewAllAnimalDetails
     * @see #searchAnimalsBasedOnName
     * @see #countAnimalsBasedOnSpecie
     * @see #viewAnimalsEnclosure
     * @see #promptContinueProgram
     */
    private void actionMenu(int choice){
        switch (choice) {
            case 1:
                showMessage(ConsoleTag.SYSTEM, "Preparing to add a new animal... ");
                addAnimal();
                break;
            case 2:
                showMessage(ConsoleTag.SYSTEM, "Preparing to show all animal details... ");
                viewAllAnimalDetails();
                break;
            case 3:
                showMessage(ConsoleTag.SYSTEM, "Preparing to show animals with specified name... ");
                searchAnimalsBasedOnName();
                break;
            case 4:
                showMessage(ConsoleTag.SYSTEM, "Preparing to show animal count of a specie... ");
                countAnimalsBasedOnSpecie();
                break;
            case 5:
                showMessage(ConsoleTag.SYSTEM, "Preparing to show animals in an enclosure... ");
                viewAnimalsEnclosure();
                break;
            case 0:
                promptContinueProgram();
                break;
        }
    }
    /*===============================
     * PROJECT SPECIFIC METHODS
     *===============================*/
    /**
     * Adds an animal based on {@link #size}
     * <p><b>Dependencies:</b></p>
     * <ul>
     *  <li>{@code animalNames} – contains the names of the animals</li>
     *  <li>{@code animalSpecies} - contains the species of the animals </li>
     *  <li>{@code animalAges} - contains the ages of the animals </li>
     *  <li>{@code animalEnclosureNums} - contains the enclosure numbers of the animals </li>
     * </ul>
     * @see #animalNames
     * @see #animalSpecies
     * @see #animalAges
     * @see #animalEnclosureNums
     */
    private void addAnimal(){
        showEnterPrompt("animal name");
        animalNames[size] = readString();

        showEnterPrompt("animal age");
        animalAges[size] = readIntWithRange(0, ageLimit);

        showEnterPrompt("animal specie");
        animalSpecies[size] = readString();

        showEnterPrompt("animal enclosure num");
        animalEnclosureNums[size] = readIntWithRange(minEnclosureNum, maxEnclosureNum);

        showMessage(ConsoleTag.SYSTEM, animalNames[size] + " added successfully!");

        size++;
    }

    /**
     * Show all animals present in the zoo
     * @see #showAnimal(int)
     */
    private void viewAllAnimalDetails(){
        for(int i = 0; i < size; i++)
            showAnimal(i);
    }

    /**
     * Search animals based on name
     * <p><b>Dependencies:</b></p>
     * <ul>
     *  <li>{@link #animalNames} contains the names of animals</li>
     * </ul>
     * @see #animalNames
     */
    private void searchAnimalsBasedOnName(){
        int counter = 0;
        showEnterPrompt("animal name");
        String animalToSearch = readString();

        for(int i=0; i < size; i++)
            if(animalToSearch.equalsIgnoreCase(animalNames[i])){
                showAnimal(i);
                counter++;
            }

        //shows an appropriate message based on the number of animals with the same animal name
        if(counter > 1){
            showMessage(ConsoleTag.INFO, "a total of " + counter + " animals with the name "
                    + animalToSearch + " was found!");
        }else if(counter == 1){
            showMessage(ConsoleTag.INFO, animalToSearch + " was found");
        }else{
            showMessage(ConsoleTag.INFO, animalToSearch + " was not found");
        }
    }

    /**
     * Counts animals by specie
     * <p><b>Dependencies:</b></p>
     * <ul>
     *  <li>{@link #animalSpecies} contains the species of animals</li>
     * </ul>
     * @see #animalSpecies
     */
    private void countAnimalsBasedOnSpecie(){
        int counter = 0;
        showEnterPrompt("animal name");
        String specieToCount = readString();

        for(int i=0; i < size; i++)
            if(specieToCount.equalsIgnoreCase(animalSpecies[i]))
                counter++;

        if(counter > 1)
            showMessage(ConsoleTag.INFO, "a total of " + counter + " animals with the specie type "
                    + specieToCount + " was found!");
    }

    /**
     * Views animals in a set enclosure
     * <p><b>Dependencies:</b></p>
     * <ul>
     *  <li>{@link #animalEnclosureNums} contains the enclosure numbers of animals</li>
     * </ul>
     * @see #animalEnclosureNums
     */
    private void viewAnimalsEnclosure() {
        boolean isThereAnimalsInEnclosure = false;
        showEnterPrompt("animal enclosure num");
        int enclosureNum = readIntWithRange(minEnclosureNum, maxEnclosureNum);

        for (int i = 0; i < size; i++)
            if (enclosureNum==animalEnclosureNums[i]){
                showAnimal(i);
                if(!isThereAnimalsInEnclosure)
                    isThereAnimalsInEnclosure = true;
            }
        if(!isThereAnimalsInEnclosure){
            showMessage(ConsoleTag.INFO, "No animals inside the enclosure yet");
        }
    }

    /**
     * Prompts user whether to continue program or not
     * <p><b>Dependencies:</b></p>
     * <ul>
     *  <li>{@link #isContinueProgram} determines the program's continuity</li>
     * </ul>
     * @see #isContinueProgram
     */
    private void promptContinueProgram(){
        printf("%n%s", "Do you wish to terminate the program? ");
        showYNOptions();
        showEnterPrompt("option");
        isContinueProgram = !readBoolean();
    }

    /*===============================
     * CONSOLE RENDERING METHODS
     *===============================*/
    /** Shows a set of options based on the actions in the system */
    private void showMenuOptions(){
        printf("%n%s", "Welcome to Z-Inventory System!");
        printf("%n%s", "------------------------------");
        printf("""
            %n[1] Add a new animal
            [2] View all animals
            [3] Search animal by name
            [4] Count animals by species
            [5] View animals by enclosure
            [0] Exit
            """);
        printf("%n");
    }
    /** Shows a yn option menu to prompt for a true/false input */
    private void showYNOptions(){
        printf("%n%s", "------------------------------");
        printf("""
            %n[1] YES
            [0] NO
            """);
    }

    /** Shows a simple enter prompt for when error messages appear */
    private void showEnterPrompt(){
        showEnterPrompt("");
    }

    /**
     * Shows a simple enter prompt with the input type needed
     * for console prompt clarity
     * @param inputType the type of input which the program needs
     */
    private void showEnterPrompt(String inputType){
        printf("Enter %s: ", inputType);
    }

    /**
     * Shows an animal based on a selected index
     * <p><b>Dependencies:</b></p>
     * <ul>
     *  <li>{@code animalNames} – contains the names of the animals</li>
     *  <li>{@code animalSpecies} - contains the species of the animals </li>
     *  <li>{@code animalAges} - contains the ages of the animals </li>
     *  <li>{@code animalEnclosureNums} - contains the enclosure numbers of the animals </li>
     * </ul>
     * @param index the allocated index of the animal's details
     * @see #animalNames
     * @see #animalSpecies
     * @see #animalAges
     * @see #animalEnclosureNums
     */
    private void showAnimal(int index){
        printf("""
                    Name: %-24s | Specie: %-16s | Age: %-3d | Enclosure: %-1d
                    """,
                animalNames[index], animalSpecies[index], animalAges[index], animalEnclosureNums[index]);
    }
    /*===============================
     * INPUT VALIDATOR METHODS
     *===============================*/

    /**
     * Gets a console user input from {@link #readString()} then compares it to set values that
     * correspond to either true or false. Retries until a valid input is provided.
     * @return {@code true} if the input is 1, true, yes, y, or t;
     *         {@code false} if the input is 0, false, no, n, or f
     */
    private boolean readBoolean(){
        while(true){
            String value = readString();
            if (value.equalsIgnoreCase("y")
                    || value.equalsIgnoreCase("true")
                    || value.equals("1")
                    || value.equalsIgnoreCase("yes")
                    || value.equalsIgnoreCase("t")) return true;
            if (value.equalsIgnoreCase("n")
                    || value.equalsIgnoreCase("false")
                    || value.equals("0")
                    || value.equalsIgnoreCase("f")
                    || value.equalsIgnoreCase("no")) return false;
            showMessage(ConsoleTag.ERROR, "Invalid input.Please try again!");
        }
    }
    /**
     * Gets an integer output from {@link #readInt()} then compares it to an inclusive min-max range,
     * if invalid, catches and retries until a valid input is gotten.
     * @param min inclusive minimum range
     * @param max inclusive maximum range
     * @return an integer within the set range of the inclusive min-max
     */
    private int readIntWithRange(int min, int max) {
        while(true){
            int value = readInt();
            if (value >= min && value <= max)
                return value;
            showMessage(ConsoleTag.ERROR, "Invalid input. Must be between " + min + " and " + max);
            showEnterPrompt();
        }
    }

    /**
     * Gets a string from {@link #readString()} then tries to parse it into an integer,
     * if invalid, catches and retries until a valid input is gotten.
     * @return a parsed integer from console user input.
     */
    private int readInt(){
        while(true){
            try{
                return Integer.parseInt(readString());
            }catch(NumberFormatException e){
                showMessage(ConsoleTag.ERROR, "Invalid input. Please try again.");
                showEnterPrompt();
            }
        }
    }

    /**
     * Reads a string, used in the program to get animal names, species, etc.
     * @return trimmed console user input.
     */
    private String readString(){
        while (true) {
            try {
                String input = reader.readLine();
                if (input != null && !input.isBlank()) //checks for null and blank inputs
                    return input.trim();
                showMessage(ConsoleTag.ERROR, "Input cannot be empty. Please try again.");
                showEnterPrompt();
            } catch (IOException e) {
                showMessage(ConsoleTag.ERROR, "Unable to process input. Please try again.");
                showEnterPrompt();
            }
        }
    }
    /*===============================
     * UTILITY METHODS
     *===============================*/
    /**
     * Type-safe console tags for labelled messages.
     * Includes getters and setters.
     */
    private enum ConsoleTag{
        INFO("[INFO]"),
        SYSTEM("[SYSTEM]"),
        ERROR("[ERROR]");

        private final String label;

        ConsoleTag(String label){this.label = label;}

        String label(){return label;}
    }

    /**
     * Provides a clear distinction between message types when rendered in console.
     * @param tag gets its label for easier message-type identification
     * @param message shows an appropriate message based on the program's output
     */
    private void showMessage(ConsoleTag tag, String message){
        printf("%n%s %s%n", tag.label(), message);
    }

    /**
     * Shortened out printf method
     * @param format shows how {@code args} must be formatted
     * @param args the arguments used in {@code format}
     */
    private void printf(String format, Object... args){
        System.out.printf(format, args);
    }
}
