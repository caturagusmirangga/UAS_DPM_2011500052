package com.example.uasdpm2011500052

import android.app.Activity
import android.app.AlertDialog
import android.content.*
import android.view.*
import android.widget.*

class AdapterDataDosen(
    private val getContext: Context,
    private val customListItem:ArrayList<dataCampuss>
): ArrayAdapter <dataCampuss>(getContext,0,customListItem) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var listLayout = convertView
        val holder: ViewHolder
        if (listLayout == null) {
            val inflatelist = (getContext as Activity).layoutInflater
            listLayout = inflatelist.inflate(R.layout.activity_main2, parent, false)
            holder = ViewHolder()
            with(holder) {
                tvNIDN = listLayout.findViewById(R.id.tvNIDN)
                tvNMDOSEN = listLayout.findViewById(R.id.tvNamaDosen)
                tvPROGRAMSTUDI = listLayout.findViewById(R.id.tvPROGRAMSTUDI)
                btnEdit = listLayout.findViewById(R.id.btnEdit)
                btnHapus = listLayout.findViewById(R.id.btnHapus)
            }
            listLayout.tag = holder
        } else
            holder = listLayout.tag as ViewHolder
        val listItem = customListItem[position]
        holder.tvNIDN!!.setText(listItem.NIDN)
        holder.tvNMDOSEN!!.setText(listItem.NMDOSEN)
        holder.tvPROGRAMSTUDI!!.setText(listItem.PROGRAMSTUDI)

        holder.btnEdit!!.setOnClickListener {
            val i = Intent(context, PengentriDataDosen::class.java)
            i.putExtra("kode", listItem.NIDN)
            i.putExtra("nama", listItem.NMDOSEN)
            i.putExtra("jabatan", listItem.JABATAN)
            i.putExtra("golongan_pangkat", listItem.GOLONGANPANGKAT)
            i.putExtra("pendidikan", listItem.PENDIDIKAN)
            i.putExtra("keahlian", listItem.KEAHLIAN)
            i.putExtra("program_studi", listItem.PROGRAMSTUDI)
            context.startActivity(i)
        }

        holder.btnHapus!!.setOnClickListener {
            val db = Campuss(context)
            val alb = AlertDialog.Builder(context)
            val kode = holder.tvNIDN!!.text
            val nama = holder.tvNMDOSEN!!.text
            val PROGRAM_STUDI = holder.tvPROGRAMSTUDI!!.text
            with(alb) {
                setTitle("Konfirmasi Penghapusan")
                setCancelable(false)
                setMessage(""" Apakah Anda yakin akan menghapus data ini?
                    $nama [$kode-$PROGRAM_STUDI]
                """.trimIndent())
                setPositiveButton("Ya") {_, _->
                    if (db.hapus("$kode"))
                        Toast.makeText(
                            context,
                            "Data Dosen berhasil dihapus",
                            Toast.LENGTH_SHORT
                        ).show()
                }
                setNegativeButton("Tidak", null)
                create().show()
            }
        }
        return listLayout!!
    }
    class ViewHolder {
        internal var tvNIDN: TextView? = null
        internal var tvNMDOSEN: TextView? = null
        internal var tvPROGRAMSTUDI: TextView? = null
        internal var btnEdit: ImageButton? = null
        internal var btnHapus: ImageButton? = null
    }
}