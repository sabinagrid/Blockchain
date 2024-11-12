package guru.springframework.json;

import java.util.ArrayList;
import java.util.List;

public class Main {
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

        for (Miner miner : miners) {
            try {
                miner.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        for (User user : users) {
            try {
                user.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        blockchain.displayBlockchain();
    }
}
