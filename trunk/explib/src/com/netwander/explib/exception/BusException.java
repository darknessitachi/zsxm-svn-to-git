package com.netwander.explib.exception;

public class BusException extends RuntimeException{

	 /**
   * 
   */
  public BusException() {
      super();
      
  }

  /**
   * @param arg0
   */
  public BusException(String arg0) {
      super(arg0);
     
  }

  /**
   * @param arg0
   * @param arg1
   */
  public BusException(String arg0, Throwable arg1) {
      super(arg0, arg1);

  }

  /**
   * @param arg0
   */
  public BusException(Throwable arg0) {
      super(arg0);
   
  }
}
