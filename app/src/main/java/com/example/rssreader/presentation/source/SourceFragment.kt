package com.example.rssreader.presentation.source

import android.os.Bundle
import android.view.*
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.rssreader.BaseFragment
import com.example.rssreader.R
import com.example.rssreader.domain.entity.RssSource
import kotlinx.android.synthetic.main.fragment_source.*

// todo break project into modules by feature?
class SourceFragment : BaseFragment(), SourceView {
    companion object {
        private const val NEW_SOURCE = -1
    }

    private lateinit var presenter: SourcePresenter
    private var newSource = true
    private var source = RssSource(0, "", "")

    private val args: SourceFragmentArgs by navArgs()

    override val layout: Int = R.layout.fragment_source

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter = SourcePresenter(this)

        setNavigationIcon(R.drawable.ic_arrow_back_24dp)

        // todo move this logic out of fragment
        val id = args.sourceId
        if (id != NEW_SOURCE) {
            presenter.getById(id) // todo fix title flickering due to db load pause (use progressbar)
            setHasOptionsMenu(true)
            newSource = false
        } else {
            setTitle(R.string.new_screen)
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

            view.findNavController().popBackStack()
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
            navController.popBackStack()
            true
        }

        else -> super.onOptionsItemSelected(item)
    }

    override fun showData(source: RssSource) {
        this.source = source

        setTitle(source.name)
        name.setText(source.name)
        url.setText(source.url)
    }
}