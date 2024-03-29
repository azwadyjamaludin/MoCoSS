package upsi.edu.mocos.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import kotlinx.android.synthetic.main.fragment_setting.view.*
import upsi.edu.mocos.R
import upsi.edu.mocos.model.MiscSetting


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [SettingFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [SettingFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SettingFragment : Fragment(), CompoundButton.OnCheckedChangeListener {

    private var mParam1: String = ""
    private var mParam2: String = ""
    var mEnglishFlag = "en"
    var mMalayFlag = "ms"

    //private var mListener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mParam1 = arguments!!.getString(ARG_PARAM1)
            mParam2 = arguments!!.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_setting, container, false)

        val otherLang = view.textView1
        val BI = view.switch1
        BI.text = "English"
        val BM = view.switch2
        BM.text = "Bahasa Melayu"

        if (MiscSetting.BI) {
            BI.isChecked = true
            BM.isChecked = false
            otherLang.text = getString(R.string.otherLangEN)
            //otherLang.visibility = View.GONE
        }
        if (MiscSetting.BM) {
            BI.isChecked = false
            BM.isChecked = true
            otherLang.text = getString(R.string.otherLangMY)
            //otherLang.visibility = View.GONE
        }

        BI.setOnCheckedChangeListener ({ _: CompoundButton, isChecked ->
            if (BI.isChecked) {
                MiscSetting.BM = false
                MiscSetting.BI = true
            }else {
                MiscSetting.BM = true
                MiscSetting.BI = false
            }
            refreshLayout()
        })
        BM.setOnCheckedChangeListener({ _:CompoundButton, isChecked ->
            if (BM.isChecked){
                MiscSetting.BI = false
                MiscSetting.BM = true
            } else {
                MiscSetting.BI = true
                MiscSetting.BM = false
            }
            refreshLayout()
        })

        return view
    }

    override fun onCheckedChanged(p0: CompoundButton?, p1: Boolean) {

    }

    private fun refreshLayout() {
            val intent: Intent = activity!!.baseContext.packageManager.getLaunchIntentForPackage("upsi.edu.mocos")
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
    }
    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html) for more information.
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
         * @return A new instance of fragment SettingFragment.
         */

        fun newInstance(param1: String, param2: String): SettingFragment {
            val fragment = SettingFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}// Required empty public constructor
