package com.la.pampa.phone.catalog.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.card.MaterialCardView
import com.la.pampa.phone.catalog.R
import com.la.pampa.phone.catalog.databinding.ActivityMainBinding
import com.la.pampa.phone.catalog.domain.models.Phone
import com.la.pampa.phone.catalog.presentation.common.MarginItemDecoration
import com.la.pampa.phone.catalog.presentation.form.FormDialogFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel
import thevoid.whichbinds.dslist.listDSL

class MainActivity : AppCompatActivity() {


    private val viewModel by viewModel<PhoneViewModel>()
    private lateinit var binding: ActivityMainBinding


    @OptIn(ExperimentalCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getPhones()

        binding.addFloatingBtn.setOnClickListener {
            val dialog = FormDialogFragment()
            dialog.show(supportFragmentManager, "FormDialogFragment")
        }

        setUpPhones()
    }

    @SuppressLint("SetTextI18n")
    @ExperimentalCoroutinesApi
    private fun setUpPhones() {
        binding.phonesRecyclerView.layoutManager = LinearLayoutManager(
            baseContext,
            LinearLayoutManager.VERTICAL,
            false
        )

        binding.phonesRecyclerView.addItemDecoration(
            MarginItemDecoration(
                spaceSize = resources.getDimensionPixelSize(R.dimen.space_default),
                spaceStart = resources.getDimensionPixelSize(R.dimen.space_default),
                spaceEnd = resources.getDimensionPixelSize(R.dimen.space_default),
                orientation = GridLayoutManager.VERTICAL
            )
        )

        listDSL<Int, Phone> {

            init {
                recyclerView = binding.phonesRecyclerView
            }

            observe(viewModel.phones) { phone ->

                ul {
                    phone?.forEach {
                        li {
                            content = it
                            viewType = R.layout.item_phone
                            viewBind { _, value, itemView, _ ->

                                val phoneImage: AppCompatImageView? =
                                    itemView.findViewById(R.id.phone_imageView)
                                val name: TextView? =
                                    itemView.findViewById(R.id.name_textView)
                                val manufacturer: TextView? =
                                    itemView.findViewById(R.id.manufacturer_textView)
                                val processor: TextView? =
                                    itemView.findViewById(R.id.processor_textView)
                                val ram: TextView? =
                                    itemView.findViewById(R.id.ram_textView)
                                val screen: TextView? =
                                    itemView.findViewById(R.id.screen_textView)

                                name?.text = value?.name ?: ""
                                manufacturer?.text = value?.manufacturer ?: ""
                                processor?.text = value?.processor ?: ""
                                ram?.text = "${value?.ram ?: "" } de ram"
                                screen?.text = "${value?.screen ?: "" }'"

                                itemView.setOnClickListener {

                                }
                            }
                        }
                    }
                }
            }
        }
    }

}