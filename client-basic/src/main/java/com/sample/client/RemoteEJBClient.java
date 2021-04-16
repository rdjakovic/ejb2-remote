package com.sample.client;

import com.sample.ejb.HelloHome;
import com.sample.ejb.HelloRemote;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.util.Hashtable;


public class RemoteEJBClient {


    public static void main(String[] args) throws Exception {
//        testRemoteOld();
        testRemoteNew();
    }

    private static void testRemoteOld() throws NamingException, RemoteException, CreateException {
//        final HelloHome home = (HelloHome) getRemoteHome(HelloHome.JNDI_NAME); //DOESN'T WORK ->
//        java.lang.ClassCastException: org.wildfly.naming.client.store.RelativeContext cannot be cast to javax.ejb.EJBHome

        // jndi eg. java:global/BeanProject/Bean!demo.BeanRemote
        final String jndi = "java:/ejb-remote-server/HelloBean!com.sample.ejb.HelloHome"; //WORKS for server ->
        //public static final String JNDI_NAME="ejb:ejb-remote-server/HelloBean!com.sample.ejb.HelloRemote";
        //works for both InitialContextFactory and WildFlyInitialContextFactory

        final HelloHome home = (HelloHome) getRemoteHome(jndi);
        HelloRemote remote = home.create();
        String s = remote.echo("Ranko from EJB2");
        System.out.println(s);
    }

    private static void testRemoteNew() throws NamingException, RemoteException {
        // jndi eg. java:global/BeanProject/Bean!demo.BeanRemote
        final String jndi = "java:/ejb-remote-server/HelloBean!com.sample.ejb.HelloRemote"; //WORKS

        final HelloRemote ejb = (HelloRemote) lookupRemoteEJB(jndi); //WORKS
        String s = ejb.echo("Ranko from EJB3");
        System.out.println(s);
    }

    private static EJBHome getRemoteHome(String jndi) {
        EJBHome home = null;
        try {
            home = (EJBHome) lookupRemoteEJB(jndi);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return home;
    }

    private static Object lookupRemoteEJB(String jndi) throws NamingException {
        Context context = createContext();
        Object obj = context.lookup(jndi);
        return obj;
    }

    private static Context createContext() throws NamingException {
        final Hashtable<String, String> jndiProperties = new Hashtable<>();

//        jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
//        jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
//        jndiProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");

        jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
        jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        jndiProperties.put(Context.PROVIDER_URL, "remote+http://localhost:8080");

        return new InitialContext(jndiProperties);
    }

}