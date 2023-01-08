import java.util.ArrayList;
import java.util.HashMap;

public class YearlyReport {
    // ArrayList для сохранения операций в разрезе вложенного класса Item, для HashMap
    ArrayList<Item> yearlyReportItems;
    // HashMap для сохранения сырого отчета по годам
    HashMap<Integer, ArrayList<Item>> yearlyReport = new HashMap<>();
    FileReader fileReader = new FileReader();

    class Item {
        int month;
        int amount;
        boolean isExpense;

        public Item(String[] lines) {
            month = Integer.parseInt(lines[0]);
            amount = Integer.parseInt(lines[1]);
            isExpense = Boolean.parseBoolean(lines[2]);
        }
    }

    class MonthData {
        int profit;
        int expense;

        public void setProfit(int profit) {
            this.profit = profit;
        }

        public void setExpense(int expense) {
            this.expense = expense;
        }
    }

    HashMap<Integer, ArrayList<Item>> yearReportRecord() {
        String path = "resources/y.2021.csv";
        int year = 2021;
        String report = fileReader.readFileContentsOrNull(path);
        String[] lines = report.split(System.lineSeparator());
        yearlyReportItems = new ArrayList<>();
        for (int i = 1; i < lines.length; i++) {
            yearlyReportItems.add(new Item(lines[i].split(",")));
            yearlyReport.put(year, yearlyReportItems);
        }
        System.out.println("Отчеты по годам успешно считаны");
        return yearlyReport;
    }

    // Метод для получения детальной информации в разрезе месяцев в указанном году, возвращает новый HashMap
    HashMap<Integer, MonthData> parseMonthData(int year, HashMap<Integer, ArrayList<Item>> map) {
        HashMap<Integer, MonthData> parsedMonthData = new HashMap<>();
        for (Item month : yearlyReport.get(year)) {
            if (!parsedMonthData.containsKey(month.month)) {
                parsedMonthData.put(month.month, new MonthData());
                if (!month.isExpense) {
                    parsedMonthData.get(month.month).setProfit(month.amount);
                } else if (month.isExpense) {
                    parsedMonthData.get(month.month).setExpense(month.amount);
                }
            } else {
                if (!month.isExpense) {
                    parsedMonthData.get(month.month).setProfit(month.amount);
                } else if (month.isExpense) {
                    parsedMonthData.get(month.month).setExpense(month.amount);
                }
            }
        }
        return parsedMonthData;
    }

    void printYearReport() {
        System.out.println("Год 2021");
        HashMap<Integer, MonthData> parsedMonthData = parseMonthData(2021, yearlyReport);
        double avgExpense = 0;
        int avgProfit = 0;
        for (int i = 1; i < 13; i++) {
            if (parsedMonthData.containsKey(i)) {
                System.out.println("Прибыль по 0" + i + " месяцу");
                System.out.println(parsedMonthData.get(i).profit - parsedMonthData.get(i).expense);
                avgExpense = avgExpense + parsedMonthData.get(i).expense;
                avgProfit = avgProfit + parsedMonthData.get(i).profit;
            }
        }
        System.out.println("Средний расход за 2021 год: " + avgExpense / 12);
        System.out.println("Средний доход за 2021 год: " + avgProfit * 12);
    }
}
