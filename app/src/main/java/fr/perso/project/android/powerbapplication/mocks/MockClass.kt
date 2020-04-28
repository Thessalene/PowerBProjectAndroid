package fr.perso.project.android.powerbproject.mocks

import fr.perso.project.android.powerbapplication.model.Account
import fr.perso.project.android.powerbproject.model.Bank
import fr.perso.project.android.powerbapplication.model.Transaction
import fr.perso.project.android.powerbapplication.model.enums.EAccountCategory
import fr.perso.project.android.powerbapplication.model.enums.ETransactionCategory
import fr.perso.project.android.powerbproject.model.enums.EBankName
import fr.perso.project.android.powerbapplication.model.enums.ETransactionType
import fr.perso.project.android.powerbproject.Utils.DateUtils.Companion.formatCalendarDateToFrDate
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
                transactionList.add(Transaction(formatCalendarDateToFrDate(calendar),"Transac C $i", i*12, ETransactionType.CREDIT))
                transactionList.add(Transaction(formatCalendarDateToFrDate(calendar),"Transac D $i", i*10-4, ETransactionType.DEBIT))
                transactionList.add(Transaction(formatCalendarDateToFrDate(calendar),"Transac C $i", i*4, ETransactionType.CREDIT))
                transactionList.add(Transaction(formatCalendarDateToFrDate(calendar),"Transac D $i", i*7, ETransactionType.DEBIT))
            }

            for(i in 1..5){
                calendar.add(Calendar.DAY_OF_WEEK, i*4)
                transactionList2.add(Transaction(formatCalendarDateToFrDate(calendar),"Transac C $i", i*5, ETransactionType.CREDIT))
                transactionList2.add(Transaction(formatCalendarDateToFrDate(calendar),"Transac D $i", i*4-4, ETransactionType.DEBIT))
            }

            for(i in 1..3){
                calendar.add(Calendar.DAY_OF_WEEK, i*8)
                transactionList2.add(Transaction(formatCalendarDateToFrDate(calendar),"Transac C $i", i*2, ETransactionType.CREDIT))
                transactionList2.add(Transaction(formatCalendarDateToFrDate(calendar),"Transac D $i", i*8-5, ETransactionType.DEBIT))
            }

            var accountList = ArrayList<Account>()

            for(i in 1..1){
                accountList.add(
                    Account(123+i,"Compte courant ${i}",124*i,
                    EAccountCategory.CURRENT, transactionList)
                )
                //accountList.add(Account(123+i,"Compte courant ${i}",15*i,EAccountCategory.CURRENT, transactionList2))
                accountList.add(
                    Account(123+i,"Compte épargne ${i}",1574*i,
                    EAccountCategory.SAVING, transactionList3, EBankName.CREDIT_AGRICOLE)
                )
                //accountList.add(Account(123+i,"Compte épargne ${i}",140*i,EAccountCategory.SAVING, transactionList))
                accountList.add(
                    Account(123+i,"Compte prévisionnel ${i}",1857+i,
                    EAccountCategory.FORECAST, transactionList2)
                )
                accountList.add(
                    Account(123+i,"Credit ${i}",1124*i,
                    EAccountCategory.CREDIT, transactionList3, EBankName.CREDIT_MUTUEL)
                )
            }
            return accountList
        }

        @JvmStatic
        fun mockAccountList2() : List<Account>{
            //Create Transaction list
            var transactionList = ArrayList<Transaction>()
            var transactionList2 = ArrayList<Transaction>()
            var transactionList3 = ArrayList<Transaction>()

            var calendar = Calendar.getInstance()

            for(i in 1..10){
                calendar.add(Calendar.DAY_OF_WEEK, i*2)
                transactionList.add(Transaction(formatCalendarDateToFrDate(calendar),"Transac C $i", i*12, ETransactionType.CREDIT))
                transactionList.add(Transaction(formatCalendarDateToFrDate(calendar),"Transac D $i", i*10-4, ETransactionType.DEBIT))
                transactionList.add(Transaction(formatCalendarDateToFrDate(calendar),"Transac C $i", i*4, ETransactionType.CREDIT))
                transactionList.add(Transaction(formatCalendarDateToFrDate(calendar),"Transac D $i", i*7, ETransactionType.DEBIT))
            }

            for(i in 1..5){
                calendar.add(Calendar.DAY_OF_WEEK, i*4)
                transactionList2.add(Transaction(formatCalendarDateToFrDate(calendar),"Transac C $i", i*5, ETransactionType.CREDIT))
                transactionList2.add(Transaction(formatCalendarDateToFrDate(calendar),"Transac D $i", i*4-4, ETransactionType.DEBIT))
            }

            for(i in 1..3){
                calendar.add(Calendar.DAY_OF_WEEK, i*8)
                transactionList2.add(Transaction(formatCalendarDateToFrDate(calendar),"Transac C $i", i*2, ETransactionType.CREDIT))
                transactionList2.add(Transaction(formatCalendarDateToFrDate(calendar),"Transac D $i", i*8-5, ETransactionType.DEBIT))
            }

            var accountList = ArrayList<Account>()

                accountList.add(
                    Account(1234,"Compte courant 1",124,
                    EAccountCategory.CURRENT, transactionList)
                )
                //accountList.add(Account(123+i,"Compte courant ${i}",15*i,EAccountCategory.CURRENT, transactionList2))
                accountList.add(
                    Account(1235,"Compte épargne 1",1574,
                    EAccountCategory.SAVING, transactionList3, EBankName.CREDIT_AGRICOLE)
                )

            return accountList
        }
        @JvmStatic
        fun mockAccountWithTransactionCategorie() : Account {
            var transactionList = ArrayList<Transaction>()
            val calendar = Calendar.getInstance()
            for(i in 1..5){
                calendar.add(Calendar.DAY_OF_WEEK, i*4)
                transactionList.add(Transaction(formatCalendarDateToFrDate(calendar), "Transac n° $i", i*8-7, ETransactionType.DEBIT, ETransactionCategory.DIVERTISSEMENT))
            }
            for(i in 1..3){
                calendar.add(Calendar.DAY_OF_WEEK, i*4)
                transactionList.add(Transaction(formatCalendarDateToFrDate(calendar), "Transac autres $i", i*3, ETransactionType.DEBIT, ETransactionCategory.AUTRE))
            }
            transactionList.add(Transaction(formatCalendarDateToFrDate(calendar), "Transac logement", 580, ETransactionType.DEBIT, ETransactionCategory.LOGEMENT))
            transactionList.add(Transaction(formatCalendarDateToFrDate(calendar), "Bank debits", 19, ETransactionType.DEBIT, ETransactionCategory.BANK))
            transactionList.add(Transaction(formatCalendarDateToFrDate(calendar), "Virement", 9, ETransactionType.CREDIT, ETransactionCategory.AUTRE))
            transactionList.add(Transaction(formatCalendarDateToFrDate(calendar), "APL CAF", 9, ETransactionType.CREDIT, ETransactionCategory.LOGEMENT))
            transactionList.add(Transaction(formatCalendarDateToFrDate(calendar), "Virement Externe", 95, ETransactionType.DEBIT, ETransactionCategory.AUTRE))
            transactionList.add(Transaction(formatCalendarDateToFrDate(calendar), "Licence", 120, ETransactionType.DEBIT, ETransactionCategory.SPORT))
            transactionList.add(Transaction(formatCalendarDateToFrDate(calendar), "Abonnement bus", 15, ETransactionType.DEBIT, ETransactionCategory.TRANSPORT))
            transactionList.add(Transaction(formatCalendarDateToFrDate(calendar), "SARL Inconnue", 285, ETransactionType.DEBIT, ETransactionCategory.UNKNOWN))

            return Account(120548, "Mon compte analyse test", 1548, EAccountCategory.CURRENT, transactionList)
        }
    }
}