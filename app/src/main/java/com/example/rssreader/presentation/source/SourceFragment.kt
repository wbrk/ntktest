package com.example.rssreader.presentation.source

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.*
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.example.rssreader.BaseFragment
import com.example.rssreader.R
import com.example.rssreader.data.RssSource
import kotlinx.android.synthetic.main.fragment_source.*

// todo break project into modules by feature?
class SourceFragment : BaseFragment(), SourceView {
    companion object {
        private const val NEW_SOURCE = -1
    }

    private lateinit var presenter: SourcePresenter
    private var newSource = true
    private var source = RssSource(0, "", "")

    override val layout: Int = R.layout.fragment_source

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter = SourcePresenter(this)

        // todo move this logic out of fragment
        val id = SourceFragmentArgs.fromBundle(arguments!!).sourceId
        if (id != NEW_SOURCE) {
            presenter.getById(id)
            setHasOptionsMenu(true)
            newSource = false
        } else {
            (activity as AppCompatActivity).supportActionBar!!.setTitle(R.string.new_screen)
            // fixme: kind of a hack
        }

        fab.setOnClickListener { view ->
            // todo validate
            // todo move this logic out of fragment
            source = source.copy(
                name = name.text.toString(),
                url = url.text.toString()
            )

            // todo move this logic out of fragment
            if (newSource) {
                presenter.add(source)
            } else {
                presenter.update(source)
            }

            Navigation.findNavController(view).popBackStack()
            // todo something bad happens to keyboard
        }
    }

    override fun onStop() {
        super.onStop()
        presenter.stop()
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

    override fun showData(source: RssSource) {
        this.source = source

        name.setText(source.name)
        url.setText(source.url)
    }
}