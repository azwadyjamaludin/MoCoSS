package upsi.edu.mocos.fragment


import android.content.ComponentCallbacks2
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import upsi.edu.mocos.R
import upsi.edu.mocos.activity.InternInfoActivity
import upsi.edu.mocos.model.MiscSetting


/**
 * A simple [Fragment] subclass.
 * Use the [AboutFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AboutFragment : Fragment() {

    private var mParam1: String = ""
    private var mParam2: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mParam1 = arguments!!.getString(ARG_PARAM1)
            mParam2 = arguments!!.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_about, container, false)
        val context = view.context
        val internStdBtn = view.findViewById<Button>(R.id.internStdBtn)

        internStd(internStdBtn)
        enterSIInfo(internStdBtn,context)
        return view
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
         * @return A new instance of fragment AboutFragment.
         */

        fun newInstance(param1: String, param2: String): AboutFragment {
            val fragment = AboutFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }

    private fun internStd (internStdBtn: Button) {
        if (MiscSetting.BI) {
            internStdBtn.text = "Internship Standard"
        }
        if (MiscSetting.BM) {
            internStdBtn.text = "Piawaian Internship"
        }

    }

    private fun enterSIInfo(internStdBtn: Button, context: Context) {
        internStdBtn.setOnClickListener{
            val siInfoIntent = Intent(context,InternInfoActivity::class.java)
            MiscSetting.info = "is"
            startActivity(siInfoIntent)
        }
    }

}// Required empty public constructor
