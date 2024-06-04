package org.example.dao;

import org.example.models.Jobs;

import java.sql.*;
import java.util.ArrayList;

public class JobDAO {

    private static final String URL = "jdbc:sqlite:C:\\Users\\dev\\IdeaProjects\\HrApiDay05\\src\\main\\resources\\hr.db";
    private static final String SELECT_ALL_JOBS = "select * from jobs";

    private static final String SELECT_Jobs_WITH_Min = "select * from jobs where min_salary = ?";
    private static final String SELECT_Jobs_WITH_Min_PAGINATION = "select * from jobs where min_salary = ? order by department_id limit ? offset ?";
    private static final String SELECT_Jobs_WITH_PAGINATION = "select * from jobs order by min_salary limit ? offset ?";




    private static final String SELECT_ONE_JOB = "select * from jobs where job_id = ?";
    private static final String INSERT_JOB = "insert into jobs values (?, ?, ?, ?)";
    private static final String UPDATE_JOB = "update jobs set job_title = ?, min_salary = ?, max_salary = ? where job_id = ?";
    private static final String DELETE_JOB = "delete from jobs where job_id = ?";

    public void insertJob(Jobs j) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st = conn.prepareStatement(INSERT_JOB);
        st.setInt(1, j.getJob_id());
        st.setString(2, j.getJob_title());
        st.setDouble(3, j.getMin_salary());
        st.setDouble(4, j.getMax_salary());
        st.executeUpdate();
    }




    public void updateJob(Jobs j) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st = conn.prepareStatement(UPDATE_JOB);
        st.setInt(4, j.getJob_id());
        st.setString(1, j.getJob_title());
        st.setDouble(2, j.getMin_salary());
        st.setDouble(3, j.getMax_salary());
        st.executeUpdate();
    }

    public void deleteJob(Integer jobId) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st = conn.prepareStatement(DELETE_JOB);
        st.setInt(1, jobId);
        st.executeUpdate();
    }


    public Jobs selectJob(int job_id) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st = conn.prepareStatement(SELECT_ONE_JOB);
        st.setInt(1, job_id);
        ResultSet rs = st.executeQuery();
        if(rs.next()) {
            return new Jobs(rs);
        }
        else {
            return null;
        }
    }

    public ArrayList<Jobs> selectAllJobs(Double min_salary,Integer limit,int offset) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st;
        if (min_salary != null && limit!= null){

            st = conn.prepareStatement(SELECT_Jobs_WITH_Min_PAGINATION);
        st.setDouble(1,min_salary);
        st.setInt(2,limit);
        st.setInt(3,offset);}
        else if (min_salary!=null) {
            st = conn.prepareStatement(SELECT_Jobs_WITH_Min);
            st.setDouble(1,min_salary);
            
        } else if (limit!=null) {
            st = conn.prepareStatement(SELECT_Jobs_WITH_PAGINATION);
            st.setInt(1,limit);
            st.setInt(2,offset);}
        else{
            st = conn.prepareStatement(SELECT_ALL_JOBS);

        }
ResultSet rs = st.executeQuery();

        ArrayList<Jobs> jobs = new ArrayList<>();
        while (rs.next()) {
            jobs.add(new Jobs(rs));
        }

        return jobs;
    }

}
