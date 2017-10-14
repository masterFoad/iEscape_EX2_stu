package gui.utils.methods;

import gui.utils.TypeConstants;
import model.Customer;
import model.Receptionist;

import java.net.MalformedURLException;
import java.net.URL;

public class LogInHandler {

    private Customer customerToLog;
    private Receptionist receptionistToLog;
    private boolean admin=false;

    public LogInHandler(String id,String password){

        Receptionist myRec =null;
        Customer myCustomer = null;
        try{
            int recId= Integer.parseInt(id);
            myRec = new Receptionist(recId);
            myRec.setPassword(password);
            receptionistToLog=myRec.validateUserAndPass();

        }catch (NumberFormatException e){

            try {
                myCustomer = new Customer();
                myCustomer.setEmail(new URL(id));
                myCustomer.setPassword(password);
                customerToLog=myCustomer.validateUserAndPass();


            } catch (MalformedURLException e1) {

            }




        }finally {

            if(id.equals(TypeConstants.ADMIN) && password.equals(TypeConstants.ADMIN))
                this.admin=true;
        }



    }

    public Object getUser(){

        if(customerToLog!=null)
            return customerToLog;

        if(receptionistToLog!=null)
            return receptionistToLog;

        if(admin)
            return admin;

        return null;

    }



}
