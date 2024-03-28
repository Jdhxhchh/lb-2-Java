import java.util.*;

class Transaction {
    private String date;
    private String description;
    private double amount;
    private String category;

    public Transaction(String date, String description, double amount, String category) {
        this.date = date;
        this.description = description;
        this.amount = amount;
        this.category = category;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }
}

class TransactionAnalyzer {
    private List<Transaction> transactions;

    public TransactionAnalyzer(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public double calculateTotalBalance() {
        double balance = 0;
        for (Transaction transaction : transactions) {
            balance += transaction.getAmount();
        }
        return balance;
    }

    public Map<String, Integer> countTransactionsByMonth() {
        Map<String, Integer> transactionsByMonth = new HashMap<>();
        for (Transaction transaction : transactions) {
            String[] dateParts = transaction.getDate().split("-");
            String month = dateParts[1];
            transactionsByMonth.put(month, transactionsByMonth.getOrDefault(month, 0) + 1);
        }
        return transactionsByMonth;
    }

    public List<Transaction> getTop10Expenses() {
        List<Transaction> sortedTransactions = new ArrayList<>(transactions);
        sortedTransactions.sort(Comparator.comparingDouble(Transaction::getAmount).reversed());
        return sortedTransactions.subList(0, Math.min(sortedTransactions.size(), 10));
    }

    public Map<String, Double> analyzeExpensesByCategory() {
        Map<String, Double> expensesByCategory = new HashMap<>();
        for (Transaction transaction : transactions) {
            String category = transaction.getCategory();
            expensesByCategory.put(category, expensesByCategory.getOrDefault(category, 0.0) + transaction.getAmount());
        }
        return expensesByCategory;
    }
}

public class BankTransactionAnalyzer {
    public static void main(String[] args) {

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction("2024-01-05", "Groceries", 50.0, "Food"));
        transactions.add(new Transaction("2024-01-10", "Gas", 40.0, "Transportation"));
        transactions.add(new Transaction("2024-02-15", "Dinner", 80.0, "Food"));
        transactions.add(new Transaction("2024-02-20", "Uber", 30.0, "Transportation"));
        transactions.add(new Transaction("2024-03-03", "Books", 25.0, "Entertainment"));

        TransactionAnalyzer analyzer = new TransactionAnalyzer(transactions);


        double totalBalance = analyzer.calculateTotalBalance();
        System.out.println("Total Balance: $" + totalBalance);


        Map<String, Integer> transactionsByMonth = analyzer.countTransactionsByMonth();
        System.out.println("Transactions by Month:");
        for (Map.Entry<String, Integer> entry : transactionsByMonth.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }


        List<Transaction> topExpenses = analyzer.getTop10Expenses();
        System.out.println("Top 10 Expenses:");
        for (Transaction expense : topExpenses) {
            System.out.println(expense.getDescription() + ": $" + expense.getAmount());
        }


        Map<String, Double> expensesByCategory = analyzer.analyzeExpensesByCategory();
        System.out.println("Expenses by Category:");
        for (Map.Entry<String, Double> entry : expensesByCategory.entrySet()) {
            System.out.println(entry.getKey() + ": $" + entry.getValue());
        }
    }
}
