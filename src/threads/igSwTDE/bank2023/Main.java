package threads.igSwTDE.bank2023;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Account accountA = new Account(5000f);
        Account accountB = new Account(10000f);

        Bank bank = new Bank();

        System.out.println("Saldo iniziale A: " + accountA.getAmount());
        System.out.println("Saldo iniziale B: " + accountB.getAmount());

        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            float loan = 100 + random.nextFloat() * (3000 - 100);   //Random lend between 100 and 3000
            System.out.print("Tentativo " + (i + 1) + ": prestito di " + String.format("%.2f", loan) + " da A a B - ");

            try {
                bank.lend(accountA, accountB, loan);
                // Lend accepted
                System.out.println("Operazione riuscita. Saldo A: " + String.format("%.2f", accountA.getAmount())
                        + ", Saldo B: " + String.format("%.2f", accountB.getAmount()));
            } catch (IllegalArgumentException e) {
                // Lend failed
                System.out.println("Operazione fallita. Saldo A: " + String.format("%.2f", accountA.getAmount())
                        + ", Saldo B: " + String.format("%.2f", accountB.getAmount()));
            }
        }

        System.out.println("\nSaldo finale Account A: " + String.format("%.2f", accountA.getAmount()));
        System.out.println("Saldo finale Account B: " + String.format("%.2f", accountB.getAmount()));
    }
}
