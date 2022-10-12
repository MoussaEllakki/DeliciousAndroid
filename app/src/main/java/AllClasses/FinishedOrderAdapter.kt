package AllClasses

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import moussa.ellakki.OrderInformationFragment
import moussa.ellakki.R

class FinishedOrderAdapter : RecyclerView.Adapter<FinishedViewHolder>() {

    var  finishedOrder = mutableListOf<Table>()

    override fun getItemCount(): Int {
            return finishedOrder.size
        }

        override fun onBindViewHolder(holder: FinishedViewHolder, position: Int) {
            holder.tableView.text = finishedOrder[position].tableNumber
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FinishedViewHolder {

            val viewHolder = FinishedViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.view_for_table_i_kitchen , parent, false)
            )
            return viewHolder
        }
}

class  FinishedViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val tableView = view.findViewById<TextView>(R.id.table_view_i_kitchen)
}

class DosentFinishedAdapter : RecyclerView.Adapter<DosentFinishedViewHolder>() {

        var  DosentfinishedOrder = mutableListOf<Table>()

    override fun getItemCount(): Int {
            return DosentfinishedOrder.size
        }

        override fun onBindViewHolder(holder: DosentFinishedViewHolder, position: Int) {

            holder.tableView.text = DosentfinishedOrder[position].tableNumber

            holder.itemView.setOnClickListener{

                var bundle = Bundle()
                bundle.putParcelable("table", DosentfinishedOrder[position])

                holder.itemView.findNavController()
                    .navigate(R.id.action_kitchenFragment_to_orderInformationFragment, bundle)

            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DosentFinishedViewHolder {

            val viewHolder = DosentFinishedViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.view_for_table_i_kitchen , parent, false)
            )
            return viewHolder
        }
    }


class  DosentFinishedViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val tableView = view.findViewById<TextView>(R.id.table_view_i_kitchen)


    }


