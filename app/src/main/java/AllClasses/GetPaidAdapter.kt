package AllClasses

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import moussa.ellakki.R

class GetPaidAdapter : RecyclerView.Adapter<GetPaidViewHolder>() {

    var tables = mutableListOf<Table>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GetPaidViewHolder {
            val vh = GetPaidViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.view_for_table_i_kitchen, parent, false)
            )
            return vh
        }

    override fun getItemCount(): Int {
            return tables.size
        }

    override fun onBindViewHolder(holder:GetPaidViewHolder, position: Int) {

        holder.tableView.text = tables[position].tableNumber

        holder.itemView.setOnClickListener {


                var bundle = Bundle()
                bundle.putParcelable("table",tables[position])

                holder.itemView.findNavController()
                    .navigate(R.id.action_getPaidFragment_to_infoFragmentToGetPaid , bundle)
        }
    }
}

    class GetPaidViewHolder(view: android.view.View) : RecyclerView.ViewHolder(view) {
        val tableView = view.findViewById<TextView>(R.id.table_view_i_kitchen)
    }



