package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        // создаем и связвыем объекты
        User user1 = new User("Алексей", "Алферов", "alexey@mail.ru");
        Car car1 = new Car("audi", 10);
        user1.setCarUser(car1);
        userService.add(user1);

        User user2 = new User("Борис", "Баринов", "boris@mail.ru");
        Car car2 = new Car("bmw", 11);
        user2.setCarUser(car2);
        userService.add(user2);

        User user3 = new User("Владимир", "Вишневский", "vladimir@mail.ru");
        Car car3 = new Car("mercedes", 12);
        user3.setCarUser(car3);
        userService.add(user3);

        User user4 = new User("Геннадий", "Георгиев", "gena@mail.ru");
        Car car4 = new Car("honda", 13);
        user4.setCarUser(car4);
        userService.add(user4);

        //тест поиска
        User userAndCar = userService.getUserByCar("honda", 13);
        if (userAndCar != null) {
            System.out.println("Пользователь найден= " + "имя:" + userAndCar.getFirstName() +
                    " " + "фамилия: " + userAndCar.getLastName());
        } else {
            System.out.println("Пользователь с такой машиной не найден");
        }

        // список юзеров и их машин
        List<User> users = userService.listUsers();
        for (User u : users) {
            System.out.println("Id = " + u.getId());
            System.out.println("First Name = " + u.getFirstName());
            System.out.println("Last Name = " + u.getLastName());
            System.out.println("Email = " + u.getEmail());
            if (u.getCarUser() != null) {
                System.out.println("Car: " + u.getCarUser().getModel() + " Series: " + u.getCarUser().getSeries());
            }
            System.out.println();
        }

        context.close();
    }
}
