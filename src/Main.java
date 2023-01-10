import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Автоматизация бухгалтерии");
        Scanner scanner = new Scanner(System.in);
        ReportHelper reportHelper = new ReportHelper();

        while (true) {
            printMenu();
            int userCommand = scanner.nextInt();
            if (userCommand == 1) {
                reportHelper.saveMonthlyReport();
            } else if (userCommand == 2) {
                reportHelper.saveYearlyParsedReport();
            } else if (userCommand == 3) {
                reportHelper.compareReports();
            } else if (userCommand == 4) {
                reportHelper.printMonthReport();
            } else if (userCommand == 5) {
                reportHelper.printYearlyReport();
            } else if (userCommand == 6) {
                System.out.println("Приложение закрыто");
                break;
            } else {
                System.out.println("Введена некорректная команда");
            }
        }
    }
    static void printMenu() {
        System.out.println(" ");
        System.out.println("Введите команду");
        System.out.println("1 - Считать все месячные отчёты");
        System.out.println("2 - Считать годовой отчёт");
        System.out.println("3 - Сверить отчёты");
        System.out.println("4 - Вывести информацию о всех месячных отчётах");
        System.out.println("5 - Вывести информацию о годовом отчёте");
        System.out.println("6 - Выйти из приложения");
    }
}

