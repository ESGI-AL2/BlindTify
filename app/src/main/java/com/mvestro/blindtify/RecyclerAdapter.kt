package com.mvestro.blindtify

import android.content.Intent
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mvestro.blindtify.Model.Game
import com.mvestro.blindtify.Model.Item
import kotlinx.android.synthetic.main.playlist_name_uri.view.*
import android.view.LayoutInflater
import androidx.annotation.LayoutRes

class RecyclerAdapter(private val playlists: ArrayList<Item?>) :
    RecyclerView.Adapter<RecyclerAdapter.PlaylistsHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerAdapter.PlaylistsHolder {
        val inflatedView = parent.inflate(R.layout.playlist_name_uri, false)
        return PlaylistsHolder(inflatedView)
    }

    fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
        return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
    }

    override fun getItemCount() = playlists.size

    override fun onBindViewHolder(holder: RecyclerAdapter.PlaylistsHolder, position: Int) {
        val itemPlaylist = playlists[position]
        holder.bindPaylist(itemPlaylist)
    }

    class PlaylistsHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {
        //2
        private var view: View = v
        private var playlist: Item? = null
        private var uri: String = ""

        //3
        init {
            v.setOnClickListener(this)
        }

        //4
        override fun onClick(v: View) {
            val context = itemView.context
            val showPlaylistsIntent = Intent(context, playersNames::class.java)
            Game.uri = v.playlistUri.text.toString()
            context.startActivity(showPlaylistsIntent)
        }

        fun bindPaylist(playlist: Item?) {
            this.playlist = playlist
            view.playlistName.text = playlist?.name
            view.playlistUri.text = playlist?.uri
        }


        companion object {
            //5
            private val PLAYLIST_KEY = "PLAYLIST"
        }
    }
}