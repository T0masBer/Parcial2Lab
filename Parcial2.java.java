import java.util.Scanner;

public class MutantDetector {
    public static void main(String[] args) {
    char[][] dnaSequence = inputDnaSequence();
    boolean result = isMutant(dnaSequence);

    if (result) {
        System.out.println("El humano es mutante.");
    } else {
        System.out.println("El humano no es mutante.");
    }
}

    public static boolean isMutant(char[][] dna) {
        return checkHorizontal(dna) || checkVertical(dna) || checkDiagonals(dna);
    }

    public static boolean checkHorizontal(char[][] dna) {
        for (char[] row : dna) {
            for (int i = 0; i < row.length - 3; i++) {
                if (row[i] == 'A' && row[i + 1] == 'A' && row[i + 2] == 'A' && row[i + 3] == 'A' ||
                        row[i] == 'T' && row[i + 1] == 'T' && row[i + 2] == 'T' && row[i + 3] == 'T' ||
                        row[i] == 'C' && row[i + 1] == 'C' && row[i + 2] == 'C' && row[i + 3] == 'C' ||
                        row[i] == 'G' && row[i + 1] == 'G' && row[i + 2] == 'G' && row[i + 3] == 'G') {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean checkVertical(char[][] dna) {
        for (int i = 0; i < dna[0].length; i++) {
            StringBuilder column = new StringBuilder();
            for (char[] row : dna) {
                column.append(row[i]);
            }
            for (int j = 0; j < column.length() - 3; j++) {
                if (column.charAt(j) == 'A' && column.charAt(j + 1) == 'A' && column.charAt(j + 2) == 'A' && column.charAt(j + 3) == 'A' ||
                        column.charAt(j) == 'T' && column.charAt(j + 1) == 'T' && column.charAt(j + 2) == 'T' && column.charAt(j + 3) == 'T' ||
                        column.charAt(j) == 'C' && column.charAt(j + 1) == 'C' && column.charAt(j + 2) == 'C' && column.charAt(j + 3) == 'C' ||
                        column.charAt(j) == 'G' && column.charAt(j + 1) == 'G' && column.charAt(j + 2) == 'G' && column.charAt(j + 3) == 'G') {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean checkDiagonals(char[][] dna) {
        for (int i = 0; i < dna.length - 3; i++) {
            for (int j = 0; j < dna[0].length - 3; j++) {
                StringBuilder diagonal = new StringBuilder();
                for (int k = 0; k < 4; k++) {
                    diagonal.append(dna[i + k][j + k]);
                }
                if (diagonal.toString().equals("AAAA") || diagonal.toString().equals("TTTT") ||
                        diagonal.toString().equals("CCCC") || diagonal.toString().equals("GGGG")) {
                    return true;
                }
            }
        }

        for (int i = 0; i < dna.length - 3; i++) {
            for (int j = dna[0].length - 1; j >= 3; j--) {
                StringBuilder diagonal = new StringBuilder();
                for (int k = 0; k < 4; k++) {
                    diagonal.append(dna[i + k][j - k]);
                }
                if (diagonal.toString().equals("AAAA") || diagonal.toString().equals("TTTT") ||
                        diagonal.toString().equals("CCCC") || diagonal.toString().equals("GGGG")) {
                    return true;
                }
            }
        }

        return false;
    }

    public static char[][] inputDnaSequence() {
        Scanner scanner = new Scanner(System.in);
        char[][] dna = new char[6][6];

        while (true) {
            System.out.println("Ingrese la secuencia de ADN (filas separadas por Enter, letras A, T, C, G):");
            try {
                for (int i = 0; i < 6; i++) {
                    String row = scanner.next().toUpperCase();
                    dna[i] = row.toCharArray();
                }
                if (isValidDna(dna)) {
                    return dna;
                } else {
                    System.out.println("La matriz de ADN ingresada no es válida. Verifique que sea una matriz 6x6 y contenga solo bases válidas (A, T, C, G).");
                    dna = new char[6][6];  // Reiniciar la matriz si es inválida
                }
            } catch (Exception e) {
                System.out.println("Entrada inválida. Por favor, ingrese una secuencia de ADN válida.");
                scanner.nextLine();  // Limpiar el buffer del scanner
            }
        }
    }

    public static boolean isValidDna(char[][] dna) {
        char[] validBases = {'A', 'T', 'C', 'G'};
        for (char[] row : dna) {
            for (char base : row) {
                boolean isValidBase = false;
                for (char validBase : validBases) {
                    if (base == validBase) {
                        isValidBase = true;
                        break;
                    }
                }
                if (!isValidBase) {
                    return false;
                }
            }
            if (row.length != 6) {
                return false;
            }
        }
        return dna.length == 6;
    }
}
