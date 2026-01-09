package com.vadejurisbr

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListFragment : Fragment() {

    private lateinit var adapter: LawAdapter
    private val allLaws = mutableListOf<LawModel>()
    private var category: String = ""

    companion object {
        private const val ARG_CATEGORY = "category"

        // Extras usados pelo ReaderActivity
        private const val EXTRA_LAW_ID = "LAW_ID"
        private const val EXTRA_LAW_TITLE = "LAW_TITLE"
        private const val EXTRA_FILE_NAME = "FILE_NAME"

        private const val CATEGORY_LEGISLACAO = "Legislação"
        private const val CATEGORY_JURIS = "Jurisprudência"

        fun newInstance(category: String): ListFragment {
            val fragment = ListFragment()
            fragment.arguments = Bundle().apply {
                putString(ARG_CATEGORY, category)
            }
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        category = arguments?.getString(ARG_CATEGORY).orEmpty()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        val searchEditText = view.findViewById<EditText>(R.id.searchEditText)

        setupData()

        adapter = LawAdapter(allLaws) { law ->
            if (law.category == CATEGORY_JURIS) {
                openExternalLink(law.fileName)
            } else {
                // use requireContext() para evitar context nulo em Fragment
                val intent = Intent(requireContext(), ReaderActivity::class.java).apply {
                    putExtra(EXTRA_LAW_ID, law.id)
                    putExtra(EXTRA_LAW_TITLE, law.title)
                    putExtra(EXTRA_FILE_NAME, law.fileName)
                }
                startActivity(intent)
            }
        }

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filter(s?.toString().orEmpty())
            }
            override fun afterTextChanged(s: Editable?) { }
        })

        return view
    }

    private fun openExternalLink(url: String) {
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(requireContext(), "Nenhum navegador encontrado para abrir o link.", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Toast.makeText(requireContext(), "Erro ao abrir o link.", Toast.LENGTH_SHORT).show()
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

        if (category == CATEGORY_LEGISLACAO) {
            allLaws.add(LawModel("1",  "Constituição Federal (CF/88)", "cf.html", CATEGORY_LEGISLACAO))
            allLaws.add(LawModel("2",  "LINDB", "lindb.html", CATEGORY_LEGISLACAO))
            allLaws.add(LawModel("3",  "Código Civil", "cc.html", CATEGORY_LEGISLACAO))
            allLaws.add(LawModel("4",  "Código de Processo Civil", "cpc.html", CATEGORY_LEGISLACAO))
            allLaws.add(LawModel("5",  "Código Penal", "cp.html", CATEGORY_LEGISLACAO))
            allLaws.add(LawModel("6",  "Código de Processo Penal", "cpp.html", CATEGORY_LEGISLACAO))
            allLaws.add(LawModel("7",  "Lei de Introdução ao Código Penal", "licp.html", CATEGORY_LEGISLACAO))
            allLaws.add(LawModel("8",  "Lei das Contravenções Penais", "lcp.html", CATEGORY_LEGISLACAO))
            allLaws.add(LawModel("9",  "Lei de Execução Penal (LEP)", "lep.html", CATEGORY_LEGISLACAO))
            allLaws.add(LawModel("10", "Lei dos Crimes Hediondos", "hediondos.html", CATEGORY_LEGISLACAO))
            allLaws.add(LawModel("11", "Lei dos Crimes contra a Ordem Tributária", "tributarios.html", CATEGORY_LEGISLACAO))
            allLaws.add(LawModel("12", "Lei Antidrogas", "drogas.html", CATEGORY_LEGISLACAO))
            allLaws.add(LawModel("13", "Código Tributário Nacional (CTN)", "ctn.html", CATEGORY_LEGISLACAO))
            allLaws.add(LawModel("14", "Código de Defesa do Consumidor (CDC)", "cdc.html", CATEGORY_LEGISLACAO))
            allLaws.add(LawModel("15", "Código Eleitoral", "eleitoral.html", CATEGORY_LEGISLACAO))
            allLaws.add(LawModel("16", "Código Florestal", "florestal.html", CATEGORY_LEGISLACAO))
            allLaws.add(LawModel("17", "CLT", "clt.html", CATEGORY_LEGISLACAO))
            allLaws.add(LawModel("18", "Estatuto da Criança e do Adolescente (ECA)", "eca.html", CATEGORY_LEGISLACAO))
            allLaws.add(LawModel("19", "Estatuto da Pessoa Idosa", "idoso.html", CATEGORY_LEGISLACAO))
            allLaws.add(LawModel("20", "Estatuto da Igualdade Racial", "racial.html", CATEGORY_LEGISLACAO))
            allLaws.add(LawModel("21", "Estatuto da Pessoa com Deficiência", "pcd.html", CATEGORY_LEGISLACAO))
            allLaws.add(LawModel("22", "Estatuto da Juventude", "juventude.html", CATEGORY_LEGISLACAO))
            allLaws.add(LawModel("23", "Estatuto da Cidade", "cidade.html", CATEGORY_LEGISLACAO))
            allLaws.add(LawModel("24", "Lei Maria da Penha", "maria_penha.html", CATEGORY_LEGISLACAO))
            allLaws.add(LawModel("25", "LDB (Lei de Diretrizes e Bases da Educação)", "ldb.html", CATEGORY_LEGISLACAO))
            allLaws.add(LawModel("26", "Lei de Licitações (Nova)", "licitacoes.html", CATEGORY_LEGISLACAO))
            allLaws.add(LawModel("27", "Lei de Responsabilidade Fiscal (LRF)", "lrf.html", CATEGORY_LEGISLACAO))
            allLaws.add(LawModel("28", "Servidores Públicos (Lei 8112)", "servidores.html", CATEGORY_LEGISLACAO))
            allLaws.add(LawModel("29", "Empregado Doméstico", "domestico.html", CATEGORY_LEGISLACAO))
        } else {
            allLaws.add(LawModel("j1", "STF — Pesquisa de Jurisprudência", "https://jurisprudencia.stf.jus.br/", CATEGORY_JURIS))
            allLaws.add(LawModel("j2", "STF — Temas de Repercussão Geral", "https://portal.stf.jus.br/jurisprudencia/repercussaogeral/", CATEGORY_JURIS))
            allLaws.add(LawModel("j3", "STJ — Pesquisa de Jurisprudência", "https://scon.stj.jus.br/SCON/", CATEGORY_JURIS))
            allLaws.add(LawModel("j4", "STJ — Repetitivos e Enunciados", "https://www.stj.jus.br/sites/portalp/Jurisprudencia/Repetitivos", CATEGORY_JURIS))
        }
    }
}
