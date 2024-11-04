// MARK: Imports
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

// MARK: Exceptions

/** 
* An exception class to be used when an invalid argument is passed into a constructor.
* 
* @author  Joshua Borck
*/
class InvalidArgumentException extends Exception {
	private static final long serialVersionUID = 1L;
}

/** 
* An exception class to be used when a vehicle already exits in the inventory.
* 
* @author  Joshua Borck
*/
class VehicleExistsException extends Exception {
	private static final long serialVersionUID = 1L;
}

/** 
* An exception class to be used when an invalid value is used for a boolean.
* 
* @author  Joshua Borck
*/
class InvalidBooleanValueException extends Exception {
	private static final long serialVersionUID = 1L;
}

/** 
* An exception class to be used when the year value provided is invalid.
* 
* @author  Joshua Borck
*/
class InvalidYearValueException extends Exception {
	private static final long serialVersionUID = 1L;
}

/** 
* An exception class to be used when an invalid price is entered.
* 
* @author  Joshua Borck
*/
class InvalidPriceValueException extends Exception {
	private static final long serialVersionUID = 1L;
}

/** 
* An exception class to be used when an invalid mileage value is entered.
* 
* @author  Joshua Borck
*/
class InvalidMileageValueException extends Exception {
	private static final long serialVersionUID = 1L;
}

/** 
* An exception class to be used when an invalid VIN value is used.
* 
* @author  Joshua Borck
*/
class InvalidVinValueException extends Exception {
	private static final long serialVersionUID = 1L;
}

/** 
* An exception class to be used when a vehicle matching the VIN supplied cannot be found.
* 
* @author  Joshua Borck
*/
class VinMismatchException extends Exception {
	private static final long serialVersionUID = 1L;
}

// MARK: Automobile Class

/** 
* An class to represent a vehicle found in the main inventory program.
* Rather than there being any getter methods except for the VIN, all adding, removing, or changing should be done using provided methods directly on the class.
* There are no setters either for the reason mentioned above.
* 
* @author  Joshua Borck
*/
class Automobile {
	// Static Properties
	private static String INVALID_CONSTANT = "Invalid";
	private static String MAKE_OUTPUT = "Enter the vehicle's make:";
	private static String MODEL_OUPUT = "Enter the vehicle's model:";
	private static String COLOR_OUTPUT = "Enter the vehicle's color:";
	private static String VIN_OUTPUT = "Enter the vehicle's VIN:";
	private static String MILEAGE_OUTPUT = "Enter the vehicle's mileage:";
	private static String YEAR_OUTPUT = "Enter the vehicle's year:";
	private static String PRICE_OUTPUT = "Enter the vehicle's price as a dollar amount like 123.33:";
	private static String AUTOMATIC_OUTPUT = "Enter \"true\" if the vehicle is automatic or \"false\" if not:";
	private static String ALL_WHEEL_DRIVE_OUTPUT = "Enter \"true\" if the vehicle is all wheel drive or \"false\" if not:";
	private static String ADD_VEHICLE_SUCCESS_OUPUT = "Successfully added the vehicle.";
	private static String ADD_VEHICLE_FAILURE_OUPUT = "Failed to add the vehicle. ";
	private static String REMOVE_VEHICLE_SUCCESS_OUPUT = "Successfully removed the vehicle.";
	private static String REMOVE_VEHICLE_FAILURE_OUPUT = "Failed to remove vehicle. ";
	private static String AUTOMATIC_LABEL = "Automatic: ";
	private static String ALL_WHEEL_DRIVE_LABEL = "All Wheel Drive: ";
	private static String YES_VALUE = "Yes";
	private static String NO_VALUE = "No";
	private static String VEHICLE_INFORMATION_LABEL = "Vehicle Information:";
	private static String MAKE_LABEL = "Make: ";
	private static String MODEL_LABEL = "Model: ";
	private static String COLOR_LABEL = "Color: ";
	private static String VIN_LABEL = "VIN: ";
	private static String MILEAGE_LABEL = "Mileage: ";
	private static String YEAR_LABEL = "Year: ";
	private static String PRICE_LABEL = "Price: $%.2f";
	private static String VEHICLE_INFO_FAILURE = "Failed to find vehicle to get information.";
	private static String BASE_UPDATE_OUTPUT = "Enter a new value for %s";
	private static String MAKE_UPDATE_LABEL = "the make";
	private static String MODEL_UPDATE_LABEL = "the model";
	private static String COLOR_UPDATE_LABEL = "the color";
	private static String VIN_UPDATE_LABEL = "the VIN";
	private static String MILEAGE_UPDATE_LABEL = "the mileage";
	private static String YEAR_UPDATE_LABEL = "the year";
	private static String PRICE_UPDATE_LABEL = "the price";
	private static String PRICE_VALUE_UPDATE_LABEL = "the price as a decimal amount like 2345.67";
	private static String AUTOMATIC_UPDATE_LABEL = "if the vehicle is automatic";
	private static String ALL_WHEEL_DRIVE_UPDATE_LABEL = "if the vehicle is all wheel drive";
	private static String VEHICLE_UPDATED_SUCCESS_OUTPUT = "Vehicle successfully updated.";
	private static String NO_UPDATE_LABEL = "Nothing was chosen to be updated for the vehicle.";
	private static String VEHICLE_UPDATED_FAILURE_OUTPUT = "Failed to update vehicle information. ";
	private static String TRUE_VALUE = "true";
	private static String FALSE_VALUE = "false";
	private static String INVALID_ARGUMENT_MESSAGE = "Incorrect value entered for vehicle.";
	private static String INVALID_BOOLEAN_MESSAGE = "Incorrect true or false value entered or vehicle";
	private static String VEHICLE_EXISTS_MESSAGE = "Vehicle already exists.";
	private static String INVALID_YEAR_MESSAGE = "Incorrect year value entered for vehicle.";
	private static String INVALID_PRICE_MESSAGE = "Incorrect price value entered for vehicle.";
	private static String INVALID_MILEAGE_MESSAGE = "Incorrect mileage value entered for vehicle.";
	private static String INVALID_VIN_MESSAGE = "Incorrect vin value entered for vehicle.";
	private static String VIN_MISMATCH_MESSAGE = "The entered vehicle VIN does not match.";
	private static String LIKE_TO_UPDATE_LABEL = "Would you like to update %s? Enter \"y\" for yes to update the value and \"n\" for no to not update that value. An invalid entry will result in no change for this value.";
	private static String Y_VALUE = "y";
	private static String N_VALUE = "n";
	
	
	// Class properties
	private String make;
	private String model;
	private String color;
	private String vin;
	private int mileage;
	private int year;
	private double price;
	private boolean isAutomatic;
	private boolean isAllWheelDrive;

	/** 
	* A default constructor that sets a default value for all class properties.
	*/
	public Automobile() {
		this.make = INVALID_CONSTANT;
		this.model = INVALID_CONSTANT;
		this.color = INVALID_CONSTANT;
		this.vin = INVALID_CONSTANT;
		this.mileage = -1;
		this.year = -1;
		this.price = -1.0;
		this.isAutomatic = false;
		this.isAllWheelDrive = false;
	}
	
	/** 
	* A parameterized constructor that sets all class properties to the parameters passed in.
	* 
	* @param make The make of the vehicle.
	* @param model The model of the vehicle.
	* @param color The current color of the vehicle.
	* @param vin The unique VIN of the vehicle.
	* @param mileage The current mileage of the vehicle.
	* @param year The year the vehicle was manufactured.
	* @param price The current price of the vehicle.
	* @param isAutomatic Set true if the vehicle has automatic transmission.
	* @param isAllWheelDrive Set to true if the vehicle is all wheel drive.
	* 
	* @return An instance of an Automobile with all properties set to what was passed in.
	* 
	* @throws InvalidArgumentException The VIN or year are invalid values.
	*/
	public Automobile(String make, String model, String color, String vin, int mileage, int year, double price, boolean isAutomatic, boolean isAllWheelDrive) throws InvalidArgumentException {
		this.make = make;
		this.model = model;
		this.color = color;
		this.price = price;
		this.mileage = mileage;
		this.isAutomatic = isAutomatic;
		this.isAllWheelDrive = isAllWheelDrive;
		
		// If the VIN is an empty string, throw an error.
		if (vin.strip().equals("")) {
			throw new InvalidArgumentException();
		} else {
			this.vin = vin.strip();
		}
		
		// If the year is an invalid value, throw an error.
		if ((year >= 1866) && (String.valueOf(year).length() == 4)) {
			this.year = year;
		} else {
			throw new InvalidArgumentException();
		}
	}
	
	/** 
	* A method that returns the vehicle's VIN.
	* 
	* @return The vehicle's VIN.
	*/
	public String getVin() {
		return this.vin;
	}
	
	/** 
	* A method that attempts to add a vehicle using user input.
	* 
	* @param scnr A Scanner that will get the user's input.
	* @param inventory An ArrayList that is the current inventory of vehicles.
	* 
	* @return A success or failure message.
	*/
	public String addVehicle(Scanner scnr, ArrayList<Automobile> inventory) {
		try {
			// Check that the vehicle is a default vehicle or throw an error.
			if (!isDefaultVehicle()) {
				throw new VehicleExistsException();
			}
			
			// Get the user's input for the new vehicle.
			System.out.println(MAKE_OUTPUT);
			String make = scnr.nextLine();
			System.out.println(MODEL_OUPUT);
			String model = scnr.nextLine();
			System.out.println(COLOR_OUTPUT);
			String color = scnr.nextLine();
			System.out.println(VIN_OUTPUT);
			String vin = scnr.nextLine();
			System.out.println(MILEAGE_OUTPUT);
			int mileage = scnr.nextInt();
			System.out.println(YEAR_OUTPUT);
			int year = scnr.nextInt();
			System.out.println(PRICE_OUTPUT);
			double price = scnr.nextDouble();
			scnr.nextLine();
			System.out.println(AUTOMATIC_OUTPUT);
			String isAutomatic = scnr.nextLine();
			System.out.println(ALL_WHEEL_DRIVE_OUTPUT);
			String isAllWheelDrive = scnr.nextLine();
			
			// Set the values of this class if they are valid.
			this.make = make;
			this.model = model;
			this.color = color;
			this.mileage = validateMileage(mileage);
			this.price = validatePrice(price);
			this.vin = validateVin(vin);
			this.year = validateYear(year);
			this.isAutomatic = validateBoolean(isAutomatic);
			this.isAllWheelDrive = validateBoolean(isAllWheelDrive);
			
			// If the vehicle exits, throw an error because we do not want duplicates.
			if (automobileExists(inventory)) {
				throw new VehicleExistsException();
			}
			
			// Add the vehicle to the inventory.
			inventory.add(this);
			
			// Return a success string.
			return ADD_VEHICLE_SUCCESS_OUPUT;
		} catch (Exception e) {
			// Return a failure string.
			return ADD_VEHICLE_FAILURE_OUPUT + errorString(e);
		}
	}
	
	/** 
	* A method that attempts to remove a vehicle if it is in the inventory.
	* 
	* @param inventory An ArrayList that is the current inventory of vehicles.
	* 
	* @return A success or failure message.
	*/
	public String removeVehicle(ArrayList<Automobile> inventory) {
		try {
			// If this is this is not a default vehicle, set all values to default or throw and error.
			if (!isDefaultVehicle()) {
				this.make = INVALID_CONSTANT;
				this.model = INVALID_CONSTANT;
				this.color = INVALID_CONSTANT;
				this.vin = INVALID_CONSTANT;
				this.mileage = -1;
				this.year = -1;
				this.price = -1.0;
				this.isAutomatic = false;
				this.isAllWheelDrive = false;
				inventory.remove(this);
				
				// Return a success string.
				return REMOVE_VEHICLE_SUCCESS_OUPUT;
			} else {
				throw new VinMismatchException();
			}
		} catch (Exception e) {
			// Return a failure message.
			return REMOVE_VEHICLE_FAILURE_OUPUT + errorString(e);
		}
	}
	
	/** 
	* A method gets the vehicle's current information.
	* 
	* 
	* @return An array of strings where each is a specific property of the class or if there is an error, a failure message in the array.
	*/
	public String[] getVehicleInfo() {
		try {
			// If the vehicle is the default one, throw an error because it should not be empty.
			if (isDefaultVehicle()) {
				throw new VinMismatchException();
			}
			
			// Initialize variables.
			String automaticString = AUTOMATIC_LABEL;
			String allWheelDriveString = ALL_WHEEL_DRIVE_LABEL;
			String[] values = new String[10];
			
			// Set an appropriate value for if the vehicle is automatic.
			if (isAutomatic) {
				automaticString = automaticString + YES_VALUE;
			} else {
				automaticString = automaticString + NO_VALUE;
			}
			
			// Set an appropriate value for if the vehicle is automatic.
			if (isAllWheelDrive) {
				allWheelDriveString = allWheelDriveString + YES_VALUE;
			} else {
				allWheelDriveString = allWheelDriveString + NO_VALUE;
			}
			
			// Add all other vehicle properties to the array.
			values[0] = VEHICLE_INFORMATION_LABEL;
			values[1] = MAKE_LABEL + this.make;
			values[2] = MODEL_LABEL + this.model;
			values[3] = COLOR_LABEL + this.color;
			values[4] = VIN_LABEL + this.vin;
			values[5] = MILEAGE_LABEL + String.valueOf(this.mileage);
			values[6] = YEAR_LABEL + String.valueOf(this.year);
			values[7] = String.format(PRICE_LABEL, this.price);
			values[8] = automaticString;
			values[9] = allWheelDriveString;
			
			// Return the array of values.
			return values;
		} catch (Exception e) {
			// Return an failure string in the array instead.
			String[] invalidValue = { VEHICLE_INFO_FAILURE + errorString(e) };
			return invalidValue;
		}
	}
	
	/** 
	* A method updates the vehicle's current values.
	* 
	* @param scnr A Scanner to get the user's input.
	* 
	* @return A success or failure message.
	*/
	public String updateVehicleInfo(Scanner scnr) {
		String baseString = BASE_UPDATE_OUTPUT;
	
		try {
			
			if (!isDefaultVehicle()) {
				boolean didUpdate = false;
				if (checkIfShouldUpdate(MAKE_UPDATE_LABEL, scnr)) {
					System.out.println(String.format(baseString, MAKE_UPDATE_LABEL));
					this.make = scnr.nextLine();
					didUpdate = true;
				}
				
				if (checkIfShouldUpdate(MODEL_UPDATE_LABEL, scnr)) {
					System.out.println(String.format(baseString, MODEL_UPDATE_LABEL));
					this.model = scnr.nextLine();
					didUpdate = true;
				}
				
				
				if (checkIfShouldUpdate(COLOR_UPDATE_LABEL, scnr)) {
					System.out.println(String.format(baseString, COLOR_UPDATE_LABEL));
					this.color = scnr.nextLine();
					didUpdate = true;
				}
				
				if (checkIfShouldUpdate(VIN_UPDATE_LABEL, scnr)) {
					System.out.println(String.format(baseString, VIN_UPDATE_LABEL));
					this.vin = validateVin(scnr.nextLine());
					didUpdate = true;
				}
				
				if (checkIfShouldUpdate(MILEAGE_UPDATE_LABEL, scnr)) {
					System.out.println(String.format(baseString, MILEAGE_UPDATE_LABEL));
					this.mileage = validateMileage(scnr.nextInt());
					didUpdate = true;
				}
				
				if (checkIfShouldUpdate(YEAR_UPDATE_LABEL, scnr)) {
					System.out.println(String.format(baseString, YEAR_UPDATE_LABEL));
					this.year = validateYear(scnr.nextInt());
					didUpdate = true;
				}
				
				if (checkIfShouldUpdate(PRICE_UPDATE_LABEL, scnr)) {
					System.out.println(String.format(baseString, PRICE_VALUE_UPDATE_LABEL));
					this.price = validatePrice(scnr.nextDouble());
					didUpdate = true;
				}
				
				if (checkIfShouldUpdate(AUTOMATIC_UPDATE_LABEL, scnr)) {
					System.out.println(String.format(baseString, AUTOMATIC_UPDATE_LABEL));
					this.isAutomatic = validateBoolean(scnr.nextLine());
					didUpdate = true;
				}
				
				if (checkIfShouldUpdate(ALL_WHEEL_DRIVE_UPDATE_LABEL, scnr)) {
					System.out.println(String.format(baseString, ALL_WHEEL_DRIVE_UPDATE_LABEL));
					this.isAllWheelDrive = validateBoolean(scnr.nextLine());
					didUpdate = true;
				}
				
				if (didUpdate) {
					// Return a success string.
					return VEHICLE_UPDATED_SUCCESS_OUTPUT;
				} else {
					return NO_UPDATE_LABEL;
				}
				
			} else {
				throw new VinMismatchException();
			}
		} catch (Exception e) {
			return VEHICLE_UPDATED_FAILURE_OUTPUT + errorString(e);
		}
	}
	
	/** 
	* A private method that checks if the vehicle exists in the inventory.
	* 
	* @param inventory The current inventory of vehicles.
	* 
	* @return Boolean true if the vehicle exists else false.
	*/
	private boolean automobileExists(ArrayList<Automobile> inventory) {
		for (int i = 0; i < inventory.size(); i++) {
			Automobile current = inventory.get(i);
			if (current.vin.equals(this.vin)) {
				return true;
			}
		}
		return false;
	}
	
	/** 
	* A private method that checks if the year value entered is valid.
	* 
	* @param year The year value entered.
	* 
	* @return int of the year if valid.
	* 
	* @throws InvalidYearValueException if the year is not valid.
	*/
	private int validateYear(int year) throws InvalidYearValueException {
		int yearLength = String.valueOf(year).length();
		if ((year >= 1866) && (yearLength == 4)) {
			return year;
		}
		
		throw new InvalidYearValueException();
	}
	
	/** 
	* A private method that checks the validity of the boolean string.
	* 
	* @param value The boolean string to check for validity.
	* 
	* @return Boolean true or false if a valid string.
	* 
	* @throws InvalidBooleanValueException The boolean string value is invalid.
	*/
	private boolean validateBoolean(String value) throws InvalidBooleanValueException {
		value.strip();
		if (value.equals(TRUE_VALUE)) {
			return true;
		} else if (value.equals(FALSE_VALUE)) {
			return false;
		} else {
			throw new InvalidBooleanValueException();
		}
	}
	
	/** 
	* A private method that checks if the price value entered is valid.
	* 
	* @param price The price value entered.
	* 
	* @return double of the year if valid.
	* 
	* @throws InvalidPriceValueException if the price is not valid.
	*/
	private double validatePrice(double price) throws InvalidPriceValueException {
		if (price >= 0.0) {
			return price;
		}
		
		throw new InvalidPriceValueException();
	}
	
	/** 
	* A private method that checks if the mileage value entered is valid.
	* 
	* @param mileage The mileage value entered.
	* 
	* @return int of the mileage if valid.
	* 
	* @throws InvalidMileageValueException if the mileage is not valid.
	*/
	private int validateMileage(int mileage) throws InvalidMileageValueException {
		if (mileage >= 0) {
			return mileage;
		}
		
		throw new InvalidMileageValueException();
	}
	
	/** 
	* A private method that checks if the VIN value entered is valid.
	* 
	* @param vin The VIN value entered.
	* 
	* @return String of the VIN if valid.
	* 
	* @throws InvalidVinValueException if the VIN is not valid.
	*/
	private String validateVin(String vin) throws InvalidVinValueException {
		vin.strip();
		if (vin.equals("") || vin.equals(INVALID_CONSTANT)) {
			throw new InvalidVinValueException();
		}
		
		return vin;
	}
	
	/** 
	* A private method that returns specific error strings for specific errors.
	* 
	* @param e A The exception encountered.
	* 
	* @return An error specific message.
	*/
	private String errorString(Exception e) {
		if (e instanceof InvalidArgumentException) {
			return INVALID_ARGUMENT_MESSAGE;
		} else if (e instanceof InvalidBooleanValueException) {
			return INVALID_BOOLEAN_MESSAGE;
		} else if (e instanceof VehicleExistsException) {
			return VEHICLE_EXISTS_MESSAGE;
		} else if (e instanceof InvalidYearValueException) {
			return INVALID_YEAR_MESSAGE;
		} else if (e instanceof InvalidPriceValueException) {
			return INVALID_PRICE_MESSAGE;
		} else if (e instanceof InvalidMileageValueException) {
			return INVALID_MILEAGE_MESSAGE;
		} else if (e instanceof InvalidVinValueException) {
			return INVALID_VIN_MESSAGE;
		} else if (e instanceof VinMismatchException) {
			return VIN_MISMATCH_MESSAGE;
		}
		
		return "";
	}
	
	/** 
	* A private method that checks if all values are default.
	* 
	* 
	* @return Boolean true if all values are default.
	*/
	private boolean isDefaultVehicle() {
		if (this.make.equals(INVALID_CONSTANT) 
				&& this.model.equals(INVALID_CONSTANT) 
				&& this.color.equals(INVALID_CONSTANT) 
				&& this.vin.equals(INVALID_CONSTANT) 
				&& this.mileage == -1
				&& this.year == -1
				&& this.price == -1.0
				&& this.isAutomatic == false
				&& this.isAllWheelDrive == false) {
			return true;
		}
		return false;
	}
	
	/** 
	* A private method that checks with the user if we should update the passed in item.
	* 
	* @param itemToUpdate A name value of the item to update.
	* 
	* @return Boolean true if the user would like to update.
	*/
	private boolean checkIfShouldUpdate(String itemToUpdate, Scanner scnr) {
		String updateString = LIKE_TO_UPDATE_LABEL;
		System.out.println(String.format(updateString, itemToUpdate));
		String shouldUpdate = scnr.next();
		scnr.nextLine();
		
		if (shouldUpdate.equals(Y_VALUE)) {
			return true;
		} else if (shouldUpdate.equals(N_VALUE)) {
			return false;
		}
		
		return false;
	}
}

// MARK: AutomobileInventory Class

/** 
* An class to be the inventory system manager and manage the automobile instances.
* 
* @author  Joshua Borck
*/
public class AutomobileInventory {
	// Static properties
	private static String DEALERSHIP_LABEL = "Enter the dealership name:";
	private static String INVENTORY_SUCCESS_LABEL = "Successfully set up inventory.";
	private static String INVENTORY_FAILURE_LABEL = "Failed to create inventory. Exiting the program.";
	private static String MENU_LABEL = "MENU";
	private static String ADD_LABEL = "a - Add vehicle to inventory";
	private static String REMOVE_LABEL = "d - Remove vehicle from inventory";
	private static String UPDATE_LABEL = "c - Update vehicle in inventory";
	private static String SINGLE_LABEL = "i - Output a single vehicle's values";
	private static String OUTPUT_LABEL = "o - Output the full inventory";
	private static String SAVE_LABEL = "s - Save the inventory to a file";
	private static String QUIT_LABEL = "q - Quit";
	private static String CHOOSE_LABEL = "Choose an option:";
	private static String EXIT_LABEL = "Exiting...";
	private static String INVALID_INPUT_LABEL = "Invalid input. Try again.";
	private static String ENTER_VIN_LABEL = "Enter the VIN of the vehicle:";
	private static String DIVIDER_LABEL ="------------------------------------------------------------\n\n";
	private static String FULL_INVENTORY_LABEL = "'s Full Inventory\n";
	private static String INVENTORY_EMTPY_LABEL = "Inventory is empty.";
	private static String VEHICLE_NUMBER_LABEL = "Vehicle #";
	private static String FAILED_TO_CREATE_FILE_MESSAGE = "Failed to create file to save inventory. ";
	private static String SAVING_INVENTORY_MESSAGE = "Saving inventory to file...";
	private static String ENTER_FILE_NAME_LABEL = "Enter the name of the file with no extensions like \"Autos\":";
	private static String TEXT_EXTENSION_VALUE = ".txt";
	private static String FILE_SUCCESS_SAVE_MESSAGE = "File successfull saved under name: ";
	private static String FILE_SUCCESS_SAVE_MESSAGE_ENDING = ". This file can be found in the root directory of this program.";
	private static String FAILED_TO_FIND_CREATE_MESSAGE = "Failed find or create file.";
	private static String NO_WRITE_PERMISSIONS_MESSAGE = "No write permissions.";
	private static String FAILED_TO_CLOSE_FILE_MESSAGE = " Failed to close file.";
	private static String CREATING_VEHICLE_MESSAGE = "Creating initial test vehicle...";
	private static String OUTPUTTING_INITIAL_VEHICLE_MESSAGE = "Outputting initial vehicle information...";
	private static String INITIAL_SETUP_COMPLETE_MESSAGE = "Initial set up complete.";
	private static String TEST_MAKE = "Toyota";
	private static String TEST_MODEL = "Tacoma";
	private static String TEST_COLOR = "Black";
	private static String TEST_VIN = "FKJHS-KDSHD-KDH";
	
	// Class properties
	private ArrayList<Automobile> inventory = new ArrayList<Automobile>();
	private String dealershipName;
	
	/** 
	* A default constructor that sets a default value for all class properties.
	* 
	* @return An instance of an AutomobileInventory with all default properties
	*/
	public AutomobileInventory() {
		dealershipName = "invalid";
	}
	
	
	/** 
	* A parameterized constructor that sets all class properties to the parameters passed in.
	* 
	* @param dealershipName The name of the dealership.
	* 
	* @return An instance of an AutomobileInventory with all properties set to what was passed in.
	* 
	* @throws InvalidArgumentException The VIN or year are invalid values.
	*/
	public AutomobileInventory(String dealershipName) throws InvalidArgumentException {
		dealershipName.strip();
		if (dealershipName.equals("")) {
			throw new InvalidArgumentException();
		} else {
			this.dealershipName = dealershipName;
		}
	}
	

	public static void main(String[] args) {
		// Initialize variables.
		AutomobileInventory inventory;
		Scanner scnr = new Scanner(System.in);
		
		try {
			// Get the dealership name from the user.
			System.out.println(DEALERSHIP_LABEL);
			String name = scnr.nextLine();
			System.out.println();
			
			// Create the new inventory and inform the user.
			inventory = new AutomobileInventory(name);
			System.out.println(INVENTORY_SUCCESS_LABEL);
			System.out.println();
			
			// Create initial test vehicle per requirements.
			System.out.println(CREATING_VEHICLE_MESSAGE);
			System.out.println();
			Automobile initial = new Automobile(TEST_MAKE, TEST_MODEL, TEST_COLOR, TEST_VIN, 1600, 2020, 35000.00, true, true);
			System.out.println(OUTPUTTING_INITIAL_VEHICLE_MESSAGE);
			System.out.println();
			
			// Output the test vehicle's information per requirements.
			System.out.println(inventory.getVehicleInfoOutput(initial.getVehicleInfo()));
			System.out.println(INITIAL_SETUP_COMPLETE_MESSAGE);
			System.out.println();
			
			// While the user does not select to quit, prompt them to enter more input.
			boolean didQuit = false;
			while (!didQuit) {
				didQuit = inventory.checkIfNextInputIsQuit(scnr);
			}
		} catch (Exception e) {
			// Inform the user that there was an issue creating the inventory and exit.
			System.out.println(INVENTORY_FAILURE_LABEL);
			scnr.close();
			System.exit(0);
		}

		
		scnr.close();
	}
	
	
	/** 
	* A private method that outputs the menu and handles the user's next input.
	* 
	* @param scnr A Scanner to get value from user.
	* 
	* @return Boolean true if the user wants to quit.
	* 
	*/
	public boolean checkIfNextInputIsQuit(Scanner scnr) {
		// Output the menu.
		boolean hasValidInput = false;
		System.out.println(MENU_LABEL);
		System.out.println(ADD_LABEL);
		System.out.println(REMOVE_LABEL);
		System.out.println(UPDATE_LABEL);
		System.out.println(SINGLE_LABEL);
		System.out.println(OUTPUT_LABEL);
		System.out.println(SAVE_LABEL);
		System.out.println(QUIT_LABEL);
		System.out.println();
		
		// Handle the users input.
		while (!hasValidInput) {
			System.out.println(CHOOSE_LABEL);
			String option = scnr.nextLine();
			String output;
			
			switch (option) {
			case "q":
				hasValidInput = true;
				System.out.println(EXIT_LABEL);
				
				// Check if the user wants to save before quitting.
				output = addInventoryToFile(scnr);
				System.out.println();
				System.out.println(output);
				return true;
			case "a":
				// Add a vehicle.
				Automobile newCar = new Automobile();
				output = newCar.addVehicle(scnr, inventory);
				hasValidInput = true;
				break;
			case "d":
				// Remove a vehicle.
				output = findMatchingVehicle(scnr).removeVehicle(inventory);
				hasValidInput = true;
				break;
			case "c":
				// Update a vehicle.
				output = findMatchingVehicle(scnr).updateVehicleInfo(scnr);
				hasValidInput = true;
				break;
			case "i":
				// Output a single vehicle's information.
				output = getVehicleInfoOutput(findMatchingVehicle(scnr).getVehicleInfo());
				hasValidInput = true;
				break;
			case "o":
				// Output the full inventory.
				output = buildFullInventoryString();
				hasValidInput = true;
				break;
			case "s":
				// Save the inventory to a text file.
				output = addInventoryToFile(scnr);
				hasValidInput = true;
				break;
			default:
				// Inform the user that the input was invalid.
				output = INVALID_INPUT_LABEL;
			}
			
			// Output the user's action's result.
			System.out.println();
			System.out.println(output);
		}
		
		// Return false since the user did not quit.
		System.out.println();
		return false;
	}
	
	
	/** 
	* A private method that finds a matching vehicle in the inventory.
	* 
	* @param scnr A Scanner to get value from user.
	* 
	* @return Automobile if it is found in the inventory of a default one if not.
	* 
	*/
	private Automobile findMatchingVehicle(Scanner scnr) {
		System.out.println(ENTER_VIN_LABEL);
		String vin = scnr.nextLine();
		Automobile match = new Automobile();
		
		for (int i = 0; i < inventory.size(); i++) {
			Automobile current = inventory.get(i);
			
			if (current.getVin().equals(vin)) {
				match = current;
			}
		}
		
		return match;
	}
	
	
	/** 
	* A private method that creates an output of the vehicles information.
	* 
	* @param values An array of vehicle information string values to output.
	* 
	* @return String that has all the vehicle's information formatted.
	* 
	*/
	private String getVehicleInfoOutput(String[] values) {
		String output = "";
		for (int i = 0; i < values.length; i++) {
			output = output + (values[i] + "\n");
		}
		
		return output;
	}
	
	/** 
	* A private method that creates an output of the entire inventory's information.
	* 
	* 
	* @return String that has all the inventory's vehicle information formatted.
	* 
	*/
	private String buildFullInventoryString() {
		String output = dealershipName + FULL_INVENTORY_LABEL;
		output = output + DIVIDER_LABEL;
		
		if (inventory.size() == 0) {
			output = output + INVENTORY_EMTPY_LABEL;
		}
	
		for (int i = 0; i < inventory.size(); i++) {
			Automobile current = inventory.get(i);
			output = output + (VEHICLE_NUMBER_LABEL + (i + 1) + "\n");
			output = output + (getVehicleInfoOutput(current.getVehicleInfo()));
		}
		
		return output;
	}
	
	
	/** 
	* A private method to add the full inventory to a text file.
	* 
	* @param scnr A Scanner to get value from user.
	* 
	* @return A success or failure message.
	* 
	*/
	private String addInventoryToFile(Scanner scnr) {
		String output = "";
		FileOutputStream outputStream = null;
		PrintWriter fileWriter = null;
		
		System.out.println();
		System.out.println(SAVING_INVENTORY_MESSAGE);
		System.out.println();
		
		try {
			// Try to open/create the file and input the inventory to it.
			System.out.println(ENTER_FILE_NAME_LABEL);
			String fileName = scnr.nextLine();
			outputStream = new FileOutputStream(fileName + TEXT_EXTENSION_VALUE);
			fileWriter = new PrintWriter(outputStream);
			String inventoryOutput = buildFullInventoryString();
			fileWriter.println(inventoryOutput);
			output = FILE_SUCCESS_SAVE_MESSAGE + fileName + FILE_SUCCESS_SAVE_MESSAGE_ENDING;
		
		// Handle all the errors and update output.
		} catch (FileNotFoundException e) {
			output = FAILED_TO_CREATE_FILE_MESSAGE + FAILED_TO_FIND_CREATE_MESSAGE;
		} catch (SecurityException e) {
			output = FAILED_TO_CREATE_FILE_MESSAGE + NO_WRITE_PERMISSIONS_MESSAGE;
		} catch (Exception e) {
			output = FAILED_TO_CREATE_FILE_MESSAGE;
		} finally {
			fileWriter.close();
			try {
				outputStream.close();
			} catch (IOException e) {
				output = output + FAILED_TO_CLOSE_FILE_MESSAGE;
			}
		}
		
		// Return output.
		return output;
	}
}
