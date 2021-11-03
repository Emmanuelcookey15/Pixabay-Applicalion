/*
 * Copyright (c) 2018 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.payback.pixabayapplication.presentation.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.payback.domain.model.Hits
import com.payback.pixabayapplication.presentation.view.fragments.SearchFragment
import com.payback.pixabayapplication.R
import kotlinx.android.synthetic.main.item_image_details.view.*


class SearchAdapter(private var hitList: MutableList<Hits>, var context: Context, var listener: SearchFragment.RecyclerItemListener) : RecyclerView.Adapter<SearchAdapter.SearchMoviesHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchMoviesHolder {
    val view = LayoutInflater.from(context).inflate(R.layout.item_image_details, parent, false)

    val viewHolder = SearchMoviesHolder(view)
    view.setOnClickListener { v -> listener.onItemClick(v, viewHolder.bindingAdapterPosition) }
    return viewHolder
  }

  override fun onBindViewHolder(holder: SearchMoviesHolder, position: Int) {
    holder.bind(hitList[position], position)
  }

  override fun getItemCount(): Int {
    return hitList.size
  }

  fun getItemAtPosition(pos: Int): Hits {
    return hitList[pos]
  }

  fun setHitList(list: List<Hits>) {
    this.hitList.clear()
    this.hitList.addAll(list)
    notifyDataSetChanged()
  }



  inner class SearchMoviesHolder(val v: View) : RecyclerView.ViewHolder(v) {

    fun bind(hit: Hits, position: Int) = with(v) {

      user_textview.text = hit.getCapitalizeUser()
      image_tag_textview.text = "Image Tags -> ${hit.tags}"

      Glide.with(v)
        .load(hit.image)
        .into(hit_imageview)

    }
    init {
      v.setOnClickListener { v: View ->
        listener.onItemClick(v, bindingAdapterPosition)
      }
    }
  }
}
