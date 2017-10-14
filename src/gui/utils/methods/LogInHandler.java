package gui.utils.methods;

import gui.utils.TypeConstants;
import model.Customer;
import model.Receptionist;

public class LogInHandler {

    private Customer customerToLog;
    private Receptionist receptionistToLog;
    private boolean admin=false;

    public LogInHandler(String id,String password){


        Customer myCustomer = new Customer(id);
        myCustomer.setPassword(password);
        customerToLog=myCustomer.validateUserAndPass();



        Receptionist myRec = new Receptionist(Integer.valueOf(id));
        myRec.setPassword(password);
        receptionistToLog=myRec.validateUserAndPass();

        if(id.equals(TypeConstants.ADMIN) && password.equals(TypeConstants.ADMIN))
            this.admin=true;


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
