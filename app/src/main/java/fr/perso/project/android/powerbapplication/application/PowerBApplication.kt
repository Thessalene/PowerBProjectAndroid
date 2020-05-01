package fr.perso.project.android.powerbapplication.application

import android.app.Application
import fr.perso.project.android.powerbapplication.model.Account

/**
 * Created on 28/04/2020 - 19:54.
 * TODO: Add a class header comment!
 *
 * @author : JEAN-LOUIS Thessalène
 */
class PowerBApplication : Application() {

    private var accountList : MutableList<Account>? = null

    fun initFBSdk() {

    }

    fun setAccountList(accountListToSet : MutableList<Account>){
        this.accountList = accountListToSet
    }

    fun getAccountList() : MutableList<Account>{
        if(this.accountList == null){
            setAccountList(mutableListOf())
        }
        return this.accountList!!
    }

    companion object {
        // Create the instance
        private var instance: PowerBApplication? = null

        //Or just call context.getApplication().
        fun getInstance(): PowerBApplication {
            if (instance == null) {
                synchronized(PowerBApplication::class.java) {
                    if (instance == null)
                        instance = PowerBApplication()
                }
            }
            // Return the instance
            return instance!!
        }




    }
}