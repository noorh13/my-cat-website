public class HealthyEating {
    public static void main(String[] args) {
        Food[] meal1 = followRecipe("PROTEIN FRUIT VEGETABLE VEGETABLE GRAIN");
        Food[] meal2 = followRecipe("JUNK_FOOD GRAIN JUNK_FOOD JUNK_FOOD JUNK_FOOD JUNK_FOOD");

        System.out.println("Meal 1: ");

        mealAnalyzer(meal1);

        System.out.println();

        System.out.println("Meal 2: ");

        mealAnalyzer(meal2);

        System.out.println();

        healthyChoice(meal1, meal2);



    }
    public static Food[] mealPrep(int numFoods) {
        Food[] foodArray = new Food[numFoods];
        Food[] foodlist = Food.values();
        for (int i = 0; i < numFoods; i++) {
            foodArray[i] = foodlist[(int) (Math.random() * foodlist.length)];
        }
        return foodArray;
    }
    public static Food[] followRecipe(String recipe) {
        String[] splitrecipe = recipe.split(" ");
        Food[] foodArray = new Food[splitrecipe.length];
        for (int i = 0; i < splitrecipe.length; i++) {
            foodArray[i] = Food.valueOf(splitrecipe[i]);
        }
        return foodArray;

    }
    public static void mealAnalyzer(Food[] foodArray) {
        System.out.println("The following types of food are in your meal:");
        int junkfood = 0;
        int dairy = 0;
        int grain = 0;
        int protein = 0;
        int fruit = 0;
        int vegetable = 0;
        for (int i = 0; i < foodArray.length; i++) {
            switch (foodArray[i]) {
            case JUNK_FOOD:
                junkfood += 1;
                break;
            case DAIRY:
                dairy += 1;
                break;
            case GRAIN:
                grain += 1;
                break;
            case PROTEIN:
                protein += 1;
                break;
            case FRUIT:
                fruit += 1;
                break;
            case VEGETABLE:
                vegetable += 1;
                break;
            default:
                break;
            }
        }
        System.out.println("JUNK_FOOD " + junkfood);
        System.out.println("DAIRY " + dairy);
        System.out.println("GRAIN " + grain);
        System.out.println("PROTEIN " + protein);
        System.out.println("FRUIT " + fruit);
        System.out.println("VEGETABLE " + vegetable);
    }
    public static void healthyChoice(Food[] meal1, Food[] meal2) {
        int meal1score = 0;
        int meal2score = 0;
        for (int i = 0; i < meal1.length; i++) {
            meal1score += meal1[i].ordinal();
        }
        for (int i = 0; i < meal2.length; i++) {
            meal2score += meal2[i].ordinal();
        }
        if (meal1score > meal2score) {
            System.out.println("The first meal is the healthier choice with a score of "
                + meal1score + ".");
        } else if (meal2score > meal1score) {
            System.out.println("The second meal is the healthier choice with a score of "
                + meal2score + ".");
        } else {
            System.out.println("The two meals are equally healthy with scores of "
                + meal2score + ".");
        }
    }
}