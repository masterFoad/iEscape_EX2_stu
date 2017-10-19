package gui.utils;

import controller.SysData;

public class Commons {

    private Commons(){}


        public static  boolean IsLoggedReceptionist(){

            if(SysData.getInstance().getParameter(TypeConstants.LOGGED_RECEPTIONIST)!=null)
            {
                return true;
            }else{
                return false;
            }

        }
        public static boolean IsLoggedCustomer(){
            if(SysData.getInstance().getParameter(TypeConstants.LOGGED_CUSTOMER)!=null){
                return true;
            }
            else{
                return false;
            }
        }
        public static boolean IsLoggedAdmin(){
            if(SysData.getInstance().getParameter(TypeConstants.ADMIN)!=null){
                return true;
            }
            else{
                return false;
            }
        }

    }



