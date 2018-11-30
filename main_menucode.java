package Database;

import java.util.ArrayList;
import java.util.Scanner;

class Main {

	private static ArrayList<DatabaseElements> myElements = new ArrayList<DatabaseElements>();
	private static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {

		int choice;
		Menu.printMenu();
		boolean quit = false;
		while (!quit) {

			System.out.print("Choice: ");
			choice = input.nextInt();
			switch (choice) {

			case (1):
				addTable();
				break;
			case (2):
				addElement();
				break;
			case (3):
				printElements();
				break;
			case (4):
				updateElement();
				break;
			case (5):
				removeElement();
				break;
			case (6):
				Menu.printMenu();
				break;
			case (7):
				quit = true;
				break;
			}

		}

		System.out.println("The program was terminated");

	}

	