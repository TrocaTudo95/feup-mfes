package mfes;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class User {
  private VDMSet posts = SetUtil.set();
  private VDMSet friends = SetUtil.set();
  private VDMSet pendentFriendRequests = SetUtil.set();
  private VDMSet family = SetUtil.set();
  private Date birthday;
  private String email;
  private String name;
  private String password;

  public void cg_init_User_1(final String n, final Date b, final String e, final String p) {

    name = n;
    birthday = Utils.copy(b);
    email = e;
    password = p;
    return;
  }

  public User(final String n, final Date b, final String e, final String p) {

    cg_init_User_1(n, Utils.copy(b), e, p);
  }

  public String getName() {

    return name;
  }

  public Date getBirthday() {

    return Utils.copy(birthday);
  }

  public String getEmail() {

    return email;
  }

  public String getPassword() {

    return password;
  }

  public VDMSet getFriends() {

    return Utils.copy(friends);
  }

  public VDMSet getPosts(final User u) {

    VDMSet res = SetUtil.set();
    for (Iterator iterator_7 = posts.iterator(); iterator_7.hasNext(); ) {
      Post p = (Post) iterator_7.next();
      if (p.isAvailable(u)) {
        res = SetUtil.union(SetUtil.set(p), Utils.copy(res));
      }
    }
    return Utils.copy(res);
  }

  public Post addPost(final String c, final String p) {

    Post pst = new Post(c, p, this);
    posts = SetUtil.union(SetUtil.set(pst), Utils.copy(posts));
    return pst;
  }

  public void removePost(final Post p) {

    posts = SetUtil.diff(Utils.copy(posts), SetUtil.set(p));
  }

  public void acceptRequest(final User f) {

    friends = SetUtil.union(SetUtil.set(f), Utils.copy(friends));
    f.addFriend(this);
    pendentFriendRequests = SetUtil.diff(Utils.copy(pendentFriendRequests), SetUtil.set(f));
  }

  public void rejectRequest(final User f) {

    pendentFriendRequests = SetUtil.diff(Utils.copy(pendentFriendRequests), SetUtil.set(f));
  }

  public void sendFriendRequest(final User f) {

    f.addFriendRequest(this);
  }

  public void addFriend(final User f) {

    friends = SetUtil.union(SetUtil.set(f), Utils.copy(friends));
  }

  public void addFriendRequest(final User f) {

    pendentFriendRequests = SetUtil.union(SetUtil.set(f), Utils.copy(pendentFriendRequests));
  }

  public void unfriend(final User f) {

    removeFriend(f);
    f.removeFriend(this);
  }

  public void removeFriend(final User f) {

    friends = SetUtil.diff(Utils.copy(friends), SetUtil.set(f));
  }

  public Number getNumberOfFriends() {

    return friends.size();
  }

  public Number getNumberOfPendentRequests() {

    return pendentFriendRequests.size();
  }

  public Boolean hasPost(final Post p) {

    return SetUtil.inSet(p, posts);
  }

  public Boolean hasFriend(final User u) {

    return SetUtil.inSet(u, friends);
  }

  public Boolean hasFriendRequest(final User u) {

    return SetUtil.inSet(u, pendentFriendRequests);
  }

  public void addToFamily(final User u) {

    family = SetUtil.union(SetUtil.set(u), Utils.copy(family));
  }

  public String getPermission(final User u) {

    if (Utils.equals(u, this)) {
      return "family";
    }

    if (SetUtil.inSet(u, family)) {
      return "family";

    } else {
      return "public";
    }
  }

  public User() {}

  public String toString() {

    return "User{"
        + ", email := "
        + Utils.toString(email)
        + ", name := "
        + Utils.toString(name)
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
    
    public Date(String date) {
    	String[] date1 = date.split("/");
        day =  Integer.parseInt(date1[0]);
        month = Integer.parseInt(date1[1]);;
        year = Integer.parseInt(date1[2]);
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

      return "mk_User`Date" + Utils.formatFields(day, month, year);
    }
  }

  public static Boolean inv_Date(final Date d) {

    Boolean andResult_10 = false;

    if (d.year.longValue() > 2018L) {
      Boolean andResult_11 = false;

      if (d.month.longValue() <= 12L) {
        if (d.day.longValue() <= 31L) {
          andResult_11 = true;
        }
      }

      if (andResult_11) {
        andResult_10 = true;
      }
    }

    return andResult_10;
  }
}
