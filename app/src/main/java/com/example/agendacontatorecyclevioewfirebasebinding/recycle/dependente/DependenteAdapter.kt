
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.agendacontatorecyclevioewfirebasebinding.R
import com.example.agendacontatorecyclevioewfirebasebinding.model.Contato
import com.example.agendacontatorecyclevioewfirebasebinding.model.Dependente
import com.example.agendacontatorecyclevioewfirebasebinding.recycle.chamado.DependenteViewHolder
import com.example.agendacontatorecyclevioewfirebasebinding.repository.DependenteRepository

class DependenteAdapter(private val contexto: Context, private val lista: ArrayList<Dependente>) :
    RecyclerView.Adapter<DependenteViewHolder>() {
    private var inflater: LayoutInflater = LayoutInflater.from(contexto)
    private var repositorio = DependenteRepository()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DependenteViewHolder {
        val view = inflater.inflate(
            R.layout.dependente_row_layout,
            parent, false)
        return DependenteViewHolder( view )
    }
    override fun getItemCount(): Int {
        return lista.size
    }
    override fun onBindViewHolder(holder: DependenteViewHolder, position: Int) {
        val dependente = lista[position]
        holder.txtNome.text = "Nome do dependente: " + dependente.nome
        holder.txtRg.text = "RG do dependente: " + dependente.rg
        holder.txtApto.text = "Apto do dependente: " + dependente.apto
        holder.btnRowApagar.setOnClickListener {
            Log.i("AGENDA-DEPENDENTE",
                "Contato selecionado para apagar: $dependente")
            repositorio.apagarDependente(dependente)
        }
    }
}