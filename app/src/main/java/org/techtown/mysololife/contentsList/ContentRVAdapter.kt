package org.techtown.mysololife.contentsList

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.techtown.mysololife.R

/*리사이클러뷰를 위한 어댑터*/
class ContentRVAdapter(val context : Context, val items : ArrayList<ContentModel>, val  keyList : ArrayList<String>) : RecyclerView.Adapter<ContentRVAdapter.Viewholder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentRVAdapter.Viewholder {
        //만들어둔 아이템 가져오기
        val v = LayoutInflater.from(parent.context).inflate(R.layout.content_rv_item,parent,false)
        return Viewholder(v)
    }

    override fun onBindViewHolder(holder: ContentRVAdapter.Viewholder, position: Int) {


        holder.bindItems(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class Viewholder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bindItems(item : ContentModel){

            itemView.setOnClickListener{
                Toast.makeText(context, item.title, Toast.LENGTH_LONG).show()
                val intent = Intent(context, ContentShowActivity::class.java)
                intent.putExtra("url", item.webUrl)
                itemView.context.startActivity(intent)
            }


            val contentTitle = itemView.findViewById<TextView>(R.id.textArea)
            val imageViewArea = itemView.findViewById<ImageView>(R.id.imageArea)
            val bookmarkArea = itemView.findViewById<ImageView>(R.id.bookmakrArea)

            bookmarkArea.setOnClickListener {
                Toast.makeText(context, keyList.toString(), Toast.LENGTH_LONG).show()
            }


            contentTitle.text = item.title
            
            //Glide 적용하기
            Glide.with(context)
                .load(item.imageUrl)
                .into(imageViewArea)

        }
    }
}