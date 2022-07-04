import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Salma
 */
public class Functions {

  Connection connect;
  PreparedStatement statement;
  ResultSet result;
  static int d = 9;

  public String case0(String username, String pass) {
    ConnectDB db = new ConnectDB();
    connect = db.connectDB();
    try {
      String sql = "SELECT * FROM client WHERE cid = ? and pass = ?";
      statement = connect.prepareStatement(sql);
      statement.setString(1, username);
      statement.setString(2, pass);
      result = statement.executeQuery();
      if (result.next()) {
        //if entered data is correct,, this area would be executed after.
        return "okay";
      } else {
        return "not okay";
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public String case1(String USER_ID, String pass, String fname, String lname) {

    ConnectDB db = new ConnectDB();
    connect = db.connectDB();

    try {
      String sql1 = " SELECT * FROM client ";
      statement = connect.prepareStatement(sql1);
      ResultSet rs = statement.executeQuery(sql1);
      while (rs.next()) {
        String ID = rs.getString("cid");
        if (ID.equals(USER_ID)) {
          return "not okay";
          //return not valid
        }
      }
      //ADD USER ID AND PASSWORD TO THE DATABASE
      String sql2 = " INSERT INTO client (cid, pass,fname,lname)" + " values (?, ?, ?, ?)";
      statement = connect.prepareStatement(sql2);
      statement.setString(1, USER_ID);
      statement.setString(2, pass);
      statement.setString(3, fname);
      statement.setString(4, lname);
      boolean res = statement.execute();
      if (res == true) {
        return "okay";
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }
    return "okay";
  }

  public String case5(String id) { //view balance
    String balance = ".";
    ConnectDB db = new ConnectDB();
    connect = db.connectDB();
    try {
      String sql5 = " SELECT balance FROM client WHERE cid=?";
      statement = connect.prepareStatement(sql5);
      statement.setString(1, id);
      result = statement.executeQuery();
      while (result.next()) {
        balance = result.getString("balance");
        System.out.println(balance);
        return balance;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return balance;
  }

  public String case8(String id, String amount) { //deposit, inc balance
    ConnectDB db = new ConnectDB();
    connect = db.connectDB();
    try {
      String sql8 = " update client set balance=balance+? where cid=?";
      statement = connect.prepareStatement(sql8);
      statement.setInt(1, Integer.parseInt(amount));
      statement.setString(2, id);
      statement.executeUpdate();
      return "success";
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return "fail";
  }

  public String case7(String iname) { //search
    ConnectDB db = new ConnectDB();
    connect = db.connectDB();
    String price = "";
    try {
      String sql7 = " select price from item where iname=?";
      statement = connect.prepareStatement(sql7);
      statement.setString(1, iname);
      result = statement.executeQuery();
      while (result.next()) {
        price = result.getString("price");
        return price;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "not okay";
  }

  public void case9(String iname, ArrayList < String > a) {
    //retrieve item id and price,, having item name
    ConnectDB db = new ConnectDB();
    connect = db.connectDB();
    String price;
    String id;

    try {
      String sql9 = " select price,itemid from item where iname= ?";
      statement = connect.prepareStatement(sql9);
      statement.setString(1, iname);
      result = statement.executeQuery();
      while (result.next()) {
        price = result.getString("price");
        id = result.getString("itemid");
        a.add(id);
        a.add(price);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public String case61(String user) { //balance check
    ConnectDB db = new ConnectDB();
    connect = db.connectDB();
    String balance;
    try {
      String sql61 = "select balance from client where cid=?";
      statement = connect.prepareStatement(sql61);
      statement.setString(1, user);
      result = statement.executeQuery();
      //String sql6="insert into purchased_items (cid,oid,itemid) values (?,?,?)";
      while (result.next()) {
        balance = result.getString("balance");
        return balance;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return "not okay";
  }

  public void case62(String un, ArrayList < String > itemid, ArrayList < String > cartqty, int tot, String user) { //check qty
    ConnectDB db = new ConnectDB();
    connect = db.connectDB();
    ArrayList < String > qty = new ArrayList < > ();
    try {
      for (int i = 0; i < itemid.size(); i++) {
        String sql62 = "select qty from item where itemid=?";
        statement = connect.prepareStatement(sql62);
        statement.setString(1, itemid.get(i));
        result = statement.executeQuery();
        while (result.next()) {
          qty.add(result.getString("qty"));
        }
      }
      for (int i = 0; i < qty.size(); i++) {
        qty.set(i, String.valueOf(Integer.parseInt(qty.get(i)) - Integer.parseInt(cartqty.get(i))));
      }
      for (int i = 0; i < qty.size(); i++) {
        String sql63 = "update item set qty=?  where itemid=?";
        statement = connect.prepareStatement(sql63);
        statement.setString(1, qty.get(i));
        statement.setString(2, itemid.get(i));
        statement.executeUpdate();

      }
      String sql64 = "update client set balance=balance-?  where cid=?";
      statement = connect.prepareStatement(sql64);
      statement.setInt(1, tot);
      statement.setString(2, user);
      statement.executeUpdate();

      Random rand = new Random();
      int z = rand.nextInt(1000);
      //d=d+1;
      String sql66 = "insert into orders (cid,oid,total_price) values(?,?,?)";
      statement = connect.prepareStatement(sql66);
      statement.setString(1, un);
      statement.setString(2, String.valueOf(z));
      statement.setString(3, String.valueOf(tot));
      statement.execute();

      for (int i = 0; i < itemid.size(); i++) {
        String sql65 = "insert into purchased_items (cid,oid,itemid) values(?,?,?)";
        statement = connect.prepareStatement(sql65);
        statement.setString(1, un);
        statement.setString(2, String.valueOf(z));
        statement.setString(3, itemid.get(i));
        statement.execute();
      }
      d++;
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void case52(String id, ArrayList < String > oid, ArrayList < String > price) { //view balance
    ConnectDB db = new ConnectDB();
    connect = db.connectDB();
    try {

      String sql52 = " SELECT oid,total_price FROM orders WHERE cid=?";
      statement = connect.prepareStatement(sql52);
      statement.setString(1, id);
      result = statement.executeQuery();
      while (result.next()) {
        oid.add(result.getString("oid"));
        price.add(result.getString("total_price"));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void caseFood(ArrayList < String > a) {
    ConnectDB db = new ConnectDB();
    connect = db.connectDB();
    String pic, iname, qty, price;
    try {
      String sql = "select iname,price,qty,pic from item where category=\"Food\"";
      ResultSet rs = connect.createStatement().executeQuery(sql);
      while (rs.next()) {
        pic = rs.getString("pic");
        iname = rs.getString("iname");
        qty = rs.getString("qty");
        price = rs.getString("price");
        a.add(pic);
        a.add(iname);
        a.add(qty);
        a.add(price);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void caseCloth(ArrayList < String > a) {
    ConnectDB db = new ConnectDB();
    connect = db.connectDB();
    String pic, iname, qty, price;
    try {
      String sql = "select iname,price,qty,pic from item where category=\"Clothing\"";
      ResultSet rs = connect.createStatement().executeQuery(sql);
      while (rs.next()) {
        pic = rs.getString("pic");
        iname = rs.getString("iname");
        qty = rs.getString("qty");
        price = rs.getString("price");
        a.add(pic);
        a.add(iname);
        a.add(qty);
        a.add(price);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  public void caseHA(ArrayList < String > a) {
    ConnectDB db = new ConnectDB();
    connect = db.connectDB();
    String pic, iname, qty, price;
    try {
      String sql = "select iname,price,qty,pic from item where category=\"Home Appliances\"";
      ResultSet rs = connect.createStatement().executeQuery(sql);
      while (rs.next()) {
        pic = rs.getString("pic");
        iname = rs.getString("iname");
        qty = rs.getString("qty");
        price = rs.getString("price");
        a.add(pic);
        a.add(iname);
        a.add(qty);
        a.add(price);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  public void caseElec(ArrayList < String > a) {
    ConnectDB db = new ConnectDB();
    connect = db.connectDB();
    String pic, iname, qty, price;
    try {
      String sql = "select iname,price,qty,pic from item where category=\"Electronics\"";
      ResultSet rs = connect.createStatement().executeQuery(sql);
      while (rs.next()) {
        pic = rs.getString("pic");
        iname = rs.getString("iname");
        qty = rs.getString("qty");
        price = rs.getString("price");
        a.add(pic);
        a.add(iname);
        a.add(qty);
        a.add(price);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  public String case20(int sum) {
    ConnectDB db = new ConnectDB();
    connect = db.connectDB();
    try {

      ArrayList < String > price = new ArrayList < > ();

      String sql20 = " SELECT total_price FROM orders";
      statement = connect.prepareStatement(sql20);
      result = statement.executeQuery();
      while (result.next()) {
        price.add(result.getString("total_price"));
      }
      for (int i = 0; i < price.size(); i++) {
        sum += Integer.parseInt(price.get(i));

      }
      return String.valueOf(sum);

    } catch (SQLException e) {
      e.printStackTrace();
    }

    return "null";

  }
  public String case21(int sum) {
    ConnectDB db = new ConnectDB();
    connect = db.connectDB();
    int i = 0;
    try {
      ArrayList < String > price = new ArrayList < > ();
      String sql21 = " SELECT cid FROM client";
      statement = connect.prepareStatement(sql21);
      result = statement.executeQuery();
      while (result.next()) {
        price.add(result.getString("cid"));
        i++;
      }
      return String.valueOf(i);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return "No Clients.";
  }
}
