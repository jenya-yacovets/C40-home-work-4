import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Стартовые настройки
        String nameUser_1;
        String nameUser_2;

        int[][] playingField = new int[3][3];

        System.out.println("Введите имя первого игрока (X):");
        nameUser_1 = scanner.nextLine();

        System.out.println("Введите имя второго игрока (0):");
        nameUser_2 = scanner.nextLine();

        System.out.println("========== Приветствую вас, " + nameUser_1 + " и " + nameUser_2 + ". Добро пожаловать в игру крестики-нолики ==========");

        // Начало игры
        int userId = 1;
        String step = "turn";

        while (true) {

            if (step == "turn") {
                // Основной ход
                for (int i=0; i<playingField.length; i++) {
                    for (int j=0; j<playingField[i].length; j++) {

                        if (playingField[i][j] == 0) {
                            System.out.print("* ");
                        } else if (playingField[i][j] == 1) {
                            System.out.print("X ");
                        } else {
                            System.out.print("0 ");
                        }
                    }
                    System.out.println();
                }

                String userMessage;

                if (userId == 1) {
                    userMessage = nameUser_1 + " (X)";
                } else {
                    userMessage = nameUser_2 + " (0)";
                }

                System.out.println("----------- Сейчас ходит " + userMessage + " -----------");
            } else {
                // Повтор хода, если первая попытка была в занятую клеточку
                System.out.println("Данная клеточка занята, повтори свой ход:");
                step = "turn";
            }

            int string = 0;
            int column = 0;

            // Получаем номер строки
            System.out.println("Введите номер строки (1-3):");
            while (string == 0) {
                if (!scanner.hasNextInt()) {
                    System.out.println("Ты ввел не целое число :-( \nПовтори попытку:");
                    scanner.next();
                    continue;
                }

                string = scanner.nextInt();

                if (string <= 0 || string > 3) {
                    System.out.println("Необходимо ввести число в промежутке 1-3 :-( \nПовтори попытку:");
                    string = 0;
                }
            }

            // Получаем номер стобца
            System.out.println("Введите номер столбца (1-3):");
            while (column == 0) {
                if (!scanner.hasNextInt()) {
                    System.out.println("Ты ввел не целое число :-( \nПовтори попытку:");
                    scanner.next();
                    continue;
                }

                column = scanner.nextInt();

                if (column <= 0 || column > 3) {
                    System.out.println("Необходимо ввести число в промежутке 1-3 :-( \nПовтори попытку:");
                    column = 0;
                }
            }

            if (playingField[string-1][column-1] != 0) {
                step = "second";
                continue;
            }

            playingField[string-1][column-1] = userId;

            // Проверка на победу комбинации строк
            if(playingField[0][0] == playingField[0][1] && playingField[0][1] == playingField[0][2] && playingField[0][2] != 0) break;
            if(playingField[1][0] == playingField[1][1] && playingField[1][1] == playingField[1][2] && playingField[1][2] != 0) break;
            if(playingField[2][0] == playingField[2][1] && playingField[2][1] == playingField[2][2] && playingField[2][2] != 0) break;
            // Проверка на победу комбинации столбцов
            if(playingField[0][0] == playingField[1][0] && playingField[1][0] == playingField[2][0] && playingField[2][0] != 0) break;
            if(playingField[0][1] == playingField[1][1] && playingField[1][1] == playingField[2][1] && playingField[2][1] != 0) break;
            if(playingField[0][2] == playingField[1][2] && playingField[1][2] == playingField[2][2] && playingField[2][2] != 0) break;
            // Проверка на победу комбинации диагоналей
            if(playingField[0][0] == playingField[1][1] && playingField[1][1] == playingField[2][2] && playingField[2][2] != 0) break;
            if(playingField[0][2] == playingField[1][1] && playingField[1][1] == playingField[2][0] && playingField[2][0] != 0) break;

            // смена хода на другого игрока
            if (userId == 1) {
                userId = 2;
            } else {
                userId = 1;
            }
        }

        if (userId == 1) {
            System.out.println("Победил " + nameUser_1);
        } else {
            System.out.println("Победил " + nameUser_2);
        }
    }
}