package com.sample.ejb;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;
import java.rmi.RemoteException;

public interface HelloHome extends EJBHome {
//    public static final String JNDI_NAME="ejb/HelloRemote"; // stari - Ne radi
    public static final String JNDI_NAME="ejb:ejb-remote-server/HelloBean!com.sample.ejb.HelloRemote";

    public HelloRemote create() throws CreateException, RemoteException;
}
