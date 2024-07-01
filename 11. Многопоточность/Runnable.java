
public class Bank {

  private HashMap<Integer, Account> accounts;
  private final Random random = new Random();

  public long getSumAllAccounts() {
    return accounts.values().stream().mapToLong(Account::getBalance).sum();
  }

  private synchronized boolean isFraud(int fromAccountNum, int toAccountNum, long amount)
	{
        return random.nextBoolean();
    }

  public void transfer(String fromAccountNum, String toAccountNum, int amount)
{
    Account fromAccount = accounts.get(Integer.parseInt(fromAccountNum));
    Account toAccount = accounts.get(Integer.parseInt(toAccountNum));

    if (fromAccount.isBlocked() || toAccount.isBlocked()) {
      return;
    }

    transaction(amount, fromAccount, toAccount);

    if (amount > 50000) {
      if (isFraud(Integer.parseInt(fromAccountNum), Integer.parseInt(toAccountNum), amount)) {
        transaction(amount, toAccount, fromAccount);
        fromAccount.blockAccount();
        toAccount.blockAccount();
      }
    }
  }

  private void transaction(int amount, Account fromAccount, Account toAccount) {
    if (fromAccount.withdrawMoney(amount)) {
      toAccount.putMoney(amount);
    }
  }

  public long getBalance(String accountNum) {
    Account account = accounts.get(Integer.parseInt(accountNum));
    return account.getBalance();
  }

  public void setAccounts(int accNumber) {
    this.accounts = new HashMap<>();
    for (int i = 1; i <= accNumber; i++) {
      long initialValue = (long) (200000);
      Account account = new Account(i, initialValue);
      this.accounts.put(i, account);
    }
  }
}

public class Account {

  private long balance;
  private int accNumber;
  private boolean isBlocked = false;

  public Account(int accNumber, long balance) {
    this.accNumber = accNumber;
    this.balance = balance;
  }

  public long getBalance() {
    return balance;
  }

  public void setIsBlocked(boolean isBlocked) {
    this.isBlocked = isBlocked;
  }

  public boolean isBlocked() {
    return isBlocked;
  }

  public synchronized boolean withdrawMoney(long money) {
    if (balance >= money) {
      balance -= money;
      return true;
    }
    return false;
  }

  public synchronized void putMoney(long money) {
    balance += money;
  }

  public synchronized void blockAccount() {
    isBlocked = true;
  }
}

public class TransferRun implements Runnable {

    private static Bank bank; //поле банка
    private int count; //количество аккаутов
    private int operationsCount; //количество операций, которые нужно совершить

    public TransferRun(Bank bank, int count, int operationsCount){
        TransferRun.bank = bank;
        this.count = count;
        this.operationsCount = operationsCount;
    }

    @Override
    public void run() {
		//TODO реализуйте здесь <operationsCount> случайных операций
          for (int j = 1; j < operationsCount; j++) {
            int amount = (int) (10000 + 45000 * Math.random());
            bank.transfer(String.valueOf(j), String.valueOf(j + 1), amount);
            if (j == 99) {
              break;
            }
          }
      }
}