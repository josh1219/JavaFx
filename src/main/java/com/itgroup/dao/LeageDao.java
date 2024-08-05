package com.itgroup.dao;

import com.itgroup.bean.League;
import com.itgroup.utility.Paging;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class LeageDao extends SuperDao {

    //생성자 만들기
    public LeageDao() {
        //2)
        super();
    }

    public List<League> selectAll() {//모든 상품 조회하기
        Connection conn = null;
        //오라클의 세미콜롬(;)은 적지 않는다
        String sql = "select * from champion order by IDX desc";
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        List<League> allData = new ArrayList<League>();

        try{
            conn = super.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            //내려가려면 next사용
            while(rs.next()){

                League bean = this.makeBean(rs);
                allData.add(bean);

            }

        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            try{
                if(rs != null){rs.close();}
                if(pstmt != null){pstmt.close();}
                if(conn != null){conn.close();}
            }catch(Exception ex){
                ex.printStackTrace();

            }
        }
        return allData;
    }

    private League makeBean(ResultSet rs) {
        League bean = new League();
        try{
            bean.setIDX(rs.getInt("IDX"));
            bean.setNAME(rs.getString("NAME"));
            bean.setPOSITION(rs.getString("POSITION"));
            bean.setPIMAGE(rs.getString("PIMAGE"));
            bean.setPNAME(rs.getString("PNAME"));
            bean.setQIMAGE(rs.getString("QIMAGE"));
            bean.setQNAME(rs.getString("QNAME"));
            bean.setWIMAGE(rs.getString("WIMAGE"));
            bean.setWNAME(rs.getString("WNAME"));
            bean.setEIMAGE(rs.getString("EIMAGE"));
            bean.setENAME(rs.getString("ENAME"));
            bean.setRIMAGE(rs.getString("RIMAGE"));
            bean.setRNAME(rs.getString("RNAME"));
            bean.setSKIN(rs.getString("SKIN"));
            bean.setCHAMPEXPLANATION(rs.getString("CHAMPEXPLANATION"));
            bean.setILLUSTRATION(rs.getString("ILLUSTRATION"));

        }catch(Exception ex){
            ex.printStackTrace();
        } return bean;
    }

    public List<League> selectByCategory(String category) {
        Connection conn = null ;

        String sql = " select * from champion " ;

        boolean bool = category == null || category.equals("all");
        if(!bool){
            sql += " where POSITION = ? ";
        }

        //문장 객체
        PreparedStatement pstmt = null ;
        ResultSet rs = null ;

        List<League> allData = new ArrayList<>();
        try{
            conn = super.getConnection();
            pstmt = conn.prepareStatement(sql);
            //값을 바꾸려면 set,치환하고 실행하기 때문에
            //set는 executeQuery 앞에

            if (!bool) {
                pstmt.setString(1,category);
            }
            rs = pstmt.executeQuery() ;

            while(rs.next()){
                League bean = this.makeBean(rs);
                allData.add(bean);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            try{
                if(rs!=null){rs.close();}
                if(pstmt!=null){pstmt.close();}
                if(conn!=null){conn.close();}
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
        return allData ;
    }

    public League selectByPK(int pnum) {
        Connection conn = null ;
        String sql = " select * from champion " ;
        sql += " where IDX = ?";

        PreparedStatement pstmt = null ;
        ResultSet rs = null ;

        League bean = null;
        try{
            conn = super.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,pnum);
            rs = pstmt.executeQuery() ;
            if(rs.next()){
                bean = this.makeBean(rs);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            try{
                if(rs!=null){rs.close();}
                if(pstmt!=null){pstmt.close();}
                if(conn!=null){conn.close();}
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
        return bean ;
    }

    public int insertData(League bean) {
        // Resultset은 출력할 때 사용
        //데이터가 잘 들어왔는지 확인
        System.out.println(bean);
        int cnt = -1;
        Connection conn = null;
        PreparedStatement pstmt = null;

        String sql = " insert into champion(IDX, NAME, POSITION, PIMAGE, PNAME, QIMAGE, QNAME, WIMAGE, WNAME, EIMAGE, ENAME, RIMAGE, RNAME, SKIN, CHAMPEXPLANATION, ILLUSTRATION)";
        sql +=" values(idxChamp.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)" ;


        try{
            //객체 생성
            conn = super.getConnection();
            //자동커밋을 끄고 한번에 커밋으 하겠다 선언,
            conn.setAutoCommit(false);
            pstmt = conn.prepareStatement(sql);


            //치환할때는 excuteUpdate전에 구문 사용
            //cnt 삭제,추가로 업데이트 사용
            pstmt.setString(1,bean.getNAME());
            pstmt.setString(2,bean.getPOSITION());
            pstmt.setString(3,bean.getPIMAGE());
            pstmt.setString(4,bean.getPNAME());
            pstmt.setString(5,bean.getQIMAGE());
            pstmt.setString(6,bean.getQNAME());
            pstmt.setString(7,bean.getWIMAGE());
            pstmt.setString(8,bean.getWNAME());
            pstmt.setString(9,bean.getEIMAGE());
            pstmt.setString(10,bean.getENAME());
            pstmt.setString(11,bean.getRIMAGE());
            pstmt.setString(12,bean.getRNAME());
            pstmt.setString(13,bean.getSKIN());
            pstmt.setString(14,bean.getCHAMPEXPLANATION());
            pstmt.setString(15,bean.getILLUSTRATION());

            cnt = pstmt.executeUpdate();

            conn.commit();

        }catch(Exception ex){
            ex.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }finally{
            try{
                if(pstmt!=null){pstmt.close();}
                if(conn!=null){conn.close();}
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }

        return cnt;
    }

    public int updateData(League bean) {

        System.out.println("탐탐탐");
        System.out.println(bean);
        int cnt = -1;
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = " update champion set name = ?,POSITION = ?,PIMAGE = ?,PNAME = ?,QIMAGE = ?,QNAME = ?,WIMAGE = ?,WNAME = ?,EIMAGE = ?,ENAME = ?,RIMAGE = ?,RNAME = ?,SKIN = ?,CHAMPEXPLANATION = ? , ILLUSTRATION = ?";
        sql += " where IDX = ? " ;


        try{
            //객체 생성
            conn = super.getConnection();
            //자동커밋을 끄고 한번에 커밋으 하겠다 선언,
            conn.setAutoCommit(false);
            pstmt = conn.prepareStatement(sql);


            //치환할때는 excuteUpdate전에 구문 사용
            //cnt 삭제,추가로 업데이트 사용

            pstmt.setString(1,bean.getNAME());
            pstmt.setString(2,bean.getPOSITION());
            pstmt.setString(3,bean.getPIMAGE());
            pstmt.setString(4,bean.getPNAME());
            pstmt.setString(5,bean.getQIMAGE());
            pstmt.setString(6,bean.getQNAME());
            pstmt.setString(7,bean.getWIMAGE());
            pstmt.setString(8,bean.getWNAME());
            pstmt.setString(9,bean.getEIMAGE());
            pstmt.setString(10,bean.getENAME());
            pstmt.setString(11,bean.getRIMAGE());
            pstmt.setString(12,bean.getRNAME());
            pstmt.setString(13,bean.getSKIN());
            pstmt.setString(14,bean.getCHAMPEXPLANATION());
            pstmt.setString(15,bean.getILLUSTRATION());
            pstmt.setString(16,String.valueOf(bean.getIDX()));

            cnt = pstmt.executeUpdate();

            conn.commit();

        }catch(Exception ex){
            ex.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }finally{
            try{
                if(pstmt!=null){pstmt.close();}
                if(conn!=null){conn.close();}
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }

        return cnt;
    }

    public int deleteData(int pnum) {

        System.out.println("기본 키 = " + pnum);
        int cnt = -1;
        Connection conn = null;
        PreparedStatement pstmt = null;

        String sql = " delete from champion ";
        sql += " where IDX = ? " ;


        try{
            //객체 생성
            conn = super.getConnection();
            //자동커밋을 끄고 한번에 커밋으 하겠다 선언,
            conn.setAutoCommit(false);
            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1,pnum);

            cnt = pstmt.executeUpdate();

            conn.commit();

        }catch(Exception ex){
            ex.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }finally{
            try{
                if(pstmt!=null){pstmt.close();}
                if(conn!=null){conn.close();}
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }

        return cnt;
    }

    public int getTotalCount(String category) {

        int getTotalCount = 0;
        String sql = " select count (*) as mycnt from champion ";
        boolean bool = category == null || category.equals("all");
        //카테고리를 넣지 않으면 실행되지 않음
        if(!bool){
            sql += " where POSITION = ? ";
        }

        Connection conn = null ;

        PreparedStatement pstmt = null ;
        ResultSet rs = null ;

        try{
            conn = super.getConnection();
            pstmt = conn.prepareStatement(sql);
            if(!bool){
                pstmt.setString(1,category);
            }

            rs = pstmt.executeQuery() ;

            if(rs.next()){
                getTotalCount = rs.getInt("mycnt");
            }

        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            try{
                if(rs!=null){rs.close();}
                if(pstmt!=null){pstmt.close();}
                if(conn!=null){conn.close();}
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }

        return getTotalCount;
    }

    public List<League> getPaginationData(Paging pageInfo) {
        Connection conn = null ;

        String sql = " select IDX, NAME, POSITION, PIMAGE, PNAME, QIMAGE, QNAME, WIMAGE, WNAME, EIMAGE, ENAME, RIMAGE, RNAME, SKIN, CHAMPEXPLANATION, ILLUSTRATION ";
        sql += " from ( ";
        sql += " select IDX, NAME, POSITION, PIMAGE, PNAME, QIMAGE, QNAME, WIMAGE, WNAME, EIMAGE, ENAME, RIMAGE, RNAME, SKIN, CHAMPEXPLANATION, ILLUSTRATION, ";
        sql += " rank() over(order by IDX desc) as ranking ";
        sql += " from champion ";

        // mode가 'all'이 아니면 where 절이 추가로 필요합니다.
        String mode = pageInfo.getMode();
        boolean bool = mode.equals(null) || mode.equals("null") || mode.equals("") || mode.equals("all");

        if (!bool) {
            sql += " where POSITION = ? ";
        }

        sql += " ) ";
        sql += " where ranking between ? and ? ";
        PreparedStatement pstmt = null ;
        ResultSet rs = null ;

        List<League> allData = new ArrayList<>();
        try{
            conn = super.getConnection();
            pstmt = conn.prepareStatement(sql);

            if (!bool) {
                pstmt.setString(1, mode);
                pstmt.setInt(2,pageInfo.getBeginRow());
                pstmt.setInt(3,pageInfo.getEndRow());
            } else {
                pstmt.setInt(1,pageInfo.getBeginRow());
                pstmt.setInt(2,pageInfo.getEndRow());
            }

            rs = pstmt.executeQuery() ;

            while(rs.next()){
                League bean = this.makeBean(rs);
                allData.add(bean);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            try{
                if(rs!=null){rs.close();}
                if(pstmt!=null){pstmt.close();}
                if(conn!=null){conn.close();}
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
        return allData;
    }
}