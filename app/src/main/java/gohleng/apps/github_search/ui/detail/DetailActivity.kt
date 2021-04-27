package gohleng.apps.github_search.ui.detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import gohleng.apps.github_search.data.model.Repository
import gohleng.apps.github_search.util.load
import gohleng.apps.itunes_appetiser_apps.databinding.ActivityDetailBinding

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_REPO_ID = "extra_repo_id"
    }

    private lateinit var binding: ActivityDetailBinding
    private val vm: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupObservers()
        setupUIAction()
    }

    private fun initWebView(url: String) {
        binding.wvRepository.loadUrl(url)
    }

    private fun setupObservers() {
        vm.getRepository(intent.getLongExtra(EXTRA_REPO_ID, 0)).observe(this, Observer {
            updateView(it)
        })
    }

    private fun setupUIAction() {
        binding.iconClose.setOnClickListener {
            onBackPressed()
        }
    }

    private fun updateView(repository: Repository) {
        initWebView(repository.html_url)
        binding.txtName.text = repository.name
        binding.txtDescription.text = repository.description
        binding.txtLogin.text = repository.owner?.login
        binding.imgRepository.load(repository.owner?.avatar_url)
    }
}