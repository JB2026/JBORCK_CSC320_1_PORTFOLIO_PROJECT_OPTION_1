// Imports
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

class InvalidArgumentException extends Exception {
	private static final long serialVersionUID = 1L;
}

class VehicleExistsException extends Exception {
	private static final long serialVersionUID = 1L;
}

class InvalidBooleanValueException extends Exception {
	private static final long serialVersionUID = 1L;
}

class InvalidYearValueException extends Exception {
	private static final long serialVersionUID = 1L;
}

class InvalidPriceValueException extends Exception {
	private static final long serialVersionUID = 1L;
}

class InvalidMileageValueException extends Exception {
	private static final long serialVersionUID = 1L;
}

class InvalidVinValueException extends Exception {
	private static final long serialVersionUID = 1L;
}

class VinMismatchException extends Exception {
	private static final long serialVersionUID = 1L;
}

class Automobile {
	// Static Properties
	private static String INVALID_CONSTANT = "Invalid";
	
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

	// Default Constructor
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
	
	// Parameterized Constructor that throws if the date or vin are invalid
	public Automobile(String make, String model, String color, String vin, int mileage, int year, double price, boolean isAutomatic, boolean isAllWheelDrive) throws InvalidArgumentException {
		this.make = make;
		this.model = model;
		this.color = color;
		this.price = price;
		this.mileage = mileage;
		this.isAutomatic = isAutomatic;
		this.isAllWheelDrive = isAllWheelDrive;
		
		
		if (vin.strip().equals("")) {
			throw new InvalidArgumentException();
		} else {
			this.vin = vin.strip();
		}
		
		if ((year >= 1866) && (String.valueOf(year).length() == 4)) {
			this.year = year;
		} else {
			throw new InvalidArgumentException();
		}
	}
	
	// Getter the vehicle's VIN.
	public String getVin() {
		return this.vin;
	}
	
	// Add vehicle
	public String addVehicle(Scanner scnr, ArrayList<Automobile> inventory) {
		try {
			if (!isDefaultVehicle()) {
				throw new VehicleExistsException();
			}
			
			System.out.println("Enter the vehicle's make:");
			String make = scnr.nextLine();
			System.out.println("Enter the vehicle's model:");
			String model = scnr.nextLine();
			System.out.println("Enter the vehicle's color:");
			String color = scnr.nextLine();
			System.out.println("Enter the vehicle's VIN:");
			String vin = scnr.nextLine();
			System.out.println("Enter the vehicle's mileage:");
			int mileage = scnr.nextInt();
			System.out.println("Enter the vehicle's year:");
			int year = scnr.nextInt();
			System.out.println("Enter the vehicle's price as a dollar amount like 123.33:");
			double price = scnr.nextDouble();
			scnr.nextLine();
			System.out.println("Enter \"true\" if the vehicle is automatic or \"false\" if not:");
			String isAutomatic = scnr.nextLine();
			System.out.println("Enter \"true\" if the vehicle is all wheel drive or \"false\" if not:");
			String isAllWheelDrive = scnr.nextLine();
			
			this.make = make;
			this.model = model;
			this.color = color;
			this.mileage = validateMileage(mileage);
			this.price = validatePrice(price);
			this.vin = validateVin(vin);
			this.year = validateYear(year);
			this.isAutomatic = validateBoolean(isAutomatic);
			this.isAllWheelDrive = validateBoolean(isAllWheelDrive);
			
			if (automobileExists(inventory)) {
				throw new VehicleExistsException();
			}
			
			inventory.add(this);
			return "Successfully added the vehicle.";
		} catch (Exception e) {
			return "Failed to add the vehicle. " + errorString(e);
		}
	}
	
	public String removeVehicle(ArrayList<Automobile> inventory) {
		try {
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
				
				return "Successfully removed the vehicle.";
			} else {
				throw new VinMismatchException();
			}
		} catch (Exception e) {
			return "Failed to remove vehicle. " + errorString(e);
		}
	}
	
	public String[] getVehicleInfo() {
		try {
			
			if (isDefaultVehicle()) {
				throw new VinMismatchException();
			}
			
			String automaticString = "Automatic: ";
			String allWheelDriveString = "All Wheel Drive: ";
			String[] values = new String[10];
			
			if (isAutomatic) {
				automaticString = automaticString + "Yes";
			} else {
				automaticString = automaticString + "No";
			}
			
			if (isAllWheelDrive) {
				allWheelDriveString = allWheelDriveString + "Yes";
			} else {
				allWheelDriveString = allWheelDriveString + "No";
			}
			
			values[0] = "Vehicle Information:";
			values[1] = "Make: " + this.make;
			values[2] = "Model: " + this.model;
			values[3] = "Color: " + this.color;
			values[4] = "VIN: " + this.vin;
			values[5] = "Mileage: " + String.valueOf(this.mileage);
			values[6] = "Year: " + String.valueOf(this.year);
			values[7] = String.format("Price: $%.2f", this.price);
			values[8] = automaticString;
			values[9] = allWheelDriveString;
			
			return values;
		} catch (Exception e) {
			String[] invalidValue = { "Failed to find vehicle to get information." + errorString(e) };
			return invalidValue;
		}
	}
	
	public String updateVehicleInfo(Scanner scnr) {
		String baseString = "Enter a new value for %s";
	
		try {
			
			if (!isDefaultVehicle()) {
				boolean didUpdate = false;
				if (checkIfShouldUpdate("the make", scnr)) {
					System.out.println(String.format(baseString, "the make"));
					this.make = scnr.nextLine();
					didUpdate = true;
				}
				
				if (checkIfShouldUpdate("the model", scnr)) {
					System.out.println(String.format(baseString, "the model"));
					this.model = scnr.nextLine();
					didUpdate = true;
				}
				
				
				if (checkIfShouldUpdate("the color", scnr)) {
					System.out.println(String.format(baseString, "the color"));
					this.color = scnr.nextLine();
					didUpdate = true;
				}
				
				if (checkIfShouldUpdate("the VIN", scnr)) {
					System.out.println(String.format(baseString, "the VIN"));
					this.vin = validateVin(scnr.nextLine());
					didUpdate = true;
				}
				
				if (checkIfShouldUpdate("the mileage", scnr)) {
					System.out.println(String.format(baseString, "the mileage"));
					this.mileage = validateMileage(scnr.nextInt());
					didUpdate = true;
				}
				
				if (checkIfShouldUpdate("the year", scnr)) {
					System.out.println(String.format(baseString, "the year"));
					this.year = validateYear(scnr.nextInt());
					didUpdate = true;
				}
				
				if (checkIfShouldUpdate("the price", scnr)) {
					System.out.println(String.format(baseString, "the price as a decimal amount like 2345.67"));
					this.price = validatePrice(scnr.nextDouble());
					didUpdate = true;
				}
				
				if (checkIfShouldUpdate("if the vehicle is automatic", scnr)) {
					System.out.println(String.format(baseString, "if the vehicle is automatic"));
					this.isAutomatic = validateBoolean(scnr.nextLine());
					didUpdate = true;
				}
				
				if (checkIfShouldUpdate("if the vehicle is all wheel drive", scnr)) {
					System.out.println(String.format(baseString, "if the vehicle is all wheel drive"));
					this.isAllWheelDrive = validateBoolean(scnr.nextLine());
					didUpdate = true;
				}
				
				if (didUpdate) {
					return "Vehicle successfully updated.";
				} else {
					return "Nothing was chosen to be updated for the vehicle.";
				}
				
			} else {
				throw new VinMismatchException();
			}
		} catch (Exception e) {
			return "Failed to update vehicle information. " + errorString(e);
		}
	}
	
	private boolean automobileExists(ArrayList<Automobile> inventory) {
		for (int i = 0; i < inventory.size(); i++) {
			Automobile current = inventory.get(i);
			if (current.vin.equals(this.vin)) {
				return true;
			}
		}
		return false;
	}
	
	private int validateYear(int year) throws InvalidYearValueException {
		int yearLength = String.valueOf(year).length();
		if ((year >= 1866) && (yearLength == 4)) {
			return year;
		}
		
		throw new InvalidYearValueException();
	}
	
	private boolean validateBoolean(String value) throws InvalidBooleanValueException {
		value.strip();
		if (value.equals("true")) {
			return true;
		} else if (value.equals("false")) {
			return false;
		} else {
			throw new InvalidBooleanValueException();
		}
	}
	
	private double validatePrice(double price) throws InvalidPriceValueException {
		if (price >= 0.0) {
			return price;
		}
		
		throw new InvalidPriceValueException();
	}
	
	private int validateMileage(int mileage) throws InvalidMileageValueException {
		if (mileage >= 0) {
			return mileage;
		}
		
		throw new InvalidMileageValueException();
	}
	
	private String validateVin(String vin) throws InvalidVinValueException {
		vin.strip();
		if (vin.equals("") || vin.equals(INVALID_CONSTANT)) {
			throw new InvalidVinValueException();
		}
		
		return vin;
	}
	
	private String errorString(Exception e) {
		if (e instanceof InvalidArgumentException) {
			return "Incorrect value entered for vehicle.";
		} else if (e instanceof InvalidBooleanValueException) {
			return "Incorrect true or false value entered or vehicle";
		} else if (e instanceof VehicleExistsException) {
			return "Vehicle already exists.";
		} else if (e instanceof InvalidYearValueException) {
			return "Incorrect year value entered for vehicle.";
		} else if (e instanceof InvalidPriceValueException) {
			return "Incorrect price value entered for vehicle.";
		} else if (e instanceof InvalidMileageValueException) {
			return "Incorrect mileage value entered for vehicle.";
		} else if (e instanceof InvalidVinValueException) {
			return "Incorrect vin value entered for vehicle.";
		} else if (e instanceof VinMismatchException) {
			return "The entered vehicle VIN does not match.";
		}
		
		return "";
	}
	
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
	
	private boolean checkIfShouldUpdate(String itemToUpdate, Scanner scnr) {
		String updateString = "Would you like to update %s? Enter \"y\" for yes to update the value and \"n\" for no to not update that value. An invalid entry will result in no change for this value.";
		System.out.println(String.format(updateString, itemToUpdate));
		String shouldUpdate = scnr.next();
		scnr.nextLine();
		
		if (shouldUpdate.equals("y")) {
			return true;
		} else if (shouldUpdate.equals("n")) {
			return false;
		}
		
		return false;
	}
}

public class AutomobileInventory {
	private ArrayList<Automobile> inventory = new ArrayList<Automobile>();
	String dealershipName;
	
	public AutomobileInventory() {
		dealershipName = "invalid";
	}
	
	public AutomobileInventory(String dealershipName) throws InvalidArgumentException {
		dealershipName.strip();
		if (dealershipName.equals("")) {
			throw new InvalidArgumentException();
		} else {
			this.dealershipName = dealershipName;
		}
	}

	public static void main(String[] args) {
		AutomobileInventory inventory;
		Scanner scnr = new Scanner(System.in);
		System.out.println("Enter the dealership name:");
		String name = scnr.nextLine();
		System.out.println();
		
		try {
			inventory = new AutomobileInventory(name);
			System.out.println("Successfully set up inventory.");
			System.out.println();
			
			System.out.println("Creating initial test vehicle...");
			System.out.println();
			Automobile initial = new Automobile("Toyota", "Tacoma", "Black", "FKJHS-KDSHD-KDH", 1600, 2020, 35000.00, true, true);
			System.out.println("Outputting initial vehicle information...");
			System.out.println();
			System.out.println(inventory.getVehicleInfoOutput(initial.getVehicleInfo()));
			System.out.println("Initial set up complete.");
			System.out.println();
			
			boolean didQuit = false;
			while (!didQuit) {
				didQuit = inventory.checkIfNextInputIsQuit(scnr);
			}
		} catch (Exception e) {
			System.out.println("Failed to create inventory. Exiting the program.");
			scnr.close();
			System.exit(0);
		}

		
		scnr.close();
	}
	
	public boolean checkIfNextInputIsQuit(Scanner scnr) {
		boolean hasValidInput = false;
		System.out.println("MENU");
		System.out.println("a - Add vehicle to inventory");
		System.out.println("d - Remove vehicle from inventory");
		System.out.println("c - Update vehicle in inventory");
		System.out.println("i - Output a single vehicle's values");
		System.out.println("o - Output the full inventory");
		System.out.println("s - Save the inventory to a file");
		System.out.println("q - Quit");
		System.out.println();
		
		while (!hasValidInput) {
			System.out.println("Choose an option:");
			String option = scnr.nextLine();
			String output;
			
			switch (option) {
			case "q":
				hasValidInput = true;
				System.out.println("Exiting...");
				output = addInventoryToFile(scnr);
				System.out.println();
				System.out.println(output);
				return true;
			case "a":
				Automobile newCar = new Automobile();
				output = newCar.addVehicle(scnr, inventory);
				hasValidInput = true;
				break;
			case "d":
				output = findMatchingVehicle(scnr).removeVehicle(inventory);
				hasValidInput = true;
				break;
			case "c":
				output = findMatchingVehicle(scnr).updateVehicleInfo(scnr);
				hasValidInput = true;
				break;
			case "i":
				output = getVehicleInfoOutput(findMatchingVehicle(scnr).getVehicleInfo());
				hasValidInput = true;
				break;
			case "o":
				output = buildFullInventoryString();
				hasValidInput = true;
				break;
			case "s":
				output = addInventoryToFile(scnr);
				hasValidInput = true;
				break;
			default:
				output = "Invalid input. Try again.";
			}
			
			System.out.println();
			System.out.println(output);
		}
		
		System.out.println();
		return false;
	}
	
	private Automobile findMatchingVehicle(Scanner scnr) {
		System.out.println("Enter the VIN of the vehicle:");
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
	
	private String getVehicleInfoOutput(String[] values) {
		String output = "";
		for (int i = 0; i < values.length; i++) {
			output = output + (values[i] + "\n");
		}
		
		return output;
	}
	
	private String buildFullInventoryString() {
		String output = dealershipName + "'s Full Inventory\n";
		output = output + "------------------------------------------------------------\n\n";
		
		if (inventory.size() == 0) {
			output = output + "Inventory is empty.";
		}
	
		for (int i = 0; i < inventory.size(); i++) {
			Automobile current = inventory.get(i);
			output = output + ("Vehicle #" + (i + 1) + "\n");
			output = output + (getVehicleInfoOutput(current.getVehicleInfo()));
		}
		
		return output;
	}
	
	private String addInventoryToFile(Scanner scnr) {
		String baseFileError = "Failed to create file to save inventory. ";
		String output = "";
		FileOutputStream outputStream = null;
		PrintWriter fileWriter = null;
		
		System.out.println();
		System.out.println("Saving inventory to file...");
		System.out.println();
		
		try {
			System.out.println("Enter the name of the file with no extensions like \"Autos\":");
			String fileName = scnr.nextLine();
			outputStream = new FileOutputStream("/SavedInventories/" + fileName + ".txt");
			fileWriter = new PrintWriter(outputStream);
			String inventoryOutput = buildFullInventoryString();
			fileWriter.println(inventoryOutput);
			output = "File successfull saved under name: " + fileName;
		} catch (FileNotFoundException e) {
			output = baseFileError + "Failed find or create file.";
		} catch (SecurityException e) {
			output = baseFileError + "No write permissions.";
		} catch (Exception e) {
			output = baseFileError;
		} finally {
			fileWriter.close();
			try {
				outputStream.close();
			} catch (IOException e) {
				output = output + " Failed to close file.";
			}
		}
		
		return output;
	}
}
