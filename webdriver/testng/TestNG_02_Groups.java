package testng;

import org.testng.Assert;
import org.testng.annotations.*;

public class TestNG_02_Groups {
    @Test(groups = "payment", description = "Create new customer with all information", priority = 3)
    public void TC_01_CreateNewUser(){
        System.out.println("Run Testcase 01");
    }
    @Test(groups = "payment", description = "Edit customer with all information", dependsOnMethods = "TC_01_CreateNewUser")
    public void TC_02_EditUser(){
        System.out.println("Run Testcase 02");
    }
    @Test(groups = "customer", description = "Delete customer with all information", priority = 1, dependsOnMethods = "TC_02_EditUser")
    public void TC_03_ViewUser(){
        System.out.println("Run Testcase 03");
    }
    @Test(groups = "customer", priority = 2, dependsOnMethods = "TC_03_ViewUser")
    public void TC_04_DeleteUser(){
        System.out.println("Run Testcase 04");
    }
    @Test(groups = "order", priority = 4, dependsOnMethods = "TC_04_DeleteUser")
    public void TC_05_CheckUserNotExisting(){
        System.out.println("Run Testcase 05");
    }
    @Test(groups = "order", dependsOnMethods = "TC_05_CheckUserNotExisting")
    public void TC_06(){
        System.out.println("Run Testcase 06");
    }
}
