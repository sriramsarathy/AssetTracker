package com.controller;



import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;



public class AppListener implements ServletContextListener {

  @Override
  public void contextDestroyed(ServletContextEvent arg0) {
//    try {
////      DAOHelper.end();
//    } catch (IOException | SQLException e) {
//      System.out.println("App Listener Exception End :" +  e.getMessage());
//    }
  }

  @Override
  public void contextInitialized(ServletContextEvent arg0) {

//      DAOHelper.init();

  }
}
