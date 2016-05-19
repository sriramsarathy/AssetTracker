package com.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.json.JSONObject;

import com.app.Location;
import com.j256.ormlite.dao.Dao;

@Path("/location")
public class LocationController {

  Dao<Location, String> locationDAO;
  final ResponseBuilder resBuilder = Response.status(200).header("Access-Control-Allow-Origin", "*")
      .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS, DELETE")
      .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With");

  @GET
  @Produces("application/json")
  public Response getLocation() throws SQLException, IOException {

    DAOHelper.getInstance().init();
    locationDAO = DAOHelper.getInstance().getLocationDAO();
    JSONObject locationListJSON = new JSONObject();

      List<Location> locations = locationDAO.queryForAll();
      for (Location loc : locations) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", loc.getId());
        jsonObject.put("name", loc.getName());
        jsonObject.put("address", loc.getAddress());
        jsonObject.put("city", loc.getCity());
        jsonObject.put("state", loc.getState());
        locationListJSON.accumulate("locations", jsonObject);
      }

      DAOHelper.getInstance().end();
    return resBuilder.entity(locationListJSON.toString()).build();
  }

  @POST
  @Produces("application/json")
  public Response storeLocation(@FormParam("name") String name, @FormParam("address") String address,
      @FormParam("city") String city, @FormParam("state") String state) throws SQLException, IOException {

    DAOHelper.getInstance().init();
    locationDAO = DAOHelper.getInstance().getLocationDAO();
      Location loc = new Location();
      loc.setName(name);
      loc.setAddress(address);
      loc.setCity(city);
      loc.setState(state);
      locationDAO.create(loc);
      DAOHelper.getInstance().end();
    return resBuilder.entity("Success").build();
  }

}