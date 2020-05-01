package fr.perso.project.android.powerbapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import fr.perso.project.android.powerbapplication.R


/**
 * Created on 29/04/2020 - 17:51.
 * TODO: Add a class header comment!
 *
 * @author : JEAN-LOUIS Thessalène
 */

class BottomSheet : BottomSheetDialogFragment() {

    companion object {
        fun newInstance(): BottomSheet =
            BottomSheet().apply {
                /*
                arguments = Bundle().apply {
                    putInt(ARG_ITEM_COUNT, itemCount)
                }
                 */
            }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.bottom_sheet_example_layout, container, false)
    }
}