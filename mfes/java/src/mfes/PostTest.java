package mfes;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class PostTest {
  private User u = new User("Sofia", new User.Date(1L, 1L, 2019L), "sofia@gmail.com", "123");
  private Post pp = new Post("I am a public post!", "public", u);
  private Post fp = new Post("I am a family post!", "family", u);
  private String cc = "I am a comment!";
  private String cc2 = "hi!";
  private User u2 = new User("Joao", new User.Date(1L, 1L, 2019L), "joao@gmail.com", "123");
  private String r = "love";

  private void assertTrue(final Boolean cond) {

    return;
  }

  private void like() {

    IO.print("\nTest Like and Dislike");
    assertTrue(Utils.empty(pp.hasLike(u, r)));
    pp.like(u, r);
    assertTrue(Utils.equals(pp.hasLike(u, r), MapUtil.map(new Maplet(u, r))));
    pp.dislike(u);
    assertTrue(Utils.empty(pp.hasLike(u, r)));
  }

  private void add_comment() {

    IO.print("\nTest Add Stranger Comment");
    assertTrue(Utils.equals(pp.addComment(cc, u2), false));
    IO.print("\nTest Add Friend Comment - Public Post");
    u2.sendFriendRequest(u);
    u.acceptRequest(u2);
    assertTrue(pp.addComment(cc, u2));
    IO.print("\nTest Add Friend Comment - Family Post");
    assertTrue(Utils.equals(fp.addComment(cc, u2), false));
    IO.print("\nTest Add Family Comment - Family Post");
    u.addToFamily(u2);
    assertTrue(fp.addComment(cc, u2));
    IO.print("\nTest Add Own Comment");
    assertTrue(fp.addComment(cc, u));
    IO.print("\nTest Remove Comment");
    assertTrue(Utils.equals(pp.getCommentsNumber(), 1L));
    Comment iotaExp_2 = null;
    Long iotaCounter_2 = 0L;
    VDMSet set_2 = pp.getComments();
    for (Iterator iterator_2 = set_2.iterator(); iterator_2.hasNext(); ) {
      Comment c = ((Comment) iterator_2.next());
      if (Utils.equals(c.getUser(), u2)) {
        iotaCounter_2++;
        if (iotaCounter_2.longValue() > 1L) {
          throw new RuntimeException("Iota selects more than one result");
        } else {
          iotaExp_2 = c;
        }
      }
    }
    if (Utils.equals(iotaCounter_2, 0L)) {
      throw new RuntimeException("Iota selects more than one result");
    }

    pp.removeComment(iotaExp_2);

    assertTrue(Utils.equals(pp.getCommentsNumber(), 0L));
  }

  private void editPost() {

    assertTrue(Utils.equals(pp.getContent(), "I am a public post!"));
    pp.editPost("hello");
    assertTrue(Utils.equals(pp.getContent(), "hello"));
  }

  private void editComment() {

    u2.sendFriendRequest(u);
    u.acceptRequest(u2);
    assertTrue(Utils.equals(pp.addComment("hi", u2), true));
    assertTrue(Utils.equals(pp.getCommentFromUser(u2).getContent(), "hi"));
    pp.getCommentFromUser(u2).editComment("bye");
    assertTrue(Utils.equals(pp.getCommentFromUser(u2).getContent(), "bye"));
  }

  public static void run() {

    new PostTest().like();
    new PostTest().add_comment();
    new PostTest().editPost();
    new PostTest().editComment();
  }

  public PostTest() {}

  public String toString() {

    return "PostTest{"
        + "u := "
        + Utils.toString(u)
        + ", pp := "
        + Utils.toString(pp)
        + ", fp := "
        + Utils.toString(fp)
        + ", cc := "
        + Utils.toString(cc)
        + ", cc2 := "
        + Utils.toString(cc2)
        + ", u2 := "
        + Utils.toString(u2)
        + ", r := "
        + Utils.toString(r)
        + "}";
  }
}
