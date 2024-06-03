package Day5.controller;

import Day5.dao.JobDAO;
import jakarta.ws.rs.*;
import Day5.models.Jobs;


import java.util.ArrayList;

@Path("jobs")
public class JobController {


   JobDAO dao = new JobDAO();

    @GET
    public ArrayList<Jobs> getAllJobs() {

        try {
            return dao.selectAllJobs();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GET
    @Path("{JobId}")
    public Jobs getDepartment(@PathParam("JobId") int JobId) {

        try {
            return dao.selectJob(JobId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DELETE
    @Path("{JobId}")
    public void deleteJob(@PathParam("JobId") int JobId) {

        try {
            dao.deleteJob(JobId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @POST
    public void insertJob(Jobs jobs) {

        try {
            dao.insertJob(jobs);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PUT
    @Path("{JobId}")
    public void updateJob(@PathParam("JobId") int JobId, Jobs jobs) {

        try {
            jobs.setJob_id(JobId);
            dao.updateJob(jobs);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
