package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.NoResultException;
import java.sql.SQLException;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User arnold = new User("Arnold", "Swarznegger", "arni@gmail.com");
      User bruce = new User("Bruce", "Wayne", "ibanez@ya.ru");
      User joe = new User("Joe", "Cocker", "lalala@mail.com");
      User rachel = new User("Rachel", "Green", "ross@gmail.com");

      Car shevrole = new Car("bezPonatia", 23);
      Car lexus = new Car("krasivaya", 34);
      Car vw = new Car("Jook", 54);
      Car batmobile = new Car("a1", 67);

      userService.add(arnold.setCar(shevrole).setUser(arnold));
      userService.add(bruce.setCar(lexus).setUser(bruce));
      userService.add(joe.setCar(vw).setUser(joe));
      userService.add(rachel.setCar(batmobile).setUser(rachel));

      for (User user : userService.listUsers()) {
         System.out.println(user + " " + user.getCar());
      }

      System.out.println(userService.getUserByCar("krasivaya", 34));

      try {
         User notFoundUser = userService.getUserByCar("niva", 11);
      } catch (NoResultException e) {
         System.out.println("User not found");
      }

      context.close();
   }
}
