package fr.perso.project.android.powerbapplication

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import fr.perso.project.android.powerbapplication.Utils.CSVFile
import fr.perso.project.android.powerbapplication.Utils.convertStringCSVDoubleToDouble
import fr.perso.project.android.powerbapplication.application.PowerBApplication
import fr.perso.project.android.powerbapplication.model.Account
import fr.perso.project.android.powerbapplication.model.Constants.Companion.CSVFILE_ACCOUNT_NAME_INDEX
import fr.perso.project.android.powerbapplication.model.Constants.Companion.CSVFILE_ACCOUNT_NUMBER_INDEX
import fr.perso.project.android.powerbapplication.model.Constants.Companion.CSVFILE_ACCOUNT_SOLDE_INDEX
import fr.perso.project.android.powerbapplication.model.Transaction
import fr.perso.project.android.powerbapplication.model.enums.ETransactionType
import java.io.InputStream


/**
 * Home Activity with bottom navigation view to navigate between Account, Budget, Saving and Settings Fragments
 *
 * @author : JEAN-LOUIS Thessalène
 */
class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        println("Account list in powerBApp : " + PowerBApplication.getInstance().getAccountList().toString())

        //Retrieve CSV File
        var csvFileRead = retrieveCSVFile()

        //Retrieve account info from csv
        saveAccountToPowerBApp(csvFileRead)

        println("Account list in powerBApp : " + PowerBApplication.getInstance().getAccountList().toString())


        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_budget,
                R.id.navigation_saving,
                R.id.navigation_settings
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when(destination.id){
                R.id.navigation_home -> updateToolbarAndBottomNavigation("MON TITRE DYNAMO", "")
                R.id.navigation_budget -> updateToolbarAndBottomNavigation("BUDGET", "")
                R.id.navigation_saving -> updateToolbarAndBottomNavigation("SAVING", "")
                R.id.navigation_settings -> updateToolbarAndBottomNavigation("SETTINGS", "")
                else -> updateToolbarAndBottomNavigation("AUTRE", " ")
            }
        }
    }

    fun saveAccountToPowerBApp(csvFileRead : List<Any?>){
        val account = retrieveAccountInfoFromCSVFile(csvFileRead)
        account.transactions = retrieveTransactionListFromCSVFile(csvFileRead)?.toList()
        PowerBApplication.getInstance().getAccountList().add(account)
    }

    private fun updateToolbarAndBottomNavigation(title : String, subtitle: String ) {
        supportActionBar?.title = title
        supportActionBar?.subtitle = subtitle
        if(title == "MON TITRE DYNAMO") {
            supportActionBar?.setHomeButtonEnabled(true)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_notifications_black_24dp)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                println("CLICK SUR HOME")
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    /**
     * CSV FIle TREATMENTS
     */
    fun retrieveCSVFile() : List<Any?>{
        val inputStream : InputStream = resources.openRawResource(R.raw.test)
        val csvFile = CSVFile(inputStream)
        val csvFileRead = csvFile.read()
        return csvFileRead
    }

    fun retrieveAccountInfoFromCSVFile(csvFileRead : List<Any?>) : Account{
        println("ACCOUNT NUMBER : " + csvFileRead[CSVFILE_ACCOUNT_NUMBER_INDEX])
        val accountNumber = (csvFileRead[CSVFILE_ACCOUNT_NUMBER_INDEX] as String).trim().toInt()
        val accountName = csvFileRead[CSVFILE_ACCOUNT_NAME_INDEX] as String
        val accountSolde = convertStringCSVDoubleToDouble(csvFileRead[CSVFILE_ACCOUNT_SOLDE_INDEX] as String)
        val accountRetrieved = Account(number = accountNumber, accountName = accountName, solde = accountSolde, transactions = null)

        return accountRetrieved
    }

    fun retrieveTransactionListFromCSVFile(csvFileRead : List<Any?>): MutableList<Transaction>? {
        val transactionList = mutableListOf<Transaction>()
        for(i in 7 until csvFileRead.size step 4){
            val date = csvFileRead[i] as String
            val libelle = csvFileRead[i+1] as String
            val amountDebit = convertStringCSVDoubleToDouble(csvFileRead[i+2] as String)
            val amountCredit = convertStringCSVDoubleToDouble(csvFileRead[i+3] as String)

            println("$i : $date | $libelle | $amountDebit | $amountCredit")

            if(amountDebit != 0.0){
                //println("AMOUNT DEBIT : $amountDebit")
                transactionList.add(Transaction(date, libelle, amountDebit, ETransactionType.DEBIT))
            } else if (amountCredit != 0.0){
                //println("AMOUNT CREDIT : $amountCredit")
                transactionList.add(Transaction(date, libelle, amountCredit, ETransactionType.CREDIT))
            }
        }
        println(transactionList.toString())
        return transactionList

    }

}
