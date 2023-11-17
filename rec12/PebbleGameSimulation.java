/**
 * The game inspired by this https://youtu.be/YzaWAEhFWpw?feature=shared
 * Class containing static method for arge parse and main method
 * Design done following 
 * https://www.cs.rit.edu/~hpb/Lectures/2231/605/Src/11/Race_2.java
 * 
 * The program solves the problem by following
 * - parse args
 * - create a game object with appropriate number of round and number of player
 * - game.createExecGame()
 * - game object creates player thread, number of player thread
 *   == player count provided in cmd + 1 (as master is a player too)
 * - game.execRounds()
 * - game object maintains state of how many have joined and how many have left the round
 *    after completion
 * - game object's join count for current round is synchronized
 *    if all have joined game proceeds to move start flag
 *    other wise it waits for notification from player
 * - meanwhile all player joins through a synchronized method and wait for game start signal
 * - last player to join notifies on all join signal
 * - game object wakes up and signal all waiting player to start
 * - the aboce three steps are through synchronized block in Player.run (line57-63)
 *   this ensures that the game will not notify on startSignal before all 
 *   player has started waiting on it
 * - after game completion, all player go through a synchronized method to report to game
 *   that they are done
 *   this is necessary for the last round, otherwise game object may start reporting before
 *   the round is finished (someone has taken the pebble)
 */
public class PebbleGameSimulation {
    /**
     * 
     */
    static class Player extends Thread {
        private boolean isMaster;
        private int round;
        private int winCount;
        private PebbleGame game;

        /**
         * 
         * @param name
         * @param isMaster
         * @param round
         * @param game
         */
        Player(
            String name, boolean isMaster, int round,
            PebbleGame game) {
            super(name);
            this.isMaster = isMaster;
            this.round = round;
            this.winCount = 0;
            this.game = game;
        }

        /**
         * 
         */
        private void play() {
            try {
                // THIS PART IS IMPORTANT TO ENSURE 
                // GAME DOES NOTE START UNTIL EVERYONE WAIT ON SAME SIGNAL
                // we are synching on gameStartSignal
                // if we can guarantee the game will wait until everyone joins
                // based on another signal
                // this guarantees that until every player joins
                synchronized(game.getStartSignal()) {        
                    // enter into the arena
                    // we will guarantee here that the game will notified
                    // until the last player has not joined
                    game.enterRound();
                    // wait for game to start
                    game.getStartSignal().wait();                    
                    // game has started, let's capture the stone
                    winCount += game.takePebble();
                }
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            // win or loss let's leave the round
            game.leaveRound();
        }
        /**
         * 
         * @return
         */
        public int getWinCount() {
            return this.winCount;
        }
        /**
         * 
         */
        public void run() {
            for (int idx = 0;idx < round;idx++) {
                play();
            }
        }
    }

    /**
     * class is static as it is inner class of PebbleGameSimulation class
     * otherwise we won't be able to refer it from PebbleGameSimulation.main
     * 
     */
    static class PebbleGame {
        private Object startSignal;
        private Object allJoinedSignal;
        private Object allLeftSignal;
        private int round;
        private int playerCount;
        private int pebble;
        private int joined;
        private int left;
        private int attempt_count;

        /**
         * 
         * @param round
         * @param student
         */
        public PebbleGame(int round, int student) {
            this.round = round;
            this.playerCount = student + 1;
            this.startSignal = new Object();
            this.allJoinedSignal = new Object();
            this.allLeftSignal = new Object();
            this.joined = 0;
            this.left = 0;
        }

        /**
         * 
         * @return
         */
        public Object getStartSignal() {
            return this.startSignal;
        }

        /**
         * 
         */
        public synchronized void enterRound() {
            synchronized (this.allJoinedSignal) {
                this.joined++;

                if (this.joined == this.playerCount) {
                    // all players have joined let's notify game
                    // System.out.printf("all %d players have joined\n", this.playerCount);
                    this.allJoinedSignal.notify();
                }
            }
        }

        /**
         * 
         */
        public synchronized void leaveRound() {
            synchronized (this.allLeftSignal) {
                this.left++;
                this.allLeftSignal.notify();
            }
        }

        /**
         * 
         * @return
         */
        public synchronized int takePebble() {
            // take only if there is pebble
            attempt_count++;
            if (this.pebble == 1) {
                // remove the pebble
                this.pebble--;
                return 1;
            }
            // no pebble found
            return 0;
        }

        /**
         * 
         */
        private void executeRounds() {
            for (int idx = 0;idx < round;idx++) {
                // putting pebble in master's palm
                this.pebble = 1;
                this.attempt_count = 0;
                this.left = 0;
                try {
                    // let's wait for all joining
                    synchronized (this.allJoinedSignal) {
                        // System.out.printf("will wait for remaining %d to join round %d\n", this.playerCount - this.joined, idx + 1);
                        // if everyone has joined already, don't wait
                        // because no one will left to notify me
                        if (this.joined < this.playerCount) {
                            this.allJoinedSignal.wait();
                        }
                        // System.out.printf("all have joined in round %d\n", idx + 1);
                    }
                    // System.out.printf("starting round %d\n", idx + 1);
                    // all have joined let's start by notifying all
                    synchronized (this.startSignal) {
                        // we are sure here that no one is manipulating joined
                        // all have joined, otherwise main thread would wait in
                        // above sync block
                        // we can notify now
                        this.joined = 0;
                        this.startSignal.notifyAll();
                    }
                    // let's wait for every one to leave/finish the round
                    // this part is needed 
                    // otherwise for the last round main thread won't be aware if all the thread is left
                    // so, it may report the based on non updated pebble count/win count
                    synchronized (this.allLeftSignal) {
                        // if everyone has already left no need to wait
                        // otherwise no one will wake me up
                        if (this.left < this.playerCount) {
                            // System.out.printf(
                            //     "%d played waiting for %d player exit from round %d\n",
                            //     attempt_count, this.joined, idx + 1);
                            this.allLeftSignal.wait();
                            // System.out.printf(
                            //     "%d played\n",
                            //     attempt_count, this.joined, idx + 1);
                        }
                    }
                    // all left we can move to next round
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

        /**
         * 
         * @param smallReport
         */
        public void createExecGame(boolean smallReport) {
            // create all the players + master
            // we need to store their reference to report win count
            Player playerArray[] = new Player[this.playerCount];

            // create master
            playerArray[0] = new Player("master", true, this.round, this);
            // now create the players
            for (int idx = 1;idx < this.playerCount;idx++) {
                playerArray[idx] = new Player(
                    "student " +  String.valueOf(idx),
                    false, this.round, this);
            }
            // let's start them
            for (int idx = 0;idx < this.playerCount;idx++) {
                playerArray[idx].start();;
            }

            executeRounds();

            int totalPebble = 0;
            for (int idx = 0;idx < this.playerCount;idx++) {
                if (!smallReport) {
                    // report who get what
                    System.out.printf("%s hold on %d pebble\n",
                    playerArray[idx].getName(), playerArray[idx].getWinCount());
                    totalPebble += playerArray[idx].getWinCount();
                }
            }
            if (totalPebble == numRound) {
                System.out.println("All marbles are accounted for is true.\n");
            }
            else {
                System.out.println("All marbles are accounted for is false.\n");
            }
            
        }
    }

    //
    private static int numRound;
    //
    private static int numPlayer;
    //
    private static boolean quiet = false;

    /**
     * 
     * @param args
     */
    private static void parseArgs(String args[]) {
        for (int idx = 0;idx < args.length;) {
            if (args[idx].equals("-nPlayers")) {
                numPlayer = Integer.valueOf(args[idx + 1]);
                idx += 2;
            }
            else if (args[idx].equals("-nRounds")) {
                numRound = Integer.valueOf(args[idx + 1]);
                idx += 2;
            }
            else if (args[idx].equals("-quiet")) {
                quiet = true;
            }
        }
    }

    /**
     * 
     * @param args
     */
    public static void main(String args[]) {
        parseArgs(args);

        // create game object
        PebbleGame game = new PebbleGame(numRound, numPlayer);
        game.createExecGame(quiet);
    }
}