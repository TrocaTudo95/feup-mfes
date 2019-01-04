package mfes;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class FacebookTest {
  private User u = new User("Sofia", new User.Date(1L, 1L, 2019L), "sofia@gmail.com", "123");
  private User u2 = new User("Joao", new User.Date(1L, 1L, 2019L), "joao@gmail.com", "123");
  private User u3 = new User("rita", new User.Date(1L, 1L, 2019L), "jose@gmail.com", "123");
  private User u4 = new User("mario", new User.Date(1L, 1L, 2019L), "jose@gmail.com", "123");
  private User u5 = new User("teresa", new User.Date(1L, 1L, 2019L), "jose@gmail.com", "123");
  private Facebook face = new Facebook();

  private void assertTrue(final Boolean cond) {

    return;
  }

  private void createUser() {

    IO.print("\nRegister Test");
    u2 = face.register("Sofia", new User.Date(1L, 1L, 2019L), "sofia@gmail.com", "123");
    u = face.login("sofia@gmail.com", "123");
    assertTrue(Utils.equals(u, u2));
    assertTrue(Utils.equals(face.getCurrentUser(), u));
    assertTrue(Utils.equals(face.hasUser(u2), true));
  }

  private void logout() {

    IO.print("\nLogout Test");
    u2 = face.register("Sofia", new User.Date(1L, 1L, 2019L), "sofia@gmail.com", "123");
    assertTrue(Utils.equals(face.hasUser(u2), true));
    assertTrue(Utils.equals(face.getCurrentUser(), u2));
    face.logout();
    assertTrue(Utils.equals(face.hasUser(u2), true));
    assertTrue(!(Utils.equals(face.getCurrentUser(), u2)));
  }

  private void friends_recommendations() {

    IO.print("\nFriend recommendations test");
    u2 = face.register("Sofia", new User.Date(1L, 1L, 2019L), "sofia@gmail.com", "123");
    u2.sendFriendRequest(u);
    u2.sendFriendRequest(u3);
    u.acceptRequest(u2);
    u3.acceptRequest(u2);
    u3.sendFriendRequest(u4);
    u4.acceptRequest(u3);
    u.sendFriendRequest(u5);
    u5.acceptRequest(u);
    assertTrue(Utils.equals(face.hasFriend(u5, face.recommendUsers()), true));
    assertTrue(Utils.equals(face.hasFriend(u4, face.recommendUsers()), true));
    assertTrue(Utils.equals(face.hasFriend(u, face.recommendUsers()), false));
    assertTrue(Utils.equals(face.hasFriend(u3, face.recommendUsers()), false));
  }

  private void feed() {

    Post p1 = u2.addPost("I am a public post", "public");
    Post p2 = u2.addPost("I am a family only post", "family");
    IO.print("\nGet Empty Feed Test");
    u = face.register("Sofia", new User.Date(1L, 1L, 2019L), "sofia@gmail.com", "123");
    u = face.login("sofi@gmail.com", "123");
    assertTrue(Utils.empty(face.getFeed()));
    IO.print("\nGet Friends Feed Test");
    u2.sendFriendRequest(u);
    u.acceptRequest(u2);
    assertTrue(Utils.equals(face.getFeed(), SetUtil.set(p1)));
    IO.print("\nGet Family Feed Test");
    u2.addToFamily(u);
    assertTrue(Utils.equals(face.getFeed(), SetUtil.set(p1, p2)));
  }

  public static void run() {

    new FacebookTest().createUser();
    new FacebookTest().logout();
    new FacebookTest().friends_recommendations();
    new FacebookTest().feed();
  }

  public FacebookTest() {}

  public String toString() {

    return "FacebookTest{"
        + "u := "
        + Utils.toString(u)
        + ", u2 := "
        + Utils.toString(u2)
        + ", u3 := "
        + Utils.toString(u3)
        + ", u4 := "
        + Utils.toString(u4)
        + ", u5 := "
        + Utils.toString(u5)
        + ", face := "
        + Utils.toString(face)
        + "}";
  }
}
