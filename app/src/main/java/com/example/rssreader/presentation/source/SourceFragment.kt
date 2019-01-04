package com.example.rssreader.presentation.source

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.*
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.example.rssreader.R
import com.example.rssreader.data.RssSource
import kotlinx.android.synthetic.main.fragment_source.*

class SourceFragment : Fragment() {
    companion object {
        private const val NEW_SOURCE = -1
    }

    private val presenter = SourcePresenter()
    private var newSource = true
    private var source = RssSource(0, "", "")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_source, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (!newSource) {
            name.setText(source.name)
            url.setText(source.url)
        }

        fab.setOnClickListener { view ->
            // todo validate
            source = source.copy(
                name = name.text.toString(),
                url = url.text.toString()
            )

            if (newSource) {
                presenter.add(source)
            } else {
                presenter.update(source)
            }

            Navigation.findNavController(view).popBackStack()
            // todo something bad happens to keyboard
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val id = SourceFragmentArgs.fromBundle(arguments!!).sourceId
        if (id != NEW_SOURCE) {
            source = presenter.getById(id)
            setHasOptionsMenu(true)
            newSource = false
        } else {
            (activity as AppCompatActivity).supportActionBar!!.setTitle(R.string.new_screen)
            // fixme: kind of a hack
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) =
        inflater.inflate(R.menu.menu_source, menu)

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when(item.itemId) {
        R.id.remove -> {
            presenter.delete(source)
            NavHostFragment.findNavController(this).popBackStack()
            true
        }

        else -> super.onOptionsItemSelected(item)
    }
}