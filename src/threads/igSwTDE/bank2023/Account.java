package threads.igSwTDE.bank2023;

public class Account {
    private float amount;

    public Account(float amount) {
        this.amount = amount;
    }

    public void deposit(float amount) {
        this.amount += amount;
    }

    public void withdraw(float amount) {
        this.amount -= amount;
    }

    public float getAmount() {
        return amount;
    }
}
