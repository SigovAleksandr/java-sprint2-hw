import java.util.ArrayList;
import java.util.HashMap;

public class ReportHelper {
    MonthlyReport monthlyReport = new MonthlyReport();
    YearlyReport yearlyReport = new YearlyReport();

    HashMap<Integer, ArrayList<MonthlyReport.Item>> monthReport = new HashMap<>();
    HashMap<Integer, ArrayList<YearlyReport.Item>> yearReport = new HashMap<>();
    HashMap<Integer, YearlyReport.MonthData> parsedYearReport = new HashMap<>();

    void saveMonthlyReport(){
        monthReport = monthlyReport.monthReportRecord();
    }

    HashMap<Integer, ArrayList<YearlyReport.Item>> saveYearlyReport(){
        yearReport = yearlyReport.yearReportRecord();
        return yearReport;
    }

    void saveYearlyParsedReport(){
        parsedYearReport = yearlyReport.parseMonthData(2021, saveYearlyReport());
    }

    void printMonthReport(){
        monthlyReport.printMonthReport();
    }

    void printYearlyReport(){
        yearlyReport.printYearReport();
    }

    void compareReports(){
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
    }
}
