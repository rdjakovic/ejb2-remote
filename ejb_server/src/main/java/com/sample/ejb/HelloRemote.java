package com.sample.ejb;

import javax.ejb.EJBObject;
import java.rmi.RemoteException;

public interface HelloRemote extends EJBObject {
     public String echo(String s) throws RemoteException;
 
}