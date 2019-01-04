package mfes;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class UserTest {
  private User u = new User("Sofia", new User.Date(1L, 1L, 2019L), "sofia@gmail.com", "123");
  private String c = "I am a Post";
  private String p = "public";
  private User u2 = new User("Joao", new User.Date(1L, 1L, 2019L), "joao@gmail.com", "123");
  private User u3 = new User("rita", new User.Date(1L, 1L, 2019L), "jose@gmail.com", "123");

  private void assertTrue(final Boolean cond) {

    return;
  }

  private void verifyUser() {

    IO.print("\nTest User");
    assertTrue(Utils.equals(u.getName(), "Sofia"));
    assertTrue(Utils.equals(u.getBirthday(), new User.Date(1L, 1L, 2019L)));
    assertTrue(Utils.equals(u.getEmail(), "sofia@gmail.com"));
    assertTrue(Utils.equals(u.getPassword(), "123"));
  }

  private void addPost() {

    IO.print("\nTest Add Post");
    assertTrue(Utils.equals(u.hasPost(u.addPost(c, p)), true));
  }

  private void removePost() {

    Post p_1 = u.addPost(c, p);
    assertTrue(Utils.equals(u.hasPost(p_1), true));
    u.removePost(p_1);
    assertTrue(Utils.equals(u.hasPost(p_1), false));
  }

  private void sendFriendRequest() {

    IO.print("\nSend Friend Request test");
    assertTrue(Utils.equals(u.getNumberOfPendentRequests(), 0L));
    assertTrue(Utils.equals(u.hasFriendRequest(u2), false));
    u2.sendFriendRequest(u);
    assertTrue(Utils.equals(u.getNumberOfPendentRequests(), 1L));
    assertTrue(Utils.equals(u.hasFriendRequest(u2), true));
  }

  private void acceptFriendRequest() {

    assertTrue(Utils.equals(u.hasFriendRequest(u2), false));
    u2.sendFriendRequest(u);
    assertTrue(Utils.equals(u.hasFriendRequest(u2), true));
    u.acceptRequest(u2);
    assertTrue(Utils.equals(u.hasFriendRequest(u2), false));
    assertTrue(Utils.equals(u.getNumberOfFriends(), 1L));
    assertTrue(Utils.equals(u.hasFriend(u2), true));
    assertTrue(Utils.equals(u2.hasFriend(u), true));
    assertTrue(Utils.equals(u2.getNumberOfFriends(), 1L));
    assertTrue(Utils.equals(u.getNumberOfPendentRequests(), 0L));
  }

  private void rejectFriendRequest() {

    u2.sendFriendRequest(u);
    assertTrue(Utils.equals(u.getNumberOfPendentRequests(), 1L));
    assertTrue(Utils.equals(u2.getNumberOfFriends(), 0L));
    u.rejectRequest(u2);
    assertTrue(Utils.equals(u.getNumberOfPendentRequests(), 0L));
    assertTrue(Utils.equals(u2.getNumberOfFriends(), 0L));
  }

  private void removeFriend() {

    u2.sendFriendRequest(u);
    u.acceptRequest(u2);
    assertTrue(Utils.equals(u.hasFriend(u2), true));
    assertTrue(Utils.equals(u2.hasFriend(u), true));
    u.unfriend(u2);
    assertTrue(Utils.equals(u.hasFriend(u2), false));
    assertTrue(Utils.equals(u2.hasFriend(u), false));
  }

  public static void run() {

    new UserTest().verifyUser();
    new UserTest().addPost();
    new UserTest().removePost();
    new UserTest().sendFriendRequest();
    new UserTest().acceptFriendRequest();
    new UserTest().removeFriend();
    new UserTest().rejectFriendRequest();
  }

  public UserTest() {}

  public String toString() {

    return "UserTest{"
        + "u := "
        + Utils.toString(u)
        + ", c := "
        + Utils.toString(c)
        + ", p := "
        + Utils.toString(p)
        + ", u2 := "
        + Utils.toString(u2)
        + ", u3 := "
        + Utils.toString(u3)
        + "}";
  }
}
