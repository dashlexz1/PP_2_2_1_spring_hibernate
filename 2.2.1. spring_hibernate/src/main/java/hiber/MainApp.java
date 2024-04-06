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

      userService.add(new User("User1", "Lastname1", "user1@mail.ru", new Car("BMW", 1)));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru", new Car("SKODA", 2)));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru", new Car("MAZDA", 3)));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru", new Car("LADA", 4)));
      userService.add(new User("User5", "Lastname5", "user5@mail.ru", null));
      userService.add(new User("User6", "Lastname7", "user7@mail.ru"));

      List<User> users = userService.listUsers();
      for (User user : users) {
         if(user.getCar() != null){
            System.out.println(user);
         } else {
            StringBuilder buffer = new StringBuilder();
            buffer.append("Id = ");
            buffer.append(user.getId());
            buffer.append(" name = ");
            buffer.append(user.getFirstName());
            buffer.append(" lastname = ");
            buffer.append(user.getLastName());
            buffer.append(" email =");
            buffer.append(user.getEmail());
            System.out.println(buffer);
         }
      }
      User byUserCar = userService.getUserByCarModelAndSeries("BMW", 1);
      System.out.println(byUserCar);

      context.close();
   }
}
