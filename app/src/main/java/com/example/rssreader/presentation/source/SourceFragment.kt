package com.example.rssreader.presentation.source

import android.os.Bundle
import android.view.*
import androidx.navigation.fragment.navArgs
import com.example.rssreader.presentation.BaseFragment
import com.example.rssreader.R
import com.example.rssreader.domain.entity.RssSource
import com.example.rssreader.extensions.setSelectionToEnd
import com.example.rssreader.extensions.showKeyboardForView
import kotlinx.android.synthetic.main.fragment_source.*

// todo break project into modules by feature?
class SourceFragment : BaseFragment(), SourceView {
    private lateinit var presenter: SourcePresenter

    private val args: SourceFragmentArgs by navArgs()

    override val layout: Int = R.layout.fragment_source

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter = SourcePresenter(args.sourceId, this)
        presenter.start()

        setNavigationIcon(R.drawable.ic_arrow_back_24dp)
        setHasOptionsMenu(true)

        fab.setOnClickListener {
            presenter.onSaveChangesClick(
                name.text.toString(),
                url.text.toString()
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.stop()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_source, menu)
        menu.findItem(R.id.remove).isVisible = presenter.shouldShowDeleteAction
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when(item.itemId) {
        R.id.remove -> {
            presenter.onDeleteClick()
            true
        }

        else -> super.onOptionsItemSelected(item)
    }

    override fun goBack() {
        navController.popBackStack()
    }

    override fun showNewSource() {
        setTitle(R.string.new_screen)
        requireActivity().showKeyboardForView(name)
    }

    override fun showExistingSource(source: RssSource) {
        setTitle(source.name)
        name.setText(source.name)
        url.setText(source.url)

        name.setSelectionToEnd()
        requireActivity().showKeyboardForView(name)
    }
}