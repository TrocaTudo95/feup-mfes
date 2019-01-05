package mfes;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Facebook {
  private VDMSet users = SetUtil.set();
  public static User currentUser;
  private VDMSet feed = SetUtil.set();

  public User register(final String n, final User.Date b, final String e, final String p) {

    currentUser = new User(n, Utils.copy(b), e, p);
    users = SetUtil.union(SetUtil.set(currentUser), Utils.copy(users));
    return currentUser;
  }

  public User login(final String e, final String p) {

    for (Iterator iterator_3 = users.iterator(); iterator_3.hasNext(); ) {
      User u = (User) iterator_3.next();
      if (Utils.equals(u.getEmail(), e)) {
        if (Utils.equals(u.getPassword(), p)) {
          IO.print("\n Login Success");
          currentUser = u;
          return currentUser;
        }
      }
    }
    return currentUser;
  }

  public void logout() {

    currentUser = new User();
  }
  
  public VDMSet getUsers() {

	    return Utils.copy(users);
}

  public Boolean hasUser(final User u) {

    return SetUtil.inSet(u, users);
  }

  public User getCurrentUser() {

    return currentUser;
  }

  public VDMSet recommendUsers() {

    VDMSet recommendations = SetUtil.set();
    VDMSet friends = currentUser.getFriends();
    for (Iterator iterator_4 = friends.iterator(); iterator_4.hasNext(); ) {
      User u = (User) iterator_4.next();
      VDMSet friendsOfFriends = u.getFriends();
      for (Iterator iterator_5 = friendsOfFriends.iterator(); iterator_5.hasNext(); ) {
        User f = (User) iterator_5.next();
        if (!(SetUtil.inSet(f, friends))) {
          recommendations = SetUtil.union(SetUtil.set(f), Utils.copy(recommendations));
        }
      }
    }
    return Utils.copy(recommendations);
  }

  public Boolean hasFriend(final User u, final VDMSet us) {

    return SetUtil.inSet(u, us);
  }

  public VDMSet getFeed() {

    VDMSet friends = currentUser.getFriends();
    for (Iterator iterator_6 = friends.iterator(); iterator_6.hasNext(); ) {
      User u = (User) iterator_6.next();
      VDMSet posts = u.getPosts(currentUser);
      feed = SetUtil.union(Utils.copy(posts), Utils.copy(feed));
    }
    return Utils.copy(feed);
  }

  public Facebook() {}

  public String toString() {

    return "Facebook{"
        + "users := "
        + Utils.toString(users)
        + ", currentUser := "
        + Utils.toString(currentUser)
        + ", feed := "
        + Utils.toString(feed)
        + "}";
  }

  public static class Date implements Record {
    public Number day;
    public Number month;
    public Number year;

    public Date(final Number _day, final Number _month, final Number _year) {

      day = _day;
      month = _month;
      year = _year;
    }

    public boolean equals(final Object obj) {

      if (!(obj instanceof Date)) {
        return false;
      }

      Date other = ((Date) obj);

      return (Utils.equals(day, other.day))
          && (Utils.equals(month, other.month))
          && (Utils.equals(year, other.year));
    }

    public int hashCode() {

      return Utils.hashCode(day, month, year);
    }

    public Date copy() {

      return new Date(day, month, year);
    }

    public String toString() {

      return "mk_Facebook`Date" + Utils.formatFields(day, month, year);
    }
  }
}
