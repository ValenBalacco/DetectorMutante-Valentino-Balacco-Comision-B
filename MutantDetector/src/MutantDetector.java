import java.util.ArrayList;
import java.util.Scanner;

public class MutantDetector {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Menú:");
            System.out.println("1. Ingresar matriz de ADN");
            System.out.println("2. Salir");
            System.out.print("Elija una opción: ");

            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                ArrayList<String> adnMatrix = buildAdnMatrix(scanner);
                if (adnMatrix != null) {
                    if (haveMutantGenes(adnMatrix)) {
                        System.out.println("El ADN es de un mutante.");
                    } else {
                        System.out.println("El ADN no es de un mutante.");
                    }
                }
            } else if (choice.equals("2")) {
                break;
            } else {
                System.out.println("Opción inválida. Por favor, elija una opción válida.");
            }
        }
    }

    public static ArrayList<String> buildAdnMatrix(Scanner scanner) {
        ArrayList<String> matrix = new ArrayList<String>();

        for (int i = 0; i < 6; i++) {
            System.out.print("Ingrese una fila de ADN (6 letras A, T, C, o G): ");
            String row = scanner.nextLine().toUpperCase();

            if (row.length() == 6 && row.matches("[ATCG]+")) {
                matrix.add(row);
            } else {
                System.out.println("La fila ingresada es inválida. Solo se permite letras A, T, C o G y debe tener exactamente 6 caracteres.");
                return null;
            }
        }

        return matrix;
    }

    public static boolean haveMutantGenes(ArrayList<String> matrix) {
        ArrayList<String> distinctSequences = new ArrayList<String>();

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {

                if (j + 3 < 6) {
                    String sequence = matrix.get(i).substring(j, j + 4);
                    if (isMutant(sequence)) {
                        distinctSequences.add(sequence);
                    }
                }

                if (i + 3 < 6) {
                    String sequence = matrix.get(i).charAt(j) + "" + matrix.get(i + 1).charAt(j) + "" +
                            matrix.get(i + 2).charAt(j) + "" + matrix.get(i + 3).charAt(j);
                    if (isMutant(sequence)) {
                        distinctSequences.add(sequence);
                    }
                }

                if (i + 3 < 6 && j + 3 < 6) {
                    String sequence = matrix.get(i).charAt(j) + "" + matrix.get(i + 1).charAt(j + 1) + "" +
                            matrix.get(i + 2).charAt(j + 2) + "" + matrix.get(i + 3).charAt(j + 3);
                    if (isMutant(sequence)) {
                        distinctSequences.add(sequence);
                    }
                }

                if (i + 3 < 6 && j - 3 >= 0) {
                    String sequence = matrix.get(i).charAt(j) + "" + matrix.get(i + 1).charAt(j - 1) + "" +
                            matrix.get(i + 2).charAt(j - 2) + "" + matrix.get(i + 3).charAt(j - 3);
                    if (isMutant(sequence)) {
                        distinctSequences.add(sequence);
                    }
                }
            }
        }

        return distinctSequences.size() > 1;
    }

    public static boolean isMutant(String sequence) {
        int count = 1;
        for (int i = 1; i < sequence.length(); i++) {
            if (sequence.charAt(i) == sequence.charAt(i - 1)) {
                count++;
            } else {
                count = 1;
            }
            if (count >= 4) {
                return true;
            }
        }
        return false;
    }
}