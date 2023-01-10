import java.util.ArrayList;
import java.util.HashMap;

public class MonthlyReport {
    // ArrayList для сохранения операций в разрезе вложенного класса Item, для HashMap
    ArrayList<Item> monthlyReportItems;
    // HashMap для сохранения информации по месяцам, Integer - номер месяца, ArrayList - список операций в месяце
    HashMap<Integer, ArrayList<Item>> monthlyReport = new HashMap<>();
    FileReader fileReader = new FileReader();

    class Item {
        String itemName;
        boolean isExpense;
        int quantity;
        int sumOfOne;

        public Item(String[] lines) {
            itemName = lines[0];
            isExpense = Boolean.parseBoolean(lines[1]);
            quantity = Integer.parseInt(lines[2]);
            sumOfOne = Integer.parseInt(lines[3]);
        }
    }

    class ResultItem {
        String resultName;
        int resultSum;

        public void setResultName(String resultName) {
            this.resultName = resultName;
        }

        public void setResultSum(int resultSum) {
            this.resultSum = resultSum;
        }
    }

    HashMap<Integer, ArrayList<Item>> monthReportRecord() {
        for (int i = 1; i < 4; i++) {
            String path = "resources/m.20210" + i + ".csv";
            String report = fileReader.readFileContentsOrNull(path);
            String[] lines = report.split(System.lineSeparator());
            monthlyReportItems = new ArrayList<>();
            for (int k = 1; k < lines.length; k++) {
                monthlyReportItems.add(new Item(lines[k].split(",")));
                monthlyReport.put(i, monthlyReportItems);
            }
        }
        System.out.println("Отчеты по месяцам успешно считаны");
        return monthlyReport;
    }

    void printMonthReport() {
        for (int i = 1; i < 13; i++) {
            if (monthlyReport.containsKey(i)) {
                System.out.println(printMonthName(i));
                System.out.println("Максимальная прибыль: " + findMaxProfit(i, monthlyReport).resultSum
                        + " " + findMaxProfit(i, monthlyReport).resultName);
                System.out.println("Максимальная трата: " + findMaxExpense(i, monthlyReport).resultSum
                        + " " + findMaxExpense(i, monthlyReport).resultName);
                System.out.println("-----");
            }
        }
    }

    ResultItem findMaxExpense(int month, HashMap<Integer, ArrayList<Item>> map) {
        ResultItem resultItem = new ResultItem();
        int maxExpense = 0;
        String itemName = "";
        for (Item item : map.get(month)) {
            if (item.isExpense) {
                if (maxExpense < item.quantity * item.sumOfOne) {
                    maxExpense = item.quantity * item.sumOfOne;
                    itemName = item.itemName;
                }
            }
        }
        resultItem.setResultName(itemName);
        resultItem.setResultSum(maxExpense);
        return resultItem;
    }

    ResultItem findMaxProfit(int month, HashMap<Integer, ArrayList<Item>> map) {
        ResultItem resultItem = new ResultItem();
        int maxProfit = 0;
        String itemName = "";
        for (Item item : map.get(month)) {
            if (!item.isExpense) {
                if (maxProfit < item.quantity * item.sumOfOne) {
                    maxProfit = item.quantity * item.sumOfOne;
                    itemName = item.itemName;
                }
            }
        }
        resultItem.setResultName(itemName);
        resultItem.setResultSum(maxProfit);
        return resultItem;
    }

    double findProfitSum(int month, HashMap<Integer, ArrayList<Item>> map) {
        double profitSum = 0;
        for (Item item : map.get(month)) {
            if (!item.isExpense) {
                profitSum = profitSum + item.quantity * item.sumOfOne;
            }
        }
        return profitSum;
    }

    double findExpenseSum(int month, HashMap<Integer, ArrayList<Item>> map) {
        double expenseSum = 0;
        for (Item item : map.get(month)) {
            if (item.isExpense) {
                expenseSum = expenseSum + item.quantity * item.sumOfOne;
            }
        }
        return expenseSum;
    }
    String printMonthName(int numberOfMonth) {
        String monthName;
        if (numberOfMonth == 1) {
            monthName = "Январь";
            return monthName;
        } else if (numberOfMonth == 2) {
            monthName = "Февраль";
            return monthName;
        } else if (numberOfMonth == 3) {
            monthName = "Март";
            return monthName;
        } else if (numberOfMonth == 4) {
            monthName = "Апрель";
            return monthName;
        } else if (numberOfMonth == 5) {
            monthName = "Май";
            return monthName;
        } else if (numberOfMonth == 6) {
            monthName = "Июнь";
            return monthName;
        } else if (numberOfMonth == 7) {
            monthName = "Июль";
            return monthName;
        } else if (numberOfMonth == 8) {
            monthName = "Август";
            return monthName;
        } else if (numberOfMonth == 9) {
            monthName = "Сентябрь";
            return monthName;
        } else if (numberOfMonth == 10) {
            monthName = "Октярь";
            return monthName;
        } else if (numberOfMonth ==11) {
            monthName = "Ноябрь";
            return monthName;
        } else if (numberOfMonth == 12) {
            monthName = "Декабрь";
            return monthName;
        }
        return null;
    }

}
