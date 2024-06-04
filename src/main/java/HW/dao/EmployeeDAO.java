package HW.dao;

import HW.dto.EmployeeFilterDto;
import HW.models.Employee;

import java.sql.*;
import java.util.ArrayList;

public class EmployeeDAO {
    private static final String URL = "jdbc:sqlite:C:\\Users\\dev\\IdeaProjects\\HrApiHW\\src\\main\\resources\\hr.db";
    private static final String SELECT_ALL_EMPLOYEES = "SELECT * FROM employees";

    private static final String SELECT_EMPLOYEE_WITH_employeeId = "SELECT * FROM Employee WHERE employee_id = ?";
    private static final String SELECT_EMPLOYEE_WITH_employeeId_PAGINATION = "SELECT * FROM Employee WHERE employee_id = ? ORDER BY department_id LIMIT ? OFFSET ?";
    private static final String SELECT_EMPLOYEE_WITH_PAGINATION = "SELECT * FROM Employee ORDER BY employee_id LIMIT ? OFFSET ?";



    private static final String SELECT_ONE_EMPLOYEE = "SELECT * FROM employees WHERE employee_id = ?";
    private static final String INSERT_EMPLOYEE = "INSERT INTO employees VALUES(employee_id, first_name, last_name, email, phone_number, hire_date, job_id, salary, manager_id, department_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_EMPLOYEE = "UPDATE employees SET first_name = ?, last_name = ?, email = ?, phone_number = ?, hire_date = ?, job_id = ?, salary = ?, manager_id = ?, department_id = ? WHERE employee_id = ?";
    private static final String DELETE_EMPLOYEE = "DELETE FROM employees WHERE employee_id = ?";

    public void insertEmployee(Employee employee) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement st = conn.prepareStatement(INSERT_EMPLOYEE)) {
            st.setInt(1, employee.getEmployeeId());
            st.setString(2, employee.getFirstName());
            st.setString(3, employee.getLastName());
            st.setString(4, employee.getEmail());
            st.setString(5, employee.getPhoneNumber());
            st.setDate(6, employee.getHireDate());
            st.setInt(7, employee.getJobId());
            st.setDouble(8, employee.getSalary());
            st.setInt(9, employee.getManagerId());
            st.setInt(10, employee.getDepartmentId());
            st.executeUpdate();
        }
    }

    public void updateEmployee(Employee employee) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement st = conn.prepareStatement(UPDATE_EMPLOYEE)) {
            st.setString(1, employee.getFirstName());
            st.setString(2, employee.getLastName());
            st.setString(3, employee.getEmail());
            st.setString(4, employee.getPhoneNumber());
            st.setDate(5, employee.getHireDate());
            st.setInt(6, employee.getJobId());
            st.setDouble(7, employee.getSalary());
            st.setInt(8, employee.getManagerId());
            st.setInt(9, employee.getDepartmentId());
            st.setInt(10, employee.getEmployeeId());
            st.executeUpdate();
        }
    }

    public void deleteEmployee(int employeeId) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement st = conn.prepareStatement(DELETE_EMPLOYEE)) {
            st.setInt(1, employeeId);
            st.executeUpdate();
        }
    }

    public Employee selectEmployee(int employeeId) throws Exception {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
             PreparedStatement st = conn.prepareStatement(SELECT_ONE_EMPLOYEE);
            st.setInt(1, employeeId);
            ResultSet rs = st.executeQuery();
        if(rs.next()) {
            return new Employee(rs);
        }
        else {
            return null;
        }
    }

    public ArrayList<Employee> selectAllEmployees( EmployeeFilterDto filterDto) throws Exception {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st;
        if (filterDto != null && filterDto!= null){

            st = conn.prepareStatement(SELECT_EMPLOYEE_WITH_employeeId_PAGINATION);
            st.setInt(1,filterDto.getEmployeeId());
            st.setInt(2,filterDto.getLimit());
            st.setInt(3,filterDto.getOffset());}
        else if (filterDto.getEmployeeId()!=null) {
            st = conn.prepareStatement(SELECT_EMPLOYEE_WITH_employeeId);
            st.setDouble(1,filterDto.getEmployeeId());

        } else if (filterDto.getLimit()!=null) {
            st = conn.prepareStatement(SELECT_EMPLOYEE_WITH_PAGINATION);
            st.setInt(1,filterDto.getLimit());
            st.setInt(2,filterDto.getOffset());}
        else{
            st = conn.prepareStatement(SELECT_ALL_EMPLOYEES);

        }
        ResultSet rs = st.executeQuery();

        ArrayList<Employee> employees = new ArrayList<>();
        while (rs.next()) {
            employees.add(new Employee(rs));
        }

        return employees;
    }

}

