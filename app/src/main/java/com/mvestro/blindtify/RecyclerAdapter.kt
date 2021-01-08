package com.mvestro.blindtify

import android.content.Intent
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mvestro.blindtify.Model.Playlist.Item
import com.mvestro.blindtify.Model.Playlist.Playlist
import kotlinx.android.synthetic.main.playlist_name_uri.view.*

class RecyclerAdapter(private val playlists: ArrayList<Item?>) : RecyclerView.Adapter<RecyclerAdapter.PlaylistsHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerAdapter.PlaylistsHolder {
        val inflatedView = parent.inflate(R.layout.playlist_name_uri, false)
        return PlaylistsHolder(inflatedView)
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

        //3
        init {
            v.setOnClickListener(this)
        }

        //4
        override fun onClick(v: View) {
            /*val context = itemView.context
            val showPlaylistsIntent = Intent(context, PlaylistsList::class.java)
            showPlaylistsIntent.putExtra(PLAYLIST_KEY, playlist)
            context.startActivity(showPlaylistsIntent)*/
        }

        fun bindPaylist(playlist: Item?) {
            this.playlist = playlist
            view.playlistName.text = playlist?.getName().toString()
            view.playlistUri.text = playlist?.getUri().toString()
        }


        companion object {
            //5
            private val PLAYLIST_KEY = "PLAYLIST"
        }
    }
}