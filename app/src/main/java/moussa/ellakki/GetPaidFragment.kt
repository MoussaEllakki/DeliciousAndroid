package moussa.ellakki

import AllClasses.GetPaidAdapter
import AllClasses.IsThereChanges
import AllClasses.Table
import AllClasses.ViewModelID
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import moussa.ellakki.databinding.FragmentGetPaidBinding


class GetPaidFragment : Fragment() {

    var getPaidAdapter = GetPaidAdapter()
    val model: ViewModelID by activityViewModels()
    lateinit var binding: FragmentGetPaidBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentGetPaidBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        model.listenToTables()
        val haveBroughAllOrder = Observer<IsThereChanges> {
            if (it == IsThereChanges.yes) {
                openView()
                model.isThereChanges.value = IsThereChanges.no
            }
        }
        model.isThereChanges.observe(viewLifecycleOwner, haveBroughAllOrder)
    }

    fun openView() {
        var recyclerViewGetPaid = binding.recyclerViewIGetPaidFragment
        recyclerViewGetPaid.layoutManager = GridLayoutManager(requireActivity(), 3)
        recyclerViewGetPaid.adapter = getPaidAdapter
        sortTables()
    }

    fun sortTables() {

        var tablesFinished = mutableListOf<Table>()
        for (table in model.tables) {
            if (table.orderFinished == true) {
                tablesFinished.add(table)
            }
        }

        if (tablesFinished.size == 0){
            binding.textIGetPaidFragment.text = "No pending payments"
            getPaidAdapter.tables = tablesFinished
            getPaidAdapter.notifyDataSetChanged()
        }
        else{
            getPaidAdapter.tables = tablesFinished
            getPaidAdapter.notifyDataSetChanged()
            binding.textIGetPaidFragment.text = "Choose table to receive payment from"
        }
    }

}