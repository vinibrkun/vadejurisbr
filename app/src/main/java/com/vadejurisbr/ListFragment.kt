package com.vadejurisbr

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListFragment : Fragment() {

    private lateinit var adapter: LawAdapter
    private var allLaws = mutableListOf<LawModel>()
    private var category: String = ""

    companion object {
        fun newInstance(category: String): ListFragment {
            val fragment = ListFragment()
            val args = Bundle()
            args.putString("category", category)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        category = arguments?.getString("category") ?: ""
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)
        
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        val searchEditText = view.findViewById<EditText>(R.id.searchEditText)

        setupData()

        adapter = LawAdapter(allLaws) { law ->
            if (law.category == "Jurisprudência") {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(law.fileName))
                startContext(intent)
            } else {
                val intent = Intent(context, ReaderActivity::class.java)
                intent.putExtra("LAW_ID", law.id)
                intent.putExtra("LAW_TITLE", law.title)
                intent.putExtra("FILE_NAME", law.fileName)
                startActivity(intent)
            }
        }

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filter(s.toString())
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        return view
    }

    private fun startContext(intent: Intent) {
        try {
            startActivity(intent)
        } catch (e: Exception) {
            // Tratar erro se não houver navegador
        }
    }

    private fun filter(text: String) {
        val filteredList = allLaws.filter { 
            it.title.contains(text, ignoreCase = true) 
        }
        adapter.updateList(filteredList)
    }

    private fun setupData() {
        allLaws.clear()
        if (category == "Legislação") {
            allLaws.add(LawModel("1", "Constituição Federal (CF/88)", "cf.html", "Legislação"))
            allLaws.add(LawModel("2", "LINDB", "lindb.html", "Legislação"))
            allLaws.add(LawModel("3", "Código Civil", "cc.html", "Legislação"))
            allLaws.add(LawModel("4", "Código de Processo Civil", "cpc.html", "Legislação"))
            allLaws.add(LawModel("5", "Código Penal", "cp.html", "Legislação"))
            allLaws.add(LawModel("6", "Código de Processo Penal", "cpp.html", "Legislação"))
            allLaws.add(LawModel("7", "Lei de Introdução ao Código Penal", "licp.html", "Legislação"))
            allLaws.add(LawModel("8", "Lei das Contravenções Penais", "lcp.html", "Legislação"))
            allLaws.add(LawModel("9", "Lei de Execução Penal (LEP)", "lep.html", "Legislação"))
            allLaws.add(LawModel("10", "Lei dos Crimes Hediondos", "hediondos.html", "Legislação"))
            allLaws.add(LawModel("11", "Lei dos Crimes contra a Ordem Tributária", "tributarios.html", "Legislação"))
            allLaws.add(LawModel("12", "Lei Antidrogas", "drogas.html", "Legislação"))
            allLaws.add(LawModel("13", "Código Tributário Nacional (CTN)", "ctn.html", "Legislação"))
            allLaws.add(LawModel("14", "Código de Defesa do Consumidor (CDC)", "cdc.html", "Legislação"))
            allLaws.add(LawModel("15", "Código Eleitoral", "eleitoral.html", "Legislação"))
            allLaws.add(LawModel("16", "Código Florestal", "florestal.html", "Legislação"))
            allLaws.add(LawModel("17", "CLT", "clt.html", "Legislação"))
            allLaws.add(LawModel("18", "Estatuto da Criança e do Adolescente (ECA)", "eca.html", "Legislação"))
            allLaws.add(LawModel("19", "Estatuto da Pessoa Idosa", "idoso.html", "Legislação"))
            allLaws.add(LawModel("20", "Estatuto da Igualdade Racial", "racial.html", "Legislação"))
            allLaws.add(LawModel("21", "Estatuto da Pessoa com Deficiência", "pcd.html", "Legislação"))
            allLaws.add(LawModel("22", "Estatuto da Juventude", "juventude.html", "Legislação"))
            allLaws.add(LawModel("23", "Estatuto da Cidade", "cidade.html", "Legislação"))
            allLaws.add(LawModel("24", "Lei Maria da Penha", "maria_penha.html", "Legislação"))
            allLaws.add(LawModel("25", "LDB (Educação)", "ldb.html", "Legislação"))
            allLaws.add(LawModel("26", "Lei de Licitações (Nova)", "licitacoes.html", "Legislação"))
            allLaws.add(LawModel("27", "Lei de Responsabilidade Fiscal (LRF)", "lrf.html", "Legislação"))
            allLaws.add(LawModel("28", "Servidores Públicos (Lei 8112)", "servidores.html", "Legislação"))
            allLaws.add(LawModel("29", "Empregado Doméstico", "domestico.html", "Legislação"))
        } else {
            allLaws.add(LawModel("j1", "STF - Pesquisa de Jurisprudência", "https://jurisprudencia.stf.jus.br/", "Jurisprudência"))
            allLaws.add(LawModel("j2", "STF - Temas de Repercussão Geral", "https://portal.stf.jus.br/jurisprudencia/repercussaogeral/", "Jurisprudência"))
            allLaws.add(LawModel("j3", "STJ - Pesquisa de Jurisprudência", "https://scon.stj.jus.br/SCON/", "Jurisprudência"))
            allLaws.add(LawModel("j4", "STJ - Repetitivos e Enunciados", "https://www.stj.jus.br/sites/portalp/Jurisprudencia/Repetitivos", "Jurisprudência"))
        }
    }
}
