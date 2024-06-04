package HW.controller;

import HW.dao.EmployeeDAO;
import HW.dto.EmployeeFilterDto;
import HW.models.Employee;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.ArrayList;


@Path("employee")
public class EmployeeController {

    EmployeeDAO dao = new EmployeeDAO();



    @GET
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})

    public ArrayList<Employee> getAllEmployee(@BeanParam EmployeeFilterDto filterDto )

    {


        try {
            return dao.selectAllEmployees(filterDto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GET
    @Path("{EmployeeID}")
    public Employee getEmployee(@PathParam("EmployeeID") int EmployeeID) {

        try {
            return dao.selectEmployee(EmployeeID);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DELETE
    @Path("{EmployeeID}")
    public void deleteEmployee(@PathParam("EmployeeID") int EmployeeID) {

        try {
            dao.deleteEmployee(EmployeeID);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @POST
    public void insertEmployee(Employee employee) {

        try {
            dao.insertEmployee(employee);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PUT
    @Path("{EmployeeID}")
    public void updateEmployee(@PathParam("EmployeeID") int EmployeeID, Employee employee) {

        try {
            employee.setEmployeeId(EmployeeID);
            dao.updateEmployee(employee);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
