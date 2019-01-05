package mfes;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Post {
  public static final VDMSet Reactions =
      SetUtil.set("sad", "love", "like", "angry", "surprise", "laugh");
  public static final VDMSet Permissions = SetUtil.set("public", "family");
  private User owner;
  private VDMSet comments = SetUtil.set();
  private String permission;
  private VDMMap likes = MapUtil.map();
  private String content;

  public void cg_init_Post_1(final String c, final String p, final User o) {

    content = c;
    permission = p;
    owner = o;
    return;
  }

  public Post(final String c, final String p, final User o) {

    cg_init_Post_1(c, p, o);
  }

  public VDMSet getComments() {

    return Utils.copy(comments);
  }

  public Boolean addComment(final String c, final User u) {

    Comment comment = new Comment(c, u);
    if (isAvailable(u)) {
      comments = SetUtil.union(SetUtil.set(comment), Utils.copy(comments));
      return true;

    } else {
      return false;
    }
  }

  public void removeComment(final Comment c) {

    if (SetUtil.inSet(c, comments)) {
      comments = SetUtil.diff(Utils.copy(comments), SetUtil.set(c));
    }
  }

  public void like(final User u, final String r) {

    likes = MapUtil.override(MapUtil.map(new Maplet(u, r)), Utils.copy(likes));
  }

  public void dislike(final User u) {

    likes = MapUtil.domResBy(SetUtil.set(u), Utils.copy(likes));
  }

  public VDMMap hasLike(final User u, final String r) {

    return MapUtil.domResTo(SetUtil.set(u), Utils.copy(likes));
  }

  public void editPost(final String c) {

    content = c;
  }

  public Comment getCommentFromUser(final User u) {

    Comment iotaExp_1 = null;
    Long iotaCounter_1 = 0L;
    VDMSet set_1 = Utils.copy(comments);
    for (Iterator iterator_1 = set_1.iterator(); iterator_1.hasNext(); ) {
      Comment c = ((Comment) iterator_1.next());
      if (Utils.equals(u, c.getUser())) {
        iotaCounter_1++;
        if (iotaCounter_1.longValue() > 1L) {
          throw new RuntimeException("Iota selects more than one result");
        } else {
          iotaExp_1 = c;
        }
      }
    }
    if (Utils.equals(iotaCounter_1, 0L)) {
      throw new RuntimeException("Iota selects more than one result");
    }

    Comment comment = iotaExp_1;
    return comment;
  }

  public String getContent() {

    return content;
  }

  public Boolean isAvailable(final User u) {

    Boolean andResult_5 = false;

    if (!(owner.hasFriend(u))) {
      if (!(Utils.equals(owner, u))) {
        andResult_5 = true;
      }
    }

    if (andResult_5) {
      return false;
    }

    if (Utils.equals(permission, "public")) {
      return true;

    } else if (Utils.equals(owner.getPermission(u), "family")) {
      return true;

    } else {
      return false;
    }
  }

  public Number getCommentsNumber() {

    return comments.size();
  }

  public Post() {}

  public String toString() {

    return "- " + content + "\n";
  }
}
