package HW.dto;

import jakarta.ws.rs.QueryParam;

public class EmployeeFilterDto {


    private @QueryParam("employeeId") Integer employeeId;
    private @QueryParam("limit") Integer limit;
    private  @QueryParam("offset") int offset;


    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }
}
