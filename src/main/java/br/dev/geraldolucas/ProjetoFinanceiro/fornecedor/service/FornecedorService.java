package br.dev.geraldolucas.ProjetoFinanceiro.fornecedor.service;

import br.dev.geraldolucas.ProjetoFinanceiro.fornecedor.model.Fornecedor;
import br.dev.geraldolucas.ProjetoFinanceiro.fornecedor.repository.FornecedorRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.List;


@Service
public class FornecedorService {

    private final FornecedorRepository fornecedorRepository;
    private final ModelMapper modelMapper;

    public FornecedorService(FornecedorRepository fornecedorRepository, ModelMapper modelMapper){
        this.fornecedorRepository = fornecedorRepository;
        this.modelMapper = modelMapper;
    }

    //Listar todos os fornecedores
    public List<Fornecedor> listarTodosFornecedores(){
        return fornecedorRepository.findAll();
    }

    //Listar apenas forncedores ativos
    public List<Fornecedor> listarFornecedoresAtivos(){
        return fornecedorRepository.findAllByAtivoTrue();
    }

    //Listar apenas forncedores ativos
    public List<Fornecedor> listarFornecedoresDesativados(){
        return fornecedorRepository.findAllByAtivoFalse();
    }

    //Listar por ID
    public Fornecedor listarFornecedoresPorID(Long id){
        return fornecedorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado com id: " + id));
    }

    //Criar um novo fornecedor
    @Transactional
    public Fornecedor criarFornecedor(Fornecedor fornecedor){
        return fornecedorRepository.save(fornecedor);
    }

    //"Deletar" o fornecedor, mudando status de ativo para false
    @Transactional
    public void deletarFornecedor(Long id){
        Fornecedor fornecedorParaDeletar = listarFornecedoresPorID(id);
        fornecedorParaDeletar.setAtivo(false);
        fornecedorRepository.save(fornecedorParaDeletar);
    }

    @Transactional
    public Fornecedor atualizarFornecedor(Long id, Fornecedor dadosParciais){
        Fornecedor fornecedorAtual = listarFornecedoresPorID(id);
        modelMapper.map(dadosParciais, fornecedorAtual);
        return fornecedorRepository.save(fornecedorAtual);
    }

}
