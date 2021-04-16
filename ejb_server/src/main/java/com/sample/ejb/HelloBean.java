package com.sample.ejb;
 
import javax.ejb.*;
import java.rmi.RemoteException;

public class HelloBean implements SessionBean {
 
    public String echo(String s) {
        return "Hello " + s;
    }

    public void ejbCreate() {
        System.out.println("ejb create");
    }

    @Override
    public void setSessionContext(SessionContext sessionContext) throws EJBException, RemoteException {
        System.out.println("ejb set sessioncontext");
    }

    @Override
    public void ejbRemove() throws EJBException, RemoteException {
        System.out.println("ejb remove");
    }

    @Override
    public void ejbActivate() throws EJBException, RemoteException {
        System.out.println("ejb activate");
    }

    @Override
    public void ejbPassivate() throws EJBException, RemoteException {
        System.out.println("ejb passivate");
    }
}