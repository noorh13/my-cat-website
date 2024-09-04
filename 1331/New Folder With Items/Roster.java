public class Roster {
    public static void main(String[] args) {
    }
    private Player[] players;
    private int size;
    public Roster(Player[] players) {
        this.players = players;
        for (int i = 0; i < players.length; i++) {
            if (players[i] != null) {
                this.size += 1;
            }
        }

    }
    public Roster() {
        this.players = new Player[4];
        this.size = 0;
    }
    public Player signPlayer(int index, Player player) {
        if (player == null || index >= this.players.length || index < 0) {
            System.out.println("Cannot add a player to this spot on the roster.");
            return null;
        } else if (this.players[index] == null) {
            this.players[index] = player;
            String playerstring = String.format("Signed: %s", player);
            System.out.println(playerstring);
            this.size += 1;
            return null;
        } else {
            Player oldplayer = this.players[index];
            String playerstring = String.format("Replaced: %s", this.players[index]);
            System.out.println(playerstring);
            this.players[index] = player;
            return oldplayer;
        }
    }
    public Player transferPlayer(int index) {
        if (index >= this.players.length || index < 0 || this.players[index] == null) {
            System.out.println("There was no player to transfer!");
            return null;
        } else {
            Player oldplayer = this.players[index];
            String playerstring = String.format("Transferred: %s", oldplayer.toString());
            System.out.println(playerstring);
            this.players[index] = null;
            this.size -= 1;
            return oldplayer;
        }

    }
    public void showBestPlayers(int skillrating) {
        for (int i = 0; i < this.players.length; i++) {
            if (this.players[i] != null) {
                if (this.players[i].getSkillRating() > skillrating) {
                    System.out.println(this.players[i].toString());
                }
            }
        }
    }
    public void trainAllPlayers() {
        boolean unTrainable = true;
        for (int i = 0; i < this.players.length; i++) {
            if (this.players[i] != null) {
                if (this.players[i].getSkillRating() != 100) {
                    unTrainable = false;
                }
            }
        }
        if (!unTrainable) {
            for (int i = 0; i < this.players.length; i++) {
                if (this.players[i] != null) {
                    if (this.players[i].isTrainable()) {
                        String oldPlayer = this.players[i].toString();
                        int trainedNum = (int) (Math.random() * 10) + 1;
                        int newSkillLevel = (this.players[i].getSkillRating()) + trainedNum;
                        if (newSkillLevel > 100) {
                            newSkillLevel = 100;
                        }
                        String playTrained = String.format("Trained to %d: %s", newSkillLevel,
                            oldPlayer);
                        System.out.println(playTrained);
                    }
                }
            }
        } else {
            System.out.println("There were no players to train.");
        }
    }
    public void play(int index, Position position) {
        if (!(index >= this.players.length || index < 0 || this.players[index] == null)) {
            if (this.players[index].preferredPosition() == position) {
                int stamina = this.players[index].getStamina();
                int randomNum = (int) (Math.random() * 5) + 1;
                stamina -= randomNum;
                if (stamina < 0) {
                    stamina = 0;
                }
                this.players[index].setStamina(stamina);
                String playerInfo = String.format("Played: %s", this.players[index].toString());
                System.out.println(playerInfo);
            } else if (!(this.players[index].preferredPosition() == position)) {
                boolean flag = false;
                for (int i = 0; i < this.players[index].getPositions().length; i++) {
                    if (position == this.players[index].getPositions()[i]) {
                        int stamina = this.players[index].getStamina();
                        int randomNum = (int) (Math.random() * 6) + 5;
                        stamina -= randomNum;
                        if (stamina < 0) {
                            stamina = 0;
                        }
                        this.players[index].setStamina(stamina);
                        String playerInfo = String.format("Played: %s", this.players[index].toString());
                        System.out.println(playerInfo);
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    String noPosition = String.format("This player cannot be played in position %s.",
                        position);
                    System.out.println(noPosition);
                }
            }
        } else {
            System.out.println("Cannot play the player in this spot.");
        }
        return;
    }
    public String toString() {
        if (this.size != 0) {
            String firstLine = String.format("There are %d players on Java FC.\n", this.size);
            for (int i = 0; i < this.players.length; i++) {
                if (this.players[i] != null) {
                    firstLine += this.players[i].toString() + "\n";
                }
            }
            return firstLine.trim();
        } else {
            String noPlayers = "The team has no players!";
            return noPlayers.trim();
        }

    }
    public int getSize() {
        return size;
    }

}