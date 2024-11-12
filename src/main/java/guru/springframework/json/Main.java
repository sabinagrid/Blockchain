package guru.springframework.json;

import java.util.ArrayList;
import java.util.List;

public final class Main {

    private Main() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static void main(String[] args) {
        Blockchain blockchain = new Blockchain();
        int maxNrOfMiners = 10;
        int maxNrOfUsers = 5;

        List<User> users = new ArrayList<>();
        for (int i = 1; i <= maxNrOfUsers; i++) {
            User user = new User(blockchain, "User" + i);
            users.add(user);
            user.start();
        }

        List<Miner> miners = new ArrayList<>();
        for (int i = 1; i <= maxNrOfMiners; i++) {
            Miner miner = new Miner(blockchain, i);
            miners.add(miner);
            miner.start();
        }

        waitForThreads(miners);
        waitForThreads(users);

        blockchain.displayBlockchain();
    }

    private static void waitForThreads(List<? extends Thread> threads) {
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
        }
    }
}
