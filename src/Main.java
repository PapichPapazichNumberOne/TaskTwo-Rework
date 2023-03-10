public class Main {
    public static void main(String[] args) {
        IHouse house = new House();
        house.addRoom("Living Room");
        house.addRoom("Bedroom");
        house.addRoom("Kitchen");
        house.addRoom("Study");
        house.addRoom("Del");
        house.addRoom("Bar");

        IRobot robot = new Robot(house);

        IUserInput userInput = new UserInput();

        String input;
        while (true) {
            input = userInput.getInput("Введите clean, чтобы убрать комнату  " + house.getRooms() +  "или 'exit' чтобы выйти: ");
            if (input.equalsIgnoreCase("exit")) {
                break;
            } else if (input.equalsIgnoreCase("clean")) {
                input = userInput.getInput("Введите какую комнату убрать "+ house.getRooms() +"или напиши 'random': ");
                if (input.equalsIgnoreCase("random")) {
                    String roomToClean = userInput.getRandomRoom(house.getRooms());
                    robot.moveToRoom(roomToClean);
                    robot.cleanRoom(roomToClean);
                    System.out.println("Cleaned " + roomToClean);
                } else {
                    if (house.getRooms().contains(input)) {
                        robot.moveToRoom(input);
                        robot.cleanRoom(input);
                        System.out.println("Cleaned " + input);
                    } else {
                        System.out.println("Неверная комната. Попробуйте снова.");
                    }
                }
            } else {
                System.out.println("Неверный ввод. Пожалуйста, попробуйте снова.");
            }
        }
    }
}