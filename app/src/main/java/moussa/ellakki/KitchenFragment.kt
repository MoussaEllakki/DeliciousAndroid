package moussa.ellakki

import AllClasses.*
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import moussa.ellakki.databinding.FragmentKitchenBinding
import moussa.ellakki.databinding.FragmentSignInBinding


class KitchenFragment : Fragment() {


    lateinit var binding: FragmentKitchenBinding
    val model: ViewModelID by activityViewModels()
    var dosentFinsihedOdersList = mutableListOf<Table>()
    var  finishedOrdersList = mutableListOf<Table>()
    val finishedAdapter = FinishedOrderAdapter()
    val dosentFinishedAdapter = DosentFinishedAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentKitchenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model.listenToTables()

        val haveBroughAllOrder = Observer<IsThereChanges> {
            if (it == IsThereChanges.yes) {
                openView()
                sortTables()
            }
        }
        model.isThereChanges.observe(viewLifecycleOwner, haveBroughAllOrder)
    }

    fun sortTables() {

        finishedOrdersList.clear()
        dosentFinsihedOdersList.clear()

        for (table in model.tables){

            if (table.haveOrder == true){

                if (table.orderFinished == true){
                    finishedOrdersList.add(table)
                }
                else  if (table.orderFinished == false){
                    dosentFinsihedOdersList.add(table)
                }
            }
        }
        finishedAdapter.finishedOrder = finishedOrdersList
        dosentFinishedAdapter.DosentfinishedOrder = dosentFinsihedOdersList
        finishedAdapter.notifyDataSetChanged()
        dosentFinishedAdapter.notifyDataSetChanged()
       model.isThereChanges.value = IsThereChanges.no
    }

    fun openView(){
        val finishedRecyclerView  = binding.finishedRecyclerView
        finishedRecyclerView.layoutManager = LinearLayoutManager(requireActivity())
        finishedRecyclerView.adapter = finishedAdapter
        val dosentFinishedRecyclerView = binding.dosentFinishedRecyclerView
        dosentFinishedRecyclerView.layoutManager = LinearLayoutManager(requireActivity())
        dosentFinishedRecyclerView.adapter = dosentFinishedAdapter
    }


}