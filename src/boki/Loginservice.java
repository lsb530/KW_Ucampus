package boki;

import java.sql.*;

public class Loginservice {

    private String id;

    private String pw;
    
    private String position;

    private String result;

    private final String SQL_SERVERNAME = "localhost";

    private final String SQL_ID         = "root";

    private final String SQL_PASS         = "root";

    private final String SQL_DBNAME     = "kw_ucampus";

    private final String SQL_PORT         = "3306";

    private Connection conn;

    private Statement stat;
    
    private Statement stat2;

    private ResultSet rs;
    
    private ResultSet rs2;

    //get set 함수

    public String getId() {
    	return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }
    
    public String getPosition() {
    	return position;
    }
    
    public void setPosition(String position) {
    	this.position = position;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    //DB 제어 함수 

    public void initDB(){

        try {

            Class.forName("com.mysql.jdbc.Driver").newInstance();

            String url = "jdbc:mysql://" + SQL_SERVERNAME + ":" + SQL_PORT + "/" + SQL_DBNAME;

            String option="?useUnicode=true&characterEncoding=EUC_KR";

            url = url + option;

            conn = DriverManager.getConnection(url, SQL_ID, SQL_PASS);

            stat = conn.createStatement();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public boolean CheckUser(){

        try {

            PreparedStatement stat;
            
            PreparedStatement stat2;
            
            stat = conn.prepareStatement("select * from user where id=?");
            
            stat2 = conn.prepareStatement("select * from user where position=?");

            stat.setString(1, id);
            
            stat2.setString(1, position);

            rs = stat.executeQuery();
            
            rs2 = stat2.executeQuery();

            if(true == rs.next()){

                //아이디 있음

                String temp = rs.getString(2);

                if(temp.equals(pw)){

                    result = id + "님 로그인완료 안녕하세요..^^ 방문의 환영 합니다!!";

                }

                else

                    result = id + "로그인 실패 패스워드 불일치";

            }else{

                //아이디 없음 (아이디 추가)

                if(true == AddUser())

                    result = id + " 아이디가 생성되었습니다.";

                else

                    result = id + " 아이디가 생성실패.";

            

            }

            

            stat.close();

 

        }catch(Exception ex){

            return false;

        }

 

        return true;

        

    }

    

    public boolean AddUser(){

        try {

            PreparedStatement stat;

            

            //도서 등록번호 데이터 등록

            stat = conn.prepareStatement("insert into user values(?, ?)");

            stat.setString(1, id);

            stat.setString(2, pw);

            stat.executeUpdate();

            

            stat.close();

 

        }catch(Exception ex){

            return false;

        }

 

        return true;

    }

      

      

    

    public void disDB(){

        try{

            if(stat != null){

                stat.close();    

            }

        }catch(Exception e)

        {

            e.printStackTrace();

        }

        

        try{

            if(conn != null){

                conn.close();

            }

        }catch(Exception e)

        {

            e.printStackTrace();

        }

    }

}