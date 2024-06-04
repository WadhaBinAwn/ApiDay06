package org.example.controller;

import jakarta.ws.rs.core.MediaType;
import org.example.dao.JobDAO;
import jakarta.ws.rs.*;
import org.example.models.Jobs;


import java.util.ArrayList;

@Path("jobs")
public class JobController {


   JobDAO dao = new JobDAO();



    @GET
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})

    public ArrayList<Jobs> getAllJobs(  @QueryParam("min_salary") Double min_salary,
                                        @QueryParam("limit") Integer limit,
                                        @QueryParam("offset") int offset) {


        try {
            return dao.selectAllJobs(min_salary,limit,offset);
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
