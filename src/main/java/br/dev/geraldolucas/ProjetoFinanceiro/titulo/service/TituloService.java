package br.dev.geraldolucas.ProjetoFinanceiro.titulo.service;

import br.dev.geraldolucas.ProjetoFinanceiro.enums.StatusTitulo;
import br.dev.geraldolucas.ProjetoFinanceiro.titulo.model.Titulo;
import br.dev.geraldolucas.ProjetoFinanceiro.titulo.repository.TituloRepository;
import org.springframework.transaction.annotation.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TituloService {
    private final TituloRepository tituloRepository;
    private final ModelMapper modelMapper;

    public TituloService(TituloRepository tituloRepository, ModelMapper modelMapper) {
        this.tituloRepository = tituloRepository;
        this.modelMapper = modelMapper;
    }

    //Listar todos os titulos
    public List<Titulo> listaTodos() {
        return tituloRepository.findAll();
    }

    //Listar por ID
    public Titulo listarTituloPorID(Long id){
        return tituloRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Titulo não encontrado com id: " + id));
    }

    //Listar pagos
    public List<Titulo> listarPagos(){
        return tituloRepository.findByStatus(StatusTitulo.PAGO);
    }

    //Listar pendentes
    public List<Titulo> listarPendentes(){
        return tituloRepository.findByStatus(StatusTitulo.PENDENTE);
    }

   //Listar titulos de um fornecedor podendo ou não  listar também com status
    public List<Titulo> listarPorFornecedorEStatus(Long fornecedorId,StatusTitulo status){
        if(status == null){
            return tituloRepository.findByFornecedorId(fornecedorId);
        }
        return tituloRepository.findByFornecedorIdAndStatus(fornecedorId, status);
    }

    //Criar um novo titulo
    @Transactional
    public Titulo criarTitulo(Titulo titulo){
        //Titulos novos são adicionados como pendente
        titulo.setStatus(StatusTitulo.PENDENTE);
        return tituloRepository.save(titulo);
    }

    //Deletar um titulo
    @Transactional
    public void cancelarTitulo(Long id){
        Titulo titulo = listarTituloPorID(id);
        // Regra de negócio: para não cancelar um título que já foi pago!
        if (titulo.getStatus() == StatusTitulo.PAGO) {
            throw new RuntimeException("Não é possível cancelar um título já pago!");
        }
        titulo.setStatus(StatusTitulo.CANCELADO);
        tituloRepository.save(titulo);
    }

    //Atulizar titulo utilizando PATCH
    @Transactional
    public Titulo atualizarTitulo(Long id, Titulo tituloDadosParciais){
        Titulo tituloAtual = listarTituloPorID(id);
        // Impede que o PATCH altere o status, só sendo pussivel mudar o status por pagamento ou cancelamento
        tituloDadosParciais.setStatus(null);
        modelMapper.map(tituloDadosParciais, tituloAtual);
        return tituloRepository.save(tituloAtual);

    }
}
