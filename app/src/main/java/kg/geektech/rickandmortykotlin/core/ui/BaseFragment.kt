package kg.geektech.rickandmortykotlin.core.ui

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.viewbinding.ViewBinding
import kg.geektech.rickandmortykotlin.R

abstract class BaseFragment<VM : BaseViewModel, VB : ViewBinding> : Fragment() {

    protected abstract val viewModel: VM
    protected lateinit var binding: VB
    private var _view: View? = null
    protected lateinit var navController: NavController

    protected abstract fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): VB

    protected abstract fun bindViewBinding(view: View): VB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (_view == null) {
            binding = inflateViewBinding(inflater, container, savedInstanceState)
            _view = binding.root
        } else {
            binding = bindViewBinding(_view!!)
        }
        return _view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navHostFragment =
            activity?.supportFragmentManager?.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController

        chekInternet(requireContext())
        initView()
        initAdapter()
        initViewModel()
        initListener()
    }

    open fun initViewModel() {}

    open fun initListener() {}

    open fun initView() {}

    private fun chekInternet(context: Context) {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkChangeFilter = NetworkRequest.Builder().build()
        cm.registerNetworkCallback(networkChangeFilter,
            object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    activity?.runOnUiThread {
                        connectedToTheInternet()
                    }
                }

                override fun onLost(network: Network) {
                    activity?.runOnUiThread {
                        noInternetConnection()
                    }
                }
            }
        )
    }

    open fun noInternetConnection() {}

    open fun connectedToTheInternet() {}

    open fun initAdapter() {}

    protected fun navigateFragment(resId: Int, bundle: Bundle? = null) {
        navController.navigate(resId, bundle)
    }
}