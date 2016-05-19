package com.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.json.JSONObject;

import com.app.Asset;
import com.app.AssetCategory;
import com.app.AssetStatusEnum;
import com.app.ItemSubCategory;
import com.app.Location;
import com.helper.AssetHelper;
import com.j256.ormlite.dao.Dao;

@Path("/asset")
public class AssetController {

  AssetHelper helper = new AssetHelper();
  Dao<Asset, String> assetDAO;
  Dao<ItemSubCategory, String> assetSubCategoryDAO ;
  Dao<Location, String> locationDAO;
  Dao<AssetCategory, String> assetCategoryDAO;
  final ResponseBuilder resBuilder = Response.status(200).header("Access-Control-Allow-Origin", "*")
      .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS, DELETE")
      .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With");

  
  @Path("get/asset")
  @GET
  @Produces("application/json")
  public Response getAsset() throws SQLException, IOException {

    DAOHelper.getInstance().init();
    assetDAO = DAOHelper.getInstance().getAssetDAO();
    locationDAO = DAOHelper.getInstance().getLocationDAO();
    assetSubCategoryDAO = DAOHelper.getInstance().getAssetSubCategoryDAO();
    assetCategoryDAO = DAOHelper.getInstance().getAssetCategoryDAO();
    JSONObject assetListJSON = new JSONObject();

    List<Asset> assets = assetDAO.queryForAll();
    for (Asset obj : assets) {
      //Set Current Value
      helper.setCurrentValue((Calendar.getInstance().get(Calendar.YEAR)), obj);
      
      AssetCategory category = assetCategoryDAO.queryForId(obj.getCategoryId().toString());
      ItemSubCategory subCategory = assetSubCategoryDAO.queryForId(obj.getSubCategoryId().toString());
      JSONObject jsonObject = new JSONObject();
      jsonObject.put("id", obj.getId());
      jsonObject.put("Name", obj.getName());
      jsonObject.put("category", category.getName());
      jsonObject.put("subCategory", subCategory.getName());
      jsonObject.put("locationId", obj.getLocationId());
      jsonObject.put("yearsOfUse", obj.getYearsOfUse());
      jsonObject.put("status", obj.getStatus());
      jsonObject.put("price", obj.getPrice());
      jsonObject.put("creationDate", obj.getCreationDate());
      
      assetListJSON.accumulate("assets", jsonObject);
    }

    DAOHelper.getInstance().end();
    return resBuilder.entity(assetListJSON.toString()).build();
  }

  @POST
  @Produces("application/json")
  public Response storeAsset(@FormParam("Name") String name, @FormParam("categoryId") Long categoryId,
      @FormParam("subCategoryId") Long subCategoryId, @FormParam("donor") String donor,
      @FormParam("locationId") Long locationId, @FormParam("yearsOfUse") int yearsOfUse,
      @FormParam("status") AssetStatusEnum status, @FormParam("price") BigDecimal price,
      @FormParam("creationDate") Date creationDate) throws SQLException, IOException {

    DAOHelper.getInstance().init();
    assetDAO = DAOHelper.getInstance().getAssetDAO();
    Asset obj = new Asset();
    obj.setName(name);
    obj.setCategoryId(categoryId);
    obj.setSubCategoryId(subCategoryId);
    obj.setDonor(donor);
    obj.setLocationId(locationId);
    obj.setYearsOfUse(yearsOfUse);
    obj.setStatus(status);
    obj.setPrice(price);
    obj.setCreationDate(creationDate);
    assetDAO.create(obj);
    DAOHelper.getInstance().end();
    return resBuilder.entity("Success").build();

  }
  
  
  @Path("{year}")
  @GET
  @Produces("application/json")
  public Response getAssetsForFinancialYear(@PathParam("year") final String year) throws SQLException, ParseException {
       
    List<Asset> assets = helper.getAssetsForFiscalYear(Integer.parseInt(year));
    JSONObject assetListJSON = new JSONObject();
    for (Asset obj : assets) {
      JSONObject jsonObject = new JSONObject();
      jsonObject.put("id", obj.getId());
      jsonObject.put("Name", obj.getName());
      jsonObject.put("categoryId", obj.getCategoryId());
      jsonObject.put("subCategoryId", obj.getSubCategoryId());
      jsonObject.put("locationId", obj.getLocationId());
      jsonObject.put("yearsOfUse", obj.getYearsOfUse());
      jsonObject.put("status", obj.getStatus());
      jsonObject.put("price", obj.getPrice());
      jsonObject.put("creationDate", obj.getCreationDate());
      jsonObject.put("bookValue", obj.getCurrentValue());
      assetListJSON.accumulate("assets", jsonObject);
    }    
    return resBuilder.entity(assetListJSON.toString()).build(); 
//    return resBuilder.entity("Success").build();
  }
}
