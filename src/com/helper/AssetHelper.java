package com.helper;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import com.app.Asset;
import com.controller.DAOHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;

public class AssetHelper {

  public List<Asset> getAssetsForFiscalYear(int year) throws SQLException, ParseException {
    DAOHelper.getInstance().init();
    Dao<Asset, String> assetDao = DAOHelper.getInstance().getAssetDAO();

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
    Date startDate = dateFormat.parse(String.valueOf(year)+"0101");
    java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
    
    QueryBuilder<Asset, String> qb = assetDao.queryBuilder();
    List<Asset> results = qb.where().le("creationDate", sqlStartDate)
        .query();
    for (Asset asset : results) {
      if(!setCurrentValue(year, asset))
        continue;
    }
    return results;
  }
  
  public boolean setCurrentValue(int year, Asset asset) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(asset.getCreationDate());
    int assetYear = cal.get(Calendar.YEAR);
    
    if (year > (assetYear + asset.getYearsOfUse())) {
      asset.setCurrentValue(BigDecimal.ZERO);
      return false;
    }

    int noYearsUsed = year - assetYear;
    if (asset.getPrice() == null) {
      asset.setCurrentValue(BigDecimal.ZERO);
      return false;
    }
    
    BigDecimal currentValue = asset.getPrice()
        .subtract(asset.getPrice().multiply((new BigDecimal("0.1"))).multiply(new BigDecimal(noYearsUsed)))
        .setScale(2, RoundingMode.FLOOR);

    asset.setCurrentValue(currentValue);
    return true;
  }
}
