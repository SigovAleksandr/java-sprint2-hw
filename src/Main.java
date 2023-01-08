import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Автоматизация бухгалтерии");
        printMenu();
        Scanner scanner = new Scanner(System.in);
        MonthlyReport monthlyReport = new MonthlyReport();
        YearlyReport yearlyReport = new YearlyReport();
        HashMap<Integer, ArrayList<MonthlyReport.Item>> monthReport = new HashMap<>();
        HashMap<Integer, ArrayList<YearlyReport.Item>> yearReport = new HashMap<>();
        HashMap<Integer, YearlyReport.MonthData> parsedYearReport= new HashMap<>();

        while (true) {
            int userCommand = scanner.nextInt();
            if (userCommand == 1) {
                monthReport = monthlyReport.monthReportRecord();
            } else if (userCommand == 2) {
                yearReport = yearlyReport.yearReportRecord();
                parsedYearReport = yearlyReport.parseMonthData(2021, yearReport);
            } else if (userCommand == 3) {
                for (int i = 1; i < 13; i++) {
                    if (monthReport.containsKey(i) && parsedYearReport.containsKey(i)) {
                        double monthProfit = monthlyReport.findProfitSum(i, monthReport);
                        double monthExpense = monthlyReport.findExpenseSum(i, monthReport);
                        if (monthProfit == parsedYearReport.get(i).profit
                            && monthExpense == parsedYearReport.get(i).expense) {
                            System.out.println("Отчет за месяц 0" + i + " сверен успешно.");
                        } else {
                            System.out.println("Отчет за месяц 0" + i + " не сходится. Ошибка!");
                        }
                    }
                }
            } else if (userCommand == 4) {
                monthlyReport.printMonthReport();
            } else if (userCommand == 5) {
                yearlyReport.printYearReport();
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

