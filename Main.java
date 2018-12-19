package databasePackage;

import java.util.ArrayList;
import java.util.Scanner;

class Main {

	private static ArrayList<DatabaseTables> myElements = new ArrayList<DatabaseTables>();
	private static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {

		int choice;
		Menu.printMenu();
		boolean quit = false;
		while (!quit) {

			System.out.print("Επιλογή: ");
			choice = input.nextInt();
			switch (choice) {

			case (1):
				addTable();
				break;
			case (2):
				addField();
				break;
			case (3):
				addElement();
				break;
			case (4):
				printElements();
				break;
			case (5):
				updateElement();
				break;
			case (6):
				removeElement();
				break;
			case (7):
				Menu.printMenu();
				break;
			case (8):
				quit = true;
				break;
			}

		}

		System.out.println("Το πρόγραμμα τερματίστηκε");

	}

	static void addTable() {

		System.out.print("Εισάγετε το όνομα του πίνακα\n");
		String name = input.next();

		if (DatabaseTables.checkTableName(name) >= 0) {
			System.out.println("Υπάρχει ήδη πίνακας με αυτό το όνομα\n");
			return;
		}

		myElements.add(new DatabaseTables(name));
		System.out.println("Ο πίνακας δημιουργήθηκε\n");

	}

	static void addField() {

		System.out.print("Εισάγετε το όνομα του πίνακα που θέλετε να προσθέσετε νεα στοιχεία: ");
		String name = input.next();
		int index = DatabaseTables.checkTableName(name);
		if (index == -1) {
			System.out.println("Δεν υπάρχει πίνακας με αυτό το όνομα\n");
			return;
		}

		System.out.print("Εισάγετε το όνομα του νέου πεδίου: ");
		String field = input.next();
		int index1 = myElements.get(index).checkFieldName(name);
		if (index1 != -1) {
			System.out.println("Υπάρχει ήδη πεδίο με αυτό το όνομα\n");
			return;
		}

		Menu.printType();
		int type = input.nextInt();
		String type1 = getType(type);
		if (type1 == null) {

			System.out.println("Λάθος τύπος\n");
			return;

		}
		myElements.get(index).addField(field, type1);
		;
		System.out.println("Το πεδίο δημιουργήθηκε\n");

	}

	static void addElement() {

		System.out.print("Εισάγετε το όνομα του πίνακα που θέλετε να προσθέσετε νεα στοιχεία: ");
		String name = input.next();
		int index = DatabaseTables.checkTableName(name);
		if (index == -1) {
			System.out.println("Δεν υπάρχει πίνακας με αυτό το όνομα\n");
			return;
		}

		System.out.print("Εισάγετε το πεδίο στο οποίο θέλετε να εισάγετε το στοιχείο: ");
		String field = input.next();
		int index1 = myElements.get(index).checkFieldName(field);
		if (index1 == -1) {
			System.out.println("Δεν υπάρχει πεδίο με αυτό το όνομα\n");
			return;
		}

		System.out.print("Εισάγετε το νέο στοιχείο: ");
		String element = input.next();
		boolean flag = myElements.get(index).readInfo(index1, element);
		if (!flag) {

			return;

		}

		myElements.get(index).addElement(element, index1);
		;
		System.out.println("Το στοιχείο προστέθηκε επιτυχώς\n");

	}

	static void printElements() {

		System.out.print("Εισάγετε το όνομα του πίνακα που θέλετε να εμφανιστεί: ");
		String name = input.next();
		int index = DatabaseTables.checkTableName(name);
		if (index == -1) {
			System.out.println("Δεν υπάρχει πίνακας με αυτό το όνομα\n");
			return;
		}

		System.out.print("Εισάγετε το πεδίο που θέλετε να εμφανιστεί: ");
		String field = input.next();
		int index1 = myElements.get(index).checkFieldName(field);
		if (index1 == -1) {
			System.out.println("Δεν υπάρχει πεδίο με αυτό το όνομα\n");
			return;
		}

		myElements.get(index).printElements(index1);

	}

	static void updateElement() {

		System.out.print("Εισάγετε το όνομα του πίνακα που θέλετε να αλλάξετε κάποιο στοιχείο: ");
		String name = input.next();
		int index = DatabaseTables.checkTableName(name);
		if (index == -1) {
			System.out.println("Δεν υπάρχει πίνακας με αυτό το όνομα\n");
			return;
		}

		System.out.print("Εισάγετε το πεδίο που θέλετε να αλλάξετε το στοιχείο: ");
		String field = input.next();
		int index1 = myElements.get(index).checkFieldName(field);
		if (index1 == -1) {
			System.out.println("Δεν υπάρχει πεδίο με αυτό το όνομα\n");
			return;
		}

		System.out.print("Εισάγετε το στοιχείο που θέλετε να αλλάξετε: ");
		String element = input.next();
		int index2 = myElements.get(index).checkElement(element, index1);

		if (index2 == -1) {
			System.out.println("Το πεδίο δεν περιέχει αυτό το στοιχείο\n");
			return;
		}

		System.out.print("Εισάγετε το νέο στοιχείο: ");
		String element1 = input.next();
		boolean flag = myElements.get(index).readInfo(index, element1);
		if (!flag) {

			return;

		}

		myElements.get(index).updateElement(index1, index2, element1);
		System.out.println("Το στοιχείο άλλαξε επιτυχώς\n");

	}

	static void removeElement() {

		System.out.print("Εισάγετε το όνομα του πίνακα που θέλετε να αφαιρέσετε κάποιο στοιχείο: ");
		String name = input.next();
		int index = DatabaseTables.checkTableName(name);
		if (index == -1) {
			System.out.println("Δεν υπάρχει πίνακας με αυτό το όνομα\n");
			return;
		}

		System.out.print("Εισάγετε το πεδίο που θέλετε να διαγράψετε κάποιο στοιχείο: ");
		String field = input.next();
		int index1 = myElements.get(index).checkFieldName(field);
		if (index1 == -1) {
			System.out.println("Δεν υπάρχει πεδίο με αυτό το όνομα\n");
			return;
		}

		System.out.print("Εισάγετε το στοιχείο που θέλετε να αφαιρέσετε: ");
		String element = input.next();
		int index2 = myElements.get(index).checkElement(element, index1);

		if (index2 == -1) {
			System.out.println("Ο πίνακας δεν περιέχει αυτό το στοιχείο\n");
			return;
		}

		myElements.get(index).removeElement(index2, index1);
		System.out.println("Το στοιχείο διαγράφθηκε επιτυχώς\n");

	}

	static String getType(int type) {

		if (type == 1) {

			return "String";

		} else if (type == 2) {

			return "int";

		} else if (type == 3) {

			return "float";

		} else if (type == 4) {

			return "double";

		} else if (type == 5) {

			return "long";

		} else {

			return null;

		}

	}

}
