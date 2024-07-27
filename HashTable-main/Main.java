import HashTable.HashTable;
import Business.Business;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static HashTable hashTable;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        hashTable = new HashTable(200000);
        boolean exit = false;

        while (!exit) {
            System.out.println("Seleccione una estructura de datos:");
            System.out.println("1. Lista de Adyacencia");
            System.out.println("2. Lista de Java");
            System.out.println("3. Salir");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    adyacenciaMenu(scanner);
                    break;
                case 2:
                    javaMenu(scanner);
                    break;
                case 3:
                    exit = true;
                    break;
                default:
                    System.out.println("Opción no válida, intente de nuevo por favor.");
            }
        }
        scanner.close();
    }

    private static void adyacenciaMenu(Scanner scanner) {
        boolean backToMain = false;

        while (!backToMain) {
            System.out.println("Seleccione una opción para Lista de Adyacencia:");
            System.out.println("1. Cargar datos usando hash de multiplicación");
            System.out.println("2. Cargar datos usando hash de división");
            System.out.println("3. Buscar por ID usando hash de multiplicación");
            System.out.println("4. Buscar por ID usando hash de división");
            System.out.println("5. Volver al menú principal");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    loadData(true, true);
                    break;
                case 2:
                    loadData(false, true);
                    break;
                case 3:
                    searchData(true, true, scanner);
                    break;
                case 4:
                    searchData(false, true, scanner);
                    break;
                case 5:
                    backToMain = true;
                    break;
                default:
                    System.out.println("Opción no válida, intente de nuevo por favor.");
            }
        }
    }

    private static void javaMenu(Scanner scanner) {
        boolean backToMain = false;

        while (!backToMain) {
            System.out.println("Seleccione una opción para Lista de Java:");
            System.out.println("1. Cargar datos usando hash de multiplicación");
            System.out.println("2. Cargar datos usando hash de división");
            System.out.println("3. Buscar por ID usando hash de multiplicación");
            System.out.println("4. Buscar por ID usando hash de división");
            System.out.println("5. Volver al menú principal");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    loadData(true, false);
                    break;
                case 2:
                    loadData(false, false);
                    break;
                case 3:
                    searchData(true, false, scanner);
                    break;
                case 4:
                    searchData(false, false, scanner);
                    break;
                case 5:
                    backToMain = true;
                    break;
                default:
                    System.out.println("Opción no válida, intente de nuevo por favor.");
            }
        }
    }
    private static void loadData(boolean useMultiplication, boolean useLinkedList) {
        String line = "";
        String splitBy = ",";
        long startTime, endTime;

        try (BufferedReader br = new BufferedReader(new FileReader("./src/dataset/bussines.csv"))) {
            startTime = System.nanoTime();
            while ((line = br.readLine()) != null) {
                String[] data = line.split(splitBy);
                String id = data[0];
                String name = data[1];
                String address = data[2];
                String city = data[3];
                String state = data[4];

                Business business = new Business(id, name, address, city, state);

                hashTable.insert(business, useMultiplication, useLinkedList);
            }
            endTime = System.nanoTime();
            System.out.println("----------------------------------------------- \n Datos cargados en " + (endTime - startTime) + " ns" +
                            "\n-----------------------------------------------" );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void searchData(boolean useMultiplication, boolean useLinkedList, Scanner scanner) {
        System.out.println("Ingrese el ID a buscar:");
        String searchId = scanner.nextLine();

        long startTime = System.nanoTime();
        Business foundBusiness = hashTable.search(searchId, useMultiplication, useLinkedList);
        long endTime = System.nanoTime();
        System.out.println("Tiempo de busqueda: " + (endTime - startTime) + " ns");

        if (foundBusiness != null) {
            System.out.println("Se encontraron correctamente los datos : \n" + foundBusiness);
        } else {
            System.out.println("No se encontraron los datos");
        }
    }
}