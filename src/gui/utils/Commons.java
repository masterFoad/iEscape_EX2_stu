package gui.utils;

import controller.SysData;
import model.Customer;

import java.util.ArrayList;

public class Commons {

    private Commons(){}


        public static  boolean isLoggedReceptionist(){

            if(SysData.getInstance().getParameter(TypeConstants.LOGGED_RECEPTIONIST)!=null)
            {
                return true;
            }else{
                return false;
            }

        }
        public static boolean isLoggedCustomer(){
            if(SysData.getInstance().getParameter(TypeConstants.LOGGED_CUSTOMER)!=null){
                return true;
            }
            else{
                return false;
            }
        }
        public static boolean isLoggedAdmin(){
            if(SysData.getInstance().getParameter(TypeConstants.ADMIN)!=null){
                return true;
            }
            else{
                return false;
            }
        }

    public static boolean isSelectedCustomers(){
        if(SysData.getInstance().getParameter(TypeConstants.SELECTED_CUSTOMERS)!=null){
            return true;
        }
        else{
            return false;
        }
    }

    public static ArrayList<Customer> getSelectedCustomers(){
        if(SysData.getInstance().getParameter(TypeConstants.SELECTED_CUSTOMERS)!=null) {
            return (ArrayList<Customer>)SysData.getInstance().getParameter(TypeConstants.SELECTED_CUSTOMERS);
        }
        else{
            return null;
        }
    }

    public static void clearSelectedCustomers(){
        if(SysData.getInstance().getParameter(TypeConstants.SELECTED_CUSTOMERS)!=null) {
             SysData.getInstance().removeParameter(TypeConstants.SELECTED_CUSTOMERS);
        }

    }
    public static void setEditingCustomerOn(){

        SysData.getInstance().setParameter(TypeConstants.EDITING_CUSTOMER,true);
    }

    public static void setEditingCustomerOff(){

        SysData.getInstance().setParameter(TypeConstants.EDITING_CUSTOMER,false);
    }

    public static boolean isEditingCustomer(){

        if( SysData.getInstance().getParameter(TypeConstants.EDITING_CUSTOMER)!=null){
            return  (boolean)SysData.getInstance().getParameter(TypeConstants.EDITING_CUSTOMER);
        }
        else {
            return false;
        }

    }

    }



