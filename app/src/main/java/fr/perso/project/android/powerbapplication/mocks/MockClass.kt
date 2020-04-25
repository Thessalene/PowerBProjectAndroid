package fr.perso.project.android.powerbproject.mocks

import fr.perso.project.android.powerbproject.model.Account
import fr.perso.project.android.powerbproject.model.Bank
import fr.perso.project.android.powerbproject.model.Transaction
import fr.perso.project.android.powerbapplication.model.enums.EAccountCategory
import fr.perso.project.android.powerbproject.model.enums.EBankName
import fr.perso.project.android.powerbproject.model.enums.ETransactionType
import java.util.*

/**
 * Created on 25/04/2020 - 11:50.
 * Mock class to create mocked objects
 *
 * @author : JEAN-LOUIS Thessalène
 */
class MockClass {
    companion object{
        @JvmStatic
        fun mockBankList() : MutableList<Bank>{
            var bankList = ArrayList<Bank>()
            for(i in 1..3){
                bankList.add(Bank(EBankName.values()[i], mockAccountList()))
            }
            return bankList
        }
        @JvmStatic
        fun mockAccountList() : List<Account>{
            //Create Transaction list
            var transactionList = ArrayList<Transaction>()
            var transactionList2 = ArrayList<Transaction>()
            var transactionList3 = ArrayList<Transaction>()

            var calendar = Calendar.getInstance()

            for(i in 1..10){
                calendar.add(Calendar.DAY_OF_WEEK, i*2)
                transactionList.add(Transaction(calendar,"Transac C $i", i*12, ETransactionType.CREDIT))
                transactionList.add(Transaction(calendar,"Transac D $i", i*10-4, ETransactionType.DEBIT))
                transactionList.add(Transaction(calendar,"Transac C $i", i*4, ETransactionType.CREDIT))
                transactionList.add(Transaction(calendar,"Transac D $i", i*7, ETransactionType.DEBIT))
            }

            for(i in 1..5){
                calendar.add(Calendar.DAY_OF_WEEK, i*4)
                transactionList2.add(Transaction(calendar,"Transac C $i", i*5, ETransactionType.CREDIT))
                transactionList2.add(Transaction(calendar,"Transac D $i", i*4-4, ETransactionType.DEBIT))
            }

            for(i in 1..3){
                calendar.add(Calendar.DAY_OF_WEEK, i*8)
                transactionList2.add(Transaction(calendar,"Transac C $i", i*2, ETransactionType.CREDIT))
                transactionList2.add(Transaction(calendar,"Transac D $i", i*8-5, ETransactionType.DEBIT))
            }

            var accountList = ArrayList<Account>()

            for(i in 1..2){
                accountList.add(Account(123+i,"Compte courant ${i}",124*i,
                    EAccountCategory.CURRENT, transactionList))
                //accountList.add(Account(123+i,"Compte courant ${i}",15*i,EAccountCategory.CURRENT, transactionList2))
                accountList.add(Account(123+i,"Compte épargne ${i}",1574*i,
                    EAccountCategory.SAVING, transactionList3))
                //accountList.add(Account(123+i,"Compte épargne ${i}",140*i,EAccountCategory.SAVING, transactionList))
                accountList.add(Account(123+i,"Compte prévisionnel ${i}",1857+i,
                    EAccountCategory.FORECAST, transactionList2))
                accountList.add(Account(123+i,"Credit ${i}",1124*i,
                    EAccountCategory.CREDIT, transactionList3))
            }
            return accountList
        }
    }
}