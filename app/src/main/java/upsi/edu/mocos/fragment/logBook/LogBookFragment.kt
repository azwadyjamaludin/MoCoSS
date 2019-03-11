package upsi.edu.mocos.fragment.logBook

import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_log_book.view.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import upsi.edu.mocos.R
import upsi.edu.mocos.adapter.listadapter.LogBookListAdapter
import upsi.edu.mocos.adapter.listadapter.LogBookListAdapter2
import upsi.edu.mocos.model.MyData.LogBook
import upsi.edu.mocos.model.MiscSetting
import upsi.edu.mocos.model.MyObject.NumberMgr


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [LogBookFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [LogBookFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class LogBookFragment : Fragment(),View.OnClickListener {
    private lateinit var layoutManager: LinearLayoutManager
    lateinit var logBookTB: Toolbar

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_log_book, container, false)
        val context = view.context
        val logBookRV = view.logBookRV

        layoutManager = LinearLayoutManager(context)
        logBookRV.layoutManager = layoutManager

        if (MiscSetting.user == "tc") {
            attachRVAdapter(view)
        }
        if (MiscSetting.user == "sl"||MiscSetting.user == "gc") {
            attachRVAdapter_2(view)
        }
        return view
    }

    override fun onClick(v: View?) {

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private val ARG_PARAM1 = "param1"
        private val ARG_PARAM2 = "param2"
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LoginFragment.
         */

        fun newInstance(logBook: LogBook): LogBookFragment {
            val fragment = LogBookFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, logBook.tbTitle)
            args.putString(ARG_PARAM2, logBook.weekTab)
            fragment.arguments = args
            return fragment
        }


    }

    private fun attachRVAdapter(view: View) {
        doAsync {
            val numbering = NumberMgr.numInput3()
            val listadapter = LogBookListAdapter(numbering)
            val logBookRV = view.logBookRV

            logBookRV.adapter = listadapter
                /*uiThread {
                    Toast.makeText(view.context,"doAsync",Toast.LENGTH_SHORT).show()
                }*/
            }
    }

    private fun attachRVAdapter_2(view: View) {
        doAsync {
            val numbering = NumberMgr.numInput3()
            val listadapter = LogBookListAdapter2(numbering)
            val logBookRV = view.logBookRV

            logBookRV.adapter = listadapter
                /*uiThread {
                    Toast.makeText(view.context,"doAsync",Toast.LENGTH_SHORT).show()
                }*/
            }
    }

}


