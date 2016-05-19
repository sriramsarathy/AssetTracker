package com.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.json.JSONObject;

import com.app.ItemSubCategory;
import com.j256.ormlite.dao.Dao;

@Path("/subCategory")
public class AssetSubCategoryController {

  Dao<ItemSubCategory, String> assetSubCategoryDAO;
  final ResponseBuilder resBuilder = Response.status(200).header("Access-Control-Allow-Origin", "*")
      .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS, DELETE")
      .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With");

  @GET
  @Produces("application/json")
  public Response getAssetSubCategory(@QueryParam("id") String id) throws SQLException, IOException {
    DAOHelper.getInstance().init();
    assetSubCategoryDAO = DAOHelper.getInstance().getAssetSubCategoryDAO();
    JSONObject subCategListJSON = new JSONObject();
      List<ItemSubCategory> subCategories = assetSubCategoryDAO.queryForAll();
      for (ItemSubCategory sc : subCategories) {        
        if(!sc.getAssetCategory().getId().equals(Integer.parseInt(id)))
          continue;
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", sc.getId());
        jsonObject.put("name", sc.getName());
        subCategListJSON.accumulate("subCategories", jsonObject);
      }
      DAOHelper.getInstance().end();
    return resBuilder.entity(subCategListJSON.toString()).build();
  }
  
  @POST
  @Produces("application/json")
  public Response storeSubCategory(@FormParam("name") String name, @FormParam("address") String address,
      @FormParam("city") String city, @FormParam("state") String state) throws SQLException, IOException {

    DAOHelper.getInstance().init();
    assetSubCategoryDAO = DAOHelper.getInstance().getAssetSubCategoryDAO();
      ItemSubCategory sub = new ItemSubCategory();
      sub.setName(name);
      assetSubCategoryDAO.create(sub);
      DAOHelper.getInstance().end();
    return resBuilder.entity("Success").build();
  }

}
