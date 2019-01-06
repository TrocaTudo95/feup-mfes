package mfes;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Comment {
  private String content;
  private User user;

  public void cg_init_Comment_1(final String c, final User u) {

    content = c;
    user = u;
    return;
  }

  public Comment(final String c, final User u) {

    cg_init_Comment_1(c, u);
  }

  public void editComment(final String c) {

    content = c;
  }

  public User getUser() {

    return user;
  }

  public String getContent() {

    return content;
  }

  public Comment() {}

  public String toString() {

    return Utils.toString(content) + " - " +  Utils.toString(user) + "\n";
  }
}
