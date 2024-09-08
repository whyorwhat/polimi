package threads.igSwTDE.bank2023;

public class Bank {
    private float totalCommissions;

    public Bank(){
        this.totalCommissions = 0;
    }

    //A lends to B
    public void lend(Account a, Account b, float loan) {
        // Lock objects
        Account firstLock = a;
        Account secondLock = b;

        // Order accounts on hashcode to avoid deadlock
        if (System.identityHashCode(a) > System.identityHashCode(b)) {
            firstLock = b;
            secondLock = a;
        }

        synchronized(firstLock){
            synchronized(secondLock){
                if(a.getAmount() >= 2*loan && b.getAmount() >= 3*loan) {
                    a.withdraw(loan);
                    float loanCommissions = loan * 0.2f;
                    this.totalCommissions += loanCommissions;
                    float movingMoney = loan - loanCommissions;
                    b.deposit(movingMoney);
                }else{
                    throw new IllegalArgumentException();
                }
            }
        }
    }
}
