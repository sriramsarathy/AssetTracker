package com.controller;

import java.io.IOException;
import java.sql.SQLException;

import com.app.Asset;
import com.app.AssetCategory;
import com.app.ItemSubCategory;
import com.app.Location;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class DAOHelper {
  private static DAOHelper DAOfactory = new DAOHelper();

  private static ConnectionSource connectionSource;
  private static Dao<Location, String> locationDAO;
  private static Dao<Asset, String> assetDAO;
  private static Dao<AssetCategory, String> assetCategoryDAO;
  private static Dao<ItemSubCategory, String> assetSubCategoryDAO;

  private DAOHelper() {

  }

  public static DAOHelper getInstance() {
    return DAOfactory;
  }

  public void init() throws SQLException {

    // String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + dbUri.getPath();

    // Connect to DB
    String databaseUrl = "jdbc:postgresql://localhost:5432/AssetTracker";
    if (connectionSource == null)
      connectionSource = new JdbcConnectionSource(databaseUrl, "postgres", "pwd");

    assetSubCategoryDAO = DaoManager.lookupDao(connectionSource, ItemSubCategory.class);
    if (assetSubCategoryDAO == null) {
      assetSubCategoryDAO = DaoManager.createDao(connectionSource, ItemSubCategory.class);
      TableUtils.createTableIfNotExists(connectionSource, ItemSubCategory.class);
    }

    assetCategoryDAO = DaoManager.lookupDao(connectionSource, AssetCategory.class);
    if (assetCategoryDAO == null) {
      assetCategoryDAO = DaoManager.createDao(connectionSource, AssetCategory.class);
      TableUtils.createTableIfNotExists(connectionSource, AssetCategory.class);
    }

    locationDAO = DaoManager.lookupDao(connectionSource, Location.class);
    if (locationDAO == null) {
      locationDAO = DaoManager.createDao(connectionSource, Location.class);
      TableUtils.createTableIfNotExists(connectionSource, Location.class);
    }

    assetDAO = DaoManager.lookupDao(connectionSource, Asset.class);
    if (assetDAO == null) {
      assetDAO = DaoManager.createDao(connectionSource, Asset.class);
      TableUtils.createTableIfNotExists(connectionSource, Asset.class);
    }

    System.out.println("Connected Successfully ! All tables Created .");
  }

  public void end() throws IOException, SQLException {
    if (connectionSource != null)
      connectionSource.close();
  }

  public Dao<Location, String> getLocationDAO() {
    return locationDAO;
  }

  public Dao<Asset, String> getAssetDAO() {
    return assetDAO;
  }

  public Dao<AssetCategory, String> getAssetCategoryDAO() {
    return assetCategoryDAO;
  }

  public Dao<ItemSubCategory, String> getAssetSubCategoryDAO() {
    return assetSubCategoryDAO;
  }
}
