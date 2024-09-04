public class Player {
    public static void main(String[] args) {
    }
    private final String playerName;
    private int stamina = 75;
    private Position[] positions;
    private int skillRating = 80;
    public Player() {
        this.playerName = "Lionel Messi";
        this.positions = new Position[] {Position.FORWARD};
        this.skillRating = 100;
        System.out.println("Skill rating: Excellent");
    }
    public Player(String playerName, Position[] positions) {
        this.playerName = playerName;
        if (positions.length > 0) {
            this.positions = positions;
        } else {
            this.positions = new Position[] {Position.MIDFIELDER};
        }
        if (this.skillRating >= 90 && this.skillRating <= 100) {
            System.out.println("Skill rating: Excellent");
        } else if (this.skillRating >= 80 && this.skillRating <= 89) {
            System.out.println("Skill rating: Great");
        } else if (this.skillRating >= 70 && this.skillRating <= 79) {
            System.out.println("Skill rating: Very Good");
        } else if (this.skillRating >= 60 && this.skillRating <= 69) {
            System.out.println("Skill rating: Good");
        } else if (this.skillRating >= 50 && this.skillRating <= 59) {
            System.out.println("Skill rating: Fine");
        } else if (this.skillRating >= 40 && this.skillRating <= 49) {
            System.out.println("Skill rating: Bad");
        }
    }
    public Player(String playerName, int stamina, Position[] positions, int skillRating) {
        this.playerName = playerName;
        if (stamina >= 0 && stamina <= 100) {
            this.stamina = stamina;
        }
        if (positions.length > 0) {
            this.positions = positions;
        } else {
            this.positions = new Position[] {Position.MIDFIELDER};
        }
        if (skillRating >= 40 && skillRating <= 100) {
            this.skillRating = skillRating;
        }
        if (this.skillRating >= 90 && this.skillRating <= 100) {
            System.out.println("Skill rating: Excellent");
        } else if (this.skillRating >= 80 && this.skillRating <= 89) {
            System.out.println("Skill rating: Great");
        } else if (this.skillRating >= 70 && this.skillRating <= 79) {
            System.out.println("Skill rating: Very Good");
        } else if (this.skillRating >= 60 && this.skillRating <= 69) {
            System.out.println("Skill rating: Good");
        } else if (this.skillRating >= 50 && this.skillRating <= 59) {
            System.out.println("Skill rating: Fine");
        } else if (this.skillRating >= 40 && this.skillRating <= 49) {
            System.out.println("Skill rating: Bad");
        }
    }
    public boolean isTrainable() {
        if (this.skillRating >= 50 && this.skillRating <= 89) {
            return true;
        }
        return false;
    }
    public Position preferredPosition() {
        return this.positions[0];
    }
    public boolean canPlayAs(Position position) {
        for (int i = 0; i < this.positions.length; i++) {
            if (position.equals(this.positions[i])) {
                return true;
            }

        }
        return false;

    }
    public String toString() {
        String playerstring = String.format("<%s,%d,%s,%d,%b>", playerName, stamina, preferredPosition(),
            skillRating, isTrainable());
        return playerstring;
    }
    public int getSkillRating() {
        return skillRating;
    }
    public int getStamina() {
        return stamina;
    }
    public Position[] getPositions() {
        return positions;
    }
    public void setStamina(int num) {
        this.stamina = num;
    }

}